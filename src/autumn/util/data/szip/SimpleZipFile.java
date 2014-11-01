package autumn.util.data.szip;

import com.google.common.collect.Maps;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * An instance of this class simplifies the writing and reading of small zip files.
 *
 * <p>
 * Small zip files are useful as container files for project information.
 * </p>
 *
 * @author Mackenzie High
 */
public class SimpleZipFile
{
    private final Map<String, String> manifest = Maps.newTreeMap();

    private final Map<String, byte[]> entries = Maps.newTreeMap();

    /**
     * This method sets an entry in the zip file.
     *
     * <p>
     * The entry will be added, if it does not already exist.
     * </p>
     *
     * @param path is the name of the entry within the zip file.
     * @param value is the new content of the entry.
     */
    public void setEntry(final String path,
                         final CharSequence value)
    {
    }

    /**
     * This method sets an entry in the zip file.
     *
     * <p>
     * The entry will be added, if it does not already exist.
     * </p>
     *
     * @param path is the name of the entry within the zip file.
     * @param value is the new content of the entry.
     * @param charset is the encoding of the value.
     */
    public void setEntry(final String path,
                         final CharSequence value,
                         final Charset charset)
    {
    }

    /**
     * This method sets an entry in the zip file.
     *
     * <p>
     * The entry will be added, if it does not already exist.
     * </p>
     *
     * @param path is the name of the entry within the zip file.
     * @param value is the new content of the entry.
     */
    public void setEntry(final String path,
                         final byte[] value)
    {
    }

    public void write(final OutputStream out)
    {
    }

    public static SimpleZipFile read(final InputStream in)
    {
        return null;
    }
}
