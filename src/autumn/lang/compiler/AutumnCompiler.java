package autumn.lang.compiler;

import autumn.lang.compiler.ast.nodes.Module;
import autumn.lang.compiler.errors.IErrorReporter;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import high.mackenzie.autumn.lang.compiler.compilers.ProgramCompiler;
import high.mackenzie.autumn.resources.Finished;
import java.util.List;

/**
 * An instance of this class is a compiler that can compile Autumn modules.
 *
 * <p>
 * You should not use this class directly.
 * Instead, use it indirectly via the Autumn class.
 * </p>
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public final class AutumnCompiler
{
    private final IErrorReporter reporter;

    private final ClassLoader loader;

    /**
     * Sole Constructor.
     *
     * @param reporter is the error-reporter used to report compilation-errors.
     * @param loader is the class-loader used to find types that were already loaded.
     * @throws NullPointerException if reporter is null.
     * @throws NullPointerException if loader is null.
     */
    public AutumnCompiler(final IErrorReporter reporter,
                          final ClassLoader loader)
    {
        Preconditions.checkNotNull(reporter);
        Preconditions.checkNotNull(loader);

        this.reporter = reporter;
        this.loader = loader;
    }

    /**
     * This method compiles the abstract-syntax-tree representations of a group of modules.
     *
     * @param modules are the modules that will be compiled.
     * @return the compiled program that results from compiling the modules,
     * or null, if the program could not be compiled.
     * @throws NullPointerException if modules is null.
     */
    public CompiledProgram compile(final Iterable<Module> modules)
    {
        Preconditions.checkNotNull(modules);

        final List<Module> list = ImmutableList.copyOf(modules);

        final CompiledProgram program = ProgramCompiler.compile(list, reporter, loader);

        return program;
    }
}
