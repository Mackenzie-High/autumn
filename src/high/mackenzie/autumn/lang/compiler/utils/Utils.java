package high.mackenzie.autumn.lang.compiler.utils;

import autumn.lang.internals.Helpers;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import high.mackenzie.autumn.lang.compiler.compilers.ModuleCompiler;
import high.mackenzie.autumn.lang.compiler.compilers.ProgramCompiler;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IArrayType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IClassType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IDeclaredType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IExpressionType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IInvokableMember;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IPrimitiveType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IReferenceType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IReturnType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVariableType;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

/**
 * This class contains utility methods needed during code-generation.
 *
 * @author Mackenzie High
 */
public final class Utils
{
    public static final int IF_TRUE = Opcodes.IFNE;

    public static final int IF_FALSE = Opcodes.IFEQ;

    /**
     * These are the keywords in the Java programming language.
     */
    private static final Set<String> KEYWORDS = Sets.newTreeSet();

    static
    {
        KEYWORDS.add("abstract");
        KEYWORDS.add("assert");
        KEYWORDS.add("boolean");
        KEYWORDS.add("break");
        KEYWORDS.add("byte");
        KEYWORDS.add("case");
        KEYWORDS.add("catch");
        KEYWORDS.add("char");
        KEYWORDS.add("class");
        KEYWORDS.add("const");
        KEYWORDS.add("continue");
        KEYWORDS.add("default");
        KEYWORDS.add("do");
        KEYWORDS.add("double");
        KEYWORDS.add("else");
        KEYWORDS.add("enum");
        KEYWORDS.add("extends");
        KEYWORDS.add("final");
        KEYWORDS.add("finally");
        KEYWORDS.add("float");
        KEYWORDS.add("for");
        KEYWORDS.add("goto");
        KEYWORDS.add("if");
        KEYWORDS.add("implements");
        KEYWORDS.add("import");
        KEYWORDS.add("instanceof");
        KEYWORDS.add("int");
        KEYWORDS.add("interface");
        KEYWORDS.add("long");
        KEYWORDS.add("native");
        KEYWORDS.add("new");
        KEYWORDS.add("package");
        KEYWORDS.add("private");
        KEYWORDS.add("protected");
        KEYWORDS.add("public");
        KEYWORDS.add("return");
        KEYWORDS.add("short");
        KEYWORDS.add("static");
        KEYWORDS.add("strictfp");
        KEYWORDS.add("super");
        KEYWORDS.add("switch");
        KEYWORDS.add("synchronized");
        KEYWORDS.add("this");
        KEYWORDS.add("throw");
        KEYWORDS.add("throws");
        KEYWORDS.add("transient");
        KEYWORDS.add("try");
        KEYWORDS.add("void");
        KEYWORDS.add("volatile");
        KEYWORDS.add("while");
        KEYWORDS.add("false");
        KEYWORDS.add("true");
        KEYWORDS.add("null");
    }

    /**
     * This method computes the internal name of a type.
     *
     * <p>
     * Example: "java.lang.String" returns "java/lang/String" <br>
     * Example: "java.lang.String[]" returns "[Ljava/lang/String;" <br>
     * </p>
     *
     * @param type is the type whose internal name is requested.
     * @return the internal name of the type.
     */
    public static String internalName(final IReferenceType type)
    {
        Preconditions.checkNotNull(type);

        assert type.isNullType() == false;

        if (type.isArrayType())
        {
            return type.getDescriptor();
        }
        else
        {
            final String descriptor = type.getDescriptor();

            final String result = descriptor.substring(1).replace(";", "");

            return result;
        }
    }

    /**
     * This method computes the simple name of a type.
     *
     * <p>
     * Example: "java.lang.String" returns "String" <br>
     * Example: "java.lang.String[]" returns "String[]" <br>
     * </p>
     *
     * @param type is the type whose simple name is requested.
     * @return the simple name of the type.
     */
    public static String simpleName(final IExpressionType type)
    {
        Preconditions.checkNotNull(type);

        assert type.isNullType() == false;

        final String source = sourceName(type);

        final String[] parts = source.split("\\.");

        final String name = parts[parts.length - 1];

        return name;
    }

