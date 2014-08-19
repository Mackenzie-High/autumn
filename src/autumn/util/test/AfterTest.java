package autumn.util.test;

import high.mackenzie.autumn.resources.Finished;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This type of annotation causes a function to be executed before a unit-test.
 *
 * @author Mackenzie High
 */
@Finished("2014/08/18")
@Retention(RetentionPolicy.RUNTIME)
public @interface AfterTest
{
    // Pass
}
