package com.mackenziehigh.autumn.lang.compiler.compilers;

import autumn.lang.compiler.ClassFile;
import autumn.lang.compiler.ast.nodes.AnnotationDefinition;
import autumn.lang.compiler.ast.nodes.DesignDefinition;
import autumn.lang.compiler.ast.nodes.EnumDefinition;
import autumn.lang.compiler.ast.nodes.ExceptionDefinition;
import autumn.lang.compiler.ast.nodes.FormalParameter;
import autumn.lang.compiler.ast.nodes.FunctionDefinition;
import autumn.lang.compiler.ast.nodes.FunctorDefinition;
import autumn.lang.compiler.ast.nodes.ImportDirective;
import autumn.lang.compiler.ast.nodes.Module;
import autumn.lang.compiler.ast.nodes.ModuleDirective;
import autumn.lang.compiler.ast.nodes.Name;
import autumn.lang.compiler.ast.nodes.StructDefinition;
import autumn.lang.compiler.ast.nodes.TupleDefinition;
import autumn.lang.compiler.ast.nodes.TypeSpecifier;
import autumn.lang.internals.annotations.ModuleDefinition;
import autumn.util.F;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mackenziehigh.autumn.lang.compiler.typesystem.CustomDeclaredType;
import com.mackenziehigh.autumn.lang.compiler.typesystem.CustomFormalParameter;
import com.mackenziehigh.autumn.lang.compiler.typesystem.CustomMethod;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IDeclaredType;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IExpressionType;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IFormalParameter;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IInterfaceType;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IMethod;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IVariableType;
import com.mackenziehigh.autumn.lang.compiler.utils.AnnotationUtils;
import com.mackenziehigh.autumn.lang.compiler.utils.Utils;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
    /**
     * An instance of this class represents a hidden field within the module class.
     *
     * <p>
     * Hidden fields are used to implement once-expressions, etc.
     * </p>
     */
    static final class HiddenField
    {
        /**
         * At the bytecode level, all fields must have a name.
         */
        public final String name = "autumn$hidden$field$" + F.unique();

        /**
         * This is the static-type of the field.
         */
        public final IVariableType type;

        /**
         * This is true, iff the field is final.
         */
        public final boolean readonly;

        /**
         * These are instructions to add to the static constructor.
         */
        public InsnList initializer = new InsnList();

        /**
         * Sole Constructor.
         *
         * @param type is the static-type of the new hidden field.
         * @param readonly is true, iff the field is final.
         * @throws NullPointerException if type is null.
         */
        public HiddenField(final IVariableType type,
                           final boolean readonly)
        {
            Preconditions.checkNotNull(type);

            this.type = type;
            this.readonly = readonly;
        }

        /**
         * This method generates the bytecode representation of the field.
         */
        public FieldNode build()
        {
            final int access = Opcodes.ACC_PRIVATE | Opcodes.ACC_STATIC | (readonly ? Opcodes.ACC_FINAL : 0);

            final FieldNode field = new FieldNode(access, name, type.getDescriptor(), null, null);

            return field;
        }
    }

    /**
     * This is the full name of the module being compiled.
     * This field is initialized during the type-declaration pass.
     */
    private String module_name;

    /**
     * Essentially, this is the program that is being compiled.
     */
    public final ProgramCompiler program;

    /**
     * This is the Abstract-Syntax-Tree representation of the module.
     */
    public final Module node;

    /**
     * This will be assigned the Abstract-Syntax-Tree of the module-directive
     * that is contained within the module.
     */
    private ModuleDirective module_directive;

    /**
     * This will be assigned the type-system representation of the module.
     */
    public CustomDeclaredType type;

    /**
     * This object makes compiling annotations easier.
     *
     * <p>
     * This object will be needed during the compilation of code inside the module.
     * </p>
     */
    public AnnotationUtils anno_utils;

    /**
     * This field is true, iff the module being compiled is anonymous.
     * This field is initialized during the type-declaration pass.
     */
    private boolean anonymous;

    /**
     * Essentially, this object implements the import-directives contained in the module.
     */
    public final Importer imports;

    /**
     * Essentially, these are the annotations that are defined in the module.
     */
    final List<AnnotationCompiler> annotations = Lists.newLinkedList();

    /**
     * Essentially, these are the exceptions that are defined in the module.
     */
    final List<ExceptionCompiler> exceptions = Lists.newLinkedList();

    /**
     * Essentially, these are the designs that are defined in the module.
     */
    final List<DesignCompiler> designs = Lists.newLinkedList();

    /**
     * Essentially, these are the structs that are defined in the module.
     */
    final List<StructCompiler> structs = Lists.newLinkedList();

    /**
     * Essentially, these are the tuples that are defined in the module.
     */
    final List<TupleCompiler> tuples = Lists.newLinkedList();

    /**
     * Essentially, these are the enums that are defined in the module.
     */
    final List<EnumCompiler> enums = Lists.newLinkedList();

    /**
     * Essentially, these are the functors that are defined in the module.
     */
    final List<FunctorCompiler> functors = Lists.newLinkedList();

    /**
     * Essentially, these are the functions that are defined in the module.
     */
    final List<FunctionCompiler> functions = Lists.newLinkedList();

    /**
     * These objects represent the hidden fields to add to the module.
     *
     * <p>
     * Hidden fields are needed to implement some constructs, such as once-expressions.
     * </p>
     */
    final List<HiddenField> hidden = Lists.newLinkedList();

    /**
     * This bytecode field caches the ModuleInfo object that describes the module.
     */
    private final FieldNode info = new FieldNode(Opcodes.ACC_PRIVATE + Opcodes.ACC_FINAL,
                                                 "module_info",
                                                 "Lautumn/lang/ModuleInfo;",
                                                 null,
                                                 null);

    /**
     * This bytecode field stores a list of delegates.
     * Each element is a delegate that refers to a function in this module.
     */
    private final FieldNode delegates = new FieldNode(Opcodes.ACC_PRIVATE + Opcodes.ACC_FINAL,
                                                      "delegates",
                                                      "Ljava/util/List;",
                                                      null,
                                                      null);

    /**
     * This bytecode field stores the only instance of the module's class.
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

        this.imports = new Importer(this);

        for (AnnotationDefinition x : node.getAnnotations())
        {
            annotations.add(new AnnotationCompiler(this, x));
        }

        for (ExceptionDefinition x : node.getExceptions())
        {
            exceptions.add(new ExceptionCompiler(this, x));
        }

        for (EnumDefinition x : node.getEnums())
        {
            enums.add(new EnumCompiler(this, x));
        }

        for (DesignDefinition x : node.getDesigns())
        {
            designs.add(new DesignCompiler(this, x));
        }

        for (StructDefinition x : node.getStructs())
        {
            structs.add(new StructCompiler(this, x));
        }

        for (TupleDefinition x : node.getTuples())
        {
            tuples.add(new TupleCompiler(this, x));
        }

        for (FunctorDefinition x : node.getFunctors())
        {
            functors.add(new FunctorCompiler(this, x));
        }

        for (FunctionDefinition x : node.getFunctions())
        {
            functions.add(new FunctionCompiler(this, x));
        }

        this.anno_utils = new AnnotationUtils(this);
    }

    /**
     * This method performs the bytecode-generation of the module and the code therein.
     *
     * @return the objects that contain the generated bytecode.
     */
    public Set<ClassFile> build()
    {
        final Set<ClassFile> classes = Sets.newHashSet();

        for (AnnotationCompiler x : annotations)
        {
            classes.add(x.build());
        }

        for (ExceptionCompiler x : exceptions)
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

        for (StructCompiler x : structs)
        {
            classes.add(x.build());
        }

        for (TupleCompiler x : tuples)
        {
            classes.add(x.build());
        }

        for (FunctorCompiler x : functors)
        {
            classes.add(x.build());
        }

        /**
         * Generate the bytecode of the module itself.
         */
        classes.add(buildModule());

        return classes;
    }

    /**
     * This method performs the bytecode-generation of a module itself.
     *
     * <p>
     * Basically, a module compiles to a JVM class.
     * </p>
     *
     * @return an object that contains the generated bytecode.
     */
    private ClassFile buildModule()
    {
        final List<FieldNode> fields = Lists.newLinkedList();

        final List<MethodNode> methods = Lists.newLinkedList();

        /**
         * Generate the bytecode that implements the special methods of the module.
         */
        methods.add(buildClinit());
        methods.add(buildInit());
        methods.add(buildModuleInfo());
        methods.add(buildInstance());
        methods.add(buildModuleInvokeFunction());

        /**
         * Generate the bytecode that implements the user-defined functions.
         */
        for (FunctionCompiler x : functions)
        {
            methods.add(x.build());
        }

        /**
         * Generate the bytecode that implements the hidden fields.
         */
        for (HiddenField field : hidden)
        {
            fields.add(field.build());
        }

        final String module_internal_name = Utils.internalName(type);

        final String module_source_name = Utils.sourceName(type);

        /**
         * Generate the bytecode of the class that is the module.
         */
        final ClassNode clazz = new ClassNode();
        {
            clazz.version = Opcodes.V1_6;
            clazz.visibleAnnotations = anno_utils.compileAnnotationList(type.getAnnotations());
            clazz.access = type.getModifiers();
            clazz.name = module_internal_name;
            clazz.superName = Utils.internalName(type.getSuperclass());
            clazz.interfaces = Lists.newLinkedList();
            clazz.fields = Lists.newLinkedList();
            clazz.fields.add(info);
            clazz.fields.add(delegates);
            clazz.fields.add(instance_field);
            clazz.fields.addAll(fields);
            clazz.methods = ImmutableList.copyOf(methods);
            clazz.sourceFile = String.valueOf(node.getLocation().getFile());

            assert clazz.superName.equals("autumn/lang/internals/AbstractModule");
            assert clazz.access == Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL;
        }

        /**
         * Convert ObjectWeb ASM representation of the bytecode to an array of bytes.
         */
        final ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        clazz.accept(writer);
        final byte[] bytecode = writer.toByteArray();

        /**
         * Wrap the bytecode in an object.
         */
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

        /**
         * Initialize the hidden fields.
         */
        for (HiddenField field : hidden)
        {
            m.instructions.add(field.initializer);
        }

        /**
         * Invoke the setup functions.
         */
        for (FunctionCompiler function : functions)
        {
            if (function.isAnnotationPresent(program.typesystem.utils.SETUP))
            {
                owner = Utils.internalName(type);
                name = function.type.getName();
                desc = "()V";
                m.instructions.add(new MethodInsnNode(Opcodes.INVOKESTATIC, owner, name, desc));
            }
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

        /**
         * Invoke super().
         */
        owner = Utils.internalName(program.typesystem.utils.ABSTRACT_MODULE);
        name = "<init>";
        desc = "()V";
        m.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0)); // Load 'this'.
        m.instructions.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, owner, name, desc));

        /**
         * Create the ModuleInfoBuilder object.
         */
        owner = Utils.internalName(program.typesystem.utils.MODULE_INFO_BUILDER);
        name = "<init>";
        desc = "(Lautumn/lang/Module;)V";
        m.instructions.add(new TypeInsnNode(Opcodes.NEW, Utils.internalName(program.typesystem.utils.MODULE_INFO_BUILDER)));
        m.instructions.add(new InsnNode(Opcodes.DUP));
        m.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0)); // Load 'this'.
        m.instructions.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, owner, name, desc));

        /**
         * Add the delegates to the user-defined functions to the ModuleInfoBuilder object.
         */
        for (FunctionCompiler function : functions)
        {
            /**
             * Duplicate the reference to the ModuleInfoBuilder object.
             */
            m.instructions.add(new InsnNode(Opcodes.DUP));

            /**
             * Create a new delegate object.
             */
            loadDelegate(m, function);

            /**
             * Add the delegate object in the ModuleInfoBuilder.
             */
            owner = Utils.internalName(program.typesystem.utils.MODULE_INFO_BUILDER);
            name = "add";
            desc = "(Lautumn/lang/Delegate;)V";
            m.instructions.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, owner, name, desc));
        }

        // Note: After the loop exited a reference to the ModuleInfoBuilder is on the operand-stack.

        /**
         * Add the annotation-types declared in the module to the ModuleInfoBuilder object.
         */
        for (AnnotationCompiler x : annotations)
        {
            owner = Utils.internalName(program.typesystem.utils.MODULE_INFO_BUILDER);
            name = "addAnnotation";
            desc = "(Ljava/lang/Class;)V";
            m.instructions.add(new InsnNode(Opcodes.DUP));
            m.instructions.add(Utils.ldcClass(x.type));
            m.instructions.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, owner, name, desc));
        }

        /**
         * Add the enum-types declared in the module to the ModuleInfoBuilder object.
         */
        for (EnumCompiler x : enums)
        {
            owner = Utils.internalName(program.typesystem.utils.MODULE_INFO_BUILDER);
            name = "addEnum";
            desc = "(Ljava/lang/Class;)V";
            m.instructions.add(new InsnNode(Opcodes.DUP));
            m.instructions.add(Utils.ldcClass(x.type));
            m.instructions.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, owner, name, desc));
        }

        /**
         * Add the exception-types declared in the module to the ModuleInfoBuilder object.
         */
        for (ExceptionCompiler x : exceptions)
        {
            owner = Utils.internalName(program.typesystem.utils.MODULE_INFO_BUILDER);
            name = "addException";
            desc = "(Ljava/lang/Class;)V";
            m.instructions.add(new InsnNode(Opcodes.DUP));
            m.instructions.add(Utils.ldcClass(x.type));
            m.instructions.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, owner, name, desc));
        }

        /**
         * Add the design-types declared in the module to the ModuleInfoBuilder object.
         */
        for (DesignCompiler x : designs)
        {
            owner = Utils.internalName(program.typesystem.utils.MODULE_INFO_BUILDER);
            name = "addDesign";
            desc = "(Ljava/lang/Class;)V";
            m.instructions.add(new InsnNode(Opcodes.DUP));
            m.instructions.add(Utils.ldcClass(x.type));
            m.instructions.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, owner, name, desc));
        }

        /**
         * Add the struct-types declared in the module to the ModuleInfoBuilder object.
         */
        for (StructCompiler x : structs)
        {
            owner = Utils.internalName(program.typesystem.utils.MODULE_INFO_BUILDER);
            name = "addStruct";
            desc = "(Ljava/lang/Class;)V";
            m.instructions.add(new InsnNode(Opcodes.DUP));
            m.instructions.add(Utils.ldcClass(x.type));
            m.instructions.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, owner, name, desc));
        }

        /**
         * Add the tuple-types declared in the module to the ModuleInfoBuilder object.
         */
        for (TupleCompiler x : tuples)
        {
            owner = Utils.internalName(program.typesystem.utils.MODULE_INFO_BUILDER);
            name = "addTuple";
            desc = "(Ljava/lang/Class;)V";
            m.instructions.add(new InsnNode(Opcodes.DUP));
            m.instructions.add(Utils.ldcClass(x.type));
            m.instructions.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, owner, name, desc));
        }

        /**
         * Add the functor-types declared in the module to the ModuleInfoBuilder object.
         */
        for (FunctorCompiler x : functors)
        {
            owner = Utils.internalName(program.typesystem.utils.MODULE_INFO_BUILDER);
            name = "addFunctor";
            desc = "(Ljava/lang/Class;)V";
            m.instructions.add(new InsnNode(Opcodes.DUP));
            m.instructions.add(Utils.ldcClass(x.type));
            m.instructions.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, owner, name, desc));
        }

        // Note: After the loop exited a reference to the ModuleInfoBuilder is on the operand-stack.

        /**
         * Convert the ModuleInfoBuilder object into a ModuleInfo object.
         */
        owner = Utils.internalName(program.typesystem.utils.MODULE_INFO_BUILDER);
        name = "build";
        desc = "()" + program.typesystem.utils.MODULE_INFO.getDescriptor();
        m.instructions.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, owner, name, desc));

        /**
         * Store the ModuleInfo object in the appropriate field.
         */
        owner = Utils.internalName(type);
        name = info.name;
        desc = info.desc;
        m.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0)); // Load 'this'.
        m.instructions.add(new InsnNode(Opcodes.SWAP));
        m.instructions.add(new FieldInsnNode(Opcodes.PUTFIELD, owner, name, desc));

        /**
         * Exit the static constructor.
         */
        m.instructions.add(new InsnNode(Opcodes.RETURN));

        return m;
    }

    /**
     * This method generates the info() special method.
     *
     * @return an object representation of the special method.
     */
    private MethodNode buildModuleInfo()
    {
        final MethodNode m = new MethodNode();

        m.access = Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL;
        m.name = "info";
        m.desc = "()Lautumn/lang/ModuleInfo;";
        m.exceptions = ImmutableList.of();

        // Load the ModuleInfo object onto the operand-stack.
        m.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0)); // Load 'this'.
        m.instructions.add(new FieldInsnNode(Opcodes.GETFIELD,
                                             Utils.internalName(type),
                                             info.name,
                                             info.desc));

        // Return the ModuleInfo object.
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
        params.compile(function.type.getParameters());

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

        m.access = Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC + Opcodes.ACC_FINAL;
        m.name = "instance";
        m.desc = "()" + type.getDescriptor();
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
     * This method generates the invoke(int, ArgumentStack) special method.
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
        m.name = "invoke";
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
            for (int i = 0; i < function.type.getParameters().size(); i++)
            {
                // Load the argument-stack onto the operand-stack.
                // The return-value of the function will need pushed onto the argument-stack later.
                code.add(new VarInsnNode(Opcodes.ALOAD, 2));

                final IExpressionType param = (IExpressionType) function.type
                        .getParameters()
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
     * {@inheritDoc}
     */
    @Override
    public void performTypeDeclaration()
    {
        /**
         * This object only compiles non empty nodes.
         */
        assert isEmpty(node) == false;

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

        /**
         * Ensure that the type was not already declared elsewhere.
         */
        final Name module_name = node.getModuleDirectives().asMutableList().get(0).getName();

        program.checker.requireNonDuplicateType(module_name, module_descriptor);

        /**
         * Declare the module's type.
         */
        this.type = program.typesystem.typefactory().newClassType(module_descriptor);

        /**
         * Declare all the designs, structs, tuples, etc that are defined in the module.
         */
        for (ICompiler x : compilers())
        {
            x.performTypeDeclaration();
        }

        /**
         * Process the import directives.
         */
        loadImports();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeInitialization()
    {
        /**
         * Create the type-system representations of the annotation-list.
         */
        type.setAnnotations(anno_utils.typesOf(module_directive.getAnnotations()));

        /**
         * Add a special annotation.
         */
        anno_utils.add(type, ModuleDefinition.class);

        /**
         * Initialize the module's type.
         */
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
        instance.setReturnType(type);
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
        /**
         * Perform type-structure-checking on all of the functions, etc.
         */
        for (ICompiler x : compilers())
        {
            x.performTypeStructureChecking();
        }

        /**
         * Check the list of annotations.
         */
        program.checker.checkAnnotations(module_directive.getAnnotations(), type.getAnnotations());

        /**
         * No two functions can have the exact same name and parameters.
         */
        reportDuplicateFunctions();
    }

    /**
     * This method ensures that the module does not contain duplicate functions.
     *
     * <p>
     * A function is a duplicate, if it has the same name and parameter-types as another function.
     * </p>
     */
    private void reportDuplicateFunctions()
    {
        final Map<String, FunctionCompiler> signatures = Maps.newTreeMap();

        /**
         * For each function F, map the signature of F to the compiler of F.
         */
        for (FunctionCompiler function : functions)
        {
            /**
             * Note: If the map already contains an entry for the signature,
             * the old entry will be replaced, which is fine.
             */
            signatures.put(function.type.getNamePlusParameterListDescriptor(), function);
        }

        /**
         * For each function compiler F, ensure that the signature of F is mapped to F.
         */
        for (FunctionCompiler function : functions)
        {
            /**
             * Yes, I really do mean identity inequality here.
             */
            if (function != signatures.get(function.type.getNamePlusParameterListDescriptor()))
            {
                /**
                 * This will throw an exception and issue a compiler-warning.
                 */
                program.checker.reportDuplicateFunction(function);
            }
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
        imports.importType(module_name.substring(module_name.lastIndexOf('.') + 1),
                           Utils.sourceName(type));

        // The module name "My" is used to generically refer to the current module.
        imports.importType("My", Utils.sourceName(type));

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

            imports.importType(simple_name, typename);
        }

        /**
         * Import all the annotations defined in this module.
         */
        for (AnnotationCompiler cmp : annotations)
        {
            autoimport(cmp.type);
        }

        /**
         * Import all the exceptions defined in this module.
         */
        for (ExceptionCompiler cmp : exceptions)
        {
            autoimport(cmp.type);
        }

        /**
         * Import all the enums defined in this module.
         */
        for (EnumCompiler cmp : enums)
        {
            autoimport(cmp.type);
        }

        /**
         * Import all the designs defined in this module.
         */
        for (DesignCompiler cmp : designs)
        {
            autoimport(cmp.type);
        }

        /**
         * Import all the structs defined in this module.
         */
        for (StructCompiler cmp : structs)
        {
            autoimport(cmp.type);
        }

        /**
         * Import all the tuples defined in this module.
         */
        for (TupleCompiler cmp : tuples)
        {
            autoimport(cmp.type);
        }

        /**
         * Import all the functors defined in this module.
         */
        for (FunctorCompiler cmp : functors)
        {
            autoimport(cmp.type);
        }
    }

    /**
     * This method performs the importation of a type declared within the module.
     *
     * @param type is the type to import.
     */
    private void autoimport(final IDeclaredType type)
    {
        /**
         * Get the fully-qualified internal-name of the type.
         */
        final String internal_name = Utils.internalName(type).replace('/', '.');

        /**
         * Get the simple-name of the type.
         */
        final String simple_name = internal_name.substring(internal_name.lastIndexOf('.') + 1, internal_name.length());

        /**
         * Import the type.
         */
        imports.importType(simple_name, internal_name);
    }

    private String processModuleDirective(final ModuleDirective directive)
    {
        this.module_directive = directive;

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
                ? createNameForAnonymousModule()
                : directive.getName().getName();

        // Remember the full name of the module for later use.
        this.module_name = module_package.toString().replace('/', '.') + module_name;

        final String module_descriptor = "L" + module_package + module_name + ";";

        return module_descriptor;
    }

    /**
     * This method creates a name for an anonymous module, since there is really no such thing.
     *
     * @return a name for the module that is being compiled.
     */
    private String createNameForAnonymousModule()
    {
        return "Module$" + F.unique();
    }

    /**
     * This method avoids the need for multiple for-loops elsewhere.
     *
     * @return the compilers used by this compiler.
     */
    private Set<ICompiler> compilers()
    {
        final Set<ICompiler> result = Sets.newHashSet();

        result.addAll(annotations);
        result.addAll(exceptions);
        result.addAll(enums);
        result.addAll(designs);
        result.addAll(structs);
        result.addAll(tuples);
        result.addAll(functors);
        result.addAll(functions);

        return result;
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
            final IVariableType param_type = imports.resolveVariableType(param.getType());

            // Create the type-system representation of the formal parameter.
            final CustomFormalParameter formal = new CustomFormalParameter();
            formal.setAnnotations(new LinkedList());
            formal.setType(param_type);

            formals.add(formal);
        }

        return formals;
    }

    /**
     * This method determines whether a module is empty.
     *
     * <p>
     * Empty modules are caused, for example, by empty source files.
     * </p>
     *
     * @param node is the AST representation of the module.
     * @return true, iff the is an empty module.
     */
    public static boolean isEmpty(Module node)
    {
        return node.getAnnotations().isEmpty()
               && node.getDesigns().isEmpty()
               && node.getEnums().isEmpty()
               && node.getExceptions().isEmpty()
               && node.getFunctions().isEmpty()
               && node.getFunctors().isEmpty()
               && node.getImportDirectives().isEmpty()
               && node.getModuleDirectives().isEmpty()
               && node.getStructs().isEmpty()
               && node.getTuples().isEmpty();
    }
}
