package high.mackenzie.autumn.lang.compiler.utils;

import com.google.common.collect.Sets;
import java.util.Collections;
import java.util.Set;

/**
 * This class can be used to detect covariance violations in a record-type.
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
 * @author Mackenzie High
 */
public final class CovarianceViolationDetector
{
    /**
     * This method detects the covariance-violations in a given record-type.
     *
     * @param record is a representation of the record-type.
     * @return an immutable set containing descriptions of the violation.
     */
    public static Set<CovarianceViolation2> detect(final RecordAnalyzer2 record)
    {
        final Set<CovarianceViolation2> violations = Sets.newHashSet();

        for (SetterMethod setter1 : record.setters())
        {
            for (SetterMethod setter2 : record.setters())
            {
                final CovarianceViolation2 violation = detect(setter1, setter2);

                if (violation != null)
                {
                    violations.add(violation);
                }
            }
        }

        return Collections.unmodifiableSet(violations);
    }

    /**
     * This method determines whether two setter methods cause a covariance violation.
     *
     * @param setter1 is the description of a setter method.
     * @param setter2 is the description of a setter method.
     * @return a non-null value, iff a violation exists.
     */
    private static CovarianceViolation2 detect(final SetterMethod setter1,
                                               final SetterMethod setter2)
    {
        /**
         * Two setters can only conflict, if they set the same element.
         */
        if (setter1.name.equals(setter2.name) == false)
        {
            return null;
        }

        /**
         * setter1 must be declared in a proper subtype of the type that declares setter2.
         */
        if (setter1.owner.equals(setter2.owner))
        {
            return null;
        }

        if (setter1.owner.isSubtypeOf(setter2.owner) == false)
        {
            return null;
        }

        /**
         * No problem exists, if setter1 or setter2 can simply redirect to the other.
         */
        if (setter1.parameter.isSubtypeOf(setter2.parameter))
        {
            return null;
        }

        return new CovarianceViolation2(setter1, setter2);
    }
}
