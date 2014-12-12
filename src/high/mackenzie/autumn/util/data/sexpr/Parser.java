package high.mackenzie.autumn.util.data.sexpr;

import high.mackenzie.snowflake.Grammar;
import high.mackenzie.snowflake.GrammarBuilder;
import high.mackenzie.snowflake.IParser;
import high.mackenzie.snowflake.ParserOutput;


/**
 * This class was auto-generated using the Snowflake parser-generator.
 *
 * <p>Generated On: Sun Nov 30 10:05:18 EST 2014</p>
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
        g.range("@class4", (char) 65, (char) 90);
        g.range("@class5", (char) 97, (char) 122);
        g.range("@class6", (char) 48, (char) 57);
        g.range("@class7", (char) 33, (char) 33);
        g.range("@class8", (char) 64, (char) 64);
        g.range("@class9", (char) 35, (char) 35);
        g.range("@class10", (char) 36, (char) 36);
        g.range("@class11", (char) 37, (char) 37);
        g.range("@class12", (char) 94, (char) 94);
        g.range("@class13", (char) 38, (char) 38);
        g.range("@class14", (char) 42, (char) 42);
        g.range("@class15", (char) 58, (char) 58);
        g.range("@class16", (char) 59, (char) 59);
        g.range("@class17", (char) 44, (char) 44);
        g.range("@class18", (char) 46, (char) 46);
        g.range("@class19", (char) 63, (char) 63);
        g.range("@class20", (char) 47, (char) 47);
        g.range("@class21", (char) 43, (char) 43);
        g.range("@class22", (char) 45, (char) 45);
        g.range("@class23", (char) 60, (char) 60);
        g.range("@class24", (char) 62, (char) 62);
        g.range("@class25", (char) 96, (char) 96);
        g.range("@class26", (char) 126, (char) 126);
        g.range("@class27", (char) 95, (char) 95);
        g.range("@class28", (char) 61, (char) 61);
        g.combine("@class29", "@class4", "@class5", "@class6", "@class7", "@class8", "@class9", "@class10", "@class11", "@class12", "@class13", "@class14", "@class15", "@class16", "@class17", "@class18", "@class19", "@class20", "@class21", "@class22", "@class23", "@class24", "@class25", "@class26", "@class27", "@class28");
        g.range("@class30", (char) 34, (char) 34);
        g.range("@class31", (char) 34, (char) 34);
        g.range("@class32", (char) 92, (char) 92);
        g.range("@class33", (char) 34, (char) 34);
        g.range("@class34", (char) 116, (char) 116);
        g.range("@class35", (char) 34, (char) 34);
        g.combine("@class36", "@class35");
        g.negate("@class37", "@class36");
        g.range("@class38", (char) 40, (char) 40);
        g.range("@class39", (char) 41, (char) 41);
        g.range("@class40", (char) 48, (char) 57);
        g.range("@class41", (char) 10, (char) 10);
        g.range("@class42", (char) 13, (char) 13);
        g.range("@class43", (char) 32, (char) 32);
        g.combine("@class44", "@class41", "@class42", "@class43");

        // Grammar Rules
        g.choose("@21", "@19", "@20");
        g.choose("@4", "@2", "@3");
        g.choose("STR_CHAR", "ESCAPE_SEQUENCE", "NON_QUOTE");
        g.choose("e", "@9", "@10", "@11", "@12", "@13", "@14");
        g.choose("number_value", "number_value_4", "number_value_3", "number_value_2", "number_value_1");
        g.choose("value", "null", "boolean", "number", "word", "string", "list");
        g.chr("@15", "@class29");
        g.chr("@16", "@class30");
        g.chr("@17", "@class31");
        g.chr("@18", "@class32");
        g.chr("@19", "@class33");
        g.chr("@20", "@class34");
        g.chr("@22", "@class38");
        g.chr("@23", "@class39");
        g.chr("@5", "@class0");
        g.chr("@6", "@class1");
        g.chr("@7", "@class2");
        g.chr("@8", "@class3");
        g.chr("DIGIT", "@class40");
        g.chr("NON_QUOTE", "@class37");
        g.chr("WS_CHR", "@class44");
        g.repeat("@0", "value", 0, 1);
        g.repeat("DIGITS", "DIGIT", 1, 2147483647);
        g.repeat("STR_CHARS", "STR_CHAR", 0, 2147483647);
        g.repeat("WS", "WS_CHR", 0, 2147483647);
        g.repeat("elements", "@24", 0, 2147483647);
        g.repeat("sign", "@8", 0, 1);
        g.repeat("word", "@15", 1, 2147483647);
        g.sequence("@24", "value", "WS");
        g.sequence("ESCAPE_SEQUENCE", "@18", "@21");
        g.sequence("boolean", "@4", "WS");
        g.sequence("input", "WS", "@0", "WS", "END");
        g.sequence("list", "@22", "WS", "elements", "WS", "@23", "WS");
        g.sequence("null", "@1", "WS");
        g.sequence("number", "number_value", "WS");
        g.sequence("number_value_1", "sign", "DIGITS");
        g.sequence("number_value_2", "sign", "DIGITS", "@5", "DIGITS");
        g.sequence("number_value_3", "sign", "DIGITS", "@6", "e", "DIGITS");
        g.sequence("number_value_4", "sign", "DIGITS", "@7", "DIGITS", "e", "DIGITS");
        g.sequence("string", "@16", "STR_CHARS", "@17", "WS");
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
