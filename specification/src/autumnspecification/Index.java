package autumnspecification;

import high.mackenzie.autumn.util.json.JsonEncoder;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.List;
import java.util.Map.Entry;

/**
 * This class represents the site's main index page.
 *
 * @author Mackenzie High
 */
public final class Index
{
    /**
     * These are the lines in the index.
     * An entry's value is the indentation level of line.
     * An entry's is the HTML of the line itself.
     */
    public static final List<Entry<Integer, String>> entries = Lists.newLinkedList();

    /**
     * This is the indentation level to apply to newly added lines.
     */
    public static int indent = 0;

    /**
     * This method adds a line to the index.
     *
     * @param html is the HTML representation of the line.
     */
    public static void add(String html)
    {
        html = html.replace(" ", "%20");

        entries.add(new SimpleImmutableEntry(indent, html));
    }

    /**
     * This method adds a line to to the index, when the line is a link.
     *
     * @param text is the text to display on the line.
     * @param href is the link.
     */
    public static void add(final String text,
                           final String href)
    {
        final String link = String.format("<a href=\\\"%s\\\">%s</a>", href, text);

        add(link);
    }

    /**
     * This method adds a line to the index, when the line is a link to a construct-page.
     *
     * @param c is the construct that related to the construct-page.
     */
    public static void add(final Construct c)
    {
        add(c.name, "ConstructPage.html?construct=" + c.name);
    }

    /**
     * This method adds a line to the index, when the line is a link to a markdown-page.
     *
     * @param c is the construct that related to the markdown-page.
     */
    public static void add(final Page c)
    {
        add(c.name, "TextPage.html?page=" + c.name);
    }

    /**
     * This method writes the index JSON file to the file-system.
     */
    public static void write()
            throws IOException
    {
        final List<List> index = Lists.newArrayList();

        /**
         * Add the entries to the index.
         */
        for (Entry<Integer, String> entry : entries)
        {
            index.add(Lists.newArrayList(entry.getKey(), entry.getValue()));
        }

        /**
         * Write the JSON file.
         */
        final String content = (new JsonEncoder(false)).encode(index);
        final File file = new File(JSONBuilder.SPECIFICATION, "index.json");
        Files.write(content, file, Charset.defaultCharset());
    }
}
