package high.mackenzie.autumn.lang.compiler.args;

import high.mackenzie.snowflake.Grammar;
import high.mackenzie.snowflake.GrammarBuilder;
import high.mackenzie.snowflake.IParser;
import high.mackenzie.snowflake.ParserOutput;


/**
 * This class was auto-generated using the Snowflake parser-generator.
 *
 * <p>Generated On: Tue Feb 17 07:08:21 EST 2015</p>
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
        g.choose("@22", "@18", "@19", "@20", "@21");
        g.choose("@24", "qstring", "pstring");
        g.choose("cases", "case_execute", "case_compile", "case_create", "case_run", "case_debug", "case_test", "case_doc", "case_version", "case_help", "case_license");
        g.chr("@25", "@class1");
        g.chr("@26", "@class4");
        g.chr("@28", "@class5");
        g.chr("@29", "@class11");
        g.chr("@31", "@class12");
        g.chr("@5", "@class0");
        g.chr("WS_CHR", "@class16");
        g.not("@10", "@9");
        g.not("@4", "@3");
        g.repeat("@27", "@26", 0, 2147483647);
        g.repeat("@30", "@29", 1, 2147483647);
        g.repeat("WS", "WS_CHR", 0, 2147483647);
        g.repeat("args", "arg", 0, 2147483647);
        g.repeat("error", "@31", 0, 2147483647);
        g.repeat("ignore", "@6", 0, 2147483647);
        g.repeat("paths", "path", 1, 2147483647);
        g.sequence("@3", "@2", "WS_CHR");
        g.sequence("@6", "@4", "@5");
        g.sequence("@9", "WS", "@8");
        g.sequence("accept", "ignore", "@1", "WS_CHR", "WS", "cases", "WS");
        g.sequence("arg", "@24", "WS");
        g.sequence("case_compile", "@11", "WS");
        g.sequence("case_create", "@12", "WS", "name", "WS");
        g.sequence("case_debug", "@14", "WS", "args", "WS");
        g.sequence("case_doc", "@16", "WS");
        g.sequence("case_execute", "paths", "WS", "@7", "WS", "args", "WS");
        g.sequence("case_help", "@22", "WS");
        g.sequence("case_license", "@23", "WS");
        g.sequence("case_run", "@13", "WS", "args", "WS");
        g.sequence("case_test", "@15", "WS");
        g.sequence("case_version", "@17", "WS");
        g.sequence("line", "@0", "END");
        g.sequence("name", "arg");
        g.sequence("path", "@10", "arg");
        g.sequence("pstring", "WS", "@30", "WS");
        g.sequence("qstring", "WS", "@25", "@27", "@28", "WS");
        g.str("@1", "autumn");
        g.str("@11", "compile");
        g.str("@12", "create");
        g.str("@13", "run");
        g.str("@14", "debug");
        g.str("@15", "test");
        g.str("@16", "document");
        g.str("@17", "version");
        g.str("@18", "help");
        g.str("@19", "-help");
        g.str("@2", "autumn");
        g.str("@20", "-h");
        g.str("@21", "--h");
        g.str("@23", "license");
        g.str("@7", "execute");
        g.str("@8", "execute");

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
