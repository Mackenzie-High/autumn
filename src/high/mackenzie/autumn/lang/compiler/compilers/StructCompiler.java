/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.compiler.ClassFile;
import autumn.lang.compiler.ast.nodes.StructDefinition;

/**
 *
 *
 * @author mackenzie
 */
public class StructCompiler
        implements ICompiler
{
    private final ModuleCompiler module;

    private final StructDefinition node;

    public StructCompiler(final ModuleCompiler module,
                          final StructDefinition node)
    {
        this.module = module;
        this.node = node;
    }

    /**
     * This method generates the compiled class-file.
     *
     * @return the compiled class-file.
     */
    public ClassFile build()
    {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeDeclaration()
    {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeInitialization()
    {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeStructureChecking()
    {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeUsageChecking()
    {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performCodeGeneration()
    {
    }
}
