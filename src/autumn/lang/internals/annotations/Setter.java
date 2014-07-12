package autumn.lang.internals.annotations;

import high.mackenzie.autumn.resources.Finished;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This annotation indicates that a bytecode-level method is a property's setter.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
@Retention(RetentionPolicy.RUNTIME)
public @interface Setter
{
    // Pass
}
