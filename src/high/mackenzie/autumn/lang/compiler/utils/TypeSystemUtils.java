package high.mackenzie.autumn.lang.compiler.utils;

import autumn.lang.Delegate;
import autumn.lang.Functor;
import autumn.lang.Local;
import autumn.lang.LocalsMap;
import autumn.lang.Module;
import autumn.lang.annotations.Start;
import autumn.lang.compiler.ast.nodes.Name;
import autumn.lang.compiler.ast.nodes.TypeSpecifier;
import autumn.lang.internals.AbstractDelegate;
import autumn.lang.internals.AbstractModule;
import autumn.lang.internals.ArgumentStack;
import autumn.lang.internals.Conversions;
import autumn.lang.internals.Helpers;
import autumn.lang.internals.ModuleDelegate;
import autumn.lang.internals.Operators;
import autumn.lang.internals.YieldState;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IAnnotatable;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IAnnotation;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IAnnotationType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IClassType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IConstructor;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IDeclaredType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IElementType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IEnumType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IField;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IFormalParameter;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IInterfaceType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IInvokableMember;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMember;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.INullType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IPrimitiveType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IReturnType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.ITypeFactory;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVariableType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVoidType;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;

/**
 * This class provides commonly used functionality involving the processing of types.
 *
 * @author Mackenzie High
 */
public final class TypeSystemUtils
{
    private final ITypeFactory factory;

    public final IPrimitiveType PRIMITIVE_BOOLEAN;

    public final IPrimitiveType PRIMITIVE_CHAR;

    public final IPrimitiveType PRIMITIVE_BYTE;

    public final IPrimitiveType PRIMITIVE_SHORT;

    public final IPrimitiveType PRIMITIVE_INT;

    public final IPrimitiveType PRIMITIVE_LONG;

    public final IPrimitiveType PRIMITIVE_FLOAT;

    public final IPrimitiveType PRIMITIVE_DOUBLE;

    public final IVoidType VOID;

    public final INullType NULL;

    public final IClassType BOXED_BOOLEAN;

    public final IClassType BOXED_CHAR;

    public final IClassType BOXED_BYTE;

    public final IClassType BOXED_SHORT;

    public final IClassType BOXED_INT;

    public final IClassType BOXED_LONG;

    public final IClassType BOXED_FLOAT;

    public final IClassType BOXED_DOUBLE;

    public final IClassType BIG_INTEGER;

    public final IClassType BIG_DECIMAL;

    public final IClassType OBJECT;

    public final IClassType NUMBER;

    public final IClassType THROWABLE;

    public final IEnumType ENUM;

    public final IClassType CLASS;

    public final IClassType STRING;

    public final IAnnotationType START;

    public final IClassType LIST;

    public final IClassType ITERABLE;

    public final IInterfaceType DELEGATE;

    public final IInterfaceType FUNCTOR;

    public final IClassType MODULE;

    public final IClassType MODULE_DELEGATE;

    public final IClassType ABSTRACT_MODULE;

    public final IClassType ABSTRACT_DELEGATE;

    public final IClassType HELPERS;

    public final IClassType YIELD_STATE;

    public final IClassType ARGUMENT_STACK;

    public final IClassType CONVERSIONS;

    public final IClassType OPERATORS;

    public final IClassType LOCALS_MAP;

    public final IClassType LOCAL;

