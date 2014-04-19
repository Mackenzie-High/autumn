/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.lang.compiler.typesystem;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IAnnotation;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IAnnotationType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IClassType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IConstructor;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IEnumType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IField;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IInterfaceType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.ITypeFactory;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author mackenzie
 */
public class CustomDeclaredType
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

    private List<IAnnotation> annotations = Lists.newLinkedList();

    private int modifiers = 0;

    private IClassType superclass;

    private List<IInterfaceType> superinterfaces = Lists.newLinkedList();

    private List<IField> fields = Lists.newLinkedList();

    private List<IConstructor> constructors = Lists.newLinkedList();

    private List<IMethod> methods = Lists.newLinkedList();

    static CustomDeclaredType newAnnotationType(final ITypeFactory factory,
                                                final String descriptor)
    {
        return new CustomDeclaredType(factory, SpecificType.ANNOTATION_TYPE, descriptor);
    }

    static CustomDeclaredType newClassType(final ITypeFactory factory,
                                           final String descriptor)
    {
        return new CustomDeclaredType(factory, SpecificType.CLASS_TYPE, descriptor);
    }

    static CustomDeclaredType newEnumType(final ITypeFactory factory,
                                          final String descriptor)
    {
        return new CustomDeclaredType(factory, SpecificType.ENUM_TYPE, descriptor);
    }

    static CustomDeclaredType newInterfaceType(final ITypeFactory factory,
                                               final String descriptor)
    {
        return new CustomDeclaredType(factory, SpecificType.INTERFACE_TYPE, descriptor);
    }

    private CustomDeclaredType(final ITypeFactory factory,
                               final SpecificType specific_type,
                               final String name)
    {
        super(factory, name);

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
    public Collection<IAnnotation> getAnnotations()
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
    public void setAnnotations(final List<IAnnotation> annotations)
    {
        this.annotations = annotations;
    }

    /**
     * Setter.
     *
     * @param constructors are the constructors declared directly within this type.
     */
    public void setConstructors(final List<IConstructor> constructors)
    {
        this.constructors = constructors;
    }

    /**
     * Setter.
     *
     * @param fields are the fields declared directly within this type.
     */
    public void setFields(final List<IField> fields)
    {
        this.fields = fields;
    }

    /**
     * Setter.
     *
     * @param methods are the methods declared directly within this type.
     */
    public void setMethods(final List<IMethod> methods)
    {
        this.methods = methods;
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
    public void setSuperinterfaces(final List<IInterfaceType> superinterfaces)
    {
        this.superinterfaces = superinterfaces;
    }

    @Override
    public boolean isPrimitiveType()
    {
        return false;
    }

    @Override
    public boolean isReferenceType()
    {
        return true;
    }

    @Override
    public boolean isNullType()
    {
        return false;
    }

    @Override
    public boolean isVoidType()
    {
        return false;
    }

    @Override
    public boolean isArrayType()
    {
        return false;
    }

    @Override
    public boolean isDeclaredType()
    {
        return true;
    }
}
