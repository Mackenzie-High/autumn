package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.compiler.TreeBuilder;
import autumn.lang.compiler.ast.commons.IExpression;
import autumn.lang.compiler.ast.nodes.Name;
import autumn.lang.compiler.ast.nodes.TypeSpecifier;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IClassType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IDeclaredType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IExpressionType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IField;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IReturnType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IType;
import java.util.List;

/**
 * This class provides methods needed by all type-checkers.
 *
 * @author Mackenzie High
 */
public abstract class AbstractTypeChecker
        extends AbstractAstVisitor
{
    protected final ProgramCompiler program;

    protected final ModuleCompiler module;

    protected final FunctionCompiler function;

    protected AbstractTypeChecker(final FunctionCompiler function)
    {
        this.program = function.module.program;
        this.module = function.module;
        this.function = function;
    }

    protected void infer(final IExpression node,
                         final IExpressionType type)
    {
        program.symbols.expressions.put(node, type);
    }

    protected void condition(final IExpression expression)
    {
        expression.accept(this);

        program.checker.checkCondition(expression);
    }

    protected void assertType(final IReturnType expected,
                              final IExpression expression)
    {
        expression.accept(this);

        program.checker.expectSubtype(expression, expected);
    }

    protected void unaryOperation(final IExpression call_site,
                                  final String function,
                                  final IExpression operand)
    {
        final TreeBuilder builder = new TreeBuilder();

        builder.createComponentName("autumn");
        builder.createComponentName("lang");
        builder.createComponentName("internals");
        builder.createComponentNamespace();
        builder.createComponentName("Operators");
        builder.createComponentTypeSpecifier();
        final TypeSpecifier owner = (TypeSpecifier) builder.pop();

        builder.createComponentName(function);
        final Name method = (Name) builder.pop();

        callStaticMethod(call_site, owner, method, ImmutableList.of(operand));
    }

    protected void binaryOperation(final IExpression call_site,
                                   final String function,
                                   final IExpression left,
                                   final IExpression right)
    {
        final TreeBuilder builder = new TreeBuilder();

        builder.createComponentName("autumn");
        builder.createComponentName("lang");
        builder.createComponentName("internals");
        builder.createComponentNamespace();
        builder.createComponentName("Operators");
        builder.createComponentTypeSpecifier();
        final TypeSpecifier owner = (TypeSpecifier) builder.pop();

        builder.createComponentName(function);
        final Name method = (Name) builder.pop();

        callStaticMethod(call_site, owner, method, ImmutableList.of(left, right));
    }

    protected void callStaticMethod(final IExpression call_site,
                                    final TypeSpecifier owner,
                                    final Name name,
                                    final Iterable<IExpression> arguments)
    {
        final List<IType> args = Lists.newLinkedList();

        for (IExpression arg : arguments)
        {
            arg.accept(this);
            args.add(program.symbols.expressions.get(arg));
        }

        final IType type = module.resolveType(owner);

        // TODO: Check that the owner is a declared type.

        final IDeclaredType declared_type = (IDeclaredType) type;

        final List<IMethod> methods = program.typesystem.utils.resolveStaticMethods(module.type,
                                                                                    declared_type,
                                                                                    name.getName(),
                                                                                    args);

        if (methods.isEmpty())
        {
            // TODO: Compile Error
            throw new RuntimeException("No Such Method: " + name.getName() + " " + args);
        }

        final IMethod method = (IMethod) methods.get(0);

        program.symbols.calls.put(call_site, method);
        infer(call_site, method.getReturnType());
    }

    /**
     * This method performs the resolution of a static-field.
     *
     * @param site is the site where the field is being used.
     * @param type is the type that contains the field.
     * @param name is the name of the field.
     * @return the field that was found, or null, if no field was found.
     */
    protected IField findStaticField(final IExpression site,
                                     final TypeSpecifier type,
                                     final String name)
    {
        final IClassType user = module.type;

        final IType owner = module.resolveType(type);

        if (owner == null)
        {
            // TODO: error
        }

        if (owner instanceof IDeclaredType == false)
        {
            // TODO: error
        }

        final IField field = program.typesystem.utils.resolveStaticField(user,
                                                                         (IDeclaredType) owner,
                                                                         name);

        if (field == null)
        {
            // TODO: error
            System.out.println("No such field: " + name);
            return null;
        }

        /**
         * Remember the field that is being set, so that the code-generator can emit bytecode.
         */
        program.symbols.fields.put(site, field);

        return field;
    }

    /**
     * This method performs the resolution of an instance-field.
     *
     * @param site is the site where the field is being used.
     * @param object is the expression that produces the owner of the field.
     * @param name is the name of the field.
     * @return the field that was found, or null, if no field was found.
     */
    protected IField findField(final IExpression site,
                               final IExpression object,
                               final String name)
    {
        final IClassType user = module.type;

        final IType owner = program.symbols.expressions.get(object);

        if (owner == null)
        {
            // TODO: error
        }

        if (owner instanceof IDeclaredType == false)
        {
            // TODO: error
        }

        final IField field = program.typesystem.utils.resolveField(user,
                                                                   (IDeclaredType) owner,
                                                                   name);

        if (field == null)
        {
            // TODO: error
            System.out.println("No such field: " + name);
            return null;
        }

        /**
         * Remember the field that is being set, so that the code-generator can emit bytecode.
         */
        program.symbols.fields.put(site, field);

        return field;
    }

    /**
     * This method ensures that an expression is assignable to a particular type.
     *
     * <p>
     * This method takes into account boxing and unboxing.
     * </p>
     *
     * @param target is the type of the location where the value will be assigned.
     * @param expression is the expression that produces the value to assign.
     */
    protected void checkAssign(final IExpressionType target,
                               final IExpression expression)
    {
        final IType etype = program.symbols.expressions.get(expression);

        if (program.typesystem.utils.box(etype, target) != null)
        {
            return; // OK
        }
        else if (program.typesystem.utils.unbox(etype, target) != null)
        {
            return; // OK
        }
        else
        {
            program.checker.expectSubtype(expression, target);
        }
    }
}