    /**
     * This method computes the name of a type as it appears in the source-code.
     *
     * @param type is the type whose name is requested.
     * @return the name of the type as it appears in source-code.
     */
    public static String sourceName(final IExpressionType type)
    {
        Preconditions.checkNotNull(type);

        if (type.isNullType())
        {
            return "null";
        }
        else if (type.isVoidType())
        {
            return "void";
        }
        else if (type.isPrimitiveType())
        {
            return ((IPrimitiveType) type).toClass().getName();
        }
        else if (type.isReferenceType() && ((IReferenceType) type).isArrayType())
        {
            final IArrayType array = (IArrayType) type;

            final String element = sourceName(array.getElement());

            final String dimensions = Strings.repeat("[]", array.getDimensions());

            return element + dimensions;
        }
        else
        {
            final String result = ((IDeclaredType) type).getDescriptor()
                    .substring(1)
                    .replace(";", "")
                    .replace("/", ".");

            return result;
        }
    }

    /**
     * This method selects the appropriate "return" bytecode instruction for a given type.
     *
     * <p>
     * This method will return a RETURN instruction, if the type is void.
     * </p>
     *
     * @param type is the type of value that will be returned.
     * @return the bytecode "return" instruction that is used for the given type.
     */
    public static AbstractInsnNode selectReturnInsn(final IReturnType type)
    {
        if (type.isVoidType())
        {
            return new InsnNode(Opcodes.RETURN);
        }
        else if (type.isReferenceType())
        {
            return new InsnNode(Opcodes.ARETURN);
        }
        else if (type.getDescriptor().equals("J"))
        {
            return new InsnNode(Opcodes.LRETURN);
        }
        else if (type.getDescriptor().equals("F"))
        {
            return new InsnNode(Opcodes.FRETURN);
        }
        else if (type.getDescriptor().equals("D"))
        {
            return new InsnNode(Opcodes.DRETURN);
        }
        else // booleans, chars, bytes, shorts, and ints
        {
            return new InsnNode(Opcodes.IRETURN);
        }
    }

    /**
     * This method selects the appropriate bytecode instruction to load a variable.
     *
     * @param type is the type of the variable.
     * @param address is the address of the variable.
     * @return the appropriate load instruction.
     */
    public static AbstractInsnNode selectLoadVarInsn(final IVariableType type,
                                                     final int address)
    {
        if (type.getDescriptor().equals("Z"))
        {
            return new VarInsnNode(Opcodes.ILOAD, address);
        }
        else if (type.getDescriptor().equals("C"))
        {
            return new VarInsnNode(Opcodes.ILOAD, address);
        }
        else if (type.getDescriptor().equals("B"))
        {
            return new VarInsnNode(Opcodes.ILOAD, address);
        }
        else if (type.getDescriptor().equals("S"))
        {
            return new VarInsnNode(Opcodes.ILOAD, address);
        }
        else if (type.getDescriptor().equals("I"))
        {
            return new VarInsnNode(Opcodes.ILOAD, address);
        }
        else if (type.getDescriptor().equals("J"))
        {
            return new VarInsnNode(Opcodes.LLOAD, address);
        }
        else if (type.getDescriptor().equals("F"))
        {
            return new VarInsnNode(Opcodes.FLOAD, address);
        }
        else if (type.getDescriptor().equals("D"))
        {
            return new VarInsnNode(Opcodes.DLOAD, address);
        }
        else
        {
            return new VarInsnNode(Opcodes.ALOAD, address);
        }
    }

    /**
     * This method selects the appropriate bytecode instruction to store a value in a variable.
     *
     * @param type is the type of the variable.
     * @param address is the address of the variable.
     * @return the appropriate store instruction.
     */
    public static AbstractInsnNode selectStoreVarInsn(final IVariableType type,
                                                      final int address)
    {
        if (type.getDescriptor().equals("Z"))
        {
            return new VarInsnNode(Opcodes.ISTORE, address);
        }
        else if (type.getDescriptor().equals("C"))
        {
            return new VarInsnNode(Opcodes.ISTORE, address);
        }
        else if (type.getDescriptor().equals("B"))
        {
            return new VarInsnNode(Opcodes.ISTORE, address);
        }
        else if (type.getDescriptor().equals("S"))
        {
            return new VarInsnNode(Opcodes.ISTORE, address);
        }
        else if (type.getDescriptor().equals("I"))
        {
            return new VarInsnNode(Opcodes.ISTORE, address);
        }
        else if (type.getDescriptor().equals("J"))
        {
            return new VarInsnNode(Opcodes.LSTORE, address);
        }
        else if (type.getDescriptor().equals("F"))
        {
            return new VarInsnNode(Opcodes.FSTORE, address);
        }
        else if (type.getDescriptor().equals("D"))
        {
            return new VarInsnNode(Opcodes.DSTORE, address);
        }
        else // (type.isReferenceType())
        {
            return new VarInsnNode(Opcodes.ASTORE, address);
        }

    }

