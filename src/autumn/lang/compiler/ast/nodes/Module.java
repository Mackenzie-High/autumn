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
 * An instance of this class is an AST node that represents a single module.
 * 
 * <p> 
 * <table border="1">
 *     <tr> <td> <b>Property Name</b> </td> <td> <b>Property Description</b> </td> </tr>
 *     <tr> <td> <code>module_directives</code> </td> <td>These are the module-directives that are directly within this module.</td> </tr>
 *     <tr> <td> <code>import_directives</code> </td> <td>These are the import-directives that are directly within this module.</td> </tr>
 *     <tr> <td> <code>annotations</code> </td> <td>These are the annotations declared directly within this module.</td> </tr>
 *     <tr> <td> <code>exceptions</code> </td> <td>These are the exceptions declared directly within this module.</td> </tr>
 *     <tr> <td> <code>enums</code> </td> <td>These are the enums declared directly within this module.</td> </tr>
 *     <tr> <td> <code>designs</code> </td> <td>These are the designs declared directly within this module.</td> </tr>
 *     <tr> <td> <code>functions</code> </td> <td>These are the functions declared directly within this module.</td> </tr>
 *     <tr> <td> <code>location</code> </td> <td>This is the source-location information regarding this construct.</td> </tr>
 * </table>
 * </p>
 * 
 * <p> This file was auto-generated on (Mon Jul 14 10:23:51 EDT 2014).</p>
 */
@SuppressWarnings("unchecked")
public final class Module extends Object implements IConstruct
{
    private ConstructList<ModuleDirective> module_directives = new ConstructList();

    private ConstructList<ImportDirective> import_directives = new ConstructList();

    private ConstructList<AnnotationDefinition> annotations = new ConstructList();

    private ConstructList<ExceptionDefinition> exceptions = new ConstructList();

    private ConstructList<EnumDefinition> enums = new ConstructList();

    private ConstructList<DesignDefinition> designs = new ConstructList();

    private ConstructList<FunctionDefinition> functions = new ConstructList<FunctionDefinition>();

    private SourceLocation location = new SourceLocation();

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>module_directives</code>.
     * @return a copy of this object with property <code>module_directives</code> set to value.
     */
    public Module setModuleDirectives(final ConstructList<ModuleDirective> value)
    {
        final Module result = this.copy();
        result.module_directives = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>module_directives</code>.
     */
    public ConstructList<ModuleDirective> getModuleDirectives()
    {
        final ConstructList<ModuleDirective> value = this.module_directives;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>import_directives</code>.
     * @return a copy of this object with property <code>import_directives</code> set to value.
     */
    public Module setImportDirectives(final ConstructList<ImportDirective> value)
    {
        final Module result = this.copy();
        result.import_directives = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>import_directives</code>.
     */
    public ConstructList<ImportDirective> getImportDirectives()
    {
        final ConstructList<ImportDirective> value = this.import_directives;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>annotations</code>.
     * @return a copy of this object with property <code>annotations</code> set to value.
     */
    public Module setAnnotations(final ConstructList<AnnotationDefinition> value)
    {
        final Module result = this.copy();
        result.annotations = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>annotations</code>.
     */
    public ConstructList<AnnotationDefinition> getAnnotations()
    {
        final ConstructList<AnnotationDefinition> value = this.annotations;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>exceptions</code>.
     * @return a copy of this object with property <code>exceptions</code> set to value.
     */
    public Module setExceptions(final ConstructList<ExceptionDefinition> value)
    {
        final Module result = this.copy();
        result.exceptions = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>exceptions</code>.
     */
    public ConstructList<ExceptionDefinition> getExceptions()
    {
        final ConstructList<ExceptionDefinition> value = this.exceptions;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>enums</code>.
     * @return a copy of this object with property <code>enums</code> set to value.
     */
    public Module setEnums(final ConstructList<EnumDefinition> value)
    {
        final Module result = this.copy();
        result.enums = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>enums</code>.
     */
    public ConstructList<EnumDefinition> getEnums()
    {
        final ConstructList<EnumDefinition> value = this.enums;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>designs</code>.
     * @return a copy of this object with property <code>designs</code> set to value.
     */
    public Module setDesigns(final ConstructList<DesignDefinition> value)
    {
        final Module result = this.copy();
        result.designs = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>designs</code>.
     */
    public ConstructList<DesignDefinition> getDesigns()
    {
        final ConstructList<DesignDefinition> value = this.designs;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>functions</code>.
     * @return a copy of this object with property <code>functions</code> set to value.
     */
    public Module setFunctions(final ConstructList<FunctionDefinition> value)
    {
        final Module result = this.copy();
        result.functions = value;
        return result;
    }

    /**
     * Getter.
     * 
     * @return the value of property <code>functions</code>.
     */
    public ConstructList<FunctionDefinition> getFunctions()
    {
        final ConstructList<FunctionDefinition> value = this.functions;
        return value;
    }

    /**
     * Setter.
     * 
     * @param value is the new value of property <code>location</code>.
     * @return a copy of this object with property <code>location</code> set to value.
     */
    public Module setLocation(final SourceLocation value)
    {
        final Module result = this.copy();
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
     * @param module_directives is the value for property <code>module_directives</code>.
     * @param import_directives is the value for property <code>import_directives</code>.
     * @param annotations is the value for property <code>annotations</code>.
     * @param exceptions is the value for property <code>exceptions</code>.
     * @param enums is the value for property <code>enums</code>.
     * @param designs is the value for property <code>designs</code>.
     * @param functions is the value for property <code>functions</code>.
     * @param location is the value for property <code>location</code>.
     * @return a new instance of this class.
     */
    public static Module create(ConstructList<ModuleDirective> module_directives, ConstructList<ImportDirective> import_directives, ConstructList<AnnotationDefinition> annotations, ConstructList<ExceptionDefinition> exceptions, ConstructList<EnumDefinition> enums, ConstructList<DesignDefinition> designs, ConstructList<FunctionDefinition> functions, SourceLocation location)
    {
        Module object = new Module();
        object = object.setModuleDirectives(module_directives);
        object = object.setImportDirectives(import_directives);
        object = object.setAnnotations(annotations);
        object = object.setExceptions(exceptions);
        object = object.setEnums(enums);
        object = object.setDesigns(designs);
        object = object.setFunctions(functions);
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
    public Module copy()
    {
        final Module result = new Module();
        result.module_directives = this.module_directives;
        result.import_directives = this.import_directives;
        result.annotations = this.annotations;
        result.exceptions = this.exceptions;
        result.enums = this.enums;
        result.designs = this.designs;
        result.functions = this.functions;
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
        map.put("module_directives", this.getModuleDirectives());
        map.put("import_directives", this.getImportDirectives());
        map.put("annotations", this.getAnnotations());
        map.put("exceptions", this.getExceptions());
        map.put("enums", this.getEnums());
        map.put("designs", this.getDesigns());
        map.put("functions", this.getFunctions());
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