    /**
     * Sole Constructor.
     *
     * @param factory is the type-factory being used by the compiler.
     */
    public TypeSystemUtils(final ITypeFactory factory)
    {
        Preconditions.checkNotNull(factory);

        this.factory = factory;

        this.PRIMITIVE_BOOLEAN = factory.getBoolean();
        this.PRIMITIVE_CHAR = factory.getChar();
        this.PRIMITIVE_BYTE = factory.getByte();
        this.PRIMITIVE_SHORT = factory.getShort();
        this.PRIMITIVE_INT = factory.getInt();
        this.PRIMITIVE_LONG = factory.getLong();
        this.PRIMITIVE_FLOAT = factory.getFloat();
        this.PRIMITIVE_DOUBLE = factory.getDouble();

        this.VOID = factory.getVoid();

        this.NULL = factory.getNull();

        this.BOXED_BOOLEAN = (IClassType) factory.fromClass(Boolean.class);
        this.BOXED_CHAR = (IClassType) factory.fromClass(Character.class);
        this.BOXED_BYTE = (IClassType) factory.fromClass(Byte.class);
        this.BOXED_SHORT = (IClassType) factory.fromClass(Short.class);
        this.BOXED_INT = (IClassType) factory.fromClass(Integer.class);
        this.BOXED_LONG = (IClassType) factory.fromClass(Long.class);
        this.BOXED_FLOAT = (IClassType) factory.fromClass(Float.class);
        this.BOXED_DOUBLE = (IClassType) factory.fromClass(Double.class);

        this.BIG_INTEGER = (IClassType) factory.fromClass(BigInteger.class);
        this.BIG_DECIMAL = (IClassType) factory.fromClass(BigDecimal.class);

        this.OBJECT = (IClassType) factory.fromClass(Object.class);

        this.NUMBER = (IClassType) factory.fromClass(Number.class);

        this.THROWABLE = (IClassType) factory.fromClass(Throwable.class);

        this.ENUM = (IEnumType) factory.fromClass(Enum.class);

        this.CLASS = (IClassType) factory.fromClass(Class.class);

        this.STRING = (IClassType) factory.fromClass(String.class);

        this.LIST = (IClassType) factory.fromClass(List.class);

        this.ITERABLE = (IClassType) factory.fromClass(Iterable.class);

        this.DELEGATE = (IInterfaceType) factory.fromClass(Delegate.class);

        this.FUNCTOR = (IInterfaceType) factory.fromClass(Functor.class);

        this.MODULE = (IClassType) factory.fromClass(Module.class);

        this.MODULE_DELEGATE = (IClassType) factory.fromClass(ModuleDelegate.class);

        this.ABSTRACT_MODULE = (IClassType) factory.fromClass(AbstractModule.class);

        this.ABSTRACT_DELEGATE = (IClassType) factory.fromClass(AbstractDelegate.class);

        this.HELPERS = (IClassType) factory.fromClass(Helpers.class);

        this.YIELD_STATE = (IClassType) factory.fromClass(YieldState.class);

        this.ARGUMENT_STACK = (IClassType) factory.fromClass(ArgumentStack.class);

        this.START = (IAnnotationType) factory.fromClass(Start.class);

        this.CONVERSIONS = (IClassType) factory.fromClass(Conversions.class);

        this.OPERATORS = (IClassType) factory.fromClass(Operators.class);

        this.LOCALS_MAP = (IClassType) factory.fromClass(LocalsMap.class);

        this.LOCAL = (IClassType) factory.fromClass(Local.class);
    }

    /**
     * This method determines whether a value of a type X can be converted to a value of a type Y,
     * as is done during method invocations and variable assignments. Specifically, an assignment
     * conversion is either a boxing conversion, an unboxing conversion, an identity conversion,
     * or an upcast.
     *
     * @param input is the type of value that will be converted.
     * @param output is the type to convert the input value to.
     * @return the bytecode that performs the actual conversion,
     * or null, if no such conversion is possible.
     */
    public List<AbstractInsnNode> assign(final IType input,
                                         final IType output)
    {
        if (input.isSubtypeOf(output))
        {
            return Lists.newLinkedList();
        }

        final List<AbstractInsnNode> box_code = box(input, output);

        if (box_code != null)
        {
            return box_code;
        }

        final List<AbstractInsnNode> unbox_code = unbox(input, output);

        if (unbox_code != null)
        {
            return unbox_code;
        }

        return null;
    }

