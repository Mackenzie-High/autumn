package high.mackenzie.autumn.lang.compiler.args;

import com.mackenziehigh.snowflake.Grammar;
import com.mackenziehigh.snowflake.GrammarBuilder;
import com.mackenziehigh.snowflake.IParser;
import com.mackenziehigh.snowflake.ParserOutput;


/**
 * This class was auto-generated using the Snowflake parser-generator.
 *
 * <p>Generated On: Mon Mar 30 16:55:04 EDT 2015</p>
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
        g.range("@class0", (char) 34, (char) 34);
        g.range("@class1", (char) 34, (char) 34);
        g.combine("@class2", "@class1");
        g.negate("@class3", "@class2");
        g.range("@class4", (char) 34, (char) 34);
        g.range("@class5", (char) 32, (char) 32);
        g.range("@class6", (char) 34, (char) 34);
        g.range("@class7", (char) 10, (char) 10);
        g.range("@class8", (char) 13, (char) 13);
        g.combine("@class9", "@class5", "@class6", "@class7", "@class8");
        g.negate("@class10", "@class9");
        g.range("@class11", (char) 0, (char) 65535);
        g.range("@class12", (char) 34, (char) 34);
        g.range("@class13", (char) 10, (char) 10);
        g.range("@class14", (char) 13, (char) 13);
        g.range("@class15", (char) 32, (char) 32);
        g.combine("@class16", "@class13", "@class14", "@class15");

        // Grammar Rules
        g.choose("@0", "accept", "error");
        g.choose("@19", "@15", "@16", "@17", "@18");
        g.choose("cases", "case_execute", "case_compile", "case_create", "case_run", "case_test", "case_doc", "case_version", "case_help", "case_license");
        g.chr("@3", "@class0");
        g.chr("@4", "@class3");
        g.chr("@6", "@class4");
        g.chr("@7", "@class10");
        g.chr("@9", "@class11");
        g.chr("Q", "@class12");
        g.chr("WS_CHR", "@class16");
        g.not("@2", "@1");
        g.repeat("@5", "@4", 0, 2147483647);
        g.repeat("@8", "@7", 1, 2147483647);
        g.repeat("WS", "WS_CHR", 0, 2147483647);
        g.repeat("args", "arg", 0, 2147483647);
        g.repeat("error", "@9", 0, 2147483647);
        g.repeat("paths", "path", 0, 2147483647);
        g.sequence("@1", "WS", "EXECUTE");
        g.sequence("AUTUMN", "Q", "@10", "Q");
        g.sequence("COMPILE", "Q", "@11", "Q");
        g.sequence("CREATE", "Q", "@12", "Q");
        g.sequence("DOCUMENT", "Q", "@13", "Q");
        g.sequence("EXECUTE", "Q", "@14", "Q");
        g.sequence("HELP", "Q", "@19", "Q");
        g.sequence("LICENSE", "Q", "@20", "Q");
        g.sequence("RUN", "Q", "@21", "Q");
        g.sequence("TEST", "Q", "@22", "Q");
        g.sequence("VERSION", "Q", "@23", "Q");
        g.sequence("accept", "WS", "cases", "WS");
        g.sequence("arg", "qstring", "WS");
        g.sequence("case_compile", "COMPILE", "WS");
        g.sequence("case_create", "CREATE", "WS", "name", "WS");
        g.sequence("case_doc", "DOCUMENT", "WS");
        g.sequence("case_execute", "paths", "WS", "EXECUTE", "WS", "args", "WS");
        g.sequence("case_help", "HELP", "WS");
        g.sequence("case_license", "LICENSE", "WS");
        g.sequence("case_run", "RUN", "WS", "args", "WS");
        g.sequence("case_test", "TEST", "WS");
        g.sequence("case_version", "VERSION", "WS");
        g.sequence("line", "@0", "END");
        g.sequence("name", "arg");
        g.sequence("path", "@2", "arg");
        g.sequence("pstring", "WS", "@8", "WS");
        g.sequence("qstring", "WS", "@3", "@5", "@6", "WS");
        g.str("@10", "autumn");
        g.str("@11", "compile");
        g.str("@12", "create");
        g.str("@13", "document");
        g.str("@14", "execute");
        g.str("@15", "help");
        g.str("@16", "-help");
        g.str("@17", "-h");
        g.str("@18", "--h");
        g.str("@20", "license");
        g.str("@21", "run");
        g.str("@22", "test");
        g.str("@23", "version");

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
