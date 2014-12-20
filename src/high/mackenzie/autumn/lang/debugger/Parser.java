package high.mackenzie.autumn.lang.debugger;

import high.mackenzie.snowflake.Grammar;
import high.mackenzie.snowflake.GrammarBuilder;
import high.mackenzie.snowflake.IParser;
import high.mackenzie.snowflake.ParserOutput;


/**
 * This class was auto-generated using the Snowflake parser-generator.
 *
 * <p>Generated On: Sat Dec 20 16:30:39 EST 2014</p>
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
        g.range("@class1", (char) 91, (char) 91);
        g.range("@class2", (char) 93, (char) 93);
        g.range("@class3", (char) 48, (char) 57);
        g.combine("@class4", "@class3");
        g.range("@class5", (char) 65, (char) 90);
        g.range("@class6", (char) 97, (char) 122);
        g.range("@class7", (char) 95, (char) 95);
        g.range("@class8", (char) 36, (char) 36);
        g.range("@class9", (char) 64, (char) 64);
        g.combine("@class10", "@class5", "@class6", "@class7", "@class8", "@class9");
        g.range("@class11", (char) 65, (char) 90);
        g.range("@class12", (char) 97, (char) 122);
        g.range("@class13", (char) 48, (char) 57);
        g.range("@class14", (char) 95, (char) 95);
        g.range("@class15", (char) 36, (char) 36);
        g.range("@class16", (char) 64, (char) 64);
        g.combine("@class17", "@class11", "@class12", "@class13", "@class14", "@class15", "@class16");
        g.range("@class18", (char) 32, (char) 32);
        g.range("@class19", (char) 9, (char) 9);
        g.range("@class20", (char) 10, (char) 10);
        g.range("@class21", (char) 11, (char) 11);
        g.range("@class22", (char) 12, (char) 12);
        g.range("@class23", (char) 13, (char) 13);
        g.combine("@class24", "@class18", "@class19", "@class20", "@class21", "@class22", "@class23");

        // Grammar Rules
        g.choose("@11", "field", "index");
        g.choose("@9", "@7", "@8");
        g.choose("command", "assignment", "printlns", "println", "vars", "locals", "location", "continue", "exit", "expression");
        g.choose("expression", "accessor", "value");
        g.choose("value", "variable", "integer");
        g.chr("@13", "@class0");
        g.chr("@14", "@class1");
        g.chr("@15", "@class2");
        g.chr("@16", "@class4");
        g.chr("@18", "@class10");
        g.chr("@19", "@class17");
        g.chr("SP", "@class24");
        g.repeat("@0", "command", 0, 2147483647);
        g.repeat("@12", "@11", 0, 2147483647);
        g.repeat("@17", "@16", 0, 2147483647);
        g.repeat("@20", "@19", 0, 2147483647);
        g.repeat("WS", "SP", 0, 2147483647);
        g.sequence("ID", "@18", "@20");
        g.sequence("accessor", "variable", "@12", "WS");
        g.sequence("assignee", "variable");
        g.sequence("assignment", "assignee", "WS", "@1", "WS", "expression", "WS");
        g.sequence("continue", "@9", "WS");
        g.sequence("exit", "@10", "WS");
        g.sequence("field", "@13", "WS", "ID", "WS");
        g.sequence("index", "@14", "WS", "value", "WS", "@15", "WS");
        g.sequence("integer", "@17", "WS");
        g.sequence("locals", "@5", "WS");
        g.sequence("location", "@6", "WS");
        g.sequence("println", "@3", "WS", "expression", "WS");
        g.sequence("printlns", "@2", "WS", "expression", "WS");
        g.sequence("root", "WS", "@0", "WS", "END");
        g.sequence("variable", "ID", "WS");
        g.sequence("vars", "@4", "WS");
        g.str("@1", "=");
        g.str("@10", "exit");
        g.str("@2", "printlns");
        g.str("@3", "println");
        g.str("@4", "vars");
        g.str("@5", "locals");
        g.str("@6", "location");
        g.str("@7", "continue");
        g.str("@8", "c");

        // Specify which rule is the root of the grammar.
        g.setRoot("root");

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
