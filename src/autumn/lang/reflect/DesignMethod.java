package autumn.lang.reflect;

import autumn.lang.Functor;

/**
 * An instance of this interface describes a method within a prototype object.
 *
 * @author Mackenzie High
 */
public interface DesignMethod
{
    public DesignObject owner();

    /**
     * This method returns the object that is used to identify the method.
     *
     * @return the key that identifies the method.
     */
    public MethodKey key();

    /**
     * This method determines whether the method is the program's entry-point.
     *
     * @return true, iff the method is the program's main method.
     */
    public boolean isStart();

    /**
     * This method determines whether the method is synchronized.
     *
     * @return true, iff the method is synchronized.
     */
    public boolean isSync();

    /**
     * This method determines whether the method is backed by a method in an inner object.
     *
     * @return true, iff this method is a composite method.
     */
    public boolean isComposite();

    /**
     * This method retrieves the function object that is executed whenever the method is invoked.
     *
     * @return the functor that implements the method.
     */
    public Functor handler();
}
