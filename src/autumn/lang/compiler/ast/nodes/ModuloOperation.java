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
 * An instance of this class is an AST node that represents the modulo operator.
 * 
 * <p> 
 * <table border="1">
 *     <tr> <td> <b>Property Name</b> </td> <td> <b>Property Description</b> </td> </tr>
 *     <tr> <td> <code>left_operand</code> </td> <td>This expression produces the operation's left-operand.</td> </tr>
 *     <tr> <td> <code>right_operand</code> </td> <td>This expression produces the operation's right-operand.</td> </tr>
 *     <tr> <td> <code>location</code> </td> <td>This is the source-location information regarding this construct.</td> </tr>
 * </table>
 * </p>
 * 
 * <p> This file was auto-generated on (Sun May 31 11:54:12 EDT 2015).</p>
 */
@SuppressWarnings("unchecked")
public final class ModuloOperation extends Object implements IBinaryOperation
{
    private IExpression left_operand;

    private IExpression right_operand;

    private SourceLocation location = new SourceLocation();

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>left_operand</code>.
     * @return a copy of this object with property <code>left_operand</code> set to value.
     */
    public ModuloOperation setLeftOperand(final IExpression value)
    {
        final ModuloOperation result = this.copy();
        result.left_operand = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>left_operand</code>.
     */
    public IExpression getLeftOperand()
    {
        final IExpression value = this.left_operand;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>right_operand</code>.
     * @return a copy of this object with property <code>right_operand</code> set to value.
     */
    public ModuloOperation setRightOperand(final IExpression value)
    {
        final ModuloOperation result = this.copy();
        result.right_operand = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>right_operand</code>.
     */
    public IExpression getRightOperand()
    {
        final IExpression value = this.right_operand;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>location</code>.
     * @return a copy of this object with property <code>location</code> set to value.
     */
    public ModuloOperation setLocation(final SourceLocation value)
    {
        final ModuloOperation result = this.copy();
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
     * @param left_operand is the value for property <code>left_operand</code>.
     * @param right_operand is the value for property <code>right_operand</code>.
     * @param location is the value for property <code>location</code>.
     * @return a new instance of this class.
     */
    public static ModuloOperation create(IExpression left_operand, IExpression right_operand, SourceLocation location)
    {
        ModuloOperation object = new ModuloOperation();
        object = object.setLeftOperand(left_operand);
        object = object.setRightOperand(right_operand);
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
    public ModuloOperation copy()
    {
        final ModuloOperation result = new ModuloOperation();
        result.left_operand = this.left_operand;
        result.right_operand = this.right_operand;
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
        map.put("left_operand", this.getLeftOperand());
        map.put("right_operand", this.getRightOperand());
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
