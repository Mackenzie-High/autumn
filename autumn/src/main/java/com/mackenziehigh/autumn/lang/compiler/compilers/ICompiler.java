package com.mackenziehigh.autumn.lang.compiler.compilers;

import com.mackenziehigh.autumn.resources.Finished;

/**
 * An instance of this interface controls the compilation of part of an Autumn program.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
interface ICompiler
{
    /**
     * This 1st compiler pass.
     */
    public void performTypeDeclaration();

    /**
     * This 2nd compiler pass.
     */
    public void performTypeInitialization();

    /**
     * This 3rd compiler pass.
     */
    public void performTypeStructureChecking();

    /**
     * This 4th compiler pass.
     */
    public void performTypeUsageChecking();
}
