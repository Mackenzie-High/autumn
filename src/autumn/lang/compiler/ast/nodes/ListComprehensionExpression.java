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
 * An instance of this class is an AST node that represents a list comprehension.
 * 
 * <p> 
 * <table border="1">
 *     <tr> <td> <b>Property Name</b> </td> <td> <b>Property Description</b> </td> </tr>
 *     <tr> <td> <code>modifier</code> </td> <td>This is the expression that transforms the value produced by the iterator.</td> </tr>
 *     <tr> <td> <code>variable</code> </td> <td>This is the variable that will store values produced by the iterator.</td> </tr>
 *     <tr> <td> <code>type</code> </td> <td>This is the static-type of the variable.</td> </tr>
 *     <tr> <td> <code>iterable</code> </td> <td>This expression produces the iterable that will be iterated over.</td> </tr>
 *     <tr> <td> <code>condition</code> </td> <td>(optional) This expression is used to determine whether an element should be skipped or not.</td> </tr>
 *     <tr> <td> <code>location</code> </td> <td>This is the source-location information regarding this construct.</td> </tr>
 * </table>
 * </p>
 * 
 * <p> This file was auto-generated on (Sat Nov 08 20:19:21 EST 2014).</p>
 */
@SuppressWarnings("unchecked")
public final class ListComprehensionExpression extends Object implements IExpression
{
    private IExpression modifier;

    private Variable variable;

    private TypeSpecifier type;

    private IExpression iterable;

    private IExpression condition;

    private SourceLocation location = new SourceLocation();

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>modifier</code>.
     * @return a copy of this object with property <code>modifier</code> set to value.
     */
    public ListComprehensionExpression setModifier(final IExpression value)
    {
        final ListComprehensionExpression result = this.copy();
        result.modifier = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>modifier</code>.
     */
    public IExpression getModifier()
    {
        final IExpression value = this.modifier;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>variable</code>.
     * @return a copy of this object with property <code>variable</code> set to value.
     */
    public ListComprehensionExpression setVariable(final Variable value)
    {
        final ListComprehensionExpression result = this.copy();
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
    public ListComprehensionExpression setType(final TypeSpecifier value)
    {
        final ListComprehensionExpression result = this.copy();
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
     * @param value is the new value of property <code>iterable</code>.
     * @return a copy of this object with property <code>iterable</code> set to value.
     */
    public ListComprehensionExpression setIterable(final IExpression value)
    {
        final ListComprehensionExpression result = this.copy();
        result.iterable = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>iterable</code>.
     */
    public IExpression getIterable()
    {
        final IExpression value = this.iterable;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>condition</code>.
     * @return a copy of this object with property <code>condition</code> set to value.
     */
    public ListComprehensionExpression setCondition(final IExpression value)
    {
        final ListComprehensionExpression result = this.copy();
        result.condition = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>condition</code>.
     */
    public IExpression getCondition()
    {
        final IExpression value = this.condition;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>location</code>.
     * @return a copy of this object with property <code>location</code> set to value.
     */
    public ListComprehensionExpression setLocation(final SourceLocation value)
    {
        final ListComprehensionExpression result = this.copy();
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
     * @param modifier is the value for property <code>modifier</code>.
     * @param variable is the value for property <code>variable</code>.
     * @param type is the value for property <code>type</code>.
     * @param iterable is the value for property <code>iterable</code>.
     * @param condition is the value for property <code>condition</code>.
     * @param location is the value for property <code>location</code>.
     * @return a new instance of this class.
     */
    public static ListComprehensionExpression create(IExpression modifier, Variable variable, TypeSpecifier type, IExpression iterable, IExpression condition, SourceLocation location)
    {
        ListComprehensionExpression object = new ListComprehensionExpression();
        object = object.setModifier(modifier);
        object = object.setVariable(variable);
        object = object.setType(type);
        object = object.setIterable(iterable);
        object = object.setCondition(condition);
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
    public ListComprehensionExpression copy()
    {
        final ListComprehensionExpression result = new ListComprehensionExpression();
        result.modifier = this.modifier;
        result.variable = this.variable;
        result.type = this.type;
        result.iterable = this.iterable;
        result.condition = this.condition;
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
        map.put("modifier", this.getModifier());
        map.put("variable", this.getVariable());
        map.put("type", this.getType());
        map.put("iterable", this.getIterable());
        map.put("condition", this.getCondition());
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
