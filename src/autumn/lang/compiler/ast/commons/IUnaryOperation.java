/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autumn.lang.compiler.ast.commons;

/**
 * An instance of this interface is an AST node that is a unary-operation.
 *
 * @author Mackenzie High
 */
public interface IUnaryOperation
        extends IOperation
{
    public IExpression getOperand();

    public IUnaryOperation setOperand(IExpression operand);
}
