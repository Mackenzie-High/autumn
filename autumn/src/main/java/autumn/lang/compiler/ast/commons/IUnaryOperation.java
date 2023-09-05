package autumn.lang.compiler.ast.commons;

import com.mackenziehigh.autumn.resources.Finished;

/**
 * An instance of this interface is an AST node that is a unary-operation.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public interface IUnaryOperation
        extends IOperation
{
    /**
     * This method gets the operand expression.
     *
     * @return the operand expression.
     */
    public IExpression getOperand();

    /**
     * This method sets the operand expression.
     *
     * @param operand is the new operand expression.
     * @return a modified copy of this object.
     */
    public IUnaryOperation setOperand(IExpression operand);
}
