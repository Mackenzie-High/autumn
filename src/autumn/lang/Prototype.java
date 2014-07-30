package autumn.lang;

import java.util.List;

/**
 * This interface is a supertype of all objects that are instantiated Autumn designs.
 *
 * <p>
 * Prototype objects are immutable.
 * As a result, methods that mutate a prototype actually return a modified copy of the prototype.
 * </p>
 *
 * @author Mackenzie High
 */
public interface Prototype
{
    /**
     * This method creates a shallow copy of this object and then returns the copy.
     *
     * @return a shallow copy of this object.
     */
    public Prototype copy();        // Create a modified version of the low-level property.
//        final AbstractLowLevelProperty property = low.setFieldValue(value);
//
//        // Create a copy of the owner that contains the modified low-level property.
//        final AbstractPrototype prototype = Actions.replaceSlot(owner, low.meta.index, property);
//
//        // Create a copy of this object that reflects the changes.
//        final ConcreteProperty result = new ConcreteProperty(prototype, low.meta.index, property);
//
//        return result;

    /**
     * This method retrieves a list of all the properties and methods in this object.
     *
     * <p>
     * The returned list lazily creates its elements.
     * </p>
     *
     * @return an immutable list containing the properties and methods in the object.
     */
    public List<Member> members();

    @Override
    public boolean equals(Object o);

    @Override
    public int hashCode();

    @Override
    public String toString();
}
