/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.lang.compiler.typesystem;

import high.mackenzie.autumn.lang.compiler.typesystem.design.IAnnotation;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IConstructor;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IDeclaredType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.ITypeFactory;
import java.lang.reflect.Constructor;
import java.util.Collection;

/**
 *
 * @author mackenzie
 */
public final class CustomConstructor
        extends AbstractCustomInvokableMember
        implements IConstructor
{

    public CustomConstructor(final ITypeFactory factory)
    {
        super(factory);
    }

    @Override
    public Constructor getConstructor()
    {
        return null;
    }
}