    /**
     * This method generates the bytecode necessary to perform a boxing conversion.
     *
     * <p>
     * <b>All Possible Boxing Conversions</b> <br>
     *
     * <table border="1">
     * <tr> <td><code>boolean</code></td> <td><code>java.lang.Boolean</code></td></tr>
     * <tr> <td><code>char</code></td> <td><code>java.lang.Character</code></td></tr>
     * <tr> <td><code>byte</code></td> <td><code>java.lang.Byte</code></td></tr>
     * <tr> <td><code>short</code></td> <td><code>java.lang.Short</code></td></tr>
     * <tr> <td><code>int</code></td> <td><code>java.lang.Integer</code></td></tr>
     * <tr> <td><code>long</code></td> <td><code>java.lang.Long</code></td></tr>
     * <tr> <td><code>float</code></td> <td><code>java.lang.Float</code></td></tr>
     * <tr> <td><code>double</code></td> <td><code>java.lang.Double</code></td></tr>
     *
     * <tr> <td><code>byte</code></td> <td><code>java.lang.Number</code></td></tr>
     * <tr> <td><code>short</code></td> <td><code>java.lang.Number</code></td></tr>
     * <tr> <td><code>int</code></td> <td><code>java.lang.Number</code></td></tr>
     * <tr> <td><code>long</code></td> <td><code>java.lang.Number</code></td></tr>
     * <tr> <td><code>float</code></td> <td><code>java.lang.Number</code></td></tr>
     * <tr> <td><code>double</code></td> <td><code>java.lang.Number</code></td></tr>
     *
     * <tr> <td><code>boolean</code></td> <td><code>java.lang.Comparable</code></td></tr>
     * <tr> <td><code>char</code></td> <td><code>java.lang.Comparable</code></td></tr>
     * <tr> <td><code>byte</code></td> <td><code>java.lang.Comparable</code></td></tr>
     * <tr> <td><code>short</code></td> <td><code>java.lang.Comparable</code></td></tr>
     * <tr> <td><code>int</code></td> <td><code>java.lang.Comparable</code></td></tr>
     * <tr> <td><code>long</code></td> <td><code>java.lang.Comparable</code></td></tr>
     * <tr> <td><code>float</code></td> <td><code>java.lang.Comparable</code></td></tr>
     * <tr> <td><code>double</code></td> <td><code>java.lang.Comparable</code></td></tr>
     *
     * <tr> <td><code>boolean</code></td> <td><code>java.lang.Object</code></td></tr>
     * <tr> <td><code>char</code></td> <td><code>java.lang.Object</code></td></tr>
     * <tr> <td><code>byte</code></td> <td><code>java.lang.Object</code></td></tr>
     * <tr> <td><code>short</code></td> <td><code>java.lang.Object</code></td></tr>
     * <tr> <td><code>int</code></td> <td><code>java.lang.Object</code></td></tr>
     * <tr> <td><code>long</code></td> <td><code>java.lang.Object</code></td></tr>
     * <tr> <td><code>float</code></td> <td><code>java.lang.Object</code></td></tr>
     * <tr> <td><code>double</code></td> <td><code>java.lang.Object</code></td></tr>
     * </table>
     * </p>
     *
     * @param input is the type of value that will be converted.
     * @param output is the type to convert the input value to.
     * @return the bytecode that performs the actual conversion,
     * or null, if no such conversion is possible.
     */
    public List<AbstractInsnNode> box(final IType input,
                                      final IType output)
    {
        final List<AbstractInsnNode> result = Lists.newLinkedList();

        final boolean is_object = output.equals(OBJECT);

        final boolean is_number = output.equals(NUMBER);

        final boolean is_comparable = output.equals(factory.fromClass(Comparable.class));

        if (input.equals(PRIMITIVE_BOOLEAN) && (output.equals(BOXED_BOOLEAN) || is_object || is_comparable))
        {
            result.add(new MethodInsnNode(Opcodes.INVOKESTATIC,
                                          Utils.internalName(CONVERSIONS),
                                          "box",
                                          "(Z)Ljava/lang/Boolean;"));
        }
        else if (input.equals(PRIMITIVE_CHAR) && (output.equals(BOXED_CHAR) || is_object || is_comparable))
        {
            result.add(new MethodInsnNode(Opcodes.INVOKESTATIC,
                                          Utils.internalName(CONVERSIONS),
                                          "box",
                                          "(C)Ljava/lang/Character;"));
        }
        else if (input.equals(PRIMITIVE_BYTE) && (output.equals(BOXED_BYTE) || is_object || is_number || is_comparable))
        {
            result.add(new MethodInsnNode(Opcodes.INVOKESTATIC,
                                          Utils.internalName(CONVERSIONS),
                                          "box",
                                          "(B)Ljava/lang/Byte;"));
        }
        else if (input.equals(PRIMITIVE_SHORT) && (output.equals(BOXED_SHORT) || is_object || is_number || is_comparable))
        {
            result.add(new MethodInsnNode(Opcodes.INVOKESTATIC,
                                          Utils.internalName(CONVERSIONS),
                                          "box",
                                          "(S)Ljava/lang/Short;"));
        }
        else if (input.equals(PRIMITIVE_INT) && (output.equals(BOXED_INT) || is_object || is_number || is_comparable))
        {
            result.add(new MethodInsnNode(Opcodes.INVOKESTATIC,
                                          Utils.internalName(CONVERSIONS),
                                          "box",
                                          "(I)Ljava/lang/Integer;"));
        }
        else if (input.equals(PRIMITIVE_LONG) && (output.equals(BOXED_LONG) || is_object || is_number || is_comparable))
        {
            result.add(new MethodInsnNode(Opcodes.INVOKESTATIC,
                                          Utils.internalName(CONVERSIONS),
                                          "box",
                                          "(J)Ljava/lang/Long;"));
        }
        else if (input.equals(PRIMITIVE_FLOAT) && (output.equals(BOXED_FLOAT) || is_object || is_number || is_comparable))
        {
            result.add(new MethodInsnNode(Opcodes.INVOKESTATIC,
                                          Utils.internalName(CONVERSIONS),
                                          "box",
                                          "(F)Ljava/lang/Float;"));
        }
        else if (input.equals(PRIMITIVE_DOUBLE) && (output.equals(BOXED_DOUBLE) || is_object || is_number || is_comparable))
        {
            result.add(new MethodInsnNode(Opcodes.INVOKESTATIC,
                                          Utils.internalName(CONVERSIONS),
                                          "box",
                                          "(D)Ljava/lang/Double;"));
        }

        return result.isEmpty() ? null : result;
    }

