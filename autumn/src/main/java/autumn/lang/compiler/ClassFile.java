package autumn.lang.compiler;

import com.google.common.base.Preconditions;
import com.mackenziehigh.autumn.resources.Finished;
import java.util.Arrays;

/**
 * An instance of this object stores the bytecode of a single class-file.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public final class ClassFile
{
    /**
     * This is the name of the class whose bytecode is stored herein, as it appears in source-code.
     */
    private final String source_name;

    /**
     * This is the bytecode that can be written to an actual class-file.
     */
    private final byte[] bytecode;

    /**
     * Sole Constructor.
     *
     * @param source_name is the fully-qualified name of the class.
     * @param bytecode is the bytecode of the class.
     */
    public ClassFile(final String source_name,
                     final byte[] bytecode)
    {
        Preconditions.checkNotNull(source_name);
        Preconditions.checkNotNull(bytecode);

        final boolean maybe_descriptor = source_name.contains(";");
        final boolean maybe_internal_name = source_name.contains("/");
        final boolean illegal = maybe_descriptor || maybe_internal_name;

        Preconditions.checkArgument(!illegal, "An unexpected class name was encountered.");

        this.source_name = source_name;
        this.bytecode = Arrays.copyOf(bytecode, bytecode.length);
    }

    /**
     * This method retrieves the fully-qualified name of the class.
     *
     * @return the name of the class contained herein.
     */
    public String name()
    {
        final String result = source_name.replace("/", ".");

        return result;
    }

    /**
     * This method returns the bytecode stored in this class-file.
     *
     * @return a copy of the bytecode that is stored herein.
     */
    public byte[] bytecode()
    {
        return Arrays.copyOf(bytecode, bytecode.length);
    }
}
