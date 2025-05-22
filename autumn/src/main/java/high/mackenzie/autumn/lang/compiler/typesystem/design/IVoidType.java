package high.mackenzie.autumn.lang.compiler.typesystem.design;

import high.mackenzie.autumn.resources.Finished;

/**
 * An instance of this interface is the type of the "void" return-type.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public interface IVoidType
        extends IReturnType
{
    /**
     * {@inheritDoc}
     */
    @Override
    public Class toClass();
}
