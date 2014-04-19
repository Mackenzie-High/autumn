package high.mackenzie.autumn.lang.compiler.args;

import high.mackenzie.snowflake.Grammar;
import high.mackenzie.snowflake.GrammarBuilder;
import high.mackenzie.snowflake.IParser;
import high.mackenzie.snowflake.ParserOutput;


/**
 * This class was auto-generated using the Snowflake parser-generator.
 *
 * <p>Generated On: Mon Feb 24 03:44:08 EST 2014</p>
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
        g.range("@class0", (char) 0, (char) 65535);
        g.range("@class1", (char) 34, (char) 34);
        g.range("@class2", (char) 34, (char) 34);
        g.combine("@class3", "@class2");
        g.negate("@class4", "@class3");
        g.range("@class5", (char) 34, (char) 34);
        g.range("@class6", (char) 32, (char) 32);
        g.range("@class7", (char) 34, (char) 34);
        g.range("@class8", (char) 45, (char) 45);
        g.combine("@class9", "@class6", "@class7", "@class8");
        g.range("@class10", (char) 0, (char) 65535);
        g.range("@class11", (char) 32, (char) 32);
        g.range("@class12", (char) 34, (char) 34);
        g.combine("@class13", "@class11", "@class12");
        g.range("@class14", (char) 0, (char) 65535);
        g.range("@class15", (char) 0, (char) 65535);
        g.range("@class16", (char) 32, (char) 32);

        // Grammar Rules
        g.choose("@0", "accept", "error");
        g.choose("@10", "@8", "@9");
        g.choose("@14", "@11", "@12", "@13");
        g.choose("@17", "@15", "@16");
        g.choose("@20", "@18", "@19");
        g.choose("@23", "@21", "@22");
        g.choose("@26", "@24", "@25");
        g.choose("@31", "@29", "@30");
        g.choose("@34", "qstring", "pstring2");
        g.choose("@35", "qstring", "pstring1");
        g.choose("@7", "@5", "@6");
        g.choose("option", "option_help", "option_compile", "option_interpret", "option_source", "option_library", "option_version", "option_output", "option_pretty", "option_license");
        g.chr("@3", "@class0");
        g.chr("@36", "@class1");
        g.chr("@37", "@class4");
        g.chr("@39", "@class5");
        g.chr("@40", "@class9");
        g.chr("@42", "@class10");
        g.chr("@44", "@class13");
        g.chr("@46", "@class14");
        g.chr("@48", "@class15");
        g.chr("SPACE", "@class16");
        g.not("@2", "@1");
        g.not("@41", "@40");
        g.not("@45", "@44");
        g.repeat("@32", "arg", 0, 2147483647);
        g.repeat("@38", "@37", 0, 2147483647);
        g.repeat("WS", "SPACE", 0, 2147483647);
        g.repeat("args", "@33", 0, 1);
        g.repeat("arguments", "argument", 0, 2147483647);
        g.repeat("error", "@48", 0, 2147483647);
        g.repeat("ignore", "@4", 0, 2147483647);
        g.repeat("options", "option", 0, 2147483647);
        g.repeat("pstring1", "@43", 1, 2147483647);
        g.repeat("pstring2", "@47", 1, 2147483647);
        g.sequence("@1", "AUTUMN", "SPACE");
        g.sequence("@33", "@31", "WS", "@32");
        g.sequence("@4", "@2", "@3");
        g.sequence("@43", "@41", "@42");
        g.sequence("@47", "@45", "@46");
        g.sequence("accept", "ignore", "AUTUMN", "SPACE", "WS", "options", "WS", "args", "WS");
        g.sequence("arg", "@34", "WS");
        g.sequence("argument", "@35", "WS");
        g.sequence("line", "@0", "END");
        g.sequence("option_compile", "@14", "WS");
        g.sequence("option_help", "@7", "WS");
        g.sequence("option_interpret", "@10", "WS");
        g.sequence("option_library", "@20", "WS", "arguments", "WS");
        g.sequence("option_license", "@28", "WS");
        g.sequence("option_output", "@23", "WS", "argument", "WS");
        g.sequence("option_pretty", "@26", "WS");
        g.sequence("option_source", "@17", "WS", "arguments", "WS");
        g.sequence("option_version", "@27", "WS");
        g.sequence("qstring", "@36", "@38", "@39");
        g.str("@11", "-cmp");
        g.str("@12", "-compile");
        g.str("@13", "-c");
        g.str("@15", "-src");
        g.str("@16", "-s");
        g.str("@18", "-lib");
        g.str("@19", "-l");
        g.str("@21", "-out");
        g.str("@22", "-o");
        g.str("@24", "-pretty");
        g.str("@25", "-p");
        g.str("@27", "-version");
        g.str("@28", "-license");
        g.str("@29", "-args");
        g.str("@30", "-a");
        g.str("@5", "-help");
        g.str("@6", "-h");
        g.str("@8", "-interpret");
        g.str("@9", "-i");
        g.str("AUTUMN", "autumn");

        // Specify which rule is the root of the grammar.
        g.setRoot("line");

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
