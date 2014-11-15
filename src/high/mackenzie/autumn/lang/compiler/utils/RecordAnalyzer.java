package high.mackenzie.autumn.lang.compiler.utils;

import autumn.lang.Record;
import autumn.lang.internals.annotations.Getter;
import autumn.lang.internals.annotations.Setter;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import high.mackenzie.autumn.lang.compiler.typesystem.TypeFactory;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IDeclaredType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.ITypeFactory;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVariableType;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An instance of this class represents the basic bytecode representation of a record.
 *
 * @author Mackenzie High
 */
public final class RecordAnalyzer
{
    /**
     * This is the type of the record that is being compiled.
     */
    public final IDeclaredType type;

    /**
     * These are the elements in the new record.
     */
    public final Map<String, RecordElement> elements;

    /**
     * These are all the setter methods in the new record.
     */
    public final Set<SetterMethod> setters = Sets.newHashSet();

    /**
     * These are the all getter methods in the record.
     */
    public final Set<GetterMethod> getters = Sets.newHashSet();

    /**
     * These are the covariance violations detected in the record-type.
     */
    public final Set<CovarianceViolation> violations;

    /**
     * Sole Constructor.
     *
     * @param type is the type of the record that is being compiled.
     */
    public RecordAnalyzer(final IDeclaredType type)
    {
        Preconditions.checkNotNull(type);

        this.type = type;

        for (IMethod method : type.getAllVisibleMethods())
        {
            potentiallyAddSetter(method);
            potentiallyAddGetter(method);
        }

        this.violations = CovarianceViolationDetector.detect(this);

        addBaseSetters();
        addBaseGetters();

        this.elements = elements();
    }

    /**
     * This method adds a setter method to the set of setters, if it is really a setter method.
     *
     * @param method is the method that may be a setter method.
     */
    private void potentiallyAddSetter(final IMethod method)
    {
        /**
         * A setter always takes exactly one argument.
         */
        if (method.getParameters().size() != 1)
        {
            return;
        }

        /**
         * A setter has a special annotation applied to it.
         */
        if (TypeSystemUtils.isAnnotationPresent(method, autumn.lang.internals.annotations.Setter.class) == false)
        {
            return;
        }

        /**
         * Create an object that represents the setter.
         */
        final IDeclaredType returns = (IDeclaredType) method.getReturnType();
        final String name = method.getName();
        final IVariableType value = (IVariableType) method.getParameters().get(0).getType();
        final SetterMethod setter = new SetterMethod(method.getOwner(), returns, name, value);

        /**
         * Remember the setter for later use.
         */
        setters.add(setter);
    }

    /**
     * This method adds a setter method to the set of getters, if it is really a getter method.
     *
     * @param method is the method that may be a getter method.
     */
    private void potentiallyAddGetter(final IMethod method)
    {
        /**
         * If the method takes parameters, then it is not a getter.
         */
        if (method.getParameters().isEmpty() == false)
        {
            return;
        }

        /**
         * A getter has a special annotation applied to it.
         */
        if (TypeSystemUtils.isAnnotationPresent(method, autumn.lang.internals.annotations.Getter.class) == false)
        {
            return;
        }

        /**
         * Create an object that represents the getter.
         */
        final String name = method.getName();
        final IVariableType value = (IVariableType) method.getReturnType();
        final GetterMethod getter = new GetterMethod(method.getOwner(), value, name);

        /**
         * Remember the getter for later use.
         */
        getters.add(getter);
    }

    private void addBaseSetters()
    {
        final List<SetterMethod> initial = Lists.newArrayList(setters);

        for (SetterMethod setter : initial)
        {
            setters.add(new SetterMethod(type, type, setter.name, setter.parameter));

            setters.add(new SetterMethod(type, setter.returns, setter.name, setter.parameter));
        }
    }

    private void addBaseGetters()
    {
        final List<GetterMethod> initial = Lists.newArrayList(getters);

        for (GetterMethod getter : initial)
        {
            final GetterMethod method = new GetterMethod(type, getter.returns, getter.name);

            getters.add(method);
        }
    }

    /**
     * These are the setter methods that are needed in the record.
     *
     * @return an immutable set containing descriptions of the needed setters.
     */
    public Set<SetterMethod> setters()
    {
        final Set<SetterMethod> result = Sets.newHashSet();

        for (SetterMethod setter : setters)
        {
            if (setter.owner.equals(type))
            {
                result.add(setter);
            }
        }

        return Collections.unmodifiableSet(result);
    }

    /**
     * These are the getter methods that are needed in the record.
     *
     * @return an immutable set containing descriptions of the needed getters.
     */
    public Set<GetterMethod> getters()
    {
        final Set<GetterMethod> result = Sets.newHashSet();

        for (GetterMethod getter : getters)
        {
            if (getter.owner.equals(type))
            {
                result.add(getter);
            }
        }

        return Collections.unmodifiableSet(result);
    }

    /**
     * This method finds the names of the elements in the record-type.
     *
     * @return an immutable set containing the names of the elements in the record-type.
     */
    private Set<String> names()
    {
        final Set<String> names = Sets.newTreeSet();

        for (GetterMethod getter : getters)
        {
            names.add(getter.name);
        }

        return Collections.unmodifiableSet(names);
    }

    /**
     * This method creates a map that maps the name of a element to a description of the element.
     *
     * @return the aforedescribed immutable map.
     */
    public Map<String, RecordElement> elements()
    {
        final Map<String, RecordElement> map = Maps.newTreeMap();

        for (String name : names())
        {
            map.put(name, new RecordElement(this, name));
        }

        return Collections.unmodifiableMap(map);
    }

    public static interface Person
            extends Record
    {
        @Setter
        public Person name(Object name);

        @Getter
        public Object name();

        @Setter
        public Person age(int age);

        @Getter
        public int age();
    }

    public static interface Taxable
    {
        @Setter
        public Taxable name(String name);

        @Getter
        public String name();
    }

    public static interface Citizen
            extends Person,
                    Taxable
    {
        @Setter
        public Citizen name(String name);

        @Getter
        @Override
        public String name();

        @Setter
        public Citizen age(int age);

        @Getter
        @Override
        public int age();
    }

    public static interface M
    {
        @Setter
        public M x(final Object value);

        @Getter
        public Object x();
    }

    public static class S
            implements M
    {
        @Setter
        public S x(final String value)
        {
            return null;
        }

        @Setter
        @Override
        public S x(Object value)
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Getter
        @Override
        public String x()
        {
            return null;
        }
    }

    public static void main(final String[] args)
    {
        final ITypeFactory factory = new TypeFactory();

        final IDeclaredType type = (IDeclaredType) factory.fromClass(S.class);

        final RecordAnalyzer record = new RecordAnalyzer(type);

        final Set<String> set = Sets.newTreeSet();

        for (SetterMethod setter : record.setters())
        {
            set.add(setter.toString());
        }

        for (GetterMethod getter : record.getters())
        {
            set.add(getter.toString());
        }

        for (String x : set)
        {
            System.out.println(x);
        }

        System.out.println();

        for (CovarianceViolation violation : record.violations)
        {
            System.out.println(violation);
        }
    }
}
