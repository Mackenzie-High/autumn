package high.mackenzie.autumn.lang.compiler.exceptions;

import high.mackenzie.autumn.lang.compiler.typesystem.design.IInterfaceType;

/**
 *
 * @author mackenzie
 */
public final class NoSuchProperty
        extends Exception
{
    public IInterfaceType owner;

    public String name;
}
