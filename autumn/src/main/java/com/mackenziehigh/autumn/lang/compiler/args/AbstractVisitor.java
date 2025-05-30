package com.mackenziehigh.autumn.lang.compiler.args;

import com.mackenziehigh.snowflake.ITreeNode;
import com.mackenziehigh.snowflake.ITreeNodeVisitor;


/**
 * This class was auto-generated using the Snowflake parser-generator.
 *
 * <p>Generated On: Mon Mar 30 16:55:04 EDT 2015</p>
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
        else if("cases".equals(name)) { visit_cases(node); }
        else if("case_execute".equals(name)) { visit_case_execute(node); }
        else if("paths".equals(name)) { visit_paths(node); }
        else if("path".equals(name)) { visit_path(node); }
        else if("case_compile".equals(name)) { visit_case_compile(node); }
        else if("case_create".equals(name)) { visit_case_create(node); }
        else if("name".equals(name)) { visit_name(node); }
        else if("case_run".equals(name)) { visit_case_run(node); }
        else if("case_test".equals(name)) { visit_case_test(node); }
        else if("case_doc".equals(name)) { visit_case_doc(node); }
        else if("case_version".equals(name)) { visit_case_version(node); }
        else if("case_help".equals(name)) { visit_case_help(node); }
        else if("case_license".equals(name)) { visit_case_license(node); }
        else if("args".equals(name)) { visit_args(node); }
        else if("arg".equals(name)) { visit_arg(node); }
        else if("qstring".equals(name)) { visit_qstring(node); }
        else if("pstring".equals(name)) { visit_pstring(node); }
        else if("error".equals(name)) { visit_error(node); }
        else if("AUTUMN".equals(name)) { visit_AUTUMN(node); }
        else if("COMPILE".equals(name)) { visit_COMPILE(node); }
        else if("CREATE".equals(name)) { visit_CREATE(node); }
        else if("DOCUMENT".equals(name)) { visit_DOCUMENT(node); }
        else if("EXECUTE".equals(name)) { visit_EXECUTE(node); }
        else if("HELP".equals(name)) { visit_HELP(node); }
        else if("LICENSE".equals(name)) { visit_LICENSE(node); }
        else if("RUN".equals(name)) { visit_RUN(node); }
        else if("TEST".equals(name)) { visit_TEST(node); }
        else if("VERSION".equals(name)) { visit_VERSION(node); }
        else if("Q".equals(name)) { visit_Q(node); }
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
     * This method visits a parse-tree node created by rule "AUTUMN".
     */
    protected void visit_AUTUMN(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "COMPILE".
     */
    protected void visit_COMPILE(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "CREATE".
     */
    protected void visit_CREATE(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "DOCUMENT".
     */
    protected void visit_DOCUMENT(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "EXECUTE".
     */
    protected void visit_EXECUTE(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "HELP".
     */
    protected void visit_HELP(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "LICENSE".
     */
    protected void visit_LICENSE(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "Q".
     */
    protected void visit_Q(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "RUN".
     */
    protected void visit_RUN(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "TEST".
     */
    protected void visit_TEST(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "VERSION".
     */
    protected void visit_VERSION(ITreeNode node)
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
     * This method visits a parse-tree node created by rule "case_compile".
     */
    protected void visit_case_compile(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "case_create".
     */
    protected void visit_case_create(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "case_doc".
     */
    protected void visit_case_doc(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "case_execute".
     */
    protected void visit_case_execute(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "case_help".
     */
    protected void visit_case_help(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "case_license".
     */
    protected void visit_case_license(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "case_run".
     */
    protected void visit_case_run(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "case_test".
     */
    protected void visit_case_test(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "case_version".
     */
    protected void visit_case_version(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "cases".
     */
    protected void visit_cases(ITreeNode node)
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
     * This method visits a parse-tree node created by rule "line".
     */
    protected void visit_line(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "name".
     */
    protected void visit_name(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "path".
     */
    protected void visit_path(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "paths".
     */
    protected void visit_paths(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "pstring".
     */
    protected void visit_pstring(ITreeNode node)
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
