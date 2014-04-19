package high.mackenzie.autumn.lang.compiler.args;

import high.mackenzie.snowflake.ITreeNode;
import high.mackenzie.snowflake.ITreeNodeVisitor;


/**
 * This class was auto-generated using the Snowflake parser-generator.
 *
 * <p>Generated On: Mon Feb 24 03:44:08 EST 2014</p>
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

        if("line".equals(name)) { visit_line(node); }
        else if("accept".equals(name)) { visit_accept(node); }
        else if("ignore".equals(name)) { visit_ignore(node); }
        else if("options".equals(name)) { visit_options(node); }
        else if("option".equals(name)) { visit_option(node); }
        else if("option_help".equals(name)) { visit_option_help(node); }
        else if("option_interpret".equals(name)) { visit_option_interpret(node); }
        else if("option_compile".equals(name)) { visit_option_compile(node); }
        else if("option_source".equals(name)) { visit_option_source(node); }
        else if("option_library".equals(name)) { visit_option_library(node); }
        else if("option_output".equals(name)) { visit_option_output(node); }
        else if("option_pretty".equals(name)) { visit_option_pretty(node); }
        else if("option_version".equals(name)) { visit_option_version(node); }
        else if("option_license".equals(name)) { visit_option_license(node); }
        else if("args".equals(name)) { visit_args(node); }
        else if("arg".equals(name)) { visit_arg(node); }
        else if("arguments".equals(name)) { visit_arguments(node); }
        else if("argument".equals(name)) { visit_argument(node); }
        else if("qstring".equals(name)) { visit_qstring(node); }
        else if("pstring1".equals(name)) { visit_pstring1(node); }
        else if("pstring2".equals(name)) { visit_pstring2(node); }
        else if("error".equals(name)) { visit_error(node); }
        else if("AUTUMN".equals(name)) { visit_AUTUMN(node); }
        else if("WS".equals(name)) { visit_WS(node); }
        else if("SPACE".equals(name)) { visit_SPACE(node); }
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
     * This method visits a parse-tree node created by rule "AUTUMN".
     */
    protected void visit_AUTUMN(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "SPACE".
     */
    protected void visit_SPACE(ITreeNode node)
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
     * This method visits a parse-tree node created by rule "accept".
     */
    protected void visit_accept(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "arg".
     */
    protected void visit_arg(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "args".
     */
    protected void visit_args(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "argument".
     */
    protected void visit_argument(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "arguments".
     */
    protected void visit_arguments(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "error".
     */
    protected void visit_error(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "ignore".
     */
    protected void visit_ignore(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "line".
     */
    protected void visit_line(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "option".
     */
    protected void visit_option(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "option_compile".
     */
    protected void visit_option_compile(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "option_help".
     */
    protected void visit_option_help(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "option_interpret".
     */
    protected void visit_option_interpret(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "option_library".
     */
    protected void visit_option_library(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "option_license".
     */
    protected void visit_option_license(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "option_output".
     */
    protected void visit_option_output(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "option_pretty".
     */
    protected void visit_option_pretty(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "option_source".
     */
    protected void visit_option_source(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "option_version".
     */
    protected void visit_option_version(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "options".
     */
    protected void visit_options(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "pstring1".
     */
    protected void visit_pstring1(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "pstring2".
     */
    protected void visit_pstring2(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "qstring".
     */
    protected void visit_qstring(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

}
