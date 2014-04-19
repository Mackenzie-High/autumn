/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.compiler.ClassFile;
import autumn.lang.compiler.CompiledProgram;
import autumn.lang.compiler.ast.nodes.Module;
import autumn.lang.compiler.errors.IErrorReporter;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.io.Files;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.utils.Utils;
import java.io.File;
import java.util.List;
import java.util.Set;

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

    public final List<File> dependencies = Lists.newLinkedList();

    private final List<ModuleCompiler> modules = Lists.newLinkedList();

    /**
     * Execution of include-directives failed for these files, if any.
     */
    private final Set<File> failed_includes = Sets.newTreeSet();

    public ProgramCompiler(final IErrorReporter reporter,
                           final List<Module> mules)
    {
        this.reporter = reporter;

        this.typesystem = new TypeSystem(this);

        this.checker = new StaticChecker(this);

        for (Module m : mules)
        {
            this.modules.add(new ModuleCompiler(this, m));
        }

        this.executeIncludeDirectives(mules);
    }

    /**
     * This method controls the execution of the include-directives in the program.
     *
     * @param mules are the modules that were initially given to the compiler.
     */
    private void executeIncludeDirectives(final List<Module> mules)
    {
        final Set<File> visited = Sets.newTreeSet();

        for (Module m : mules)
        {
            visited.add(m.getLocation().getFile());
        }
    }

    /**
     * This method generates the the compiled-program that can be written to a JAR file.
     *
     * @return a jar-able representation of the program.
     */
    public CompiledProgram build()
    {
        final List<ClassFile> classes = Lists.newLinkedList();

        for (ModuleCompiler m : modules)
        {
            classes.addAll(m.build());
        }

        for (DelegateCompiler x : symbols.delegates.values())
        {
            classes.add(x.build());
        }

        for (ClassCompiler x : symbols.creators.values())
        {
            classes.add(x.build());
        }

        final List<IMethod> starts = this.findStart();

        final String main_class = starts.size() == 1
                ? Utils.sourceName(starts.get(0).getOwner())
                : null;

        final CompiledProgram result = new CompiledProgram(main_class, dependencies, classes);

        // TODO: Remove this
        try
        {
            for (ClassFile klass : classes)
            {
                if ((new File("/home/mackenzie")).isDirectory() == false)
                {
                    break;
                }

                final File file = new File("/home/mackenzie/test/" + klass.name() + ".class");
                Files.write(klass.bytecode(), file);
            }
        }
        catch (Exception x)
        {
            // PASS
        }

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
     * @return the types of the functions that may be the program's entry-point.
     */
    private List<IMethod> findStart()
    {
        final List<IMethod> result = Lists.newLinkedList();

        for (ModuleCompiler module : modules)
        {
            for (FunctionCompiler function : module.functions)
            {
                if (function.isAnnotationPresent(typesystem.utils.START))
                {
                    result.add(function.type);
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
     * {@inheritDoc}
     */
    @Override
    public void performCodeGeneration()
    {
        for (ModuleCompiler m : modules)
        {
            m.performCodeGeneration();
        }
    }

    public static CompiledProgram compile(final List<Module> input,
                                          final IErrorReporter reporter)
    {
        final ProgramCompiler compiler = new ProgramCompiler(reporter, input);

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

        compiler.performCodeGeneration();

        if (reporter.errorCount() > 0)
        {
            return null;
        }

        final CompiledProgram program = compiler.build();

        return program;
    }
}
