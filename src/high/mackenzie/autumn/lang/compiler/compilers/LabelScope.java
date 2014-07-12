package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.compiler.ast.nodes.GotoStatement;
import autumn.lang.compiler.ast.nodes.MarkerStatement;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import high.mackenzie.autumn.resources.Finished;
import java.util.List;
import java.util.Map;
import org.objectweb.asm.tree.LabelNode;

/**
 * An instance of this class controls the allocation of location labels.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public final class LabelScope
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
