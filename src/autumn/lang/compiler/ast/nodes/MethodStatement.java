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
 * An instance of this class is an AST node that represents a statement that binds the implementation of a method to an object.
 * 
 * <p> 
 * <table border="1">
 *     <tr> <td> <b>Property Name</b> </td> <td> <b>Property Description</b> </td> </tr>
 *     <tr> <td> <code>owner</code> </td> <td>This is the mutable variable that contains the object.</td> </tr>
 *     <tr> <td> <code>name</code> </td> <td>This is the name of the method.</td> </tr>
 *     <tr> <td> <code>module</code> </td> <td>This is the type of the module that contains the implementation method.</td> </tr>
 *     <tr> <td> <code>method</code> </td> <td>This is the name of the implementation method.</td> </tr>
 *     <tr> <td> <code>location</code> </td> <td>This is the source-location information regarding this construct.</td> </tr>
 * </table>
 * </p>
 * 
 * <p> This file was auto-generated on (Wed Aug 20 23:42:46 EDT 2014).</p>
 */
@SuppressWarnings("unchecked")
public final class MethodStatement extends Object implements IStatement
{
    private Variable owner;

    private Name name;

    private TypeSpecifier module;

    private Name method;

    private SourceLocation location = new SourceLocation();

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>owner</code>.
     * @return a copy of this object with property <code>owner</code> set to value.
     */
    public MethodStatement setOwner(final Variable value)
    {
        final MethodStatement result = this.copy();
        result.owner = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>owner</code>.
     */
    public Variable getOwner()
    {
        final Variable value = this.owner;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>name</code>.
     * @return a copy of this object with property <code>name</code> set to value.
     */
    public MethodStatement setName(final Name value)
    {
        final MethodStatement result = this.copy();
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
     * @param value is the new value of property <code>module</code>.
     * @return a copy of this object with property <code>module</code> set to value.
     */
    public MethodStatement setModule(final TypeSpecifier value)
    {
        final MethodStatement result = this.copy();
        result.module = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>module</code>.
     */
    public TypeSpecifier getModule()
    {
        final TypeSpecifier value = this.module;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>method</code>.
     * @return a copy of this object with property <code>method</code> set to value.
     */
    public MethodStatement setMethod(final Name value)
    {
        final MethodStatement result = this.copy();
        result.method = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>method</code>.
     */
    public Name getMethod()
    {
        final Name value = this.method;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>location</code>.
     * @return a copy of this object with property <code>location</code> set to value.
     */
    public MethodStatement setLocation(final SourceLocation value)
    {
        final MethodStatement result = this.copy();
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
     * @param module is the value for property <code>module</code>.
     * @param method is the value for property <code>method</code>.
     * @param location is the value for property <code>location</code>.
     * @return a new instance of this class.
     */
    public static MethodStatement create(Variable owner, Name name, TypeSpecifier module, Name method, SourceLocation location)
    {
        MethodStatement object = new MethodStatement();
        object = object.setOwner(owner);
        object = object.setName(name);
        object = object.setModule(module);
        object = object.setMethod(method);
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
    public MethodStatement copy()
    {
        final MethodStatement result = new MethodStatement();
        result.owner = this.owner;
        result.name = this.name;
        result.module = this.module;
        result.method = this.method;
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
        map.put("owner", this.getOwner());
        map.put("name", this.getName());
        map.put("module", this.getModule());
        map.put("method", this.getMethod());
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
