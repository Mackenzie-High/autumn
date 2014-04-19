/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.compiler.ast.commons.IConstruct;
import autumn.lang.compiler.ast.commons.IExpression;
import autumn.lang.compiler.ast.commons.IStatement;
import autumn.lang.compiler.ast.nodes.CreateExpression;
import autumn.lang.compiler.ast.nodes.DelegateExpression;
import autumn.lang.compiler.ast.nodes.DispatchExpression;
import autumn.lang.compiler.ast.nodes.ExceptionHandler;
import autumn.lang.compiler.ast.nodes.TryCatchStatement;
import autumn.lang.compiler.ast.nodes.TypeSpecifier;
import com.google.common.collect.Maps;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IClassType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IExpressionType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IField;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IInvokableMember;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.utils.Conversion;
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

    public final Map<IExpression, IField> fields = Maps.newIdentityHashMap();

    public final Map<IExpression, IInvokableMember> calls = Maps.newIdentityHashMap();

    public final Map<IExpression, Conversion> conversions = Maps.newIdentityHashMap();

    public final Map<TryCatchStatement, List<ExceptionHandler>> handlers = Maps.newIdentityHashMap();

    public final Map<DispatchExpression, List<IMethod>> dispatches = Maps.newIdentityHashMap();

    public final Map<IStatement, LabelNode> yields = Maps.newIdentityHashMap();

    public final Map<DelegateExpression, DelegateCompiler> delegates = Maps.newIdentityHashMap();

    public final Map<CreateExpression, ClassCompiler> creators = Maps.newIdentityHashMap();

    public final Map<IClassType, ExceptionCompiler> exceptions = Maps.newIdentityHashMap();
}
