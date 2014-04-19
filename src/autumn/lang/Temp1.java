/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autumn.lang;

import autumn.lang.internals.annotations.Getter;
import autumn.lang.internals.annotations.Setter;

/**
 *
 * @author mackenzie
 */
public interface Temp1
        extends Prototype
{
    public Object moo(String x,
                      int y,
                      long z);

    public Object moo(int x);

    @Setter
    public Temp1 boo(Object x);

    @Getter
    public Object boo();
}
