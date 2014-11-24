package high.mackenzie.autumn.compiler.documentor;

import autumn.lang.compiler.ast.nodes.DocComment;
import autumn.lang.compiler.ast.nodes.DocCommentLine;
import autumn.lang.internals.annotations.Getter;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.io.Resources;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 * @author mackenzie
 */
public final class Utils
{
    private static final String AUTUMN_DOC = "http://www.mackenziehigh.me/autumn/javadoc/";

    private static final String JAVA_DOC = "https://docs.oracle.com/javase/7/docs/api/";

    public static String summaryOf(final DocComment comment)
    {
        if (comment == null)
        {
            return "";
        }
        else
        {
            return comment.getLines().get(0).getText();
        }
    }

    private static boolean isSpecialLine(final String line)
    {
        return line.startsWith("@element");
    }

    public static String commentOf(final DocComment lines)
    {
        if (lines == null)
        {
            return "";
        }

        final StringBuilder text = new StringBuilder();

        for (DocCommentLine line : lines.getLines())
        {
            final String line_text = removeExtraSpaces(line.getText());

            if (isSpecialLine(line_text) == false)
            {
                text.append(line_text);
            }
        }

        return text.toString();
    }

    public static String template(final String name)
    {
        try
        {
            final String path = "/high/mackenzie/autumn/resources/documentor/" + name;

            final URL url = Resources.getResource(Utils.class, path);

            final String text = Resources.toString(url, Charset.defaultCharset());

            return text;
        }
        catch (IOException ex)
        {
            /**
             * This should never happen.
             */
            throw new RuntimeException(ex);
        }
    }

    public static Class classOf(final ClassLoader loader,
                                final String name)
    {
        try
        {
            return Class.forName(name, false, loader);
        }
        catch (ClassNotFoundException ex)
        {
            /**
             * This should never happen.
             */
            throw new RuntimeException();
        }
    }

    public static Set<Class> supertypes(final Class klass)
    {
        final Set<Class> initial = Sets.newHashSet();

        initial.add(klass.getSuperclass() == null ? Object.class : klass.getSuperclass());
        initial.addAll(Arrays.asList(klass.getInterfaces()));

        final Set<Class> result = Sets.newHashSet();

        for (Class supertype : initial)
        {
            result.add(supertype);

            if (supertype.equals(Object.class) == false)
            {
                result.addAll(supertypes(supertype));
            }
        }

        return result;
    }

    public static String listofSupertypes(final Class klass)
    {
        final TreeMap<String, Class> sorted = Maps.newTreeMap();

        for (Class type : supertypes(klass))
        {
            sorted.put(type.getName(), type);
        }

        final StringBuilder html = new StringBuilder();

        html.append("<ul>");

        for (Entry<String, Class> entry : sorted.entrySet())
        {
            final String link = linkTo(entry.getValue(), false);

            html.append("<li>").append(link).append("</li>");
        }

        html.append("</ul>");

        return html.toString();
    }

    /**
     * This method creates a link to the documentation for a type.
     *
     * @param type is the type that the link will refer to.
     * @param shorten is false, if the type's full name must be displayed.
     * @return the html link.
     */
    public static String linkTo(final Class type,
                                boolean shorten)
    {
        // TODO: what if the type is from a third-party library?

        final String name = type.getName();

        String href;

        if (type.isPrimitive())
        {
            return type.getSimpleName();
        }
        else if (type.isArray())
        {
            return type.getSimpleName(); // TODO
        }
        else if (name.startsWith("autumn."))
        {
            href = AUTUMN_DOC + name.replace('.', '/') + ".html";
        }
        else if (name.startsWith("java.") || name.startsWith("javax."))
        {
            href = JAVA_DOC + name.replace('.', '/') + ".html";
        }
        else
        {
            href = name.replace('.', '-') + ".html";
        }

        final String alias = shorten ? type.getSimpleName() : name;

        final String link = "<a href=\"" + href + "\">" + alias + "</a>";

        return link;
    }

    public static SortedMap<String, String> descriptionsOfElements(final DocComment comment)
    {
        final SortedMap<String, String> map = Maps.newTreeMap();

        if (comment == null)
        {
            return map;
        }

        for (DocCommentLine line : comment.getLines())
        {
            final String text = removeExtraSpaces(line.getText());

            if (text.startsWith("@element") == false)
            {
                continue;
            }

            final String[] parts = text.split(" ");

            final String element = parts[1];

            final String description = text.replace("@element", "Element");

            map.put(element, description);
        }

        return map;
    }

    private static String removeExtraSpaces(final String input)
    {
        String text = input;

        while (input.contains("  "))
        {
            text = text.replace("  ", " ");
        }

        text = text.trim();

        return text;
    }

    public static String createTableOfElements(final DocComment comment,
                                               final Class type)
    {
        if (comment == null)
        {
            return "";
        }

        final SortedMap<String, String> descriptions = Maps.newTreeMap();

        final SortedMap<String, Class> types = Maps.newTreeMap();

        findElements(type, descriptions, types);

        for (Entry<String, String> entry : Utils.descriptionsOfElements(comment).entrySet())
        {
            if (types.containsKey(entry.getKey()))
            {
                descriptions.put(entry.getKey(), entry.getValue());
            }
        }

        final StringBuilder table = new StringBuilder();

        table.append("<table>");

        table.append("<tr>").append("<td><b>Name</b></td>").append("<td><b>Type</b></td>").append("<td><b>Description</b></td>").append("</tr>");

        for (String element : descriptions.keySet())
        {
            final String description = descriptions.get(element);

            createTableOfElementsEntry(table, element, types.get(element), description);
        }

        table.append("</table>");

        return table.toString();
    }

    public static void createTableOfElementsEntry(final StringBuilder table,
                                                  final String element,
                                                  final Class type,
                                                  final String description)
    {
        final String about = description == null ? "" : description;

        final String link = Utils.linkTo(type, true);

        table.append("<tr>")
                .append("<td>").append(element).append("</td>")
                .append("<td>").append(link).append("</td>")
                .append("<td>").append(about).append("</td>")
                .append("</tr> ");
    }

    private static void findElements(final Class type,
                                     final Map<String, String> descriptions,
                                     final Map<String, Class> types)
    {
        for (Field field : type.getFields())
        {
            if (field.isEnumConstant())
            {
                descriptions.put(field.getName(), "");
                types.put(field.getName(), field.getType());
            }
        }

        for (java.lang.reflect.Method method : type.getMethods())
        {
            if (method.isAnnotationPresent(Getter.class))
            {
                descriptions.put(method.getName(), "");
                types.put(method.getName(), method.getReturnType());
            }
        }
    }
}
