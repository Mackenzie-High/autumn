package high.mackenzie.autumn.lang.compiler.typesystem.design;

import high.mackenzie.autumn.resources.Finished;

/**
 * An instance of this interface can be the type of a value that an expression produces.
 *
 * @author Nackenzie High
 */
@Finished("2014/07/12")
public interface IExpressionType
        extends IType
{
    /**
     * This method determines whether this type is a primitive-type.
     *
     * @return true, iff this type is one of the primitive types.
     */
    public boolean isPrimitiveType();

    /**
     * This method determines whether this type is a reference-type.
     *
     * <p>
     * Remember, the null-type is also a reference-type.
     * </p>
     *
     * @return true, iff this type is a reference-type.
     */
    public boolean isReferenceType();

    /**
     * This method determines whether this type is the null-type.
     *
     * <p>
     * Remember, the null-type is also a reference-type.
     * </p>
     *
     * @return true, iff this type is the null-type.
     */
    public boolean isNullType();

    /**
     * This method determines whether this type is the void-type.
     *
     * @return true, iff this type is the void-type.
     */
    public boolean isVoidType();
}
