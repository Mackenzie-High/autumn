package high.mackenzie.autumn.util.json;

import autumn.lang.Module;
import autumn.lang.Record;
import autumn.util.F;
import com.google.common.collect.Maps;
import high.mackenzie.autumn.util.json.parser.Parser;
import high.mackenzie.snowflake.ParserOutput;
import high.mackenzie.snowflake.ParsingFailedException;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Instances of this class are used to convert a JSON string to an Autumn object.
 *
 * @author Mackenzie High
 */
public final class JsonDecoder
{
    private final Map<String, Record> types = Maps.newTreeMap();

    public Object decode(final Module module,
                         final String input)
    {
        try
        {
            loadTypes(module);

            final Parser parser = new Parser();

            final ParserOutput output = parser.parse(input);

            output.requireSuccess();



            return null;
        }
        catch (ParsingFailedException ex)
        {
            throw new IllegalArgumentException(ex);
        }
    }

    private void loadTypes(final Module module)
    {
        for (Class type : module.moduleInfo().structs())
        {
            loadType(type);
        }

        for (Class type : module.moduleInfo().tuples())
        {
            loadType(type);
        }
    }

    private void loadType(final Class type)
    {
        try
        {
            final Record instance = (Record) type.newInstance();

            final String signatrue = signatureOf(instance);

            types.put(signatrue, instance);
        }
        catch (InstantiationException ex)
        {
            throw new IllegalArgumentException(ex);
        }
        catch (IllegalAccessException ex)
        {
            throw new IllegalArgumentException(ex);
        }
    }

    private String signatureOf(final Record instance)
    {
        final SortedSet<String> keys = new TreeSet(instance.keys());

        final String result = F.str(keys, "(", ", ", ")");

        return result;
    }
}
