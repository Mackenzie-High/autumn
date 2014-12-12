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

package high.mackenzie.autumn.lang.compiler.parser;

import autumn.lang.compiler.*;
import autumn.lang.compiler.ast.commons.*;
import autumn.lang.compiler.ast.literals.*;
import autumn.lang.compiler.ast.nodes.*;
import high.mackenzie.autumn.lang.compiler.parser.Utils;
import high.mackenzie.snowflake.ITreeNode;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * This class is used to convert a parse-tree to an abstract-syntax-tree.
 */
public final class AstBuilder extends AbstractVisitor
{
    /**
     * This stack is used to store intermediate results.
     */
    private final Stack<Object> $stack = new Stack<Object>();

    /**
     * This method converts a parse-tree to an abstract-syntax-tree.
     */
    public static Module build(final ITreeNode root)
    {
        final AstBuilder visitor = new AstBuilder();
        visitor.visit(root);
        return (Module) visitor.$stack.peek();
    }

    /**
     * Sole Constructor.
     */
    private AstBuilder()
    {
        /* Do Nothing */
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visitUnknown(final ITreeNode node)
    {
        for(ITreeNode child : node.children())
        {
            visit(child);
        }
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_module(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createModule();

        Utils.setSourceLocation($node);

        final IConstruct module = builder.pop();

        $stack.push(module);
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_module_directive(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createDirectiveModule();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_name_of_anonymous_module(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.pushNull();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_import_directive(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createDirectiveImport();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_annotation_definition(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createDefinitionAnnotation();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_exception_definition(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createDefinitionException();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_enum_definition(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createDefinitionEnum();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_design_definition(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createDefinitionDesign();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_tuple_definition(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createDefinitionTuple();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_struct_definition(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createDefinitionStruct();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_functor_definition(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createDefinitionFunctor();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_function_definition(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createDefinitionFunction();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_sequence_statement(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createStatementSequence();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_expression_statement(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createStatementExpression();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_nop_statement(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createStatementNop();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_if_statement(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createStatementIf();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_when_statement(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createStatementWhen();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_goto_statement(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createStatementGoto();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_marker_statement(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createStatementMarker();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_branch_statement(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createStatementBranch();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_assert_statement(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createStatementAssert();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_assertion_echo_empty(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.pushNull();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_assume_statement(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createStatementAssume();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_assumption_echo_empty(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.pushNull();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_throw_statement(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createStatementThrow();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_try_catch_statement(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createStatementTryCatch();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_trycatch_handler(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createComponentExceptionHandler();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_break_statement(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createStatementBreak();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_continue_statement(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createStatementContinue();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_redo_statement(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createStatementRedo();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_while_statement(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createStatementWhile();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_forever_statement(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createStatementForever();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_until_statement(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createStatementUntil();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_do_until_trigger(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createStatementDoUntil();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_do_while_trigger(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createStatementDoWhile();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_for_statement(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createStatementFor();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_foreach_statement(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createStatementForeach();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_var_statement(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createStatementVar();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_val_statement(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createStatementVal();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_let_statement(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createStatementLet();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_lambda_statement(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createStatementLambda();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_delegate_statement(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createStatementDelegate();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_debug_statement(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createStatementDebug();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_return_void_statement(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createStatementReturnVoid();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_return_value_statement(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createStatementReturnValue();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_recur_statement(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createStatementRecur();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_boolean_datum(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        final boolean value = Utils.extractBooleanValue($node);

        builder.createDatum(value);

        Utils.setSourceLocation($node);
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_char_datum(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        final CharLiteral value = Utils.extractCharValue($node);

        builder.createDatum(value);

        Utils.setSourceLocation($node);
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_byte_datum(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        final ByteLiteral value = Utils.extractByteValue($node);

        builder.createDatum(value);

        Utils.setSourceLocation($node);
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_short_datum(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        final ShortLiteral value = Utils.extractShortValue($node);

        builder.createDatum(value);

        Utils.setSourceLocation($node);
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_int_datum(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        final IntLiteral value = Utils.extractIntValue($node);

        builder.createDatum(value);

        Utils.setSourceLocation($node);
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_long_datum(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        final LongLiteral value = Utils.extractLongValue($node);

        builder.createDatum(value);

        Utils.setSourceLocation($node);
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_float_datum(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        final FloatLiteral value = Utils.extractFloatValue($node);

        builder.createDatum(value);

        Utils.setSourceLocation($node);
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_double_datum(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        final DoubleLiteral value = Utils.extractDoubleValue($node);

        builder.createDatum(value);

        Utils.setSourceLocation($node);
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_big_integer_datum(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        final BigIntegerLiteral value = Utils.extractBigIntegerValue($node);

        builder.createDatum(value);

        Utils.setSourceLocation($node);
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_big_decimal_datum(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        final BigDecimalLiteral value = Utils.extractBigDecimalValue($node);

        builder.createDatum(value);

        Utils.setSourceLocation($node);
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_string_datum(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        final boolean verbatim = Utils.getVerbatim();

        final String value = Utils.getString();

        builder.createDatum(verbatim, value);

        Utils.setSourceLocation($node);
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_variable_datum(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createDatumVariable();

        Utils.setSourceLocation($node);
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_null_datum(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createDatumNull();

        Utils.setSourceLocation($node);
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_class_datum(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createDatumClass();

        Utils.setSourceLocation($node);
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_negate_operation(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createOperationNegate();

        Utils.setSourceLocation($node);
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_not_operation(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createOperationNot();

        Utils.setSourceLocation($node);
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_divide_operation(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createOperationDivide();

        Utils.setSourceLocation($node);
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_modulo_operation(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createOperationModulo();

        Utils.setSourceLocation($node);
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_multiply_operation(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createOperationMultiply();

        Utils.setSourceLocation($node);
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_add_operation(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createOperationAdd();

        Utils.setSourceLocation($node);
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_concat_operation(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createOperationConcat();

        Utils.setSourceLocation($node);
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_subtract_operation(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createOperationSubtract();

        Utils.setSourceLocation($node);
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_identity_equals_operation(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createOperationIdentityEquals();

        Utils.setSourceLocation($node);
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_identity_not_equals_operation(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createOperationIdentityNotEquals();

        Utils.setSourceLocation($node);
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_equals_operation(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createOperationEquals();

        Utils.setSourceLocation($node);
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_not_equals_operation(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createOperationNotEquals();

        Utils.setSourceLocation($node);
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_less_or_equals_operation(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createOperationLessThanOrEquals();

        Utils.setSourceLocation($node);
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_greater_or_equals_operation(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createOperationGreaterThanOrEquals();

        Utils.setSourceLocation($node);
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_less_operation(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createOperationLessThan();

        Utils.setSourceLocation($node);
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_greater_operation(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createOperationGreaterThan();

        Utils.setSourceLocation($node);
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_and_operation(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createOperationAnd();

        Utils.setSourceLocation($node);
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_or_operation(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createOperationOr();

        Utils.setSourceLocation($node);
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_xor_operation(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createOperationXor();

        Utils.setSourceLocation($node);
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_implies_operation(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createOperationImplies();

        Utils.setSourceLocation($node);
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_null_coalescing_operation(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createOperationNullCoalescing();

        Utils.setSourceLocation($node);
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_as_operation(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createOperationAs();

        Utils.setSourceLocation($node);
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_is_operation(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createOperationIs();

        Utils.setSourceLocation($node);
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_dispatch_expression(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createExpressionDispatch();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_new_expression(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createExpressionNew();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_call_method_expression(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createExpressionCallMethod();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_p8(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        Utils.createChainedMethodCall();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_call_static_method_expression(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createExpressionCallStaticMethod();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_set_field_expression(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createExpressionSetField();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_set_static_expression(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createExpressionSetStaticField();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_get_field_expression(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createExpressionGetField();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_get_static_expression(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createExpressionGetStaticField();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_instanceof_expression(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createExpressionInstanceOf();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_list_comprehension_expression(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createExpressionListComprehension();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_list_expression(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createExpressionList();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_locals_expression(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createExpressionLocals();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_progn_expression(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createExpressionProgn();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_ternary_conditional_expression(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createExpressionTernaryConditional();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_api_comment(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createComponentDocComment();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_api_comment_line(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        final String text = Utils.extractCommentLine($node);

        builder.createComponentDocCommentLine(text);

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_annotation_list(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createComponentAnnotationList();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_annotation(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        final List values = Utils.extractAnnotationValues($node);

        builder.createComponentAnnotation(values);

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_conditional_case(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createComponentConditionalCase();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_name_id(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        Utils.createComponentName($node);

        Utils.setSourceLocation($node);
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_type(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        Utils.createComponentTypeSpecifier($node);

        Utils.setSourceLocation($node);
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_type_namespace_explicit(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createComponentNamespace();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_type_namespace_implicit(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.pushNull();

        Utils.setSourceLocation($node);
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_namespace_explicit(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createComponentNamespace();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_namespace_implicit(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.pushNull();

        Utils.setSourceLocation($node);
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_variable(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        Utils.createComponentVariable($node);

        Utils.setSourceLocation($node);
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_label(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        Utils.createComponentLabel($node);

        Utils.setSourceLocation($node);
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_elements(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createComponentElementList();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_element(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createComponentElement();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_formals(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createComponentFormalParameterList();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_parameter(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.createComponentFormalParameter();

        Utils.setSourceLocation($node);

        builder.popStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_static_owner_implicit(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.pushNull();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_PS(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        final TreeBuilder builder = Utils.builder();

        builder.pushStack();
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_verbatim_string_value(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        Utils.storeStringValue($node, true);
    }

    /**
     * {inheritDoc} 
     */
    @Override
    public void visit_regular_string_value(final ITreeNode $node)
    {
        final int $stack_size = $stack.size();

        visitUnknown($node);

        final int $change = $stack.size() - $stack_size;

        assert $change >= 0;

        Utils.storeStringValue($node, false);
    }
}
