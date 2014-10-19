package high.mackenzie.autumn.lang.compiler.utils;

import autumn.lang.internals.annotations.Getter;
import autumn.lang.internals.annotations.Setter;
import com.google.common.base.Preconditions;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IDeclaredType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVariableType;

/**
 * An instance of this class describes an element in a record.
 *
 * @author Mackenzie High
 */
public final class RecordElement
{
    /**
     * This is the type of the element's owner.
     */
    public final IDeclaredType owner;

    /**
     * This is the name of the element.
     */
    public final String name;

    /**
     * This is the type of the value stored in the element.
     */
    public final IVariableType value;

    /**
     * Sole Constructor.
     *
     * @param owner is the type of the element's owner.
     * @param name is the element's name.
     * @param value is the element's static-type.
     */
    RecordElement(final IDeclaredType owner,
                  final String name,
                  final IVariableType value)
    {
        Preconditions.checkNotNull(owner);
        Preconditions.checkNotNull(name);
        Preconditions.checkNotNull(value);

        this.owner = owner;
        this.name = name;
        this.value = value;
    }

    /**
     * This method searches for a setter method within the record-type.
     *
     * @return the setter related to the element.
     */
    public IMethod setter()
    {
        return findMethod(Setter.class);
    }

    /**
     * This method searches for a getter method within the record-type.
     *
     * @return the getter related to the element.
     */
    public IMethod getter()
    {
        return findMethod(Getter.class);
    }

    /**
     * This method searches for a method within the record-type.
     *
     * @param annotation is applied to the method to find.
     * @return the getter or setter related to the element.
     */
    public IMethod findMethod(final Class annotation)
    {
        /**
         * Iterate over each method declared directly within the record-type.
         */
        for (IMethod method : owner.getMethods())
        {
            /**
             * Skip the method, if the name does not match.
             */
            if (name.equals(method.getName()) == false)
            {
                continue;
            }

            /**
             * Skip the method, if it is not a getter.
             * Remember, there is also a setter in the record-type.
             */
            if (TypeSystemUtils.isAnnotationPresent(method, annotation))
            {
                continue;
            }

            /**
             * The method was found, so return it.
             */
            return method;
        }

        throw new RuntimeException("This should never happen.");
    }
}
