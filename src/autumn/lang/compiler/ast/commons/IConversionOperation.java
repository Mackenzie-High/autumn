package autumn.lang.compiler.ast.commons;

import autumn.lang.compiler.ast.nodes.TypeSpecifier;
import high.mackenzie.autumn.resources.Finished;

/**
 * An instance of this interface is an AST node that represents a conversion-operation.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public interface IConversionOperation
        extends IOperation
{
    /**
     * This method gets the expression that produces the value to convert.
     *
     * @return the operand expression.
     */
    public IExpression getValue();

    /**
     * This method sets the expression that produces the value to convert.
     *
     * @return a modified copy of this object.
     */
    public IConstruct setValue(IExpression value);

    /**
     * This method gets the type-specifier that specifies the output-type of the conversion.
     *
     * @return the output-type type-specifier.
     */
    public TypeSpecifier getType();

    /**
     * This method sets the type-specifier that specifies the output-type of the conversion.
     *
     * @return a modified copy of this object.
     */
    public IConstruct setType(TypeSpecifier type);
}
