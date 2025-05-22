package high.mackenzie.autumn.lang.compiler.utils;

import autumn.lang.DefinedFunctor;
import com.google.common.base.Preconditions;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVariableType;

/**
 * An instance of this class is used to indicate why one functor-type cannot subtype another.
 *
 * @author Mackenzie High
 */
public final class FunctorSubtypingViolation
{
    public final IMethod lower;

    public final IMethod upper;

    /**
     * Sole Constructor.
     *
     * @param lower is the type of the invoke(*) method in the subclass.
     * @param upper is the type of the invoke(*) method in the superclass.
     */
    FunctorSubtypingViolation(final IMethod lower,
                              final IMethod upper)
    {
        Preconditions.checkNotNull(lower);
        Preconditions.checkNotNull(upper);

        assert lower.getOwner().isSubtypeOf(upper.getOwner());
        assert lower.getName().equals("invoke");
        assert upper.getName().equals("invoke");
        assert lower.getOwner().isSubtypeOf(lower.getOwner().getTypeFactory().fromClass(DefinedFunctor.class));
        assert upper.getOwner().isSubtypeOf(upper.getOwner().getTypeFactory().fromClass(DefinedFunctor.class));

        this.lower = lower;
        this.upper = upper;
    }

    /**
     * The lengths of the methods parameter-lists must be the same.
     *
     * @return true, if this violation was caused by differing numbers of parameters.
     */
    public boolean isParameterLengthViolation()
    {
        return lower.getParameters().size() != upper.getParameters().size();
    }

    /**
     * Each parameter in the lower method must be a subtype of the equivalent parameter
     * in the upper method.
     *
     * @return true, if this violation was caused by mismatched parameters.
     */
    public boolean isParameterTypeViolation()
    {
        for (int i = 0; i < lower.getParameters().size() && i < upper.getParameters().size(); i++)
        {
            final IVariableType param1 = lower.getParameters().get(i).getType();
            final IVariableType param2 = upper.getParameters().get(i).getType();

            if (param1.isSubtypeOf(param2) == false)
            {
                return true; // Violation Detected
            }
        }

        return false; // No Violation Detected
    }

    /**
     * The return-type of the lower method must be subtype of the upper method's return-type.
     *
     * @return true, if this violation is a result of a covariance violation.
     */
    public boolean isReturnTypeViolation()
    {
        return !lower.getReturnType().isSubtypeOf(upper.getReturnType());
    }
}
