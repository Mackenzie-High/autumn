package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.compiler.ast.commons.IRecord;
import autumn.lang.compiler.ast.nodes.DesignDefinition;
import autumn.lang.compiler.ast.nodes.Element;
import autumn.lang.compiler.ast.nodes.StructDefinition;
import autumn.lang.compiler.ast.nodes.TupleDefinition;
import autumn.lang.compiler.ast.nodes.TypeSpecifier;
import autumn.lang.internals.annotations.Getter;
import autumn.lang.internals.annotations.Setter;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import high.mackenzie.autumn.lang.compiler.typesystem.CustomDeclaredType;
import high.mackenzie.autumn.lang.compiler.typesystem.CustomFormalParameter;
import high.mackenzie.autumn.lang.compiler.typesystem.CustomMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IAnnotation;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IConstructor;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IFormalParameter;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IInterfaceType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVariableType;
import high.mackenzie.autumn.lang.compiler.utils.BridgeMethod;
import high.mackenzie.autumn.lang.compiler.utils.CovarianceViolation2;
import high.mackenzie.autumn.lang.compiler.utils.GetterMethod;
import high.mackenzie.autumn.lang.compiler.utils.RecordAnalyzer2;
import high.mackenzie.autumn.lang.compiler.utils.RecordElement;
import high.mackenzie.autumn.lang.compiler.utils.SetterMethod;
import high.mackenzie.autumn.lang.compiler.utils.TypeSystemUtils;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.objectweb.asm.Opcodes;

/**
 * This class provides a partial implementation of the ICompiler interface for compiling records.
 *
 * <p>
 * This class provides the logic necessary to declare and type-check record-types.
 * </p>
 *
 * @author Mackenzie High
 */
