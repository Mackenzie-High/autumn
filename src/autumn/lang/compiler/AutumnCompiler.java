package autumn.lang.compiler;

import autumn.lang.compiler.ast.nodes.Module;
import autumn.lang.compiler.errors.BasicErrorReporter;
import autumn.lang.compiler.errors.IErrorReporter;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import high.mackenzie.autumn.lang.compiler.compilers.ProgramCompiler;
import java.util.List;

/**
 * An instance of this class is a compiler that can compile Autumn modules.
 *
 * @author Mackenzie High
 */
public final class AutumnCompiler
{
    private final IErrorReporter reporter;

    /**
     * Constructor.
     *
     * <p>
     * A compiler created using this constructor will print error messages to STDOUT.
     * </p>
     */
    public AutumnCompiler()
    {
        this(new BasicErrorReporter());
    }

    /**
     * Constructor.
     *
     * @param reporter is the error-reporter used to report compilation-errors.
     */
    public AutumnCompiler(final IErrorReporter reporter)
    {
        Preconditions.checkNotNull(reporter);

        this.reporter = reporter;
    }

    /**
     * This method compiles the abstract-syntax-tree representation of a module.
     *
     * @param module is the module that will be compiled.
     * @return the compiled program that results from compiling the module,
     * or null, if the program could not be compiled.
     */
    public CompiledProgram compile(final Module module)
    {
        final List<Module> list = ImmutableList.of(module);

        final CompiledProgram program = ProgramCompiler.compile(list, reporter);

        return program;
    }

    /**
     * This method compiles the abstract-syntax-tree representations of a group of modules.
     *
     * @param modules are the modules that will be compiled.
     * @return the compiled program that results from compiling the modules,
     * or null, if the program could not be compiled.
     */
    public CompiledProgram compile(final Iterable<Module> modules)
    {
        final List<Module> list = ImmutableList.copyOf(modules);

        final CompiledProgram program = ProgramCompiler.compile(list, reporter);

        return program;
    }
}
