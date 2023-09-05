package autumnspecification;

import autumn.lang.compiler.errors.ErrorCode;

/**
 * An instance of this class stores information regarding a static-check for a
 * construct.
 *
 * @author Mackenzie
 */
public final class StaticCheck
{

    /**
     * This is the error-code that identifies the error.
     */
    public final ErrorCode code;

    /**
     * This is a human-readable description of the error.
     */
    public final String description;

    /**
     * This is the name of the test-file that provides an example.
     * This is null, if no example was provided.
     */
    public final String example;

    /**
     * Sole Constructor.
     *
     * @param code
     * @param description
     * @param example
     */
    public StaticCheck (ErrorCode code,
                        String description,
                        String example)
    {
        this.code = code;
        this.description = description;
        this.example = example;
    }
}
