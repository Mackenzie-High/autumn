package autumnspecification;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import org.pegdown.PegDownProcessor;

/**
 * An instance of this class is page that contains Markdown code.
 *
 * @author Mackenzie High
 */
public class Page
{
    /**
     * These are the instances of this class.
     */
    public static final List<Page> instances = Lists.newArrayList();

    /**
     * This is the name of the page.
     */
    public String name;

    /**
     * This is the path to the file that contains the markdown code.
     */
    public File markdown;

    /**
     * Sole Constructor.
     */
    private Page()
    {
    }

    /**
     * This method creates a new instance of this class and adds it to the list of instances.
     *
     * @return the new instance.
     */
    public static Page newInstance()
    {
        final Page instance = new Page();

        instances.add(instance);

        return instance;
    }

    /**
     * This method gets the low-level name of this page, which is used in links.
     *
     * @return the low-level name of this page.
     */
    public String pageName()
    {
        return "P" + Strings.padStart("" + instances.indexOf(this), 4, '0');
    }

    /**
     * This method searches for a page based on its name.
     *
     * @param name is the name of the page to search for.
     * @return the found page.
     * @throws RuntimeException if the page was not found.
     */
    public static Page fromName(final String name)
    {
        for (Page p : instances)
        {
            if (name.equals(p.name))
            {
                return p;
            }
        }

        throw new RuntimeException("No Such Page: " + name);
    }

    /**
     * This method executes the associated markdown file and returns the resulting HTML.
     *
     * @return the HTML representation of this page.
     */
    public String html()
    {
        try
        {
            // Read the file.
            String text = Files.toString(new File(JSONBuilder.MARKDOWN + markdown.getPath()),
                                         Charset.defaultCharset());

            text = JSONBuilder.expandlinks(text);

            text = text.replace("\\\"", "\"");

            // Execute the file.
            PegDownProcessor p = new PegDownProcessor();

            text = p.markdownToHtml(text);

            // Escape newlines and quotes.
            text = text.replace("\n", "%0A");
            text = text.replace("\"", "%22");

            return text;
        }
        catch (IOException ex)
        {
            throw new RuntimeException(ex);
        }
    }

    /**
     * This method writes the page's html file to the file-system.
     */
    public void write()
            throws IOException
    {
        /**
         * Write the JSON file.
         */
        final String content = this.html().toString();
        final File file = new File(JSONBuilder.SPECIFICATION, this.name + ".html");
        Files.write(content, file, Charset.defaultCharset());
    }
}
