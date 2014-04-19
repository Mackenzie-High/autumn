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
 * An instance of this class is an AST node that represents a switch-statement over an enumerations.
 * 
 * <p> 
 * <table border="1">
 *     <tr> <td> <b>Property Name</b> </td> <td> <b>Property Description</b> </td> </tr>
 *     <tr> <td> <code>selector</code> </td> <td>This expression produces the value that indicates which case will be executed.</td> </tr>
 *     <tr> <td> <code>conditional_cases</code> </td> <td>These cases are conditionally executed.</td> </tr>
 *     <tr> <td> <code>default_case</code> </td> <td>(Optional) This is the case that will be executed, if none of the other cases match.</td> </tr>
 *     <tr> <td> <code>location</code> </td> <td>This is the source-location information regarding this construct.</td> </tr>
 * </table>
 * </p>
 * 
 * <p> This file was auto-generated on (Thu Apr 17 06:31:04 EDT 2014).</p>
 */
@SuppressWarnings("unchecked")
public final class SwitchStatement extends Object implements IStatement
{
    private IExpression selector;

    private ConstructList<EnumCase> conditional_cases = new ConstructList();

    private SequenceStatement default_case = new SequenceStatement();

    private SourceLocation location = new SourceLocation();

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>selector</code>.
     * @return a copy of this object with property <code>selector</code> set to value.
     */
    public SwitchStatement setSelector(final IExpression value)
    {
        final SwitchStatement result = this.copy();
        result.selector = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>selector</code>.
     */
    public IExpression getSelector()
    {
        final IExpression value = this.selector;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>conditional_cases</code>.
     * @return a copy of this object with property <code>conditional_cases</code> set to value.
     */
    public SwitchStatement setConditionalCases(final ConstructList<EnumCase> value)
    {
        final SwitchStatement result = this.copy();
        result.conditional_cases = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>conditional_cases</code>.
     */
    public ConstructList<EnumCase> getConditionalCases()
    {
        final ConstructList<EnumCase> value = this.conditional_cases;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>default_case</code>.
     * @return a copy of this object with property <code>default_case</code> set to value.
     */
    public SwitchStatement setDefaultCase(final SequenceStatement value)
    {
        final SwitchStatement result = this.copy();
        result.default_case = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>default_case</code>.
     */
    public SequenceStatement getDefaultCase()
    {
        final SequenceStatement value = this.default_case;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>location</code>.
     * @return a copy of this object with property <code>location</code> set to value.
     */
    public SwitchStatement setLocation(final SourceLocation value)
    {
        final SwitchStatement result = this.copy();
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
    public SwitchStatement copy()
    {
        final SwitchStatement result = new SwitchStatement();
        result.selector = this.selector;
        result.conditional_cases = this.conditional_cases;
        result.default_case = this.default_case;
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
        map.put("selector", this.getSelector());
        map.put("conditional_cases", this.getConditionalCases());
        map.put("default_case", this.getDefaultCase());
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
