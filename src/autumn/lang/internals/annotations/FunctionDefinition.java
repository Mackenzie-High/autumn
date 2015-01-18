package autumn.lang.internals.annotations;

import high.mackenzie.autumn.resources.Finished;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This type of annotation indicates that a method was created from an function-definition.
 *
 * @author Mackenzie High
 */
@Finished("2015/01/17")
@Retention(RetentionPolicy.RUNTIME)
public @interface FunctionDefinition
{
    // Pass
}
