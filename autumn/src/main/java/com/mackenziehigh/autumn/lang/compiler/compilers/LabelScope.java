package com.mackenziehigh.autumn.lang.compiler.compilers;

import autumn.lang.compiler.ast.nodes.BranchStatement;
import autumn.lang.compiler.ast.nodes.GotoStatement;
import autumn.lang.compiler.ast.nodes.Label;
import autumn.lang.compiler.ast.nodes.MarkerStatement;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mackenziehigh.autumn.resources.Finished;
import java.util.List;
import java.util.Map;
import org.objectweb.asm.tree.LabelNode;

/**
 * An instance of this class controls the allocation of location labels.
 *
 * <p>
 * This class also performs the checking of marker, goto, and branch statements.
 * Since a label can be declared at a point its usage, multiple compiler passes are needed.
 * However, we can get around that need by deferring the checking until the end of one pass.
 * In particular, we simply defer the checking until the end of the type-usage-checking pass.
 * Then, the label declarations and usages can be checked all at once.
 * </p>
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
final class LabelScope
{
    /**
     * This is essentially the program that is being compiled.
     */
    private final ProgramCompiler program;

    /**
     * This map maps the name of a label to its bytecode representation.
     */
    private final Map<String, LabelNode> labels = Maps.newTreeMap();

    /**
     * These are the goto-statements in this scope.
     */
    private final List<GotoStatement> jumps = Lists.newLinkedList();

    /**
     * These are the marker-statements in this scope.
     */
    private final List<MarkerStatement> markers = Lists.newLinkedList();

    /**
     * These are the branch-statements in this scope.
     */
    private final List<BranchStatement> branches = Lists.newLinkedList();

    /**
     * Sole Constructor.
     *
     * @param program is the program being compiled.
     */
    public LabelScope(final ProgramCompiler program)
    {
        Preconditions.checkNotNull(program);

        this.program = program;
    }

    /**
     * This method defers checking of a goto-statement.
     *
     * <p>
     * Deferring checking alleviates the need for yet another compiler pass.
     * </p>
     *
     * @param statement is the goto-statement itself.
     */
    public void defer(final GotoStatement statement)
    {
        Preconditions.checkNotNull(statement);

        jumps.add(statement);
    }

    /**
     * This method defers checking of a marker-statement.
     *
     * <p>
     * Deferring checking alleviates the need for yet another compiler pass.
     * </p>
     *
     * @param statement is the marker-statement itself.
     */
    public void defer(final MarkerStatement statement)
    {
        Preconditions.checkNotNull(statement);

        markers.add(statement);
    }

    /**
     * This method defers checking of a branch-statement.
     *
     * <p>
     * Deferring checking alleviates the need for yet another compiler pass.
     * </p>
     *
     * @param statement is the marker-statement itself.
     */
    public void defer(final BranchStatement statement)
    {
        Preconditions.checkNotNull(statement);

        branches.add(statement);
    }

    /**
     * This method performs the checking of goto-statements and marker-statements that was deferred.
     */
    public void check()
    {
        for (MarkerStatement x : markers)
        {
            checkMarker(x);
        }

        for (GotoStatement x : jumps)
        {
            checkGoto(x);
        }

        for (BranchStatement x : branches)
        {
            checkBranch(x);
        }
    }

    private void checkMarker(final MarkerStatement statement)
    {
        if (labels.containsKey(statement.getLabel().getName()))
        {
            program.checker.reportDuplicateLabel(statement.getLabel());
        }
        else
        {
            labels.put(statement.getLabel().getName(), new LabelNode());
        }
    }

    private void checkGoto(final GotoStatement statement)
    {
        if (labels.containsKey(statement.getLabel().getName()))
        {
            // The statement is OK.
        }
        else
        {
            program.checker.reportUndeclaredLabel(statement.getLabel());
        }
    }

    private void checkBranch(final BranchStatement statement)
    {
        /**
         * Each of the case labels must be declared in the enclosing function.
         */
        for (Label label : statement.getLabels())
        {
            if (labels.containsKey(label.getName()) == false)
            {
                program.checker.reportUndeclaredLabel(statement.getDefaultLabel());
            }
        }

        /**
         * The label of the default-case must be declared in the enclosing function.
         */
        if (labels.containsKey(statement.getDefaultLabel().getName()) == false)
        {
            program.checker.reportUndeclaredLabel(statement.getDefaultLabel());
        }
    }

    /**
     * This method retrieves the bytecode node that implements the label.
     *
     * @param name is the name of the label to retrieve.
     * @return the bytecode for the node.
     * @throws IllegalStateException if the label was already declared.
     */
    public LabelNode nodeOf(final String name)
    {
        return labels.get(name);
    }
}
