package high.mackenzie.autumn.util.json.parser;

import com.mackenziehigh.snowflake.Grammar;
import com.mackenziehigh.snowflake.GrammarBuilder;
import com.mackenziehigh.snowflake.IParser;
import com.mackenziehigh.snowflake.ParserOutput;


/**
 * This class was auto-generated using the Snowflake parser-generator.
 *
 * <p>Generated On: Sun Nov 16 06:03:02 EST 2014</p>
 */
public final class Parser implements IParser
{

    private static final Grammar grammar = grammar();


    /**
     * This method constructs the grammar object.
     */
    private static Grammar grammar()
    {
        final GrammarBuilder g = new GrammarBuilder();

        // Character Classes
        g.range("@class0", (char) 46, (char) 46);
        g.range("@class1", (char) 46, (char) 46);
        g.range("@class2", (char) 46, (char) 46);
        g.range("@class3", (char) 45, (char) 45);
        g.range("@class4", (char) 34, (char) 34);
        g.range("@class5", (char) 34, (char) 34);
        g.range("@class6", (char) 92, (char) 92);
        g.range("@class7", (char) 34, (char) 34);
        g.range("@class8", (char) 116, (char) 116);
        g.range("@class9", (char) 34, (char) 34);
        g.combine("@class10", "@class9");
        g.negate("@class11", "@class10");
        g.range("@class12", (char) 91, (char) 91);
        g.range("@class13", (char) 93, (char) 93);
        g.range("@class14", (char) 44, (char) 44);
        g.range("@class15", (char) 123, (char) 123);
        g.range("@class16", (char) 125, (char) 125);
        g.range("@class17", (char) 44, (char) 44);
        g.range("@class18", (char) 58, (char) 58);
        g.range("@class19", (char) 48, (char) 57);
        g.range("@class20", (char) 10, (char) 10);
        g.range("@class21", (char) 13, (char) 13);
        g.range("@class22", (char) 32, (char) 32);
        g.combine("@class23", "@class20", "@class21", "@class22");

        // Grammar Rules
        g.choose("@20", "@18", "@19");
        g.choose("@4", "@2", "@3");
        g.choose("STR_CHAR", "ESCAPE_SEQUENCE", "NON_QUOTE");
        g.choose("e", "@9", "@10", "@11", "@12", "@13", "@14");
        g.choose("number_value", "number_value_4", "number_value_3", "number_value_2", "number_value_1");
        g.choose("value", "null", "boolean", "number", "string", "array", "object");
        g.chr("@15", "@class4");
        g.chr("@16", "@class5");
        g.chr("@17", "@class6");
        g.chr("@18", "@class7");
        g.chr("@19", "@class8");
        g.chr("@21", "@class12");
        g.chr("@22", "@class13");
        g.chr("@23", "@class14");
        g.chr("@27", "@class15");
        g.chr("@28", "@class16");
        g.chr("@29", "@class17");
        g.chr("@33", "@class18");
        g.chr("@5", "@class0");
        g.chr("@6", "@class1");
        g.chr("@7", "@class2");
        g.chr("@8", "@class3");
        g.chr("DIGIT", "@class19");
        g.chr("NON_QUOTE", "@class11");
        g.chr("WS_CHR", "@class23");
        g.repeat("@0", "value", 0, 1);
        g.repeat("@25", "@24", 0, 2147483647);
        g.repeat("@31", "@30", 0, 2147483647);
        g.repeat("DIGITS", "DIGIT", 1, 2147483647);
        g.repeat("STR_CHARS", "STR_CHAR", 0, 2147483647);
        g.repeat("WS", "WS_CHR", 0, 2147483647);
        g.repeat("elements", "@26", 0, 1);
        g.repeat("members", "@32", 0, 1);
        g.repeat("sign", "@8", 0, 1);
        g.sequence("@24", "WS", "@23", "WS", "value");
        g.sequence("@26", "value", "@25");
        g.sequence("@30", "WS", "@29", "WS", "pair");
        g.sequence("@32", "pair", "@31");
        g.sequence("ESCAPE_SEQUENCE", "@17", "@20");
        g.sequence("array", "@21", "WS", "elements", "WS", "@22", "WS");
        g.sequence("boolean", "@4", "WS");
        g.sequence("input", "WS", "@0", "WS", "END");
        g.sequence("null", "@1", "WS");
        g.sequence("number", "number_value", "WS");
        g.sequence("number_value_1", "sign", "DIGITS");
        g.sequence("number_value_2", "sign", "DIGITS", "@5", "DIGITS");
        g.sequence("number_value_3", "sign", "DIGITS", "@6", "e", "DIGITS");
        g.sequence("number_value_4", "sign", "DIGITS", "@7", "DIGITS", "e", "DIGITS");
        g.sequence("object", "@27", "WS", "members", "@28", "WS");
        g.sequence("pair", "string", "WS", "@33", "WS", "value", "WS");
        g.sequence("string", "@15", "STR_CHARS", "@16", "WS");
        g.str("@1", "null");
        g.str("@10", "e-");
        g.str("@11", "e");
        g.str("@12", "E+");
        g.str("@13", "E-");
        g.str("@14", "E");
        g.str("@2", "true");
        g.str("@3", "false");
        g.str("@9", "e+");

        // Specify which rule is the root of the grammar.
        g.setRoot("input");

        // Specify the number of tracing records to store concurrently.
        g.setTraceCount(1024);

        // Perform the actual construction of the grammar object.
        return g.build();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public ParserOutput parse(final char[] input)
    {
        return grammar.newParser().parse(input);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public ParserOutput parse(final String input)
    {
        return parse(input.toCharArray());
    }
}
