package high.mackenzie.autumn.lang.compiler.typesystem.design;

import high.mackenzie.autumn.resources.Finished;

/**
 * An instance of this interface is a type that represents a runtime object.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public interface IReferenceType
        extends IExpressionType
{
    /**
     * This method determines whether this type is an array-type.
     *
     * @return true, iff this type is an array-type.
     */
    public boolean isArrayType();

    /**
     * This method determines whether this type is a declared-type.
     *
     * @return true, iff this type is a declared-type.
     */
    public boolean isDeclaredType();

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isNullType();
}
