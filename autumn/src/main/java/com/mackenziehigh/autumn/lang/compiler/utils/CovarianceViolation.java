package com.mackenziehigh.autumn.lang.compiler.utils;

import com.google.common.base.Preconditions;

/**
 * An instance of this class describes a covariance-violation in a record-definition.
 *
 * <p>
 * Example Violation:
 * <ul>
 * <li>Let R be a design.</li>
 * <li>Let S be a struct.</li>
 * <li>S is a subtype of R.</li>
 * <li>R defines an element E of type String.</li>
 * <li>S defines an element E of type Object.</li>
 * </ul>
 * <br>
 * This is a covariance-violation. because S->E is not a subtype of R->E.
 * </p>
 *
 * <p>
 * In the previous example, R is the upper type and S is the lower type.
 * </p>
 *
 * @author Mackenzie High
 */
public final class CovarianceViolation
{
    /**
     * This is the setter method in the supertype of the lower type.
     */
    public final SetterMethod upper;

    /**
     * This is the setter method in the lower type.
     */
    public final SetterMethod lower;

    /**
     * Sole Constructor.
     *
     * @param lower
     * @param upper
     */
    CovarianceViolation(final SetterMethod lower,
                         final SetterMethod upper)
    {
        Preconditions.checkNotNull(upper);
        Preconditions.checkNotNull(lower);

        this.upper = upper;
        this.lower = lower;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 89 * hash + (this.upper != null ? this.upper.hashCode() : 0);
        hash = 89 * hash + (this.lower != null ? this.lower.hashCode() : 0);
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
        final CovarianceViolation other = (CovarianceViolation) obj;
        if (this.upper != other.upper && (this.upper == null || !this.upper.equals(other.upper)))
        {
            return false;
        }
        if (this.lower != other.lower && (this.lower == null || !this.lower.equals(other.lower)))
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
        return lower + " => " + upper;
    }
}
