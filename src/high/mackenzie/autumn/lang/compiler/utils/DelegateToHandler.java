package high.mackenzie.autumn.lang.compiler.utils;

import high.mackenzie.autumn.lang.compiler.typesystem.design.IClassType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVariableType;
import java.util.List;

/**
 * An instance of this class is used to map a delegate to handler function.
 *
 * @author mackenzie
 */
public final class DelegateToHandler
{
    public static enum Errors
    {
        NO_SUCH_METHOD,
        OVERLOADED_METHOD,
        INCOMPATIBLE_METHOD,
    }

    /**
     * This field will be null, unless an error was detected.
     */
    public final Errors error;

    /**
     * This is the type of the delegate's invoke(*) method.
     */
    public final IMethod delegate;

    /**
     * This is the type of the method that the delegate refers to.
     */
    public final IMethod handler;

    /**
     * Sole Constructor.
     *
     * @param error is the type of error, if any.
     * @param member is the delegate.
     * @param handler is the handler function.
     */
    private DelegateToHandler(final Errors error,
                              final IMethod member,
                              final IMethod handler)
    {
        this.error = error;
        this.delegate = member;
        this.handler = handler;
    }

    public static DelegateToHandler find(final IClassType functor,
                                         final IClassType owner,
                                         final String name)
    {
        final IMethod invoke = TypeSystemUtils.find(functor.getMethods(), "invoke");

        final List<IMethod> overloads = TypeSystemUtils.findFunctions(owner, name);

        /**
         * An overload must exist in order to map the delegate to a handler.
         */
        if (overloads.isEmpty())
        {
            return new DelegateToHandler(Errors.NO_SUCH_METHOD, invoke, null);
        }

        /**
         * Per the specification, a delegate can not target an overloaded function.
         */
        if (overloads.size() > 1)
        {
            return new DelegateToHandler(Errors.OVERLOADED_METHOD, invoke, null);
        }

        /**
         * Get the type of the function that is referred to by the delegate.
         */
        final IMethod handler = overloads.get(0);

        /**
         * The delegate must be compatible with the function that it refers to.
         */
        if (checkCompatability(invoke, handler) == false)
        {
            return new DelegateToHandler(Errors.INCOMPATIBLE_METHOD, invoke, handler);
        }

        /**
         * Everything appear to be OK.
         */
        return new DelegateToHandler(null, invoke, handler);
    }

    /**
     * This method determines whether a delegate is compatible with the method that it refers to.
     *
     * @param functor is the type of the functor's invoke() method.
     * @param handler is the type of the function that the delegate refers to.
     * @return true, iff the delegate is compatible with the function that it refers to.
     */
    private static boolean checkCompatability(final IMethod functor,
                                              final IMethod handler)
    {
        /**
         * Per the specification, the handler's return-type must
         * be a subtype of the functor's return-type.
         */
        if (handler.getReturnType().isSubtypeOf(functor.getReturnType()) == false)
        {
            return false;
        }

        /**
         * Per the specification, the handler and the functor must
         * take the same number of arguments.
         */
        if (functor.getParameters().size() != handler.getParameters().size())
        {
            return false;
        }

        /**
         * Per the specification, the type of parameter[i] of the functor
         * must be a subtype of parameter[i] of the handler.
         */
        for (int i = 0; i < handler.getParameters().size(); i++)
        {
            final IVariableType functor_parameter = functor.getParameters().get(i).getType();

            final IVariableType handler_parameter = handler.getParameters().get(i).getType();

            if (functor_parameter.isSubtypeOf(handler_parameter) == false)
            {
                return false;
            }
        }

        /**
         * Everything appears to be OK.
         */
        return true;
    }
}
