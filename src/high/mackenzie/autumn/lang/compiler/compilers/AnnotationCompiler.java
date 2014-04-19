/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.compiler.ClassFile;
import autumn.lang.compiler.ast.nodes.AnnotationDefinition;

/**
 * An instance of this class controls the compilation of an annotation-definition.
 *
 * @author Mackenzie High
 */
public final class AnnotationCompiler
        implements ICompiler
{
    public final ModuleCompiler module;

    public final AnnotationDefinition annotation;

    public AnnotationCompiler(final ModuleCompiler module,
                              final AnnotationDefinition annotation)
    {
        assert module != null;
        assert annotation != null;

        this.module = module;
        this.annotation = annotation;
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
