package high.mackenzie.autumn.lang.compiler.compilers;

import com.google.common.base.Preconditions;
import high.mackenzie.autumn.lang.compiler.typesystem.TypeFactory;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IType;
import high.mackenzie.autumn.lang.compiler.utils.TypeSystemUtils;

/**
 * An instance of this class is the type-system in use by a compiler.
 *
 * @author Mackenzie High
 */
public final class TypeSystem
{
    /**
     * This is essentially the program that is being compiled.
     */
    private final ProgramCompiler program;

    /**
     * This is the type-factory that the compiled uses to create new type objects.
     */
    private final TypeFactory factory = new TypeFactory();

    /**
     * This object provides utility methods related to the type-system.
     */
    public final TypeSystemUtils utils = new TypeSystemUtils(factory);

    /**
     * Sole Constructor.
     *
     * @param program is essentially the program that is being compiled.
     */
    public TypeSystem(final ProgramCompiler program)
    {
        Preconditions.checkNotNull(program);

        this.program = program;
    }

    /**
     * This method gets the type-factory that is used to create and access types.
     *
     * @return the type-factory in use by the compiler.
     */
    public TypeFactory typefactory()
    {
        return factory;
    }

    /**
     * This method determines whether a type is a subtype of a type represented b a class.
     *
     * @param instance is the possible subtype.
     * @param supertype is the possible supertype.
     * @return true, iff the instance is really a subtype of the supertype.
     */
    public boolean isSubtypeOf(final IType instance,
                               final Class supertype)
    {
        Preconditions.checkNotNull(instance);
        Preconditions.checkNotNull(supertype);

        final IType target = typefactory().fromClass(supertype);

        return target != null && instance.isSubtypeOf(target);
    }
}
