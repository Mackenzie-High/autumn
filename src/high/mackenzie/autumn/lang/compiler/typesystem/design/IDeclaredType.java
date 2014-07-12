package high.mackenzie.autumn.lang.compiler.typesystem.design;

import high.mackenzie.autumn.resources.Finished;
import java.util.Collection;
import java.util.List;

/**
 * An instance of this interface is a type that can be placed in a class-file.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public interface IDeclaredType
        extends IElementType,
                IReferenceType,
                IAnnotatable
{
    /**
     * This method determines whether this type is already compiled.
     *
     * <p>
     * Types that are already compiled include types from shared libraries.
     * </p>
     *
     * @return true, if and only if, this type is already compiled.
     */
    public boolean isAlreadyCompiled();

    /**
     * This method returns the name of the package that this type is declared within.
     *
     * <p>
     * <b>Examples</b> <br>
     * <table border="1">
     * <tr> <td>
     * <code>getName()</code> </td> <td>
     * <code>getNamespace()</code> </td> </tr>
     * <tr> <td>
     * <code>java.lang.String</code> </td> <td>
     * <code>java.lang</code> </td> </tr>
     * <tr> <td>
     * <code>java.util.List</code> </td> <td>
     * <code>java.util</code> </td> </tr>
     * </table>
     * </p>
     *
     * @return the name of the package that this type is declared within.
     */
    public String getNamespace();

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<IAnnotation> getAnnotations();

    /**
     * This method returns the modifiers that are applied to this type.
     *
     * @return the modifiers that modify this type.
     * @see java.lang.reflect.Modifier
     */
    public int getModifiers();

    /**
     * This method returns the direct superclass of this type.
     *
     * <p>
     * If this type is
     * <code>java.lang.Object</code> then this method returns
     * <code>this</code>.
     * </p>
     *
     * @return the direct superclass of this type.
     */
    public IClassType getSuperclass();

    /**
     * This method returns all the superclasses of this type.
     *
     * <p>
     * The returned list is ordered from most-specific to most general.
     * </p>
     *
     * @return an immutable list containing all the superclasses of this type,
     * or an empty immutable list, if no superclasses are present.
     */
    public List<IClassType> getAllSuperclasses();

    /**
     * This method returns the direct superinterfaces of this type.
     *
     * @return an immutable collection containing the direct superinterfaces of this type,
     * or an empty immutable collection, if no superinterfaces are present.
     */
    public Collection<IInterfaceType> getSuperinterfaces();

    /**
     * This method returns all the superinterfaces of this type.
     *
     * @return an immutable collection containing all the superinterfaces of this type,
     * or an empty immutable collection, if no superinterfaces are present.
     */
    public Collection<IInterfaceType> getAllSuperinterfaces();

    /**
     * This method returns the fields that this type directly declares.
     *
     * <p>
     * If the type is an enumeration, the returned list includes the enum-constants in the enum.
     * </p>
     *
     * @return an immutable collection containing the directly declared fields in this type,
     * or an empty immutable collection, if no fields are present.
     */
    public Collection<IField> getFields();

    /**
     * This method returns the constructors that this type directly declares.
     *
     * @return an immutable collection containing the constructors that this type directly declares,
     * or an empty immutable collection, if no constructors are present.
     */
    public Collection<IConstructor> getConstructors();

    /**
     * This method returns the methods that this type directly declares.
     *
     * @return an immutable collection containing the directly declared methods in this type,
     * or an empty immutable collection, if no methods are present.
     */
    public Collection<IMethod> getMethods();

    /**
     * This method returns all the explicit and implicit methods that this type declares.
     *
     * <p>
     * Inherited method are included in the returned list.
     * Private methods are never inherited.
     * Static methods are never inherited.
     * </p>
     *
     * @return an immutable collection containing all the methods that are declared in this type.
     */
    public Collection<IMethod> getAllVisibleMethods();

    /**
     * This method determines whether this object is really the type of an annotation-definition.
     *
     * @return true, if and only if, this object is the type of an annotation-definition.
     */
    public boolean isAnnotationType();

    /**
     * This method determines whether this object is really the type of an class-definition.
     *
     * @return true, if and only if, this object is the type of an class-definition.
     */
    public boolean isClassType();

    /**
     * This method determines whether this object is really the type of an enum-definition.
     *
     * @return true, if and only if, this object is the type of an enum-definition.
     */
    public boolean isEnumType();

    /**
     * This method determines whether this object is really the type of an interface-definition.
     *
     * @return true, if and only if, this object is the type of an interface-definition.
     */
    public boolean isInterfaceType();

    /**
     * {@inheritDoc}
     */
    @Override
    public Class toClass();
}
