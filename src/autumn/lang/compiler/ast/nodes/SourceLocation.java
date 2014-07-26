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
 * An instance of this class stores the source-location information for an enclosing construct.
 * 
 * <p> 
 * <table border="1">
 *     <tr> <td> <b>Property Name</b> </td> <td> <b>Property Description</b> </td> </tr>
 *     <tr> <td> <code>file</code> </td> <td>(optional) This is the path to the source-code file.</td> </tr>
 *     <tr> <td> <code>line</code> </td> <td>(optional) This is the one-based index of the line within the source-file.</td> </tr>
 *     <tr> <td> <code>column</code> </td> <td>(optional) This is the one-based index of the column within the line within the source-file.</td> </tr>
 *     <tr> <td> <code>additional_info</code> </td> <td>(optional) This is a place for things like IDEs to store additional location information.</td> </tr>
 * </table>
 * </p>
 * 
 * <p> This file was auto-generated on (Thu Jul 24 16:15:35 EDT 2014).</p>
 */
@SuppressWarnings("unchecked")
public final class SourceLocation extends Object 
{
    private File file = null;

    private Integer line = null;

    private Integer column = null;

    private Object additional_info = null;

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>file</code>.
     * @return a copy of this object with property <code>file</code> set to value.
     */
    public SourceLocation setFile(final File value)
    {
        final SourceLocation result = this.copy();
        result.file = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>file</code>.
     */
    public File getFile()
    {
        final File value = this.file;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>line</code>.
     * @return a copy of this object with property <code>line</code> set to value.
     */
    public SourceLocation setLine(final Integer value)
    {
        final SourceLocation result = this.copy();
        result.line = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>line</code>.
     */
    public Integer getLine()
    {
        final Integer value = this.line;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>column</code>.
     * @return a copy of this object with property <code>column</code> set to value.
     */
    public SourceLocation setColumn(final Integer value)
    {
        final SourceLocation result = this.copy();
        result.column = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>column</code>.
     */
    public Integer getColumn()
    {
        final Integer value = this.column;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>additional_info</code>.
     * @return a copy of this object with property <code>additional_info</code> set to value.
     */
    public SourceLocation setAdditionalInfo(final Object value)
    {
        final SourceLocation result = this.copy();
        result.additional_info = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>additional_info</code>.
     */
    public Object getAdditionalInfo()
    {
        final Object value = this.additional_info;
        return value;
    }

    /**
     * This method creates a new instance of this class.
     * 
     * @param file is the value for property <code>file</code>.
     * @param line is the value for property <code>line</code>.
     * @param column is the value for property <code>column</code>.
     * @param additional_info is the value for property <code>additional_info</code>.
     * @return a new instance of this class.
     */
    public static SourceLocation create(File file, Integer line, Integer column, Object additional_info)
    {
        SourceLocation object = new SourceLocation();
        object = object.setFile(file);
        object = object.setLine(line);
        object = object.setColumn(column);
        object = object.setAdditionalInfo(additional_info);
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
    public SourceLocation copy()
    {
        final SourceLocation result = new SourceLocation();
        result.file = this.file;
        result.line = this.line;
        result.column = this.column;
        result.additional_info = this.additional_info;
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
        map.put("file", this.getFile());
        map.put("line", this.getLine());
        map.put("column", this.getColumn());
        map.put("additional_info", this.getAdditionalInfo());

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
