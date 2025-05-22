package autumn.lang.internals.annotations;

import com.mackenziehigh.autumn.resources.Finished;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This type of annotation indicates that a type was created from an module-definition.
 *
 * @author Mackenzie High
 */
@Finished("2015/01/17")
@Retention(RetentionPolicy.RUNTIME)
public @interface ModuleDefinition
{
    // Pass
}
