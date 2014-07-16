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
 * An instance of this class is an AST node that represents the definition of a design.
 * 
 * <p> 
 * <table border="1">
 *     <tr> <td> <b>Property Name</b> </td> <td> <b>Property Description</b> </td> </tr>
 *     <tr> <td> <code>annotations</code> </td> <td>These are the annotations applied directly to the definition.</td> </tr>
 *     <tr> <td> <code>name</code> </td> <td>This is the simple name of the new type.</td> </tr>
 *     <tr> <td> <code>superinterfaces</code> </td> <td>These are the direct superinterfaces of the new type.</td> </tr>
 *     <tr> <td> <code>properties</code> </td> <td>These are the properties declared directly within this definition.</td> </tr>
 *     <tr> <td> <code>methods</code> </td> <td>These are the methods declared directly within this definition.</td> </tr>
 *     <tr> <td> <code>location</code> </td> <td>This is the source-location information regarding this construct.</td> </tr>
 * </table>
 * </p>
 * 
 * <p> This file was auto-generated on (Mon Jul 14 10:23:51 EDT 2014).</p>
 */
@SuppressWarnings("unchecked")
public final class DesignDefinition extends Object implements IAnnotated
{
    private AnnotationList annotations = new AnnotationList();

    private Name name;

    private ConstructList<TypeSpecifier> superinterfaces = new ConstructList();

    private ConstructList<DesignProperty> properties = new ConstructList<DesignProperty>();

    private ConstructList<DesignMethod> methods = new ConstructList<DesignMethod>();

    private SourceLocation location = new SourceLocation();

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>annotations</code>.
     * @return a copy of this object with property <code>annotations</code> set to value.
     */
    public DesignDefinition setAnnotations(final AnnotationList value)
    {
        final DesignDefinition result = this.copy();
        result.annotations = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>annotations</code>.
     */
    public AnnotationList getAnnotations()
    {
        final AnnotationList value = this.annotations;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>name</code>.
     * @return a copy of this object with property <code>name</code> set to value.
     */
    public DesignDefinition setName(final Name value)
    {
        final DesignDefinition result = this.copy();
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
     * @param value is the new value of property <code>superinterfaces</code>.
     * @return a copy of this object with property <code>superinterfaces</code> set to value.
     */
    public DesignDefinition setSuperinterfaces(final ConstructList<TypeSpecifier> value)
    {
        final DesignDefinition result = this.copy();
        result.superinterfaces = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>superinterfaces</code>.
     */
    public ConstructList<TypeSpecifier> getSuperinterfaces()
    {
        final ConstructList<TypeSpecifier> value = this.superinterfaces;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>properties</code>.
     * @return a copy of this object with property <code>properties</code> set to value.
     */
    public DesignDefinition setProperties(final ConstructList<DesignProperty> value)
    {
        final DesignDefinition result = this.copy();
        result.properties = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>properties</code>.
     */
    public ConstructList<DesignProperty> getProperties()
    {
        final ConstructList<DesignProperty> value = this.properties;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>methods</code>.
     * @return a copy of this object with property <code>methods</code> set to value.
     */
    public DesignDefinition setMethods(final ConstructList<DesignMethod> value)
    {
        final DesignDefinition result = this.copy();
        result.methods = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>methods</code>.
     */
    public ConstructList<DesignMethod> getMethods()
    {
        final ConstructList<DesignMethod> value = this.methods;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>location</code>.
     * @return a copy of this object with property <code>location</code> set to value.
     */
    public DesignDefinition setLocation(final SourceLocation value)
    {
        final DesignDefinition result = this.copy();
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
     * @param annotations is the value for property <code>annotations</code>.
     * @param name is the value for property <code>name</code>.
     * @param superinterfaces is the value for property <code>superinterfaces</code>.
     * @param properties is the value for property <code>properties</code>.
     * @param methods is the value for property <code>methods</code>.
     * @param location is the value for property <code>location</code>.
     * @return a new instance of this class.
     */
    public static DesignDefinition create(AnnotationList annotations, Name name, ConstructList<TypeSpecifier> superinterfaces, ConstructList<DesignProperty> properties, ConstructList<DesignMethod> methods, SourceLocation location)
    {
        DesignDefinition object = new DesignDefinition();
        object = object.setAnnotations(annotations);
        object = object.setName(name);
        object = object.setSuperinterfaces(superinterfaces);
        object = object.setProperties(properties);
        object = object.setMethods(methods);
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
    public DesignDefinition copy()
    {
        final DesignDefinition result = new DesignDefinition();
        result.annotations = this.annotations;
        result.name = this.name;
        result.superinterfaces = this.superinterfaces;
        result.properties = this.properties;
        result.methods = this.methods;
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
        map.put("annotations", this.getAnnotations());
        map.put("name", this.getName());
        map.put("superinterfaces", this.getSuperinterfaces());
        map.put("properties", this.getProperties());
        map.put("methods", this.getMethods());
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
