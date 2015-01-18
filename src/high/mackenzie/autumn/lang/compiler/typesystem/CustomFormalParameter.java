package high.mackenzie.autumn.lang.compiler.typesystem;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IAnnotation;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IFormalParameter;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVariableType;
import high.mackenzie.autumn.resources.Finished;
import java.util.Collection;
import java.util.List;

/**
 * An instance of this class represents a formal-parameter.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public final class CustomFormalParameter
        implements IFormalParameter,
                   ICustomAnnotatable
{
    private ImmutableList<IAnnotation> annotations = ImmutableList.of();

    private IVariableType type;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IAnnotation> getAnnotations()
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
    @Override
    public void setAnnotations(final Collection<IAnnotation> annotations)
    {
        this.annotations = ImmutableList.copyOf(annotations);
    }

    /**
     * Setter.
     *
     * @param type is the type of value that this formal-parameter accepts.
     */
    public void setType(IVariableType type)
    {
        Preconditions.checkNotNull(type);

        this.type = type;
    }
}
