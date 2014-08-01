package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.Delegate;
import autumn.lang.Functor;
import autumn.lang.compiler.ClassFile;
import autumn.lang.compiler.ast.nodes.DesignDefinition;
import autumn.lang.compiler.ast.nodes.EnumDefinition;
import autumn.lang.compiler.ast.nodes.ExceptionDefinition;
import autumn.lang.compiler.ast.nodes.FormalParameter;
import autumn.lang.compiler.ast.nodes.FunctionDefinition;
import autumn.lang.compiler.ast.nodes.ImportDirective;
import autumn.lang.compiler.ast.nodes.Module;
import autumn.lang.compiler.ast.nodes.ModuleDirective;
import autumn.lang.compiler.ast.nodes.Name;
import autumn.lang.compiler.ast.nodes.TupleDefinition;
import autumn.lang.compiler.ast.nodes.TypeSpecifier;
import autumn.util.DS;
import autumn.util.F;
import autumn.util.proto.ProtoMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import high.mackenzie.autumn.lang.compiler.typesystem.CustomDeclaredType;
import high.mackenzie.autumn.lang.compiler.typesystem.CustomFormalParameter;
import high.mackenzie.autumn.lang.compiler.typesystem.CustomMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IExpressionType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IFormalParameter;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IInterfaceType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IReturnType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVariableType;
import high.mackenzie.autumn.lang.compiler.utils.AnnotationUtils;
import high.mackenzie.autumn.lang.compiler.utils.Utils;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TableSwitchInsnNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

/**
 * An instance of this class controls the compilation of an entire module.
 *
 * @author Mackenzie High
 */
