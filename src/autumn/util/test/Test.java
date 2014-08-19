package autumn.util.test;

import high.mackenzie.autumn.resources.Finished;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This type of annotation indicates that a function is a unit-test.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
@Retention(RetentionPolicy.RUNTIME)
public @interface Test
{
    // Pass
}
