package autumn.lang.compiler.errors;

import com.google.common.base.Preconditions;
import com.mackenziehigh.autumn.resources.Finished;
import java.net.URL;

/**
 * An instance of this class is an error-reporter that routes reports through two other
 * error-reporters. In short, this type of error reporter facilitates the creation of a
 * team of error reporters.
 *
 * @author Mackenzie High
 */
@Finished("2014/08/22")
public final class CoupledErrorReporter
        implements IErrorReporter
{
    private final IErrorReporter reporter1;

    private final IErrorReporter reporter2;

    private int count = 0;

    public CoupledErrorReporter(final IErrorReporter reporter1,
                                final IErrorReporter reporter2)
    {
        Preconditions.checkNotNull(reporter1);
        Preconditions.checkNotNull(reporter2);

        this.reporter1 = reporter1;
        this.reporter2 = reporter2;
    }

    /**
     * This method retrieves the first error-reporter.
     *
     * @return the first error-reporter.
     */
    public IErrorReporter reporter1()
    {
        return reporter1;
    }

    /**
     * This method retrieves the second error-reporter.
     *
     * @return the second error-reporter.
     */
    public IErrorReporter reporter2()
    {
        return reporter2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reportSyntaxError(final URL file,
                                  final int line,
                                  final int column)
    {
        ++count;
        reporter1.reportSyntaxError(file, line, column);
        reporter2.reportSyntaxError(file, line, column);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reportFailedCheck(final ErrorReport report)
    {
        ++count;
        reporter1.reportFailedCheck(report);
        reporter2.reportFailedCheck(report);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int errorCount()
    {
        return count;
    }
}
