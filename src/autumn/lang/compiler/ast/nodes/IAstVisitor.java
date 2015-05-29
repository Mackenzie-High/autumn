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
import autumn.lang.compiler.ast.commons.IDatum;
import autumn.lang.compiler.ast.commons.IDirective;
import autumn.lang.compiler.ast.commons.IDocumented;
import autumn.lang.compiler.ast.commons.IExpression;
import autumn.lang.compiler.ast.commons.IRecord;
import autumn.lang.compiler.ast.commons.IStatement;
import autumn.lang.compiler.ast.commons.IUnaryOperation;
import autumn.lang.compiler.ast.literals.BigDecimalLiteral;
import autumn.lang.compiler.ast.literals.BigIntegerLiteral;
import autumn.lang.compiler.ast.literals.ByteLiteral;
import autumn.lang.compiler.ast.literals.CharLiteral;
import autumn.lang.compiler.ast.literals.DoubleLiteral;
import autumn.lang.compiler.ast.literals.FloatLiteral;
import autumn.lang.compiler.ast.literals.IntLiteral;
import autumn.lang.compiler.ast.literals.LongLiteral;
import autumn.lang.compiler.ast.literals.ShortLiteral;
import java.io.File;
import java.net.URL;
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
 * An instance of this interface is a visitor that can visit the nodes in an Autumn abstract-syntax-tree.
 * 
 * <p> This file was auto-generated on (Sun May 24 06:57:14 EDT 2015).</p>
 */
