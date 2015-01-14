package autumnspecification;

import high.mackenzie.autumn.util.json.JsonEncoder;
import static autumnspecification.JSONBuilder.*;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mackenzie
 */
public final class ExamplePage
{
    public static List<ExamplePage> instances = Lists.newLinkedList();

    public String name;

    public String description = "";

    public URL code;

    public URL stdout;

    public ExamplePage()
    {
        instances.add(this);
    }

    /**
     * This method writes the page's JSON file to the file-system.
     */
    public void write()
            throws IOException
    {
        final Map<String, Object> page = Maps.newLinkedHashMap();

        /**
         * Generate the page.
         */
        page.put("description", expand(description));
        page.put("code-url", code);
        page.put("stdout-url", stdout);

        /**
         * Write the JSON file.
         */
        final String content = (new JsonEncoder(false)).encode(page);
        final File file = new File(JSONBuilder.SPECIFICATION, this.name + ".json");
        Files.write(content, file, Charset.defaultCharset());
    }
}
