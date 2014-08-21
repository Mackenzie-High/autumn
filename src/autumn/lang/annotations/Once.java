package autumn.lang.annotations;

import high.mackenzie.autumn.resources.Finished;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This annotation causes a function to only only be evaluated once.
 *
 * <p>
 * The first time the function is invoked, it will be executed and its result will be stored.
 * Any subsequent invocation of the function will simply return the stored value.
 * </p>
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
@Retention(RetentionPolicy.RUNTIME)
public @interface Once
{
    // Pass
}
