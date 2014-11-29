package autumn.util.data.json;

import autumn.lang.Record;
import autumn.util.F;
import autumn.util.Records;
import autumn.util.Strings;
import com.google.common.collect.Lists;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * An instance of this class can convert an Autumn object to a Json string.
 *
 * @author Mackenzie High
 */
public final class JsonEncoder
{
    private boolean typed = true;

    public JsonEncoder()
    {
        this.typed = true;
    }

    public JsonEncoder(final boolean typed)
    {
        this.typed = typed;
    }

    private String encodeTypedAtom(final String type,
                                final String value)
    {
        if (typed)
        {
            return String.format("{ \"class\" : \"%s\", \"value\" : %s }", type, value);
        }
        else
        {
            return value;
        }
    }

    public String encode(final Object value)
    {
        if (value == null)
        {
            return "null";
        }
        else if (value instanceof Record)
        {
            return encode((Record) value);
        }
        else if (value instanceof Enum)
        {
            return encode((Enum) value);
        }
        else if (value instanceof Boolean)
        {
            return encode((boolean) (Boolean) value);
        }
        else if (value instanceof Character)
        {
            return encode((char) (Character) value);
        }
        else if (value instanceof Byte)
        {
            return encode((byte) (Byte) value);
        }
        else if (value instanceof Short)
        {
            return encode((short) (Short) value);
        }
        else if (value instanceof Integer)
        {
            return encode((int) (Integer) value);
        }
        else if (value instanceof Long)
        {
            return encode((long) (Long) value);
        }
        else if (value instanceof Float)
        {
            return encode((float) (Float) value);
        }
        else if (value instanceof Double)
        {
            return encode((double) (Double) value);
        }
        else if (value instanceof BigInteger)
        {
            return encode((BigInteger) value);
        }
        else if (value instanceof BigDecimal)
        {
            return encode((BigDecimal) value);
        }
        else if (value instanceof String)
        {
            return encode((String) value);
        }
        else if (value instanceof Map)
        {
            return encode((Map) value);
        }
        else if (value instanceof List)
        {
            return encode((List) value);
        }
        else
        {
            throw new IllegalArgumentException("The given object cannot be converted to a JSON string.");
        }
    }

    public String encode(final Record record)
    {
        final List<String> pairs = Lists.newLinkedList();

        pairs.add(String.format("\"class\" : \"%s\"", record.getClass().getName()));

        for (Entry<String, ? extends Object> entry : Records.entryMap(record).entrySet())
        {
            final String key = entry.getKey();

            final String value = encode(entry.getValue());

            final String pair = String.format("\"%s\" : %s", key, value);

            pairs.add(pair);
        }

        final String result = Strings.str(pairs, "{ ", ", ", " }");

        return result;
    }

    public String encode(final Enum value)
    {
        final String text = value.toString();

        final String result = encodeTypedAtom(value.getClass().getSimpleName(), text);

        return result;
    }

    public String encode(final boolean value)
    {
        final String text = F.str(value);

        return text;
    }

    public String encode(final char value)
    {
        final String text = F.str((int) value);

        final String result = encodeTypedAtom(char.class.getSimpleName(), text);

        return result;
    }

    public String encode(final byte value)
    {
        final String text = F.str(value);

        final String result = encodeTypedAtom(byte.class.getSimpleName(), text);

        return result;
    }

    public String encode(final short value)
    {
        final String text = F.str(value);

        final String result = encodeTypedAtom(short.class.getSimpleName(), text);

        return result;
    }

    public String encode(final int value)
    {
        final String text = F.str(value);

        final String result = encodeTypedAtom(int.class.getSimpleName(), text);

        return result;
    }

    public String encode(final long value)
    {
        final String text = F.str(value);

        final String result = encodeTypedAtom(long.class.getSimpleName(), text);

        return result;
    }

    public String encode(final float value)
    {
        final String text = F.str(value);

        final String result = encodeTypedAtom(float.class.getSimpleName(), text);

        return result;
    }

    public String encode(final double value)
    {
        final String text = F.str(value);

        final String result = encodeTypedAtom(double.class.getSimpleName(), text);

        return result;
    }

    public String encode(final BigInteger value)
    {
        final String text = F.str(value);

        final String result = encodeTypedAtom(BigInteger.class.getSimpleName(), text);

        return result;
    }

    public String encode(final BigDecimal value)
    {
        final String text = F.str(value);

        final StringBuilder result = new StringBuilder();

        boolean add = false;

        for (int i = text.length() - 1; i >= 0; i--)
        {
            final char chr = text.charAt(i);

            if (chr != '0')
            {
                add = true;
            }

            if (add)
            {
                result.append(chr);
            }
        }

        result.reverse();

        if (result.charAt(result.length() - 1) == '.')
        {
            result.append('0');
        }

        return result.toString();
    }

    public String encode(final String value)
    {
        return "\"" + F.str(value) + "\"";
    }

    public String encode(final Map<String, ? extends Object> map)
    {
        final List<String> pairs = Lists.newLinkedList();

        for (Entry<String, ? extends Object> entry : map.entrySet())
        {
            final String key = entry.getKey();

            final String value = encode(entry.getValue());

            final String pair = String.format("\"%s\" : %s", key, value);

            pairs.add(pair);
        }

        final String result = Strings.str(pairs, "{ ", ", ", " }");

        return result;
    }

    public String encode(final List value)
    {
        final List<String> elements = Lists.newLinkedList();

        for (Object element : value)
        {
            elements.add(encode(element));
        }

        final String result = Strings.str(elements, "[", ", ", "]");

        return result;
    }

    public static void main(String[] args)
    {
        Map m = new HashMap();
        m.put("Emma", "Pretty");


        Object x = Lists.newArrayList(12, 'A', "Hello", F.big(123.0), true, null, m);


        final String json = (new JsonEncoder()).encode(x);
        System.out.println(json);
    }
}
