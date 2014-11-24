package autumn.lang.debugger;

import autumn.lang.LocalsMap;

/**
 * An instance of this interface is a debugger plugin.
 *
 * @author Mackenzie High
 */
public interface IDebugger
{
    /**
     * This method will be invoked whenever a breakpoint is hit.
     *
     * <p>
     * An implementation of this method should be synchronized,
     * because multiple different threads may hit breakpoints at the same time.
     * </p>
     *
     * @param file is the path to the source-file that contains the breakpoint.
     * @param line is the line-number where the breakpoint is located.
     * @param column is the column-number where the breakpoint is located.
     * @param locals contains the local-variables in the same scope as the breakpoint.
     */
    public void debug(final String file,
                      final int line,
                      final int column,
                      final LocalsMap locals);
}
