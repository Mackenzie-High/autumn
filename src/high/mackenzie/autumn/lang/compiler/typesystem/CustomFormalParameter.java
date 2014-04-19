/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.lang.compiler.typesystem;

import high.mackenzie.autumn.lang.compiler.typesystem.design.IAnnotation;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IFormalParameter;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVariableType;
import com.google.common.collect.ImmutableList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author mackenzie
 */
public final class CustomFormalParameter
        implements IFormalParameter
{

    private List<IAnnotation> annotations;

    private IVariableType type;

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<IAnnotation> getAnnotations()
    {
        return ImmutableList.copyOf(annotations);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IVariableType getType()
    {
        return type;
    }

    /**
     * Setter.
     *
     * @param annotations are the annotations applied to this formal-parameter.
     */
    public void setAnnotations(final List<IAnnotation> annotations)
    {
        this.annotations = annotations;
    }

    /**
     * Setter.
     *
     * @param type is the type of value that this formal-parameter accepts.
     */
    public void setType(IVariableType type)
    {
        this.type = type;
    }
}
