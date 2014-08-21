package autumn.lang.internals;

import high.mackenzie.autumn.resources.Finished;
import java.util.Arrays;

/**
 * Instances of this class are used to send arguments into functors and send result back therefrom.
 *
 * <p>
 * <b>Warning:</b> The API of this class shall not be changed, because the methods
 * herein are invoked directly from bytecode. As a result, any revision could break
 * backwards compatibility. That being said, the internal implementation of this class
 * may be enhanced in the future in order to further optimize it.
 * </p>
 *
 * <p>
 * The code in this class is among the most frequently accessed code in a running Autumn program.
 * </p>
 *
 * <p>
 * There will be one instance of this class for every thread in an Autumn program.
 * The instance is usually referred to simply as the "argument-stack".
 * The argument-stack should not be confused with the bytecode level operand-stack.
 * The two stacks perform similar purposes; however, they are distinct entities.
 * </p>
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public final class ArgumentStack
{
    /**
     * Instances of this class are used to store arguments on the argument-stack.
     */
    private static final class Argument
    {
        /**
         * This is the type of value that is currently stored herein.
         */
        public Class type;

        public boolean value_Z;

        public char value_C;

        public byte value_B;

        public short value_S;

        public int value_I;

        public long value_J;

        public float value_F;

        public double value_D;

        public Object value_O;
    }

    /**
     * This thread-local variable stores the argument-stack associated with this thread.
     */
    private static final ThreadLocal<ArgumentStack> thread_stack = new ThreadLocal<ArgumentStack>();

    /**
     * This array stores the arguments that are currently on the argument-stack.
     * Note: The size of this array is not the same thing as the size of the argument-stack itself.
     */
    private Argument[] stack;

    /**
     * This is the size of the argument-stack itself.
     * In other words, this is the number of arguments that are currently on the argument-stack.
     */
    private int size = 0;

    /**
     * Sole Constructor.
     */
    ArgumentStack()
    {
        /**
         * Initially, the stack will have room for sixty-four elements.
         * In reality, no thread should need a larger stack than this.
         */
        final int INITIAL_SIZE = 64;

        // Allocate the stack itself.
        stack = new Argument[INITIAL_SIZE];

        // Allocate the objects that store the arguemnts.
        for (int i = 0; i < INITIAL_SIZE; i++)
        {
            stack[i] = new Argument();
        }
    }

    /**
     * This method allocate space for at least one more argument.
     */
    private void increaseSize()
    {
        if (stack.length == size)
        {
            stack = Arrays.copyOf(stack, size + 64);
        }

        stack[size] = new Argument();
    }

    /**
     * This method retrieves the argument-stack that is associated with the current thread.
     *
     * <p>
     * The returned argument-stack will be empty.
     * </p>
     *
     * @return the argument-stack of the current thread.
     */
    public static ArgumentStack getThreadStack()
    {
        final ArgumentStack stk = thread_stack.get();

        if (stk != null)
        {
            stk.clear();

            return stk;
        }
        else
        {
            final ArgumentStack result = new ArgumentStack();

            thread_stack.set(result);

            return result;
        }
    }

    /**
     * This method removes all arguments from the argument-stack.
     */
    public final void clear()
    {
        // Remove references to objects.
        // Otherwise, a memory leak could result.
        for (int i = 0; i < size; i++)
        {
            stack[i].value_O = null;
        }

        size = 0;
    }

    /**
     * This method retrieves the size of the argument-stack.
     *
     * @return the number of arguments currently on the argument-stack.
     */
    public final int size()
    {
        return size;
    }

    /**
     * This method determines whether the argument-stack is empty.
     *
     * @return true, iff the size of this stack is zero.
     */
    public final boolean isEmpty()
    {
        return size == 0;
    }

    /**
     * This method pushes an argument onto the argument-stack.
     *
     * @param value is the argument to push onto the stack.
     */
    public final void push(final boolean value)
    {
        /**
         * Ensure that there is enough space on the argument-stack for one more element.
         */
        if (size == stack.length)
        {
            increaseSize();
        }

        /**
         * Push the value onto the argument-stack.
         */
        final Argument x = stack[size++];
        x.type = boolean.class;
        x.value_Z = value;
    }

    /**
     * This method pushes an argument onto the argument-stack.
     *
     * @param value is the argument to push onto the stack.
     */
    public final void push(final char value)
    {
        /**
         * Ensure that there is enough space on the argument-stack for one more element.
         */
        if (size == stack.length)
        {
            increaseSize();
        }

        /**
         * Push the value onto the argument-stack.
         */
        final Argument x = stack[size++];
        x.type = char.class;
        x.value_C = value;
    }

    /**
     * This method pushes an argument onto the argument-stack.
     *
     * @param value is the argument to push onto the stack.
     */
    public final void push(final byte value)
    {
        /**
         * Ensure that there is enough space on the argument-stack for one more element.
         */
        if (size == stack.length)
        {
            increaseSize();
        }

        /**
         * Push the value onto the argument-stack.
         */
        final Argument x = stack[size++];
        x.type = byte.class;
        x.value_B = value;
    }

    /**
     * This method pushes an argument onto the argument-stack.
     *
     * @param value is the argument to push onto the stack.
     */
    public final void push(final short value)
    {
        /**
         * Ensure that there is enough space on the argument-stack for one more element.
         */
        if (size == stack.length)
        {
            increaseSize();
        }

        /**
         * Push the value onto the argument-stack.
         */
        final Argument x = stack[size++];
        x.type = short.class;
        x.value_S = value;
    }

    /**
     * This method pushes an argument onto the argument-stack.
     *
     * @param value is the argument to push onto the stack.
     */
    public final void push(final int value)
    {
        /**
         * Ensure that there is enough space on the argument-stack for one more element.
         */
        if (size == stack.length)
        {
            increaseSize();
        }

        /**
         * Push the value onto the argument-stack.
         */
        final Argument x = stack[size++];
        x.type = int.class;
        x.value_I = value;
    }

    /**
     * This method pushes an argument onto the argument-stack.
     *
     * @param value is the argument to push onto the stack.
     */
    public final void push(final long value)
    {
        /**
         * Ensure that there is enough space on the argument-stack for one more element.
         */
        if (size == stack.length)
        {
            increaseSize();
        }

        /**
         * Push the value onto the argument-stack.
         */
        final Argument x = stack[size++];
        x.type = long.class;
        x.value_J = value;
    }

    /**
     * This method pushes an argument onto the argument-stack.
     *
     * @param value is the argument to push onto the stack.
     */
    public final void push(final float value)
    {
        /**
         * Ensure that there is enough space on the argument-stack for one more element.
         */
        if (size == stack.length)
        {
            increaseSize();
        }

        /**
         * Push the value onto the argument-stack.
         */
        final Argument x = stack[size++];
        x.type = float.class;
        x.value_F = value;
    }

    /**
     * This method pushes an argument onto the argument-stack.
     *
     * @param value is the argument to push onto the stack.
     */
    public final void push(final double value)
    {
        /**
         * Ensure that there is enough space on the argument-stack for one more element.
         */
        if (size == stack.length)
        {
            increaseSize();
        }

        /**
         * Push the value onto the argument-stack.
         */
        final Argument x = stack[size++];
        x.type = double.class;
        x.value_D = value;
    }

    /**
     * This method pushes an argument onto the argument-stack.
     *
     * @param value is the argument to push onto the stack.
     */
    public final void push(final Object value)
    {
        /**
         * Ensure that there is enough space on the argument-stack for one more element.
         */
        if (size == stack.length)
        {
            increaseSize();
        }

        /**
         * Push the value onto the argument-stack.
         */
        final Argument x = stack[size++];
        x.type = Object.class;
        x.value_O = value;
    }

    /**
     * This method removes the topmost element from the argument-stack.
     *
     * @throws IllegalStateException if the argument-stack is already empty.
     */
    public final void pop()
    {
        if (size == 0)
        {
            throw new IllegalStateException("The argument-stack is empty.");
        }

        --size;
    }

    /**
     * This method creates a special exception object.
     *
     * @return the new exception.
     */
    private RuntimeException wrongType()
    {
        return new ClassCastException("The argument cannot be returned due to its type.");
    }

    /**
     * This method retrieves an argument that is at a specific location on the argument-stack.
     *
     * <p>
     * The argument will be unboxed, if necessary.
     * </p>
     *
     * @param index is the zero-based index of the argument as measured from the stack's base.
     * @return the argument.
     * @throws IndexOutOfBoundsException if the index is invalid.
     * @throws ClassCastException if unboxing fails.
     * @throws ClassCastException if the value cannot be returned due to its type.
     */
    public final boolean getZ(final int index)
    {
        final Argument x = stack[index];

        if (x.type == boolean.class)
        {
            return x.value_Z;
        }
        else if (x.type == Object.class)
        {
            return (boolean) (Boolean) x.value_O;
        }
        else
        {
            throw wrongType();
        }
    }

    /**
     * This method retrieves an argument that is at a specific location on the argument-stack.
     *
     * <p>
     * The argument will be unboxed, if necessary.
     * </p>
     *
     * @param index is the zero-based index of the argument as measured from the stack's base.
     * @return the argument.
     * @throws IndexOutOfBoundsException if the index is invalid.
     * @throws ClassCastException if unboxing fails.
     * @throws ClassCastException if the value cannot be returned due to its type.
     */
    public final char getC(final int index)
    {
        final Argument x = stack[index];

        if (x.type == char.class)
        {
            return x.value_C;
        }
        else if (x.type == Object.class)
        {
            return (char) (Character) x.value_O;
        }
        else
        {
            throw wrongType();
        }
    }

    /**
     * This method retrieves an argument that is at a specific location on the argument-stack.
     *
     * <p>
     * The argument will be unboxed, if necessary.
     * </p>
     *
     * @param index is the zero-based index of the argument as measured from the stack's base.
     * @return the argument.
     * @throws IndexOutOfBoundsException if the index is invalid.
     * @throws ClassCastException if unboxing fails.
     * @throws ClassCastException if the value cannot be returned due to its type.
     */
    public final byte getB(final int index)
    {
        final Argument x = stack[index];

        if (x.type == byte.class)
        {
            return x.value_B;
        }
        else if (x.type == Object.class)
        {
            return (byte) (Byte) x.value_O;
        }
        else
        {
            throw wrongType();
        }
    }

    /**
     * This method retrieves an argument that is at a specific location on the argument-stack.
     *
     * <p>
     * The argument will be unboxed or coerced, if necessary.
     * </p>
     *
     * @param index is the zero-based index of the argument as measured from the stack's base.
     * @return the argument.
     * @throws IndexOutOfBoundsException if the index is invalid.
     * @throws ClassCastException if unboxing fails.
     * @throws ClassCastException if the value cannot be returned due to its type.
     */
    public final short getS(final int index)
    {
        final Argument x = stack[index];

        if (x.type == short.class)
        {
            return x.value_S;
        }
        else if (x.type == byte.class)
        {
            return x.value_B;
        }
        else if (x.type == Object.class)
        {
            return (short) (Short) x.value_O;
        }
        else
        {
            throw wrongType();
        }
    }

    /**
     * This method retrieves an argument that is at a specific location on the argument-stack.
     *
     * <p>
     * The argument will be unboxed or coerced, if necessary.
     * </p>
     *
     * @param index is the zero-based index of the argument as measured from the stack's base.
     * @return the argument.
     * @throws IndexOutOfBoundsException if the index is invalid.
     * @throws ClassCastException if unboxing fails.
     * @throws ClassCastException if the value cannot be returned due to its type.
     */
    public final int getI(final int index)
    {
        final Argument x = stack[index];

        if (x.type == int.class)
        {
            return x.value_I;
        }
        else if (x.type == char.class)
        {
            return x.value_C;
        }
        else if (x.type == byte.class)
        {
            return x.value_B;
        }
        else if (x.type == short.class)
        {
            return x.value_S;
        }
        else if (x.type == Object.class)
        {
            return (int) (Integer) x.value_O;
        }
        else
        {
            throw wrongType();
        }
    }

    /**
     * This method retrieves an argument that is at a specific location on the argument-stack.
     *
     * <p>
     * The argument will be unboxed or coerced, if necessary.
     * </p>
     *
     * @param index is the zero-based index of the argument as measured from the stack's base.
     * @return the argument.
     * @throws IndexOutOfBoundsException if the index is invalid.
     * @throws ClassCastException if unboxing fails.
     * @throws ClassCastException if the value cannot be returned due to its type.
     */
    public final long getJ(final int index)
    {
        final Argument x = stack[index];

        if (x.type == long.class)
        {
            return x.value_J;
        }
        else if (x.type == char.class)
        {
            return x.value_C;
        }
        else if (x.type == byte.class)
        {
            return x.value_B;
        }
        else if (x.type == short.class)
        {
            return x.value_S;
        }
        else if (x.type == int.class)
        {
            return x.value_I;
        }
        else if (x.type == Object.class)
        {
            return (long) (Long) x.value_O;
        }
        else
        {
            throw wrongType();
        }
    }

    /**
     * This method retrieves an argument that is at a specific location on the argument-stack.
     *
     * <p>
     * The argument will be unboxed, if necessary.
     * </p>
     *
     * @param index is the zero-based index of the argument as measured from the stack's base.
     * @return the argument.
     * @throws IndexOutOfBoundsException if the index is invalid.
     * @throws ClassCastException if unboxing fails.
     * @throws ClassCastException if the value cannot be returned due to its type.
     */
    public final float getF(final int index)
    {
        final Argument x = stack[index];

        if (x.type == float.class)
        {
            return x.value_F;
        }
        else if (x.type == Object.class)
        {
            return (float) (Float) x.value_O;
        }
        else
        {
            throw wrongType();
        }
    }

    /**
     * This method retrieves an argument that is at a specific location on the argument-stack.
     *
     * <p>
     * The argument will be unboxed or coerced, if necessary.
     * </p>
     *
     * @param index is the zero-based index of the argument as measured from the stack's base.
     * @return the argument.
     * @throws IndexOutOfBoundsException if the index is invalid.
     * @throws ClassCastException if unboxing fails.
     * @throws ClassCastException if the value cannot be returned due to its type.
     */
    public final double getD(final int index)
    {
        final Argument x = stack[index];

        if (x.type == double.class)
        {
            return x.value_D;
        }
        else if (x.type == float.class)
        {
            return x.value_F;
        }
        else if (x.type == Object.class)
        {
            return (double) (Double) x.value_O;
        }
        else
        {
            throw wrongType();
        }
    }

    /**
     * This method retrieves an argument that is at a specific location on the argument-stack.
     *
     * <p>
     * The argument will be boxed, if necessary.
     * </p>
     *
     * @param index is the zero-based index of the argument as measured from the stack's base.
     * @return the argument.
     * @throws IndexOutOfBoundsException if the index is invalid.
     * @throws ClassCastException if the value cannot be returned due to its type.
     */
    public final Object getO(final int index)
    {
        final Argument x = stack[index];

        if (x.type == boolean.class)
        {
            return x.value_Z;
        }
        else if (x.type == char.class)
        {
            return x.value_C;
        }
        else if (x.type == byte.class)
        {
            return x.value_B;
        }
        else if (x.type == short.class)
        {
            return x.value_S;
        }
        else if (x.type == int.class)
        {
            return x.value_I;
        }
        else if (x.type == long.class)
        {
            return x.value_J;
        }
        else if (x.type == float.class)
        {
            return x.value_F;
        }
        else if (x.type == double.class)
        {
            return x.value_D;
        }
        else if (x.type == Object.class)
        {
            return x.value_O;
        }
        else
        {
            throw wrongType();
        }
    }

    /**
     * This method retrieves the argument that is on the top of the argument-stack.
     *
     * <p>
     * The argument will be unboxed, if necessary.
     * </p>
     *
     * @return the argument.
     * @throws IndexOutOfBoundsException if the stack is empty.
     * @throws ClassCastException if unboxing fails.
     * @throws ClassCastException if the value cannot be returned due to its type.
     */
    public final boolean peekZ()
    {
        return getZ(size - 1);
    }

    /**
     * This method retrieves the argument that is on the top of the argument-stack.
     *
     * <p>
     * The argument will be unboxed, if necessary.
     * </p>
     *
     * @return the argument.
     * @throws IndexOutOfBoundsException if the stack is empty.
     * @throws ClassCastException if unboxing fails.
     * @throws ClassCastException if the value cannot be returned due to its type.
     */
    public final char peekC()
    {
        return getC(size - 1);
    }

    /**
     * This method retrieves the argument that is on the top of the argument-stack.
     *
     * <p>
     * The argument will be unboxed, if necessary.
     * </p>
     *
     * @return the argument.
     * @throws IndexOutOfBoundsException if the stack is empty.
     * @throws ClassCastException if unboxing fails.
     * @throws ClassCastException if the value cannot be returned due to its type.
     */
    public final byte peekB()
    {
        return getB(size - 1);
    }

    /**
     * This method retrieves the argument that is on the top of the argument-stack.
     *
     * <p>
     * The argument will be unboxed, if necessary.
     * </p>
     *
     * @return the argument.
     * @throws IndexOutOfBoundsException if the stack is empty.
     * @throws ClassCastException if unboxing fails.
     * @throws ClassCastException if the value cannot be returned due to its type.
     */
    public final short peekS()
    {
        return getS(size - 1);
    }

    /**
     * This method retrieves the argument that is on the top of the argument-stack.
     *
     * <p>
     * The argument will be unboxed, if necessary.
     * </p>
     *
     * @return the argument.
     * @throws IndexOutOfBoundsException if the stack is empty.
     * @throws ClassCastException if unboxing fails.
     * @throws ClassCastException if the value cannot be returned due to its type.
     */
    public final int peekI()
    {
        return getI(size - 1);
    }

    /**
     * This method retrieves the argument that is on the top of the argument-stack.
     *
     * <p>
     * The argument will be unboxed, if necessary.
     * </p>
     *
     * @return the argument.
     * @throws IndexOutOfBoundsException if the stack is empty.
     * @throws ClassCastException if unboxing fails.
     * @throws ClassCastException if the value cannot be returned due to its type.
     */
    public final long peekJ()
    {
        return getJ(size - 1);
    }

    /**
     * This method retrieves the argument that is on the top of the argument-stack.
     *
     * <p>
     * The argument will be unboxed, if necessary.
     * </p>
     *
     * @return the argument.
     * @throws IndexOutOfBoundsException if the stack is empty.
     * @throws ClassCastException if unboxing fails.
     * @throws ClassCastException if the value cannot be returned due to its type.
     */
    public final float peekF()
    {
        return getF(size - 1);
    }

    /**
     * This method retrieves the argument that is on the top of the argument-stack.
     *
     * <p>
     * The argument will be unboxed, if necessary.
     * </p>
     *
     * @return the argument.
     * @throws IndexOutOfBoundsException if the stack is empty.
     * @throws ClassCastException if unboxing fails.
     * @throws ClassCastException if the value cannot be returned due to its type.
     */
    public final double peekD()
    {
        return getD(size - 1);
    }

    /**
     * This method retrieves the argument that is on the top of the argument-stack.
     *
     * <p>
     * The argument will be boxed, if necessary.
     * </p>
     *
     * @return the argument.
     * @throws IndexOutOfBoundsException if the stack is empty.
     */
    public final Object peekO()
    {
        return getO(size - 1);
    }

    /**
     * This method retrieves and removes the argument that is on the top of the argument-stack.
     *
     * <p>
     * The argument will be boxed, if necessary.
     * </p>
     *
     * @return the argument.
     * @throws IndexOutOfBoundsException if the stack is empty.
     */
    public final Object popO()
    {
        final Object value = peekO();

        pop();

        return value;
    }

    /**
     * This method retrieves and removes the value that is on top of the argument-stack, if any.
     *
     * <p>
     * Caution: This method will also clear the stack.
     * </p>
     *
     * <p>
     * This method will return null, if the stack is empty.
     * </p>
     *
     * <p>
     * This method was added in order to simply the retrieval of functor return-values.
     * </p>
     *
     * @return the topmost argument, if one exists; otherwise, return null.
     */
    public final Object popResult()
    {
        final Object result = isEmpty() ? null : popO();

        clear();

        return result;
    }
}
