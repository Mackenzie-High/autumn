package autumn.util;

import autumn.util.functors.FileProcessor;
import com.google.common.base.Preconditions;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * This class provides static utility methods for working with files.
 *
 * @author Mackenzie High
 */
public final class Files
{
    /**
     * This method creates an iterator that can iterate over all the files in a directory.
     *
     * <p>
     * If recur is true, sub-directories will be returned before their contents.
     * </p>
     *
     * @param root is the directory to iterate over.
     * @param recur is true, if the sub-directories transversed.
     * @return the aforedescribed iterator.
     * @throws IllegalArgumentException if root is not a directory.
     */
    public static Iterator<File> iterFiles(final File root,
                                           final boolean recur)
    {
        Preconditions.checkNotNull(root);
        Preconditions.checkArgument(root.isDirectory(), "Expected a Directory: " + root);

        // This stack stores fiels and directories that have not yet been returned.
        final Stack<File> stack = new Stack();

        // The root directory will be the first file returned.
        stack.push(root);

        /**
         * Create the iterator, which basically performs a preorder depth-first transversal.
         */
        return new Iterator<File>()
        {
            @Override
            public boolean hasNext()
            {
                return !stack.isEmpty();
            }

            @Override
            public File next()
            {
                // This is required by the iterator interface.
                if (stack.isEmpty())
                {
                    throw new NoSuchElementException("Out of Files");
                }

                // Get the next file to return.
                final File next = stack.pop();

                if (recur && next.isDirectory())
                {
                    // Push all the files that are directly contained in the sub-directory.
                    for (File child : next.listFiles())
                    {
                        stack.push(child);
                    }
                }

                return next;
            }

            @Override
            public void remove()
            {
                throw new UnsupportedOperationException("Not Supported");
            }
        };
    }

    public static void processFiles(final Iterable<File> files,
                                    final FileProcessor functor)
            throws Throwable
    {
        for (File file : F.iter(files))
        {
            functor.invoke(file);
        }
    }

    /**
     * This method sets the contents of a text file.
     *
     * <p>
     * This method uses the default encoding.
     * </p>
     *
     * @param file is the path to the text file.
     * @param data is value to assign to the text file.
     * @throws IOException if something goes wrong.
     */
    public static void writeText(final File file,
                                 final CharSequence data)
            throws IOException
    {
        writeText(file, data, Charset.defaultCharset());
    }

    /**
     * This method sets the contents of a text file.
     *
     * @param file is the path to the text file.
     * @param data is value to assign to the text file.
     * @param charset is the encoding to use.
     * @throws IOException if something goes wrong.
     */
    public static void writeText(final File file,
                                 final CharSequence data,
                                 final Charset charset)
            throws IOException
    {
        com.google.common.io.Files.write(data, file, charset);
    }

    /**
     * This method sets the contents of a text file.
     *
     * @param file is the path to the text file.
     * @param lines are the lines of data to assign to the text file.
     * @param charset is the encoding to use.
     * @throws IOException if something goes wrong.
     */
    public static void writeLines(final File file,
                                  final Iterable<? extends CharSequence> lines,
                                  final Charset charset)
            throws IOException
    {
    }

    /**
     * This method sets the contents of a binary file.
     *
     * @param file is the path to the binary file.
     * @param data is value to assign to the binary file.
     * @throws IOException if something goes wrong.
     */
    public static void writeBytes(final File file,
                                  final byte[] data)
            throws IOException
    {
        com.google.common.io.Files.write(data, file);
    }

    public static String readLines(final File file)
            throws IOException
    {
        return null;
    }

    public static String readLines(final File file,
                                   final Charset charset)
            throws IOException
    {
        return null;
    }

    public static String readText(final File file)
            throws IOException
    {
        return null;
    }

    public static String readText(final File file,
                                  final Charset charset)
            throws IOException
    {
        return null;
    }

    public static byte[] readBytes(final File file)
            throws IOException
    {
        return null;
    }

    /**
     * This map writes a zip file.
     *
     * <p>
     * Each entry in the map describes an entry in the zip-file.
     * Each key is the path to the entry within the zip-file.
     * Each value is the content to place in the zip-file entry.
     * </p>
     *
     * <p>
     * This method is intended for writing small zip-files.
     * Large zip files should be created using the Java zip-file API.
     * </p>
     *
     * @param file is the output path.
     * @param map describes the contents of the zip-file.
     * @throws IOException if something goes wrong.
     */
    public static void writeZipMap(final File file,
                                   final Map<String, byte[]> map)
            throws IOException
    {
    }

    /**
     * This method reads a zip file.
     *
     * <p>
     * Each entry in the returned map describes an entry in the zip-file.
     * Each key is the path to the entry within the zip-file.
     * Each value is the content to place in the zip-file entry.
     * </p>
     *
     * <p>
     * This method is intended for reading small zip-files.
     * Large zip files should be created using the Java zip-file API.
     * </p>
     *
     * @param file is the path to the zip-file to read.
     * @return a map that describes the zip-file.
     * @throws IOException if something goes wrong.
     */
    public static Map<String, byte[]> readZipMap(final File file)
            throws IOException
    {
        return null;
    }
}