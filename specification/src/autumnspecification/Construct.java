package autumnspecification;

import autumn.lang.compiler.errors.ErrorCode;
import static autumnspecification.JSONBuilder.*;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import high.mackenzie.autumn.util.json.JsonEncoder;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * An instance of this class stores the description of an Autumn language construct.
 *
 * <p>
 * In short, an instance of this class describes a "Construct Page".
 * </p>
 *
 * @author Mackenzie High
 */
public final class Construct
{
    /**
     * These are the instances of this class.
     */
    public static final List<Construct> instances = Lists.newArrayList();

    /**
     * This is the name of the construct.
     */
    public String name;

    /**
     * This is the class-object of the Abstract-Syntax-Tree node class.
     */
    public Class klass;

    /**
     * This is a short summary describing the construct.
     */
    public String summary;

    /**
     * This field describes the syntax description.
     * Each line of syntax is represented by a single entry.
     * The key is the indentation level.
     * The value is the string of text to display on the line.
     */
    public final List<Entry<Integer, String>> syntax = Lists.newArrayList();

    /**
     * These are the "Details" related to the construct.
     * An entry's key is the indentation level of the detail in the list of details.
     * An entry's value is the detail itself.
     */
    public final List<Entry<Integer, String>> details = Lists.newArrayList();

    /**
     * These are the "Static Checks" related to the construct.
     */
    public final List<StaticCheck> checks = Lists.newArrayList();

    /**
     * These are the "Examples" associated with the construct.
     * An entry's key is the index of the example as displayed on the page.
     * An entry's value is the index of the example in the examples folder.
     * On this computer, I keep the examples folder with the web-site's source code.
     * At least, that is what I was doing, when I wrote this. ;)
     */
    public final List<Entry<Integer, Integer>> examples = Lists.newArrayList();

    /**
     * Sole Constructor.
     */
    private Construct()
    {
    }

    /**
     * This method creates a new "Construct Page" and adds it to the list of such pages.
     *
     * @return the new instance.
     */
    public static Construct newInstance()
    {
        final Construct instance = new Construct();

        instances.add(instance);

        return instance;
    }

    /**
     * This method returns the low-level name of this page.
     *
     * @return the name of this page that is used in links.
     */
    public String pageName()
    {
        return "C" + Strings.padStart("" + instances.indexOf(this), 4, '0');
    }

    /**
     * This method adds a line of syntax to the syntax box.
     *
     * @param indent is the indentation level of the line.
     * @param line is the line itself.
     */
    public void addSyntax(final int indent,
                          final String line)
    {
        syntax.add(new HashMap.SimpleEntry(indent, line));
    }

    /**
     * This method adds a horizontal-rule to the syntax box.
     */
    public void addSyntaxHR()
    {
        this.addSyntax(0, "<hr class=\\\"syntax-hr\\\">");
    }

    /**
     * This method adds a detail to the list of details.
     *
     * @param indent is the indentation level of the detail.
     * @param text is the detail itself.
     */
    public void addDetail(final int indent,
                          final String text)
    {
        details.add(new HashMap.SimpleEntry(indent, text));
    }

    /**
     * This method adds a static-check to the list of static-checks.
     *
     * @param code is the error-code that will be issued, when the static-check fails.
     * @param text is a description of the static-check.
     */
    public void addCheck(final ErrorCode code,
                         final String text)
    {
        checks.add(new StaticCheck(code, text, null));
    }

    /**
     * This method adds a static-check to the list of static-checks.
     *
     * @param code is the error-code that will be issued, when the static-check fails.
     * @param text is a description of the static-check.
     * @param example is the index of the type-check test-file that provides the example.
     */
    public void addCheck(final ErrorCode code,
                         final String text,
                         final int example)
    {
        final String file = "T" + Strings.padStart("" + example, 4, '0');

        checks.add(new StaticCheck(code, text, file));
    }

    /**
     * This method adds an example to the construct-page.
     *
     * @param index is the index of the examples on the page.
     * @param example is the index of the example in the "examples" folder.
     */
    public void addExample(final int index,
                           final int example)
    {
        this.examples.add(new HashMap.SimpleEntry(index, example));
    }

    /**
     * This method writes the construct page to the file-system.
     */
    public void write()
            throws IOException
    {
        final Map<String, Object> page = Maps.newLinkedHashMap();

        List list;

        /**
         * Add the summary.
         */
        page.put("summary", expand(summary));

        /**
         * Add the AST class.
         */
        page.put("ast", klass.getName());

        /**
         * Add the syntax.
         */
        list = Lists.newArrayList();

        for (Entry<Integer, String> line : syntax)
        {
            list.add(Lists.newArrayList(line.getKey(), expand(line.getValue())));
        }

        page.put("syntax", list);

        /**
         * Add the details.
         */
        list = Lists.newArrayList();

        for (Entry<Integer, String> detail : details)
        {
            list.add(Lists.newArrayList(detail.getKey(), expand(detail.getValue())));
        }

        page.put("details", list);

        /**
         * Add the static-checks.
         */
        list = Lists.newArrayList();

        for (StaticCheck check : checks)
        {
            list.add(Lists.newArrayList(check.code.name(), expand(check.description), "" + check.example));
        }

        page.put("static-checks", list);

        /**
         * Add the examples.
         */
        for (Entry<Integer, Integer> example : examples)
        {
            final String path = EXAMPLES + "E" + Strings.padStart(example.getValue().toString(), 4, '0') + '/';

            final File codefile = new File(path + "/project/src/Main.leaf");

            final File outfile = new File(path + "stdout.txt");

            final String code = expand(Files.toString(codefile, Charset.defaultCharset()));

            final String stdout = expand(Files.toString(outfile, Charset.defaultCharset()));

            page.put("example-" + example.getKey(), Lists.newArrayList(code, stdout));
        }

        /**
         * Write the JSON file.
         */
        final String content = (new JsonEncoder()).encode(page);
        final File file = new File(JSONBuilder.SPECIFICATION, this.name + ".json");
        Files.write(content, file, Charset.defaultCharset());
    }
}
