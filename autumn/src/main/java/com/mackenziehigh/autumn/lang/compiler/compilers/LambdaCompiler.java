package com.mackenziehigh.autumn.lang.compiler.compilers;

import autumn.lang.compiler.ClassFile;
import autumn.lang.compiler.ast.nodes.LambdaStatement;
import autumn.lang.internals.Helpers;
import autumn.util.F;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mackenziehigh.autumn.lang.compiler.typesystem.CustomDeclaredType;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IClassType;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IExpressionType;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IFormalParameter;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IMethod;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IReferenceType;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IVariableType;
import com.mackenziehigh.autumn.lang.compiler.utils.TypeSystemUtils;
import com.mackenziehigh.autumn.lang.compiler.utils.Utils;
import java.util.List;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

/**
 * An instance of this this class controls the compilation of a lambda function.
 *
 * @author Mackenzie High
 */
final class LambdaCompiler
        extends AbstractFunctionCompiler
        implements ICompiler
{
    private static final class SpecialParameter
    {
        public int index;
    }

    /**
     * Essentially, this is the enclosing function that is being compiled.
     */
    private final FunctionCompiler function;

    /**
     * This is the Abstract-Syntax-Tree representation of the lambda function.
     */
    private final LambdaStatement node;

    /**
     * This is the type-system representation of the lambda function.
     */
    private CustomDeclaredType lambda_type;

    private IClassType functor_type;

    private IMethod invoke;

    private final List<String> captured = Lists.newLinkedList();

    /**
     * Sole Constructor.
     *
     * @param function is essentially the regular function that encloses the lambda-statement.
     */
    public LambdaCompiler(final FunctionCompiler function,
                          final LambdaStatement node)
    {
        /**
         * The variable allocator cannot use the first two slots (i.e. #0 and #1),
         * because apply(ArgumentStack) uses #0 for 'this' and #1 for the ArgumentStack.
         */
        super(function.module, new VariableAllocator(2));

        this.function = function;
        this.node = node;

        this.performTypeDeclaration();
        this.performTypeInitialization();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeDeclaration()
    {
        /**
         * Determine the descriptor of the tuple.
         */
        final String namespace = function.module.type.getNamespace().replace('.', '/');
        final String name = "autumn$lambda$" + F.unique();
        final String descriptor = "L" + namespace + '/' + name + ';';

        /**
         * Declare the type.
         */
        this.lambda_type = program.typesystem.typefactory().newClassType(descriptor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeInitialization()
    {
        functor_type = module.imports.resolveDefinedFunctorType(node.getType());

        lambda_type.setSuperclass(program.typesystem.utils.ABSTRACT_LAMBDA);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeStructureChecking()
    {
        // Pass
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeUsageChecking()
    {
        try
        {
            /**
             * Resolve the invoke(*) method.
             */
            invoke = TypeSystemUtils.find(functor_type.getMethods(), "invoke");

            /**
             * A lambda-statement defines a nested scope that covers its parameters and body.
             */
            allocator.enterScope();

            /**
             * A lambda declares a special variable to store itself.
             */
            allocator.declareVal("$0", program.typesystem.utils.LAMBDA);

            /**
             * A lambda declares special variables to store its parameters.
             */
            for (int i = 0; i < invoke.getParameters().size(); i++)
            {
                final String variable_name = "$" + (i + 1);

                final IVariableType variable_type = invoke.getParameters().get(i).getType();

                /**
                 * The variable cannot be allowed to hide a variable in the enclosing function.
                 */
                // TODO: and remember to do this for $0 too
                //
                //
                /**
                 * Declare the variable.
                 */
                allocator.declareVal(variable_name, variable_type);
            }

            /**
             * A lambda captures all the declared local variables in the enclosing regular function,
             * except the variable that stores the lambda itself.
             */
            for (String variable : function.allocator.getVariables())
            {
                /**
                 * Skip the variable that stores the lambda.
                 */
                if (variable.equals(node.getVariable().getName()))
                {
                    continue;
                }

                /**
                 * Declare the variable in the lambda's scope.
                 */
                allocator.declareVal(variable, function.allocator.typeOf(variable));

                /**
                 * Remember the variable for later use.
                 */
                captured.add(variable);
            }

            /**
             * Visit and type-check the lambda's body.
             */
            final ExpressionTypeChecker checker = new ExpressionTypeChecker(this, this.allocator);

            node.getBody().accept(checker);

            /**
             * The return-type of the expression must match the return-type of the lambda.
             * However, the type of the body is not important, if the return-type is void.
             */
            if (invoke.getReturnType().isVoidType() == false)
            {
                program.checker.checkReturn(node, invoke.getReturnType(), node.getBody());
            }

        }
        catch (TypeCheckFailed ex)
        {
            throw ex;
        }
        finally
        {
            /**
             * This must always be done; otherwise, the scope management could get messed up.
             */
            allocator.exitScope();
        }
    }

    /**
     * {@inheritDoc}
     */
    public ClassFile build()
    {
        final List<MethodNode> methods = Lists.newLinkedList();

        methods.add(this.generateConstructor());
        methods.add(this.generateMethodApply());
        methods.add(this.generateMethodFile());
        methods.add(this.generateMethodLine());
        methods.add(this.generateMethodColumn());
        methods.add(this.generateMethodParameterTypes());
        methods.add(this.generateMethodReturnType());

        final String lambda_internal_name = Utils.internalName(lambda_type);

        final String lambda_source_name = Utils.sourceName(lambda_type);

        final ClassNode clazz = new ClassNode();
        {
            clazz.version = Opcodes.V1_6;
            clazz.visibleAnnotations = ImmutableList.of();
            clazz.access = Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL;
            clazz.name = lambda_internal_name;
            clazz.superName = Utils.internalName(lambda_type.getSuperclass());
            clazz.interfaces = Lists.newLinkedList();
            clazz.fields = Lists.newLinkedList();
            clazz.methods = ImmutableList.copyOf(methods);
            clazz.sourceFile = String.valueOf(node.getLocation().getFile());

            assert clazz.superName.equals("autumn/lang/internals/AbstractLambda");
            assert clazz.access == Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL;
        }

        final ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);

        clazz.accept(writer);

        final byte[] bytecode = writer.toByteArray();

        final ClassFile file = new ClassFile(lambda_source_name, bytecode);

        return file;
    }

    /**
     * This method generates the bytecode representation of the lambda's only constructor.
     *
     * @return the generated bytecode.
     */
    private MethodNode generateConstructor()
    {
        final MethodNode ctor = new MethodNode();
        ctor.access = Opcodes.ACC_PUBLIC;
        ctor.name = "<init>";
        ctor.desc = "()V";
        ctor.exceptions = ImmutableList.of();


        ctor.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0)); // Load 'this'
        ctor.instructions.add(new MethodInsnNode(Opcodes.INVOKESPECIAL,
                                                 Utils.internalName(program.typesystem.utils.ABSTRACT_LAMBDA),
                                                 "<init>",
                                                 "()V"));

        ctor.instructions.add(new InsnNode(Opcodes.RETURN));

        return ctor;
    }

    /**
     * This method generates the bytecode representation of the file() method.
     *
     * @return the generated bytecode.
     */
    private MethodNode generateMethodFile()
    {
        final MethodNode method = Utils.bytecodeOf(function.module,
                                                   TypeSystemUtils.find(program.typesystem.utils.ABSTRACT_LAMBDA.getAllVisibleMethods(),
                                                                        "file"));

        // Remove the abstract modifier.
        method.access = method.access & (~Opcodes.ACC_ABSTRACT);

        method.instructions.add(new LdcInsnNode(node.getLocation().getFile().toString()));
        method.instructions.add(new InsnNode(Opcodes.ARETURN));

        return method;
    }

    /**
     * This method generates the bytecode representation of the line() method.
     *
     * @return the generated bytecode.
     */
    private MethodNode generateMethodLine()
    {
        final MethodNode method = Utils.bytecodeOf(function.module,
                                                   TypeSystemUtils.find(program.typesystem.utils.ABSTRACT_LAMBDA.getAllVisibleMethods(),
                                                                        "line"));

        // Remove the abstract modifier.
        method.access = method.access & (~Opcodes.ACC_ABSTRACT);

        method.instructions.add(new LdcInsnNode(node.getLocation().getLine()));
        method.instructions.add(new InsnNode(Opcodes.IRETURN));

        return method;
    }

    /**
     * This method generates the bytecode representation of the column() method.
     *
     * @return the generated bytecode.
     */
    private MethodNode generateMethodColumn()
    {
        final MethodNode method = Utils.bytecodeOf(function.module,
                                                   TypeSystemUtils.find(program.typesystem.utils.ABSTRACT_LAMBDA.getAllVisibleMethods(),
                                                                        "column"));

        // Remove the abstract modifier.
        method.access = method.access & (~Opcodes.ACC_ABSTRACT);

        method.instructions.add(new LdcInsnNode(node.getLocation().getColumn()));
        method.instructions.add(new InsnNode(Opcodes.IRETURN));

        return method;
    }

    /**
     * This method generates the bytecode representation of the parameterTypes() method.
     *
     * @return the generated bytecode.
     */
    private MethodNode generateMethodParameterTypes()
    {
        final MethodNode method = Utils.bytecodeOf(function.module,
                                                   TypeSystemUtils.find(program.typesystem.utils.ABSTRACT_LAMBDA.getAllVisibleMethods(),
                                                                        "parameterTypes"));

        // Remove the abstract modifier.
        method.access = method.access & (~Opcodes.ACC_ABSTRACT);

        final CollectionCompiler<IFormalParameter> cmp = new CollectionCompiler<IFormalParameter>()
        {
            @Override
            public void compile(final IFormalParameter element)
            {
                code().add(Utils.ldcClass(element.getType()));
            }

            @Override
            public InsnList code()
            {
                return method.instructions;
            }
        };

        /**
         * Generate the bytecode that creates the mutable list of class objects.
         */
        cmp.compile(invoke.getParameters());

        /**
         * Convert the mutableList to an immutable list.
         */
        Utils.invoke(method.instructions,
                     Opcodes.INVOKESTATIC,
                     Helpers.class,
                     List.class,
                     "newImmutableList",
                     Iterable.class);

        /**
         * Return from the method.
         */
        method.instructions.add(new InsnNode(Opcodes.ARETURN));

        return method;
    }

    /**
     * This method generates the bytecode representation of the returnType() method.
     *
     * @return the generated bytecode.
     */
    private MethodNode generateMethodReturnType()
    {
        final MethodNode method = Utils.bytecodeOf(function.module,
                                                   TypeSystemUtils.find(program.typesystem.utils.ABSTRACT_LAMBDA.getAllVisibleMethods(),
                                                                        "returnType"));

        // Remove the abstract modifier.
        method.access = method.access & (~Opcodes.ACC_ABSTRACT);

        method.instructions.add(Utils.ldcClass(invoke.getReturnType()));
        method.instructions.add(new InsnNode(Opcodes.ARETURN));

        return method;
    }

    /**
     * This method generates the bytecode representation of the apply(ArgumentStack) method.
     *
     * @return the generated bytecode.
     */
    private MethodNode generateMethodApply()
    {
        final MethodNode method = Utils.bytecodeOf(function.module,
                                                   TypeSystemUtils.find(program.typesystem.utils.ABSTRACT_LAMBDA.getAllVisibleMethods(),
                                                                        "apply"));

        // Remove the abstract modifier.
        method.access = method.access & (~Opcodes.ACC_ABSTRACT);

        method.instructions = instructions;

        final IExpressionType body_type = program.symbols.expressions.get(node.getBody());

        final ExpressionCodeGenerator codegen = new ExpressionCodeGenerator(module, vars, instructions);

        /**
         * Push the argument-stack onto the operand-stack,
         * because it will be needed for the return-value.
         */
        instructions.add(new VarInsnNode(Opcodes.ALOAD, 1));

        /**
         * Set argument $0 to 'this'.
         */
        instructions.add(new VarInsnNode(Opcodes.ALOAD, 0));
        vars.store("$0");

        /**
         * Transfer the arguments from the argument-stack to the call-stack.
         */
        setArgumentVariables();

        /**
         * Transfer the captured variables from inside the lambda object to the call-stack.
         */
        setClosureVariables();

        /**
         * Generate the bytecode of the body.
         */
        node.getBody().accept(codegen);

        /**
         * Clear the argument-stack.
         * Simply retrieving the argument-stack causes it to be cleared.
         */
        Utils.loadArgumentStack(method.instructions);
        method.instructions.add(new InsnNode(Opcodes.POP));

        /**
         * Convert the return-value to the return-type.
         */
        if (invoke.getReturnType().isVoidType() == false)
        {
            program.typesystem.utils.assign(body_type, invoke.getReturnType());
        }

        /**
         * Transfer the return-value from the operand-stack to the argument-stack.
         */
        if (invoke.getReturnType().isVoidType() == false)
        {
            Utils.pushArgument(program, instructions, body_type);
        }

        instructions.add(new InsnNode(Opcodes.RETURN));

        return method;
    }

    private void setArgumentVariables()
    {
        /**
         * Pop the arguments off of the argument-stack
         * and store them in variables on the call-stack.
         */
        for (int i = invoke.getParameters().size() - 1; i >= 0; i--)
        {
            final String variable_name = "$" + (i + 1);

            final IVariableType variable_type = invoke.getParameters().get(i).getType();

            setArgumentVariable(variable_name, variable_type);
        }
    }

    private void setArgumentVariable(final String name,
                                     final IVariableType type)
    {
        // Generated Bytecode:
        //

        instructions.add(new VarInsnNode(Opcodes.ALOAD, 1));
        Utils.peekArgument(program, instructions, type);

        instructions.add(new VarInsnNode(Opcodes.ALOAD, 1));
        Utils.popArgument(program, instructions);

        vars.store(name);
    }

    private void setClosureVariables()
    {
        for (String variable : captured)
        {
            final IVariableType variable_type = function.allocator.typeOf(variable);

            setClosureVariable(variable, variable_type);
        }
    }

    private void setClosureVariable(final String variable,
                                    final IVariableType type)
    {
        // Generated Bytecode:
        //

        instructions.add(new InsnNode(Opcodes.NOP));

        /**
         * Load the lambda itself onto the operand-stack.
         */
        instructions.add(new VarInsnNode(Opcodes.ALOAD, 0));

        /**
         * Load the name of the variable onto the operand-stack.
         */
        instructions.add(new LdcInsnNode(variable));

        /**
         * Transfer the value of the variable from inside the lambda object to the operand-stack.
         */
        final String owner = "autumn/lang/Lambda";
        String name;
        String desc;

        if (type.equals(program.typesystem.utils.PRIMITIVE_BOOLEAN))
        {
            name = "valueAsBoolean";
            desc = "(Ljava/lang/String;)Z";
            instructions.add(new MethodInsnNode(Opcodes.INVOKEINTERFACE, owner, name, desc));
        }
        else if (type.equals(program.typesystem.utils.PRIMITIVE_CHAR))
        {
            name = "valueAsChar";
            desc = "(Ljava/lang/String;)C";
            instructions.add(new MethodInsnNode(Opcodes.INVOKEINTERFACE, owner, name, desc));
        }
        else if (type.equals(program.typesystem.utils.PRIMITIVE_BYTE))
        {
            name = "valueAsByte";
            desc = "(Ljava/lang/String;)B";
            instructions.add(new MethodInsnNode(Opcodes.INVOKEINTERFACE, owner, name, desc));
        }
        else if (type.equals(program.typesystem.utils.PRIMITIVE_SHORT))
        {
            name = "valueAsShort";
            desc = "(Ljava/lang/String;)S";
            instructions.add(new MethodInsnNode(Opcodes.INVOKEINTERFACE, owner, name, desc));
        }
        else if (type.equals(program.typesystem.utils.PRIMITIVE_INT))
        {
            name = "valueAsInt";
            desc = "(Ljava/lang/String;)I";
            instructions.add(new MethodInsnNode(Opcodes.INVOKEINTERFACE, owner, name, desc));
        }
        else if (type.equals(program.typesystem.utils.PRIMITIVE_LONG))
        {
            name = "valueAsLong";
            desc = "(Ljava/lang/String;)J";
            instructions.add(new MethodInsnNode(Opcodes.INVOKEINTERFACE, owner, name, desc));
        }
        else if (type.equals(program.typesystem.utils.PRIMITIVE_FLOAT))
        {
            name = "valueAsFloat";
            desc = "(Ljava/lang/String;)F";
            instructions.add(new MethodInsnNode(Opcodes.INVOKEINTERFACE, owner, name, desc));
        }
        else if (type.equals(program.typesystem.utils.PRIMITIVE_DOUBLE))
        {
            name = "valueAsDouble";
            desc = "(Ljava/lang/String;)D";
            instructions.add(new MethodInsnNode(Opcodes.INVOKEINTERFACE, owner, name, desc));
        }
        else
        {
            assert type.isReferenceType();

            final String internal = Utils.internalName((IReferenceType) type);

            name = "valueAsObject";
            desc = "(Ljava/lang/String;)Ljava/lang/Object;";
            instructions.add(new MethodInsnNode(Opcodes.INVOKEINTERFACE, owner, name, desc));
            instructions.add(new TypeInsnNode(Opcodes.CHECKCAST, internal));
        }

        /**
         * Place the value of the variable into the variable slot that will store it.
         */
        vars.store(variable);

        instructions.add(new InsnNode(Opcodes.NOP));
    }

    /**
     * This method generates the bytecode needed to instantiate the lambda
     * and load it onto the operand-stack.
     *
     * @param code is the bytecode being generated.
     */
    public void load(final InsnList code)
    {
        // Generated Bytecode:
        //
        // NEW functor-type                     - Create a new uninitialized instance of the functor.
        // DUP                                  - Duplicate the reference thereto.
        //                                      - Note: The functor object is still uninitialized.
        //
        //                                      - Now, thestack looks like [functor, functor].
        //
        // NEW lambda-type                      - Create a new uninitialized instance of the lambda.
        // DUP                                  - Duplicate the reference thereto.
        // INVOKESPECIAL <init>()V              - Initialize the lambda-type using its no-arg constructor.
        //
        //                                      - Now, the stack looks like [functor, functor, lambda].
        //
        // <capture locals>                     - Generate bytecode to capture the local variables and place them into the closure.
        //
        // INVOKESPECIAL <init>(TypedFunctor)   - Initialize the functor object.
        //                                      - Notice the functor will subsequently contain the lambda as its inner functor.
        //
        //                                      - Now the stack looks like [functor].
        //
        ///////////////////////////////////////////////////////////////////////////////////////////

        code.add(new TypeInsnNode(Opcodes.NEW, Utils.internalName(functor_type)));
        code.add(new InsnNode(Opcodes.DUP));

        code.add(new TypeInsnNode(Opcodes.NEW, Utils.internalName(lambda_type)));
        code.add(new InsnNode(Opcodes.DUP));
        code.add(new MethodInsnNode(Opcodes.INVOKESPECIAL,
                                    Utils.internalName(lambda_type),
                                    "<init>",
                                    "()V"));

        capture(code);

        code.add(new MethodInsnNode(Opcodes.INVOKESPECIAL,
                                    Utils.internalName(functor_type),
                                    "<init>",
                                    "(Lautumn/lang/TypedFunctor;)V"));

    }

    /**
     * This method generates bytecode that captures the local variables to form a closure.
     *
     * @param code is the bytecode being generated.
     */
    private void capture(final InsnList code)
    {
        /**
         * For each variable in the scope of the enclosing regular function.
         */
        for (String variable : function.vars.allocator().getVariables())
        {
            /**
             * Do not capture the variable that will store the lambda.
             * The variable is uninitialized before the lambda is created.
             * So, you would always be capturing null.
             */
            if (variable.equals(node.getVariable().getName()))
            {
                continue;
            }

            /**
             * Get the type of the variable that will be captured.
             */
            final IVariableType variable_type = function.allocator.typeOf(variable);

            /**
             * Duplicate the reference to the lambda.
             */
            code.add(new InsnNode(Opcodes.DUP));

            /**
             * Load the name of the variable onto the operand-stack.
             */
            code.add(new LdcInsnNode(variable));

            /**
             * Load the value of the variable onto the operand-stack.
             */
            function.vars.load(variable);

            /**
             * Box the value of the variable, if it is not a reference-type already.
             */
            program.typesystem.utils.autoboxToObject(code, variable_type);

            /**
             * Capture the variable.
             *
             * In other words, place the variable's name and the variable's value into the closure.
             */
            code.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL,
                                        Utils.internalName(program.typesystem.utils.ABSTRACT_LAMBDA),
                                        "capture",
                                        "(Ljava/lang/String;Ljava/lang/Object;)V"));
        }
    }
}
