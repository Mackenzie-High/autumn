/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autumn.lang.internals;

import java.util.ArrayList;

/**
 *
 * @author mackenzie
 */
public final class ArgumentStack
{
    private static final class Argument
    {
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

        public void reset()
        {
            this.value_Z = false;
            this.value_C = 0;
            this.value_B = 0;
            this.value_S = 0;
            this.value_I = 0;
            this.value_J = 0;
            this.value_F = 0;
            this.value_D = 0;
            this.value_O = null;
            this.type = null;
        }
    }

    private static final ThreadLocal<ArgumentStack> thread_stack = new ThreadLocal<ArgumentStack>();

    private final ArrayList<Argument> stack = new ArrayList<Argument>(64);

    private int size = 0;

    public ArgumentStack()
    {
        for (int i = 0; i < 64; i++)
        {
            stack.add(new Argument());
        }
    }

    public static ArgumentStack getThreadStack()
    {
        final ArgumentStack stk = thread_stack.get();

        if (stk != null)
        {
            return stk;
        }
        else
        {
            final ArgumentStack result = new ArgumentStack();

            thread_stack.set(result);

            return result;
        }
    }

    public void clear()
    {
        while (size > 0)
        {
            stack.get(size).reset();
        }
    }

    public int size()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    private void checkSize()
    {
        if (size == stack.size())
        {
            stack.add(new Argument());
        }
    }

    public void push(final boolean value)
    {
        checkSize();

        final Argument x = stack.get(size++);

        x.type = boolean.class;

        x.value_Z = value;
    }

    public void push(final char value)
    {
        checkSize();

        final Argument x = stack.get(size++);

        x.type = char.class;

        x.value_C = value;
    }

    public void push(final byte value)
    {
        checkSize();

        final Argument x = stack.get(size++);

        x.type = byte.class;

        x.value_B = value;
    }

    public void push(final short value)
    {
        checkSize();

        final Argument x = stack.get(size++);

        x.type = short.class;

        x.value_S = value;
    }

    public void push(final int value)
    {
        checkSize();

        final Argument x = stack.get(size++);

        x.type = int.class;

        x.value_I = value;
    }

    public void push(final long value)
    {
        checkSize();

        final Argument x = stack.get(size++);

        x.type = long.class;

        x.value_J = value;
    }

    public void push(final float value)
    {
        checkSize();

        final Argument x = stack.get(size++);

        x.type = float.class;

        x.value_F = value;
    }

    public void push(final double value)
    {
        checkSize();

        final Argument x = stack.get(size++);

        x.type = double.class;

        x.value_D = value;
    }

    public void push(final Object value)
    {
        checkSize();

        final Argument x = stack.get(size++);

        x.type = Object.class;

        x.value_O = value;
    }

    public void pop()
    {
        checkSize();

        --size;
    }

    private RuntimeException wrongType()
    {
        return new RuntimeException();
    }

    public boolean getZ(final int index)
    {
        final Argument x = stack.get(index);

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

    public char getC(final int index)
    {
        final Argument x = stack.get(index);

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

    public byte getB(final int index)
    {
        final Argument x = stack.get(index);

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

    public short getS(final int index)
    {
        final Argument x = stack.get(index);

        if (x.type == short.class)
        {
            return x.value_S;
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

    public int getI(final int index)
    {
        final Argument x = stack.get(index);

        if (x.type == int.class)
        {
            return x.value_I;
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

    public long getJ(final int index)
    {
        final Argument x = stack.get(index);

        if (x.type == long.class)
        {
            return x.value_J;
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

    public float getF(final int index)
    {
        final Argument x = stack.get(index);

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

    public double getD(final int index)
    {
        final Argument x = stack.get(index);

        if (x.type == double.class)
        {
            return x.value_D;
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

    public Object getO(final int index)
    {
        final Argument x = stack.get(index);

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

    public boolean peekZ()
    {
        return getZ(size - 1);
    }

    public char peekC()
    {
        return getC(size - 1);
    }

    public byte peekB()
    {
        return getB(size - 1);
    }

    public short peekS()
    {
        return getS(size - 1);
    }

    public int peekI()
    {
        return getI(size - 1);
    }

    public long peekJ()
    {
        return getJ(size - 1);
    }

    public float peekF()
    {
        return getF(size - 1);
    }

    public double peekD()
    {
        return getD(size - 1);
    }

    public Object peekO()
    {
        return getO(size - 1);
    }

    public Object popO()
    {
        final Object value = peekO();

        pop();

        return value;
    }
}
