package high.mackenzie.autumn.lang.compiler.utils;

import autumn.lang.StaticFunctor;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IClassType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import java.util.Collections;
import java.util.List;

/**
 * An instance of this class can be used to analyze a well-formed functor type.
 *
 * <p>
 * Note: A functor-type never declares more than one invoke(*) method directly.
 * However, a functor-type may inherit multiple invoke(*) methods.
 * </p>
 *
 * @author Mackenzie High
 */
public final class FunctorAnalyzer
{
    /**
     * This is the functor-type being analyzed.
     */
    private final IClassType functor;

    /**
     * This object is needed in order to sort methods.
     */
    private final TypeSystemUtils utils;

    /**
     * There is no need to search for the invoke(*) methods multiple times.
     */
    private List<IMethod> cache;

    /**
     * Sole Constructor.
     *
     * @param functor is the functor-type to analyze.
     */
    public FunctorAnalyzer(final TypeSystemUtils utils,
                           final IClassType functor)
    {
        Preconditions.checkNotNull(utils);
        Preconditions.checkNotNull(functor);

        assert functor.isSubtypeOf(functor.getTypeFactory().fromClass(StaticFunctor.class));

        this.utils = utils;
        this.functor = functor;
    }

    /**
     * This method retrieves the invoke(*) methods from the type.
     *
     * <p>
     * The functor-type and each of its superclasses should define one invoke methods
     * </p>
     *
     * @return the methods sorted topologically based on their specificity.
     */
    public List<IMethod> invokeMethods()
    {
        /**
         * If we already found the methods, simply return them.
         */
        if (cache != null)
        {
            return cache;
        }

        final List<IClassType> types = Lists.newLinkedList();

        types.add(functor);
        types.addAll(functor.getAllSuperclasses());

        final List<IMethod> methods = Lists.newLinkedList();

        /**
         * Transverse the functor-type and its superclasses.
         */
        for (IClassType type : types)
        {
            /**
             * Find the invoke methods declared in the type, if any.
             */
            for (IMethod method : type.getMethods())
            {
                if (method.getOwner().isClassType() && method.getName().equals("invoke"))
                {
                    methods.add(method);
                }
            }
        }

        /**
         * Sort the methods.
         */
        cache = Collections.unmodifiableList(utils.sort(methods));

        return cache;
    }

    /**
     * This method retrieves the most-specific of the invoke(*) methods.
     *
     * @return the most-specific of invoke-method.
     */
    public IMethod invokeMethod()
    {
        return invokeMethods().get(0);
    }

    /**
     * Every invoke(*) method in a subclass must be compatible with those in its superclasses.
     *
     * <p>
     * <ul>
     * <li>Let M be an invoke(*) method in type P. </li>
     * <li>Let W be an invoke(*) method in type Q. </li>
     * <li>Assume P is a subclass of Q. </li>
     * <li>Requirement: M.formals.length == W.formals.length </li>
     * <li>Requirement: M.return-type is a subtype of W.return-type </li>
     * <li>Requirement: M.formals[i].type is a subtype of W.formals[i].type </li>
     * </ul>
     * </p>
     *
     * @return a description of the violation, or null, if no violation exists.
     */
    public FunctorSubtypingViolation detectSubtypeViolation()
    {
        // Note: This is an O(n^2) algorithm.
        //       However, the inputs should always be small.
        //       So, until a profiler says otherwise, this algorithm is acceptable.

        final List<IMethod> invoke_methods = invokeMethods();

        for (IMethod invoke1 : invoke_methods)
        {
            for (IMethod invoke2 : invoke_methods)
            {
                if (invoke1.getOwner().isSubtypeOf(invoke2.getReturnType()))
                {
                    final FunctorSubtypingViolation potential_violation = new FunctorSubtypingViolation(invoke1, invoke2);

                    if (potential_violation.isParameterLengthViolation())
                    {
                        return potential_violation;
                    }

                    if (potential_violation.isReturnTypeViolation())
                    {
                        return potential_violation;
                    }

                    if (potential_violation.isParameterTypeViolation())
                    {
                        return potential_violation;
                    }
                }
            }
        }

        return null; // No Violation Detected
    }

    /**
     * This method creates a list of the bridge methods that the functor-type must provide.
     *
     * @return a list of the bridge methods.
     */
    public List<BridgeMethod> bridges()
    {
        final List<BridgeMethod> bridges = Lists.newLinkedList();

        final List<IMethod> invoke_methods = invokeMethods();

        /**
         * This is the method that the bridge methods will invoke.
         */
        final IMethod target = invokeMethod();

        /**
         * Create the bridge method objects.
         */
        for (int i = 0; i < invoke_methods.size(); i++)
        {
            final IMethod bridge = invoke_methods.get(i);

            /**
             * No bridge is needed, if the descriptors are the same.
             * Simple inheritance will work instead.
             */
            if (target.getDescriptor().equals(target.getDescriptor()) == false)
            {
                final BridgeMethod object = new BridgeMethod(bridge, target);

                bridges.add(object);
            }
        }

        return Collections.unmodifiableList(bridges);
    }
}
