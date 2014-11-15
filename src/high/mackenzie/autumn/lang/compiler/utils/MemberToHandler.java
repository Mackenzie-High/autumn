package high.mackenzie.autumn.lang.compiler.utils;

import autumn.lang.Module;
import com.google.common.base.Preconditions;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IClassType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import java.util.List;

/**
 * An instance of this class is used to map a delegate to handler function.
 *
 * @author mackenzie
 */
public final class MemberToHandler
{
    private final IMethod member;

    private final IMethod handler;

    /**
     * Sole Constructor.
     *
     * @param member is the delegate.
     * @param handler is the handler function.
     */
    private MemberToHandler(final IMethod member,
                            final IMethod handler)
    {
        Preconditions.checkNotNull(member);
        Preconditions.checkNotNull(handler);

        //assert member.getOwner().isSubtypeOf(member.getOwner().getTypeFactory().fromClass(Prototype.class));
        assert handler.getOwner().isSubtypeOf(handler.getOwner().getTypeFactory().fromClass(Module.class));

        this.member = member;
        this.handler = handler;
    }

    /**
     * This method retrieves the type-system representation of the delegate.
     *
     * @return the member that will be handled by the handler.
     */
    public IMethod member()
    {
        return member;
    }

    /**
     * This method retrieves the type-system representation of the handler function.
     *
     * @return the function that will handle invocations of the member.
     */
    public IMethod handler()
    {
        return handler;
    }

    public static IMethod findHandler(final IClassType owner,
                                      final String name)
    {
        final List<IMethod> overloads = TypeSystemUtils.findFunctions(owner, name);

        if (overloads.isEmpty())
        {
            // TODO: error
            throw new RuntimeException(Utils.simpleName(owner) + "::" + name);
        }

        if (overloads.size() > 1)
        {
            // TODO: error
            throw new RuntimeException(Utils.simpleName(owner) + "::" + name);
        }

        final IMethod handler = overloads.get(0);

        return handler;
    }
}
