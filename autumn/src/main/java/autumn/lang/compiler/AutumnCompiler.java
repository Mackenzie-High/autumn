package autumn.lang.compiler;

import autumn.lang.compiler.ast.nodes.Module;
import autumn.lang.compiler.errors.IErrorReporter;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mackenziehigh.autumn.lang.compiler.compilers.ProgramCompiler;
import com.mackenziehigh.autumn.resources.Finished;
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
    /**
     * This error-reporter is used to report compilation-errors.
     */
    private final IErrorReporter reporter;

    /**
     * This class-loader provides the types that were already loaded.
     */
    private final ClassLoader loader;

    /**
     * These are additional classes to automatically import inside each module.
     * This is a list, because order is important in Autumn imports.
     */
    private final List<Class> imported = Lists.newLinkedList();

    /**
     * Constructor.
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
     * This method specifies that a particular class should be imported in every module.
     *
     * @param klass is the type to import in every module automatically.
     * @throws NullPointerException if klass is null.
     */
    public void addImport(final Class klass)
    {
        Preconditions.checkNotNull(klass);

        imported.add(klass);
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

        final CompiledProgram program = ProgramCompiler.compile(list, reporter, loader, imported);

        return program;
    }
}
