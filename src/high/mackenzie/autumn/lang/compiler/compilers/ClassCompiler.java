package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.Prototype;
import autumn.lang.compiler.ClassFile;
import autumn.lang.compiler.ast.nodes.SourceLocation;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IFormalParameter;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IInterfaceType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IReferenceType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVariableType;
import high.mackenzie.autumn.lang.compiler.utils.Design;
import high.mackenzie.autumn.lang.compiler.utils.GetterList;
import high.mackenzie.autumn.lang.compiler.utils.MethodList;
import high.mackenzie.autumn.lang.compiler.utils.ProtoClass;
import high.mackenzie.autumn.lang.compiler.utils.SetterList;
import high.mackenzie.autumn.lang.compiler.utils.Utils;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

/**
 * An instance of this class is used by a create-expression to create an instantiatable type.
 *
 * @author Mackenzie High
 */
public final class ClassCompiler
{
    private final ProtoClass proto = new ProtoClass();

    private static int counter = 0;

    public final ProgramCompiler program;

    public final ModuleCompiler module;

    public final IInterfaceType type;

    public final SourceLocation location;

    private final int index = counter++;

    public final Design info;

    private final Map<GetterList, FieldNode> getter_handlers = Maps.newIdentityHashMap();

    private final Map<SetterList, FieldNode> setter_handlers = Maps.newIdentityHashMap();

    private final Map<MethodList, FieldNode> method_handlers = Maps.newIdentityHashMap();

    public ClassCompiler(final ModuleCompiler module,
                         final IInterfaceType type,
                         final SourceLocation location)
    {
        this.program = module.program;
        this.module = module;
        this.type = type;
        this.location = location;

        this.info = new Design(type);
    }

    protected String name()
    {
        final String namespace = module.type.getNamespace().replace(".", "/") + '/';

        return namespace + "AutumnSyntheticCreator" + index;
    }

    /**
     * This method generates the compiled class-file.
     *
     * @return the compiled class-file.
     */
    public ClassFile build()
    {
        final String source_name = name().replace('/', '.');

        /**
         * Create the bytecode representation of the enum itself.
         */
        final ClassNode clazz = new ClassNode();
        {
            clazz.version = Opcodes.V1_6;
            clazz.visibleAnnotations = Lists.newLinkedList();
            clazz.access = Opcodes.ACC_FINAL;
            clazz.name = name();
            clazz.superName = Utils.internalName(type.getSuperclass());
            clazz.interfaces = ImmutableList.of(Utils.internalName(type));
            clazz.methods = Lists.newLinkedList();
            clazz.sourceFile = String.valueOf(location.getFile());

            addCtor(clazz);

            addCopyCtor(clazz);

            addSpecialMethodDesign(clazz);
            addSpecialMethodCopy(clazz);

            for (GetterList getters : info.getters().values())
            {
                final IMethod most_specific = getters.list().get(0);
                addGetter(clazz, most_specific);
                addBridgeGetters(clazz, getters.bridgeGetters());
                addPropertyField(clazz, most_specific);
                addGetterHandlerField(clazz, most_specific.getName());
                addSetterHandlerField(clazz, most_specific.getName());
            }

            for (SetterList setters : info.setters().values())
            {
                final IMethod most_specific = setters.list().get(0);
                addSetter(clazz, most_specific);
                addBridgeSetters(clazz, setters.bridgeSetters());
            }

            for (MethodList methods : info.methods().values())
            {
                final IMethod most_specific = methods.list().get(0);

                if (isSpecialMethod(most_specific) == false)
                {
                    addMethod(clazz, most_specific);
                    addBridgeMethods(clazz, methods.bridgeMethods());
                }
            }
        }

//        proto.print(System.out);

        /**
         * Assemble the bytecode into an array of bytes.
         */
        final ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        clazz.accept(writer);
        final byte[] bytecode = writer.toByteArray();

        /**
         * Create the class-file object that will store the emitted bytecode.
         */
        final ClassFile file = new ClassFile(source_name, bytecode);

        return file;
    }

