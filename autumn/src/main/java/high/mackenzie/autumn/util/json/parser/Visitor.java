package high.mackenzie.autumn.util.json.parser;

import autumn.lang.Record;
import autumn.util.F;
import com.mackenziehigh.snowflake.ITreeNode;
import java.math.BigDecimal;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

/**
 * An instance of this class can be used to convert a JSON string to an Autumn object tree.
 *
 * @author Mackenzie High
 */
public final class Visitor
        extends AbstractVisitor
{
    /**
     * This stack is used to hold intermediate values during the conversion process.
     */
    public final Stack<Object> stack = new Stack<Object>();

    /**
     * This map maps a set containing the keys of a record to the record itself.
     * The records are used as prototypes for creating new records.
     */
    public final Map<Set<String>, Record> prototypes = new HashMap<Set<String>, Record>();

    /**
     * {@inheritDoc}
     */
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
    public void visit_null(final ITreeNode node)
    {
        stack.push(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit_boolean(final ITreeNode node)
    {
        if (node.text().equalsIgnoreCase("true"))
        {
            stack.push(Boolean.TRUE);
        }
        else
        {
            stack.push(Boolean.FALSE);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit_number(final ITreeNode node)
    {
        final String text = node.text().trim();

        final BigDecimal number = F.parseBigDecimal(text);

        stack.push(number);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit_string(final ITreeNode node)
    {
        String text = node.text().trim();

        // TODO: Fix Grammar - capturing WS

        text = text.substring(1, text.length() - 1);

        stack.push(text);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit_pair(final ITreeNode node)
    {
        visitUnknown(node);

        final Object value = stack.pop();

        final Object key = stack.pop();

        final SimpleImmutableEntry entry = new SimpleImmutableEntry(key, value);

        stack.push(entry);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit_object(final ITreeNode node)
    {
        final int size = stack.size();

        visitUnknown(node);

        final Map<Object, Object> builder = new HashMap<Object, Object>();

        while (stack.size() > size)
        {
            final Entry entry = (Entry) stack.pop();

            builder.put(entry.getKey().toString(), entry.getValue());
        }

        if (prototypes.containsKey(builder.keySet()))
        {
            final Record prototype = prototypes.get(builder.keySet());

            final Record record = F.set(prototype, builder);

            stack.push(record);
        }
        else
        {
            stack.push(F.unmodifiable(builder));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit_array(final ITreeNode node)
    {
        final int size = stack.size();

        visitUnknown(node);

        final List builder = new LinkedList();

        while (stack.size() > size)
        {
            builder.add(0, stack.pop());
        }

        stack.push(F.unmodifiable(builder));
    }
}
