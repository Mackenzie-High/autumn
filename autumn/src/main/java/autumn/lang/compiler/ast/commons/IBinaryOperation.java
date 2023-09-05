package autumn.lang.compiler.ast.commons;

import com.mackenziehigh.autumn.resources.Finished;

/**
 * An instance of this interface is an AST node that represents a binary-operation.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public interface IBinaryOperation
        extends IOperation
{
    /**
     * This method gets the left operand expression.
     *
     * @return the left operand.
     */
    public IExpression getLeftOperand();

    /**
     * This method gets the right operand expression.
     *
     * @return the right operand.
     */
    public IExpression getRightOperand();

    /**
     * This method sets the left operand expression.
     *
     * @param operand is the new left operand expression.
     * @return a modified copy of this object.
     */
    public IBinaryOperation setLeftOperand(IExpression operand);

    /**
     * This method sets the right operand expression.
     *
     * @param operand is the new right operand expression.
     * @return a modified copy of this object.
     */
    public IBinaryOperation setRightOperand(IExpression operand);
}
