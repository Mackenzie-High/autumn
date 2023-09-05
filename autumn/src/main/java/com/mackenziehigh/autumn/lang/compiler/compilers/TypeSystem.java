package com.mackenziehigh.autumn.lang.compiler.compilers;

import com.google.common.base.Preconditions;
import com.mackenziehigh.autumn.lang.compiler.typesystem.TypeFactory;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IType;
import com.mackenziehigh.autumn.lang.compiler.utils.TypeSystemUtils;

/**
 * An instance of this class is the type-system in use by a compiler.
 *
 * @author Mackenzie High
 */
public final class TypeSystem
{
    /**
     * This is the type-factory that the compiled uses to create new type objects.
     */
    private final TypeFactory factory;

    /**
     * This object provides utility methods related to the type-system.
     */
    public final TypeSystemUtils utils;

    /**
     * Sole Constructor.
     *
     * @param loader is the class-loader used to find previously compiled code.
     */
    public TypeSystem(final ClassLoader loader)
    {
        Preconditions.checkNotNull(loader);

        this.factory = new TypeFactory(loader);
        this.utils = new TypeSystemUtils(factory);
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
