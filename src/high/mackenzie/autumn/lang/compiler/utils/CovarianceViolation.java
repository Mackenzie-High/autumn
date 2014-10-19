package high.mackenzie.autumn.lang.compiler.utils;

import com.google.common.base.Preconditions;

/**
 * An instance of this class describes a covariance-violation in a record-definition.
 *
 * @author Mackenzie High
 */
public final class CovarianceViolation
{
    /**
     * This is the element that attempts to override the overridden element.
     */
    public final RecordElement overridder;

    /**
     * This is the element that is overridden unsuccessfully.
     */
    public final RecordElement overridden;

    /**
     * Sole Constructor.
     */
    CovarianceViolation(final RecordElement overridden,
                        final RecordElement overridder)
    {
        Preconditions.checkNotNull(overridden);
        Preconditions.checkNotNull(overridder);

        this.overridden = overridden;
        this.overridder = overridder;
    }
}
