package high.mackenzie.autumn.util.data.json;

import autumn.util.data.json.JsonValue;
import high.mackenzie.snowflake.ParserOutput;

/**
 *
 * @author mackenzie
 */
public final class JsonParser
{
    public static JsonValue parse(final String input)
    {
        final Parser parser = new Parser();

        final ParserOutput output = parser.parse(input);

        if (output.success() == false)
        {
            final int line = 0; // TODO

            final int column = 0; // TODO

            throw new IllegalArgumentException(String.format("Parsing Failed - Line: %d - Column %d", line, column));
        }

        final Visitor visitor = new Visitor();

        visitor.visit(output.parseTree());

        if (visitor.stack.isEmpty())
        {
            return null;
        }
        else
        {
            return (JsonValue) visitor.stack.pop();
        }
    }
}
