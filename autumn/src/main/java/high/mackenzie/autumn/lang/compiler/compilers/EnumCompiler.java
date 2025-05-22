package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.compiler.ClassFile;
import autumn.lang.compiler.ast.nodes.EnumDefinition;
import autumn.lang.compiler.ast.nodes.Name;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import high.mackenzie.autumn.lang.compiler.typesystem.CustomDeclaredType;
import high.mackenzie.autumn.lang.compiler.typesystem.CustomField;
import high.mackenzie.autumn.lang.compiler.typesystem.CustomFormalParameter;
import high.mackenzie.autumn.lang.compiler.typesystem.CustomMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IAnnotation;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IEnumConstant;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IField;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IFormalParameter;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IInterfaceType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.utils.Utils;
import high.mackenzie.autumn.resources.Finished;
import java.util.List;
import java.util.Set;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

/**
 * An instance of this class controls the compilation of an enum-definition.
 *
 * @author Mackenzie High
 */
@Finished("2014/08/11")
final class EnumCompiler
        implements ICompiler
{
    /**
     * Essentially, this is the program that is being compiled.
     */
    public final ProgramCompiler program;

    /**
     * Essentially, this is the module that contains the enum-definition.
     */
    public final ModuleCompiler module;

    /**
     * This is the Abstract-Syntax-Tree representation of the enum-definition.
     */
    public final EnumDefinition node;

    /**
     * This will be the type-system representation of the enum-definition.
     *
     * This field is set during the type-declaration compiler pass.
     */
    public CustomDeclaredType type;

    /**
     * This is the type of the values() method.
     */
    private final CustomMethod method_values;

    /**
     * This is the type of the valueOf(String) method.
     */
    private final CustomMethod method_valueof;

    /**
     * Sole Constructor.
     *
     * @param module is the module that contains the enum being compiled.
     * @param node is the AST node that represents the enum being compiled.
     */
    public EnumCompiler(final ModuleCompiler module,
                        final EnumDefinition node)
    {
        Preconditions.checkNotNull(module);
        Preconditions.checkNotNull(node);

        this.program = module.program;
        this.module = module;
        this.node = node;

        this.method_values = new CustomMethod(program.typesystem.typefactory(), false);
        this.method_valueof = new CustomMethod(program.typesystem.typefactory(), false);
    }

    /**
     * This method generates the compiled class-file.
     *
     * @return the compiled class-file.
     */
    public ClassFile build()
    {
        final String enum_internal_name = Utils.internalName(type);

        final String enum_source_name = Utils.sourceName(type);

        /**
         * Create the bytecode representations of the enum-constants' fields.
         */
        final List<FieldNode> fields = Lists.newLinkedList();
        {
            for (IEnumConstant ec : type.getEnumConstants())
            {
                final int access = ec.getModifiers();
                final String name = ec.getName();
                final String desc = ec.getType().getDescriptor();
                final FieldNode field = new FieldNode(access, name, desc, null, null);

                fields.add(field);
            }
        }

        /**
         * Create the bytecode representation of the enum itself.
         */
        final ClassNode clazz = new ClassNode();
        {
            clazz.version = Opcodes.V1_6;
            clazz.visibleAnnotations = module.anno_utils.compileAnnotationList(type.getAnnotations());
            clazz.access = type.getModifiers();
            clazz.name = enum_internal_name;
            clazz.superName = Utils.internalName(type.getSuperclass());
            clazz.interfaces = Lists.newLinkedList();
            clazz.fields = fields;
            clazz.methods = Lists.newLinkedList();
            clazz.sourceFile = String.valueOf(node.getLocation().getFile());

            // Add some special methods to the enum.
            clazz.methods.add(generateStaticInitializer());
            clazz.methods.add(generateConstructor());
            clazz.methods.add(generateMethodValues());
            clazz.methods.add(generateMethodValueOf());
        }

        /**
         * Assemble the bytecode into an array of bytes.
         */
        final ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        clazz.accept(writer);
        final byte[] bytecode = writer.toByteArray();

        /**
         * Create the class-file object that will store the emitted bytecode.
         */
        final ClassFile file = new ClassFile(enum_source_name, bytecode);

        return file;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeDeclaration()
    {
        /**
         * Determine the descriptor of the enum.
         */
        final String namespace = module.type.getNamespace().replace('.', '/');
        final String name = node.getName().getName();
        final String descriptor = "L" + namespace + '/' + name + ';';

        /**
         * Ensure that the type was not already declared elsewhere.
         */
        program.checker.requireNonDuplicateType(node.getName(), descriptor);

        /**
         * Declare the enum.
         */
        this.type = program.typesystem.typefactory().newEnumType(descriptor);
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
        type.setAnnotations(module.anno_utils.typesOf(node.getAnnotations()));

        /**
         * Add a special annotation.
         */
        module.anno_utils.add(type, autumn.lang.internals.annotations.EnumDefinition.class);

        /**
         * Check the list of annotations.
         */
        program.checker.checkAnnotations(node.getAnnotations(), type.getAnnotations());

        /**
         * Set the type's access modifiers and make the type an enum.
         */
        type.setModifiers(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL | Opcodes.ACC_SUPER | Opcodes.ACC_ENUM);

        /**
         * Create the type-system representations of the enum-constant's field.
         */
        final List<IField> constants = Lists.newLinkedList();
        {
            int ordinal = 0;

            for (Name ec : node.getConstants())
            {
                final CustomField field = new CustomField(program.typesystem.typefactory());
                {
                    field.setOwner(type);
                    field.setModifiers(Opcodes.ACC_PUBLIC
                                       | Opcodes.ACC_STATIC
                                       | Opcodes.ACC_ENUM
                                       | Opcodes.ACC_FINAL);
                    field.setName(ec.getName());
                    field.setType(type);
                    field.setOrdinal(ordinal++);

                    constants.add(field);
                }
            }
        }

        /**
         * Initialize the type-system representation of the values() method.
         */
        method_values.setOwner(type);
        method_values.setAnnotations(ImmutableList.<IAnnotation>of());
        method_values.setModifiers(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC | Opcodes.ACC_FINAL);
        method_values.setName("values");
        method_values.setParameters(ImmutableList.<IFormalParameter>of());
        method_values.setReturnType(program.typesystem.typefactory().getArrayType(type, 1));

        /**
         * Initialize the type-system representation of the valueOf(String) method.
         */
        method_valueof.setOwner(type);
        method_valueof.setAnnotations(ImmutableList.<IAnnotation>of());
        method_valueof.setModifiers(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC | Opcodes.ACC_FINAL);
        method_valueof.setName("valueOf");
        final CustomFormalParameter param = new CustomFormalParameter();
        param.setType(program.typesystem.utils.STRING);
        method_valueof.setParameters(ImmutableList.<IFormalParameter>of(param));
        method_valueof.setReturnType(type);

        /**
         * Initialize the type that represents the enum being compiled.
         */
        type.setModifiers(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL | Opcodes.ACC_ENUM);
        type.setSuperclass(program.typesystem.utils.ENUM);
        type.setSuperinterfaces(ImmutableList.<IInterfaceType>of());
        type.setFields(constants);
        type.setMethods(ImmutableList.<IMethod>of(method_values, method_valueof));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeStructureChecking()
    {
        final Set<String> constants = Sets.newHashSet();

        for (Name name : node.getConstants())
        {
            /**
             * No two enum-constants can have the same name.
             */
            if (constants.contains(name.getName()))
            {
                program.checker.reportDuplicateEnumConstant(type, name);
            }
            else
            {
                constants.add(name.getName());
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeUsageChecking()
    {
        // Pass, because no type-usages exist to check.
    }

    /**
     * This method generates the static-initializer that initializes the enum-constant fields.
     *
     * @return the bytecode representation of the generated method.
     */
    private MethodNode generateStaticInitializer()
    {
        final MethodNode method = new MethodNode();
        method.access = Opcodes.ACC_PRIVATE | Opcodes.ACC_STATIC;
        method.name = "<clinit>";
        method.desc = "()V";
        method.exceptions = ImmutableList.of();

        // Now bytecode will be generated that initializes the enum-constants' fields.

        for (IEnumConstant ec : type.getEnumConstants())
        {
            // Create an instance of the enum.
            method.instructions.add(new TypeInsnNode(Opcodes.NEW, Utils.internalName(type)));

            // Duplicate the enum object-reference on the operand-stack.
            method.instructions.add(new InsnNode(Opcodes.DUP));

            // Push the enum-constant's name onto the operand-stack.
            method.instructions.add(new LdcInsnNode(ec.getName()));

            // Push the enum-constant's ordinal onto the stack.
            method.instructions.add(new LdcInsnNode(ec.getOrdinal()));

            // Call the enum's instance constructor.
            method.instructions.add(new MethodInsnNode(Opcodes.INVOKESPECIAL,
                                                       Utils.internalName(type),
                                                       "<init>",
                                                       "(Ljava/lang/String;I)V"));

            // Assign the enum object-reference to the enum-constant's field.
            method.instructions.add(new FieldInsnNode(Opcodes.PUTSTATIC,
                                                      Utils.internalName(type),
                                                      ec.getName(),
                                                      ec.getType().getDescriptor()));

            // Add NOPs to aid debugging bytecode.
            method.instructions.add(new InsnNode(Opcodes.NOP));
            method.instructions.add(new InsnNode(Opcodes.NOP));
        }

        // Return.
        method.instructions.add(new InsnNode(Opcodes.RETURN));

        return method;
    }

    /**
     * This method generates an instance constructor for the enum.
     *
     * @return the bytecode representation of the generated method.
     */
    private MethodNode generateConstructor()
    {
        final MethodNode method = new MethodNode();
        method.access = Opcodes.ACC_PRIVATE;
        method.name = "<init>";
        method.desc = "(Ljava/lang/String;I)V";
        method.exceptions = ImmutableList.of();

        // Invoke the super constructor.
        method.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0)); // load 'this'
        method.instructions.add(new VarInsnNode(Opcodes.ALOAD, 1)); // load argument #1 (name)
        method.instructions.add(new VarInsnNode(Opcodes.ILOAD, 2)); // load argument #2 (ordinal)
        method.instructions.add(new MethodInsnNode(Opcodes.INVOKESPECIAL,
                                                   "java/lang/Enum",
                                                   "<init>",
                                                   "(Ljava/lang/String;I)V"));

        // Return.
        method.instructions.add(new InsnNode(Opcodes.RETURN));

        return method;
    }

    /**
     * This method generates the values() method for the enum.
     *
     * @return the bytecode representation of the generated method.
     */
    private MethodNode generateMethodValues()
    {
        final MethodNode method = Utils.bytecodeOf(module, method_values);

        // Now bytecode will be generated that creates an array and then returns it.

        // Push the size of the array onto the operand-stack.
        method.instructions.add(new LdcInsnNode(type.getEnumConstants().size()));

        // Create the array that will contain the enum-constants.
        method.instructions.add(new TypeInsnNode(Opcodes.ANEWARRAY, Utils.internalName(type)));

        // Push the initial value of a counter onto the operand-stack.
        method.instructions.add(new LdcInsnNode(0));

        // Add NOPs to aid debugging bytecode.
        method.instructions.add(new InsnNode(Opcodes.NOP));
        method.instructions.add(new InsnNode(Opcodes.NOP));

        for (IEnumConstant ec : type.getEnumConstants())
        {
            // Duplicate the top two elements on the operand-stack.
            // Specifically, duplicate the array-reference and the counter.
            method.instructions.add(new InsnNode(Opcodes.DUP2));

            // Get the value stored in the enum-constant's field.
            method.instructions.add(new FieldInsnNode(Opcodes.GETSTATIC,
                                                      Utils.internalName(type),
                                                      ec.getName(),
                                                      ec.getType().getDescriptor()));

            // Put the value from the enum-constant's field into the array.
            // The index of the array element is the counter.
            method.instructions.add(new InsnNode(Opcodes.AASTORE));

            // Increment the counter, by one.
            method.instructions.add(new LdcInsnNode(1));
            method.instructions.add(new InsnNode(Opcodes.IADD));

            // Add NOPs to aid debugging bytecode.
            method.instructions.add(new InsnNode(Opcodes.NOP));
            method.instructions.add(new InsnNode(Opcodes.NOP));
        }

        // Pop the counter off of the operand-stack.
        method.instructions.add(new InsnNode(Opcodes.POP));

        // Return the array.
        method.instructions.add(new InsnNode(Opcodes.ARETURN));

        return method;
    }

    /**
     * This method generates the valueOf(String) method for the enum.
     *
     * @return the bytecode representation of the generated method.
     */
    private MethodNode generateMethodValueOf()
    {
        final MethodNode method = Utils.bytecodeOf(module, method_valueof);

        // Now bytecode will be generated that searches for the specified enum-constant.

        // Get an array containing all of the num-constants, by calling the values() method.
        method.instructions.add(new MethodInsnNode(Opcodes.INVOKESTATIC,
                                                   Utils.internalName(type),
                                                   method_values.getName(),
                                                   method_values.getDescriptor()));

        // Load the name of the enum-constant to search for onto the operand-stack.
        method.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0));

        // Call a helper method that will perform the search.
        method.instructions.add(new MethodInsnNode(Opcodes.INVOKESTATIC,
                                                   Utils.internalName(program.typesystem.utils.HELPERS),
                                                   "findEnumConstant",
                                                   "([Ljava/lang/Enum;Ljava/lang/String;)Ljava/lang/Enum;"));

        // Cast the return-value of the helper method to the type of the enum being compiled.
        // Note: This cast will always succeed at runtime.
        method.instructions.add(new TypeInsnNode(Opcodes.CHECKCAST, Utils.internalName(type)));

        // Return the enum-constant that was found.
        method.instructions.add(new InsnNode(Opcodes.ARETURN));

        return method;
    }
}
