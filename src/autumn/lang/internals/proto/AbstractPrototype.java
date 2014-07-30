package autumn.lang.internals.proto;

import autumn.lang.Member;
import autumn.lang.Prototype;
import autumn.lang.internals.ArgumentStack;
import com.google.common.base.Preconditions;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;

/**
 * This class provides a partial implementation of a prototype based object.
 *
 * <p>
 * <ul>
 * Subclasses of this class need to provide:
 * <li>nullary constructor</li>
 * <li>copy() method</li>
 * <li>property setters</li>
 * <li>property getters</li>
 * <li>methods invokers</li>
 * <li>bridge setters</li>
 * <li>bridge getters</li>
 * <li>bridge methods</li>
 * <ul>
 * </p>
 *
 * @author Mackenzie High
 */
public abstract class AbstractPrototype
        implements Prototype
{
    protected static final class Actions
    {
        /**
         * This method creates a copy of this object with one member replaced.
         *
         * <p>
         * This is a low-level method and thus not intended for use by programmers.
         * </p>
         *
         * @param owner is the prototype that will be copied.
         * @param index is the index of the member to replace.
         * @param member is the member that replaces the old member at the given index.
         * @return a modified copy of this object.
         */
        static AbstractPrototype replaceSlot(final AbstractPrototype owner,
                                             final int index,
                                             final AbstractLowLevelMember member)
        {
            Preconditions.checkNotNull(owner);
            Preconditions.checkNotNull(member);

            /**
             * Create a copy of this object.
             * The copy is simply a new instance with the same low-level member objects.
             * In fact, the array that stores the members is the same array.
             */
            final AbstractPrototype copy = owner.copy();

            /**
             * Since we are modifying one of the elements in the array of members, we must copy it.
             * If we did not copy of the array, then the modified element would be visible
             * elsewhere.
             */
            copy.members = Arrays.copyOf(copy.members, copy.members.length);

            /**
             * Now we can perform the actual replacement.
             */
            copy.members[index] = member;

            return copy;
        }

        /**
         * This method simplifies the invocation of a method in a prototype object.
         *
         * <p>
         * Precondition of stack (from top to bottom):
         * <ul>
         * <li>argument[n]</li>
         * <li>argument[.]</li>
         * <li>argument[2]</li>
         * <li>argument[1]</li>
         * <li>argument[0]</li>
         * <li>owner</li>
         * </ul>
         * </p>
         *
         * <p>
         * Postcondition of stack (from top to bottom), if the method returns a non-void type:
         * <ul>
         * <li>result</li>
         * </ul>
         * </p>
         *
         * <p>
         * If the return-type of the method is the void-type,
         * then the postcondition of the stack is empty.
         * </p>
         *
         * @param owner is the prototype that contains the method to invoke.
         * @param index is the index of the method in the prototype's list of members.
         * @param stack contains the arguments to pass to the method, including the owner object.
         * The stack will also be used to pass the return-value back, if any.
         * @throws Throwable in order to propagate exceptions thrown by the method.
         */
        protected static void invoke(final AbstractPrototype owner,
                                     final int index,
                                     final ArgumentStack stack)
                throws Throwable
        {
            assert owner != null;
            assert stack != null;

            // Get the low-level representation of the method that will be invoked.
            final LowLevelMethod method = (LowLevelMethod) owner.members[index];

            if (method.handler != null)
            {
                // Invoke the method.
                method.handler.apply(stack);
            }
            else
            {
                // TODO: default handler, or exception?
            }

            assert stack.size() == 1 || stack.isEmpty();
        }

        /**
         * This method simplifies the invocation of a property setter in a prototype object.
         *
         * <p>
         * Precondition of stack (from top to bottom):
         * <ul>
         * <li>value</li>
         * <li>owner</li>
         * </ul>
         * </p>
         *
         * <p>
         * Postcondition of stack (from top to bottom): empty
         * </p>
         *
         * @param owner is the prototype that contains the setter to invoke.
         * @param index is the index of the property in the prototype's list of members.
         * @param stack contains the value to pass to the setter, including the owner object.
         * @return a modified copy of the owner.
         */
        private static AbstractPrototype set(final AbstractPrototype owner,
                                             final int index,
                                             final ArgumentStack stack)
                throws Throwable
        {
            assert owner != null;
            assert stack != null;
            assert stack.size() == 2;

            // Get the low-level representation of the property.
            final AbstractLowLevelProperty property = (AbstractLowLevelProperty) owner.members[index];

            // Invoke the setter.
            if (property.setter != null)
            {
                property.setter.apply(stack);
            }

            // Place the value into a modified copy of the property.
            final AbstractLowLevelProperty new_property = property.store(stack);

            // Create a derivative of the owner that reflects the changed property.
            final AbstractPrototype new_owner = Actions.replaceSlot(owner, index, new_property);

            assert stack.isEmpty();

            return new_owner;
        }

        private static void get(final AbstractPrototype owner,
                                final int index,
                                final ArgumentStack stack)
                throws Throwable
        {
            assert owner != null;
            assert stack != null;
            assert stack.size() == 1;

            // Get the low-level representation of the property.
            final AbstractLowLevelProperty property = (AbstractLowLevelProperty) owner.members[index];

            // The property must contain a value to retrieve.
            if (property.has_value == false)
            {
                // TODO: new type of error
                throw new RuntimeException("No Value in Property: " + property.meta.name);
            }

            // The owner must be the first argument to the getter.
            stack.push(owner);

            // Get the value from the field.
            // The value must be the second argument to the getter.
            property.load(stack);

            // If a getter is present, then it must be executed.
            if (property.getter != null)
            {
                // Invoke the getter.
                property.getter.apply(stack);
            }

            assert stack.size() == 1 || stack.size() == 2;

            // At this point, the stack may should be in one of two states.
            // State 1. The stack contains one element, namely the value of the property.
            // State 2. The stack contains the owner and the value of the property.
            //
            // State 1 occurs when there is a getter present.
            // State 2 occurs when no getter is present.
        }

        protected static AbstractPrototype set(final AbstractPrototype owner,
                                               final int index,
                                               final boolean value)
                throws Throwable
        {
            // Get the argument-stack for the current thread.
            final ArgumentStack stack = ArgumentStack.getThreadStack();

            // Invoke the method that will get the value from the property.
            stack.clear();
            stack.push(owner);
            stack.push(value);
            final AbstractPrototype result = Actions.set(owner, index, stack);

            assert stack.isEmpty();

            return result;
        }

        protected static AbstractPrototype set(final AbstractPrototype owner,
                                               final int index,
                                               final char value)
                throws Throwable
        {
            // Get the argument-stack for the current thread.
            final ArgumentStack stack = ArgumentStack.getThreadStack();

            // Invoke the method that will get the value from the property.
            stack.clear();
            stack.push(owner);
            stack.push(value);
            final AbstractPrototype result = Actions.set(owner, index, stack);

            assert stack.isEmpty();

            return result;
        }

        protected static AbstractPrototype set(final AbstractPrototype owner,
                                               final int index,
                                               final byte value)
                throws Throwable
        {
            // Get the argument-stack for the current thread.
            final ArgumentStack stack = ArgumentStack.getThreadStack();

            // Invoke the method that will get the value from the property.
            stack.clear();
            stack.push(owner);
            stack.push(value);
            final AbstractPrototype result = Actions.set(owner, index, stack);

            assert stack.isEmpty();

            return result;
        }

        protected static AbstractPrototype set(final AbstractPrototype owner,
                                               final int index,
                                               final short value)
                throws Throwable
        {
            // Get the argument-stack for the current thread.
            final ArgumentStack stack = ArgumentStack.getThreadStack();

            // Invoke the method that will get the value from the property.
            stack.clear();
            stack.push(owner);
            stack.push(value);
            final AbstractPrototype result = Actions.set(owner, index, stack);

            assert stack.isEmpty();

            return result;
        }

        protected static AbstractPrototype set(final AbstractPrototype owner,
                                               final int index,
                                               final int value)
                throws Throwable
        {
            // Get the argument-stack for the current thread.
            final ArgumentStack stack = ArgumentStack.getThreadStack();

            // Invoke the method that will get the value from the property.
            stack.clear();
            stack.push(owner);
            stack.push(value);
            final AbstractPrototype result = Actions.set(owner, index, stack);

            assert stack.isEmpty();

            return result;
        }

        protected static AbstractPrototype set(final AbstractPrototype owner,
                                               final int index,
                                               final long value)
                throws Throwable
        {
            // Get the argument-stack for the current thread.
            final ArgumentStack stack = ArgumentStack.getThreadStack();

            // Invoke the method that will get the value from the property.
            stack.clear();
            stack.push(owner);
            stack.push(value);
            final AbstractPrototype result = Actions.set(owner, index, stack);

            assert stack.isEmpty();

            return result;
        }

        protected static AbstractPrototype set(final AbstractPrototype owner,
                                               final int index,
                                               final float value)
                throws Throwable
        {
            // Get the argument-stack for the current thread.
            final ArgumentStack stack = ArgumentStack.getThreadStack();

            // Invoke the method that will get the value from the property.
            stack.clear();
            stack.push(owner);
            stack.push(value);
            final AbstractPrototype result = Actions.set(owner, index, stack);

            assert stack.isEmpty();

            return result;
        }

        protected static AbstractPrototype set(final AbstractPrototype owner,
                                               final int index,
                                               final double value)
                throws Throwable
        {
            // Get the argument-stack for the current thread.
            final ArgumentStack stack = ArgumentStack.getThreadStack();

            // Invoke the method that will get the value from the property.
            stack.clear();
            stack.push(owner);
            stack.push(value);
            final AbstractPrototype result = Actions.set(owner, index, stack);

            assert stack.isEmpty();

            return result;
        }

        protected static AbstractPrototype set(final AbstractPrototype owner,
                                               final int index,
                                               final Object value)
                throws Throwable
        {
            // Get the argument-stack for the current thread.
            final ArgumentStack stack = ArgumentStack.getThreadStack();

            // Invoke the method that will get the value from the property.
            stack.clear();
            stack.push(owner);
            stack.push(value);
            final AbstractPrototype result = Actions.set(owner, index, stack);

            assert stack.isEmpty();

            return result;
        }

        protected static boolean getZ(final AbstractPrototype owner,
                                      final int index)
                throws Throwable
        {
            // Get the argument-stack for the current thread.
            final ArgumentStack stack = ArgumentStack.getThreadStack();

            // Get the value from the property.
            // This may cause a getter to be executed.
            get(owner, index, stack);

            // Retrieve the returned value.
            final boolean value = stack.peekZ();
            stack.clear();

            return value;
        }

        protected static char getC(final AbstractPrototype owner,
                                   final int index)
                throws Throwable
        {
            // Get the argument-stack for the current thread.
            final ArgumentStack stack = ArgumentStack.getThreadStack();

            // Get the value from the property.
            // This may cause a getter to be executed.
            get(owner, index, stack);

            // Retrieve the returned value.
            final char value = stack.peekC();
            stack.clear();

            return value;
        }

        protected static short getS(final AbstractPrototype owner,
                                    final int index)
                throws Throwable
        {
            // Get the argument-stack for the current thread.
            final ArgumentStack stack = ArgumentStack.getThreadStack();

            // Get the value from the property.
            // This may cause a getter to be executed.
            get(owner, index, stack);

            // Retrieve the returned value.
            final short value = stack.peekS();
            stack.clear();

            return value;
        }

        protected static int getI(final AbstractPrototype owner,
                                  final int index)
                throws Throwable
        {
            // Get the argument-stack for the current thread.
            final ArgumentStack stack = ArgumentStack.getThreadStack();

            // Get the value from the property.
            // This may cause a getter to be executed.
            get(owner, index, stack);

            // Retrieve the returned value.
            final int value = stack.peekI();
            stack.clear();

            return value;
        }

        protected static long getJ(final AbstractPrototype owner,
                                   final int index)
                throws Throwable
        {
            // Get the argument-stack for the current thread.
            final ArgumentStack stack = ArgumentStack.getThreadStack();

            // Get the value from the property.
            // This may cause a getter to be executed.
            get(owner, index, stack);

            // Retrieve the returned value.
            final long value = stack.peekJ();
            stack.clear();

            return value;
        }

        protected static float getF(final AbstractPrototype owner,
                                    final int index)
                throws Throwable
        {
            // Get the argument-stack for the current thread.
            final ArgumentStack stack = ArgumentStack.getThreadStack();

            // Get the value from the property.
            // This may cause a getter to be executed.
            get(owner, index, stack);

            // Retrieve the returned value.
            final float value = stack.peekF();
            stack.clear();

            return value;
        }

        protected static double getD(final AbstractPrototype owner,
                                     final int index)
                throws Throwable
        {
            // Get the argument-stack for the current thread.
            final ArgumentStack stack = ArgumentStack.getThreadStack();

            // Get the value from the property.
            // This may cause a getter to be executed.
            get(owner, index, stack);

            // Retrieve the returned value.
            final double value = stack.peekD();
            stack.clear();

            return value;
        }

        protected static Object getO(final AbstractPrototype owner,
                                     final int index)
                throws Throwable
        {
            // Get the argument-stack for the current thread.
            final ArgumentStack stack = ArgumentStack.getThreadStack();

            // Get the value from the property.
            // This may cause a getter to be executed.
            get(owner, index, stack);

            // Retrieve the returned value.
            final Object value = stack.peekO();
            stack.clear();

            return value;
        }
    }

    /**
     * @{@inheritDoc}
     */
    @Override
    public abstract AbstractPrototype copy();

    /**
     * These are the methods and properties in this prototype object.
     * Essentially, the purpose of this object is to be a wrapper around this array.
     */
    private AbstractLowLevelMember[] members;

    /**
     * Constructor.
     *
     * @param klass describes the new prototype object.
     */
    public AbstractPrototype(final MetaPrototype klass)
    {
        this.members = klass.instantiate();
    }

    /**
     * Constructor.
     *
     * @param original is the prototype to copy.
     */
    public AbstractPrototype(final AbstractPrototype original)
    {
        /**
         * Since we are only creating a simple copy, we can reuse the internal state.
         */
        this.members = original.members;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<Member> members()
    {
        final AbstractPrototype SELF = this;

        /**
         * In order to be more efficient, this list will lazily create the member objects.
         */
        final List<Member> list = new AbstractList<Member>()
        {
            @Override
            public Member get(final int index)
            {
                assert members[index].isProperty() || members[index].isMethod();

                return members[index].isProperty()
                        ? (new ConcreteProperty(SELF, index, (AbstractLowLevelProperty) members[index]))
                        : (new ConcreteMethod(SELF, index, (LowLevelMethod) members[index]));
            }

            @Override
            public int size()
            {
                return members.length;
            }
        };

        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean equals(final Object o)
    {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int hashCode()
    {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String toString()
    {
        return "";
    }
}
