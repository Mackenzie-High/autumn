package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.compiler.ast.commons.IConstruct;
import autumn.lang.compiler.ast.commons.IExpression;
import autumn.lang.compiler.ast.commons.IStatement;
import autumn.lang.compiler.ast.nodes.DelegateStatement;
import autumn.lang.compiler.ast.nodes.DispatchExpression;
import autumn.lang.compiler.ast.nodes.ExceptionHandler;
import autumn.lang.compiler.ast.nodes.GetterStatement;
import autumn.lang.compiler.ast.nodes.MethodStatement;
import autumn.lang.compiler.ast.nodes.SetterStatement;
import autumn.lang.compiler.ast.nodes.TryCatchStatement;
import autumn.lang.compiler.ast.nodes.TypeSpecifier;
import com.google.common.collect.Maps;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IClassType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IExpressionType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IField;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IInvokableMember;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.utils.Conversion;
import high.mackenzie.autumn.lang.compiler.utils.MemberToHandler;
import java.util.List;
import java.util.Map;
import org.objectweb.asm.tree.LabelNode;

/**
 * An instance of this class provides a place to store information needed during compilation.
 *
 * @author Mackenzie High
 */
public final class SymbolTable
{
    /**
     * This map maps an AST node that represents an expression to the expression's output type.
     */
    public final Map<IConstruct, IExpressionType> expressions = Maps.newIdentityHashMap();

    /**
     * This map maps type-specifier to the fully qualified name of the type.
     */
    public final Map<TypeSpecifier, String> specifiers = Maps.newIdentityHashMap();

    /**
     * This map maps an expression that manipulates a field to the manipulated field.
     */
    public final Map<IExpression, IField> fields = Maps.newIdentityHashMap();

    /**
     * This map maps an expression that invokes an invokable to the invoked invokable.
     */
    public final Map<IExpression, IInvokableMember> calls = Maps.newIdentityHashMap();

    /**
     * This map maps an expression that performs a conversion to a description of the conversion.
     */
    public final Map<IExpression, Conversion> conversions = Maps.newIdentityHashMap();

    public final Map<TryCatchStatement, List<ExceptionHandler>> handlers = Maps.newIdentityHashMap();

    public final Map<DispatchExpression, List<IMethod>> dispatches = Maps.newIdentityHashMap();

    /**
     * This map maps a statement that uses a label to the bytecode representation of the label.
     */
    public final Map<IStatement, LabelNode> yields = Maps.newIdentityHashMap();

    /**
     * This map maps an exception-type to the compiler that compiles the exception's class.
     */
    public final Map<IClassType, ExceptionCompiler> exceptions = Maps.newIdentityHashMap();

    /**
     * This map maps setter-statements to to objects that describe the statement's operation.
     */
    public final Map<SetterStatement, MemberToHandler> setters = Maps.newIdentityHashMap();

    /**
     * This map maps getter-statements to to objects that describe the statement's operation.
     */
    public final Map<GetterStatement, MemberToHandler> getters = Maps.newIdentityHashMap();

    /**
     * This map maps method-statements to to objects that describe the statement's operation.
     */
    public final Map<MethodStatement, MemberToHandler> methods = Maps.newIdentityHashMap();

    /**
     * This map maps a delegate-statement to the function that is invoked by the delegate.
     */
    public final Map<DelegateStatement, IMethod> delegates = Maps.newIdentityHashMap();
}
