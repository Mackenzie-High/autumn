package com.mackenziehigh.autumn.lang.compiler.utils;

import autumn.lang.Delegate;
import autumn.lang.Functor;
import autumn.lang.Lambda;
import autumn.lang.Local;
import autumn.lang.LocalsMap;
import autumn.lang.Module;
import autumn.lang.ModuleInfo;
import autumn.lang.Record;
import autumn.lang.TypedFunctor;
import autumn.lang.annotations.Infer;
import autumn.lang.annotations.Setup;
import autumn.lang.annotations.Start;
import autumn.lang.annotations.Sync;
import autumn.lang.compiler.ast.nodes.Name;
import autumn.lang.compiler.ast.nodes.TypeSpecifier;
import autumn.lang.internals.AbstractDefinedFunctor;
import autumn.lang.internals.AbstractDelegate;
import autumn.lang.internals.AbstractLambda;
import autumn.lang.internals.AbstractModule;
import autumn.lang.internals.AbstractRecord;
import autumn.lang.internals.ArgumentStack;
import autumn.lang.internals.Conversions;
import autumn.lang.internals.Helpers;
import autumn.lang.internals.ModuleDelegate;
import autumn.lang.internals.ModuleInfoBuilder;
import autumn.lang.internals.Operators;
import autumn.util.test.Test;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mackenziehigh.autumn.lang.compiler.typesystem.CustomFormalParameter;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IAnnotatable;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IAnnotation;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IAnnotationType;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IClassType;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IConstructor;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IDeclaredType;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IElementType;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IEnumType;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IExpressionType;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IField;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IFormalParameter;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IInterfaceType;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IInvokableMember;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IMember;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IMethod;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.INullType;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IPrimitiveType;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IReferenceType;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IReturnType;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IType;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.ITypeFactory;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IVariableType;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IVoidType;
import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
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

    public final IInterfaceType ANNOTATION;

    public final IClassType OBJECT;

    public final IClassType COMPARABLE;

    public final IClassType NUMBER;

    public final IClassType THROWABLE;

    public final IEnumType ENUM;

    public final IClassType CLASS;

    public final IClassType STRING;

    public final IAnnotationType START;

    public final IInterfaceType LIST;

    public final IInterfaceType ITERABLE;

    public final IInterfaceType DELEGATE;

    public final IInterfaceType FUNCTOR;

    public final IInterfaceType LAMBDA;

    public final IInterfaceType RECORD;

    public final IInterfaceType MODULE;

    public final IClassType MODULE_DELEGATE;

    public final IClassType ABSTRACT_MODULE;

    public final IClassType ABSTRACT_DELEGATE;

    public final IClassType ABSTRACT_LAMBDA;

    public final IClassType ABSTRACT_RECORD;

    public final IClassType ABSTRACT_STATIC_FUNCTOR;

    public final IClassType HELPERS;

    public final IClassType ARGUMENT_STACK;

    public final IClassType CONVERSIONS;

    public final IClassType OPERATORS;

    public final IClassType LOCALS_MAP;

    public final IClassType LOCAL;

    public final IInterfaceType TYPED_FUNCTOR;

    public final IClassType MODULE_INFO_BUILDER;

    public final IInterfaceType MODULE_INFO;

    public final IAnnotationType SETUP;

    public final IAnnotationType SYNC;

    public final IAnnotationType INFER;

    public final IAnnotationType TEST;

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

        this.ANNOTATION = (IInterfaceType) factory.fromClass(Annotation.class);

        this.OBJECT = (IClassType) factory.fromClass(Object.class);

        this.COMPARABLE = (IClassType) factory.fromClass(Comparable.class);

        this.NUMBER = (IClassType) factory.fromClass(Number.class);

        this.THROWABLE = (IClassType) factory.fromClass(Throwable.class);

        this.ENUM = (IEnumType) factory.fromClass(Enum.class);

        this.CLASS = (IClassType) factory.fromClass(Class.class);

        this.STRING = (IClassType) factory.fromClass(String.class);

        this.RECORD = (IInterfaceType) factory.fromClass(Record.class);

        this.LIST = (IInterfaceType) factory.fromClass(List.class);

        this.ITERABLE = (IInterfaceType) factory.fromClass(Iterable.class);

        this.DELEGATE = (IInterfaceType) factory.fromClass(Delegate.class);

        this.FUNCTOR = (IInterfaceType) factory.fromClass(Functor.class);

        this.TYPED_FUNCTOR = (IInterfaceType) factory.fromClass(TypedFunctor.class);

        this.LAMBDA = (IInterfaceType) factory.fromClass(Lambda.class);

        this.MODULE = (IInterfaceType) factory.fromClass(Module.class);

        this.MODULE_DELEGATE = (IClassType) factory.fromClass(ModuleDelegate.class);

        this.ABSTRACT_RECORD = (IClassType) factory.fromClass(AbstractRecord.class);

        this.ABSTRACT_MODULE = (IClassType) factory.fromClass(AbstractModule.class);

        this.ABSTRACT_DELEGATE = (IClassType) factory.fromClass(AbstractDelegate.class);

        this.ABSTRACT_LAMBDA = (IClassType) factory.fromClass(AbstractLambda.class);

        this.ABSTRACT_STATIC_FUNCTOR = (IClassType) factory.fromClass(AbstractDefinedFunctor.class);

        this.HELPERS = (IClassType) factory.fromClass(Helpers.class);

        this.ARGUMENT_STACK = (IClassType) factory.fromClass(ArgumentStack.class);

        this.START = (IAnnotationType) factory.fromClass(Start.class);

        this.CONVERSIONS = (IClassType) factory.fromClass(Conversions.class);

        this.OPERATORS = (IClassType) factory.fromClass(Operators.class);

        this.LOCALS_MAP = (IClassType) factory.fromClass(LocalsMap.class);

        this.LOCAL = (IClassType) factory.fromClass(Local.class);

        this.MODULE_INFO_BUILDER = (IClassType) factory.fromClass(ModuleInfoBuilder.class);

        this.MODULE_INFO = (IInterfaceType) factory.fromClass(ModuleInfo.class);

        this.SETUP = (IAnnotationType) factory.fromClass(Setup.class);

        this.SYNC = (IAnnotationType) factory.fromClass(Sync.class);

        this.INFER = (IAnnotationType) factory.fromClass(Infer.class);

        this.TEST = (IAnnotationType) factory.fromClass(Test.class);
    }

    /**
     * This method determines whether a value of a type X can be converted to a value of a type Y,
     * as is done during method invocations and variable assignments. Specifically, an assignment
     * conversion is either a boxing conversion, an unboxing conversion, an identity conversion,
     * an upcast, or a primitive coercion.
     *
     * @param input is the type of value that will be converted.
     * @param output is the type to convert the input value to.
     * @return the bytecode that performs the actual conversion,
     * or null, if no such conversion is possible.
     */
    public InsnList assign(final IType input,
                           final IType output)
    {
        // Primitive Type Coercions:
        //   char  ==> int
        //   char  ==> long
        //   byte  ==> short
        //   byte  ==> int
        //   byte  ==> long
        //   short ==> int
        //   short ==> long
        //   int   ==> long
        //   float ==> double

        /**
         * Case: Primitive-To-Primitive Coercions
         */
        if (input.equals(PRIMITIVE_CHAR) && output.equals(PRIMITIVE_INT))
        {
            return new InsnList();
        }
        else if (input.equals(PRIMITIVE_CHAR) && output.equals(PRIMITIVE_LONG))
        {
            final InsnList result = new InsnList();
            result.add(new InsnNode(Opcodes.I2L));
            return result;
        }
        else if (input.equals(PRIMITIVE_BYTE) && output.equals(PRIMITIVE_SHORT))
        {
            return new InsnList();
        }
        else if (input.equals(PRIMITIVE_BYTE) && output.equals(PRIMITIVE_INT))
        {
            return new InsnList();
        }
        else if (input.equals(PRIMITIVE_BYTE) && output.equals(PRIMITIVE_LONG))
        {
            final InsnList result = new InsnList();
            result.add(new InsnNode(Opcodes.I2L));
            return result;
        }
        else if (input.equals(PRIMITIVE_SHORT) && output.equals(PRIMITIVE_INT))
        {
            return new InsnList();
        }
        else if (input.equals(PRIMITIVE_SHORT) && output.equals(PRIMITIVE_LONG))
        {
            final InsnList result = new InsnList();
            result.add(new InsnNode(Opcodes.I2L));
            return result;
        }
        else if (input.equals(PRIMITIVE_INT) && output.equals(PRIMITIVE_LONG))
        {
            final InsnList result = new InsnList();
            result.add(new InsnNode(Opcodes.I2L));
            return result;
        }
        else if (input.equals(PRIMITIVE_FLOAT) && output.equals(PRIMITIVE_DOUBLE))
        {
            final InsnList result = new InsnList();
            result.add(new InsnNode(Opcodes.F2D));
            return result;
        }

        /**
         * Case: The input is a subtype of the output.
         */
        if (input.isSubtypeOf(output))
        {
            return new InsnList();
        }

        /**
         * Case: Boxing
         */
        final InsnList box_code = box(input, output);

        if (box_code != null)
        {
            return box_code;
        }

        /**
         * Case: Unboxing
         */
        final InsnList unbox_code = unbox(input, output);

        if (unbox_code != null)
        {
            return unbox_code;
        }

        /**
         * Case: Invalid Assignment
         */
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
    public InsnList box(final IType input,
                        final IType output)
    {
        final InsnList result = new InsnList();

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

        return result.size() == 0 ? null : result;
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
    public InsnList unbox(final IType input,
                          final IType output)
    {
        final InsnList result = new InsnList();

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

        return result.size() == 0 ? null : result;
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
        // Here "less" is synonymous with "more-specific".
        final boolean LESS = true;

        /**
         * Name Comparison
         */
        if (left.getName().compareTo(right.getName()) < 0)
        {
            return LESS;
        }
        else if (left.getName().equals(right.getName()) == false)
        {
            return !LESS;
        }

        assert left.getName().equals(right.getName());

        final List<IFormalParameter> left_params = left.getParameters();
        final List<IFormalParameter> right_params = right.getParameters();

        /**
         * Parameter Count Comparison
         */
        if (left_params.size() < right_params.size())
        {
            return LESS;
        }
        else if (left_params.size() > right_params.size())
        {
            return !LESS;
        }

        assert left_params.size() == right_params.size();

        /**
         * Owner Comparison
         */
        final boolean is_proper_subtype = (!left.getOwner().equals(right.getOwner()))
                                          && left.getOwner().isSubtypeOf(right.getOwner());

        if (is_proper_subtype)
        {
            return LESS;
        }

        /**
         * Parameter Comparison
         */
        for (int i = 0; i < left_params.size(); i++)
        {
            final IVariableType left_param = left_params.get(i).getType();
            final IVariableType right_param = right_params.get(i).getType();

            final boolean assignable = assign(left_param, right_param) != null;

            if (left_param.equals(right_param))
            {
                // Pass, because a type is not more specific than itself.
            }
            else if (left_param.isSubtypeOf(right_param))
            {
                // A subtype is more specific than its supertype.
                return LESS;
            }
            else if (left_param.isPrimitiveType() && !right_param.isPrimitiveType())
            {
                // A primitive-type is more specific than a reference-type.
                return LESS;
            }
            else if (assignable && left_param.isPrimitiveType() && right_param.isPrimitiveType())
            {
                // Some primitive-types are more specific than other primitive-types.
                return LESS;
            }
        }

        /**
         * Return Type Comparison
         */
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
                                                          final List<? extends IType> arguments)
    {
        final List<T> result = Lists.newLinkedList();

NEXT_METHOD:
        for (IInvokableMember method : members)
        {
            if (method.getParameters().size() != arguments.size())
            {
                continue;
            }

            for (int i = 0; i < arguments.size(); i++)
            {
                final IType parameter = method.getParameters().get(i).getType();

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

        // Case: A member with public-access, of an accessible type, is always accessible.

        if (Modifier.isPublic(member.getModifiers()))
        {
            return true;
        }

        // Case: A member with private-access, of an accessible type, is never accessible.
        //       Remember, Autumn does not support access modifiers is Autumn code.

        if (Modifier.isPrivate(member.getModifiers()))
        {
            return false;
        }

        // Case: A member with package-access, of an accessible type,
        //       is only accessible from within the same package.

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
                                           final List<? extends IType> arguments)
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
                                        final List<? extends IType> arguments)
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
                                              final List<? extends IType> arguments)
    {
        Preconditions.checkNotNull(user);
        Preconditions.checkNotNull(owner);
        Preconditions.checkNotNull(name);
        Preconditions.checkNotNull(arguments);

        final List<IMethod> selected = select(user,
                                              owner.getMethods(),
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

    /**
     * This method searches through a list for a specific method or constructor.
     *
     * @param list is a list of methods and/or constructors.
     * @param name is the name of the element to find.
     * @return the found element; or null, if no such element exists.
     */
    public static <T extends IInvokableMember> T find(final Iterable<? extends T> list,
                                                      final String name)
    {
        Preconditions.checkNotNull(list);
        Preconditions.checkNotNull(name);

        Preconditions.checkNotNull(list);
        Preconditions.checkNotNull(name);

        for (T element : list)
        {
            if (element.getName().equals(name))
            {
                return element;
            }
        }

        return null;
    }

    /**
     * This method generates the bytecode necessary to box a value.
     *
     * <p>
     * This method effectively does nothing, if no boxing is required.
     * </p>
     *
     * @param type is the type of the topmost value on the operand-stack.
     */
    public void autoboxToObject(final InsnList code,
                                final IType type)
    {
        Preconditions.checkNotNull(type);

        // Generate the code to box the value.
        // If no boxing is required, then this variable will be assigned null.
        InsnList boxing = box(type, OBJECT);

        boxing = boxing == null ? new InsnList() : boxing;

        code.add(boxing);
    }

    /**
     * This method creates a list containing the internal-names of a set of reference-types.
     *
     * @param types are the reference-types whose internal-names will be placed in a new list.
     * @return the list containing the internal-names of the given types.
     */
    public List<String> internalNamesOf(final Iterable<? extends IReferenceType> types)
    {
        final List<String> result = Lists.newLinkedList();

        for (IReferenceType x : types)
        {
            result.add(Utils.internalName(x));
        }

        return result;
    }

    /**
     * This method creates the type-system representation of a formal-parameter.
     *
     * @param type is the type part of the parameter.
     * @return the new formal parameter.
     */
    public IFormalParameter formal(final IVariableType type)
    {
        final CustomFormalParameter param = new CustomFormalParameter();
        param.setAnnotations(new LinkedList());
        param.setType(type);

        return param;
    }

    /**
     * This method creates a list of the overloads of a specific function.
     *
     * <p>
     * The returned list is not sorted.
     * </p>
     *
     * @param owner is the type of the module that contains the overloads.
     * @param name is the name of the function.
     * @return a list of the overloads of the named function.
     * If there are no overloads, the list will be empty.
     */
    public static List<IMethod> findFunctions(final IDeclaredType owner,
                                              final String name)
    {
        Preconditions.checkNotNull(owner);
        Preconditions.checkNotNull(name);
        Preconditions.checkArgument(owner.isSubtypeOf(owner.getTypeFactory().fromClass(Module.class)));

        final List<IMethod> result = Lists.newLinkedList();

        for (IMethod function : owner.getMethods())
        {
            if (function.getName().equals(name))
            {
                assert Modifier.isPublic(function.getModifiers());
                assert Modifier.isStatic(function.getModifiers());

                result.add(function);
            }
        }

        return result;
    }

    /**
     * This method determines whether this class or any of its parents inherit from themselves.
     *
     * @param base is the most specific type.
     * @return true, iff circular inheritance is present.
     */
    public static boolean detectCircularInheritance(final IDeclaredType base)
    {
        final Set<IType> set = Sets.newHashSet();

        set.add(base);

        /**
         * Detect circular inheritance due to a superclass.
         */
        IClassType p = base.getSuperclass();

        while ("Ljava/lang/Object;".equals(p.getDescriptor()) == false)
        {
            if (set.contains(p))
            {
                return true;
            }
            else
            {
                set.add(p);
                p = p.getSuperclass();
            }
        }

        /**
         * Detect circular inheritance due to superinterfaces.
         */
        if (base.getAllSuperinterfaces().contains(base))
        {
            return true;
        }

        return false;
    }

    /**
     * This method generates bytecode that assigns a reference-type to another, possibly primitive, type.
     *
     * <p>
     * If the target is a primitive-type, the generated code is a cast to the related boxed-type
     * and then an unboxing operation.
     * </p>
     *
     * @param code is the code being generated.
     * @param target is the type of the assignee.
     * @param value is the type of the value being assigned to the assignee.
     */
    public void forceAssign(final InsnList code,
                            final IExpressionType target,
                            final IReferenceType value)
    {
        if (target.equals(PRIMITIVE_BOOLEAN))
        {
            code.add(Utils.conditionalCast(value, BOXED_BOOLEAN));
            code.add(unbox(BOXED_BOOLEAN, target));
        }
        else if (target.equals(PRIMITIVE_CHAR))
        {
            code.add(Utils.conditionalCast(value, BOXED_CHAR));
            code.add(unbox(BOXED_CHAR, target));
        }
        else if (target.equals(PRIMITIVE_BYTE))
        {
            code.add(Utils.conditionalCast(value, BOXED_BYTE));
            code.add(unbox(BOXED_BYTE, target));
        }
        else if (target.equals(PRIMITIVE_SHORT))
        {
            code.add(Utils.conditionalCast(value, BOXED_SHORT));
            code.add(unbox(BOXED_SHORT, target));
        }
        else if (target.equals(PRIMITIVE_INT))
        {
            code.add(Utils.conditionalCast(value, BOXED_INT));
            code.add(unbox(BOXED_INT, target));
        }
        else if (target.equals(PRIMITIVE_LONG))
        {
            code.add(Utils.conditionalCast(value, BOXED_LONG));
            code.add(unbox(BOXED_LONG, target));
        }
        else if (target.equals(PRIMITIVE_FLOAT))
        {
            code.add(Utils.conditionalCast(value, BOXED_FLOAT));
            code.add(unbox(BOXED_FLOAT, target));
        }
        else if (target.equals(PRIMITIVE_DOUBLE))
        {
            code.add(Utils.conditionalCast(value, BOXED_DOUBLE));
            code.add(unbox(BOXED_DOUBLE, target));
        }
        else
        {
            assert target.isReferenceType();

            code.add(Utils.conditionalCast(value, target));
        }
    }
}
