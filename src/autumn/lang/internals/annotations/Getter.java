/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autumn.lang.internals.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This annotation indicates that a bytecode-level method is a property's getter.
 *
 * @author Mackenzie High
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Getter
{
}
