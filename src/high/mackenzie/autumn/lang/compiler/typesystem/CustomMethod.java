/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.lang.compiler.typesystem;

import high.mackenzie.autumn.lang.compiler.typesystem.design.IAnnotation;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IClassType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IDeclaredType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IFormalParameter;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IReturnType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.ITypeFactory;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author mackenzie
 */
public final class CustomMethod
        extends AbstractCustomInvokableMember
        implements IMethod
{

    private final boolean annotation_method;

    public CustomMethod(final ITypeFactory factory,
                        final boolean annotation_method)
    {
        super(factory);

        this.annotation_method = annotation_method;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAnnotationMethod()
    {
        return this.annotation_method;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Method getMethod()
    {
        return null;
    }
}
