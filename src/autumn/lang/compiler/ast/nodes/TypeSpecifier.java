/*
 * Copyright 2013 Michael Mackenzie High
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package autumn.lang.compiler.ast.nodes;

import autumn.lang.compiler.ast.commons.ConstructList;
import autumn.lang.compiler.ast.commons.IAnnotated;
import autumn.lang.compiler.ast.commons.IBinaryOperation;
import autumn.lang.compiler.ast.commons.IConstruct;
import autumn.lang.compiler.ast.commons.IConversionOperation;
import autumn.lang.compiler.ast.commons.IDirective;
import autumn.lang.compiler.ast.commons.IDocumented;
import autumn.lang.compiler.ast.commons.IExpression;
import autumn.lang.compiler.ast.commons.IStatement;
import autumn.lang.compiler.ast.commons.IUnaryOperation;
import autumn.lang.compiler.ast.literals.ByteLiteral;
import autumn.lang.compiler.ast.literals.CharLiteral;
import autumn.lang.compiler.ast.literals.DoubleLiteral;
import autumn.lang.compiler.ast.literals.FloatLiteral;
import autumn.lang.compiler.ast.literals.IntLiteral;
import autumn.lang.compiler.ast.literals.LongLiteral;
import autumn.lang.compiler.ast.literals.ShortLiteral;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * An instance of this class is an AST node that represents a type-declaration.
 * 
 * <p> 
 * <table border="1">
 *     <tr> <td> <b>Property Name</b> </td> <td> <b>Property Description</b> </td> </tr>
 *     <tr> <td> <code>namespace</code> </td> <td>This is the name of the package that contains the type, if explicitly specified.</td> </tr>
 *     <tr> <td> <code>name</code> </td> <td>This is the simple-name of the type.</td> </tr>
 *     <tr> <td> <code>dimensions</code> </td> <td>If the specified type is an array-type, then this is the non-zero positive number of dimensions in the array; otherwise, null.</td> </tr>
 *     <tr> <td> <code>location</code> </td> <td>This is the source-location information regarding this construct.</td> </tr>
 * </table>
 * </p>
 * 
 * <p> This file was auto-generated on (Sun Sep 07 00:40:15 EDT 2014).</p>
 */
@SuppressWarnings("unchecked")
public final class TypeSpecifier extends Object implements IConstruct
{
    private Namespace namespace;

    private Name name;

    private Integer dimensions = null;

    private SourceLocation location = new SourceLocation();

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>namespace</code>.
     * @return a copy of this object with property <code>namespace</code> set to value.
     */
    public TypeSpecifier setNamespace(final Namespace value)
    {
        final TypeSpecifier result = this.copy();
        result.namespace = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>namespace</code>.
     */
    public Namespace getNamespace()
    {
        final Namespace value = this.namespace;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>name</code>.
     * @return a copy of this object with property <code>name</code> set to value.
     */
    public TypeSpecifier setName(final Name value)
    {
        final TypeSpecifier result = this.copy();
        result.name = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>name</code>.
     */
    public Name getName()
    {
        final Name value = this.name;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>dimensions</code>.
     * @return a copy of this object with property <code>dimensions</code> set to value.
     */
    public TypeSpecifier setDimensions(final Integer value)
    {
        final TypeSpecifier result = this.copy();
        result.dimensions = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>dimensions</code>.
     */
    public Integer getDimensions()
    {
        final Integer value = this.dimensions;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>location</code>.
     * @return a copy of this object with property <code>location</code> set to value.
     */
    public TypeSpecifier setLocation(final SourceLocation value)
    {
        final TypeSpecifier result = this.copy();
        result.location = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>location</code>.
     */
    public SourceLocation getLocation()
    {
        final SourceLocation value = this.location;
        return value;
    }

    /**
     * This method creates a new instance of this class.
     * 
     * @param namespace is the value for property <code>namespace</code>.
     * @param name is the value for property <code>name</code>.
     * @param dimensions is the value for property <code>dimensions</code>.
     * @param location is the value for property <code>location</code>.
     * @return a new instance of this class.
     */
    public static TypeSpecifier create(Namespace namespace, Name name, Integer dimensions, SourceLocation location)
    {
        TypeSpecifier object = new TypeSpecifier();
        object = object.setNamespace(namespace);
        object = object.setName(name);
        object = object.setDimensions(dimensions);
        object = object.setLocation(location);
        return object;
    }

    /**
     * This method welcomes a visitor that wants to visit this object.
     * 
     * @param visitor is the visitor that is visiting this object.
     */
    public void accept(final IAstVisitor visitor)
    {
        visitor.visit(this);
    }

    /**
     * This method creates a shallow copy of this object.
     * 
     * @return a shallow copy of this object.
     */
    public TypeSpecifier copy()
    {
        final TypeSpecifier result = new TypeSpecifier();
        result.namespace = this.namespace;
        result.name = this.name;
        result.dimensions = this.dimensions;
        result.location = this.location;
        return result;
    }

    /**
     * This method creates a map representation of this struct.
     * 
     * <p>
     * Each key is the name of a field.
     * Each value is the result of calling the key field's getter.
     * </p>
     * 
     * @returns a map containing the entries in this struct.
     */
    public Map<String, Object> toMap()
    {
        final Map<String, Object> map = new TreeMap<String, Object>();
        map.put("namespace", this.getNamespace());
        map.put("name", this.getName());
        map.put("dimensions", this.getDimensions());
        map.put("location", this.getLocation());

        return map;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        return this.toMap().toString();
    }

}