    /**
     * This method generates the bytecode necessary to perform an unboxing conversion.
     *
     * <p>
     * <b>All Possible Unboxing Conversions</b> <br>
     *
     * <table border="1">
     * <tr> <td><code>java.lang.Boolean</code></td> <td><code>boolean</code></td> </tr>
     * <tr> <td><code>java.lang.Character</code></td> <td><code>char</code></td> </tr>
     * <tr> <td><code>java.lang.Byte</code></td> <td><code>byte</code></td> </tr>
     * <tr> <td><code>java.lang.Short</code></td> <td><code>short</code></td> </tr>
     * <tr> <td><code>java.lang.Integer</code></td> <td><code>int</code></td> </tr>
     * <tr> <td><code>java.lang.Long</code></td> <td><code>long</code></td> </tr>
     * <tr> <td><code>java.lang.Float</code></td> <td><code>float</code></td> </tr>
     * <tr> <td><code>java.lang.Double</code></td> <td><code>double</code></td> </tr>
     * </table>
     * </p>
     *
     * @param input is the type of value that will be be converted.
     * @param output is the type to convert the input value to.
     * @return the bytecode that performs the actual conversion,
     * or null, if no such conversion is possible.
     */
    public List<AbstractInsnNode> unbox(final IType input,
                                        final IType output)
    {
        final List<AbstractInsnNode> result = Lists.newLinkedList();

        final boolean is_object = output.equals(OBJECT);

        final boolean is_number = output.equals(NUMBER);

        if (output.equals(PRIMITIVE_BOOLEAN) && input.equals(BOXED_BOOLEAN))
        {
            result.add(new MethodInsnNode(Opcodes.INVOKESTATIC,
                                          Utils.internalName(CONVERSIONS),
                                          "unbox",
                                          "(Ljava/lang/Boolean;)Z"));
        }
        else if (output.equals(PRIMITIVE_CHAR) && input.equals(BOXED_CHAR))
        {
            result.add(new MethodInsnNode(Opcodes.INVOKESTATIC,
                                          Utils.internalName(CONVERSIONS),
                                          "unbox",
                                          "(Ljava/lang/Character;)C"));
        }
        else if (output.equals(PRIMITIVE_BYTE) && input.equals(BOXED_BYTE))
        {
            result.add(new MethodInsnNode(Opcodes.INVOKESTATIC,
                                          Utils.internalName(CONVERSIONS),
                                          "unbox",
                                          "(Ljava/lang/Byte;)B"));
        }
        else if (output.equals(PRIMITIVE_SHORT) && input.equals(BOXED_SHORT))
        {
            result.add(new MethodInsnNode(Opcodes.INVOKESTATIC,
                                          Utils.internalName(CONVERSIONS),
                                          "unbox",
                                          "(Ljava/lang/Short;)S"));
        }
        else if (output.equals(PRIMITIVE_INT) && input.equals(BOXED_INT))
        {
            result.add(new MethodInsnNode(Opcodes.INVOKESTATIC,
                                          Utils.internalName(CONVERSIONS),
                                          "unbox",
                                          "(Ljava/lang/Integer;)I"));
        }
        else if (output.equals(PRIMITIVE_LONG) && input.equals(BOXED_LONG))
        {
            result.add(new MethodInsnNode(Opcodes.INVOKESTATIC,
                                          Utils.internalName(CONVERSIONS),
                                          "unbox",
                                          "(Ljava/lang/Long;)J"));
        }
        else if (output.equals(PRIMITIVE_FLOAT) && input.equals(BOXED_FLOAT))
        {
            result.add(new MethodInsnNode(Opcodes.INVOKESTATIC,
                                          Utils.internalName(CONVERSIONS),
                                          "unbox",
                                          "(Ljava/lang/Float;)F"));
        }
        else if (output.equals(PRIMITIVE_DOUBLE) && input.equals(BOXED_DOUBLE))
        {
            result.add(new MethodInsnNode(Opcodes.INVOKESTATIC,
                                          Utils.internalName(CONVERSIONS),
                                          "unbox",
                                          "(Ljava/lang/Double;)D"));
        }

        return result.isEmpty() ? null : result;
    }

