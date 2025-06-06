package com.mackenziehigh.autumn.lang.compiler.utils;

import com.google.common.base.Preconditions;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IDeclaredType;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IMethod;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IVariableType;

/**
 * An instance of this class represents a bytecode setter method in a record.
 *
 * @author Mackenzie High
 */
public final class SetterMethod
{
    /**
     * This is the type that declares the getter.
     */
    public final IDeclaredType owner;

    /**
     * This is the type of the type of record returned by the setter method.
     */
    public final IDeclaredType returns;

    /**
     * This is the name of the element that the setter sets.
     */
    public final String name;

    /**
     * This is the type of value that the setter accepts as an argument.
     */
    public final IVariableType parameter;

    /**
     * Constructor.
     *
     * @param owner is the type that declares the setter.
     * @param returns is the return-type of the setter.
     * @param name is the name of the setter.
     * @param parameter is the type of the setter's only formal-parameter.
     */
    public SetterMethod(final IDeclaredType owner,
                        final IDeclaredType returns,
                        final String name,
                        final IVariableType parameter)
    {
        Preconditions.checkNotNull(owner);
        Preconditions.checkNotNull(returns);
        Preconditions.checkNotNull(name);
        Preconditions.checkNotNull(parameter);

        this.owner = owner;
        this.returns = returns;
        this.name = name;
        this.parameter = parameter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 23 * hash + (this.owner != null ? this.owner.hashCode() : 0);
        hash = 23 * hash + (this.returns != null ? this.returns.hashCode() : 0);
        hash = 23 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 23 * hash + (this.parameter != null ? this.parameter.hashCode() : 0);
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
        final SetterMethod other = (SetterMethod) obj;
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
        if (this.parameter != other.parameter && (this.parameter == null || !this.parameter.equals(other.parameter)))
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
        return "setter " + Utils.simpleName(owner) + "." + name + "(" + Utils.simpleName(parameter) + ") : " + Utils.simpleName(returns);
    }

    /**
     * This method determines whether this setter method is declared in its owner.
     *
     * @return true, iff the owner currently directly declares this setter method.
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
            if (method.getParameters().size() != 1)
            {
                continue;
            }

            /**
             * If the parameter-type is different, continue.
             */
            if (method.getParameters().get(0).getType().equals(parameter) == false)
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
             * we have found a method declaration that describes the setter method.
             */
            return true;
        }

        /**
         * None of the method declarations in the owner describe the setter method.
         */
        return false;
    }

    /**
     * If this setter is a bridge-method, this method determines which setter to invoke.
     *
     * @return setter method that this setter method invokes at runtime.
     */
    public IMethod findBridgeTarget()
    {
        /**
         * If this setter method is the target, it should cannot target itself.
         */
        assert returns.equals(owner) == false;

        IMethod result = null;

        final IMethod self = this.findSelf();

        for (IMethod method : owner.getMethods())
        {
            if (method == self)
            {
                continue;
            }

            final boolean match1 = method.getName().equals(name);

            final boolean match2 = method.getReturnType().equals(owner);

            final boolean match3 = method.getParameters().size() == 1;

            final boolean matches = match1 && match2 && match3 && match3;

            if (matches && result == null)
            {
                result = method;
                continue;
            }

            if (matches == false || result == null)
            {
                continue;
            }

            final IVariableType other_parameter = method.getParameters().get(0).getType();

            final IVariableType result_parameter = result.getParameters().get(0).getType();

            final boolean subtype = other_parameter.isSubtypeOf(result_parameter);

            final boolean equals = other_parameter.equals(result_parameter);

            final boolean proper_subtype = subtype && !equals;

            /**
             * If the method is more specific than the previous result, use the new result.
             */
            if (proper_subtype)
            {
                result = method;
            }
        }

        /**
         * This method should only be invoked, when the result is known to exist.
         */
        assert result != null;

        return result;
    }

    /**
     * This method retrieves the type-system representation of this method.
     *
     * @return the member of the owner-type that represents this setter method.
     */
    public IMethod findSelf()
    {
        IMethod result = null;

        for (IMethod method : owner.getMethods())
        {
            final boolean match1 = method.getName().equals(name);

            final boolean match2 = method.getReturnType().equals(returns);

            final boolean match3 = method.getParameters().size() == 1;

            final boolean match4 = match3 && method.getParameters().get(0).getType().equals(parameter);

            if (match1 && match2 && match3 && match4)
            {
                result = method;
                break;
            }
        }

        /**
         * This method should only be invoked, when the result is known to exist.
         */
        assert result != null;

        return result;
    }
}