    private boolean isSpecialMethod(final IMethod method)
    {
        // TODO: change
        for (Class c : ImmutableList.of(Object.class, Serializable.class, Cloneable.class, Prototype.class))
        {
            for (Method m : c.getDeclaredMethods())
            {
                if (m.getName().equals(method.getName()))
                {
                    return true;
                }
            }
        }

        return false;
    }

    private void addCtor(final ClassNode clazz)
    {
        final MethodNode ctor = new MethodNode();
        ctor.access = Opcodes.ACC_PUBLIC;
        ctor.name = "<init>";
        ctor.desc = "()V";
        ctor.exceptions = ImmutableList.of();

        ctor.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0)); // load 'this'
        ctor.instructions.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, clazz.superName, "<init>", "()V"));
        ctor.instructions.add(new InsnNode(Opcodes.RETURN));

        clazz.methods.add(ctor);
    }

    private void addCopyCtor(final ClassNode clazz)
    {
        final MethodNode ctor = new MethodNode();
        ctor.access = Opcodes.ACC_PRIVATE;
        ctor.name = "<init>";
        ctor.desc = "(L" + name() + ";)V";
        ctor.exceptions = ImmutableList.of();

        ctor.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0)); // load 'this'
        ctor.instructions.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, clazz.superName, "<init>", "()V"));
        copyPropertyFields(ctor.instructions);
        copyGetterHandlerFields(ctor.instructions);
        copySetterHandlerFields(ctor.instructions);
        copyMethodHandlerFields(ctor.instructions);
        ctor.instructions.add(new InsnNode(Opcodes.RETURN));

        clazz.methods.add(ctor);
    }

    private void copyPropertyFields(final InsnList code)
    {
        for (String property : info.getters().keySet())
        {
            copyField(code, property, info.typeof(property).getDescriptor());
        }
    }

    private void copyGetterHandlerFields(final InsnList code)
    {
        for (String property : info.getters().keySet())
        {
            final String name = "autumn$getter$" + property;

            final String desc = program.typesystem.utils.FUNCTOR.getDescriptor();

            copyField(code, name, desc);
        }
    }

    private void copySetterHandlerFields(final InsnList code)
    {
        for (String property : info.setters().keySet())
        {
            final String name = "autumn$setter$" + property;

            final String desc = program.typesystem.utils.FUNCTOR.getDescriptor();

            copyField(code, name, desc);
        }
    }

    private void copyMethodHandlerFields(final InsnList code)
    {
    }

    private void copyField(final InsnList code,
                           final String name,
                           final String desc)
    {
        // Load 'this'.
        code.add(new VarInsnNode(Opcodes.ALOAD, 0));

        // Load the original copy, which is the only argument to the constructor.
        code.add(new VarInsnNode(Opcodes.ALOAD, 1));

        // Get the value of the field in the original copy.
        code.add(new FieldInsnNode(Opcodes.GETFIELD, name(), name, desc));

        // Put the value into in the field in 'this'.
        code.add(new FieldInsnNode(Opcodes.PUTFIELD, name(), name, desc));
    }

    private void addPropertyField(final ClassNode clazz,
                                  final IMethod getter)
    {
        final int modifiers = Opcodes.ACC_PRIVATE;

        final String name = getter.getName();

        final String desc = getter.getReturnType().getDescriptor();

        final FieldNode field = new FieldNode(modifiers, name, desc, null, null);

        clazz.fields.add(field);

        proto.properties.add(proto.new Property(name, (IVariableType) getter.getReturnType(), true, true, true));
    }

    private void addGetter(final ClassNode clazz,
                           final IMethod getter)
    {
        final MethodNode m = new MethodNode();
        m.access = getter.getModifiers() - Opcodes.ACC_ABSTRACT;
        m.name = getter.getName();
        m.desc = getter.getDescriptor();
        m.exceptions = ImmutableList.of(); // TODO: change

        final IVariableType datatype = info.typeof(m.name);

        m.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0));
        m.instructions.add(new FieldInsnNode(Opcodes.GETFIELD, name(), m.name, datatype.getDescriptor()));
        m.instructions.add(Utils.selectReturnInsn(getter.getReturnType()));

        clazz.methods.add(m);
    }

    private void addSetter(final ClassNode clazz,
                           final IMethod setter)
    {
        final MethodNode m = new MethodNode();
        m.access = setter.getModifiers() - Opcodes.ACC_ABSTRACT;
        m.name = setter.getName();
        m.desc = setter.getDescriptor();
        m.exceptions = ImmutableList.of(); // TODO: change

        final IVariableType datatype = info.typeof(m.name);

        m.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0));
        m.instructions.add(Utils.selectLoadVarInsn(datatype, 1));
        m.instructions.add(new FieldInsnNode(Opcodes.PUTFIELD, name(), m.name, datatype.getDescriptor()));
        m.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0));
        m.instructions.add(new InsnNode(Opcodes.ARETURN));

        clazz.methods.add(m);
    }

    private void addMethod(final ClassNode clazz,
                           final IMethod method)
    {
        final MethodNode m = new MethodNode();
        m.access = method.getModifiers() - Opcodes.ACC_ABSTRACT;
        m.name = method.getName();
        m.desc = method.getDescriptor();
        m.exceptions = ImmutableList.of(); // TODO: change

        m.instructions.add(Utils.selectLdcDefault(method.getReturnType()));
        m.instructions.add(Utils.selectReturnInsn(method.getReturnType()));

        clazz.methods.add(m);
    }

    private void addBridgeGetters(final ClassNode clazz,
                                  final Map<IMethod, IMethod> bridges)
    {
        for (Entry<IMethod, IMethod> bridge : bridges.entrySet())
        {
            addBridgeGetter(clazz, bridge.getKey(), bridge.getValue());
        }
    }

    private void addBridgeSetters(final ClassNode clazz,
                                  final Map<IMethod, IMethod> bridges)
    {
        for (Entry<IMethod, IMethod> bridge : bridges.entrySet())
        {
            addBridgeSetter(clazz, bridge.getKey(), bridge.getValue());
        }
    }

    private void addBridgeMethods(final ClassNode clazz,
                                  final Map<IMethod, IMethod> bridges)
    {
        for (Entry<IMethod, IMethod> bridge : bridges.entrySet())
        {
            addBridgeMethod(clazz, bridge.getKey(), bridge.getValue());
        }
    }

    private void addBridgeGetter(final ClassNode clazz,
                                 final IMethod caller,
                                 final IMethod callee)
    {
        addBridgeMethod(clazz, caller, callee);
    }

    private void addBridgeSetter(final ClassNode clazz,
                                 final IMethod caller,
                                 final IMethod callee)
    {
        final MethodNode m = new MethodNode();
        m.access = caller.getModifiers() - Opcodes.ACC_ABSTRACT;
        m.name = caller.getName();
        m.desc = caller.getDescriptor();
        m.exceptions = ImmutableList.of(); // TODO: change

        clazz.methods.add(m);

        final IVariableType param_type = callee.getFormalParameters().get(0).getType();

        // Load 'this' onto the operand-stack, because it owns the method to invoke.
        m.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0));

        // Load the parameter onto the operand-stack, because it is the argument.
        m.instructions.add(Utils.selectLoadVarInsn(type, 1));

        // If the argument is an object, cast it to the appropriate type.
        if (param_type.isReferenceType())
        {
            final IReferenceType casted = (IReferenceType) param_type;

            m.instructions.add(new TypeInsnNode(Opcodes.CHECKCAST, Utils.internalName(casted)));
        }

        // Invoke the real method.
        m.instructions.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL,
                                              name(),
                                              callee.getName(),
                                              callee.getDescriptor()));

        // Return from the bridge method.
        m.instructions.add(Utils.selectReturnInsn(caller.getReturnType()));
    }

    private void addBridgeMethod(final ClassNode clazz,
                                 final IMethod caller,
                                 final IMethod callee)
    {
        final MethodNode m = new MethodNode();
        m.access = caller.getModifiers() - Opcodes.ACC_ABSTRACT;
        m.name = caller.getName();
        m.desc = caller.getDescriptor();
        m.exceptions = ImmutableList.of(); // TODO: change
        clazz.methods.add(m);

        // Load 'this' onto the operand-stack, because it owns the method to invoke.
        m.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0));

        // Load the parameters onto the operand-stack, because they are the arguments.
        loadParameters(m.instructions, caller);

        // Invoke the real method.
        m.instructions.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL,
                                              name(),
                                              callee.getName(),
                                              callee.getDescriptor()));

        // Return from the bridge method.
        m.instructions.add(Utils.selectReturnInsn(caller.getReturnType()));
    }

    private void loadParameters(final InsnList code,
                                final IMethod method)
    {
        // Start at address #1, because 'this' is at address #0.
        int address = 1;

        // Load each parameter onto the operand-stack.
        for (IFormalParameter param : method.getFormalParameters())
        {
            final IVariableType param_type = param.getType();

            code.add(Utils.selectLoadVarInsn(param_type, address));

            address += Utils.sizeof(param_type);
        }
    }

    private void addSpecialMethodDesign(final ClassNode clazz)
    {
        final MethodNode m = new MethodNode();
        m.access = Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL;
        m.name = "objectDesign";
        m.desc = "()Ljava/lang/Class;";
        m.exceptions = ImmutableList.of();
        clazz.methods.add(m);

        m.instructions.add(Utils.ldcClass(type));
        m.instructions.add(Utils.selectReturnInsn(type));
    }

    private void addSpecialMethodCopy(final ClassNode clazz)
    {
        // TODO: Add a bridge method for this too!
    }

    private void addSpecialMethodProperties(final ClassNode clazz)
    {
    }

    private void addSpecialMethodMethods(final ClassNode clazz)
    {
    }

    private void addSpecialMethodSetGetter(final ClassNode clazz)
    {
    }

    private void addSpecialMethodSetSetter(final ClassNode clazz)
    {
    }

    private void addSpecialMethodSetMethod(final ClassNode clazz)
    {
    }

    private void addSpecialMethodSetValue(final ClassNode clazz)
    {
    }

    private void addSpecialMethodGetValue(final ClassNode clazz)
    {
    }

    private void addSetterHandlerField(final ClassNode clazz,
                                       final String property)
    {
        final int modifiers = Opcodes.ACC_PRIVATE;

        final String name = "autumn$setter$" + property;

        final String desc = program.typesystem.utils.FUNCTOR.getDescriptor();

        final FieldNode field = new FieldNode(modifiers, name, desc, null, null);

        clazz.fields.add(field);
    }

    private void addGetterHandlerField(final ClassNode clazz,
                                       final String property)
    {
        final int modifiers = Opcodes.ACC_PRIVATE;

        final String name = "autumn$getter$" + property;

        final String desc = program.typesystem.utils.FUNCTOR.getDescriptor();

        final FieldNode field = new FieldNode(modifiers, name, desc, null, null);

        clazz.fields.add(field);
    }

    private void addMethodHandlerField(final ClassNode clazz,
                                       final String method)
    {
        final int modifiers = Opcodes.ACC_PRIVATE;

        final String name = "autumn$method$" + method;

        final String desc = program.typesystem.utils.FUNCTOR.getDescriptor();

        final FieldNode field = new FieldNode(modifiers, name, desc, null, null);

        clazz.fields.add(field);
    }

    public void load(final List<AbstractInsnNode> code)
    {
        code.add(new TypeInsnNode(Opcodes.NEW, name()));
        code.add(new InsnNode(Opcodes.DUP));
        code.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, name(), "<init>", "()V"));
    }
}
