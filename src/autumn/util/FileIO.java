package autumn.util;

import com.google.common.base.Preconditions;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * This class provides a few static utility methods for dealing with files.
 *
 * <p>
 * <b>Warning: This class is still under development.</b>
 * </p>
 *
 * @author Mackenzie High
 */
public final class FileIO
{
    /**
     * Sole Constructor.
     */
    private FileIO()
    {
        // Pass, because this is merely a static utility class.
    }

    /**
     * This method creates an iterable whose iterator can iterate over all the files in a directory.
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
    public static Iterable<File> filesOf(final File root,
                                         final boolean recur)
    {
        // TODO: buggy

        Preconditions.checkNotNull(root);
        Preconditions.checkArgument(root.isDirectory(), "Expected a Directory: " + root);

        // This stack stores fiels and directories that have not yet been returned.
        final Stack<File> stack = new Stack();

        // Push the files that are in the root directory.
        for (File file : root.listFiles())
        {
            stack.push(file);
        }

        /**
         * Create the iterable, which basically performs a preorder depth-first transversal.
         */
        return new Iterable<File>()
        {
            @Override
            public Iterator<File> iterator()
            {
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
        };
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

    public static List<String> readLines(final File file)
            throws IOException
    {
        return com.google.common.io.Files.readLines(file, Charset.defaultCharset());
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
        return readText(file, Charset.defaultCharset());
    }

    public static String readText(final File file,
                                  final Charset charset)
            throws IOException
    {
        return com.google.common.io.Files.toString(file, charset);
    }

    public static byte[] readBytes(final File file)
            throws IOException
    {
        return null;
    }
}
