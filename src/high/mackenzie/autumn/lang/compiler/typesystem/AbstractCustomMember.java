package high.mackenzie.autumn.lang.compiler.typesystem;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IAnnotation;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IDeclaredType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.ITypeFactory;
import high.mackenzie.autumn.resources.Finished;
import java.util.Collection;

/**
 * This class provides a partial implementation of the IMember interface.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public abstract class AbstractCustomMember
        extends AbstractMember
{
    private IDeclaredType owner;

    private ImmutableList<IAnnotation> annotations = ImmutableList.of();

    private int modifiers;

    private String name;

    /**
     * Sole Constructor.
     *
     * @param factory type-factory that is used to access types.
     */
    public AbstractCustomMember(final ITypeFactory factory)
    {
        super(factory);

        Preconditions.checkNotNull(factory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IDeclaredType getOwner()
    {
        return owner;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<IAnnotation> getAnnotations()
    {
        return annotations;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getModifiers()
    {
        return modifiers;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName()
    {
        return name;
    }

    /**
     * Setter.
     *
     * @param owner is the owner of this member.
     */
    public void setOwner(final IDeclaredType owner)
    {
        Preconditions.checkNotNull(owner);

        this.owner = owner;
    }

    /**
     * Setter.
     *
     * @param annotations are the annotations applied directly to this member.
     */
    public void setAnnotations(final Collection<IAnnotation> annotations)
    {
        this.annotations = ImmutableList.copyOf(annotations);
    }

    /**
     * Setter.
     *
     * @param modifiers are the modifiers applied to this member.
     */
    public void setModifiers(final int modifiers)
    {
        this.modifiers = modifiers;
    }

    /**
     * Setter.
     *
     * @param name is the name of this member.
     */
    public void setName(final String name)
    {
        Preconditions.checkNotNull(name);

        this.name = name;
    }
}
