/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autumn.lang.exceptions;

import java.io.File;

/**
 *
 * @author mackenzie
 */
public class AssertionFailedException
        extends RuntimeException
{
    private final File file;

    private final int line;

    public AssertionFailedException(final String file,
                                    final int line,
                                    final String message)
    {
        super(message);

        this.file = new File(file);
        this.line = line;
    }

    public AssertionFailedException(final String file,
                                    final int line)
    {
        super();

        this.file = new File(file);
        this.line = line;
    }

    public File file()
    {
        return file;
    }

    public int line()
    {
        return line;
    }
}
