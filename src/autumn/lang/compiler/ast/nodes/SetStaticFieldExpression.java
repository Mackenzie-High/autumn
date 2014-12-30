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
import autumn.util.ds.ImmutableSequence;
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
 * An instance of this class is an AST node that represents a static field assignment.
 * 
 * <p> 
 * <table border="1">
 *     <tr> <td> <b>Property Name</b> </td> <td> <b>Property Description</b> </td> </tr>
 *     <tr> <td> <code>owner</code> </td> <td>This is the type that declares the field to set.</td> </tr>
 *     <tr> <td> <code>name</code> </td> <td>This is the name of the field to set.</td> </tr>
 *     <tr> <td> <code>value</code> </td> <td>This expression produces the value to assign to the field.</td> </tr>
 *     <tr> <td> <code>location</code> </td> <td>This is the source-location information regarding this construct.</td> </tr>
 * </table>
 * </p>
 * 
 * <p> This file was auto-generated on (Mon Dec 29 14:17:29 EST 2014).</p>
 */
@SuppressWarnings("unchecked")
public final class SetStaticFieldExpression extends Object implements IExpression
{
    private TypeSpecifier owner;

    private Name name;

    private IExpression value;

    private SourceLocation location = new SourceLocation();

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>owner</code>.
     * @return a copy of this object with property <code>owner</code> set to value.
     */
    public SetStaticFieldExpression setOwner(final TypeSpecifier value)
    {
        final SetStaticFieldExpression result = this.copy();
        result.owner = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>owner</code>.
     */
    public TypeSpecifier getOwner()
    {
        final TypeSpecifier value = this.owner;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>name</code>.
     * @return a copy of this object with property <code>name</code> set to value.
     */
    public SetStaticFieldExpression setName(final Name value)
    {
        final SetStaticFieldExpression result = this.copy();
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
     * @param value is the new value of property <code>value</code>.
     * @return a copy of this object with property <code>value</code> set to value.
     */
    public SetStaticFieldExpression setValue(final IExpression value)
    {
        final SetStaticFieldExpression result = this.copy();
        result.value = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>value</code>.
     */
    public IExpression getValue()
    {
        final IExpression value = this.value;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>location</code>.
     * @return a copy of this object with property <code>location</code> set to value.
     */
    public SetStaticFieldExpression setLocation(final SourceLocation value)
    {
        final SetStaticFieldExpression result = this.copy();
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
     * @param owner is the value for property <code>owner</code>.
     * @param name is the value for property <code>name</code>.
     * @param value is the value for property <code>value</code>.
     * @param location is the value for property <code>location</code>.
     * @return a new instance of this class.
     */
    public static SetStaticFieldExpression create(TypeSpecifier owner, Name name, IExpression value, SourceLocation location)
    {
        SetStaticFieldExpression object = new SetStaticFieldExpression();
        object = object.setOwner(owner);
        object = object.setName(name);
        object = object.setValue(value);
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
    public SetStaticFieldExpression copy()
    {
        final SetStaticFieldExpression result = new SetStaticFieldExpression();
        result.owner = this.owner;
        result.name = this.name;
        result.value = this.value;
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
        map.put("owner", this.getOwner());
        map.put("name", this.getName());
        map.put("value", this.getValue());
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
