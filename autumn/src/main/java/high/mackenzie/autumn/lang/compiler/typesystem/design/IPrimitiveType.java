package high.mackenzie.autumn.lang.compiler.typesystem.design;

import high.mackenzie.autumn.resources.Finished;

/**
 * An instance of this interface is the type of a primitive value.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public interface IPrimitiveType
        extends IElementType
{
    /**
     * This method determines whether this object represents the "boolean" type.
     *
     * @return true, if and only if, this object represents the "boolean" type;
     * otherwise, return false..
     */
    public Boolean isBoolean();

    /**
     * This method determines whether this object represents the "char" type.
     *
     * @return true, if and only if, this object represents the "char" type;
     * otherwise, return false..
     */
    public Boolean isChar();

    /**
     * This method determines whether this object represents the "byte" type.
     *
     * @return true, if and only if, this object represents the "byte" type;
     * otherwise, return false..
     */
    public Boolean isByte();

    /**
     * This method determines whether this object represents the "short" type.
     *
     * @return true, if and only if, this object represents the "short" type;
     * otherwise, return false..
     */
    public Boolean isShort();

    /**
     * This method determines whether this object represents the "int" type.
     *
     * @return true, if and only if, this object represents the "int" type;
     * otherwise, return false..
     */
    public Boolean isInt();

    /**
     * This method determines whether this object represents the "long" type.
     *
     * @return true, if and only if, this object represents the "long" type;
     * otherwise, return false..
     */
    public Boolean isLong();

    /**
     * This method determines whether this object represents the "float" type.
     *
     * @return true, if and only if, this object represents the "float" type;
     * otherwise, return false..
     */
    public Boolean isFloat();

    /**
     * This method determines whether this object represents the "double" type.
     *
     * @return true, if and only if, this object represents the "double" type;
     * otherwise, return false.
     */
    public Boolean isDouble();

    /**
     * {@inheritDoc}
     */
    @Override
    public Class toClass();
}
