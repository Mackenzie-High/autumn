package high.mackenzie.autumn.lang.compiler.exceptions;

import high.mackenzie.autumn.lang.compiler.typesystem.design.IClassType;

/**
 *
 * @author mackenzie
 */
public final class NoSuchHandlerFunction
        extends Exception
{
    public IClassType module;

    public String function;
}
