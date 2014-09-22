package autumn.util.io;

import com.google.common.collect.Maps;
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

    /**
     * This method adds an entry to the zip-file's manifest.
     *
     * @param key is the name of the entry in the zip-file's manifest.
     * @param value is the new value of the entry in the zip-file's manifest.
     */
    public void setManifestEntry(final String key,
                                 final String value)
    {
    }

    public String getManifestEntry(final String key)
    {
        return null;
    }
}