    /**
     * This method sorts a collection of invokable members, using a topological sort,
     * such that narrowest methods are closest the front of the resultant list.
     *
     * @param members are the invokable members to sort.
     * @return the aforedescribed topologically sorted list.
     */
    public <T extends IInvokableMember> List<T> sort(final Collection<T> members)
    {
        final TopoSorter<T> sorter = new TopoSorter<T>()
        {
            @Override
            public boolean isLess(final IInvokableMember left,
                                  final IInvokableMember right)
            {
                final boolean result = compare(left, right);

                return result;
            }
        };

        sorter.addAll(members);

        return sorter.elements();
    }

    /**
     * This method compares a method/constructor X to another method/constructor Y in order to
     * determine whether X is more specific than Y.
     *
     * @param left is X.
     * @param right is Y.
     * @return true, if and only if, X is more specific than Y.
     */
    final boolean compare(final IInvokableMember left,
                          final IInvokableMember right)
    {
        final boolean LESS = true;

        if (left.getName().compareTo(right.getName()) < 0)
        {
            return LESS;
        }
        else if (left.getName().equals(right.getName()) == false)
        {
            return !LESS;
        }

        assert left.getName().equals(right.getName());

        final List<IFormalParameter> left_params = left.getFormalParameters();
        final List<IFormalParameter> right_params = right.getFormalParameters();

        if (left_params.size() < right_params.size())
        {
            return LESS;
        }
        else if (left_params.size() > right_params.size())
        {
            return !LESS;
        }

        assert left_params.size() == right_params.size();

        final boolean is_proper_subtype = (!left.getOwner().equals(right.getOwner()))
                                          && left.getOwner().isSubtypeOf(right.getOwner());

        if (is_proper_subtype)
        {
            return LESS;
        }

        for (int i = 0; i < left_params.size(); i++)
        {
            final IVariableType left_param = left_params.get(i).getType();
            final IVariableType right_param = right_params.get(i).getType();

            final boolean subtype = left_param.isSubtypeOf(right_param);

            if (subtype)
            {
                return LESS;
            }
            else if (left_param.isPrimitiveType() && !right_param.isPrimitiveType())
            {
                return LESS;
            }
        }

        final boolean more_specific_return = !left.getReturnType().equals(right.getReturnType())
                                             && left.getReturnType().isSubtypeOf(right.getReturnType());

        if (more_specific_return)
        {
            return LESS;
        }

        return !LESS;
    }

    /**
     * Given the types of the arguments in an arguments-list and a set of invokable members,
     * this method selects those invokable members whose formal-parameter types are acceptable
     * matches for the given arguments.
     *
     * <p>
     * A method will be selected, if each argument X is convertible to the related
     * formal-parameter type P, using an assignment conversion.
     * </p>
     *
     * @param members is the list invokable members to choose from.
     * @param arguments are the types of the arguments.
     * @return a list containing the selected members.
     */
    public <T extends IInvokableMember> List<T> checkArgs(final List<T> members,
                                                          final List<IType> arguments)
    {
        final List<T> result = Lists.newLinkedList();

NEXT_METHOD:
        for (IInvokableMember method : members)
        {
            if (method.getFormalParameters().size() != arguments.size())
            {
                continue;
            }

            for (int i = 0; i < arguments.size(); i++)
            {
                final IType parameter = method.getFormalParameters().get(i).getType();

                final IType argument = arguments.get(i);

                if (assign(argument, parameter) == null)
                {
                    continue NEXT_METHOD;
                }
            }

            result.add((T) method);
        }

        return result;
    }

