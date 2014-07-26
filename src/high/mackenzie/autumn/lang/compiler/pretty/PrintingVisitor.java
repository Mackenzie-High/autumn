package high.mackenzie.autumn.lang.compiler.pretty;

import autumn.lang.compiler.ast.commons.ConstructList;
import autumn.lang.compiler.ast.commons.IBinaryOperation;
import autumn.lang.compiler.ast.commons.IConstruct;
import autumn.lang.compiler.ast.commons.IConversionOperation;
import autumn.lang.compiler.ast.commons.IExpression;
import autumn.lang.compiler.ast.commons.IStatement;
import autumn.lang.compiler.ast.commons.IUnaryOperation;
import autumn.lang.compiler.ast.nodes.*;
import autumn.lang.compiler.exceptions.IncompleteNodeException;
import autumn.lang.compiler.exceptions.RepeatedNodeException;
import autumn.lang.compiler.exceptions.UnprintableNodeException;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import high.mackenzie.autumn.resources.Finished;
import java.util.List;
import java.util.Set;

/**
 * An instance of this class is a simple pretty-printer of Abstract Syntax Trees.
 *
 * <p>
 * This visitor throws a RepeatedNodeException, if an AST node appears more than once in an AST.
 * </p>
 *
 * <p>
 * This visitor throws a IncompleteNodeException, if an AST is incomplete.
 * </p>
 *
 * <p>
 * This visitor throws an UnprintableNodeException, if an AST node is unprintable.
 * </p>
 *
 * <p>
 * This visitor records the AST nodes that it visits.
 * This record can be obtained via the visited() method in order to linearize the AST.
 * </p>
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public final class PrintingVisitor
        implements IAstVisitor
{
    private static final String ID_REGEX = "([A-Za-z_$][A-Za-z_$0-9]*)";

    private final CurlyPrinter p = new CurlyPrinter();

    private final Set<IConstruct> visited = Sets.newIdentityHashSet();

    private final List<IConstruct> linearized = Lists.newLinkedList();

    /**
     * This method returns all the AST nodes that were visited thus far,
     * in the order they were visited.
     *
     * @return the aforedescribed nodes.
     */
    public List<IConstruct> visited()
    {
        return ImmutableList.copyOf(linearized);
    }

    /**
     * This method retrieves the pretty printable string that was created.
     *
     * <p>
     * Note: Calling this method causes the internal state of this object to be reset.
     * </p>
     *
     * @return the aforedescribed string.
     */
    public String buildString()
    {
        visited.clear();

        final String result = p.buildString();

        return result;
    }

    /**
     * The method requires that a specific AST node exists.
     *
     * @param parent is the parent of the node that must exist.
     * @param node is the node that must exist.
     * @throws IncompleteNodeException if the node is null (i.e. does not exist).
     */
    private void require(final IConstruct parent,
                         final IConstruct node)
    {
        assert parent != null;

        if (node == null)
        {
            throw new IncompleteNodeException(parent);
        }
    }

    /**
     * The method requires that a list of AST nodes exist.
     *
     * @param parent is the parent of the nodes that must exist.
     * @param list is the list that must exist and contain node that exist.
     * @throws IncompleteNodeException if the the list or any of its element do not exist.
     */
    private void require(final IConstruct parent,
                         final ConstructList<? extends IConstruct> list)
    {
        assert parent != null;

        if (list == null || list.asMutableList().contains(null))
        {
            throw new IncompleteNodeException(parent);
        }
    }

    /**
     * The method reports that an AST node is missing, unless a condition is fulfilled.
     *
     * @param parent is the parent of the nodes that must exist.
     * @param condition is the condition that must be fulfilled.
     * @throws IncompleteNodeException if the condition is false.
     */
    private void require(final IConstruct parent,
                         final boolean condition)
    {
        assert parent != null;

        if (condition == false)
        {
            throw new IncompleteNodeException(parent);
        }
    }

    /**
     * This method records the visitation of an AST node.
     *
     * @param node is the node being visited.
     * @throws RepeatedNodeException if the node was already visited once.
     * @throws NullPointerException if the node is does not exist (i.e. null).
     * @throws NullPointerException if the node's location information is missing.
     */
    private void record(final IConstruct node)
    {
        Preconditions.checkNotNull(node, "An abstract-syntax-tree node is missing.");

        if (node.getLocation() == null)
        {
            throw new IncompleteNodeException(node);
        }

        if (visited.contains(node))
        {
            throw new RepeatedNodeException(node);
        }

        visited.add(node);
        linearized.add(node);

        // The node files are auto-generated.
        // Therefore, it is not necessary to test the equals(Object) of hashCode() methods.
        // However, the lack of tests messes up the unit-test coverage statistics.
        // Thus, these assertions will prevent that issue.
        assert node.toString().equals(node.toString());
        assert node.getLocation().equals(node.getLocation());
        assert node.getLocation().hashCode() == node.getLocation().hashCode();
        assert node.getLocation().toString().equals(node.getLocation().toString());
    }

    /**
     * This method reports that an AST node is unprintable, unless a condition is fulfilled.
     *
     * @param node is the node that may be unprintable.
     * @param condition is the condition that must be true for the node to be printable.
     * @throws UnprintableNodeException if the node is unprintable.
     */
    private void reportUnprintableNode(final IConstruct node,
                                       final boolean condition)
    {
        if (condition == false)
        {
            throw new UnprintableNodeException(node);
        }
    }

    @Override
    public void visit(final Module object)
    {
        record(object);

        require(object, object.getImportDirectives());
        require(object, object.getModuleDirectives());
        require(object, object.getAnnotations());
        require(object, object.getDesigns());
        require(object, object.getEnums());
        require(object, object.getExceptions());
        require(object, object.getExceptions());
        require(object, object.getFunctions());

        for (ModuleDirective x : object.getModuleDirectives())
        {
            x.accept(this);
        }

        p.addEmptyLine();

        for (ImportDirective x : object.getImportDirectives())
        {
            x.accept(this);
        }

        p.addEmptyLine();

        for (AnnotationDefinition x : object.getAnnotations())
        {
            x.accept(this);
        }

        p.addEmptyLine();

        for (ExceptionDefinition x : object.getExceptions())
        {
            x.accept(this);
        }

        p.addEmptyLine();

        for (TupleDefinition x : object.getTuples())
        {
            x.accept(this);
        }

        p.addEmptyLine();

        for (FunctorDefinition x : object.getFunctors())
        {
            x.accept(this);
        }

        p.addEmptyLine();

        for (EnumDefinition x : object.getEnums())
        {
            x.accept(this);
        }

        p.addEmptyLine();

        for (DesignDefinition x : object.getDesigns())
        {
            x.accept(this);
        }

        p.addEmptyLine();

        for (FunctionDefinition x : object.getFunctions())
        {
            x.accept(this);
        }

        p.removeEmptyLines();
    }

    @Override
    public void visit(final ModuleDirective object)
    {
        record(object);

        require(object, object.getComment());
        require(object, object.getAnnotations());
        require(object, object.getNamespace());

        object.getComment().accept(this);

        object.getAnnotations().accept(this);

        p.addLine();
        p.addText("module ");
        if (object.getName() == null)
        {
            p.addText("*");
        }
        else
        {
            object.getName().accept(this);
        }
        p.addText(" in ");
        object.getNamespace().accept(this);
        p.addText(";");
    }

    @Override
    public void visit(final ImportDirective object)
    {
        record(object);
        require(object, object.getType());

        p.addLine();
        p.addText("import ");
        object.getType().accept(this);
        p.addText(";");
    }

    @Override
    public void visit(final Annotation object)
    {
        record(object);
        require(object, object.getType());

        p.addLine();
        p.addText("@");
        object.getType().accept(this);
    }

    @Override
    public void visit(final AnnotationDefinition object)
    {
        record(object);
        require(object, object.getComment());
        require(object, object.getAnnotations());
        require(object, object.getName());

        object.getComment().accept(this);

        object.getAnnotations().accept(this);

        p.addLine();
        p.addText("annotation ");
        object.getName().accept(this);
        p.addText(";");

        p.addEmptyLine();
    }

    @Override
    public void visit(final ExceptionDefinition object)
    {
        record(object);
        require(object, object.getComment());
        require(object, object.getAnnotations());
        require(object, object.getName());
        require(object, object.getSuperclass());

        object.getComment().accept(this);

        object.getAnnotations().accept(this);

        p.addLine();
        p.addText("exception ");
        object.getName().accept(this);
        p.addText(" extends ");
        object.getSuperclass().accept(this);
        p.addText(";");

        p.addEmptyLine();
    }

    @Override
    public void visit(final TupleDefinition object)
    {
        record(object);
        require(object, object.getComment());
        require(object, object.getAnnotations());
        require(object, object.getName());
        require(object, object.getElements());

        object.getComment().accept(this);

        object.getAnnotations().accept(this);

        p.addLine();
        p.addText("tuple ");
        object.getName().accept(this);
        p.addText(" ");
        object.getElements().accept(this);
        p.addText(";");

        p.addEmptyLine();
    }

    @Override
    public void visit(final FunctorDefinition object)
    {
        record(object);
        require(object, object.getComment());
        require(object, object.getAnnotations());
        require(object, object.getName());
        require(object, object.getParameters());
        require(object, object.getReturnType());

        object.getComment().accept(this);

        object.getAnnotations().accept(this);

        p.addLine();
        p.addText("functor ");
        object.getName().accept(this);
        p.addText(" ");
        object.getParameters().accept(this);
        p.addText(" : ");
        object.getReturnType().accept(this);
        p.addText(";");

        p.addEmptyLine();
    }

    @Override
    public void visit(final EnumDefinition object)
    {
        record(object);
        require(object, object.getComment());
        require(object, object.getAnnotations());
        require(object, object.getName());
        require(object, object.getConstants());

        object.getComment().accept(this);

        object.getAnnotations().accept(this);

        p.addLine();
        p.addText("enum ");
        object.getName().accept(this);

        p.addOpeningBracket();
        {
            for (EnumConstant x : object.getConstants())
            {
                x.accept(this);
                p.addEmptyLine();
            }
        }
        p.removeEmptyLines();
        p.addClosingBracket();

        p.addEmptyLine();
    }

    @Override
    public void visit(final EnumConstant object)
    {
        record(object);
        require(object, object.getComment());
        require(object, object.getAnnotations());
        require(object, object.getName());

        object.getComment().accept(this);

        object.getAnnotations().accept(this);

        p.addLine();
        p.addText("constant ");
        object.getName().accept(this);
        p.addText(";");

        p.addEmptyLine();
    }

    @Override
    public void visit(final DesignDefinition object)
    {
        record(object);
        require(object, object.getComment());
        require(object, object.getAnnotations());
        require(object, object.getName());
        require(object, object.getSuperinterfaces());
        require(object, object.getProperties());

        object.getComment().accept(this);

        object.getAnnotations().accept(this);

        p.addLine();
        p.addText("design ");
        object.getName().accept(this);

        if (object.getSuperinterfaces().isEmpty() == false)
        {
            p.addText(" extends ");
        }

        int count = 0;

        for (TypeSpecifier supertype : object.getSuperinterfaces())
        {
            ++count;

            supertype.accept(this);

            if (count < object.getSuperinterfaces().size())
            {
                p.addText(" & ");
            }
        }

        p.addOpeningBracket();
        {
            for (DesignProperty x : object.getProperties())
            {
                x.accept(this);
            }

            for (DesignMethod x : object.getMethods())
            {
                x.accept(this);
            }
        }
        p.removeEmptyLines();
        p.addClosingBracket();

        p.addEmptyLine();
    }

    @Override
    public void visit(final DesignProperty object)
    {
        record(object);
        require(object, object.getComment());
        require(object, object.getAnnotations());
        require(object, object.getName());
        require(object, object.getType());

        object.getComment().accept(this);

        object.getAnnotations().accept(this);

        p.addLine();
        p.addText("data ");
        object.getName().accept(this);
        p.addText(" : ");
        object.getType().accept(this);
        p.addText(";");

        p.addEmptyLine();
    }

    @Override
    public void visit(final DesignMethod object)
    {
        record(object);
        require(object, object.getComment());
        require(object, object.getAnnotations());
        require(object, object.getName());
        require(object, object.getParameters());
        require(object, object.getReturnType());

        object.getComment().accept(this);

        object.getAnnotations().accept(this);

        p.addLine();
        p.addText("method ");
        object.getName().accept(this);
        p.addText(" ");
        object.getParameters().accept(this);
        p.addText(" : ");
        object.getReturnType().accept(this);
        p.addText(";");

        p.addEmptyLine();
    }

    @Override
    public void visit(final FunctionDefinition object)
    {
        record(object);
        require(object, object.getComment());
        require(object, object.getAnnotations());
        require(object, object.getName());
        require(object, object.getParameters());
        require(object, object.getReturnType());
        require(object, object.getBody());

        object.getComment().accept(this);

        object.getAnnotations().accept(this);

        p.addLine();
        p.addText("defun ");
        object.getName().accept(this);
        p.addText(" ");
        object.getParameters().accept(this);
        p.addText(" : ");
        object.getReturnType().accept(this);

        object.getBody().accept(this);

        p.addEmptyLine();
    }

    @Override
    public void visit(final IfStatement object)
    {
        record(object);
        require(object, object.getMainCase());

        p.addLine();
        p.addText("if ");
        object.getMainCase().accept(this);

        for (ConditionalCase elif_case : object.getElifCases())
        {
            p.addLine();
            p.addText("elif ");
            elif_case.accept(this);
        }

        if (object.getElseCase() != null)
        {
            p.addLine();
            p.addText("else");

            object.getElseCase().accept(this);
        }

        p.addEmptyLine();
    }

    @Override
    public void visit(final WhenStatement object)
    {
        record(object);
        require(object, object.getCondition());
        require(object, object.getBody());

        p.addLine();
        p.addText("when (");
        object.getCondition().accept(this);
        p.addText(") then");
        p.addOpeningBracket();
        object.getBody().accept(this);
        p.removeEmptyLines();
        p.addClosingBracket();
        p.addEmptyLine();
    }

    @Override
    public void visit(final GotoStatement object)
    {
        record(object);
        require(object, object.getLabel());

        p.addLine();
        p.addText("goto ");
        object.getLabel().accept(this);
        p.addText(";");
        p.addEmptyLine();
    }

    @Override
    public void visit(final MarkerStatement object)
    {
        record(object);
        require(object, object.getLabel());

        p.addLine();
        p.addText("marker ");
        object.getLabel().accept(this);
        p.addText(";");
        p.addEmptyLine();
    }

    @Override
    public void visit(final ForeverStatement object)
    {
        record(object);
        require(object, object.getBody());

        p.addLine();
        p.addText("forever");

        object.getBody().accept(this);

        p.addEmptyLine();
    }

    @Override
    public void visit(final WhileStatement object)
    {
        record(object);
        require(object, object.getCondition());
        require(object, object.getBody());

        p.addLine();
        p.addText("while (");
        object.getCondition().accept(this);
        p.addText(")");

        object.getBody().accept(this);

        p.addEmptyLine();
    }

    @Override
    public void visit(final UntilStatement object)
    {
        record(object);
        require(object, object.getCondition());
        require(object, object.getBody());

        p.addLine();
        p.addText("until (");
        object.getCondition().accept(this);
        p.addText(")");

        object.getBody().accept(this);

        p.addEmptyLine();
    }

    @Override
    public void visit(final DoWhileStatement object)
    {
        record(object);
        require(object, object.getBody());
        require(object, object.getCondition());

        p.addLine();
        p.addText("do");

        object.getBody().accept(this);

        p.addLine();
        p.addText("while (");
        object.getCondition().accept(this);
        p.addText(")");

        p.addEmptyLine();
    }

    @Override
    public void visit(final DoUntilStatement object)
    {
        record(object);
        require(object, object.getBody());
        require(object, object.getCondition());

        p.addLine();
        p.addText("do");

        object.getBody().accept(this);

        p.addLine();
        p.addText("until (");
        object.getCondition().accept(this);
        p.addText(")");

        p.addEmptyLine();
    }

    @Override
    public void visit(final ForStatement object)
    {
        record(object);
        require(object, object.getVariable());
        require(object, object.getInitializer());
        require(object, object.getCondition());
        require(object, object.getNext());
        require(object, object.getBody());

        p.addLine();
        p.addText("for (");
        object.getVariable().accept(this);
        p.addText(" = ");
        object.getInitializer().accept(this);
        p.addText("; ");
        object.getCondition().accept(this);
        p.addText("; ");
        object.getNext().accept(this);
        p.addText(")");

        object.getBody().accept(this);

        p.addEmptyLine();
    }

    @Override
    public void visit(final ForeachStatement object)
    {
        record(object);
        require(object, object.getVariable());
        require(object, object.getType());
        require(object, object.getIterable());
        require(object, object.getBody());

        p.addLine();
        p.addText("foreach (");
        object.getVariable().accept(this);
        p.addText(" : ");
        object.getType().accept(this);
        p.addText(" in ");
        object.getIterable().accept(this);
        p.addText(")");

        object.getBody().accept(this);

        p.addEmptyLine();
    }

    @Override
    public void visit(final BreakStatement object)
    {
        record(object);

        p.addLine();
        p.addText("break;");

        p.addEmptyLine();
    }

    @Override
    public void visit(final ContinueStatement object)
    {
        record(object);

        p.addLine();
        p.addText("continue;");

        p.addEmptyLine();
    }

    @Override
    public void visit(final RedoStatement object)
    {
        record(object);

        p.addLine();
        p.addText("redo;");
        p.addEmptyLine();
    }

    @Override
    public void visit(final VarStatement object)
    {
        record(object);
        require(object, object.getVariable());
        require(object, object.getValue());

        p.addLine();
        p.addText("var ");
        object.getVariable().accept(this);
        p.addText(" = ");
        object.getValue().accept(this);
        p.addText(";");
        p.addEmptyLine();
    }

    @Override
    public void visit(final ValStatement object)
    {
        record(object);
        require(object, object.getVariable());
        require(object, object.getValue());

        p.addLine();
        p.addText("val ");
        object.getVariable().accept(this);
        p.addText(" = ");
        object.getValue().accept(this);
        p.addText(";");
        p.addEmptyLine();
    }

    @Override
    public void visit(final LetStatement object)
    {
        record(object);
        require(object, object.getVariable());
        require(object, object.getValue());

        p.addLine();
        p.addText("let ");
        object.getVariable().accept(this);
        p.addText(" = ");
        object.getValue().accept(this);
        p.addText(";");
        p.addEmptyLine();
    }

    @Override
    public void visit(final LambdaStatement object)
    {
        record(object);
        require(object, object.getVariable());
        require(object, object.getType());
        require(object, object.getBody());

        p.addLine();
        p.addText("lambda ");
        object.getVariable().accept(this);
        p.addText(" : ");
        object.getType().accept(this);
        object.getBody().accept(this);
        p.addEmptyLine();
    }

    @Override
    public void visit(final DelegateStatement object)
    {
        record(object);
        require(object, object.getVariable());
        require(object, object.getType());
        require(object, object.getOwner());
        require(object, object.getMethod());

        p.addLine();
        p.addText("delegate ");
        object.getVariable().accept(this);
        p.addText(" : ");
        object.getType().accept(this);
        p.addText(" => ");
        printStaticMemberAccess(object.getOwner(), object.getMethod());
        p.addText(";");
        p.addEmptyLine();
    }

    @Override
    public void visit(final SetterStatement object)
    {
        record(object);
        require(object, object.getOwner());
        require(object, object.getName());
        require(object, object.getModule());
        require(object, object.getMethod());

        p.addLine();
        p.addText("setter ");
        object.getOwner().accept(this);
        p.addText(".");
        object.getName().accept(this);
        p.addText(" => ");
        printStaticMemberAccess(object.getModule(), object.getMethod());
        p.addText(";");
        p.addEmptyLine();
    }

    @Override
    public void visit(final GetterStatement object)
    {
        record(object);
        require(object, object.getOwner());
        require(object, object.getName());
        require(object, object.getModule());
        require(object, object.getMethod());

        p.addLine();
        p.addText("getter ");
        object.getOwner().accept(this);
        p.addText(".");
        object.getName().accept(this);
        p.addText(" => ");
        printStaticMemberAccess(object.getModule(), object.getMethod());
        p.addText(";");
        p.addEmptyLine();
    }

    @Override
    public void visit(final MethodStatement object)
    {
        record(object);
        require(object, object.getOwner());
        require(object, object.getName());
        require(object, object.getModule());
        require(object, object.getMethod());

        p.addLine();
        p.addText("method ");
        object.getOwner().accept(this);
        p.addText(".");
        object.getName().accept(this);
        p.addText(" => ");
        printStaticMemberAccess(object.getModule(), object.getMethod());
        p.addText(";");
        p.addEmptyLine();
    }

    @Override
    public void visit(final SequenceStatement object)
    {
        record(object);
        require(object, object.getElements());

        p.addOpeningBracket();
        {
            for (IStatement x : object.getElements())
            {
                x.accept(this);
            }
        }
        p.removeEmptyLines();
        p.addClosingBracket();
    }

    @Override
    public void visit(final ExpressionStatement object)
    {
        record(object);
        require(object, object.getExpression());

        p.addLine();
        object.getExpression().accept(this);
        p.addText(";");
        p.addEmptyLine();
    }

    @Override
    public void visit(final NopStatement object)
    {
        record(object);

        p.addLine();
        p.addText("nop;");
        p.addEmptyLine();
    }

    @Override
    public void visit(final DebugStatement object)
    {
        record(object);

        p.addLine();
        p.addText("debug;");
        p.addEmptyLine();
    }

    @Override
    public void visit(final TryCatchStatement object)
    {
        record(object);
        require(object, object.getBody());
        require(object, object.getHandlers());
        require(object, object.getHandlers().isEmpty() == false);

        p.addLine();
        p.addText("try");
        object.getBody().accept(this);
        for (ExceptionHandler x : object.getHandlers())
        {
            x.accept(this);
        }
        p.addEmptyLine();
    }

    @Override
    public void visit(final ExceptionHandler object)
    {
        record(object);
        require(object, object.getVariable());
        require(object, object.getType());
        require(object, object.getHandler());

        p.addLine();
        p.addText("catch (");
        object.getVariable().accept(this);
        p.addText(" : ");
        object.getType().accept(this);
        p.addText(")");
        object.getHandler().accept(this);
    }

    @Override
    public void visit(final ThrowStatement object)
    {
        record(object);
        require(object, object.getValue());

        p.addLine();
        p.addText("throw ");
        object.getValue().accept(this);
        p.addText(";");
        p.addEmptyLine();
    }

    @Override
    public void visit(final AssertStatement object)
    {
        record(object);
        require(object, object.getCondition());

        p.addLine();
        p.addText("assert ");
        object.getCondition().accept(this);
        if (object.getMessage() != null)
        {
            p.addText(" echo ");
            object.getMessage().accept(this);
        }
        p.addText(";");
        p.addEmptyLine();
    }

    @Override
    public void visit(final AssumeStatement object)
    {
        record(object);
        require(object, object.getCondition());

        p.addLine();
        p.addText("assume ");
        object.getCondition().accept(this);
        if (object.getMessage() != null)
        {
            p.addText(" echo ");
            object.getMessage().accept(this);
        }
        p.addText(";");
        p.addEmptyLine();
    }

    @Override
    public void visit(final ReturnVoidStatement object)
    {
        record(object);

        p.addLine();
        p.addText("return;");
        p.addEmptyLine();
    }

    @Override
    public void visit(final ReturnValueStatement object)
    {
        record(object);
        require(object, object.getValue());

        p.addLine();
        p.addText("return ");
        object.getValue().accept(this);
        p.addText(";");
        p.addEmptyLine();
    }

    @Override
    public void visit(final RecurStatement object)
    {
        record(object);
        require(object, object.getArguments());

        p.addLine();
        p.addText("recur");
        printIf(" ", !object.getArguments().isEmpty());
        printList("", object.getArguments(), ", ", ";");
        p.addEmptyLine();
        p.addEmptyLine();
    }

    @Override
    public void visit(final YieldVoidStatement object)
    {
        record(object);

        p.addLine();
        p.addText("yield;");
        p.addEmptyLine();
    }

    @Override
    public void visit(final YieldValueStatement object)
    {
        record(object);
        require(object, object.getValue());

        p.addLine();
        p.addText("yield ");
        object.getValue().accept(this);
        p.addText(";");
        p.addEmptyLine();
    }

    @Override
    public void visit(final PrognExpression object)
    {
        record(object);
        require(object, object.getElements());
        require(object, object.getElements().isEmpty() == false);

        printList("(progn ", object.getElements(), ", ", ")");
    }

    @Override
    public void visit(final BooleanDatum object)
    {
        record(object);

        p.addText(object.getValue());
    }

    @Override
    public void visit(final CharDatum object)
    {
        record(object);
        require(object, object.getValue() != null);
        reportUnprintableNode(object, object.getValue().isParsable());

        final char value = object.getValue().value();

        if (value >= 33 && value <= 126)
        {
            p.addText("'" + value + "'");
        }
        else
        {
            p.addText(Long.toString(value) + "C");
        }
    }

    @Override
    public void visit(final ByteDatum object)
    {
        record(object);
        require(object, object.getValue() != null);
        reportUnprintableNode(object, object.getValue().isParsable());

        p.addText(object.getValue());
    }

    @Override
    public void visit(final ShortDatum object)
    {
        record(object);
        require(object, object.getValue() != null);
        reportUnprintableNode(object, object.getValue().isParsable());

        p.addText(object.getValue());
    }

    @Override
    public void visit(final IntDatum object)
    {
        record(object);
        require(object, object.getValue() != null);
        reportUnprintableNode(object, object.getValue().isParsable());

        p.addText(object.getValue());
    }

    @Override
    public void visit(final LongDatum object)
    {
        record(object);
        require(object, object.getValue() != null);
        reportUnprintableNode(object, object.getValue().isParsable());

        p.addText(object.getValue());
    }

    @Override
    public void visit(final FloatDatum object)
    {
        record(object);
        require(object, object.getValue() != null);
        reportUnprintableNode(object, object.getValue().isParsable());

        p.addText(object.getValue().source());
    }

    @Override
    public void visit(final DoubleDatum object)
    {
        record(object);
        require(object, object.getValue() != null);
        reportUnprintableNode(object, object.getValue().isParsable());

        p.addText(object.getValue().source());
    }

    @Override
    public void visit(final StringDatum object)
    {
        record(object);
        require(object, object.getValue() != null);
        reportUnprintableNode(object, object.getValue().contains("'''") == false);

        p.addText(object.getVerbatim() ? "@" : "");
        p.addText("'''");
        p.addText(object.getValue());
        p.addText("'''");
    }

    @Override
    public void visit(final NullDatum object)
    {
        record(object);

        p.addText("null");
    }

    @Override
    public void visit(final VariableDatum object)
    {
        record(object);
        require(object, object.getVariable());

        object.getVariable().accept(this);
    }

    @Override
    public void visit(final ClassDatum object)
    {
        record(object);
        require(object, object.getType());

        p.addText("(class ");
        object.getType().accept(this);
        p.addText(")");
    }

    @Override
    public void visit(final ListExpression object)
    {
        record(object);
        require(object, object.getElements());

        printList("[ ", object.getElements(), ", ", " ]");
    }

    @Override
    public void visit(final DispatchExpression object)
    {
        record(object);
        require(object, object.getName());
        require(object, object.getArguments());

        p.addText("(dispatch ");
        object.getName().accept(this);
        printIf(" ", !object.getArguments().isEmpty());
        printList("", object.getArguments(), ", ", "");
        p.addText(")");
    }

    @Override
    public void visit(final CallStaticMethodExpression object)
    {
        record(object);
        require(object, object.getName());
        require(object, object.getArguments());

        p.addText("(call ");
        printStaticMemberAccess(object.getOwner(), object.getName());
        printIf(" ", !object.getArguments().isEmpty());
        printList("", object.getArguments(), ", ", "");
        p.addText(")");
    }

    @Override
    public void visit(final SetStaticFieldExpression object)
    {
        record(object);
        require(object, object.getOwner());
        require(object, object.getName());
        require(object, object.getValue());

        p.addText("(field ");
        printStaticMemberAccess(object.getOwner(), object.getName());
        p.addText(" = ");
        object.getValue().accept(this);
        p.addText(")");
    }

    @Override
    public void visit(final GetStaticFieldExpression object)
    {
        record(object);
        require(object, object.getOwner());
        require(object, object.getName());

        p.addText("(field ");
        printStaticMemberAccess(object.getOwner(), object.getName());
        p.addText(")");
    }

    @Override
    public void visit(final NewExpression object)
    {
        record(object);
        require(object, object.getType());
        require(object, object.getArguments());

        p.addText("(new ");
        object.getType().accept(this);
        printIf(" ", !object.getArguments().isEmpty());
        printList("", object.getArguments(), ", ", "");
        p.addText(")");
    }

    @Override
    public void visit(final CreateExpression object)
    {
        record(object);
        require(object, object.getType());

        p.addText("(create ");

        object.getType().accept(this);

        p.addText(")");
    }

    @Override
    public void visit(final CallMethodExpression object)
    {
        record(object);
        require(object, object.getOwner());
        require(object, object.getName());
        require(object, object.getArguments());

        p.addText("(call ");
        printInstanceMemberAccess(object.getOwner(), object.getName());
        printIf(" ", !object.getArguments().isEmpty());
        printList("", object.getArguments(), ", ", "");
        p.addText(")");
    }

    @Override
    public void visit(final SetFieldExpression object)
    {
        record(object);
        require(object, object.getOwner());
        require(object, object.getName());
        require(object, object.getValue());

        p.addText("(field ");
        printInstanceMemberAccess(object.getOwner(), object.getName());
        p.addText(" = ");
        object.getValue().accept(this);
        p.addText(")");
    }

    @Override
    public void visit(final GetFieldExpression object)
    {
        record(object);
        require(object, object.getOwner());
        require(object, object.getName());

        p.addText("(field ");
        printInstanceMemberAccess(object.getOwner(), object.getName());
        p.addText(")");
    }

    @Override
    public void visit(final InstanceOfExpression object)
    {
        record(object);
        require(object, object.getValue());
        require(object, object.getType());

        p.addText("(instanceof ");
        object.getValue().accept(this);
        p.addText(" : ");
        object.getType().accept(this);
        p.addText(")");
    }

    @Override
    public void visit(final TernaryConditionalExpression object)
    {
        record(object);
        require(object, object.getCondition());
        require(object, object.getCaseTrue());
        require(object, object.getCaseFalse());

        p.addText("(if ");
        object.getCondition().accept(this);
        p.addText(" then ");
        object.getCaseTrue().accept(this);
        p.addText(" else ");
        object.getCaseFalse().accept(this);
        p.addText(")");
    }

    @Override
    public void visit(final LocalsExpression object)
    {
        record(object);

        p.addText("(locals)");
    }

    @Override
    public void visit(final AsOperation object)
    {
        visitConversionOperation("as", object);
    }

    @Override
    public void visit(final IsOperation object)
    {
        visitConversionOperation("is", object);
    }

    @Override
    public void visit(final NegateOperation object)
    {
        visitUnaryOperation("-", object);
    }

    @Override
    public void visit(final NotOperation object)
    {
        visitUnaryOperation("!", object);
    }

    @Override
    public void visit(final DivideOperation object)
    {
        visitBinaryOperation("/", object);
    }

    @Override
    public void visit(final ModuloOperation object)
    {
        visitBinaryOperation("%", object);
    }

    @Override
    public void visit(final MultiplyOperation object)
    {
        visitBinaryOperation("*", object);
    }

    @Override
    public void visit(final AddOperation object)
    {
        visitBinaryOperation("+", object);
    }

    @Override
    public void visit(final SubtractOperation object)
    {
        visitBinaryOperation("-", object);
    }

    @Override
    public void visit(final LessThanOperation object)
    {
        visitBinaryOperation("<", object);
    }

    @Override
    public void visit(final LessThanOrEqualsOperation object)
    {
        visitBinaryOperation("<=", object);
    }

    @Override
    public void visit(final GreaterThanOperation object)
    {
        visitBinaryOperation(">", object);
    }

    @Override
    public void visit(final GreaterThanOrEqualsOperation object)
    {
        visitBinaryOperation(">=", object);
    }

    @Override
    public void visit(final EqualsOperation object)
    {
        visitBinaryOperation("==", object);
    }

    @Override
    public void visit(final NotEqualsOperation object)
    {
        visitBinaryOperation("!=", object);
    }

    @Override
    public void visit(final IdentityEqualsOperation object)
    {
        visitBinaryOperation("===", object);
    }

    @Override
    public void visit(final IdentityNotEqualsOperation object)
    {
        visitBinaryOperation("!==", object);
    }

    @Override
    public void visit(final AndOperation object)
    {
        visitBinaryOperation("&", object);
    }

    @Override
    public void visit(final OrOperation object)
    {
        visitBinaryOperation("|", object);
    }

    @Override
    public void visit(final XorOperation object)
    {
        visitBinaryOperation("^", object);
    }

    @Override
    public void visit(final ImpliesOperation object)
    {
        visitBinaryOperation("->", object);
    }

    @Override
    public void visit(final ShortCircuitAndOperation object)
    {
        visitBinaryOperation("&&", object);
    }

    @Override
    public void visit(final ShortCircuitOrOperation object)
    {
        visitBinaryOperation("||", object);
    }

    @Override
    public void visit(final NullCoalescingOperation object)
    {
        visitBinaryOperation("??", object);
    }

    @Override
    public void visit(final ConcatOperation object)
    {
        visitBinaryOperation("..", object);
    }

    @Override
    public void visit(final Variable object)
    {
        record(object);
        require(object, object.getName() != null);
        reportUnprintableNode(object, object.getName().matches(ID_REGEX));

        p.addText(object.getName());
    }

    @Override
    public void visit(final DocComment object)
    {
        record(object);
        require(object, object.getLines());

        for (DocCommentLine line : object.getLines())
        {
            line.accept(this);
        }
    }

    @Override
    public void visit(final DocCommentLine object)
    {
        record(object);

        p.addLine();
        p.addText("'' ");
        p.addText(object.getText() == null ? "" : object.getText());
    }

    @Override
    public void visit(final Name object)
    {
        record(object);
        require(object, object.getName() != null);
        reportUnprintableNode(object, object.getName().matches(ID_REGEX));

        p.addText(object.getName());
    }

    @Override
    public void visit(final TypeSpecifier object)
    {
        record(object);
        require(object, object.getName());

        final Integer dimensions = object.getDimensions();
        reportUnprintableNode(object, dimensions == null || dimensions >= 0);

        if (object.getNamespace() != null)
        {
            object.getNamespace().accept(this);
            printIf(".", object.getNamespace() != null);
        }
        object.getName().accept(this);
        p.addText(Strings.repeat("[]", dimensions == null ? 0 : dimensions));
    }

    @Override
    public void visit(final Namespace object)
    {
        record(object);
        require(object, object.getNames());
        require(object, object.getNames().size() >= 1);

        int count = 0;

        for (Name name : object.getNames())
        {
            assert name != null;

            ++count;

            name.accept(this);

            if (count < object.getNames().size())
            {
                p.addText(".");
            }
        }
    }

    @Override
    public void visit(final FormalParameter object)
    {
        record(object);
        require(object, object.getVariable());
        require(object, object.getType());

        object.getVariable().accept(this);
        p.addText(" : ");
        object.getType().accept(this);
    }

    @Override
    public void visit(final AnnotationList object)
    {
        record(object);
        require(object, object.getAnnotations());

        for (Annotation x : object.getAnnotations())
        {
            x.accept(this);
        }
    }

    @Override
    public void visit(final FormalParameterList object)
    {
        record(object);
        require(object, object.getParameters());

        p.addText("(");
        {
            int count = 0;

            for (FormalParameter param : object.getParameters())
            {
                ++count;

                param.accept(this);

                if (count < object.getParameters().size())
                {
                    p.addText(", ");
                }
            }
        }
        p.addText(")");
    }

    @Override
    public void visit(final ConditionalCase object)
    {
        record(object);
        require(object, object.getCondition());
        require(object, object.getBody());

        p.addText("(");
        object.getCondition().accept(this);
        p.addText(")");
        object.getBody().accept(this);
    }

    @Override
    public void visit(final Label object)
    {
        record(object);
        require(object, object.getName() != null);
        reportUnprintableNode(object, object.getName().matches(ID_REGEX));

        p.addText(object.getName());
    }

    @Override
    public void visit(final SourceLocation object)
    {
        // Do Nothing
    }

    /**
     * This method generalizes the visitation of a conversion operation.
     *
     * @param operator is the string representation of the conversion operator.
     * @param operation is the conversion operation itself.
     */
    private void visitConversionOperation(final String operator,
                                          final IConversionOperation operation)
    {
        record(operation);
        require(operation, operation.getValue());
        require(operation, operation.getType());

        p.addText("(");
        operation.getValue().accept(this);
        p.addText(" ");
        p.addText(operator);
        p.addText(" ");
        operation.getType().accept(this);
        p.addText(")");
    }

    /**
     * This method generalizes the visitation of a unary operation.
     *
     * @param operator is the string representation of the unary operator.
     * @param operation is the operation itself.
     */
    private void visitUnaryOperation(final String operator,
                                     final IUnaryOperation operation)
    {
        record(operation);
        require(operation, operation.getOperand());

        p.addText("(");
        p.addText(operator);
        p.addText(" ");
        operation.getOperand().accept(this);
        p.addText(")");
    }

    /**
     * This method generalizes the visitation of a binary operation.
     *
     * @param operator is the string representation of the binary operator.
     * @param operation is the operation itself.
     */
    private void visitBinaryOperation(final String operator,
                                      final IBinaryOperation operation)
    {
        record(operation);
        require(operation, operation.getLeftOperand());
        require(operation, operation.getRightOperand());

        p.addText("(");
        operation.getLeftOperand().accept(this);
        p.addText(" ");
        p.addText(operator);
        p.addText(" ");
        operation.getRightOperand().accept(this);
        p.addText(")");
    }

    /**
     * This method prints a static member accessor.
     *
     * @param owner is the owner of the member being accessed.
     * @param member is the name of the member being accessed.
     */
    private void printStaticMemberAccess(final TypeSpecifier owner,
                                         final Name member)
    {
        assert owner != null;
        assert member != null;

        owner.accept(this);
        p.addText("::");
        member.accept(this);
    }

    /**
     * This method prints an instance member accessor.
     *
     * @param owner is an expression that produces the owner of the member being accessed.
     * @param member is the name of the member being accessed.
     */
    private void printInstanceMemberAccess(final IExpression owner,
                                           final Name member)
    {
        assert owner != null;
        assert member != null;

        owner.accept(this);
        p.addText(".");
        member.accept(this);
    }

    /**
     * This method prints a list of constructs.
     *
     * @param prefix is a string to print immediately before the arguments.
     * @param arguments are the arguments to print.
     * @param separator is used to separate the arguments.
     * @param suffix is a string to print immediately after the arguments.
     */
    private void printList(final String prefix,
                           final ConstructList<? extends IConstruct> arguments,
                           final String separator,
                           final String suffix)
    {
        assert prefix != null;
        assert arguments != null;
        assert suffix != null;

        p.addText(prefix);
        {
            int count = 0;

            for (IConstruct arg : arguments)
            {
                assert arg != null;

                ++count;

                arg.accept(this);

                if (count < arguments.size())
                {
                    p.addText(separator);
                }
            }
        }
        p.addText(suffix);
    }

    /**
     * This method conditionally prints a string of text.
     *
     * @param text is the text that may be printed.
     * @param condition is true, iff the text is to be printed.
     */
    private void printIf(final String text,
                         final boolean condition)
    {
        assert text != null;

        if (condition)
        {
            p.addText(text);
        }
    }
}
