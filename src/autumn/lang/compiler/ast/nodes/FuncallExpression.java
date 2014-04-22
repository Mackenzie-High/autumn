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
 * An instance of this class is an AST node that represents an expression that invokes an anonymous function.
 * 
 * <p> 
 * <table border="1">
 *     <tr> <td> <b>Property Name</b> </td> <td> <b>Property Description</b> </td> </tr>
 *     <tr> <td> <code>functor</code> </td> <td>This expression produces the anonymous function to invoke.</td> </tr>
 *     <tr> <td> <code>arguments</code> </td> <td>These expressions produce the arguments to pass to the anonymous function.</td> </tr>
 *     <tr> <td> <code>location</code> </td> <td>This is the source-location information regarding this construct.</td> </tr>
 * </table>
 * </p>
 * 
 * <p> This file was auto-generated on (Mon Apr 21 22:27:52 EDT 2014).</p>
 */
@SuppressWarnings("unchecked")
public final class FuncallExpression extends Object implements IExpression
{
    private IExpression functor;

    private ConstructList<IExpression> arguments = new ConstructList();

    private SourceLocation location = new SourceLocation();

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>functor</code>.
     * @return a copy of this object with property <code>functor</code> set to value.
     */
    public FuncallExpression setFunctor(final IExpression value)
    {
        final FuncallExpression result = this.copy();
        result.functor = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>functor</code>.
     */
    public IExpression getFunctor()
    {
        final IExpression value = this.functor;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>arguments</code>.
     * @return a copy of this object with property <code>arguments</code> set to value.
     */
    public FuncallExpression setArguments(final ConstructList<IExpression> value)
    {
        final FuncallExpression result = this.copy();
        result.arguments = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>arguments</code>.
     */
    public ConstructList<IExpression> getArguments()
    {
        final ConstructList<IExpression> value = this.arguments;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>location</code>.
     * @return a copy of this object with property <code>location</code> set to value.
     */
    public FuncallExpression setLocation(final SourceLocation value)
    {
        final FuncallExpression result = this.copy();
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
    public FuncallExpression copy()
    {
        final FuncallExpression result = new FuncallExpression();
        result.functor = this.functor;
        result.arguments = this.arguments;
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
        map.put("functor", this.getFunctor());
        map.put("arguments", this.getArguments());
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