    /**
     * This method selects the accessible members of a type that have a given name.
     *
     * @param usage_site is the type from where the members are being accessed from.
     * @param members are the members to examine and possibly select.
     * @param shared is true, if and only if, the desired members are static.
     * @param name the name of the desired members.
     * @return a list containing the selected members.
     */
    public <T extends IMember> List<T> select(final IDeclaredType usage_site,
                                              final Collection<T> members,
                                              final boolean shared,
                                              final String name)
    {
        final List<T> result = Lists.newLinkedList();

        for (IMember member : members)
        {
            final boolean name_match = member.getName().equals(name);

            final boolean static_match = shared == Modifier.isStatic(member.getModifiers());

            final boolean accessible = isAccessible(usage_site, member);

            if (accessible && name_match && static_match)
            {
                result.add((T) member);
            }
        }

        return result;
    }

    /**
     * This method determines whether a given constructor, method, or field is accessible.
     *
     * @param user is the place where the member is being used from.
     * @param member is the member being used.
     * @return true, iff the member is accessible.
     */
    public boolean isAccessible(final IDeclaredType user,
                                final IMember member)
    {
        Preconditions.checkNotNull(user);
        Preconditions.checkNotNull(member);

        // Case: A member of an inaccessible type is not accessible.

        if (isAccessible(user, member.getOwner()) == false)
        {
            return false;
        }

        // Case: A member, with public-access, of an accessible type is always accessible.

        if (Modifier.isPublic(member.getModifiers()))
        {
            return true;
        }

        // Case: A member, with package-access, of an accessible type is only accessible
        //        from within the same package.

        final String namespace1 = user.getNamespace();
        final String namespace2 = member.getOwner().getNamespace();

        return namespace1.equals(namespace2);
    }

    /**
     * This method determines whether a given type is accessible.
     *
     * @param usage_site is the place where the type is being used from.
     * @param used is the type being used.
     * @return true, iff the type being used is accessible.
     */
    public boolean isAccessible(final IDeclaredType usage_site,
                                final IDeclaredType used)
    {
        Preconditions.checkNotNull(usage_site);
        Preconditions.checkNotNull(used);

        // Case: A type is always accessible from within itself.

        if (usage_site.equals(used))
        {
            return true;
        }

        // Case: A type that has public-access is always accessible.

        if (Modifier.isPublic(used.getModifiers()))
        {
            return true;
        }

        // Case: A type that has package-access is accessible from
        //         any other type within the same package.

        final String namespace1 = usage_site.getNamespace();
        final String namespace2 = used.getNamespace();

        return namespace1.equals(namespace2);
    }

    /**
     * This method resolves a non-static field.
     *
     * @param user is the site where the field is being accessed.
     * @param owner is the type that contains the field.
     * @param name is the name of the field.
     * @return the resolved field; or null, if no such field could be found.
     */
    public IField resolveField(final IDeclaredType user,
                               final IDeclaredType owner,
                               final String name)
    {
        Preconditions.checkNotNull(user);
        Preconditions.checkNotNull(owner);
        Preconditions.checkNotNull(name);

        final List<IField> result = select(user, owner.getFields(), false, name);

        return (result.isEmpty() ? null : result.get(0));
    }

    /**
     * This method resolves a static field.
     *
     * @param user is the site where the field is being accessed.
     * @param owner is the type that contains the field.
     * @param name is the name of the field.
     * @return the resolved field; or null, if no such field could be found.
     */
    public IField resolveStaticField(final IDeclaredType user,
                                     final IDeclaredType owner,
                                     final String name)
    {
        Preconditions.checkNotNull(user);
        Preconditions.checkNotNull(owner);
        Preconditions.checkNotNull(name);

        final List<IField> result = select(user, owner.getFields(), true, name);

        return (result.isEmpty() ? null : result.get(0));
    }

    /**
     * This method resolves a constructor.
     *
     * @param user is the site where the constructor is being invoked.
     * @param owner is the type that contains the constructor.
     * @param arguments are the types of the arguments being passed to the constructor.
     * @return the resolved constructor; or null, if no such constructor could be found.
     */
    public List<IConstructor> resolveCtors(final IDeclaredType user,
                                           final IDeclaredType owner,
                                           final List<IType> arguments)
    {
        Preconditions.checkNotNull(user);
        Preconditions.checkNotNull(owner);
        Preconditions.checkNotNull(arguments);

        final List<IConstructor> selected = select(user,
                                                   owner.getConstructors(),
                                                   false,
                                                   "<init>");

        final List<IConstructor> applicable = checkArgs(selected, arguments);

        final List<IConstructor> sorted = sort(applicable);

        return sorted;
    }

