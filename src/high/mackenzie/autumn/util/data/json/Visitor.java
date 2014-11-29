//package high.mackenzie.autumn.util.data.json;
//
//import high.mackenzie.snowflake.ITreeNode;
//import java.util.AbstractMap.SimpleImmutableEntry;
//import java.util.Map.Entry;
//import java.util.Stack;
//
///**
// *
// *
// * @author mackenzie
// */
//public final class Visitor
//        extends AbstractVisitor
//{
//    public final Stack<Object> stack = new Stack<Object>();
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void visitUnknown(final ITreeNode node)
//    {
//        for (ITreeNode child : node.children())
//        {
//            visit(child);
//        }
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void visit_null(final ITreeNode node)
//    {
//        stack.push(Json.nil());
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void visit_boolean(final ITreeNode node)
//    {
//        if (node.text().contains("true"))
//        {
//            stack.push(Json.bool(true));
//        }
//        else
//        {
//            stack.push(Json.bool(false));
//        }
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void visit_number(final ITreeNode node)
//    {
//        final String text = node.text().trim();
//
//        stack.push(Json.number(text));
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void visit_string(final ITreeNode node)
//    {
//        String text = node.text().trim();
//
//        // TODO: Fix Grammar - capturing WS
//
//        text = text.substring(1, text.length() - 1);
//
//
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void visit_pair(final ITreeNode node)
//    {
//        visitUnknown(node);
//
//        final JsonValue value = (JsonValue) stack.pop();
//
//        final JsonValue key = (JsonValue) stack.pop();
//
//        final SimpleImmutableEntry entry = new SimpleImmutableEntry(key, value);
//
//        stack.push(entry);
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void visit_object(final ITreeNode node)
//    {
//        final int size = stack.size();
//
//        visitUnknown(node);
//
//        final ConcreteMutableJsonObject builder = new ConcreteMutableJsonObject();
//
//        while (stack.size() > size)
//        {
//            final Entry<JsonValue, JsonValue> entry = (Entry<JsonValue, JsonValue>) stack.pop();
//
//            builder.put(entry.getKey().toString(), entry.getValue());
//        }
//
//        stack.push(builder.immutable());
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void visit_array(final ITreeNode node)
//    {
//        final int size = stack.size();
//
//        visitUnknown(node);
//
//        final ConcreteMutableJsonArray builder = new ConcreteMutableJsonArray();
//
//        while (stack.size() > size)
//        {
//            builder.add(0, (JsonValue) stack.pop());
//        }
//
//        stack.push(builder.immutable());
//    }
//}

