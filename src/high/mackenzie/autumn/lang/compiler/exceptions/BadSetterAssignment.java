package high.mackenzie.autumn.lang.compiler.exceptions;

import high.mackenzie.autumn.lang.compiler.typesystem.design.IClassType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IInterfaceType;

/**
 *
 * @author mackenzie
 */
public final class BadSetterAssignment
        extends Exception
{
    public IInterfaceType owner;

    public String name;

    public IClassType module;

    public String function;
}
