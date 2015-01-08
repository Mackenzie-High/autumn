package high.mackenzie.autumn.lang.debugger;

import autumn.lang.Local;
import autumn.lang.LocalsMap;
import autumn.lang.Record;
import autumn.util.T;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import high.mackenzie.snowflake.ITreeNode;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * This is a little interpreter for interpreting debugger commands.
 *
 * @author Mackenzie High
 */
public final class Visitor
        extends AbstractVisitor
{
    public boolean done;

    public LocalsMap locals;

    /**
     * This map is used to hold debugger variables.
     */
    private final Map<String, Object> state = Maps.newTreeMap();

    private final Stack<Object> operands = new Stack<Object>();

    @Override
    public void visitUnknown(final ITreeNode node)
    {
        for (ITreeNode child : node.children())
        {
            visit(child);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit_root(final ITreeNode node)
    {
        visitUnknown(node);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit_integer(final ITreeNode node)
    {
        final int value = Integer.valueOf(node.childAt(0).text().trim());

        operands.push(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit_variable(final ITreeNode node)
    {
        final String name = node.childAt(0).text().trim();

        if (locals.get(name) == null)
        {
            operands.push(state.get(name));
        }
        else
        {
            operands.push(locals.get(name).value());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit_assignment(final ITreeNode node)
    {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit_printlns(final ITreeNode node)
    {
        visitUnknown(node);

        for (Object element : (Iterable) operands.pop())
        {
            System.out.println(element);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit_println(final ITreeNode node)
    {
        visitUnknown(node);

        System.out.println(operands.pop());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit_vars(final ITreeNode node)
    {
        for (Local local : locals.locals())
        {
            System.out.println(local.name() + " : " + local.type().getSimpleName());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit_locals(final ITreeNode node)
    {
        locals.print(System.out);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit_location(final ITreeNode node)
    {
        System.out.println("Line 1 - TODO");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit_continue(final ITreeNode node)
    {
        done = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit_exit(final ITreeNode node)
    {
        System.exit(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit_field(final ITreeNode node)
    {
        final Object owner = operands.pop();

        Preconditions.checkNotNull(owner, "null owner");

        final String name = node.childAt(2).text().trim();

        Object value = null;

        if (owner instanceof Record)
        {
            final Record record = (Record) owner;

            value = T.get(record, name);
        }
        else
        {
            try
            {
                final Field field = owner.getClass().getField(name);

                value = field.get(owner);
            }
            catch (NoSuchFieldException ex)
            {
                // TODO
            }
            catch (IllegalArgumentException ex)
            {
                // TODO
            }
            catch (IllegalAccessException ex)
            {
                // TODO
            }
        }

        operands.push(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit_index(final ITreeNode node)
    {
        if (operands.peek() instanceof List)
        {
            /**
             * Pop the list off of the stack.
             */
            final List operand = (List) operands.pop();

            /**
             * Evaluate the index.
             */
            visitUnknown(node.childAt(2));

            /**
             * Pop the index off of the stack.
             */
            final int index = (Integer) operands.pop();

            /**
             * Retrieve the indexed element.
             */
            final Object value = operand.get(index);

            /**
             * Push the element onto the stack.
             */
            operands.push(value);
        }
        else
        {
            /**
             * Pop the map off of the stack.
             */
            final Map operand = (Map) operands.pop();

            /**
             * Evaluate the key.
             */
            visitUnknown(node.childAt(2));

            /**
             * Pop the key off of the stack.
             */
            final Object key = operands.pop();

            /**
             * Retrieve the element.
             */
            final Object value = operand.get(key);

            /**
             * Push the element onto the stack.
             */
            operands.push(value);
        }
    }
}
