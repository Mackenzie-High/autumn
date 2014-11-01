package high.mackenzie.autumn.lang.compiler.utils;

import com.google.common.collect.Sets;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVariableType;
import java.util.Collections;
import java.util.Set;

/**
 * An instance of this class represents an element in a record-type.
 *
 * @author Mackenzie High
 */
public final class RecordElement
{
    /**
     * This is a description of the record that contains the element.
     */
    public final RecordAnalyzer2 record;

    /**
     * This is the name of the element.
     */
    public final String name;

    /**
     * This is a cache for type() in order to improve performance.
     */
    private IVariableType memoized_type;

    /**
     * This is a cache for setter() in order to improve performance.
     */
    private SetterMethod memoized_setter;

    /**
     * This is a cache for getter() in order to improve performance.
     */
    private GetterMethod memoized_getter;

    /**
     * Sole Constructor.
     *
     * @param record is a description of the record-type.
     * @param name is the name of the element.
     */
    public RecordElement(final RecordAnalyzer2 record,
                         final String name)
    {
        this.record = record;
        this.name = name;
    }

    /**
     * This method determines the most specific type of the element.
     *
     * <p>
     * This will become the type of the field that actually stores the element's value.
     * </p>
     *
     * @return the type of the field.
     */
    public IVariableType type()
    {
        /**
         * If this method was already invoked, then return the memoized result.
         */
        if (memoized_type != null)
        {
            return memoized_type;
        }

        /**
         * Search for the return-type of the most specific getter.
         */
        for (GetterMethod getter : record.getters)
        {
            /**
             * If the getter is for a different element, skip it.
             */
            if (getter.name.equals(name) == false)
            {
                continue;
            }

            /**
             * If we have no idea what the type is supposed to be, use the first possible type.
             */
            memoized_type = memoized_type == null ? getter.returns : memoized_type;

            /**
             * If this getter returns a more specific type, then use its return-type.
             */
            memoized_type = getter.returns.isSubtypeOf(memoized_type) ? getter.returns : memoized_type;
        }

        /**
         * Return the return-type of the most specific getter method.
         */
        return memoized_type;
    }

    /**
     * This method determines which setter method is the most specific.
     *
     * <p>
     * This setter method is the one that bridge methods will invoke.
     * </p>
     *
     * @return a description of the primary setter method.
     */
    public SetterMethod setter()
    {
        /**
         * If this method was already invoked, then return the memoized result.
         */
        if (memoized_setter != null)
        {
            return memoized_setter;
        }

        /**
         * Search for the primary setter.
         */
        for (SetterMethod setter : record.setters)
        {
            /**
             * The primary setter must share its name with this element.
             */
            if (setter.name.equals(name) == false)
            {
                continue;
            }

            /**
             * The primary setter must be a direct member of the record.
             */
            if (setter.owner.equals(record.type) == false)
            {
                continue;
            }

            /**
             * The return-type of the primary setter must be the type of the record.
             */
            if (setter.returns.equals(record.type) == false)
            {
                continue;
            }

            /**
             * The primary setter must take the most specific type of this element as its argument.
             */
            if (type().equals(setter.parameter) == false)
            {
                continue;
            }

            /**
             * The primary setter has been found.
             */
            memoized_setter = setter;
            break;
        }

        assert memoized_setter != null;

        return memoized_setter;
    }

    /**
     * This method determines which getter method is the most specific.
     *
     * <p>
     * This setter method is the one that bridge methods will invoke.
     * </p>
     *
     * @return a description of the primary getter method.
     */
    public GetterMethod getter()
    {
        /**
         * If this method was already invoked, then return the memoized result.
         */
        if (memoized_getter != null)
        {
            return memoized_getter;
        }

        /**
         * Search for the primary setter.
         */
        for (GetterMethod getter : record.getters)
        {
            /**
             * The primary getter must share its name with this element.
             */
            if (getter.name.equals(name) == false)
            {
                continue;
            }

            /**
             * The primary getter must be a direct member of the record.
             */
            if (getter.owner.equals(record.type) == false)
            {
                continue;
            }

            /**
             * The return-type of the primary getter must be most specific type of the element.
             */
            if (type().equals(getter.returns) == false)
            {
                continue;
            }

            /**
             * The primary getter has been found.
             */
            memoized_getter = getter;
            break;
        }

        assert memoized_getter != null;

        return memoized_getter;
    }

    /**
     * This method finds the setter methods that simply invoke the primary setter method.
     *
     * @return an immutable set containing the bridge setters.
     */
    public Set<SetterMethod> bridgeSetters()
    {
        final Set<SetterMethod> bridges = Sets.newHashSet();

        for (SetterMethod setter : record.setters)
        {
            final boolean inheritance = !setter.returns.equals(record.type);

            final boolean covariance = setter.returns.equals(record.type)
                                       && !setter().parameter.equals(setter.parameter);

            if (setter.owner.equals(record.type) && setter.name.equals(name) && (inheritance || covariance))
            {
                bridges.add(setter);
            }
        }

        return Collections.unmodifiableSet(bridges);
    }

    /**
     * This method finds the getter methods that simply invoke the primary getter method.
     *
     * @return an immutable set containing the bridge getters.
     */
    public Set<GetterMethod> bridgeGetters()
    {
        final Set<GetterMethod> bridges = Sets.newHashSet();

        for (GetterMethod getter : record.getters)
        {
            if (getter.owner.equals(record.type) && getter.name.equals(name) && !getter.returns.equals(getter().returns))
            {
                bridges.add(getter);
            }
        }

        return Collections.unmodifiableSet(bridges);
    }
}
