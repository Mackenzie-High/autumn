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
import autumn.lang.compiler.ast.commons.IDatum;
import autumn.lang.compiler.ast.commons.IDirective;
import autumn.lang.compiler.ast.commons.IDocumented;
import autumn.lang.compiler.ast.commons.IExpression;
import autumn.lang.compiler.ast.commons.IRecord;
import autumn.lang.compiler.ast.commons.IStatement;
import autumn.lang.compiler.ast.commons.IUnaryOperation;
import autumn.lang.compiler.ast.literals.BigDecimalLiteral;
import autumn.lang.compiler.ast.literals.BigIntegerLiteral;
import autumn.lang.compiler.ast.literals.ByteLiteral;
import autumn.lang.compiler.ast.literals.CharLiteral;
import autumn.lang.compiler.ast.literals.DoubleLiteral;
import autumn.lang.compiler.ast.literals.FloatLiteral;
import autumn.lang.compiler.ast.literals.IntLiteral;
import autumn.lang.compiler.ast.literals.LongLiteral;
import autumn.lang.compiler.ast.literals.ShortLiteral;
import autumn.util.ds.ImmutableList;
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
 * An instance of this class is an AST node that represents the definition of an anonymous function.
 * 
 * <p> 
 * <table border="1">
 *     <tr> <td> <b>Property Name</b> </td> <td> <b>Property Description</b> </td> </tr>
 *     <tr> <td> <code>variable</code> </td> <td>This is the variable that the new lambda will be assigned to.</td> </tr>
 *     <tr> <td> <code>type</code> </td> <td>This is the type of the new lambda.</td> </tr>
 *     <tr> <td> <code>body</code> </td> <td>This is the expression that is evaluated whenever the anonymous function is invoked.</td> </tr>
 *     <tr> <td> <code>location</code> </td> <td>This is the source-location information regarding this construct.</td> </tr>
 * </table>
 * </p>
 * 
 * <p> This file was auto-generated on (Fri Dec 12 09:04:03 EST 2014).</p>
 */
@SuppressWarnings("unchecked")
public final class LambdaStatement extends Object implements IStatement
{
    private Variable variable;

    private TypeSpecifier type;

    private IExpression body;

    private SourceLocation location = new SourceLocation();

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>variable</code>.
     * @return a copy of this object with property <code>variable</code> set to value.
     */
    public LambdaStatement setVariable(final Variable value)
    {
        final LambdaStatement result = this.copy();
        result.variable = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>variable</code>.
     */
    public Variable getVariable()
    {
        final Variable value = this.variable;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>type</code>.
     * @return a copy of this object with property <code>type</code> set to value.
     */
    public LambdaStatement setType(final TypeSpecifier value)
    {
        final LambdaStatement result = this.copy();
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
     * @param value is the new value of property <code>body</code>.
     * @return a copy of this object with property <code>body</code> set to value.
     */
    public LambdaStatement setBody(final IExpression value)
    {
        final LambdaStatement result = this.copy();
        result.body = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>body</code>.
     */
    public IExpression getBody()
    {
        final IExpression value = this.body;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>location</code>.
     * @return a copy of this object with property <code>location</code> set to value.
     */
    public LambdaStatement setLocation(final SourceLocation value)
    {
        final LambdaStatement result = this.copy();
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
     * @param variable is the value for property <code>variable</code>.
     * @param type is the value for property <code>type</code>.
     * @param body is the value for property <code>body</code>.
     * @param location is the value for property <code>location</code>.
     * @return a new instance of this class.
     */
    public static LambdaStatement create(Variable variable, TypeSpecifier type, IExpression body, SourceLocation location)
    {
        LambdaStatement object = new LambdaStatement();
        object = object.setVariable(variable);
        object = object.setType(type);
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
    public LambdaStatement copy()
    {
        final LambdaStatement result = new LambdaStatement();
        result.variable = this.variable;
        result.type = this.type;
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
        map.put("variable", this.getVariable());
        map.put("type", this.getType());
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
