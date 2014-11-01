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
import autumn.lang.compiler.ast.commons.IRecord;
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
 * An instance of this class is an AST node that represents the definition of a function.
 * 
 * <p> 
 * <table border="1">
 *     <tr> <td> <b>Property Name</b> </td> <td> <b>Property Description</b> </td> </tr>
 *     <tr> <td> <code>comment</code> </td> <td>This is the doc-comment applied directly to the definition.</td> </tr>
 *     <tr> <td> <code>annotations</code> </td> <td>These are the annotations applied directly to the definition.</td> </tr>
 *     <tr> <td> <code>name</code> </td> <td>This is the name of the function itself.</td> </tr>
 *     <tr> <td> <code>parameters</code> </td> <td>These are the formal parameters of the function.</td> </tr>
 *     <tr> <td> <code>return_type</code> </td> <td>This is the type of value that the function can return, or the void type, if the function does not return a value.</td> </tr>
 *     <tr> <td> <code>body</code> </td> <td>This is the statement that is executed whenever the function is invoked.</td> </tr>
 *     <tr> <td> <code>location</code> </td> <td>This is the source-location information regarding this construct.</td> </tr>
 * </table>
 * </p>
 * 
 * <p> This file was auto-generated on (Sat Nov 01 12:16:03 EDT 2014).</p>
 */
@SuppressWarnings("unchecked")
public final class FunctionDefinition extends Object implements IAnnotated, IDocumented
{
    private DocComment comment = new DocComment();

    private AnnotationList annotations = new AnnotationList();

    private Name name;

    private FormalParameterList parameters;

    private TypeSpecifier return_type;

    private SequenceStatement body = new SequenceStatement();

    private SourceLocation location = new SourceLocation();

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>comment</code>.
     * @return a copy of this object with property <code>comment</code> set to value.
     */
    public FunctionDefinition setComment(final DocComment value)
    {
        final FunctionDefinition result = this.copy();
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
    public FunctionDefinition setAnnotations(final AnnotationList value)
    {
        final FunctionDefinition result = this.copy();
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
    public FunctionDefinition setName(final Name value)
    {
        final FunctionDefinition result = this.copy();
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
     * @param value is the new value of property <code>parameters</code>.
     * @return a copy of this object with property <code>parameters</code> set to value.
     */
    public FunctionDefinition setParameters(final FormalParameterList value)
    {
        final FunctionDefinition result = this.copy();
        result.parameters = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>parameters</code>.
     */
    public FormalParameterList getParameters()
    {
        final FormalParameterList value = this.parameters;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>return_type</code>.
     * @return a copy of this object with property <code>return_type</code> set to value.
     */
    public FunctionDefinition setReturnType(final TypeSpecifier value)
    {
        final FunctionDefinition result = this.copy();
        result.return_type = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>return_type</code>.
     */
    public TypeSpecifier getReturnType()
    {
        final TypeSpecifier value = this.return_type;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>body</code>.
     * @return a copy of this object with property <code>body</code> set to value.
     */
    public FunctionDefinition setBody(final SequenceStatement value)
    {
        final FunctionDefinition result = this.copy();
        result.body = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>body</code>.
     */
    public SequenceStatement getBody()
    {
        final SequenceStatement value = this.body;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>location</code>.
     * @return a copy of this object with property <code>location</code> set to value.
     */
    public FunctionDefinition setLocation(final SourceLocation value)
    {
        final FunctionDefinition result = this.copy();
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
     * @param parameters is the value for property <code>parameters</code>.
     * @param return_type is the value for property <code>return_type</code>.
     * @param body is the value for property <code>body</code>.
     * @param location is the value for property <code>location</code>.
     * @return a new instance of this class.
     */
    public static FunctionDefinition create(DocComment comment, AnnotationList annotations, Name name, FormalParameterList parameters, TypeSpecifier return_type, SequenceStatement body, SourceLocation location)
    {
        FunctionDefinition object = new FunctionDefinition();
        object = object.setComment(comment);
        object = object.setAnnotations(annotations);
        object = object.setName(name);
        object = object.setParameters(parameters);
        object = object.setReturnType(return_type);
        object = object.setBody(body);
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
    public FunctionDefinition copy()
    {
        final FunctionDefinition result = new FunctionDefinition();
        result.comment = this.comment;
        result.annotations = this.annotations;
        result.name = this.name;
        result.parameters = this.parameters;
        result.return_type = this.return_type;
        result.body = this.body;
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
        map.put("parameters", this.getParameters());
        map.put("return_type", this.getReturnType());
        map.put("body", this.getBody());
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
