/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autumn.lang.internals;

import java.util.List;

/**
 * An instance of this class is a delegate that refers to a function within a module.
 *
 * @author Mackenzie High
 */
public class ModuleDelegate
        extends AbstractDelegate
{
//    private final Module owner;
//
//    private
//
//
//    public ModuleDelegate(final Module owner,
//                          final String name,
//                          final List<Class> parameters,
//                          final int index)
//    {
//    }
    @Override
    public Class owner()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String name()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Class returnType()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Class> parameterTypes()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void invoke(ArgumentStack stack)
            throws Throwable
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
