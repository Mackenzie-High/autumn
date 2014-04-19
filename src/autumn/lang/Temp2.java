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
public interface Temp2
        extends Temp1
{
    @Override
    public String moo(String x,
                      int y,
                      long z);

    @Setter
    public Temp2 boo(String x);

    @Getter
    public String boo();

    @Setter
    public Temp2 hoo(String x);

    @Getter
    public String hoo();
}