    /**
     * This method resolves a non-static method.
     *
     * @param user is the site where the method is being invoked.
     * @param owner is the type that contains the method.
     * @param name is the name of the method.
     * @param arguments are the types of the arguments being passed to the method.
     * @return the resolved method; or null, if no such method could be found.
     */
    public List<IMethod> resolveMethods(final IDeclaredType user,
                                        final IDeclaredType owner,
                                        final String name,
                                        final List<IType> arguments)
    {
        Preconditions.checkNotNull(user);
        Preconditions.checkNotNull(owner);
        Preconditions.checkNotNull(name);
        Preconditions.checkNotNull(arguments);

        final List<IMethod> selected = select(user,
                                              owner.getAllVisibleMethods(),
                                              false,
                                              name);

        final List<IMethod> applicable = checkArgs(selected, arguments);

        final List<IMethod> sorted = sort(applicable);

        return sorted;
    }

    /**
     * This method resolves a static method.
     *
     * @param user is the site where the method is being invoked.
     * @param owner is the type that contains the method.
     * @param name is the name of the method.
     * @param arguments are the types of the arguments being passed to the method.
     * @return the resolved method; or null, if no such method could be found.
     */
    public List<IMethod> resolveStaticMethods(final IDeclaredType user,
                                              final IDeclaredType owner,
                                              final String name,
                                              final List<IType> arguments)
    {
        Preconditions.checkNotNull(user);
        Preconditions.checkNotNull(owner);
        Preconditions.checkNotNull(name);
        Preconditions.checkNotNull(arguments);

        final List<IMethod> selected = select(user,
                                              owner.getAllVisibleMethods(),
                                              true,
                                              name);

        final List<IMethod> applicable = checkArgs(selected, arguments);

        final List<IMethod> sorted = sort(applicable);

        return sorted;
    }

    /**
     * This method retrieves a type from the type-factory.
     *
     * @param typename is the fully-qualified name of the type. (not descriptor or internal name)
     * @param dimensions is the number of dimensions in the array-type, otherwise, null.
     * @return the type denoted by the type-name and the optional dimensions,
     * or null, if the type does not exist or cannot exist.
     */
    public IReturnType findType(final String typename,
                                final Integer dimensions)
    {
        Preconditions.checkNotNull(typename);

        final IReturnType element;

        // Primitive Types
        if ("void".equals(typename))
        {
            element = this.VOID;
        }
        else if ("boolean".equals(typename))
        {
            element = this.PRIMITIVE_BOOLEAN;
        }
        else if ("char".equals(typename))
        {
            element = this.PRIMITIVE_CHAR;
        }
        else if ("byte".equals(typename))
        {
            element = this.PRIMITIVE_BYTE;
        }
        else if ("short".equals(typename))
        {
            element = this.PRIMITIVE_SHORT;
        }
        else if ("int".equals(typename))
        {
            element = this.PRIMITIVE_INT;
        }
        else if ("long".equals(typename))
        {
            element = this.PRIMITIVE_LONG;
        }
        else if ("float".equals(typename))
        {
            element = this.PRIMITIVE_FLOAT;
        }
        else if ("double".equals(typename))
        {
            element = this.PRIMITIVE_DOUBLE;
        }
        else
        {
            final String descriptor = "L" + typename.replace('.', '/') + ";";

            element = (IReturnType) factory.findType(descriptor);
        }

        if (element == null || dimensions == null)
        {
            return element;
        }
        else
        {
            if (element instanceof IElementType == false)
            {
                // For example, void[] cannot exist.
                return null;
            }
            else
            {
                final IReturnType array = factory.getArrayType((IElementType) element, dimensions);

                return array;
            }
        }
    }

    /**
     * This method extract the name of a type from a type-specifier.
     *
     * <p>
     * Example: "java.lang.String[][][]" will return "java.util.String".
     * </p>
     *
     * @param specifier is the type-specifier itself.
     * @return the fully-qualified name, excluding dimensions, of the specified type.
     */
    public String extractTypeName(final TypeSpecifier specifier)
    {
        Preconditions.checkNotNull(specifier);

        final StringBuilder result = new StringBuilder();

        if (specifier.getNamespace() != null)
        {
            for (Name x : specifier.getNamespace().getNames())
            {
                result.append(x.getName()).append(".");
            }
        }

        result.append(specifier.getName().getName());

        return result.toString();
    }

    /**
     * This method determines whether a type is a subtype of another type.
     *
     * @param instance is the possible subtype.
     * @param supertype is the possible supertype.
     * @return true, iff the instance is a subtype of the supertype.
     */
    public boolean isSubtypeOf(final IType instance,
                               final Class supertype)
    {
        Preconditions.checkNotNull(instance);
        Preconditions.checkNotNull(supertype);

        final IType target = factory.fromClass(supertype);

        return target != null && instance.isSubtypeOf(target);
    }

