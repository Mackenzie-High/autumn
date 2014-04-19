/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.lang.compiler.compilers;

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
    private final ProgramCompiler program;

    private final TypeFactory factory = new TypeFactory();

    public final TypeSystemUtils utils = new TypeSystemUtils(factory);

    public TypeSystem(final ProgramCompiler program)
    {
        this.program = program;
    }

    public TypeFactory getTypeFactory()
    {
        return factory;
    }

    public boolean isSubtypeOf(final IType instance,
                               final Class supertype)
    {
        final IType target = getTypeFactory().fromClass(supertype);

        return target != null && instance.isSubtypeOf(target);
    }
}
