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
 * 
 * 
 * <p> This file was auto-generated on (Thu Apr 17 06:31:04 EDT 2014).</p>
 */
public abstract class AbstractAstVisitor implements IAstVisitor
{
    /**
     * This function is called before an object is visited by a default visitor.
     * 
     * @param node is the node to visit.
     */
    public void visitBefore(final Object object)
    {
        // Pass
    }
    
    /**
     * This function is called after an object is visited by a default visitor.
     * 
     * @param node is the node to visit.
     */
    public void visitAfter(final Object object)
    {
        // Pass
    }
    
    /**
     * This method visits an object of type <code>Module</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final Module object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>ModuleDirective</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final ModuleDirective object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>ImportDirective</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final ImportDirective object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>Annotation</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final Annotation object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>AnnotationList</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final AnnotationList object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>AnnotationDefinition</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final AnnotationDefinition object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>ExceptionDefinition</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final ExceptionDefinition object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>EnumDefinition</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final EnumDefinition object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>EnumConstant</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final EnumConstant object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>StructDefinition</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final StructDefinition object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>StructProperty</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final StructProperty object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>FunctionDefinition</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final FunctionDefinition object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>IfStatement</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final IfStatement object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>WhenStatement</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final WhenStatement object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>SwitchStatement</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final SwitchStatement object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>GotoStatement</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final GotoStatement object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>MarkerStatement</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final MarkerStatement object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>WhileStatement</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final WhileStatement object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>UntilStatement</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final UntilStatement object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>DoWhileStatement</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final DoWhileStatement object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>DoUntilStatement</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final DoUntilStatement object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>ForStatement</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final ForStatement object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>ForeachStatement</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final ForeachStatement object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>BreakStatement</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final BreakStatement object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>ContinueStatement</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final ContinueStatement object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>RedoStatement</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final RedoStatement object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>VarStatement</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final VarStatement object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>ValStatement</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final ValStatement object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>LetStatement</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final LetStatement object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>SetterStatement</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final SetterStatement object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>GetterStatement</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final GetterStatement object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>MethodStatement</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final MethodStatement object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>SequenceStatement</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final SequenceStatement object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>ExpressionStatement</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final ExpressionStatement object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>NopStatement</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final NopStatement object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>TryCatchStatement</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final TryCatchStatement object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>ExceptionHandler</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final ExceptionHandler object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>ThrowStatement</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final ThrowStatement object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>AssertStatement</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final AssertStatement object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>ReturnVoidStatement</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final ReturnVoidStatement object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>ReturnValueStatement</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final ReturnValueStatement object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>RecurStatement</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final RecurStatement object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>YieldVoidStatement</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final YieldVoidStatement object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>YieldValueStatement</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final YieldValueStatement object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>BooleanDatum</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final BooleanDatum object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>CharDatum</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final CharDatum object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>ByteDatum</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final ByteDatum object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>ShortDatum</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final ShortDatum object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>IntDatum</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final IntDatum object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>LongDatum</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final LongDatum object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>FloatDatum</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final FloatDatum object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>DoubleDatum</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final DoubleDatum object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>StringDatum</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final StringDatum object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>NullDatum</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final NullDatum object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>VariableDatum</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final VariableDatum object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>ClassDatum</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final ClassDatum object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>PrognExpression</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final PrognExpression object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>ListExpression</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final ListExpression object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>DispatchExpression</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final DispatchExpression object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>CallStaticMethodExpression</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final CallStaticMethodExpression object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>SetStaticFieldExpression</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final SetStaticFieldExpression object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>GetStaticFieldExpression</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final GetStaticFieldExpression object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>NewExpression</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final NewExpression object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>CreateExpression</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final CreateExpression object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>CallMethodExpression</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final CallMethodExpression object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>SetFieldExpression</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final SetFieldExpression object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>GetFieldExpression</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final GetFieldExpression object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>InstanceOfExpression</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final InstanceOfExpression object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>FuncallExpression</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final FuncallExpression object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>TernaryConditionalExpression</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final TernaryConditionalExpression object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>DelegateExpression</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final DelegateExpression object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>LocalsExpression</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final LocalsExpression object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>AsOperation</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final AsOperation object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>IsOperation</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final IsOperation object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>NegateOperation</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final NegateOperation object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>NotOperation</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final NotOperation object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>DivideOperation</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final DivideOperation object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>ModuloOperation</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final ModuloOperation object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>MultiplyOperation</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final MultiplyOperation object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>AddOperation</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final AddOperation object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>SubtractOperation</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final SubtractOperation object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>LessThanOperation</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final LessThanOperation object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>LessThanOrEqualsOperation</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final LessThanOrEqualsOperation object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>GreaterThanOperation</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final GreaterThanOperation object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>GreaterThanOrEqualsOperation</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final GreaterThanOrEqualsOperation object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>EqualsOperation</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final EqualsOperation object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>NotEqualsOperation</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final NotEqualsOperation object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>IdentityEqualsOperation</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final IdentityEqualsOperation object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>IdentityNotEqualsOperation</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final IdentityNotEqualsOperation object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>AndOperation</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final AndOperation object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>OrOperation</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final OrOperation object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>XorOperation</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final XorOperation object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>ShortCircuitAndOperation</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final ShortCircuitAndOperation object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>ShortCircuitOrOperation</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final ShortCircuitOrOperation object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>NullCoalescingOperation</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final NullCoalescingOperation object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>ConcatOperation</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final ConcatOperation object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>Variable</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final Variable object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>Name</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final Name object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>TypeSpecifier</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final TypeSpecifier object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>Namespace</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final Namespace object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>FormalParameterList</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final FormalParameterList object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>FormalParameter</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final FormalParameter object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>ConditionalCase</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final ConditionalCase object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>EnumCase</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final EnumCase object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>Label</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final Label object)
    {
        visitBefore(object);
        visitAfter(object);
    }

    /**
     * This method visits an object of type <code>SourceLocation</code>.
     * 
     * <p>
     */
    @Override
    public void visit(final SourceLocation object)
    {
        visitBefore(object);
        visitAfter(object);
    }

}
