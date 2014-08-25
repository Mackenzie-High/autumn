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
 * An instance of this class is an AST node that represents an if-then-else style statement.
 * 
 * <p> 
 * <table border="1">
 *     <tr> <td> <b>Property Name</b> </td> <td> <b>Property Description</b> </td> </tr>
 *     <tr> <td> <code>main_case</code> </td> <td>This is the primary conditional case.</td> </tr>
 *     <tr> <td> <code>elif_cases</code> </td> <td>These are the secondary conditional cases.</td> </tr>
 *     <tr> <td> <code>else_case</code> </td> <td>(optional) This is the statement that is exected, if none of the conditional-cases can be executed.</td> </tr>
 *     <tr> <td> <code>location</code> </td> <td>This is the source-location information regarding this construct.</td> </tr>
 * </table>
 * </p>
 * 
 * <p> This file was auto-generated on (Sat Aug 23 10:55:08 EDT 2014).</p>
 */
@SuppressWarnings("unchecked")
public final class IfStatement extends Object implements IStatement
{
    private ConditionalCase main_case;

    private ConstructList<ConditionalCase> elif_cases = new ConstructList();

    private SequenceStatement else_case = null;

    private SourceLocation location = new SourceLocation();

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>main_case</code>.
     * @return a copy of this object with property <code>main_case</code> set to value.
     */
    public IfStatement setMainCase(final ConditionalCase value)
    {
        final IfStatement result = this.copy();
        result.main_case = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>main_case</code>.
     */
    public ConditionalCase getMainCase()
    {
        final ConditionalCase value = this.main_case;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>elif_cases</code>.
     * @return a copy of this object with property <code>elif_cases</code> set to value.
     */
    public IfStatement setElifCases(final ConstructList<ConditionalCase> value)
    {
        final IfStatement result = this.copy();
        result.elif_cases = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>elif_cases</code>.
     */
    public ConstructList<ConditionalCase> getElifCases()
    {
        final ConstructList<ConditionalCase> value = this.elif_cases;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>else_case</code>.
     * @return a copy of this object with property <code>else_case</code> set to value.
     */
    public IfStatement setElseCase(final SequenceStatement value)
    {
        final IfStatement result = this.copy();
        result.else_case = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>else_case</code>.
     */
    public SequenceStatement getElseCase()
    {
        final SequenceStatement value = this.else_case;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>location</code>.
     * @return a copy of this object with property <code>location</code> set to value.
     */
    public IfStatement setLocation(final SourceLocation value)
    {
        final IfStatement result = this.copy();
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
     * @param main_case is the value for property <code>main_case</code>.
     * @param elif_cases is the value for property <code>elif_cases</code>.
     * @param else_case is the value for property <code>else_case</code>.
     * @param location is the value for property <code>location</code>.
     * @return a new instance of this class.
     */
    public static IfStatement create(ConditionalCase main_case, ConstructList<ConditionalCase> elif_cases, SequenceStatement else_case, SourceLocation location)
    {
        IfStatement object = new IfStatement();
        object = object.setMainCase(main_case);
        object = object.setElifCases(elif_cases);
        object = object.setElseCase(else_case);
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
    public IfStatement copy()
    {
        final IfStatement result = new IfStatement();
        result.main_case = this.main_case;
        result.elif_cases = this.elif_cases;
        result.else_case = this.else_case;
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
        map.put("main_case", this.getMainCase());
        map.put("elif_cases", this.getElifCases());
        map.put("else_case", this.getElseCase());
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
