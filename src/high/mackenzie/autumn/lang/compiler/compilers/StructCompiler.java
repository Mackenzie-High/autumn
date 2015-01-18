package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.compiler.ast.nodes.StructDefinition;

/**
 * An instance of this class controls the compilation of a struct-definition.
 *
 * @author Mackenzie High
 */
class StructCompiler
        extends AbstractStructTupleCompiler
{
    /**
     * Sole Constructor.
     *
     * @param module is the module that contains the tuple being compiled.
     * @param node is the AST node that represents the tuple being compiled.
     */
    public StructCompiler(final ModuleCompiler module,
                          final StructDefinition node)
    {
        super(module, node, false);
    }
}
