package high.mackenzie.autumn.lang.compiler.exceptions;

import high.mackenzie.autumn.lang.compiler.typesystem.design.IInterfaceType;

/**
 * @author mackenzie
 */
public final class PrototypeExpected
        extends Exception
{
    /**
     * This is the type that should be a prototype.
     */
    public IInterfaceType face;
}
