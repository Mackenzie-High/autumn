package com.mackenziehigh.autumn.lang.compiler.typesystem.design;

import com.mackenziehigh.autumn.resources.Finished;
import java.util.Collection;

/**
 * An instance of this interface is a factory that produces type objects and components therefor.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public interface ITypeFactory
{
    /**
     * This method returns the primitive-boolean type.
     *
     * @return type primitive-boolean.
     */
    public IPrimitiveType getBoolean();

    /**
     * This method returns the primitive-char type.
     *
     * @return type primitive-char.
     */
    public IPrimitiveType getChar();

    /**
     * This method returns the primitive-byte type.
     *
     * @return type primitive-byte.
     */
    public IPrimitiveType getByte();

    /**
     * This method returns the primitive-short type.
     *
     * @return type primitive-short.
     */
    public IPrimitiveType getShort();

    /**
     * This method returns the primitive-int type.
     *
     * @return type primitive-int.
     */
    public IPrimitiveType getInt();

    /**
     * This method returns the primitive-long type.
     *
     * @return type primitive-long.
     */
    public IPrimitiveType getLong();

    /**
     * This method returns the primitive-float type.
     *
     * @return type primitive-float.
     */
    public IPrimitiveType getFloat();

    /**
     * This method returns the primitive-double type.
     *
     * @return type primitive-double.
     */
    public IPrimitiveType getDouble();

    /**
     * This method returns the type of the null-constant.
     *
     * @return the null-type.
     */
    public INullType getNull();

    /**
     * This method returns the type of the void-constant.
     *
     * @return the void-type.
     */
    public IVoidType getVoid();

    /**
     * This method returns the type of an array, given its element-type and dimensions.
     *
     * @param element is the type of the elements in an array of the returned type.
     * @param dimensions is the number of dimensions in an array of the returned type.
     * @return the aforedescribed array-type.
     * @throws NullPointerException <code>if(element == null)</code>
     * @throws IllegalArgumentException <code>if(dimensions &lt; 1)</code>
     */
    public IArrayType getArrayType(IElementType element,
                                   int dimensions);

    /**
     * This method finds and returns a type, given its
     * <code>java.lang.Class</code> representation.
     *
     * @param clazz is the class-object representation of the type to find.
     * @return the aforedescribed type, or null, if no suitable type can be found.
     * @throws NullPointerException <code>if(clazz == null)</code>
     */
    public IType fromClass(Class<?> clazz);

    /**
     * This method finds and returns a type, given its name.
     *
     * <p>
     * <b>The following types of types can be found:</b>
     * <ul>
     * <li>Primitive Types</li>
     * <li>Void IType</li>
     * <li>Null IType</li>
     * <li>Previously Manufactured - Class Types</li>
     * <li>Previously Manufactured - Interface Types</li>
     * <li>Previously Manufactured - Enum Types</li>
     * <li>Previously Manufactured - Array Types</li>
     * <li><code>Class.forName(name)</code></li>
     * </ul>
     * </p>
     *
     * @param name is the name of the type to find.
     * @return the aforedescribed type, or null, if no suitable type can be found.
     * @throws NullPointerException <code>if(name == null)</code>
     */
    public IType findType(String name);

    /**
     * This method returns all the type objects that have been created by this factory.
     *
     * @return an immutable collection containing the products of this type-factory.
     */
    public Collection<IType> getTypes();
}
