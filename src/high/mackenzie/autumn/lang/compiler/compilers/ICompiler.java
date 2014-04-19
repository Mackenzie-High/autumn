/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.lang.compiler.compilers;

/**
 * An instance of this interface controls the compilation of part of an Autumn program.
 *
 * @author Mackenzie High
 */
public interface ICompiler
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

    /**
     * This 5th compiler pass.
     */
    public void performCodeGeneration();
}
