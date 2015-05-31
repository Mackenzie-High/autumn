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
 * An instance of this class is an AST node that represents a branch-statement.
 * 
 * <p> 
 * <table border="1">
 *     <tr> <td> <b>Property Name</b> </td> <td> <b>Property Description</b> </td> </tr>
 *     <tr> <td> <code>index</code> </td> <td>This is the expression whose value is used to select the label to branch to.</td> </tr>
 *     <tr> <td> <code>labels</code> </td> <td>These are the labels that indicate where to jump to, excluding the default-case.</td> </tr>
 *     <tr> <td> <code>default_label</code> </td> <td>This is the label that indicates where to jump to, if the index is out-of-bounds.</td> </tr>
 *     <tr> <td> <code>location</code> </td> <td>This is the source-location information regarding this construct.</td> </tr>
 * </table>
 * </p>
 * 
 * <p> This file was auto-generated on (Sun May 31 11:54:12 EDT 2015).</p>
 */
@SuppressWarnings("unchecked")
public final class BranchStatement extends Object implements IStatement
{
    private IExpression index;

    private ConstructList<Label> labels = new ConstructList();

    private Label default_label;

    private SourceLocation location = new SourceLocation();

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>index</code>.
     * @return a copy of this object with property <code>index</code> set to value.
     */
    public BranchStatement setIndex(final IExpression value)
    {
        final BranchStatement result = this.copy();
        result.index = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>index</code>.
     */
    public IExpression getIndex()
    {
        final IExpression value = this.index;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>labels</code>.
     * @return a copy of this object with property <code>labels</code> set to value.
     */
    public BranchStatement setLabels(final ConstructList<Label> value)
    {
        final BranchStatement result = this.copy();
        result.labels = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>labels</code>.
     */
    public ConstructList<Label> getLabels()
    {
        final ConstructList<Label> value = this.labels;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>default_label</code>.
     * @return a copy of this object with property <code>default_label</code> set to value.
     */
    public BranchStatement setDefaultLabel(final Label value)
    {
        final BranchStatement result = this.copy();
        result.default_label = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>default_label</code>.
     */
    public Label getDefaultLabel()
    {
        final Label value = this.default_label;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>location</code>.
     * @return a copy of this object with property <code>location</code> set to value.
     */
    public BranchStatement setLocation(final SourceLocation value)
    {
        final BranchStatement result = this.copy();
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
     * @param index is the value for property <code>index</code>.
     * @param labels is the value for property <code>labels</code>.
     * @param default_label is the value for property <code>default_label</code>.
     * @param location is the value for property <code>location</code>.
     * @return a new instance of this class.
     */
    public static BranchStatement create(IExpression index, ConstructList<Label> labels, Label default_label, SourceLocation location)
    {
        BranchStatement object = new BranchStatement();
        object = object.setIndex(index);
        object = object.setLabels(labels);
        object = object.setDefaultLabel(default_label);
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
    public BranchStatement copy()
    {
        final BranchStatement result = new BranchStatement();
        result.index = this.index;
        result.labels = this.labels;
        result.default_label = this.default_label;
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
        map.put("index", this.getIndex());
        map.put("labels", this.getLabels());
        map.put("default_label", this.getDefaultLabel());
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
