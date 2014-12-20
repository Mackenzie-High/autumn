package autumn.lang.internals;

import autumn.lang.Module;
import autumn.util.Strings;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import high.mackenzie.autumn.resources.Finished;
import java.util.List;

/**
 * An instance of this class is a delegate that refers to a function within a module.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public final class ModuleDelegate
        extends AbstractDelegate
{
    /**
     * This is an instance of the module that contains the referenced function.
     */
    private final Module owner;

    /**
     * This is the name of the referenced function.
     */
    private final String name;

    /**
     * These are the types of the referenced function's formal parameters.
     */
    private final ImmutableList<Class> parameters;

    /**
     * This is the return-type of the referenced function.
     */
    private final Class returns;

    /**
     * This is the index of the referenced function in its enclosing method.
     */
    private final int index;

    /**
     * Sole Constructor.
     *
     * @param owner is the module that contains the referenced function.
     * @param name is the name of the referenced function.
     * @param parameters are the types of the function's formal parameters.
     * @param returns is the function's return-type.
     * @param index is the index of the function within the module.
     */
    public ModuleDelegate(final Module owner,
                          final String name,
                          final List<Class> parameters,
                          final Class returns,
                          final int index)
    {
        // Warning: Do not reorder the parameters.
        //          Because, This constructor is invoked from bytecode.

        Preconditions.checkNotNull(owner);
        Preconditions.checkNotNull(name);
        Preconditions.checkNotNull(parameters);
        Preconditions.checkArgument(parameters.contains(null) == false);
        Preconditions.checkNotNull(returns);
        Preconditions.checkArgument(index >= 0);

        this.owner = owner;
        this.name = name;
        this.parameters = ImmutableList.copyOf(parameters);
        this.returns = returns;
        this.index = index;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Module module()
    {
        return owner;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class owner()
    {
        return owner.getClass();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name()
    {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class returnType()
    {
        return returns;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Class> parameterTypes()
    {
        return parameters;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void apply(ArgumentStack stack)
            throws Throwable
    {
        owner.moduleInvokeFunction(index, stack);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        final StringBuilder result = new StringBuilder();

        /**
         * Determine the simple-names of the parameters.
         */
        final List<String> list = Lists.newLinkedList();

        for (Class parameter : parameterTypes())
        {
            list.add(parameter.getSimpleName());
        }

        /**
         * Create the desired string representation of his delegate.
         */
        result.append(owner().getSimpleName());
        result.append("::");
        result.append(name);
        result.append(" ");
        result.append(Strings.str(list, "(", ", ", ")"));
        result.append(" : ");
        result.append(returnType().getSimpleName());

        return result.toString();
    }
}