public abstract class AbstractRecordCompiler
        implements ICompiler
{
    /**
     * This method creates the type-system representation of the only constructor in the record.
     *
     * @return the aforedescribed constructor; or null, if no constructor is needed.
     */
    protected abstract IConstructor typeofCtor();

    /**
     * This method creates the type-system representation of the instance() method.
     *
     * @return the aforedescribed method; or null, if no instance() method is needed.
     */
    protected abstract IMethod typeofInstance();

    /**
     * Essentially, this is the program that is being compiled.
     */
    public final ProgramCompiler program;

    /**
     * Essentially, this is the enclosing module that is also being compiled.
     */
    public final ModuleCompiler module;

    /**
     * This is the Abstract-Syntax-Tree representation of the tuple's definition.
     */
    public final IRecord node;

    /**
     * This will be the type-system representation of the tuple's definition.
     *
     * This field will be initialized during the type-declaration compiler pass.
     */
    public CustomDeclaredType type;

    /**
     * This object is used to infer inherited elements and detect problems related thereto.
     *
     * This field will be initialized during the type-initialization compiler pass.
     */
    protected RecordAnalyzer2 analyzer;

    /**
     * These objects will simplify the creation of special bridge methods.
     *
     * The elements of this list will be created during the type-initialization compiler pass.
     *
     * These objects will be used to generate the bytecode representations of bridge methods.
     */
    protected final List<BridgeMethod> bridges = Lists.newLinkedList();

    /**
     * This list stores the keys of the record.
     *
     * Ordering of the keys is important!
     */
    protected final Set<String> keys = Sets.newLinkedHashSet();

    /**
     * This flag is true, iff the record-type being compiled is a design-type.
     */
    protected final boolean design;

    /**
     * This flag is true, iff the record-type being compiled is a struct-type.
     */
    protected final boolean struct;

    /**
     * This flag is true, iff the record-type being compiled is a tuple-type.
     */
    protected final boolean tuple;

    /**
     * Sole Constructor.
     *
     * @param module is the module that contains the tuple being compiled.
     * @param node is the AST node that represents the tuple being compiled.
     */
    public AbstractRecordCompiler(final ModuleCompiler module,
                                  final IRecord node)
    {
        assert module != null;
        assert node != null;
        assert module.tuples.contains(node);

        this.program = module.program;
        this.module = module;
        this.node = node;
        this.design = node instanceof DesignDefinition;
        this.struct = node instanceof StructDefinition;
        this.tuple = node instanceof TupleDefinition;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void performTypeDeclaration()
    {
        /**
         * Determine the descriptor of the tuple.
         */
        final String namespace = module.type.getNamespace().replace('.', '/');
        final String name = node.getName().getName();
        final String descriptor = "L" + namespace + '/' + name + ';';

        /**
         * Ensure that the type was not already declared elsewhere.
         */
        program.checker.requireNonDuplicateType(node.getName(), descriptor);

        /**
         * Declare the tuple.
         */
        if (design)
        {
            this.type = program.typesystem.typefactory().newInterfaceType(descriptor);
        }
        else // struct | tuple
        {
            this.type = program.typesystem.typefactory().newClassType(descriptor);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void performTypeInitialization()
    {
        /**
         * Apply the user-applied annotations to the record-type.
         */
        type.setAnnotations(module.anno_utils.typesOf(node.getAnnotations()));

        /**
         * Set the type's modifier flags to public and final.
         */
        if (struct || tuple)
        {
            type.setModifiers(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL);
        }
        else // design
        {
            type.setModifiers(Opcodes.ACC_PUBLIC | Opcodes.ACC_ABSTRACT | Opcodes.ACC_INTERFACE);
        }

        /**
         * Set the superclass of the tuple's type.
         */
        if (struct || tuple)
        {
            type.setSuperclass(program.typesystem.utils.ABSTRACT_RECORD);
        }
        else // design
        {
            type.setSuperclass(program.typesystem.utils.OBJECT);
        }

        /**
         * These are the types of designs that this record implements.
         */
        final List<IInterfaceType> superinterfaces = Lists.newLinkedList();

        /**
         * Every record-type is a subtype of the Record interface.
         */
        superinterfaces.add(program.typesystem.utils.RECORD);

        /**
         * Each user-specified supertype of a record is really a superinterface.
         */
        for (TypeSpecifier supertype : node.getSupers())
        {
            superinterfaces.add(module.imports.resolveInterfaceType(supertype));
        }

        /**
         * Cause the record's type to implement the inherited designs.
         */
        type.setSuperinterfaces(superinterfaces);

        /**
         * Add the types of the methods defined in the tuple to the tuple's type.
         */
        final List<IMethod> methods = Lists.newLinkedList();

        /**
         * Create the getter and setter methods for each element declared directly in the record.
         */
        for (Element entry : node.getElements().getElements())
        {
            final String element_name = entry.getName().getName();

            final IVariableType element_type = module.imports.resolveVariableType(entry.getType());

            /**
             * Remember the name of the element for later use.
             */
            keys.add(element_name);

            /**
             * Each element has an associated getter method and a setter method.
             */
            methods.add(typeofSetter(new SetterMethod(type, type, element_name, element_type)));
            methods.add(typeofGetter(new GetterMethod(type, element_type, element_name)));
        }

        /**
         * Now we have generated the types of the methods directly declared in the tuple.
         */
        type.setMethods(methods);

        /**
         * Now we need to generate special bridge methods.
         * Special bridge methods are for the methods defined in the Record interface.
         * The bridge methods for the setters and getters will not be generated by this invocation.
         */
        addTypesOfSpecialBridgeMethods();
    }

    /**
     * This method finishes type-initialization in order to avoid another compiler pass.
     */
    private void finishTypeInitialization()
    {
        final List<IMethod> methods = Lists.newLinkedList(type.getMethods());

        /**
         * Analyze the partially constructed record-type in order to detect inherited elements.
         */
        this.analyzer = new RecordAnalyzer2(type);

        /**
         * Due to inherited elements, more setters may need added to the record-type.
         */
        for (SetterMethod setter : analyzer.undeclaredSetters())
        {
            methods.add(typeofSetter(setter));
        }

        /**
         * Due to inherited elements, more getters may need added to the record-type.
         */
        for (GetterMethod getter : analyzer.undeclaredGetters())
        {
            methods.add(typeofGetter(getter));
        }

        /**
         * Add the type of the static instance() method.
         */
        final IMethod instance = typeofInstance();

        if (instance != null)
        {
            methods.add(instance);
        }

        /**
         * Now we have generated the types of the directly declared and inherited methods.
         */
        type.setMethods(methods);

        /**
         * Add the types of the bridge setters and bridge getters.
         */
        addTypesOfElementRelatedBridgeMethods();

        /**
         * Add all the inherited keys.
         */
        for (String key : analyzer.elements.keySet())
        {
            keys.add(key);
        }

        /**
         * Structs sort their keys lexicographically.
         */
        if (struct)
        {
            // Create a sorted set containing the keys in lexicographic order.
            final TreeSet<String> sorted = Sets.newTreeSet(keys);

            // Clear the original set of keys.
            keys.clear();

            // Replace the original unsorted keys with the sorted keys.
            keys.addAll(sorted);
        }

        /**
         * Add the type of the constructor defined in the tuple to the tuple's type.
         *
         * This cannot be done earlier, because they order of the keys is important.
         * So, we must wait until all the keys are available and sorted.
         */
        final IConstructor ctor = typeofCtor();

        if (ctor != null)
        {
            type.setConstructors(Lists.newArrayList(ctor));
        }
    }

    /**
     * This method creates the type-system representation of the method that sets a tuple element.
     *
     * @param setter is a description of the setter.
     * @return the aforedescribed method.
     */
    private IMethod typeofSetter(final SetterMethod setter)
    {
        final IAnnotation SETTER = module.anno_utils.typeOf(Setter.class);

        final CustomMethod method = new CustomMethod(program.typesystem.typefactory(), false);

        final CustomFormalParameter param = new CustomFormalParameter();
        param.setType(setter.parameter);

        method.setAnnotations(Lists.newArrayList(SETTER));
        method.setModifiers(Opcodes.ACC_PUBLIC);
        method.setName(setter.name);
        method.setOwner(setter.owner);
        method.setParameters(Collections.<IFormalParameter>singletonList(param));
        method.setReturnType(setter.returns);
        method.setThrowsClause(new LinkedList());

        return method;
    }

    /**
     * This method creates the type-system representation of the method that gets a tuple element.
     *
     * @param getter is a description of the getter.
     * @return the aforedescribed method.
     */
    private IMethod typeofGetter(final GetterMethod getter)
    {
        final IAnnotation GETTER = module.anno_utils.typeOf(Getter.class);

        final CustomMethod method = new CustomMethod(program.typesystem.typefactory(), false);

        method.setAnnotations(Lists.newArrayList(GETTER));
        method.setModifiers(Opcodes.ACC_PUBLIC);
        method.setName(getter.name);
        method.setOwner(type);
        method.setParameters(new LinkedList());
        method.setReturnType(getter.returns);
        method.setThrowsClause(new LinkedList());

        return method;
    }

    /**
     * This method creates the type-system representations of the special bridge methods.
     */
    private void addTypesOfSpecialBridgeMethods()
    {
        final IInterfaceType RECORD = program.typesystem.utils.RECORD;

        /**
         * Method: bind(SpecialMethods)
         */
        bridges.add(new BridgeMethod(type,
                                     type,
                                     TypeSystemUtils.find(RECORD.getAllVisibleMethods(),
                                                          "bind")));

        /**
         * Method: set(int, Object)
         */
        bridges.add(new BridgeMethod(type,
                                     type,
                                     TypeSystemUtils.find(RECORD.getAllVisibleMethods(),
                                                          "set",
                                                          "(ILjava/lang/Object;)" + RECORD.getDescriptor())));

        /**
         * Method: mutable()
         */
        bridges.add(new BridgeMethod(type,
                                     type,
                                     TypeSystemUtils.find(RECORD.getAllVisibleMethods(),
                                                          "mutable",
                                                          "()" + RECORD.getDescriptor())));

        /**
         * Method: immutable()
         */
        bridges.add(new BridgeMethod(type,
                                     type,
                                     TypeSystemUtils.find(RECORD.getAllVisibleMethods(),
                                                          "immutable",
                                                          "()" + RECORD.getDescriptor())));

        /**
         * Method: copy()
         */
        bridges.add(new BridgeMethod(type,
                                     type,
                                     TypeSystemUtils.find(RECORD.getAllVisibleMethods(),
                                                          "copy",
                                                          "()" + RECORD.getDescriptor())));
    }

    /**
     * This method creates the type-system representations of the element related bridge methods.
     */
    private void addTypesOfElementRelatedBridgeMethods()
    {
        for (RecordElement element : analyzer.elements.values())
        {
            for (SetterMethod setter : element.bridgeSetters())
            {
                final String target_desc = "(" + element.setter().parameter.getDescriptor() + ")" + element.setter().returns.getDescriptor();

                final IMethod target = TypeSystemUtils.find(type.getAllVisibleMethods(), element.name, target_desc);

                bridges.add(new BridgeMethod(type, setter.returns, target));
            }

            for (GetterMethod getter : element.bridgeGetters())
            {
                final String target_desc = "()" + element.getter().returns.getDescriptor();

                final IMethod target = TypeSystemUtils.find(type.getAllVisibleMethods(), element.name, target_desc);

                bridges.add(new BridgeMethod(type, getter.returns, target));
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void performTypeStructureChecking()
    {
        finishTypeInitialization();

        // TODO:
        // 1. No duplicate elements.
        // 2. Element types must be non-void and non-null.
        // 3. Covariance violations
        // 4. No duplicate direct superinterfaces

        /**
         * Detect and report any covariance violations.
         */
        for (CovarianceViolation2 violation : analyzer.violations)
        {
            //
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void performTypeUsageChecking()
    {
        // Pass
    }
}
