package high.mackenzie.autumn.lang.compiler.typesystem;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IAnnotation;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IAnnotationType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IClassType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IConstructor;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IEnumType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IField;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IInterfaceType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.ITypeFactory;
import high.mackenzie.autumn.resources.Finished;
import java.util.Collection;
import java.util.List;

/**
 * An instance of this class represents a declared type.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public final class CustomDeclaredType
        extends AbstractDeclaredType
        implements IAnnotationType,
                   IClassType,
                   IEnumType,
                   IInterfaceType
{
    private static enum SpecificType
    {
        ANNOTATION_TYPE,
        CLASS_TYPE,
        ENUM_TYPE,
        INTERFACE_TYPE
    }

    private final SpecificType specific_type;

    private ImmutableList<IAnnotation> annotations = ImmutableList.of();

    private int modifiers = 0;

    private IClassType superclass;

    private ImmutableList<IInterfaceType> superinterfaces = ImmutableList.of();

    private ImmutableList<IField> fields = ImmutableList.of();

    private ImmutableList<IConstructor> constructors = ImmutableList.of();

    private ImmutableList<IMethod> methods = ImmutableList.of();

    /**
     * This method creates a new annotation-type.
     *
     * @param factory type-factory that is used to access types.
     * @param descriptor is the type-descriptor of the new type.
     * @return a new annotation-type.
     */
    static CustomDeclaredType newAnnotationType(final ITypeFactory factory,
                                                final String descriptor)
    {
        return new CustomDeclaredType(factory, SpecificType.ANNOTATION_TYPE, descriptor);
    }

    /**
     * This method creates a new class-type.
     *
     * @param factory type-factory that is used to access types.
     * @param descriptor is the type-descriptor of the new type.
     * @return a new class-type.
     */
    static CustomDeclaredType newClassType(final ITypeFactory factory,
                                           final String descriptor)
    {
        return new CustomDeclaredType(factory, SpecificType.CLASS_TYPE, descriptor);
    }

    /**
     * This method creates a new enum-type.
     *
     * @param factory type-factory that is used to access types.
     * @param descriptor is the type-descriptor of the new type.
     * @return a new enum-type.
     */
    static CustomDeclaredType newEnumType(final ITypeFactory factory,
                                          final String descriptor)
    {
        return new CustomDeclaredType(factory, SpecificType.ENUM_TYPE, descriptor);
    }

    /**
     * This method creates a new interface-type.
     *
     * @param factory type-factory that is used to access types.
     * @param descriptor is the type-descriptor of the new type.
     * @return a new interface-type.
     */
    static CustomDeclaredType newInterfaceType(final ITypeFactory factory,
                                               final String descriptor)
    {
        return new CustomDeclaredType(factory, SpecificType.INTERFACE_TYPE, descriptor);
    }

    /**
     * Sole Constructor.
     *
     * @param factory is the type-factory that is used to access types.
     * @param specific_type is the type of type being created.
     * @param descriptor of the new type.
     */
    private CustomDeclaredType(final ITypeFactory factory,
                               final SpecificType specific_type,
                               final String descriptor)
    {
        super(factory, descriptor);

        this.specific_type = specific_type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAlreadyCompiled()
    {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IAnnotation> getAnnotations()
    {
        return ImmutableList.copyOf(annotations);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getModifiers()
    {
        return modifiers;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IClassType getSuperclass()
    {
        return superclass;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<IInterfaceType> getSuperinterfaces()
    {
        return ImmutableList.copyOf(superinterfaces);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<IField> getFields()
    {
        return ImmutableList.copyOf(fields);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<IConstructor> getConstructors()
    {
        return ImmutableList.copyOf(constructors);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<IMethod> getMethods()
    {
        return ImmutableList.copyOf(methods);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAnnotationType()
    {
        return specific_type == SpecificType.ANNOTATION_TYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isClassType()
    {
        return specific_type == SpecificType.CLASS_TYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEnumType()
    {
        return specific_type == SpecificType.ENUM_TYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isInterfaceType()
    {
        return specific_type == SpecificType.INTERFACE_TYPE;
    }

    /**
     * {@inheritDoc}
     *
     * @return null always.
     */
    @Override
    public Class toClass()
    {
        return null;
    }

    /**
     * Setter.
     *
     * @param annotations are the annotations to directly apply to this type.
     */
    public void setAnnotations(final Collection<IAnnotation> annotations)
    {
        this.annotations = ImmutableList.copyOf(annotations);
    }

    /**
     * Setter.
     *
     * @param constructors are the constructors declared directly within this type.
     */
    public void setConstructors(final Collection<IConstructor> constructors)
    {
        this.constructors = ImmutableList.copyOf(constructors);
    }

    /**
     * Setter.
     *
     * @param fields are the fields declared directly within this type.
     */
    public void setFields(final Collection<IField> fields)
    {
        this.fields = ImmutableList.copyOf(fields);
    }

    /**
     * Setter.
     *
     * @param methods are the methods declared directly within this type.
     */
    public void setMethods(final Collection<IMethod> methods)
    {
        this.methods = ImmutableList.copyOf(methods);
    }

    /**
     * Setter.
     *
     * @param modifiers are the modifiers applied to this type.
     */
    public void setModifiers(final int modifiers)
    {
        this.modifiers = modifiers;
    }

    /**
     * Setter.
     *
     * @param superclass is the superclass of this type.
     */
    public void setSuperclass(final IClassType superclass)
    {
        Preconditions.checkNotNull(superclass);

        this.superclass = superclass;
    }

    /**
     * Setter.
     *
     * @param superinterfaces are the direct superinterfaces of this type.
     */
    public void setSuperinterfaces(final Collection<IInterfaceType> superinterfaces)
    {
        this.superinterfaces = ImmutableList.copyOf(superinterfaces);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPrimitiveType()
    {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isReferenceType()
    {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isNullType()
    {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isVoidType()
    {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isArrayType()
    {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDeclaredType()
    {
        return true;
    }
}
