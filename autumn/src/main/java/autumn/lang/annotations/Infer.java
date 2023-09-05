package autumn.lang.annotations;

import com.mackenziehigh.autumn.resources.Finished;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This type of annotation indicates that the return-types
 * of a function's invocations will be inferred.
 *
 * <p>
 * The compiler infers that the return-type is the type of the first actual argument.
 * </p>
 *
 * <p>
 * This feature of the language helps to deal with the lack of generics.
 * </p>
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
@Retention(RetentionPolicy.RUNTIME)
public @interface Infer
{
    // Pass
}
