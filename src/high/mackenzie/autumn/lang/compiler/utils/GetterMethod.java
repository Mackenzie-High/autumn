package high.mackenzie.autumn.lang.compiler.utils;

import com.google.common.base.Preconditions;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IDeclaredType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVariableType;

/**
 * An instance of this class represents a bytecode getter method.
 *
 * @author Mackenzie High
 */
public final class GetterMethod
{
    /**
     * This is the type that declares the getter.
     */
    public final IDeclaredType owner;

    /**
     * This is the type of value that the getter returns.
     */
    public final IVariableType returns;

    /**
     * This is the name of the element that the getter gets.
     */
    public final String name;

    /**
     * Sole Constructor.
     *
     * @param owner is the type that declares the getter.
     * @param returns is the return-type of the getter.
     * @param name is the name of the getter.
     */
    public GetterMethod(final IDeclaredType owner,
                        final IVariableType returns,
                        final String name)
    {
        Preconditions.checkNotNull(owner);
        Preconditions.checkNotNull(returns);
        Preconditions.checkNotNull(name);

        this.owner = owner;
        this.returns = returns;
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 97 * hash + (this.owner != null ? this.owner.hashCode() : 0);
        hash = 97 * hash + (this.returns != null ? this.returns.hashCode() : 0);
        hash = 97 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final GetterMethod other = (GetterMethod) obj;
        if (this.owner != other.owner && (this.owner == null || !this.owner.equals(other.owner)))
        {
            return false;
        }
        if (this.returns != other.returns && (this.returns == null || !this.returns.equals(other.returns)))
        {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name))
        {
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        return "getter " + Utils.simpleName(owner) + "." + name + "() = " + Utils.simpleName(returns);
    }

    /**
     * This method determines whether this getter method is declared in its owner.
     *
     * @return true, iff the owner currently directly declares this getter method.
     */
    public boolean isDeclared()
    {
        for (IMethod method : owner.getMethods())
        {
            /**
             * If the name is different, continue.
             */
            if (method.getName().equals(name) == false)
            {
                continue;
            }

            /**
             * If the number of parameters is different, continue.
             */
            if (method.getParameters().isEmpty() == false)
            {
                continue;
            }

            /**
             * If the return-type is different, continue.
             */
            if (method.getReturnType().equals(returns) == false)
            {
                continue;
            }

            /**
             * Since the owner, name, parameter-list, and return-type match,
             * we have found a method declaration that describes the getter method.
             */
            return true;
        }

        /**
         * None of the method declarations in the owner describe the getter method.
         */
        return false;
    }
}
