package autumn.lang;

import high.mackenzie.autumn.resources.Finished;

/**
 * An instance of this interfaces represents an entry in a record.
 *
 * @author Mackenzie High
 */
@Finished("2014/10/18")
public interface RecordEntry
{
    /**
     * This method retrieves the record that this entry is a part of.
     *
     * @return the underlying record.
     */
    public Record record();

    /**
     * This method retrieves the name of this entry.
     *
     * @return the name of this entry.
     */
    public String key();

    /**
     * This method retrieves the static-type of this entry.
     *
     * @return the static-type of this record entry.
     */
    public Class type();

    /**
     * This method gets the value currently stored in this entry.
     *
     * @return the current value of this record entry.
     */
    public Object value();

    /**
     * This method sets the value stored in this entry to the default value for its type.
     *
     * @return this object, if the underlying record is mutable;
     * otherwise, return a copy of this object whose underlying record
     * is a modified copy of the record that underlies this object.
     */
    public Record clear();

    /**
     * This method sets the value stored in this entry.
     *
     * <p>
     * Auto-unboxing will be performed, if necessary.
     * However, the unboxed value will not be auto-widened.
     * </p>
     *
     * @param value is the new value of this record entry.
     * @return this object, if the underlying record is mutable;
     * otherwise, return a copy of this object whose underlying record
     * is a modified copy of the record that underlies this object.
     * @throws ClassCastException if the object is not of an acceptable type.
     */
    public Record set(Object value);

    /**
     * This method creates a string representation of this object.
     *
     * @return the string representation of the value returned by value().
     */
    @Override
    public String toString();
}
