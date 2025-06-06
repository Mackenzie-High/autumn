package autumn.lang.internals.annotations;

import com.mackenziehigh.autumn.resources.Finished;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This annotation indicates that a bytecode-level method is a getter.
 *
 * <p>
 * This annotation is used inside of structs, tuples, and designs.
 * </p>
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
@Retention(RetentionPolicy.RUNTIME)
public @interface Getter
{
    // Pass
}
