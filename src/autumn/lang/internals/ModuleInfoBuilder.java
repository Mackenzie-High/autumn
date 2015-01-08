package autumn.lang.internals;

import autumn.lang.Delegate;
import autumn.lang.Memoizer;
import autumn.lang.Module;
import autumn.lang.ModuleInfo;
import autumn.lang.annotations.Start;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Instances of this class are used to construct ModuleInfo objects.
 *
 * @author Mackenzie High
 */
public final class ModuleInfoBuilder
{
    private final Module instance;

    private final ArrayList<Class> annotations = Lists.newArrayList();

    private final ArrayList<Class> exceptions = Lists.newArrayList();

    private final ArrayList<Class> enums = Lists.newArrayList();

    private final ArrayList<Class> designs = Lists.newArrayList();

    private final ArrayList<Class> structs = Lists.newArrayList();

    private final ArrayList<Class> tuples = Lists.newArrayList();

    private final ArrayList<Class> functors = Lists.newArrayList();

    private final ArrayList<Delegate> delegates = Lists.newArrayList();

    /**
     * Sole Constructor.
     *
     * @param instance is the module object itself.
     */
    public ModuleInfoBuilder(final Module instance)
    {
        Preconditions.checkNotNull(instance);

        this.instance = instance;
    }

    /**
     * This method declares that an annotation-type is defined directly within the module.
     *
     * @param type is the type.
     */
    public void addAnnotation(final Class type)
    {
        Preconditions.checkNotNull(type);

        annotations.add(type);
    }

    /**
     * This method declares that an exception-type is defined directly within the module.
     *
     * @param type is the type.
     */
    public void addException(final Class type)
    {
        Preconditions.checkNotNull(type);

        exceptions.add(type);
    }

    /**
     * This method declares that an enum-type is defined directly within the module.
     *
     * @param type is the type.
     */
    public void addEnum(final Class type)
    {
        Preconditions.checkNotNull(type);

        enums.add(type);
    }

    /**
     * This method declares that a design-type is defined directly within the module.
     *
     * @param type is the type.
     */
    public void addDesign(final Class type)
    {
        Preconditions.checkNotNull(type);

        designs.add(type);
    }

    /**
     * This method declares that a struct-type is defined directly within the module.
     *
     * @param type is the type.
     */
    public void addStruct(final Class type)
    {
        Preconditions.checkNotNull(type);

        structs.add(type);
    }

    /**
     * This method declares that a tuple-type is defined directly within the module.
     *
     * @param type is the type.
     */
    public void addTuple(final Class type)
    {
        Preconditions.checkNotNull(type);

        tuples.add(type);
    }

    /**
     * This method declares that a functor-type is defined directly within the module.
     *
     * @param type is the type.
     */
    public void addFunctor(final Class type)
    {
        Preconditions.checkNotNull(type);

        functors.add(type);
    }

    /**
     * This method declares that a function is defined directly within the module.
     *
     * @param delegate refers to the function.
     */
    public void add(final Delegate delegate)
    {
        Preconditions.checkNotNull(delegate);

        delegates.add(delegate);
    }

    /**
     * This method creates a ModuleInfo object based on the information that was provided to this builder.
     *
     * @return the newly created object.
     */
    public ModuleInfo build()
    {
        final ModuleInfoBuilder SELF = this;

        /**
         * TODO: This should be static in order to avoid holding onto the builder.
         */
        return new ModuleInfo()
        {
            private final List<Class> annotations = Collections.unmodifiableList(SELF.annotations);

            private final List<Class> exceptions = Collections.unmodifiableList(SELF.exceptions);

            private final List<Class> enums = Collections.unmodifiableList(SELF.enums);

            private final List<Class> designs = Collections.unmodifiableList(SELF.designs);

            private final List<Class> structs = Collections.unmodifiableList(SELF.structs);

            private final List<Class> tuples = Collections.unmodifiableList(SELF.tuples);

            private final List<Class> functors = Collections.unmodifiableList(SELF.functors);

            private final List<Delegate> delegates = Collections.unmodifiableList(SELF.delegates);

            private final Map<String, Memoizer> memoizers = Maps.newTreeMap();

            @Override
            public Module instance()
            {
                return instance;
            }

            @Override
            public String name()
            {
                return type().getName();
            }

            @Override
            public Class type()
            {
                return instance().getClass();
            }

            @Override
            public List<Class> annotations()
            {
                return annotations;
            }

            @Override
            public List<Class> enums()
            {
                return enums;
            }

            @Override
            public List<Class> exceptions()
            {
                return exceptions;
            }

            @Override
            public List<Class> designs()
            {
                return designs;
            }

            @Override
            public List<Class> tuples()
            {
                return tuples;
            }

            @Override
            public List<Class> structs()
            {
                return structs;
            }

            @Override
            public List<Class> functors()
            {
                return functors;
            }

            @Override
            public List<Delegate> functions()
            {
                return delegates;
            }

            @Override
            public boolean isStart()
            {
                for (Delegate x : functions())
                {
                    final boolean test1 = x.method().isAnnotationPresent(Start.class);

                    final boolean test2 = test1 && x.name().equals("main");

                    final boolean test3 = test1 && x.parameterTypes().equals(Lists.newArrayList(String[].class));

                    final boolean test4 = test1 && x.returnType().equals(void.class);

                    if (test1 && test2 && test3 && test4)
                    {
                        return true;
                    }
                }

                return false;
            }

            @Override
            public Set<Class> types()
            {
                final Set<Class> result = Sets.newIdentityHashSet();

                result.addAll(annotations());
                result.addAll(designs());
                result.addAll(enums());
                result.addAll(exceptions());
                result.addAll(functors());
                result.addAll(structs());
                result.addAll(tuples());

                return Collections.unmodifiableSet(result);
            }

            @Override
            public Memoizer memoizerOf(final String name)
            {
                // TODO: This is only a temporary implementation!!!!

                if (memoizers.containsKey(name) == false)
                {
                    memoizers.put(name, new Memoizer());
                }

                return memoizers.get(name);
            }

            @Override
            public String toString()
            {
                return name();
            }
        };
    }
}
