package autumn.lang;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * An instance of this interface is a composite data-type.
 *
 * @author Mackenzie
 */
public interface Struct
{
    public interface Property
    {
        /**
         * This method retrieves the underlying structure that this property is a member of.
         *
         * @return the property's owner.
         */
        public Struct struct();

        /**
         * This method retrieves the name of this property.
         *
         * @return this property's name.
         */
        public String name();

        /**
         * This method retrieves the type of this property.
         *
         * @return this property's type.
         */
        public Class type();

        /**
         * This method gets the value stored in this property.
         *
         * @return
         */
        public Object get();

        public Property set(Object value);

        public boolean isSet();

        public boolean isMutable();

        public Functor getSetter();

        public Functor setSetter(Functor functor);

        public Functor getGetter();

        public Functor setGetter(Functor functor);
    }

    /**
     * This method creates a shallow copy of this object.
     *
     * @return a copy of this struct.
     */
    public Struct copy();

    public Struct set(String key,
                      Object value);

    public Struct setAll(Map<? extends String, ? extends Object> map);

    public Struct setAll(Struct other);

    public Object get(Object key);

    public Property find(String key);

    /**
     * This method sets each property to its default value and marks it as unset.
     *
     * <p>
     * This method does not remove setters and getters.
     * </p>
     *
     * <p>
     * If this structure contains mutable properties,
     * then the changes will be reflected in both the original and modified objects.
     * </p>
     *
     * @return the modified version of this structure.
     */
    public Struct clear();

    /**
     * This method returns the number of fields in the structure.
     *
     * @return the number of fields.
     */
    public int size();

    /**
     * {@inheritDoc}
     */
    public boolean isEmpty();

    /**
     * This method determines whether this structure contains a property with a given name.
     *
     * @param key is the name of the possible property.
     * @return true, iff a property in this structure has the given name.
     */
    public boolean containsKey(Object key);

    /**
     * This method determines whether this structure contains a given value.
     *
     * @param value is the value that may be contained herein.
     * @return true, iff this structure contains the given value.
     */
    public boolean containsValue(Object value);

    /**
     * This method returns a set containing the names of the properties in this structure.
     *
     * @return the aforesaid immutable set.
     */
    public Set<String> keySet();

    /**
     * This method returns a set containing the values of the properties in this structure.
     *
     * <p>
     * The returned set is a view of this structure.
     * Thus, changes to this structure will be reflected in the set.
     * Furthermore, the returned set cannot be modified.
     * </p>
     *
     * <p>
     * The values in the returned set are the outputs of the struct's getters.
     * </p>
     *
     * @return the aforesaid set.
     */
    public Collection<Object> values();

    /**
     * This method returns a set containing views of the properties in this structure.
     *
     * @return the aforesaid immutable set.
     */
    public Set<Property> properties();

    /**
     * This method returns a map view of this struct.
     *
     * @return a view of this struct.
     */
    public Map<String, Object> mapView();

    /**
     * This method creates a map from this struct.
     *
     * <p>
     * The returned struct is *not* backed by this struct.
     * Thus, changes to this struct will not be reflected in the map.
     * </p>
     *
     * @return an immutable map representation of this struct.
     */
    public Map<String, Object> map();

    @Override
    public boolean equals(Object other);

    @Override
    public int hashCode();

    @Override
    public String toString();
}