    /**
     * This method creates a bytecode instruction that loads a class-literal onto the operand-stack.
     *
     * @param type is the type that the class-literal represents.
     * @return the aforedescribed bytecode instruction.
     */
    public static AbstractInsnNode ldcClass(final IType type)
    {
        final String descriptor = type.getDescriptor();

        final Type klass = Type.getType(descriptor);

        final Map<String, String> predefined = Maps.newTreeMap();
        predefined.put("Z", Type.getInternalName(Boolean.class));
        predefined.put("C", Type.getInternalName(Character.class));
        predefined.put("B", Type.getInternalName(Byte.class));
        predefined.put("S", Type.getInternalName(Short.class));
        predefined.put("I", Type.getInternalName(Integer.class));
        predefined.put("J", Type.getInternalName(Long.class));
        predefined.put("F", Type.getInternalName(Float.class));
        predefined.put("D", Type.getInternalName(Double.class));
        predefined.put("V", Type.getInternalName(Void.class));


        if (predefined.containsKey(klass.getDescriptor()))
        {
            return new FieldInsnNode(Opcodes.GETSTATIC,
                                     predefined.get(klass.getDescriptor()),
                                     "TYPE",
                                     Type.getDescriptor(Class.class));
        }
        else
        {
            return new LdcInsnNode(klass);
        }
    }

    /**
     * This method determines the amount of storage needed for a variable.
     *
     * @param type is the type whose size will be determined.
     * @return the amount of space needed to store a value of the given type.
     */
    public static int sizeof(final IType type)
    {
        if (type.getDescriptor().equals("Z"))
        {
            return 1;
        }
        else if (type.getDescriptor().equals("C"))
        {
            return 1;
        }
        else if (type.getDescriptor().equals("B"))
        {
            return 1;
        }
        else if (type.getDescriptor().equals("S"))
        {
            return 1;
        }
        else if (type.getDescriptor().equals("I"))
        {
            return 1;
        }
        else if (type.getDescriptor().equals("J"))
        {
            return 2;
        }
        else if (type.getDescriptor().equals("F"))
        {
            return 1;
        }
        else if (type.getDescriptor().equals("D"))
        {
            return 2;
        }
        else if (type.getDescriptor().equals("V"))
        {
            return 0;
        }
        else // (type.isReferenceType())
        {
            return 1;
        }

    }

    /**
     * This method selects a bytecode instruction to pop a value off of the operand-stack.
     *
     * <p>
     * This method returns a NOP instruction, if the type is the void type.
     * </p>
     *
     * @param type is the type of value that will be popped off of the stack.
     * @return the aforedescribed bytecode instruction.
     */
    public static AbstractInsnNode selectPop(final IType type)
    {
        if ("V".equals(type.getDescriptor())) // void
        {
            return new InsnNode(Opcodes.NOP); // There is no value to pop, so return nop.
        }
        else if ("J".equals(type.getDescriptor())) // long
        {
            return new InsnNode(Opcodes.POP2);
        }
        else if ("D".equals(type.getDescriptor())) // double
        {
            return new InsnNode(Opcodes.POP2);
        }
        else // boolean, char, byte, short, int, and object references
        {
            return new InsnNode(Opcodes.POP);
        }
    }

    /**
     * This method retrieves the set of words that are reserved in the Java programming language.
     *
     * @return the immutable set of reserved words.
     */
    public static Set<String> keywords()
    {
        return Collections.unmodifiableSet(KEYWORDS);
    }

    /**
     * This method determines whether a word is a keyword in the Java programming language.
     *
     * @param word is the word that may be a keyword.
     * @return true, iff the word is a reserved keyword.
     */
    public static boolean isKeyword(final String word)
    {
        return keywords().contains(word);
    }