public final class ModuleCompiler
        implements ICompiler
{
    private static int count = 0;

    private int index = ++count;

    /**
     * This is the full name of the module being compiled.
     * This field is initialized during the type-declaration pass.
     */
    private String name;

    public final ProgramCompiler program;

    public final Module node;

    public CustomDeclaredType type;

    public AnnotationUtils anno_utils;

    /**
     * This field is true, iff the module being compiled is anonymous.
     * This field is initialized during the type-declaration pass.
     */
    private boolean anonymous;

    private final Map<String, String> imports = Maps.newTreeMap();


    {
        imports.put("Start", "autumn.lang.annotations.Start");
        imports.put("Significant", "autumn.lang.annotations.Significant");
        imports.put("Unique", "autumn.lang.annotations.Unique");

        importClass(F.class);
        importClass(DS.class);
        importClass(Functor.class);
        importClass(Delegate.class);

        importClass(Object.class);
        importClass(Number.class);
        importClass(CharSequence.class);

        importClass(Boolean.class);
        importClass(Character.class);
        importClass(Byte.class);
        importClass(Short.class);
        importClass(Integer.class);
        importClass(Long.class);
        importClass(Float.class);
        importClass(Double.class);
        importClass(BigInteger.class);
        importClass(BigDecimal.class);
        importClass(String.class);
        importClass(CharSequence.class);
        importClass(StringBuilder.class);

        importClass(Class.class);

        importClass(Throwable.class);
        importClass(Exception.class);
        importClass(RuntimeException.class);
        importClass(ClassCastException.class);
        importClass(IllegalArgumentException.class);
        importClass(IllegalStateException.class);
        importClass(IndexOutOfBoundsException.class);
        importClass(NoSuchElementException.class);

        importClass(Iterable.class);
        importClass(Iterator.class);

        importClass(Collection.class);
        importClass(List.class);
        importClass(LinkedList.class);
        importClass(ArrayList.class);
        importClass(Map.class);
        importClass(HashMap.class);
        importClass(TreeMap.class);
        importClass(Set.class);
        importClass(HashSet.class);
        importClass(TreeSet.class);

        importClass(ProtoMap.class);
    }

    public final List<ExceptionCompiler> exceptions = Lists.newLinkedList();

    public final List<TupleCompiler> tuples = Lists.newLinkedList();

    public final List<EnumCompiler> enums = Lists.newLinkedList();

    public final List<DesignCompiler> designs = Lists.newLinkedList();

    public final List<FunctionCompiler> functions = Lists.newLinkedList();

    public final List<FieldNode> yields = Lists.newLinkedList();

    /**
     * This field stores a list of delegates.
     * Each element is a delegate that refers to a function in this module.
     */
    private final FieldNode delegates = new FieldNode(Opcodes.ACC_PRIVATE + Opcodes.ACC_FINAL,
                                                      "delegates",
                                                      "Ljava/util/List;",
                                                      null,
                                                      null);

    /**
     * This field stores the only instance of the module's class.
     */
    private FieldNode instance_field;

    /**
     * Sole Constructor.
     *
     * @param program is the program being compiled.
     * @param node is the Abstract-Syntax-Tree representation of the module to compile.
     */
    public ModuleCompiler(final ProgramCompiler program,
                          final Module node)
    {
        this.program = program;
        this.node = node;

        for (ExceptionDefinition x : node.getExceptions())
        {
            exceptions.add(new ExceptionCompiler(this, x));
        }

        for (TupleDefinition x : node.getTuples())
        {
            tuples.add(new TupleCompiler(this, x));
        }

        for (EnumDefinition x : node.getEnums())
        {
            enums.add(new EnumCompiler(this, x));
        }

        for (DesignDefinition x : node.getDesigns())
        {
            designs.add(new DesignCompiler(this, x));
        }

        for (FunctionDefinition x : node.getFunctions())
        {
            functions.add(new FunctionCompiler(this, x));
        }

        this.anno_utils = new AnnotationUtils(this);
    }

    public Set<ClassFile> build()
    {
        final Set<ClassFile> classes = Sets.newHashSet();

        for (ExceptionCompiler x : exceptions)
        {
            classes.add(x.build());
        }

        for (TupleCompiler x : tuples)
        {
            classes.add(x.build());
        }

        for (EnumCompiler x : enums)
        {
            classes.add(x.build());
        }

        for (DesignCompiler x : designs)
        {
            classes.add(x.build());
        }

        classes.add(buildModule());

        return classes;
    }

    private ClassFile buildModule()
    {
        final List<MethodNode> methods = Lists.newLinkedList();

        methods.add(buildClinit());
        methods.add(buildInit());
        methods.add(buildModuleFunctions());
        methods.add(buildInstance());
        methods.add(buildModuleExceptions());
        methods.add(buildModuleEnums());
        methods.add(buildModuleInvokeFunction());

        for (FunctionCompiler x : functions)
        {
            methods.add(x.build());
        }

        final String module_internal_name = Utils.internalName(type);

        final String module_source_name = Utils.sourceName(type);

        final ClassNode clazz = new ClassNode();
        {
            clazz.version = Opcodes.V1_6;
            clazz.visibleAnnotations = ImmutableList.of();
            clazz.access = type.getModifiers();
            clazz.name = module_internal_name;
            clazz.superName = Utils.internalName(type.getSuperclass());
            clazz.interfaces = Lists.newLinkedList();
            clazz.fields = Lists.newLinkedList();
            clazz.fields.addAll(yields);
            clazz.fields.add(delegates);
            clazz.fields.add(instance_field);
            clazz.methods = ImmutableList.copyOf(methods);
            clazz.sourceFile = String.valueOf(node.getLocation().getFile());

            assert clazz.superName.equals("autumn/lang/internals/AbstractModule");
            assert clazz.access == Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL;
        }

        final ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);

        clazz.accept(writer);

        final byte[] bytecode = writer.toByteArray();

        final ClassFile file = new ClassFile(module_source_name, bytecode);

        return file;
    }

    /**
     * This method generates the static constructor for the module's class.
     *
     * @return an object representation of the module's static constructor.
     */
    private MethodNode buildClinit()
    {
        final MethodNode m = new MethodNode();

        m.access = Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC + Opcodes.ACC_FINAL;
        m.name = "<clinit>";
        m.desc = "()V";
        m.exceptions = ImmutableList.of();

        String owner;
        String name;
        String desc;

        // Create the only instance of the module's class.
        owner = Utils.internalName(type);
        m.instructions.add(new TypeInsnNode(Opcodes.NEW, owner));
        m.instructions.add(new InsnNode(Opcodes.DUP));
        name = "<init>";
        desc = "()V";
        m.instructions.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, owner, name, desc));

        // Put the instance in to the field named "instance".
        owner = Utils.internalName(type);
        name = "instance";
        desc = type.getDescriptor();
        m.instructions.add(new FieldInsnNode(Opcodes.PUTSTATIC, owner, name, desc));


        for (FieldNode field : yields)
        {
            // Create a YieldState object.
            owner = Utils.internalName(program.typesystem.utils.YIELD_STATE);
            m.instructions.add(new TypeInsnNode(Opcodes.NEW, owner));
            m.instructions.add(new InsnNode(Opcodes.DUP));
            name = "<init>";
            desc = "()V";
            m.instructions.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, owner, name, desc));

            // Store the YieldState object in the field.
            owner = Utils.internalName(type);
            name = field.name;
            desc = field.desc;
            m.instructions.add(new FieldInsnNode(Opcodes.PUTSTATIC, owner, name, desc));
        }

        // Exit the static constructor.
        m.instructions.add(new InsnNode(Opcodes.RETURN));

        return m;
    }

    /**
     * This method generates the instance constructor for the module's class.
     *
     * @return an object representation of the module's instance constructor.
     */
    private MethodNode buildInit()
    {
        final MethodNode m = new MethodNode();

        m.access = Opcodes.ACC_PRIVATE;
        m.name = "<init>";
        m.desc = "()V";
        m.exceptions = ImmutableList.of();

        String owner;
        String name;
        String desc;

        // Invoke super().
        owner = Utils.internalName(program.typesystem.utils.ABSTRACT_MODULE);
        name = "<init>";
        desc = "()V";
        m.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0)); // Load 'this'.
        m.instructions.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, owner, name, desc));

        // Load the module itself onto the operand-stack for later use.
        m.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0)); // Load 'this'.

        // Create the list that will store the delegates that refer to functions in this module.
        owner = "java/util/ArrayList";
        m.instructions.add(new TypeInsnNode(Opcodes.NEW, owner));
        m.instructions.add(new InsnNode(Opcodes.DUP));
        name = "<init>";
        desc = "()V";
        m.instructions.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, owner, name, desc));

        for (FunctionCompiler function : functions)
        {
            // Duplicate the reference to the list of delegates.
            m.instructions.add(new InsnNode(Opcodes.DUP));

            // Create a new delegate object.
            loadDelegate(m, function);

            // Store the delegate object in the list.
            owner = Utils.internalName(program.typesystem.utils.LIST);
            name = "add";
            desc = "(Ljava/lang/Object;)Z";

            m.instructions.add(new MethodInsnNode(Opcodes.INVOKEINTERFACE, owner, name, desc));
            m.instructions.add(new InsnNode(Opcodes.POP)); // Pop the returned boolean.
        }

        // Note: After the loop exited a reference to the list of delegates is on the operand-stack.

        // Make the list unmodifiable.
        owner = Utils.internalName(program.typesystem.utils.HELPERS);
        name = "newImmutableList";
        desc = "(Ljava/lang/Iterable;)Ljava/util/List;";
        m.instructions.add(new MethodInsnNode(Opcodes.INVOKESTATIC, owner, name, desc));

        // Store the list in the delegates field.
        owner = Utils.internalName(type);
        name = delegates.name;
        desc = delegates.desc;
        m.instructions.add(new FieldInsnNode(Opcodes.PUTFIELD, owner, name, desc));

        // Exit the static constructor.
        m.instructions.add(new InsnNode(Opcodes.RETURN));

        return m;
    }

    /**
     * This method generates the moduleFunctions() special method.
     *
     * @return an object representation of the special method.
     */
    private MethodNode buildModuleFunctions()
    {
        final MethodNode m = new MethodNode();

        m.access = Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL;
        m.name = "moduleFunctions";
        m.desc = "()Ljava/util/List;";
        m.exceptions = ImmutableList.of();

        // Load the list of delegates onto the operand-stack.
        m.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0)); // Load 'this'.
        m.instructions.add(new FieldInsnNode(Opcodes.GETFIELD,
                                             Utils.internalName(type),
                                             delegates.name,
                                             delegates.desc));

        // Return the list of delegates.
        m.instructions.add(new InsnNode(Opcodes.ARETURN));

        return m;
    }

    /**
     * This method generates bytecode that creates a new delegate object.
     *
     * <p>
     * The delegate must refer to a function in the module being compiled by this object.
     * </p>
     *
     * @param m is the method that will have bytecode added to it.
     * @param function is the function that the delegate refers to.
     */
    private void loadDelegate(final MethodNode m,
                              final FunctionCompiler function)
    {
        assert functions.contains(function);

        String owner;
        String name;
        String desc;

        final InsnList code = new InsnList();

        // This object can create a list containing the types of the function's parameters.
        final CollectionCompiler<IFormalParameter> params = new CollectionCompiler<IFormalParameter>()
        {
            @Override
            public void compile(IFormalParameter element)
            {
                code().add(Utils.ldcClass(element.getType()));
            }

            @Override
            public InsnList code()
            {
                return code;
            }
        };

        ////////////////////////////////////////////////////////////////////////////////////////////
        // Now we need to create an instance of the ModuleDelegate class.
        // This involves two steps.
        // First, we must create an uninitialized ModuleDelegate object.
        // Second, we must invoke a constructor in the object ot finalize object construction.
        // The constructor will expect several arguments.
        // Argument #1 is the singleton instance of the module's class.
        // Argument #2 is the name of the function that the delegate refers to.
        // Argument #3 is a list of Class objects that represent the types of
        //             the function's formal parameters.
        // Argument #4 is a Class object that represents the function's return type.
        // Argument #5 is the index of the function within the module.
        //             This is needed to facilitate invocation of the function via the delegate.
        ////////////////////////////////////////////////////////////////////////////////////////////

        // Create the uninitialized object.
        owner = Utils.internalName(program.typesystem.utils.MODULE_DELEGATE);
        code.add(new TypeInsnNode(Opcodes.NEW, owner));

        // Duplicate the reference to the delegate object.
        // We need one reference for use during the constructor invocation.
        // Then, we will need to leave another reference on the operand-stack.
        // Whoever called this code generation method expects the object reference to be there.
        code.add(new InsnNode(Opcodes.DUP));

        // Load Argument #1.
        code.add(new VarInsnNode(Opcodes.ALOAD, 0)); // Load 'this'.

        // Load Argument #2.
        code.add(new LdcInsnNode(function.type.getName()));

        // Load Argument #3.
        params.compile(function.type.getFormalParameters());

        // Load Argument #4.
        code.add(Utils.ldcClass(function.type.getReturnType()));

        // Load Argument #5.
        code.add(new LdcInsnNode(functions.indexOf(function)));

        // Invoke the constructor.
        name = "<init>";
        desc = "(Lautumn/lang/Module;Ljava/lang/String;Ljava/util/List;Ljava/lang/Class;I)V";
        code.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, owner, name, desc));

        // Now, an initialized ModuleDelegate object is on top of the operand-stack.

        // Add the code to the method.
        m.instructions.add(code);
    }

    /**
     * This method generates the instance() special method.
     *
     * <p>
     * The special method returns the only instance of the module's class.
     * The special method is static and takes no arguments.
     * </p>
     *
     * @return an object representation of the special method.
     */
    private MethodNode buildInstance()
    {
        final MethodNode m = new MethodNode();

        m.access = (anonymous ? 0 : Opcodes.ACC_PUBLIC) + Opcodes.ACC_STATIC;
        m.name = "instance";
        m.desc = "()" + program.typesystem.utils.MODULE.getDescriptor();
        m.exceptions = ImmutableList.of();

        // The only instance of the module's class is stored in a static final field.
        // Get the value from the field and push it onto the operand-stack.
        m.instructions.add(new FieldInsnNode(Opcodes.GETSTATIC,
                                             Utils.internalName(type),
                                             instance_field.name,
                                             instance_field.desc));

        // Return the instance of the module.
        m.instructions.add(new InsnNode(Opcodes.ARETURN));

        return m;
    }

    /**
     * This method generates the moduleInvokeFunction(int, ArgumentStack) special method.
     *
     * @return an object representation of the special method.
     */
    private MethodNode buildModuleInvokeFunction()
    {
        final MethodNode m = new MethodNode();

        String owner;
        String name;
        String desc;

        m.access = Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL;
        m.name = "moduleInvokeFunction";
        m.desc = "(ILautumn/lang/internals/ArgumentStack;)V";
        m.exceptions = ImmutableList.of("java/lang/Throwable");

        final InsnList code = new InsnList();

        final LabelNode[] cases = new LabelNode[functions.size()];

        for (int i = 0; i < functions.size(); i++)
        {
            cases[i] = new LabelNode();
        }

        final LabelNode default_case = new LabelNode();

        code.add(new VarInsnNode(Opcodes.ILOAD, 1));

        code.add(new TableSwitchInsnNode(0,
                                         functions.size() - 1,
                                         default_case,
                                         cases));

        int c = 0;

        for (FunctionCompiler function : functions)
        {
            // Add the label that marks the entry-point of the switch case.
            code.add(cases[c++]);

            // Load the argument-stack onto the operand-stack.
            // We will need it later in order to return the return-value from the method.
            code.add(new VarInsnNode(Opcodes.ALOAD, 2));


            // Pop the arguments off of the argument-stack and push them onto the operand-stack.
            for (int i = 0; i < function.type.getFormalParameters().size(); i++)
            {
                // Load the argument-stack onto the operand-stack.
                // The return-value of the function will need pushed onto the argument-stack later.
                code.add(new VarInsnNode(Opcodes.ALOAD, 2));

                final IExpressionType param = (IExpressionType) function.type
                        .getFormalParameters()
                        .get(i)
                        .getType();

                // Transfer the i-th argument from the argument-stack to the operand-stack.
                Utils.getArgument(program, code, param, i);
            }

            // Load the argument-stack onto the operand-stack.
            code.add(new VarInsnNode(Opcodes.ALOAD, 2));

            // Clear the argument-stack.
            owner = Utils.internalName(program.typesystem.utils.ARGUMENT_STACK);
            name = "clear";
            desc = "()V";
            code.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, owner, name, desc));

            // Invoke the function.
            owner = Utils.internalName(type);
            name = function.type.getName();
            desc = function.type.getDescriptor();
            code.add(new MethodInsnNode(Opcodes.INVOKESTATIC, owner, name, desc));

            // Transfer the return-value from the operand-stack to the argument-stack.
            if (function.type.getReturnType().isVoidType() == false)
            {
                Utils.pushArgument(program, code, function.type.getReturnType());
            }
            else
            {
                // Since the method returns void, null must be loaded onto the argument-stack.
                code.add(new InsnNode(Opcodes.ACONST_NULL));

                Utils.pushArgument(program, code, function.type.getReturnType());
            }

            // Do not fall through to the next switch-case.
            code.add(new InsnNode(Opcodes.RETURN));
        }

        code.add(default_case);
        code.add(new InsnNode(Opcodes.RETURN));

        m.instructions.add(code);


        return m;
    }

    /**
     * This method generates the moduleEnumDefinitions() special method.
     *
     * @return an object representation of the special method.
     */
    private MethodNode buildModuleEnums()
    {
        final MethodNode m = new MethodNode();

        m.access = Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL;
        m.name = "moduleEnumDefinitions";
        m.desc = "()Ljava/util/List;";
        m.exceptions = ImmutableList.of();

        final InsnList code = new InsnList();

        /**
         * This class is used to generate a list of Class object.
         */
        final CollectionCompiler<EnumCompiler> cmp = new CollectionCompiler<EnumCompiler>()
        {
            @Override
            public void compile(final EnumCompiler element)
            {
                code().add(Utils.ldcClass(element.type));
            }

            @Override
            public InsnList code()
            {
                return code;
            }
        };

        // Generate a list of Class objects.
        cmp.compile(enums);

        // Make the list immutable.
        code.add(new MethodInsnNode(Opcodes.INVOKESTATIC,
                                    Utils.internalName(program.typesystem.utils.HELPERS),
                                    "newImmutableList",
                                    "(Ljava/lang/Iterable;)Ljava/util/List;"));

        // Return the list.
        code.add(new InsnNode(Opcodes.ARETURN));

        // Emit the generated instructions.
        m.instructions.add(code);

        return m;
    }

    /**
     * This method generates the moduleExceptionDefinitions() special method.
     *
     * @return an object representation of the special method.
     */
    private MethodNode buildModuleExceptions()
    {
        final MethodNode m = new MethodNode();

        m.access = Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL;
        m.name = "moduleExceptionDefinitions";
        m.desc = "()Ljava/util/List;";
        m.exceptions = ImmutableList.of();

        final InsnList code = new InsnList();

        /**
         * This class is used to generate a list of Class object.
         */
        final CollectionCompiler<ExceptionCompiler> cmp = new CollectionCompiler<ExceptionCompiler>()
        {
            @Override
            public void compile(final ExceptionCompiler element)
            {
                code().add(Utils.ldcClass(element.type));
            }

            @Override
            public InsnList code()
            {
                return code;
            }
        };

        // Generate a list of Class objects.
        cmp.compile(exceptions);

        // Make the list immutable.
        code.add(new MethodInsnNode(Opcodes.INVOKESTATIC,
                                    Utils.internalName(program.typesystem.utils.HELPERS),
                                    "newImmutableList",
                                    "(Ljava/lang/Iterable;)Ljava/util/List;"));

        // Return the list.
        code.add(new InsnNode(Opcodes.ARETURN));

        // Emit the generated instructions.
        m.instructions.add(code);

        return m;
    }

    private String dealisTypeName(final String typename)
    {
        final String deimported = imports.containsKey(typename) ? imports.get(typename) : typename;

        final String dealiased = deimported.contains(".")
                ? deimported
                : type.getNamespace() + '.' + typename;

        return dealiased;
    }

    public IReturnType resolveType(final TypeSpecifier specifier)
    {
        final String alias = program.typesystem.utils.extractTypeName(specifier);

        if (Utils.isKeyword(alias))
        {
            // Special Case: The specifier specifies a primitive-type or the void-type.
            return program.typesystem.utils.findType(alias, null);
        }

        final String typename = dealisTypeName(alias);

        final Integer dimensions = specifier.getDimensions();

        final IReturnType result = program.typesystem.utils.findType(typename, dimensions);

        return result;
    }

    public IVariableType resolveVariableType(final TypeSpecifier specifier)
    {
        // TODO: error if non-vartype

        return (IVariableType) resolveType(specifier);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeDeclaration()
    {
        if (node.getModuleDirectives().size() < 1)
        {
            // Error - missing module directive
            // fail fast
        }

        if (node.getModuleDirectives().size() > 1)
        {
            // Error - too many directives
            // fail fast
        }

        final ModuleDirective directive = node.getModuleDirectives().asMutableList().get(0);

        final String module_descriptor = processModuleDirective(directive);

        this.type = program.typesystem.typefactory().newClassType(module_descriptor);

        for (ICompiler x : compilers())
        {
            x.performTypeDeclaration();
        }

        // Process the import directives.
        loadImports();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeInitialization()
    {
        this.type.setModifiers(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL);
        this.type.setSuperclass(program.typesystem.utils.ABSTRACT_MODULE);
        this.type.setSuperinterfaces(ImmutableList.<IInterfaceType>of());

        for (ICompiler x : compilers())
        {
            x.performTypeInitialization();
        }

        final CustomMethod instance = new CustomMethod(type.getTypeFactory(), false);
        instance.setOwner(type);
        instance.setName("instance");
        instance.setModifiers(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC);
        instance.setParameters(new LinkedList());
        instance.setReturnType(program.typesystem.utils.MODULE);
        instance.setThrowsClause(new LinkedList());

        // Add this function's type to the collection of functions in the module's type.
        final List<IMethod> list = Lists.newLinkedList(type.getMethods());
        list.add(instance);
        type.setMethods(list);

        // Create the field that will store the only instance of the module's class.
        instance_field = new FieldNode(Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC + Opcodes.ACC_FINAL,
                                       "instance",
                                       type.getDescriptor(),
                                       null,
                                       null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeStructureChecking()
    {
        // TODO:
        // . No duplicate methods.
        // . No methods that hide/override a method from OBJECT.
        // . Chack annotation-list on the module directive.
        // . The name of the module cannot be a forbidden name.
        //

        for (ICompiler x : compilers())
        {
            x.performTypeStructureChecking();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeUsageChecking()
    {
        for (ICompiler x : compilers())
        {
            x.performTypeUsageChecking();
        }
    }

    /**
     * This method executes all the import-directives and imports the types defined in this module.
     */
    private void loadImports()
    {
        /**
         * Import the module itself.
         */
        imports.put(name.substring(name.lastIndexOf('.') + 1),
                    Utils.sourceName(type));

        // The module name "My" is used to generically refer to the current module.
        imports.put("My", Utils.sourceName(type));

        /**
         * Execute all the import directives.
         */
        for (ImportDirective import_directive : node.getImportDirectives())
        {
            final TypeSpecifier imported_type = import_directive.getType();

            final String typename = program.typesystem.utils.extractTypeName(imported_type);

            final String simple_name = imported_type.getName().getName();

            if (imported_type.getDimensions() != null)
            {
                // TODO: error
            }

            imports.put(simple_name, typename);
        }

        /**
         * Import all the enums defined in this module.
         */
        for (EnumCompiler cmp : enums)
        {
            final String simple_name = cmp.node.getName().getName();

            final String typename = Utils.internalName(cmp.type).replace('/', '.');

            imports.put(simple_name, typename);
        }
    }

    private String processModuleDirective(final ModuleDirective directive)
    {
        // TODO: Handle annotations on the directive

        // This is the name of the package that the module resides in.
        final StringBuilder module_package = new StringBuilder();

        // String together the pieces of the package's name.
        for (Name n : directive.getNamespace().getNames())
        {
            module_package.append(n.getName());
            module_package.append('/');
        }

        // This is the name of the module, excluding the name of the enclosing package.
        final String module_name = directive.getName() == null
                ? createNameForAnonymousModule(module_package.toString())
                : directive.getName().getName();

        // Remember the full name of the module for later use.
        name = module_package.toString().replace('/', '.') + module_name;

        final String module_descriptor = "L" + module_package + module_name + ";";

        return module_descriptor;
    }

    /**
     * This method creates a name for an anonymous module, since there is really no such thing.
     *
     * @return a name for the module that is being compiled.
     */
    private String createNameForAnonymousModule(final String namespace)
    {
        return "Module$" + index;
    }

    /**
     * This method avoids the need for multiple for-loops elsewhere.
     *
     * @return the compilers used by this compiler.
     */
    private Set<ICompiler> compilers()
    {
        final Set<ICompiler> result = Sets.newHashSet();

        result.addAll(exceptions);
        result.addAll(tuples);
        result.addAll(enums);
        result.addAll(designs);
        result.addAll(functions);

        return result;
    }

    private void importClass(final Class type)
    {
        imports.put(type.getSimpleName(), type.getName());
    }

    /**
     * This method converts a list of AST nodes that represent formal parameters
     * to their type-system based representation.
     *
     * @param parameters are the formal parameters as represented by AST nodes.
     * @return the formal parameters as represented in the type-system.
     */
    public List<IFormalParameter> formals(final Iterable<FormalParameter> parameters)
    {
        final List<IFormalParameter> formals = Lists.newArrayList();

        for (FormalParameter param : parameters)
        {
            // This method will issue an error-message, if the type does not exist, etc.
            final IVariableType param_type = resolveVariableType(param.getType());

            // Create the type-system representation of the formal parameter.
            final CustomFormalParameter formal = new CustomFormalParameter();
            formal.setAnnotations(new LinkedList());
            formal.setType(param_type);

            formals.add(formal);
        }

        return formals;
    }
}
