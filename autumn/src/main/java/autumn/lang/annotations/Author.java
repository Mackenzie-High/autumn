package autumn.lang.annotations;

import com.mackenziehigh.autumn.resources.Finished;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This type of annotation stores the names of the authors of the entity that it is applied to.
 *
 * @author Mackenzie High
 */
@Finished("2014/12/20")
@Retention(RetentionPolicy.RUNTIME)
public @interface Author
{
    /**
     * These are the names of the authors.
     *
     * @return the names of the authors.
     */
    public String[] value();
}
