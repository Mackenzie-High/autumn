package high.mackenzie.autumn.lang.compiler.pretty;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import java.util.LinkedList;

/**
 * An instance of this class aids in the pretty-printing of curly-bracket style languages.
 *
 * @author Mackenzie High
 */
final class CurlyPrinter
{
    private static enum LineTypes
    {
        BRACKET_OPEN,
        BRACKET_CLOSE,
        STRING,
        EMPTY_LINE,
    }

    /**
     * This is the current indentation level.
     */
    private int indent = 0;

    /**
     * The nth element of this list is the type of information on the nth line.
     */
    private final LinkedList<LineTypes> line_types = Lists.newLinkedList();

    /**
     * The nth element of this list is the text of the nth line.
     */
    private final LinkedList<StringBuilder> line_texts = Lists.newLinkedList();

    /**
     * This method begins printing a new non-empty line.
     */
    public void addLine()
    {
        final StringBuilder line = new StringBuilder();
        line.append(buildIndent());

        line_types.add(LineTypes.STRING);
        line_texts.add(line);
    }

    /**
     * This method adds a line that is deliberately empty.
     *
     * <p>
     * This method will ignore an invocation, if it would result in a duplicate empty line.
     * </p>
     */
    public void addEmptyLine()
    {
        if (!line_types.isEmpty() && line_types.getLast() == LineTypes.EMPTY_LINE)
        {
            return;
        }

        line_types.add(LineTypes.EMPTY_LINE);
        line_texts.add(new StringBuilder());
    }

    /**
     * This method adds an opening bracket that starts an indented region.
     */
    public void addOpeningBracket()
    {
        final StringBuilder line = new StringBuilder();
        line.append(buildIndent());
        line.append("{");

        line_types.add(LineTypes.BRACKET_OPEN);
        line_texts.add(line);

        ++indent;
    }

    /**
     * This method adds a closing bracket that terminates an indented region.
     */
    public void addClosingBracket()
    {
        --indent;

        final StringBuilder line = new StringBuilder();
        line.append(buildIndent());
        line.append("}");

        line_types.add(LineTypes.BRACKET_CLOSE);
        line_texts.add(line);
    }

    /**
     * This method adds a string of text to the current line.
     *
     * @param value is the text to add.
     * @throws IllegalStateException if the current line cannot be written to.
     */
    public void addText(final Object value)
    {
        assert line_types.size() == line_texts.size();

        Preconditions.checkState(line_types.isEmpty() == false);
        Preconditions.checkState(line_types.getLast() == LineTypes.STRING);

        line_texts.get(line_texts.size() - 1).append(value);
    }

    /**
     * If the last lines added were empty-lines, then remove them; otherwise, do nothing.
     */
    public void removeEmptyLines()
    {
        while (!line_types.isEmpty() && line_types.getLast() == LineTypes.EMPTY_LINE)
        {
            line_types.removeLast();
            line_texts.removeLast();
        }
    }

    /**
     * This method creates an indentation string.
     *
     * @return (4 * indent) number of space characters.
     */
    private String buildIndent()
    {
        return Strings.repeat(" ", indent * 4);
    }

    /**
     * This method creates a string representation of this object.
     *
     * <p>
     * Note: Calling this method resets the internal state of this object.
     * </p>
     *
     * @return a pretty printable string.
     */
    public String buildString()
    {
        // Remove any leading empty-lines.
        while (!line_types.isEmpty() && line_types.getFirst() == LineTypes.EMPTY_LINE)
        {
            line_types.removeFirst();
            line_texts.removeFirst();
        }

        // Remove any trailing empty-lines.
        while (!line_types.isEmpty() && line_types.getLast() == LineTypes.EMPTY_LINE)
        {
            line_types.removeLast();
            line_texts.removeLast();
        }

        final StringBuilder result = new StringBuilder();

        for (StringBuilder line : line_texts)
        {
            result.append(line.toString());
            result.append('\n');
        }

        // Reset Everything
        this.indent = 0;
        this.line_types.clear();
        this.line_texts.clear();

        return result.toString();
    }
}
