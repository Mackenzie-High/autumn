package high.mackenzie.autumn.util.data.sexpr;

import high.mackenzie.snowflake.ITreeNode;
import high.mackenzie.snowflake.ITreeNodeVisitor;


/**
 * This class was auto-generated using the Snowflake parser-generator.
 *
 * <p>Generated On: Sun Nov 30 10:05:18 EST 2014</p>
 */
public abstract class AbstractVisitor implements ITreeNodeVisitor
{

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit(ITreeNode node)
    {
        final String name = node.rule();

        if("input".equals(name)) { visit_input(node); }
        else if("value".equals(name)) { visit_value(node); }
        else if("null".equals(name)) { visit_null(node); }
        else if("boolean".equals(name)) { visit_boolean(node); }
        else if("number".equals(name)) { visit_number(node); }
        else if("number_value".equals(name)) { visit_number_value(node); }
        else if("number_value_1".equals(name)) { visit_number_value_1(node); }
        else if("number_value_2".equals(name)) { visit_number_value_2(node); }
        else if("number_value_3".equals(name)) { visit_number_value_3(node); }
        else if("number_value_4".equals(name)) { visit_number_value_4(node); }
        else if("sign".equals(name)) { visit_sign(node); }
        else if("e".equals(name)) { visit_e(node); }
        else if("word".equals(name)) { visit_word(node); }
        else if("string".equals(name)) { visit_string(node); }
        else if("STR_CHARS".equals(name)) { visit_STR_CHARS(node); }
        else if("STR_CHAR".equals(name)) { visit_STR_CHAR(node); }
        else if("ESCAPE_SEQUENCE".equals(name)) { visit_ESCAPE_SEQUENCE(node); }
        else if("NON_QUOTE".equals(name)) { visit_NON_QUOTE(node); }
        else if("list".equals(name)) { visit_list(node); }
        else if("elements".equals(name)) { visit_elements(node); }
        else if("DIGITS".equals(name)) { visit_DIGITS(node); }
        else if("DIGIT".equals(name)) { visit_DIGIT(node); }
        else if("WS".equals(name)) { visit_WS(node); }
        else if("WS_CHR".equals(name)) { visit_WS_CHR(node); }
        else { visitUnknown(node); }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visitUnknown(ITreeNode node)
    {
        // You should *not* place your code right here.
        // Instead, you should override this method via a subclass.
    }

    /**
     * This method visits a parse-tree node created by rule "DIGIT".
     */
    protected void visit_DIGIT(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "DIGITS".
     */
    protected void visit_DIGITS(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "ESCAPE_SEQUENCE".
     */
    protected void visit_ESCAPE_SEQUENCE(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "NON_QUOTE".
     */
    protected void visit_NON_QUOTE(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "STR_CHAR".
     */
    protected void visit_STR_CHAR(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "STR_CHARS".
     */
    protected void visit_STR_CHARS(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "WS".
     */
    protected void visit_WS(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "WS_CHR".
     */
    protected void visit_WS_CHR(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "boolean".
     */
    protected void visit_boolean(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "e".
     */
    protected void visit_e(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "elements".
     */
    protected void visit_elements(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "input".
     */
    protected void visit_input(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "list".
     */
    protected void visit_list(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "null".
     */
    protected void visit_null(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "number".
     */
    protected void visit_number(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "number_value".
     */
    protected void visit_number_value(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "number_value_1".
     */
    protected void visit_number_value_1(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "number_value_2".
     */
    protected void visit_number_value_2(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "number_value_3".
     */
    protected void visit_number_value_3(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "number_value_4".
     */
    protected void visit_number_value_4(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "sign".
     */
    protected void visit_sign(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "string".
     */
    protected void visit_string(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "value".
     */
    protected void visit_value(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "word".
     */
    protected void visit_word(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

}
