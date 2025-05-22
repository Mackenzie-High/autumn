package autumn.lang.compiler.errors;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mackenziehigh.autumn.resources.Finished;
import java.io.PrintStream;
import java.net.URL;
import java.util.List;
import java.util.Map.Entry;

/**
 * An instance of this class is an error-reporter that reports errors to a PrintStream.
 *
 * @author Mackenzie High
 */
@Finished("2014/08/22")
public final class BasicErrorReporter
        implements IErrorReporter
{
    /**
     * This is where error messages will be sent to.
     */
    private PrintStream out;

    /**
     * This is the number of errors that have been reported.
     */
    private int count = 0;

    /**
     * These are the error-codes of the reported errors.
     */
    private final List<ErrorCode> codes = Lists.newLinkedList();

    /**
     * These are the errors that have been reported.
     */
    private final List<ErrorReport> reports = Lists.newLinkedList();

    /**
     * Constructor.
     *
     * <p>
     * This constructor creates an error-reporter that reports errors using STDOUT.
     * </p>
     */
    public BasicErrorReporter()
    {
        this(System.out);
    }

    /**
     * Constructor.
     *
     * @param out is the stream to report errors to.
     */
    public BasicErrorReporter(final PrintStream out)
    {
        this.out = out;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reportSyntaxError(final URL file,
                                  final int line,
                                  final int column)
    {
        Preconditions.checkNotNull(file);

        codes.add(ErrorCode.SYNTAX_ERROR);

        ++count;
        out.println("Parsing Failed!");
        out.println("  File: " + file);
        out.println("  Line: #" + line);
        out.println("  Column: #" + column);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reportFailedCheck(final ErrorReport report)
    {
        Preconditions.checkNotNull(report);

        codes.add(report.code());
        reports.add(report);

        ++count;
        out.println("Warning: " + report.code());
        out.println("  File: " + report.node().getLocation().getFile());
        out.println("  Line: #" + report.node().getLocation().getLine());
        out.println("  Column: #" + report.node().getLocation().getColumn());
        out.println("  Message: " + report.message());

        for (Entry<String, String> entry : report.details().entrySet())
        {
            out.println("  " + entry.getKey() + ": " + entry.getValue());
        }

        out.println();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int errorCount()
    {
        return count;
    }

    /**
     * This method returns a list containing the error-codes that have been reported already.
     *
     * @return an immutable list containing the reported error-codes.
     */
    public List<ErrorCode> codes()
    {
        return ImmutableList.copyOf(codes);
    }

    /**
     * This method returns a list containing the error-reports that have been reported already.
     *
     * @return an immutable list containing the reported errors.
     */
    public List<ErrorReport> reports()
    {
        return ImmutableList.copyOf(reports);
    }
}