    /**
     * This method sorts a group of types.
     *
     * <p>
     * The returned list will be sorted from most specific to most abstract.
     * </p>
     *
     * @param types are the types to sort.
     * @return a sorted immutable list containing the types.
     */
    public <T extends IType> List<T> sort(final Iterable<T> types)
    {
        Preconditions.checkNotNull(types);

        final TopoSorter<T> sorter = new TopoSorter<T>()
        {
            @Override
            public boolean isLess(IType left,
                                  IType right)
            {
                return left.isSubtypeOf(right);
            }
        };

        sorter.addAll(types);

        final List<T> result = ImmutableList.copyOf(sorter.elements());

        return result;
    }

    /**
     * This method determines which of two types is the widest type.
     *
     * @param left is first of the two types.
     * @param right is second of the two types.
     * @return the widest of the two types, or null, if the two types are incompatible.
     */
    public <T extends IType> T widestType(final T left,
                                          final T right)
    {
        Preconditions.checkNotNull(left);
        Preconditions.checkNotNull(right);

        final boolean test1 = left.isSubtypeOf(right);
        final boolean test2 = right.isSubtypeOf(left);

        if (test1)
        {
            return right;
        }
        else if (test2)
        {
            return left;
        }
        else
        {
            return null;
        }
    }

    /**
     * This method searches for an annotation, given the annotation's descriptor.
     *
     * @param annotatable is the annotatable entity that may contain the annotation.
     * @param descriptor is the descriptor of the sought after annotation.
     * @return the found annotation, or null, if the annotation was not found.
     */
    public static IAnnotation findAnnotation(final IAnnotatable annotatable,
                                             final String descriptor)
    {
        Preconditions.checkNotNull(annotatable);
        Preconditions.checkNotNull(descriptor);

        for (IAnnotation x : annotatable.getAnnotations())
        {
            if (descriptor.equals(x.getAnnotationType().getDescriptor()))
            {
                return x;
            }
        }

        return null;
    }

    /**
     * This method determines whether an annotation is applied to an annotatable entity.
     *
     * @param annotatable is the annotatable entity that may contain the annotation.
     * @param descriptor is the descriptor of the sought after annotation.
     * @return true, iff the annotation was found.
     */
    public static boolean isAnnotationPresent(final IAnnotatable annotatable,
                                              final String descriptor)
    {
        return findAnnotation(annotatable, descriptor) != null;
    }

    /**
     * This method determines whether an annotation is applied to an annotatable entity.
     *
     * @param annotatable is the annotatable entity that may contain the annotation.
     * @param clazz is the type of the sought after annotation.
     * @return true, iff the annotation was found.
     */
    public static boolean isAnnotationPresent(final IAnnotatable annotatable,
                                              final Class clazz)
    {
        Preconditions.checkNotNull(annotatable);
        Preconditions.checkNotNull(clazz);

        final String descriptor = Type.getDescriptor(clazz);

        return findAnnotation(annotatable, descriptor) != null;
    }

    /**
     * This method counts the number of annotatables that are annotated with a given annotation.
     *
     * @param annotatables are the classes/methods/etc that may be annotated.
     * @param annotation is the annotation that may be applied to the annotatables.
     * @return the number of annotatables that have the annotation applied to them.
     */
    public static int countAnnotations(final Iterable<? extends IAnnotatable> annotatables,
                                       final Class annotation)
    {
        Preconditions.checkNotNull(annotatables);
        Preconditions.checkNotNull(annotation);

        int count = 0;

        for (IAnnotatable annotatable : annotatables)
        {
            if (isAnnotationPresent(annotatable, annotation))
            {
                ++count;
            }
        }

        return count;
    }

    /**
     * This method searches through a list for a specific method or constructor.
     *
     * @param list is a list of methods and/or constructors.
     * @param name is the name of the element to find.
     * @param descriptor is the descriptor of the element to find.
     * @return the found element; or null, if no such element exists.
     */
    public static <T extends IInvokableMember> T find(final Iterable<? extends T> list,
                                                      final String name,
                                                      final String descriptor)
    {
        Preconditions.checkNotNull(list);
        Preconditions.checkNotNull(name);
        Preconditions.checkNotNull(descriptor);

        for (T element : list)
        {
            if (element.getName().equals(name) && element.getDescriptor().equals(descriptor))
            {
                return element;
            }
        }

        return null;
    }
}