public interface IAstVisitor
{
    /**
     * This method visits an object of type <code>Module</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final Module object);

    /**
     * This method visits an object of type <code>ModuleDirective</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final ModuleDirective object);

    /**
     * This method visits an object of type <code>ImportDirective</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final ImportDirective object);

    /**
     * This method visits an object of type <code>AnnotationDefinition</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final AnnotationDefinition object);

    /**
     * This method visits an object of type <code>ExceptionDefinition</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final ExceptionDefinition object);

    /**
     * This method visits an object of type <code>FunctorDefinition</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final FunctorDefinition object);

    /**
     * This method visits an object of type <code>EnumDefinition</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final EnumDefinition object);

    /**
     * This method visits an object of type <code>DesignDefinition</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final DesignDefinition object);

    /**
     * This method visits an object of type <code>TupleDefinition</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final TupleDefinition object);

    /**
     * This method visits an object of type <code>StructDefinition</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final StructDefinition object);

    /**
     * This method visits an object of type <code>FunctionDefinition</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final FunctionDefinition object);

    /**
     * This method visits an object of type <code>IfStatement</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final IfStatement object);

    /**
     * This method visits an object of type <code>WhenStatement</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final WhenStatement object);

    /**
     * This method visits an object of type <code>GotoStatement</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final GotoStatement object);

    /**
     * This method visits an object of type <code>MarkerStatement</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final MarkerStatement object);

    /**
     * This method visits an object of type <code>BranchStatement</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final BranchStatement object);

    /**
     * This method visits an object of type <code>ForeverStatement</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final ForeverStatement object);

    /**
     * This method visits an object of type <code>WhileStatement</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final WhileStatement object);

    /**
     * This method visits an object of type <code>UntilStatement</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final UntilStatement object);

    /**
     * This method visits an object of type <code>DoWhileStatement</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final DoWhileStatement object);

    /**
     * This method visits an object of type <code>DoUntilStatement</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final DoUntilStatement object);

    /**
     * This method visits an object of type <code>ForStatement</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final ForStatement object);

    /**
     * This method visits an object of type <code>ForeachStatement</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final ForeachStatement object);

    /**
     * This method visits an object of type <code>BreakStatement</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final BreakStatement object);

    /**
     * This method visits an object of type <code>ContinueStatement</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final ContinueStatement object);

    /**
     * This method visits an object of type <code>RedoStatement</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final RedoStatement object);

    /**
     * This method visits an object of type <code>VarStatement</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final VarStatement object);

    /**
     * This method visits an object of type <code>ValStatement</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final ValStatement object);

    /**
     * This method visits an object of type <code>LetStatement</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final LetStatement object);

    /**
     * This method visits an object of type <code>LambdaStatement</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final LambdaStatement object);

    /**
     * This method visits an object of type <code>DelegateStatement</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final DelegateStatement object);

    /**
     * This method visits an object of type <code>SequenceStatement</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final SequenceStatement object);

    /**
     * This method visits an object of type <code>ExpressionStatement</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final ExpressionStatement object);

    /**
     * This method visits an object of type <code>NopStatement</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final NopStatement object);

    /**
     * This method visits an object of type <code>TryCatchStatement</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final TryCatchStatement object);

    /**
     * This method visits an object of type <code>ExceptionHandler</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final ExceptionHandler object);

    /**
     * This method visits an object of type <code>ThrowStatement</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final ThrowStatement object);

    /**
     * This method visits an object of type <code>AssertStatement</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final AssertStatement object);

    /**
     * This method visits an object of type <code>AssumeStatement</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final AssumeStatement object);

    /**
     * This method visits an object of type <code>ReturnVoidStatement</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final ReturnVoidStatement object);

    /**
     * This method visits an object of type <code>ReturnValueStatement</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final ReturnValueStatement object);

    /**
     * This method visits an object of type <code>RecurStatement</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final RecurStatement object);

    /**
     * This method visits an object of type <code>BooleanDatum</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final BooleanDatum object);

    /**
     * This method visits an object of type <code>CharDatum</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final CharDatum object);

    /**
     * This method visits an object of type <code>ByteDatum</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final ByteDatum object);

    /**
     * This method visits an object of type <code>ShortDatum</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final ShortDatum object);

    /**
     * This method visits an object of type <code>IntDatum</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final IntDatum object);

    /**
     * This method visits an object of type <code>LongDatum</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final LongDatum object);

    /**
     * This method visits an object of type <code>FloatDatum</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final FloatDatum object);

    /**
     * This method visits an object of type <code>DoubleDatum</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final DoubleDatum object);

    /**
     * This method visits an object of type <code>BigIntegerDatum</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final BigIntegerDatum object);

    /**
     * This method visits an object of type <code>BigDecimalDatum</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final BigDecimalDatum object);

    /**
     * This method visits an object of type <code>StringDatum</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final StringDatum object);

    /**
     * This method visits an object of type <code>NullDatum</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final NullDatum object);

    /**
     * This method visits an object of type <code>VariableDatum</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final VariableDatum object);

    /**
     * This method visits an object of type <code>ClassDatum</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final ClassDatum object);

    /**
     * This method visits an object of type <code>PrognExpression</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final PrognExpression object);

    /**
     * This method visits an object of type <code>ListComprehensionExpression</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final ListComprehensionExpression object);

    /**
     * This method visits an object of type <code>ListExpression</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final ListExpression object);

    /**
     * This method visits an object of type <code>DispatchExpression</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final DispatchExpression object);

    /**
     * This method visits an object of type <code>CallStaticMethodExpression</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final CallStaticMethodExpression object);

    /**
     * This method visits an object of type <code>SetStaticFieldExpression</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final SetStaticFieldExpression object);

    /**
     * This method visits an object of type <code>GetStaticFieldExpression</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final GetStaticFieldExpression object);

    /**
     * This method visits an object of type <code>NewExpression</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final NewExpression object);

    /**
     * This method visits an object of type <code>CallMethodExpression</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final CallMethodExpression object);

    /**
     * This method visits an object of type <code>SetFieldExpression</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final SetFieldExpression object);

    /**
     * This method visits an object of type <code>GetFieldExpression</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final GetFieldExpression object);

    /**
     * This method visits an object of type <code>InstanceOfExpression</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final InstanceOfExpression object);

    /**
     * This method visits an object of type <code>TernaryConditionalExpression</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final TernaryConditionalExpression object);

    /**
     * This method visits an object of type <code>LocalsExpression</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final LocalsExpression object);

    /**
     * This method visits an object of type <code>OnceExpression</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final OnceExpression object);

    /**
     * This method visits an object of type <code>AsOperation</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final AsOperation object);

    /**
     * This method visits an object of type <code>IsOperation</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final IsOperation object);

    /**
     * This method visits an object of type <code>NegateOperation</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final NegateOperation object);

    /**
     * This method visits an object of type <code>NotOperation</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final NotOperation object);

    /**
     * This method visits an object of type <code>DivideOperation</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final DivideOperation object);

    /**
     * This method visits an object of type <code>ModuloOperation</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final ModuloOperation object);

    /**
     * This method visits an object of type <code>MultiplyOperation</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final MultiplyOperation object);

    /**
     * This method visits an object of type <code>AddOperation</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final AddOperation object);

    /**
     * This method visits an object of type <code>SubtractOperation</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final SubtractOperation object);

    /**
     * This method visits an object of type <code>LessThanOperation</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final LessThanOperation object);

    /**
     * This method visits an object of type <code>LessThanOrEqualsOperation</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final LessThanOrEqualsOperation object);

    /**
     * This method visits an object of type <code>GreaterThanOperation</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final GreaterThanOperation object);

    /**
     * This method visits an object of type <code>GreaterThanOrEqualsOperation</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final GreaterThanOrEqualsOperation object);

    /**
     * This method visits an object of type <code>EqualsOperation</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final EqualsOperation object);

    /**
     * This method visits an object of type <code>NotEqualsOperation</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final NotEqualsOperation object);

    /**
     * This method visits an object of type <code>IdentityEqualsOperation</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final IdentityEqualsOperation object);

    /**
     * This method visits an object of type <code>IdentityNotEqualsOperation</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final IdentityNotEqualsOperation object);

    /**
     * This method visits an object of type <code>AndOperation</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final AndOperation object);

    /**
     * This method visits an object of type <code>OrOperation</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final OrOperation object);

    /**
     * This method visits an object of type <code>XorOperation</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final XorOperation object);

    /**
     * This method visits an object of type <code>ImpliesOperation</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final ImpliesOperation object);

    /**
     * This method visits an object of type <code>NullCoalescingOperation</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final NullCoalescingOperation object);

    /**
     * This method visits an object of type <code>ConcatOperation</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final ConcatOperation object);

    /**
     * This method visits an object of type <code>DocComment</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final DocComment object);

    /**
     * This method visits an object of type <code>DocCommentLine</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final DocCommentLine object);

    /**
     * This method visits an object of type <code>Annotation</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final Annotation object);

    /**
     * This method visits an object of type <code>AnnotationList</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final AnnotationList object);

    /**
     * This method visits an object of type <code>Variable</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final Variable object);

    /**
     * This method visits an object of type <code>Name</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final Name object);

    /**
     * This method visits an object of type <code>TypeSpecifier</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final TypeSpecifier object);

    /**
     * This method visits an object of type <code>Namespace</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final Namespace object);

    /**
     * This method visits an object of type <code>ElementList</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final ElementList object);

    /**
     * This method visits an object of type <code>Element</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final Element object);

    /**
     * This method visits an object of type <code>FormalParameterList</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final FormalParameterList object);

    /**
     * This method visits an object of type <code>FormalParameter</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final FormalParameter object);

    /**
     * This method visits an object of type <code>ConditionalCase</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final ConditionalCase object);

    /**
     * This method visits an object of type <code>Label</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final Label object);

    /**
     * This method visits an object of type <code>SourceLocation</code>.
     * 
     * @param object is the object to visit.
     */
    public void visit(final SourceLocation object);

}
