package com.mackenziehigh.autumn.util.json;

import autumn.lang.Record;
import autumn.util.F;
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
            return encode(F.big(value));
        }
        else if (value instanceof Short)
        {
            return encode(F.big(value));
        }
        else if (value instanceof Integer)
        {
            return encode(F.big(value));
        }
        else if (value instanceof Long)
        {
            return encode(F.big(value));
        }
        else if (value instanceof Float)
        {
            return encode(F.big(value));
        }
        else if (value instanceof Double)
        {
            return encode(F.big(value));
        }
        else if (value instanceof BigInteger)
        {
            return encode(F.big(value));
        }
        else if (value instanceof BigDecimal)
        {
            return encode(F.big(value));
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

        for (String key : record.keys())
        {
            final String value = encode(F.get(record, key));

            final String pair = String.format("\"%s\" : %s", key, value);

            pairs.add(pair);
        }

        final String result = F.str(pairs, "{ ", ", ", " }");

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

        final String result = text;

        return result;
    }

    public String encode(final BigDecimal value)
    {
        if (value.equals(F.big(0)))
        {
            return "0";
        }

        final String result = value
                .toString()
                .replaceAll("0*[Ee]", "E")
                .replaceAll("\\.0*$", "");

        assert F.parseBigDecimal(result).equals(F.big(value));

        return result;
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

        final String result = F.str(pairs, "{ ", ", ", " }");

        return result;
    }

    public String encode(final List value)
    {
        final List<String> elements = Lists.newLinkedList();

        for (Object element : value)
        {
            elements.add(encode(element));
        }

        final String result = F.str(elements, "[", ", ", "]");

        return result;
    }

    public static void main(String[] args)
    {
        Map m = new HashMap();
        m.put("Emma", "Pretty");


        Object x = Lists.newArrayList(12, 'A', "Hello", F.parseBigDecimal("123.0"), true, null, m);


        final String json = (new JsonEncoder()).encode(x);
        System.out.println(json);
    }
}
