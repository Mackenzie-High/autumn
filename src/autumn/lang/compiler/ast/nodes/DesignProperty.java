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
 * An instance of this class is an AST node that represents the declaration of a property within a design.
 * 
 * <p> 
 * <table border="1">
 *     <tr> <td> <b>Property Name</b> </td> <td> <b>Property Description</b> </td> </tr>
 *     <tr> <td> <code>comment</code> </td> <td>This is the doc-comment applied directly to the property.</td> </tr>
 *     <tr> <td> <code>annotations</code> </td> <td>These are the annotations applied directly to the property.</td> </tr>
 *     <tr> <td> <code>name</code> </td> <td>This is the name of the property.</td> </tr>
 *     <tr> <td> <code>type</code> </td> <td>This is the type of the data that can be stored in the property at runtime.</td> </tr>
 *     <tr> <td> <code>location</code> </td> <td>This is the source-location information regarding this construct.</td> </tr>
 * </table>
 * </p>
 * 
 * <p> This file was auto-generated on (Tue Aug 19 02:50:18 EDT 2014).</p>
 */
@SuppressWarnings("unchecked")
public final class DesignProperty extends Object implements IAnnotated, IDocumented
{
    private DocComment comment = new DocComment();

    private AnnotationList annotations = new AnnotationList();

    private Name name;

    private TypeSpecifier type;

    private SourceLocation location = new SourceLocation();

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>comment</code>.
     * @return a copy of this object with property <code>comment</code> set to value.
     */
    public DesignProperty setComment(final DocComment value)
    {
        final DesignProperty result = this.copy();
        result.comment = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>comment</code>.
     */
    public DocComment getComment()
    {
        final DocComment value = this.comment;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>annotations</code>.
     * @return a copy of this object with property <code>annotations</code> set to value.
     */
    public DesignProperty setAnnotations(final AnnotationList value)
    {
        final DesignProperty result = this.copy();
        result.annotations = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>annotations</code>.
     */
    public AnnotationList getAnnotations()
    {
        final AnnotationList value = this.annotations;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>name</code>.
     * @return a copy of this object with property <code>name</code> set to value.
     */
    public DesignProperty setName(final Name value)
    {
        final DesignProperty result = this.copy();
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
     * @param value is the new value of property <code>type</code>.
     * @return a copy of this object with property <code>type</code> set to value.
     */
    public DesignProperty setType(final TypeSpecifier value)
    {
        final DesignProperty result = this.copy();
        result.type = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>type</code>.
     */
    public TypeSpecifier getType()
    {
        final TypeSpecifier value = this.type;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>location</code>.
     * @return a copy of this object with property <code>location</code> set to value.
     */
    public DesignProperty setLocation(final SourceLocation value)
    {
        final DesignProperty result = this.copy();
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
     * @param comment is the value for property <code>comment</code>.
     * @param annotations is the value for property <code>annotations</code>.
     * @param name is the value for property <code>name</code>.
     * @param type is the value for property <code>type</code>.
     * @param location is the value for property <code>location</code>.
     * @return a new instance of this class.
     */
    public static DesignProperty create(DocComment comment, AnnotationList annotations, Name name, TypeSpecifier type, SourceLocation location)
    {
        DesignProperty object = new DesignProperty();
        object = object.setComment(comment);
        object = object.setAnnotations(annotations);
        object = object.setName(name);
        object = object.setType(type);
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
    public DesignProperty copy()
    {
        final DesignProperty result = new DesignProperty();
        result.comment = this.comment;
        result.annotations = this.annotations;
        result.name = this.name;
        result.type = this.type;
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
        map.put("comment", this.getComment());
        map.put("annotations", this.getAnnotations());
        map.put("name", this.getName());
        map.put("type", this.getType());
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
