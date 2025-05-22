package high.mackenzie.autumn.lang.compiler.typesystem.design;

import high.mackenzie.autumn.resources.Finished;
import java.util.Collection;
import java.util.List;

/**
 * An instance of this interface is the type of an interface.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public interface IInterfaceType
        extends IDeclaredType
{
    /**
     * {@inheritDoc}
     */
    @Override
    public String getNamespace();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IAnnotation> getAnnotations();

    /**
     * {@inheritDoc}
     */
    @Override
    public int getModifiers();

    /**
     * {@inheritDoc}
     */
    @Override
    public IClassType getSuperclass();

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<IInterfaceType> getSuperinterfaces();

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<IField> getFields();

    /**
     * {@inheritDoc}
     *
     * @return an empty immutable collection.
     */
    @Override
    public Collection<IConstructor> getConstructors();

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<IMethod> getMethods();

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isInterfaceType();

    /**
     * {@inheritDoc}
     */
    @Override
    public Class toClass();
}
