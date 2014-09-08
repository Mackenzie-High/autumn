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
 * An instance of this class is an AST node that represents the ternary conditional expression.
 * 
 * <p> 
 * <table border="1">
 *     <tr> <td> <b>Property Name</b> </td> <td> <b>Property Description</b> </td> </tr>
 *     <tr> <td> <code>condition</code> </td> <td>This expression's value indicates which  of the cases to evaluate.</td> </tr>
 *     <tr> <td> <code>case_true</code> </td> <td>This expression is evaluated, if the condition is true.</td> </tr>
 *     <tr> <td> <code>case_false</code> </td> <td>This expression is evaluated, if the condition is false.</td> </tr>
 *     <tr> <td> <code>location</code> </td> <td>This is the source-location information regarding this construct.</td> </tr>
 * </table>
 * </p>
 * 
 * <p> This file was auto-generated on (Sun Sep 07 00:40:15 EDT 2014).</p>
 */
@SuppressWarnings("unchecked")
public final class TernaryConditionalExpression extends Object implements IExpression
{
    private IExpression condition;

    private IExpression case_true;

    private IExpression case_false;

    private SourceLocation location = new SourceLocation();

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>condition</code>.
     * @return a copy of this object with property <code>condition</code> set to value.
     */
    public TernaryConditionalExpression setCondition(final IExpression value)
    {
        final TernaryConditionalExpression result = this.copy();
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
     * @param value is the new value of property <code>case_true</code>.
     * @return a copy of this object with property <code>case_true</code> set to value.
     */
    public TernaryConditionalExpression setCaseTrue(final IExpression value)
    {
        final TernaryConditionalExpression result = this.copy();
        result.case_true = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>case_true</code>.
     */
    public IExpression getCaseTrue()
    {
        final IExpression value = this.case_true;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>case_false</code>.
     * @return a copy of this object with property <code>case_false</code> set to value.
     */
    public TernaryConditionalExpression setCaseFalse(final IExpression value)
    {
        final TernaryConditionalExpression result = this.copy();
        result.case_false = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>case_false</code>.
     */
    public IExpression getCaseFalse()
    {
        final IExpression value = this.case_false;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>location</code>.
     * @return a copy of this object with property <code>location</code> set to value.
     */
    public TernaryConditionalExpression setLocation(final SourceLocation value)
    {
        final TernaryConditionalExpression result = this.copy();
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
     * @param condition is the value for property <code>condition</code>.
     * @param case_true is the value for property <code>case_true</code>.
     * @param case_false is the value for property <code>case_false</code>.
     * @param location is the value for property <code>location</code>.
     * @return a new instance of this class.
     */
    public static TernaryConditionalExpression create(IExpression condition, IExpression case_true, IExpression case_false, SourceLocation location)
    {
        TernaryConditionalExpression object = new TernaryConditionalExpression();
        object = object.setCondition(condition);
        object = object.setCaseTrue(case_true);
        object = object.setCaseFalse(case_false);
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
    public TernaryConditionalExpression copy()
    {
        final TernaryConditionalExpression result = new TernaryConditionalExpression();
        result.condition = this.condition;
        result.case_true = this.case_true;
        result.case_false = this.case_false;
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
        map.put("condition", this.getCondition());
        map.put("case_true", this.getCaseTrue());
        map.put("case_false", this.getCaseFalse());
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
