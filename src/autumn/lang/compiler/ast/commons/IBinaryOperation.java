/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autumn.lang.compiler.ast.commons;

/**
 * An instance of this interface is an AST node that represents a binary-operation.
 *
 * @author Mackenzie High
 */
public interface IBinaryOperation
        extends IOperation
{
    public IExpression getLeftOperand();

    public IExpression getRightOperand();

    public IBinaryOperation setLeftOperand(IExpression operand);

    public IBinaryOperation setRightOperand(IExpression operand);
}
