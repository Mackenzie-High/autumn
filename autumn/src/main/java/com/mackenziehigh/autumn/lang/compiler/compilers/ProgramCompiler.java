package com.mackenziehigh.autumn.lang.compiler.compilers;

import autumn.lang.compiler.ClassFile;
import autumn.lang.compiler.CompiledProgram;
import autumn.lang.compiler.ast.nodes.Module;
import autumn.lang.compiler.errors.IErrorReporter;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mackenziehigh.autumn.lang.compiler.utils.Utils;
import java.util.List;

/**
 * An instance of this class controls the compilation of an entire program.
 *
 * @author Mackenzie High
 */
public final class ProgramCompiler
        implements ICompiler
{
    public final TypeSystem typesystem;

    public final SymbolTable symbols = new SymbolTable();

    public final StaticChecker checker;

    public final IErrorReporter reporter;

    public final List<Class> imported;

    private final List<ModuleCompiler> modules = Lists.newLinkedList();

    /**
     * Sole Constructor.
     *
     * @param reporter is used to report errors.
     * @param loader is the class-loader used to find previously loaded types.
     * @param imported are types to automatically import into every module.
     * @param mules are the modules that will be compiled.
     */
    private ProgramCompiler(final IErrorReporter reporter,
                            final ClassLoader loader,
                            final List<Class> imported,
                            final List<Module> mules)
    {
        Preconditions.checkNotNull(reporter);
        Preconditions.checkNotNull(mules);

        this.reporter = reporter;

        this.typesystem = new TypeSystem(loader);

        this.checker = new StaticChecker(this);

        this.imported = ImmutableList.copyOf(imported);

        /**
         * Create a ModuleCompiler for each module AST node.
         */
        for (Module m : mules)
        {
            if (ModuleCompiler.isEmpty(m) == false)
            {
                this.modules.add(new ModuleCompiler(this, m));
            }
        }
    }

    /**
     * This method generates the the compiled-program that can be written to a JAR file.
     *
     * @return a jar-able representation of the program.
     */
    private CompiledProgram build()
    {
        /**
         * The generated class-files will be added to this list.
         */
        final List<ClassFile> classes = Lists.newLinkedList();

        /**
         * Add all the class-files, which represent modules, to the list.
         */
        for (ModuleCompiler m : modules)
        {
            classes.addAll(m.build());
        }

        /**
         * Add all the class-files, which represent lambdas, to the list.
         */
        for (LambdaCompiler x : symbols.lambdas.values())
        {
            classes.add(x.build());
        }

        /**
         * Find all of the entry-point functions, if any.
         */
        final List<FunctionCompiler> starts = this.findStart();

        /**
         * There should only be one entry-point function.
         */
        if (starts.size() > 1)
        {
            checker.reportTooManyStarts(starts.get(0).node);
        }

        /**
         * Compute the name of the module that contains the entry-point function.
         */
        final String main_class = starts.size() == 1
                ? Utils.sourceName(starts.get(0).type.getOwner())
                : null;

        /**
         * Create an object representation of the compiled program.
         */
        final CompiledProgram result = new CompiledProgram(main_class, classes);

        return result;
    }

    /**
     * This method finds all the functions being compiled that are marked as the entry-point.
     *
     * <p>
     * If the returned list should not have more than one element,
     * because only one function can be the program's entry-point.
     * </p>
     *
     * @return the functions that may be the program's entry-point.
     */
    private List<FunctionCompiler> findStart()
    {
        final List<FunctionCompiler> result = Lists.newLinkedList();

        for (ModuleCompiler module : modules)
        {
            for (FunctionCompiler function : module.functions)
            {
                if (function.isAnnotationPresent(typesystem.utils.START))
                {
                    result.add(function);
                }
            }
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeDeclaration()
    {
        for (ModuleCompiler m : modules)
        {
            m.performTypeDeclaration();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeInitialization()
    {
        for (ModuleCompiler m : modules)
        {
            m.performTypeInitialization();
        }

        for (ExceptionCompiler ecmp : symbols.exceptions.values())
        {
            ecmp.inferConstructors();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeStructureChecking()
    {
        /**
         * A compilation-unit can only contain a single start-function.
         */
        final List<FunctionCompiler> starts = this.findStart();

        if (starts.size() > 1)
        {
            checker.reportTooManyStarts(starts.get(0).node);
        }

        /**
         * Perform type-structure-checking on all the modules, etc.
         */
        for (ModuleCompiler m : modules)
        {
            m.performTypeStructureChecking();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeUsageChecking()
    {
        for (ModuleCompiler m : modules)
        {
            m.performTypeUsageChecking();
        }
    }

    /**
     * This method compiles a program to its bytecode representation.
     *
     * @param input are the modules in the program that will be compiled.
     * @param reporter is used to report errors.
     * @param loader is the class-loader used to find previously loaded types.
     * @param imported are types to automatically import into every module.
     * @return the bytecode representation of the compiled program; or null, if compilation fails.
     */
    public static CompiledProgram compile(final List<Module> input,
                                          final IErrorReporter reporter,
                                          final ClassLoader loader,
                                          final List<Class> imported)
    {

        try
        {
            final ProgramCompiler compiler = new ProgramCompiler(reporter, loader, imported, input);

            compiler.performTypeDeclaration();

            if (reporter.errorCount() > 0)
            {
                return null;
            }

            compiler.performTypeInitialization();

            if (reporter.errorCount() > 0)
            {
                return null;
            }

            compiler.performTypeStructureChecking();

            if (reporter.errorCount() > 0)
            {
                return null;
            }

            compiler.performTypeUsageChecking();

            if (reporter.errorCount() > 0)
            {
                return null;
            }

            final CompiledProgram program = compiler.build();

            return program;
        }
        catch (TypeCheckFailed ex)
        {
            // Pass, because the error will be reported via the error-reporter object.
        }
        catch (Exception ex)
        {
            // TODO: should a generalized exception handler go here, in case of compiler bugs?
            throw new RuntimeException(ex);
        }

        return null;
    }
}
