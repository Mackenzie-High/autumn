package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.compiler.ClassFile;
import autumn.lang.compiler.ast.commons.IRecord;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IConstructor;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IInterfaceType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVariableType;
import high.mackenzie.autumn.lang.compiler.utils.BridgeMethod;
import high.mackenzie.autumn.lang.compiler.utils.GetterMethod;
import high.mackenzie.autumn.lang.compiler.utils.RecordElement;
import high.mackenzie.autumn.lang.compiler.utils.SetterMethod;
import high.mackenzie.autumn.lang.compiler.utils.TypeSystemUtils;
import high.mackenzie.autumn.lang.compiler.utils.Utils;
import java.util.List;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

/**
 * An instance of this class controls the compilation of a design-definition.
 *
 * @author Mackenzie High
 */
class DesignCompiler
        extends AbstractRecordCompiler
{
    /**
     * Sole Constructor.
     *
     * @param module is the module that contains the tuple being compiled.
     * @param node is the AST node that represents the tuple being compiled.
     */
    public DesignCompiler(final ModuleCompiler module,
                          final IRecord node)
    {
        super(module, node);

        assert module != null;
        assert node != null;
    }

    /**
     * A design-type never has a constructor, because design-types compiles to an interface.
     *
     * @return null.
     */
    @Override
    protected IConstructor typeofCtor()
    {
        return null;
    }

    /**
     * A design-type never has an instance() method, because design-types compiles to an interface.
     *
     * @return null.
     */
    @Override
    protected IMethod typeofInstance()
    {
        return null;
    }

    /**
     * This method generates the compiled class-file.
     *
     * @return the compiled class-file.
     */
    public ClassFile build()
    {
        final String internal_name = Utils.internalName(type);

        final String source_name = Utils.sourceName(type);

        /**
         * Create the bytecode representation of the tuple itself.
         */
        final ClassNode clazz = new ClassNode();
        {
            clazz.version = Opcodes.V1_6;
            clazz.visibleAnnotations = module.anno_utils.compileAnnotationList(type.getAnnotations());
            clazz.access = type.getModifiers();
            clazz.name = internal_name;
            clazz.superName = Utils.internalName(type.getSuperclass());
            clazz.fields = ImmutableList.of();
            clazz.methods = Lists.newLinkedList();
            clazz.sourceFile = String.valueOf(node.getLocation().getFile());

            /**
             * The record may implement zero or more designs, as specified by the user.
             */
            for (IInterfaceType superinterface : type.getSuperinterfaces())
            {
                clazz.interfaces.add(Utils.internalName(superinterface));
            }

            /**
             * Generate the special methods.
             */
            clazz.methods.add(this.generateMethodSet());

            /**
             * Generate all the bridge methods.
             */
            clazz.methods.addAll(this.generateBridgeMethods());

            /**
             * Generate all the setters and getters.
             */
            for (RecordElement element : analyzer.elements.values())
            {
                clazz.methods.add(this.generateSetter(element.setter()));
                clazz.methods.add(this.generateGetter(element.getter()));
            }
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
        final ClassFile file = new ClassFile(source_name, bytecode);

        return file;
    }

    /**
     * This method generates the bytecode representations of the bridge methods.
     *
     * @return the generated methods.
     */
    private List<MethodNode> generateBridgeMethods()
    {
        final List<MethodNode> result = Lists.newLinkedList();

        /**
         * Generate the bridge methods related to predefined methods.
         */
        for (BridgeMethod bridge : special_bridges)
        {
            result.add(bridge.compileAbstract(module));
        }

        /**
         * Generate the bridge methods related to elements.
         */
        for (RecordElement element : analyzer.elements().values())
        {
            /**
             * Generate the bridge getter methods.
             */
            for (GetterMethod getter : element.bridgeGetters())
            {
                final BridgeMethod bridge = new BridgeMethod(getter.findSelf(),
                                                             getter.findBridgeTarget());

                result.add(bridge.compileAbstract(module));
            }

            /**
             * Generate the bridge setter methods.
             */
            for (SetterMethod setter : element.bridgeSetters())
            {
                final BridgeMethod bridge = new BridgeMethod(setter.findSelf(),
                                                             setter.findBridgeTarget());

                result.add(bridge.compileAbstract(module));
            }
        }

        return result;
    }

    /**
     * This method generates the bytecode representation of the set(int, Object) method.
     *
     * @return the generated method.
     */
    private MethodNode generateMethodSet()
    {
        final MethodNode method = Utils.bytecodeOf(module,
                                                   TypeSystemUtils.find(type.getAllVisibleMethods(),
                                                                        "set",
                                                                        "(ILjava/lang/Object;)" + program.typesystem.utils.RECORD.getDescriptor()));

        // Add the ABSTRACT modifier.
        method.access = method.access | Opcodes.ACC_ABSTRACT;

        return method;
    }

    /**
     * This method generates the bytecode representation of a setter method.
     *
     * <p>
     * A setter method must obtain a modifiable variant of the tuple.
     * In other words, the setter must copy the tuple, if it is immutable.
     * Then the setter must set the field in the tuple to the new value.
     * Finally, the setter must return the modified tuple.
     * Take note, the returned tuple may not be the original tuple.
     * </p>
     *
     * @param element is an object that describes the element.
     * @return the generated method.
     */
    private MethodNode generateSetter(final SetterMethod element)
    {
        /**
         * Get the static-type of the element.
         */
        final IVariableType element_type = typeOfElement(element.name);

        final MethodNode method = Utils.bytecodeOf(module,
                                                   TypeSystemUtils.find(type.getMethods(),
                                                                        element.name,
                                                                        "(" + element_type.getDescriptor() + ")" + type.getDescriptor()));

        // Add the ABSTRACT modifier.
        method.access = method.access | Opcodes.ACC_ABSTRACT;

        return method;
    }

    /**
     * This method generates the bytecode representation of a getter method.
     *
     * <p>
     * A getter method simply read the field that stores the element and then returns the result.
     * </p>
     *
     * @param element is an object that describes the element.
     * @return the generated method.
     */
    private MethodNode generateGetter(final GetterMethod element)
    {
        /**
         * Get the static-type of the element.
         */
        final IVariableType element_type = typeOfElement(element.name);

        final MethodNode method = Utils.bytecodeOf(module,
                                                   TypeSystemUtils.find(type.getMethods(),
                                                                        element.name,
                                                                        "()" + element_type.getDescriptor()));

        // Add the ABSTRACT modifier.
        method.access = method.access | Opcodes.ACC_ABSTRACT;

        return method;
    }

    /**
     * This method retrieves the static-type of an element given the element's name.
     *
     * @param key is the name of the element.
     * @return the most-specific static-type of the element.
     */
    private IVariableType typeOfElement(final String key)
    {
        final IVariableType result = analyzer.elements.get(key).type();

        return result;
    }
}