    /**
     * This method selects a bytecode instruction to push a default value onto the operand-stack.
     *
     * @param type is the type of the default value.
     * @return the aforedescribed instruction.
     */
    public static AbstractInsnNode ldcDefault(final IType type)
    {
        if ("Z".equals(type.getDescriptor()))
        {
            return new LdcInsnNode(false);
        }
        else if ("C".equals(type.getDescriptor()))
        {
            return new LdcInsnNode((char) 0);
        }
        else if ("B".equals(type.getDescriptor()))
        {
            return new LdcInsnNode((byte) 0);
        }
        else if ("S".equals(type.getDescriptor()))
        {
            return new LdcInsnNode((short) 0);
        }
        else if ("I".equals(type.getDescriptor()))
        {
            return new LdcInsnNode((int) 0);
        }
        else if ("J".equals(type.getDescriptor()))
        {
            return new LdcInsnNode((long) 0);
        }
        else if ("F".equals(type.getDescriptor()))
        {
            return new LdcInsnNode((float) 0);
        }
        else if ("D".equals(type.getDescriptor()))
        {
            return new LdcInsnNode((double) 0);
        }
        else // object references
        {
            return new InsnNode(Opcodes.ACONST_NULL);
        }
    }

    /**
     * This method generates bytecode to invoke the "push" method of an ArgumentStack.
     *
     * @param program is the program being compiled.
     * @param code is the list of generated bytecode to add instructions to.
     * @param type is the type of the value being pushed.
     */
    public static void pushArgument(final ProgramCompiler program,
                                    final InsnList code,
                                    final IExpressionType type)
    {
        Preconditions.checkNotNull(type);

        // Assume: The ArgumentStack is already on the operand-stack.
        // Assume: The argument is already on the operand-stack.

        final String OBJECT = "Ljava/lang/Object;";

        final String owner = "autumn/lang/internals/ArgumentStack";
        final String name = "push";
        final String desc = "(" + (type.isPrimitiveType() ? type.getDescriptor() : OBJECT) + ")V";

        // Invoke the push method in order to push the argument onto the argument-stack.
        code.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, owner, name, desc));
    }

    /**
     * This method generates bytecode to retrieve a value from an ArgumentStack.
     *
     * <p>
     * If the value is a reference type, then it will be downcast.
     * </p>
     *
     * @param program is the program being compiled.
     * @param code is the list of generated bytecode to add instructions to.
     * @param type is the type of the value that will be retrieved.
     */
    public static void peekArgument(final ProgramCompiler program,
                                    final InsnList code,
                                    final IExpressionType type)
    {
        // Assume: The ArgumentStack is already on the operand-stack.

        final String OBJECT = "Ljava/lang/Object;";

        // Invoke the peek method in order to retrive the value.
        final String owner = Utils.internalName(program.typesystem.utils.ARGUMENT_STACK);
        final String name = "peek" + (type.isPrimitiveType() ? type.getDescriptor() : "O");
        final String desc = "()" + (type.isPrimitiveType() ? type.getDescriptor() : OBJECT);
        code.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, owner, name, desc));

        // If the type is a reference-type, then downcast the value.
        if (type.isReferenceType())
        {
            final String cast = Utils.internalName((IDeclaredType) type);
            code.add(new TypeInsnNode(Opcodes.CHECKCAST, cast));
        }
    }

    /**
     * This method generates bytecode to pop a value off of an ArgumentStack.
     *
     * @param program is the program being compiled.
     * @param code is the list of generated bytecode to add instructions to.
     */
    public static void popArgument(final ProgramCompiler program,
                                   final InsnList code)
    {
        // Invoke the peek method in order to retrive the value.
        final String owner = Utils.internalName(program.typesystem.utils.ARGUMENT_STACK);
        final String name = "pop";
        final String desc = "()V";
        code.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, owner, name, desc));
    }

    /**
     * This method generates bytecode to retrieve a value from an ArgumentStack at a given index.
     *
     * <p>
     * The index is numbered from the base of the stack.
     * So, index zero is the bottommost element.
     * </p>
     *
     * <p>
     * If the value is a reference type, then it will be downcast.
     * </p>
     *
     * @param program is the program being compiled.
     * @param code is the list of generated bytecode to add instructions to.
     * @param type is the type of the value that will be retrieved.
     * @param index is the index of the argument to retrieve.
     */
    public static void getArgument(final ProgramCompiler program,
                                   final InsnList code,
                                   final IExpressionType type,
                                   final int index)
    {
        // Assume: The ArgumentStack is already on the operand-stack.

        final String OBJECT = "Ljava/lang/Object;";

        // Load the index onto the operand-stack.
        code.add(new LdcInsnNode(index));

        // Invoke the get method in order to retrive the value.
        final String owner = Utils.internalName(program.typesystem.utils.ARGUMENT_STACK);
        final String name = "get" + (type.isPrimitiveType() ? type.getDescriptor() : "O");
        final String desc = "(I)" + (type.isPrimitiveType() ? type.getDescriptor() : OBJECT);
        code.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, owner, name, desc));

        // If the type is a reference-type, then downcast the value.
        if (type.isReferenceType())
        {
            final String cast = Utils.internalName((IReferenceType) type);
            code.add(new TypeInsnNode(Opcodes.CHECKCAST, cast));
        }
    }

    /**
     * This method appends a list of instructions onto another list of instructions.
     *
     * @param out is the list that will have elements added to it.
     * @param addendum are the elements to add to the other list.
     */
    public static void appendToInsnList(final InsnList out,
                                        final Iterable<AbstractInsnNode> addendum)
    {
        for (AbstractInsnNode node : addendum)
        {
            out.add(node);
        }
    }

    /**
     * This method generates the bytecode needed to load a delegate onto the operand-stack.
     *
     * @param code is the list of bytecode instructions to append the generated code onto.
     * @param module is the type of the module that contains the delegated function.
     * @param method is the name of the delegated function.
     */
    public static void loadDelegate(final InsnList code,
                                    final IClassType module,
                                    final String method)
    {
        String owner;
        String name;
        String desc;

        /**
         * Load the an instance of the module onto the operand-stack.
         */
        owner = Utils.internalName(module);
        name = "instance";
        desc = "()Lautumn/lang/Module;";
        code.add(new MethodInsnNode(Opcodes.INVOKESTATIC, owner, name, desc));

        /**
         * Load the name of the delegated method onto the operand-stack.
         */
        code.add(new LdcInsnNode(method));

        /**
         * Invoke the helper utility method that resolves the delegate.
         */
        owner = "Lautumn/lang/internals/Helpers;";
        name = "delegate";
        desc = "(Lautumn/lang/Module;Ljava/lang/String;)Lautumn/lang/Delegate;";
        code.add(new MethodInsnNode(Opcodes.INVOKESTATIC, owner, name, desc));
    }

    /**
     * This method creates the bytecode representation of a constructor or method.
     *
     * @param module is essentially the module that is being compiled.
     * @param member is the type-system representation of the constructor or method.
     * @return the bytecode representation of the
     */
    public static MethodNode bytecodeOf(final ModuleCompiler module,
                                        final IInvokableMember member)
    {
        Preconditions.checkNotNull(module);
        Preconditions.checkNotNull(member);

        final MethodNode node = new MethodNode();

        node.visibleAnnotations = module.anno_utils.compileAnnotationList(member.getAnnotations());
        node.access = member.getModifiers();
        node.name = member.getName();
        node.desc = member.getDescriptor();
        node.exceptions = Lists.newLinkedList();

        for (IClassType exception : member.getThrowsClause())
        {
            node.exceptions.add(Utils.internalName(exception));
        }

        return node;
    }

    /**
     * This method creates a bytecode instruction that invokes a method.
     *
     * @param code is the list of bytecode instruction to append the instruction onto.
     * @param opcode is the opcode of the new instruction.
     * @param owner is the type where the method is declared.
     * @param returns is the return type of the method.
     * @param name is the name of the method.
     * @param parameters are the types of the method's parameters.
     */
    public static void invoke(final InsnList code,
                              final int opcode,
                              final Class owner,
                              final Class returns,
                              final String name,
                              final Class... parameters)
    {
        final StringBuilder descriptor = new StringBuilder();

        descriptor.append("(");
        {
            for (Class param : parameters)
            {
                descriptor.append(Type.getDescriptor(param));
            }
        }
        descriptor.append(")");

        descriptor.append(Type.getDescriptor(returns));

        final Type type = Type.getType(owner);

        code.add(new MethodInsnNode(opcode, type.getInternalName(), name, descriptor.toString()));
    }

    /**
     * This method generates bytecode that makes a list immutable.
     *
     * @param code is the list of bytecode instruction to append the instruction onto.
     */
    public static void makeListImmutable(final InsnList code)
    {
        Utils.invoke(code,
                     Opcodes.INVOKESTATIC,
                     Helpers.class,
                     List.class,
                     "newImmutableList",
                     Iterable.class);
    }

    /**
     * This method generates the bytecode necessary to load the current thread's
     * argument-stack onto the operand-stack.
     *
     * @param code is the code being generated.
     */
    public static void loadArgumentStack(final InsnList code)
    {
        code.add(new MethodInsnNode(Opcodes.INVOKESTATIC,
                                    "autumn/lang/internals/ArgumentStack",
                                    "getThreadStack",
                                    "()Lautumn/lang/internals/ArgumentStack;"));
    }
}
