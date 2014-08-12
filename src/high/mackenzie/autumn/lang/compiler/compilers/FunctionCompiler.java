/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.compiler.ast.nodes.FormalParameter;
import autumn.lang.compiler.ast.nodes.FunctionDefinition;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import high.mackenzie.autumn.lang.compiler.exceptions.TypeCheckFailed;
import high.mackenzie.autumn.lang.compiler.typesystem.CustomFormalParameter;
import high.mackenzie.autumn.lang.compiler.typesystem.CustomMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IAnnotation;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IAnnotationType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IExpressionType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IFormalParameter;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVariableType;
import high.mackenzie.autumn.lang.compiler.utils.Utils;
import java.util.Collections;
import java.util.List;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TableSwitchInsnNode;
import org.objectweb.asm.tree.TryCatchBlockNode;
import org.objectweb.asm.tree.TypeInsnNode;

/**
 * An instance of this class controls the compilation of a function-definition.
 *
 * @author Mackenzie High
 */
public final class FunctionCompiler
        implements ICompiler
{
    /**
     * This object represents the program that contains the function.
     *
     * More specifically, this is the compiler that is compiling the program.
     */
    public final ProgramCompiler program;

    /**
     * This object represents the module that contains the function.
     *
     * More specifically, this is the compiler that is compiling the module.
     */
    public final ModuleCompiler module;

    /**
     * This is the Abstract-Syntax-Tree representation of the function.
     */
    public final FunctionDefinition node;

    /**
     * These are the bytecode declarations of the try-catch blocks in the function.
     */
    public final List<TryCatchBlockNode> trycatches = Lists.newLinkedList();

    /**
     * These are the bytecode instructions that constitute the body of the function.
     */
    public final InsnList instructions = new InsnList();

    /**
     * This is the type-system representation of the function.
     */
    public final CustomMethod type;

    /**
     * This object manages the allocation of local variables in the function.
     */
    public final VariableScope scope;

    /**
     * This object simplifies the generation of bytecode that manipulates local variables.
     */
    public final VariableManipulator vars;

    /**
     * This object manages the allocation of user-visible labels in the function.
     */
    public final LabelScope labels;

    /**
     * This is field-node represents the field that stores the state of the function
     * between invocations. Thus, if this field is non-null, the function is a generator function.
     */
    private FieldNode yield_field = null;

    /**
     * This label marks the location that recur-statements redirect execution to.
     */
    public final LabelNode recur_label = new LabelNode();

    /**
     * These labels mark the reentry points of yield statements.
     */
    public final List<LabelNode> yields = Lists.newLinkedList();

    /**
     * Sole Constructor.
     *
     * @param module is the compiler of the module that this definition is part of.
     * @param node is the AST node that represents this definition.
     */
    public FunctionCompiler(final ModuleCompiler module,
                            final FunctionDefinition node)
    {
        this.program = module.program;
        this.module = module;
        this.node = node;

        this.type = new CustomMethod(module.program.typesystem.typefactory(), false);
        this.scope = new VariableScope(null, 0);
        this.vars = new VariableManipulator(scope, this.instructions);
        this.labels = new LabelScope(program);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeDeclaration()
    {
        // Do not do anything during this pass.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeInitialization()
    {
        // Add this function's type to the collection of functions in the module's type.
        final List<IMethod> functions = Lists.newLinkedList(module.type.getMethods());
        functions.add(type);
        module.type.setMethods(functions);

        final int modifiers = Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC + Opcodes.ACC_FINAL;

        final List<IFormalParameter> params = Lists.newLinkedList();
        {
            for (FormalParameter p : node.getParameters().getParameters())
            {
                final IVariableType param_type = (IVariableType) module.imports.resolveReturnType(p.getType());

                final CustomFormalParameter param = new CustomFormalParameter();

                param.setAnnotations(ImmutableList.<IAnnotation>of());

                param.setType(param_type);

                params.add(param);

                scope.declareParameter(p.getVariable(), param_type);
            }
        }

        type.setOwner(module.type);
        type.setAnnotations(module.anno_utils.typesOf(node.getAnnotations()));
        type.setModifiers(modifiers);
        type.setName(node.getName().getName());
        type.setParameters(params);
        type.setReturnType(module.imports.resolveReturnType(node.getReturnType()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeStructureChecking()
    {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeUsageChecking()
    {
        try
        {
            final StatementTypeChecker checker = new StatementTypeChecker(this);

            node.getBody().accept(checker);

            labels.check();
        }
        catch (TypeCheckFailed ex)
        {
            // Pass
        }
    }

    public MethodNode build()
    {
        // TODO: This should be done in the type initiailiztion phase.
        final int sync = yield_field == null ? 0 : Opcodes.ACC_SYNCHRONIZED;

        final MethodNode method = Utils.bytecodeOf(module, type);

        method.instructions.clear();
        {
            final StatementCodeGenerator codegen = new StatementCodeGenerator(this);

            method.instructions.add(recur_label);

            vars.initScope();

            addReentryTable();

            node.getBody().accept(codegen);

            for (AbstractInsnNode x : instructions.toArray())
            {
                method.instructions.add(x);
            }
        }
//        method.instructions.add(new FieldInsnNode(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;"));
//        method.instructions.add(new LdcInsnNode("Hello World!"));
//        method.instructions.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V"));
//        method.instructions.add(new InsnNode(Opcodes.RETURN));

        addDefaultMethodTermination(method);

        method.tryCatchBlocks = ImmutableList.copyOf(trycatches);

        return method;
    }

    /**
     * This method generates the bytecode that handles reentry into a generator function.
     *
     * <p>
     * When a generator function is entered, it is necessary to jump to the location
     * where the function last yielded from. As a result, the beginning of the function's
     * bytecode must contain a jump-table that redirects execution. This method generates
     * that jump-table.
     * </p>
     */
    private void addReentryTable()
    {
        // TODO: Maybe we should move the restoration code to the method.

        String owner;
        String name;
        String desc;

        // If the function does not contain a yield statement,
        // then there is no need for a reentry table.
        if (yield_field == null)
        {
            return;
        }

        // This label marks the exit point of the reentry table.
        final LabelNode END = new LabelNode();

        // Restore the state of method, if necessary.
        addRestorationCode();

        // Push the yield-state object onto the operand-stack.
        owner = Utils.internalName(module.type);
        name = yield_field.name;
        desc = yield_field.desc;
        instructions.add(new FieldInsnNode(Opcodes.GETSTATIC, owner, name, desc));

        // Extract the jump index.
        owner = Utils.internalName(program.typesystem.utils.YIELD_STATE);
        name = "getIndex";
        desc = "()I";
        instructions.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, owner, name, desc));

        // Generate the reentry-table itself.
        final int min = 0;
        final int max = yields.size() - 1;
        final LabelNode[] labels = (LabelNode[]) yields.toArray(new LabelNode[0]);
        final TableSwitchInsnNode table = new TableSwitchInsnNode(min, max, END, labels);
        instructions.add(table);

        // Exit the reentry table.
        instructions.add(END);
    }

    /**
     * This method generates the bytecode that restores the state of a generator function.
     *
     * <p>
     * When a generator function yields, the function stores the state of its local variables
     * in a special object that is referenced by a special field. Later, when a generator
     * function is reentered, it is necessary to restore the state of the function's locals.
     * This method generates the bytecode that performs the restoration.
     * </p>
     */
    private void addRestorationCode()
    {
        String owner;
        String name;
        String desc;

        /**
         * This marker marks the end of the restoration code.
         */
        final LabelNode END = new LabelNode();

        // Load the yield-state object onto the operand-stack.
        owner = Utils.internalName(module.type);
        name = yield_field.name;
        desc = yield_field.desc;
        instructions.add(new FieldInsnNode(Opcodes.GETSTATIC, owner, name, desc));

        // Determine whether this is the first entry of the method.
        owner = Utils.internalName(program.typesystem.utils.YIELD_STATE);
        name = "isFirstCall";
        desc = "()Z";
        instructions.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, owner, name, desc));

        // If this is the first entry of the method, then there is no need to restore the state.
        instructions.add(new JumpInsnNode(Utils.IF_TRUE, END));

        // Load the yield-state object onto the operand-stack.
        owner = Utils.internalName(module.type);
        name = yield_field.name;
        desc = yield_field.desc;
        instructions.add(new FieldInsnNode(Opcodes.GETSTATIC, owner, name, desc));

        // Load the storage-stack onto the operand-stack.
        owner = "autumn/lang/internals/YieldState";
        name = "stack";
        desc = "()Lautumn/lang/internals/ArgumentStack;";
        instructions.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, owner, name, desc));

        // Restore each variable.
        final List<String> reversed = Lists.newLinkedList(scope.getAllVariables());
        Collections.reverse(reversed);
        for (String variable : reversed)
        {
            // Skip parameters, because they are unique to each invocation.
            if (scope.isParameter(variable))
            {
                continue;
            }

            // Duplicate the reference to the storage-stack.
            instructions.add(new InsnNode(Opcodes.DUP));

            // Peek the variable's value off of the storage-stack.
            final IExpressionType var_type = scope.typeOf(variable);
            Utils.peekArgument(program, instructions, var_type);

            // Pop the variable's value off of the stack and place it into the variable.
            vars.store(variable);

            // Duplicate the reference to the storage-stack.
            instructions.add(new InsnNode(Opcodes.DUP));

            // Remove the variable's value from the storage-stack.
            Utils.popArgument(program, instructions);
        }

        // Pop the storage-stack off of the operand-stack.
        instructions.add(new InsnNode(Opcodes.POP));

        // Exit the restoration code.
        instructions.add(END);
    }

    /**
     * This method generates the default method termination bytecode.
     *
     * <p>
     * If a function does not contain a return statement, then execution may reach the end
     * of the function during an invocation. The JVM requires that this special case be handled.
     * Thus, it is necessary to generate bytecode to handle this special situation.
     * </p>
     *
     * <p>
     * Per the specification, a function will simply return, if the return-type is void.
     * On the other hand, the function will raise an exception, if the return-type is non-void.
     * </p>
     *
     * @param is the bytecode representation of the function.
     */
    private void addDefaultMethodTermination(final MethodNode method)
    {
        if (isReturnTypeVoid())
        {
            method.instructions.add(new InsnNode(Opcodes.RETURN));
        }
        else
        {
            method.instructions.add(new TypeInsnNode(Opcodes.NEW, "autumn/lang/exceptions/UnexpectedTerminationException"));
            method.instructions.add(new InsnNode(Opcodes.DUP));
            method.instructions.add(new MethodInsnNode(Opcodes.INVOKESPECIAL,
                                                       "autumn/lang/exceptions/UnexpectedTerminationException",
                                                       "<init>",
                                                       "()V"));
            method.instructions.add(new InsnNode(Opcodes.ATHROW));
        }
    }

    /**
     * This convenience function determines whether the function's return-type is void.
     *
     * @return true, iff the function returns void.
     */
    public boolean isReturnTypeVoid()
    {
        return "void".equals(node.getReturnType().getName().getName())
               && node.getReturnType().getDimensions() == null
               && node.getReturnType().getNamespace() == null;
    }

    /**
     * This method determines whether the function is a generator function.
     *
     * <p>
     * A generator function is a function that contains some form of yield statement.
     * </p>
     *
     * @return true, if the function is a generator function.
     */
    public boolean isGenerator()
    {
        return yield_field != null;
    }

    /**
     * This method determines whether a particular annotation is applied to this method.
     *
     * @param anno is the type of the annotation.
     * @return true, iff an annotation of the specified type is applied to this function.
     */
    public boolean isAnnotationPresent(final IAnnotationType anno)
    {
        assert anno != null;

        for (IAnnotation x : type.getAnnotations())
        {
            if (x.getAnnotationType().equals(anno))
            {
                return true;
            }
        }

        return false;
    }

    /**
     * This function generates the field used to store the state of a generator function.
     *
     * @return the field that stores the function's state between invocations.
     */
    public FieldNode yieldField()
    {
        if (yield_field == null)
        {
            final int access = Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC + Opcodes.ACC_FINAL;

            final String name = "autumn$yield$field$" + module.yields.size();

            final String desc = "Lautumn/lang/internals/YieldState;";

            yield_field = new FieldNode(access, name, desc, null, null);

            module.yields.add(yield_field);

            return yield_field;
        }
        else
        {
            return yield_field;
        }
    }
}
