package com.mackenziehigh.autumn.lang.compiler.typesystem;

import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IMethod;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.ITypeFactory;
import java.lang.reflect.Method;

/**
 * An instance of this class represents a method.
 *
 * @author Mackenzie High
 */
public final class CustomMethod
        extends AbstractCustomInvokableMember
        implements IMethod
{
    private final boolean annotation_method;

    /**
     * Sole Constructor.
     *
     * @param factory is the type-factory that is used to access types.
     * @param annotation_method is true, iff this object will represent an annotation method.
     */
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
