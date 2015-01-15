package autumnspecification;

import autumn.util.F;
import static autumnspecification.JSONBuilder.EXAMPLES;
import static autumnspecification.JSONBuilder.expand;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import high.mackenzie.autumn.util.json.JsonEncoder;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * An instance of this class represents one of the functions in the F class of the Autumn standard library.
 *
 * @author mackenzie
 */
public final class Function
        implements Comparable<Function>
{
    /**
     * These constants are used to classify functions.
     *
     * Note; Do not change the case of these constants, because it simplifies the website's Javascript.
     */
    public static enum Group
    {
        Standard_IO,
        File_IO,
        Math,
        Statistics,
        Strings,
        Bitwise,
        Data_Structures,
        Conversions,
        Iterables,
        Higher_Order,
        Operators,
        Functors,
        NOS,
        Exceptions,
        Reflection,
        Type_Creation,
        Threading,
        Records;

        @Override
        public String toString()
        {
            return this.name().replace("_", " ");
        }
    }

    /**
     * An instance of this class represents a formal parameter.
     */
    public static final class Parameter
    {
        public String about;

        public String name;

        public Class type;

        @Override
        public String toString()
        {
            return name + " : " + JSONBuilder.link(type);
        }
    }

    /**
     * An instance of this class describes the return-value of the function.
     */
    public static final class Return
    {
        public String about;

        public Class type;

        @Override
        public String toString()
        {
            return JSONBuilder.link(type);
        }
    }

    public static final class Problem
    {
        public Class type;

        public String reason;

        public String check = null;

        @Override
        public String toString()
        {
            return JSONBuilder.link(type);
        }
    }

    /**
     * This is the classification that the function belongs to.
     */
    public Group group;

    /**
     * This is a one sentence summary of the function's behavior.
     */
    public String summary = "";

    /**
     * This is the name of the function.
     */
    public String name;

    /**
     * These are the formal parameters of the function.
     */
    public final List<Parameter> formals = Lists.newLinkedList();

    /**
     * This is a description of the result returned by the function.
     */
    public Return returns;

    /**
     * This is true, if the return type is inferred from the first parameter.
     */
    public boolean infer = false;

    /**
     * This is effectively the throws-clause of the function.
     */
    public final List<Problem> problems = Lists.newLinkedList();

    /**
     * These are indented details regarding the function.
     */
    public final List<Entry<Integer, String>> details = Lists.newLinkedList();

    /**
     * These are the indexes of the examples.
     */
    public final List<Entry<Integer, Integer>> examples = Lists.newLinkedList();

    /**
     * This is the single line of code to execute, when the function is invoked.
     */
    public String body = "";

    /**
     * This method adds a formal parameter to the function.
     *
     * @param name
     * @param type
     * @param about
     */
    public void formal(final String name,
                       final Class type,
                       final String about)
    {
        final Parameter param = new Parameter();

        param.about = about;
        param.name = name;
        param.type = type;

        formals.add(param);
    }

    /**
     * This method sets the description of the returned result.
     *
     * @param type
     * @param about
     */
    public void returns(final Class type,
                        final String about)
    {
        final Return r = new Return();

        r.about = about;
        r.type = type;

        this.returns = r;
    }

    /**
     * This method adds an entry to the function's throws-clause.
     *
     * @param type
     * @param reason
     */
    public void raise(final Class type,
                      final String reason)
    {
        final Problem p = new Problem();

        p.type = type;
        p.reason = reason;

        problems.add(p);
    }

    /**
     * This method adds an entry to the function's throws-clause.
     *
     * @param type
     * @param reason
     * @param check
     */
    public void raise(final Class type,
                      final String reason,
                      final String check)
    {
        final Problem p = new Problem();

        p.type = type;
        p.reason = reason;
        p.check = check;

        problems.add(p);
    }

    /**
     * This method adds a detail to the list of details.
     *
     * @param indent
     * @param line
     */
    public void detail(final int indent,
                       final String line)
    {
        details.add(new SimpleImmutableEntry(indent, line));
    }

    /**
     * This method adds an example to the list of examples.
     *
     * @param number is the number of the example (1 to 5).
     * @param example is the index of the example in the examples folder.
     */
    public void example(final int number,
                        final int example)
    {
        examples.add(new SimpleImmutableEntry(number, example));
    }

    /**
     * This method adds a detail that indicates that the function simply invokes a JVM method.
     *
     * @param owner
     * @param name
     * @param formals
     */
    public void redirect(final Class owner,
                         final String name,
                         final Class... formals)
    {
        final String method = owner.getSimpleName() + "." + name + F.str(Arrays.asList(formals), "(", ", ", ")");

        detail(0, "This function simply invokes " + method + ".");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(final Function o)
    {
        return toString().compareTo(o.toString());
    }

    /**
     * This method creates the signatures HTML string.
     */
    public String signature()
    {
        return name + " " + F.str(formals, "(", ", ", ")") + " : " + returns.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        final List<String> params = Lists.newLinkedList();

        for (Parameter formal : formals)
        {
            params.add(formal.name + " : " + formal.type.getSimpleName());
        }

        return name + " " + F.str(params, "(", ", ", ")") + " : " + returns.type.getSimpleName();
    }

    /**
     * This method writes the JSON file that stores
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
         * Add the signature.
         */
        page.put("signature", signature());

        /**
         * Add the formals.
         */
        list = Lists.newArrayList();

        for (Parameter formal : formals)
        {
            list.add(Lists.newArrayList(formal.name, formal.type.toString(), expand(formal.about)));
        }

        page.put("formals", list);

        /**
         * Add the return-type.
         */
        page.put("returns", Lists.newArrayList(returns.toString(), expand(returns.about)));

        /**
         * Add the throws-clause.
         */
        list = Lists.newArrayList();

        for (Problem problem : problems)
        {
            list.add(Lists.newArrayList(problem.toString(), expand(problem.reason)));
        }

        page.put("throws", list);

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
        final File file = new File(JSONBuilder.FUNCTIONS, this.toString() + ".json");
        Files.write(content, file, Charset.defaultCharset());
    }

    /**
     * This method generates the Java code representation of the function.
     *
     * @return the generated Java code that will be placed into the autumn.util.F class.
     */
    public String generate()
    {
        if (body == null)
        {
            return "";
        }

        final StringBuilder checks = new StringBuilder();

        final StringBuilder throws_clause = new StringBuilder();

        for (Problem problem : problems)
        {
            throws_clause.append(nameOf(problem.type)).append(", ");

            if (problem.check != null)
            {
                checks.append("        if(").append(problem.check).append(") { throw new ").append(problem.type.getName()).append("(); }\n");
            }
        }

        final String raise = throws_clause.length() == 0
                ? ""
                : "throws " + throws_clause.substring(0, throws_clause.length() - 2);

        final StringBuilder parameters = new StringBuilder();

        for (Parameter p : formals)
        {
            parameters.append("final ")
                    .append(nameOf(p.type))
                    .append(" ")
                    .append(p.name)
                    .append(", ");
        }

        final String params = parameters.length() == 0
                ? ""
                : parameters.substring(0, parameters.length() - 2);

        final StringBuilder function = new StringBuilder();

        function.append("    ").append(infer ? "@Infer\n" : "\n")
                .append("    ").append("public static ").append(nameOf(returns.type)).append(" ").append(name).append(" (").append(params).append(") ").append(raise).append("\n")
                .append("    ").append("{").append("\n\n")
                .append(checks)
                .append("    ").append("    ").append(body).append("\n")
                .append("    ").append("}").append("\n");

        return function.toString();
    }

    public static String nameOf(final Class klass)
    {
        if (klass.isArray())
        {
            return nameOf(klass.getComponentType()) + "[]";
        }
        else
        {
            return klass.getName();
        }
    }
}
