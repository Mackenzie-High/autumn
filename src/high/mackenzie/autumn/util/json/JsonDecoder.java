package high.mackenzie.autumn.util.json;

import autumn.lang.Module;
import autumn.lang.Record;
import com.google.common.collect.Maps;
import high.mackenzie.autumn.util.json.parser.Parser;
import high.mackenzie.autumn.util.json.parser.Visitor;
import high.mackenzie.snowflake.ITreeNode;
import high.mackenzie.snowflake.ParserOutput;
import high.mackenzie.snowflake.ParsingFailedException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Instances of this class are used to convert a JSON string to an Autumn object.
 *
 * @author Mackenzie High
 */
public final class JsonDecoder
{
    private final Map<Set<String>, Record> types = Maps.newHashMap();

    public Object decode(final Module module,
                         final String input)
    {
        try
        {
            loadTypes(module);

            final Parser parser = new Parser();

            final ParserOutput output = parser.parse(input);

            output.requireSuccess();

            final ITreeNode tree = output.parseTree();

            final Visitor converter = new Visitor();

            converter.prototypes.putAll(types);

            converter.visitUnknown(tree);

            final Object result = converter.stack.pop();

            return result;
        }
        catch (ParsingFailedException ex)
        {
            throw new IllegalArgumentException(ex);
        }
    }

    private void loadTypes(final Module module)
    {
        for (Class type : module.info().structs())
        {
            loadType(type);
        }

        for (Class type : module.info().tuples())
        {
            loadType(type);
        }
    }

    private void loadType(final Class type)
    {
        try
        {
            final Record instance = (Record) type.getMethod("instance").invoke(null);

            final Set<String> keys = new TreeSet(instance.keys());

            types.put(keys, instance);
        }
        catch (IllegalAccessException ex)
        {
            throw new IllegalArgumentException(ex);
        }
        catch (NoSuchMethodException ex)
        {
            throw new IllegalArgumentException(ex);
        }
        catch (SecurityException ex)
        {
            throw new IllegalArgumentException(ex);
        }
        catch (InvocationTargetException ex)
        {
            throw new IllegalArgumentException(ex);
        }
    }
}
