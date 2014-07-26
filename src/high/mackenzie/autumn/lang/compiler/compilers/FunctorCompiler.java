package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.compiler.ast.nodes.FunctorDefinition;

/**
 * An instance of this class controls the compilation of a functor-definition.
 *
 * @author Mackenzie High
 */
public final class FunctorCompiler
        implements ICompiler
{
    private final ProgramCompiler program;

    private final ModuleCompiler module;

    private final FunctorDefinition node;

    /**
     * Sole Constructor.
     *
     * @param module is essentially the module that is being compiled.
     * @param node is the AST node that represents the functor-definition.
     */
    public FunctorCompiler(final ModuleCompiler module,
                           final FunctorDefinition node)
    {
        this.module = module;
        this.node = node;

        this.program = module.program;
    }

    @Override
    public void performTypeDeclaration()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void performTypeInitialization()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void performTypeStructureChecking()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void performTypeUsageChecking()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void performCodeGeneration()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
