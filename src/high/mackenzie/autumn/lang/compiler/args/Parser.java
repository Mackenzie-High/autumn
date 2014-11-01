package high.mackenzie.autumn.lang.compiler.args;

import high.mackenzie.snowflake.Grammar;
import high.mackenzie.snowflake.GrammarBuilder;
import high.mackenzie.snowflake.IParser;
import high.mackenzie.snowflake.ParserOutput;


/**
 * This class was auto-generated using the Snowflake parser-generator.
 *
 * <p>Generated On: Sat Nov 01 12:15:18 EDT 2014</p>
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
        g.range("@class8", (char) 10, (char) 10);
        g.range("@class9", (char) 13, (char) 13);
        g.combine("@class10", "@class6", "@class7", "@class8", "@class9");
        g.negate("@class11", "@class10");
        g.range("@class12", (char) 0, (char) 65535);
        g.range("@class13", (char) 10, (char) 10);
        g.range("@class14", (char) 13, (char) 13);
        g.range("@class15", (char) 32, (char) 32);
        g.combine("@class16", "@class13", "@class14", "@class15");

        // Grammar Rules
        g.choose("@0", "accept", "error");
        g.choose("@17", "@13", "@14", "@15", "@16");
        g.choose("@19", "qstring", "pstring");
        g.choose("cases", "case_create", "case_run", "case_debug", "case_test", "case_doc", "case_version", "case_help", "case_license");
        g.chr("@20", "@class1");
        g.chr("@21", "@class4");
        g.chr("@23", "@class5");
        g.chr("@24", "@class11");
        g.chr("@26", "@class12");
        g.chr("@5", "@class0");
        g.chr("WS_CHR", "@class16");
        g.not("@4", "@3");
        g.repeat("@22", "@21", 0, 2147483647);
        g.repeat("@25", "@24", 1, 2147483647);
        g.repeat("WS", "WS_CHR", 0, 2147483647);
        g.repeat("args", "arg", 0, 2147483647);
        g.repeat("error", "@26", 0, 2147483647);
        g.repeat("ignore", "@6", 0, 2147483647);
        g.sequence("@3", "@2", "WS_CHR");
        g.sequence("@6", "@4", "@5");
        g.sequence("accept", "ignore", "@1", "WS_CHR", "WS", "cases", "WS");
        g.sequence("arg", "@19", "WS");
        g.sequence("case_create", "@7", "WS", "name", "WS");
        g.sequence("case_debug", "@9", "WS", "args", "WS");
        g.sequence("case_doc", "@11", "WS");
        g.sequence("case_help", "@17", "WS");
        g.sequence("case_license", "@18", "WS");
        g.sequence("case_run", "@8", "WS", "args", "WS");
        g.sequence("case_test", "@10", "WS");
        g.sequence("case_version", "@12", "WS");
        g.sequence("line", "@0", "END");
        g.sequence("name", "arg");
        g.sequence("pstring", "WS", "@25", "WS");
        g.sequence("qstring", "@20", "@22", "@23");
        g.str("@1", "autumn");
        g.str("@10", "test");
        g.str("@11", "document");
        g.str("@12", "version");
        g.str("@13", "help");
        g.str("@14", "-help");
        g.str("@15", "-h");
        g.str("@16", "--h");
        g.str("@18", "license");
        g.str("@2", "autumn");
        g.str("@7", "create");
        g.str("@8", "run");
        g.str("@9", "debug");

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
