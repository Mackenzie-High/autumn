package autumn.lang.compiler.errors;

import autumn.lang.compiler.ast.commons.IConstruct;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import high.mackenzie.autumn.resources.Finished;
import java.util.LinkedHashMap;

/**
 * An instance of this class is an error-report that describes the failure of a single static check.
 *
 * @author Mackenzie High
 */
@Finished("2014/08/22")
public final class ErrorReport
{
    /**
     * This is the AST node that failed a compile-time check.
     */
    private final IConstruct node;

    /**
     * This error-code indicates the type of static check that failed.
     */
    private final ErrorCode code;

    /**
     * This is an informative message that should help the user find the cause of the error.
     */
    private String message;

    /**
     * These are details that should help the user find the cause of the reported error.
     */
    private final LinkedHashMap<String, String> details = Maps.newLinkedHashMap();

    /**
     * Constructor.
     *
     * @param node is the AST node that failed the compile-time check.
     * @param code is the error-code that indicates the type of check that failed.
     * @param message is a meaningful message that describes the error being reported.
     */
    public ErrorReport(final IConstruct node,
                       final ErrorCode code,
                       final String message)
    {
        Preconditions.checkNotNull(node);
        Preconditions.checkNotNull(code);
        Preconditions.checkNotNull(message);

        this.node = node;
        this.code = code;
        this.message = message;
    }

    /**
     * This method returns the AST node that failed the static check.
     *
     * @return the aforedescribed AST node.
     */
    public IConstruct node()
    {
        return node;
    }

    /**
     * This method returns the error-code that indicates the type of static check that failed.
     *
     * @return the aforedescribed error-code.
     */
    public ErrorCode code()
    {
        return code;
    }

    /**
     * This method returns the error-message associated with this error-report.
     *
     * @return a meaningful error-message.
     */
    public String message()
    {
        return message;
    }

    /**
     * This method adds an additional detail to this error report.
     *
     * @param key describes the type of detail being added.
     * @param value is the detail itself.
     */
    public void addDetail(final String key,
                          final String value)
    {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(value);

        details.put(key, value);
    }

    /**
     * This method retrieves the details that have been added to this error-report.
     *
     * @return a map that contains the details in the order that they were added to this report.
     */
    public LinkedHashMap<String, String> details()
    {
        return Maps.newLinkedHashMap(details);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        return code().name();
    }
}
