package autumn.lang;

import autumn.lang.internals.ArgumentStack;
import java.util.List;

/**
 * This interface is the supertype of statically defined functor types.
 *
 * <p>
 * A class that implements this interface must also provide a single invoke(*) method.
 * The method's parameters are the parameters to the functor.
 * The return-type of the method is the return-type of the functor.
 * The invoke(*) method simply invokes the apply(ArgumentStack) method.
 * For example, imagine a functor that takes an int parameter and returns a double.
 * Then, the class should provide a method: invoke(int) : double.
 * </p>
 *
 * <p>
 * A class that implements this interface should provide a single constructor.
 * The constructor should take a TypedFunctor as its only parameter.
 * The functor will become the inner() functor.
 * </p>
 *
 * <p>
 * The parameterTypes() method and the returnType() method should be implemented
 * independently of the inner() functor. In other words, those methods
 * should return immutable lists that were created directly by the implementor.
 * </p>
 *
 * <p>
 * The apply(ArgumentStack) method simply invokes the apply(ArgumentStack) method
 * of the inner() functor.
 * </p>
 *
 * @author Mackenzie High
 */
public interface DefinedFunctor
        extends TypedFunctor
{
    /**
     * This method retrieves the functor that is wrapped by this object.
     *
     * @return the inner functor.
     */
    public TypedFunctor inner();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Class> parameterTypes();

    /**
     * {@inheritDoc}
     */
    @Override
    public Class returnType();

    /**
     * {@inheritDoc}
     */
    @Override
    public void apply(ArgumentStack stack)
            throws Throwable;
}
