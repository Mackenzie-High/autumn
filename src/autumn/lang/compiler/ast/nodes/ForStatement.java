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
 * An instance of this class is an AST node that represents a for-loop style statement.
 * 
 * <p> 
 * <table border="1">
 *     <tr> <td> <b>Property Name</b> </td> <td> <b>Property Description</b> </td> </tr>
 *     <tr> <td> <code>variable</code> </td> <td>This is the variable that is used to control the loop.</td> </tr>
 *     <tr> <td> <code>initializer</code> </td> <td>This expression is used to initialize the control variable.</td> </tr>
 *     <tr> <td> <code>condition</code> </td> <td>This expression is used to control the termination of the loop.</td> </tr>
 *     <tr> <td> <code>next</code> </td> <td>This expression produces the next value for the control variable.</td> </tr>
 *     <tr> <td> <code>body</code> </td> <td>This is the statement that is repeatedly executed.</td> </tr>
 *     <tr> <td> <code>location</code> </td> <td>This is the source-location information regarding this construct.</td> </tr>
 * </table>
 * </p>
 * 
 * <p> This file was auto-generated on (Sun May 31 11:54:12 EDT 2015).</p>
 */
@SuppressWarnings("unchecked")
public final class ForStatement extends Object implements IStatement
{
    private Variable variable;

    private IExpression initializer;

    private IExpression condition;

    private IExpression next;

    private SequenceStatement body = new SequenceStatement();

    private SourceLocation location = new SourceLocation();

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>variable</code>.
     * @return a copy of this object with property <code>variable</code> set to value.
     */
    public ForStatement setVariable(final Variable value)
    {
        final ForStatement result = this.copy();
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
     * @param value is the new value of property <code>initializer</code>.
     * @return a copy of this object with property <code>initializer</code> set to value.
     */
    public ForStatement setInitializer(final IExpression value)
    {
        final ForStatement result = this.copy();
        result.initializer = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>initializer</code>.
     */
    public IExpression getInitializer()
    {
        final IExpression value = this.initializer;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>condition</code>.
     * @return a copy of this object with property <code>condition</code> set to value.
     */
    public ForStatement setCondition(final IExpression value)
    {
        final ForStatement result = this.copy();
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
     * @param value is the new value of property <code>next</code>.
     * @return a copy of this object with property <code>next</code> set to value.
     */
    public ForStatement setNext(final IExpression value)
    {
        final ForStatement result = this.copy();
        result.next = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>next</code>.
     */
    public IExpression getNext()
    {
        final IExpression value = this.next;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>body</code>.
     * @return a copy of this object with property <code>body</code> set to value.
     */
    public ForStatement setBody(final SequenceStatement value)
    {
        final ForStatement result = this.copy();
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
    public ForStatement setLocation(final SourceLocation value)
    {
        final ForStatement result = this.copy();
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
     * @param initializer is the value for property <code>initializer</code>.
     * @param condition is the value for property <code>condition</code>.
     * @param next is the value for property <code>next</code>.
     * @param body is the value for property <code>body</code>.
     * @param location is the value for property <code>location</code>.
     * @return a new instance of this class.
     */
    public static ForStatement create(Variable variable, IExpression initializer, IExpression condition, IExpression next, SequenceStatement body, SourceLocation location)
    {
        ForStatement object = new ForStatement();
        object = object.setVariable(variable);
        object = object.setInitializer(initializer);
        object = object.setCondition(condition);
        object = object.setNext(next);
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
    public ForStatement copy()
    {
        final ForStatement result = new ForStatement();
        result.variable = this.variable;
        result.initializer = this.initializer;
        result.condition = this.condition;
        result.next = this.next;
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
     * @return a map containing the entries in this struct.
     */
    public Map<String, Object> toMap()
    {
        final Map<String, Object> map = new TreeMap<String, Object>();
        map.put("variable", this.getVariable());
        map.put("initializer", this.getInitializer());
        map.put("condition", this.getCondition());
        map.put("next", this.getNext());
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
