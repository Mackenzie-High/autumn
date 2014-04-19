/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autumn.lang.compiler.ast.commons;

import autumn.lang.compiler.ast.nodes.TypeSpecifier;

/**
 * An instance of this interface is an AST node that represents a conversion-operation.
 *
 * @author Mackenzie High
 */
public interface IConversionOperation
        extends IOperation
{
    public IExpression getValue();

    public IConstruct setValue(IExpression value);

    public TypeSpecifier getType();

    public IConstruct setType(TypeSpecifier type);
}
