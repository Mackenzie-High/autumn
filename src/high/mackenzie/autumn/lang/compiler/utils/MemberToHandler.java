package high.mackenzie.autumn.lang.compiler.utils;

import autumn.lang.Module;
import autumn.lang.Prototype;
import com.google.common.base.Preconditions;
import high.mackenzie.autumn.lang.compiler.exceptions.BadGetterAssignment;
import high.mackenzie.autumn.lang.compiler.exceptions.BadMethodAssignment;
import high.mackenzie.autumn.lang.compiler.exceptions.BadSetterAssignment;
import high.mackenzie.autumn.lang.compiler.exceptions.NoSuchHandlerFunction;
import high.mackenzie.autumn.lang.compiler.exceptions.NoSuchProperty;
import high.mackenzie.autumn.lang.compiler.exceptions.PrototypeExpected;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IClassType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IInterfaceType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVariableType;
import java.util.List;
import java.util.Map.Entry;

/**
 * An instance of this class is used to map a setter, getter, or method to a handler function.
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
     * @param member is the setter, getter, or method.
     * @param handler is the handler function.
     */
    public MemberToHandler(final IMethod member,
                           final IMethod handler)
    {
        Preconditions.checkNotNull(member);
        Preconditions.checkNotNull(handler);

        assert member.getOwner().isSubtypeOf(member.getOwner().getTypeFactory().fromClass(Prototype.class));
        assert handler.getOwner().isSubtypeOf(handler.getOwner().getTypeFactory().fromClass(Module.class));

        this.member = member;
        this.handler = handler;
    }

    /**
     * This method retrieves the type-system representation of the setter, getter, or method.
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

    /**
     * This method creates a mapping from a setter to a handler function.
     *
     * @param owner is the type of the prototype that contains the property.
     * @param name is the name of the property.
     * @param module is the type of the module that contains the handler function.
     * @param method is the name of the handler function.
     * @return a mapping from a setter to a handler function.
     */
    public static MemberToHandler forSetter(final IVariableType owner,
                                            final String name,
                                            final IClassType module,
                                            final String method)
            throws PrototypeExpected,
                   NoSuchProperty,
                   NoSuchHandlerFunction,
                   BadSetterAssignment
    {
        final Design design = designOf(owner);

        final IMethod setter = design.setters().get(name).mostSpecific();

        final IMethod handler = findHandler(module, method);

        final MemberToHandler mapping = new MemberToHandler(setter, handler);

        checkSetterAssignment(mapping);

        return mapping;
    }

    /**
     * This method creates a mapping from a getter to a handler function.
     *
     * @param owner is the type of the prototype that contains the property.
     * @param name is the name of the property.
     * @param module is the type of the module that contains the handler function.
     * @param method is the name of the handler function.
     * @return a mapping from a getter to a handler function.
     */
    public static MemberToHandler forGetter(final IVariableType owner,
                                            final String name,
                                            final IClassType module,
                                            final String method)
            throws PrototypeExpected,
                   NoSuchProperty,
                   NoSuchHandlerFunction,
                   BadGetterAssignment
    {
        final Design design = designOf(owner);

        final IMethod getter = design.setters().get(name).mostSpecific();

        final IMethod handler = findHandler(module, method);

        final MemberToHandler mapping = new MemberToHandler(getter, handler);

        checkGetterAssignment(mapping);

        return mapping;
    }

    /**
     * This method creates a mapping from a method to a handler function.
     *
     * @param owner is the type of the prototype that contains the method.
     * @param name is the name of the method.
     * @param module is the type of the module that contains the handler function.
     * @param method is the name of the handler function.
     * @return a mapping from a method to a handler function.
     */
    public static MemberToHandler forMethod(final IVariableType owner,
                                            final String name,
                                            final IClassType module,
                                            final String method)
            throws PrototypeExpected,
                   NoSuchProperty,
                   NoSuchHandlerFunction,
                   BadMethodAssignment
    {
        // TODO: improve

        final Design design = designOf(owner);

        final IMethod handler = findHandler(module, method);

        for (Entry<String, MethodList> entry : design.methods().entrySet())
        {
            if (entry.getKey().startsWith(name + "(") == false)
            {
                continue;
            }

            final MethodList list = entry.getValue();

            if (isBestMatchMethod(handler, list))
            {
                return new MemberToHandler(list.mostSpecific(), handler);
            }
        }

        throw new BadMethodAssignment();
    }

    private static boolean isBestMatchMethod(final IMethod handler,
                                             final MethodList method)
    {
        // TODO: improve

        return true;
    }

    private static void checkSetterAssignment(final MemberToHandler mapping)
            throws BadSetterAssignment
    {
    }

    private static void checkGetterAssignment(final MemberToHandler mapping)
            throws BadGetterAssignment
    {
    }

    private static void checkMethodAssignment(final MemberToHandler mapping)
            throws BadMethodAssignment
    {
    }

    private static Design designOf(final IVariableType owner)
    {
        if (owner instanceof IInterfaceType == false)
        {
            // TODO: error - prototype expected
        }

        if (owner.isSubtypeOf(owner.getTypeFactory().fromClass(Prototype.class)) == false)
        {
            // TODO: error - prototype expected
        }

        return new Design((IInterfaceType) owner);
    }

    public static IMethod findHandler(final IClassType owner,
                                      final String name)
    {
        final List<IMethod> overloads = TypeSystemUtils.findFunctions(owner, name);

        if (overloads.isEmpty())
        {
            // TODO: error
        }

        if (overloads.size() > 1)
        {
            // TODO: error
        }

        final IMethod handler = overloads.get(0);

        return handler;
    }
}
