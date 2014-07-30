package autumn.lang.internals.proto;

import autumn.lang.Functor;
import autumn.lang.Method;
import autumn.lang.Prototype;
import autumn.lang.internals.ArgumentStack;
import com.google.common.base.Preconditions;
import java.util.List;

/**
 * This class provides a concrete implementation of the Method interface.
 *
 * @author Mackenzie High
 */
final class ConcreteMethod
        implements Method
{
    private final AbstractPrototype owner;

    private final int index;

    private final LowLevelMethod low;

    /**
     * Sole Constructor.
     *
     * @param owner is the prototype that contains the method.
     * @param index is the index of the method in the prototype.
     * @param low is the low-level representation of the method.
     */
    public ConcreteMethod(final AbstractPrototype owner,
                          final int index,
                          final LowLevelMethod low)
    {
        Preconditions.checkNotNull(owner);
        Preconditions.checkNotNull(low);
        Preconditions.checkArgument(index == low.meta.index); // Sanity Check

        this.owner = owner;
        this.index = index;
        this.low = low;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int index()
    {
        return index;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Prototype owner()
    {
        return owner;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name()
    {
        return low.meta.name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Class> parameterTypes()
    {
        return low.meta.formals;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class returnType()
    {
        return low.meta.returns;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Method setHandler(final Functor handler)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Functor getHandler()
    {
        return low.handler;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isProperty()
    {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMethod()
    {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void apply(ArgumentStack stack)
            throws Throwable
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String toString()
    {
        final StringBuilder string = new StringBuilder();

        string.append("method ");

        string.append(this.name());

        string.append(" ");

        string.append('(');
        {
            int i = 0;

            int size = this.parameterTypes().size();

            for (Class param : this.parameterTypes())
            {
                string.append(param.getSimpleName());

                if (i++ < size - 1)
                {
                    string.append(", ");
                }
            }
        }
        string.append(')');

        string.append(" : ");

        string.append(this.returnType().getSimpleName());

        return string.toString();
    }
}
