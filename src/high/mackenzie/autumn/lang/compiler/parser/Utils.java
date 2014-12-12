package high.mackenzie.autumn.lang.compiler.parser;

import autumn.lang.compiler.TreeBuilder;
import autumn.lang.compiler.ast.commons.IConstruct;
import autumn.lang.compiler.ast.commons.IExpression;
import autumn.lang.compiler.ast.literals.BigDecimalLiteral;
import autumn.lang.compiler.ast.literals.BigIntegerLiteral;
import autumn.lang.compiler.ast.literals.ByteLiteral;
import autumn.lang.compiler.ast.literals.CharLiteral;
import autumn.lang.compiler.ast.literals.DoubleLiteral;
import autumn.lang.compiler.ast.literals.FloatLiteral;
import autumn.lang.compiler.ast.literals.IntLiteral;
import autumn.lang.compiler.ast.literals.LongLiteral;
import autumn.lang.compiler.ast.literals.ShortLiteral;
import autumn.lang.compiler.ast.nodes.Name;
import autumn.util.Strings;
import com.google.common.collect.Lists;
import high.mackenzie.autumn.resources.Finished;
import high.mackenzie.snowflake.ITreeNode;
import high.mackenzie.snowflake.LinesAndColumns;
import high.mackenzie.snowflake.NewlineStyles;
import high.mackenzie.snowflake.TreeNode;
import java.io.File;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * This class is used by the parser to help facilitate the building of an abstract-syntax-tree.
 * This class helps connect the parser to the tree-builder.
 * Specifically, this class provides a location to store state and place utility methods.
 *
 * <p>
 * <b>Warning: </b>Only one parser can be utilizing this class at a time.
 * </p>
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public final class Utils
{
    /**
     * It is OK for this to be public.
     */
    public static URL source_file = null;

    /**
     * This builder is used to construct an AST.
     */
    private static final TreeBuilder builder = new TreeBuilder();

    /**
     * This method retrieves the builder that is building the AST.
     *
     * @return the aforedescribed builder.
     */
    public static TreeBuilder builder()
    {
        return builder;
    }

    /**
     * Whenever a string-literal is encountered, this field will be assigned its content.
     */
    private static String last_string;

    /**
     * Whenever a string-literal is encountered, this field will be set.
     */
    private static boolean verbatim;

    public static void storeStringValue(final ITreeNode node,
                                        final boolean verbatim)
    {
        Utils.verbatim = verbatim;

        String literal = TreeNode.find(node, "STRING_LITERAL").text().trim();

        if (literal.startsWith("'''"))
        {
            last_string = literal.substring(3, literal.length() - 3);
        }
        else // literal.startsWith("\"")
        {
            last_string = literal.substring(1, literal.length() - 1);
        }

        if (verbatim)
        {
            return;
        }

        // TODO: escape sequences
    }

    public static boolean getVerbatim()
    {
        return verbatim;
    }

    public static String getString()
    {
        return last_string;
    }

    public static File getFile()
    {
        return new File(last_string);
    }

    public static void createChainedMethodCall()
    {
        if (builder.size() == 1)
        {
            return;
        }

        /**
         * The primary stack looks something like this (from top to bottom):
         *
         * . argument[N]
         * . argument[2]
         * . argument[1]
         * . name
         * . argument[N]
         * . argument[2]
         * . argument[1]
         * . name
         * . argument[N]
         * . argument[2]
         * . argument[1]
         * . name
         * . owner
         *
         * Get the primary stack off of the stack of stacks.
         */
        final Stack<IConstruct> stack = builder.copyStack();

        /**
         * Remove the primary stack from the stack of stack.
         */
        builder.clear();
        builder.popStack();

        /**
         * Reverse the order of the stack (i.e. the former primary stack).
         * So now, the stack looks something like this (from top to bottom):
         *
         * . owner
         * . name
         * . argument[1]
         * . argument[2]
         * . argument[N]
         * . name
         * . argument[1]
         * . argument[2]
         * . argument[N]
         * . name
         * . argument[1]
         * . argument[2]
         * . argument[N]
         */
        Collections.reverse(stack);

        /**
         * Push a new primary stack onto the stack of stacks.
         */
        builder.pushStack();

        /**
         * Transfer the owner onto the primary stack.
         */
        assert stack.peek() instanceof IExpression;
        builder.push(stack.pop());

        /**
         * For each chained method call:
         */
        while (stack.isEmpty() == false)
        {
            /**
             * Transfer the name onto the primary stack.
             */
            assert stack.peek() instanceof Name;
            builder.push(stack.pop());

            /**
             * Transfer each argument onto the primary stack.
             */
            while (stack.isEmpty() == false && stack.peek() instanceof Name == false)
            {
                assert stack.peek() instanceof IExpression;
                builder.push(stack.pop());
            }

            /**
             * Create a call-method-expression given the single chained method call
             * that is currently in pieces on the primary stack.
             */
            builder.createExpressionCallMethod();
        }

        assert stack.isEmpty();
        assert builder.size() == 1;
    }

    public static void createComponentTypeSpecifier(final ITreeNode node)
    {
        final int dimensions = TreeNode.findAll(node, "dimension").size();

        builder.createComponentTypeSpecifier(dimensions == 0 ? null : dimensions);
    }

    public static void createComponentName(final ITreeNode node)
    {
        final String identifier = TreeNode.find(node, "ID").text().trim();

        Name result = new Name();
        result = result.setName(identifier);

        builder.push(result);
    }

    public static void createComponentVariable(final ITreeNode node)
    {
        final String identifier = node.childAt(0).text().trim();

        builder.createComponentVariable(identifier);
    }

    public static void createComponentLabel(final ITreeNode node)
    {
        final String identifier = node.childAt(0).text().trim();

        builder.createComponentLabel(identifier);
    }

    public static IntLiteral extractIntLiteral(final ITreeNode node)
    {
        final ITreeNode integer = TreeNode.find(node.iterableBFS(), "int_value");

        final IntLiteral constant = extractIntValue(integer);

        return constant;
    }

    public static boolean extractBooleanValue(final ITreeNode node)
    {
        final String value = node.childAt(0).text().trim();

        return Boolean.valueOf(value);
    }

    public static CharLiteral extractCharValue(final ITreeNode node)
    {
        return new CharLiteral(removeWS(node));
    }

    public static ByteLiteral extractByteValue(final ITreeNode node)
    {
        return new ByteLiteral(removeWS(node));
    }

    public static ShortLiteral extractShortValue(final ITreeNode node)
    {
        return new ShortLiteral(removeWS(node));
    }

    public static IntLiteral extractIntValue(final ITreeNode node)
    {
        return new IntLiteral(removeWS(node));
    }

    public static LongLiteral extractLongValue(final ITreeNode node)
    {
        return new LongLiteral(removeWS(node));
    }

    public static FloatLiteral extractFloatValue(final ITreeNode node)
    {
        return new FloatLiteral(removeWS(node));
    }

    public static DoubleLiteral extractDoubleValue(final ITreeNode node)
    {
        return new DoubleLiteral(removeWS(node));
    }

    public static BigIntegerLiteral extractBigIntegerValue(final ITreeNode node)
    {
        return new BigIntegerLiteral(removeWS(node));
    }

    public static BigDecimalLiteral extractBigDecimalValue(final ITreeNode node)
    {
        return new BigDecimalLiteral(removeWS(node));
    }

    public static List<String> extractAnnotationValues(final ITreeNode node)
    {
        final List<ITreeNode> nodes = TreeNode.findAll(node, "annotation_value");

        if (nodes.isEmpty())
        {
            return null;
        }

        final List<String> values = Lists.newLinkedList();

        for (ITreeNode x : nodes)
        {
            values.add(extractAnnotationValue(x));
        }

        return values;
    }

    private static String extractAnnotationValue(final ITreeNode node)
    {
        final ITreeNode annotation_value = TreeNode.find(node, "annotation_value");

        if (annotation_value == null)
        {
            return null;
        }

        final String text = annotation_value.text().trim();

        final String modified = text.startsWith("\"") || text.startsWith("'")
                ? text.substring(1, text.length() - 1)
                : text;

        final String escaped = Strings.escape(modified);

        return escaped;
    }

    public static String extractCommentLine(final ITreeNode node)
    {
        return TreeNode.find(node, "api_comment_text").text().trim();
    }

    public static String removeWS(final ITreeNode node)
    {
        // This method removes all nodes created by the WS rule.
        // Then, the leaf nodes are used to form a string, which is then returned.

        final StringBuilder result = new StringBuilder();

        for (ITreeNode kid : node.children())
        {
            if (kid.rule().equals("WS"))
            {
                // Do Nothing
            }
            else if (kid.childCount() == 0)
            {
                result.append(kid.text());
            }
            else
            {
                result.append(removeWS(kid));
            }
        }

        return result.toString();
    }

    public static void setSourceLocation(final ITreeNode node)
    {
        final char[] code = node.input();

        final int position = node.start();

        final NewlineStyles newline = NewlineStyles.fromSystem();

        final LinesAndColumns finder = new LinesAndColumns(code, newline);

        final int line = finder.lineNumbers()[position];

        final int column = finder.columnNumbers()[position];

        builder.setSourceLocation(source_file, line, column, null);
    }
}
