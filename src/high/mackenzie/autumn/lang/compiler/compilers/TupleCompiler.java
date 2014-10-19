package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.compiler.ast.nodes.TupleDefinition;

/**
 * An instance of this class controls the compilation of a tuple-definition.
 *
 * @author Mackenzie High
 */
public final class TupleCompiler
        extends AbstractRecordCompiler
{
    /**
     * Sole Constructor.
     *
     * @param module is the module that contains the tuple being compiled.
     * @param node is the AST node that represents the tuple being compiled.
     */
    public TupleCompiler(final ModuleCompiler module,
                         final TupleDefinition node)
    {
        super(module, node, true);
    }
}