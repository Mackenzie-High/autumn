package autumn.lang.compiler;

import autumn.lang.compiler.ast.commons.*;
import autumn.lang.compiler.ast.literals.BigDecimalLiteral;
import autumn.lang.compiler.ast.literals.BigIntegerLiteral;
import autumn.lang.compiler.ast.literals.ByteLiteral;
import autumn.lang.compiler.ast.literals.CharLiteral;
import autumn.lang.compiler.ast.literals.DoubleLiteral;
import autumn.lang.compiler.ast.literals.FloatLiteral;
import autumn.lang.compiler.ast.literals.IntLiteral;
import autumn.lang.compiler.ast.literals.LongLiteral;
import autumn.lang.compiler.ast.literals.ShortLiteral;
import autumn.lang.compiler.ast.nodes.*;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import high.mackenzie.autumn.resources.Finished;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Use an instance of this class when building the abstract-syntax-tree of a module.
 *
 * <p>
 * An instance of this class can be thought of as a stack-machine.
 * Calling methods, such as createDatum(String), causes leaf AST nodes to be pushed onto the stack.
 * Calling methods, such as createSequenceStatement(), causes an inner AST node to be created.
 * Using these different types of methods in combination facilitates the construction of an AST.
 * </p>
 *
 * <p>
 * This stack-machine employs a primary-stack and a stack-of-stacks.
 * The primary stack is the stack that is manipulated when building an AST node.
 * Sometimes it is necessary to limit the scope of a node construction operation.
 * As a result, it is possible to push the current primary-stack onto the stack-of-stacks.
 * When the primary-stack is pushed onto the stack-of-stacks, a new stack will replace it.
 * Later, the old primary-stack can be restored by popping it off of the stack-of-stacks.
 * </p>
 *
 * <p>
 * Some methods herein denote the bottom element on the primary-stack as ".....".
 * This means that the method only manipulates the topmost element(s) on the stack.
 * The elements below the manipulated elements will not be popped, relocated, etc.
 * On the other hand, some methods do not denote the bottom element as ".....".
 * These methods may manipulate the entire primary-stack.
 * </p>
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public final class TreeBuilder
{
    private Stack<IConstruct> stack = new Stack<IConstruct>();

    private final Stack<Stack<IConstruct>> stack_of_stacks = new Stack<Stack<IConstruct>>();

    /**
     * Sole Constructor.
     */
    public TreeBuilder()
    {
        // Do Nothing.
    }

    /**
     * This method sets the source-location information for the topmost node on the stack.
     *
     * <p>
     * This method has no effect, if the stack is empty or the topmost node is null.
     * </p>
     *
     * @param file is the path to the source-code file.
     * This is null, if no file is specified.
     * @param line is the one-based line-number where the node starts.
     * This is null, if no line-number is specified.
     * @param column is the one-based column-number where the node starts.
     * This is null, if the column-number is specified.
     * @param additional_info is additional information that helps locate the node.
     * This is null, if no additional-info is specified.
     */
    public void setSourceLocation(final URL file,
                                  final Integer line,
                                  final Integer column,
                                  final Object additional_info)
    {
        Preconditions.checkArgument(line == null || line >= 1);
        Preconditions.checkArgument(column == null || column >= 1);

        if (stack.isEmpty() || stack.peek() == null)
        {
            return;
        }

        // Create the location token.
        SourceLocation location = new SourceLocation();
        location = location.setFile(file);
        location = location.setLine(line);
        location = location.setColumn(column);
        location = location.setAdditionalInfo(additional_info);

        // Apply the location to the AST node.
        IConstruct node = stack.pop();
        node = node.setLocation(location);

        stack.push(node);
    }

    /**
     * This method pushes the primary-stack onto the stack-of-stacks.
     */
    public void pushStack()
    {
        stack_of_stacks.push(stack);

        stack = new Stack<IConstruct>();
    }

    /**
     * This method restores the previous stack, by popping it off of the
     * stack-of-stacks.
     *
     * <p>
     * Note: If the current stack is not empty, then its elements will be pushed onto
     * the restored stack.
     * </p>
     *
     * <p>
     * <b>Warning:</b> Do not confuse this method with pop().
     * </p>
     */
    public void popStack()
    {
        final Stack<IConstruct> current = stack;

        stack = stack_of_stacks.pop();

        stack.addAll(current);
    }

    /**
     * This method pushes null onto the primary-stack.
     */
    public void pushNull()
    {
        stack.push(null);
    }

    /**
     * This method pushes a construct onto the primary-stack.
     *
     * @param value is the value to push onto the stack.
     */
    public void push(final IConstruct value)
    {
        stack.push(value);
    }

    /**
     * This method retrieves, but does not remove, the topmost value on the primary-stack.
     *
     * @return the topmost value on the stack.
     */
    public IConstruct peek()
    {
        return stack.peek();
    }

    /**
     * This method retrieves and removes the topmost value on the primary-stack.
     *
     * @return the topmost value on the stack.
     */
    public IConstruct pop()
    {
        return stack.pop();
    }

    /**
     * This method creates and returns a copy of the primary-stack.
     *
     * @return a copy of the stack.
     */
    public Stack<IConstruct> copyStack()
    {
        final Stack<IConstruct> result = new Stack<IConstruct>();

        result.addAll(stack);

        return result;
    }

    /**
     * This method determines whether the primary-stack is empty.
     *
     * @return true, iff the stack is empty.
     */
    public boolean isEmpty()
    {
        return stack.isEmpty();
    }

    /**
     * This method retrieves the current size of the primary-stack.
     *
     * @return the number of values on the stack.
     */
    public int size()
    {
        return stack.size();
    }

    /**
     * This method removes all the elements off of the primary-stack.
     */
    public void clear()
    {
        stack.clear();
    }

    /**
     * This method creates a module.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> module-member[n] </li>
     * <li> module-member[2] </li>
     * <li> module-member[1] </li>
     * <li> module-member[0] </li>
     * </ul>
     * </p>
     *
     * <p>
     * A module-member is one of:
     * <ul>
     * <li> ModuleDirective </li>
     * <li> ImportDirective </li>
     * <li> AnnotationDefinition </li>
     * <li> ExceptionDefinition </li>
     * <li> FunctorDefinition </li>
     * <li> EnumDefinition </li>
     * <li> DesignDefinition </li>
     * <li> StructDefinition </li>
     * <li> TupleDefinition </li>
     * <li> FunctionDefinition </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : Module </li>
     * </ul>
     * </p>
     */
    public void createModule()
    {
        final LinkedList<ModuleDirective> module_directives = Lists.newLinkedList();

        final LinkedList<ImportDirective> import_directives = Lists.newLinkedList();

        final LinkedList<AnnotationDefinition> annotation_definitions = Lists.newLinkedList();

        final LinkedList<ExceptionDefinition> exception_definitions = Lists.newLinkedList();

        final LinkedList<DesignDefinition> design_definitions = Lists.newLinkedList();

        final LinkedList<StructDefinition> struct_definitions = Lists.newLinkedList();

        final LinkedList<TupleDefinition> tuple_definitions = Lists.newLinkedList();

        final LinkedList<FunctorDefinition> functor_definitions = Lists.newLinkedList();

        final LinkedList<EnumDefinition> enum_definitions = Lists.newLinkedList();

        final LinkedList<FunctionDefinition> function_definitions = Lists.newLinkedList();

        // Pop the pieces off of the stack.
        while (stack.isEmpty() == false)
        {
            final IConstruct member = stack.pop();

            if (member instanceof ModuleDirective)
            {
                final ModuleDirective directive = (ModuleDirective) member;

                module_directives.addFirst(directive);
            }
            else if (member instanceof ImportDirective)
            {
                final ImportDirective directive = (ImportDirective) member;

                import_directives.addFirst(directive);
            }
            else if (member instanceof AnnotationDefinition)
            {
                final AnnotationDefinition definition = (AnnotationDefinition) member;

                annotation_definitions.addFirst(definition);
            }
            else if (member instanceof ExceptionDefinition)
            {
                final ExceptionDefinition definition = (ExceptionDefinition) member;

                exception_definitions.addFirst(definition);
            }
            else if (member instanceof DesignDefinition)
            {
                final DesignDefinition definition = (DesignDefinition) member;

                design_definitions.addFirst(definition);
            }
            else if (member instanceof StructDefinition)
            {
                final StructDefinition definition = (StructDefinition) member;

                struct_definitions.addFirst(definition);
            }
            else if (member instanceof TupleDefinition)
            {
                final TupleDefinition definition = (TupleDefinition) member;

                tuple_definitions.addFirst(definition);
            }
            else if (member instanceof FunctorDefinition)
            {
                final FunctorDefinition definition = (FunctorDefinition) member;

                functor_definitions.addFirst(definition);
            }
            else if (member instanceof EnumDefinition)
            {
                final EnumDefinition definition = (EnumDefinition) member;

                enum_definitions.addFirst(definition);
            }
            else if (member instanceof FunctionDefinition)
            {
                final FunctionDefinition definition = (FunctionDefinition) member;

                function_definitions.addFirst(definition);
            }
        }

        // Create the AST node.
        Module module = new Module();

        // Initialize the AST node.
        module = module.setModuleDirectives((new ConstructList()).addAll(module_directives));
        module = module.setImportDirectives((new ConstructList()).addAll(import_directives));

        module = module.setAnnotations((new ConstructList()).addAll(annotation_definitions));
        module = module.setExceptions((new ConstructList()).addAll(exception_definitions));
        module = module.setDesigns((new ConstructList()).addAll(design_definitions));
        module = module.setStructs((new ConstructList()).addAll(struct_definitions));
        module = module.setTuples((new ConstructList()).addAll(tuple_definitions));
        module = module.setFunctors((new ConstructList()).addAll(functor_definitions));
        module = module.setEnums((new ConstructList()).addAll(enum_definitions));
        module = module.setFunctions((new ConstructList()).addAll(function_definitions));

        // Push the AST node onto the stack.
        stack.push(module);

        assert stack.size() == 1;
    }

    /**
     * This method creates a module-directive.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> namespace : Namespace </li>
     * <li> name : Name </li>
     * <li> annotations : AnnotationList </li>
     * <li> comment : DocComment </li>
     * </ul>
     * </p>
     *
     * <p>
     * The name must be null, if the module is anonymous.
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : ModuleDirective </li>
     * </ul>
     * </p>
     */
    public void createDirectiveModule()
    {
        Preconditions.checkState(stack.size() == 4);

        // Get the pieces off of the stack.
        final Namespace namespace = (Namespace) stack.pop();
        final Name name = (Name) stack.pop();
        final AnnotationList annotations = (AnnotationList) stack.pop();
        final DocComment comment = (DocComment) stack.pop();

        // Create the AST node.
        ModuleDirective node = new ModuleDirective();

        // Initialize the AST node.
        node = node.setComment(comment);
        node = node.setAnnotations(annotations);
        node = node.setName(name);
        node = node.setNamespace(namespace);

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates an import-directive.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> type : TypeSpecifier </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : ImportDirective </li>
     * </ul>
     * </p>
     */
    public void createDirectiveImport()
    {
        Preconditions.checkState(stack.size() == 1);

        // Get the pieces off of the stack.
        final TypeSpecifier type = (TypeSpecifier) stack.pop();

        // Create the AST node.
        ImportDirective node = new ImportDirective();

        // Initialize the AST node.
        node = node.setType(type);

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates an annotation-definition.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> name : Name </li>
     * <li> annotations : AnnotationList </li>
     * <li> comment : DocComment </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : AnnotationDefinition </li>
     * </ul>
     * </p>
     */
    public void createDefinitionAnnotation()
    {
        Preconditions.checkState(stack.size() == 3);

        // Get the pieces off of the stack.
        final Name name = (Name) stack.pop();
        final AnnotationList annotations = (AnnotationList) stack.pop();
        final DocComment comment = (DocComment) stack.pop();

        // Create the AST node.
        AnnotationDefinition node = new AnnotationDefinition();

        // Initialize the AST node.
        node = node.setComment(comment);
        node = node.setAnnotations(annotations);
        node = node.setName(name);

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates an exception-definition.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> superclass : TypeSpecifier </li>
     * <li> name : Name </li>
     * <li> annotations : AnnotationList </li>
     * <li> comment : DocComment </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : ExceptionDefinition </li>
     * </ul>
     * </p>
     */
    public void createDefinitionException()
    {
        Preconditions.checkState(stack.size() == 4);

        // Get the pieces off of the stack.
        final TypeSpecifier superclass = (TypeSpecifier) stack.pop();
        final Name name = (Name) stack.pop();
        final AnnotationList annotations = (AnnotationList) stack.pop();
        final DocComment comment = (DocComment) stack.pop();

        // Create the AST node.
        ExceptionDefinition node = new ExceptionDefinition();

        // Initialize the AST node.
        node = node.setComment(comment);
        node = node.setAnnotations(annotations);
        node = node.setName(name);
        node = node.setSuperclass(superclass);

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates an design-definition.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> supertype[n] : TypeSpecifier </li>
     * <li> supertype[.] : TypeSpecifier </li>
     * <li> supertype[2] : TypeSpecifier </li>
     * <li> supertype[1] : TypeSpecifier </li>
     * <li> supertype[0] : TypeSpecifier </li>
     * <li> elements : ElementList </li>
     * <li> name : Name </li>
     * <li> annotations : AnnotationList </li>
     * <li> comment : DocComment </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : DesignDefinition </li>
     * </ul>
     * </p>
     */
    public void createDefinitionDesign()
    {
        Preconditions.checkState(stack.size() >= 4);

        // Get the pieces off of the stack.

        final LinkedList<TypeSpecifier> supers = Lists.newLinkedList();

        while (stack.size() > 4)
        {
            final IConstruct x = stack.pop();

            supers.add(0, (TypeSpecifier) x);
        }

        final ElementList elements = (ElementList) stack.pop();

        final Name name = (Name) stack.pop();

        final AnnotationList annotations = (AnnotationList) stack.pop();

        final DocComment comment = (DocComment) stack.pop();

        // Create the AST node.
        DesignDefinition node = new DesignDefinition();

        // Initialize the AST node.
        node = node.setComment(comment);
        node = node.setAnnotations(annotations);
        node = node.setName(name);
        node = node.setSupers((new ConstructList<TypeSpecifier>()).addAll(supers));
        node = node.setElements(elements);

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates an tuple-definition.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> supertype[n] : TypeSpecifier </li>
     * <li> supertype[.] : TypeSpecifier </li>
     * <li> supertype[2] : TypeSpecifier </li>
     * <li> supertype[1] : TypeSpecifier </li>
     * <li> supertype[0] : TypeSpecifier </li>
     * <li> elements : ElementList </li>
     * <li> name : Name </li>
     * <li> annotations : AnnotationList </li>
     * <li> comment : DocComment </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : TupleDefinition </li>
     * </ul>
     * </p>
     */
    public void createDefinitionTuple()
    {
        Preconditions.checkState(stack.size() >= 4);

        // Get the pieces off of the stack.

        final LinkedList<TypeSpecifier> supers = Lists.newLinkedList();

        while (stack.size() > 4)
        {
            final IConstruct x = stack.pop();

            supers.add(0, (TypeSpecifier) x);
        }

        final ElementList elements = (ElementList) stack.pop();

        final Name name = (Name) stack.pop();

        final AnnotationList annotations = (AnnotationList) stack.pop();

        final DocComment comment = (DocComment) stack.pop();

        // Create the AST node.
        TupleDefinition node = new TupleDefinition();

        // Initialize the AST node.
        node = node.setComment(comment);
        node = node.setAnnotations(annotations);
        node = node.setName(name);
        node = node.setSupers((new ConstructList<TypeSpecifier>()).addAll(supers));
        node = node.setElements(elements);

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates a struct-definition.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> supertype[m] : TypeSpecifier </li>
     * <li> supertype[.] : TypeSpecifier </li>
     * <li> supertype[2] : TypeSpecifier </li>
     * <li> supertype[1] : TypeSpecifier </li>
     * <li> supertype[0] : TypeSpecifier </li>
     * <li> elements : ElementList </li>
     * <li> name : Name </li>
     * <li> annotations : AnnotationList </li>
     * <li> comment : DocComment </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : StructDefinition </li>
     * </ul>
     * </p>
     */
    public void createDefinitionStruct()
    {
        Preconditions.checkState(stack.size() >= 4);

        // Get the pieces off of the stack.

        final LinkedList<TypeSpecifier> supers = Lists.newLinkedList();

        while (stack.size() > 4)
        {
            final IConstruct x = stack.pop();

            supers.add(0, (TypeSpecifier) x);
        }

        final ElementList elements = (ElementList) stack.pop();

        final Name name = (Name) stack.pop();

        final AnnotationList annotations = (AnnotationList) stack.pop();

        final DocComment comment = (DocComment) stack.pop();

        // Create the AST node.
        StructDefinition node = new StructDefinition();

        // Initialize the AST node.
        node = node.setComment(comment);
        node = node.setAnnotations(annotations);
        node = node.setName(name);
        node = node.setSupers((new ConstructList<TypeSpecifier>()).addAll(supers));
        node = node.setElements(elements);

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates an functor-definition.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> superclass : TypeSpecifier </li>
     * <li> return-type : TypeSpecifier </li>
     * <li> parameters : FormalParameterList </li>
     * <li> name : Name </li>
     * <li> annotations : AnnotationList </li>
     * <li> comment : DocComment </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : FunctorDefinition </li>
     * </ul>
     * </p>
     */
    public void createDefinitionFunctor()
    {
        Preconditions.checkState(stack.size() == 5 || stack.size() == 6);

        // Get the pieces off of the stack.
        final TypeSpecifier superclass = (TypeSpecifier) (stack.size() == 6 ? stack.pop() : null);
        final TypeSpecifier returns = (TypeSpecifier) stack.pop();
        final FormalParameterList parameters = (FormalParameterList) stack.pop();
        final Name name = (Name) stack.pop();
        final AnnotationList annotations = (AnnotationList) stack.pop();
        final DocComment comment = (DocComment) stack.pop();

        // Create the AST node.
        FunctorDefinition node = new FunctorDefinition();

        // Initialize the AST node.
        node = node.setComment(comment);
        node = node.setAnnotations(annotations);
        node = node.setName(name);
        node = node.setParameters(parameters);
        node = node.setReturnType(returns);
        node = node.setSuperclass(superclass);

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates an enum-definition.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> constant[n] : Name </li>
     * <li> constant[2] : Name </li>
     * <li> constant[1] : Name </li>
     * <li> constant[0] : Name </li>
     * <li> name : Name </li>
     * <li> annotations : AnnotationList </li>
     * <li> comment : DocComment </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : EnumDefinition </li>
     * </ul>
     * </p>
     */
    public void createDefinitionEnum()
    {
        Preconditions.checkState(stack.size() >= 3);

        // Get the pieces off of the stack.

        final LinkedList<Name> constants = Lists.newLinkedList();

        while (stack.size() > 3)
        {
            constants.add(0, (Name) stack.pop());
        }

        final Name name = (Name) stack.pop();

        final AnnotationList annotations = (AnnotationList) stack.pop();

        final DocComment comment = (DocComment) stack.pop();

        // Create the AST node.
        EnumDefinition node = new EnumDefinition();

        // Initialize the AST node.
        node = node.setComment(comment);
        node = node.setAnnotations(annotations);
        node = node.setName(name);
        node = node.setConstants((new ConstructList<Name>()).addAll(constants));

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates a function-definition.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> body : SequenceStatement </li>
     * <li> return-type : TypeSpecifier </li>
     * <li> parameters : FormalParameterList </li>
     * <li> name : Name </li>
     * <li> annotations : AnnotationList </li>
     * <li> comment : DocComment </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : FunctionDefinition </li>
     * </ul>
     * </p>
     */
    public void createDefinitionFunction()
    {
        Preconditions.checkState(stack.size() == 6);

        // Pop the pieces off of the stack.
        final SequenceStatement body = (SequenceStatement) stack.pop();
        final TypeSpecifier return_type = (TypeSpecifier) stack.pop();
        final FormalParameterList parameters = (FormalParameterList) stack.pop();
        final Name name = (Name) stack.pop();
        final AnnotationList annotations = (AnnotationList) stack.pop();
        final DocComment comment = (DocComment) stack.pop();

        // Create the AST node.
        FunctionDefinition function = new FunctionDefinition();
        function = function.setComment(comment);
        function = function.setAnnotations(annotations);
        function = function.setName(name);
        function = function.setParameters(parameters);
        function = function.setReturnType(return_type);
        function = function.setBody(body);

        // Push the AST node onto the stack.
        stack.push(function);

        assert stack.size() == 1;
    }

    /**
     * This method creates an if-statement.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> else-case : SequenceStatement </li>
     * <li> elif-case[n] : ConditionCase </li>
     * <li> elif-case[2] : ConditionCase </li>
     * <li> elif-case[1] : ConditionCase </li>
     * <li> elif-case[0] : ConditionCase </li>
     * <li> main-case : ConditionCase </li>
     * </ul>
     * </p>
     *
     * <p>
     * Note: The elif-cases and the else-case are optional.
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : IfStatement </li>
     * </ul>
     * </p>
     */
    public void createStatementIf()
    {
        Preconditions.checkState(stack.size() >= 1);

        // Get the pieces off of the stack.

        final boolean has_else = stack.peek() instanceof SequenceStatement;

        final SequenceStatement else_case = (SequenceStatement) (has_else ? stack.pop() : null);

        final LinkedList<ConditionalCase> elif_cases = Lists.newLinkedList();

        while (stack.size() > 1)
        {
            elif_cases.add(0, (ConditionalCase) stack.pop());
        }

        final ConditionalCase main_case = (ConditionalCase) stack.pop();

        // Create the AST node.
        IfStatement node = new IfStatement();
        node = node.setMainCase(main_case);
        node = node.setElifCases((new ConstructList()).addAll(elif_cases));
        node = node.setElseCase(else_case);

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates a goto-statement.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> label : Label </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : GotoStatement </li>
     * </ul>
     * </p>
     */
    public void createStatementGoto()
    {
        Preconditions.checkState(stack.size() == 1);

        // Create the AST node.
        GotoStatement node = new GotoStatement();

        // Initialize the AST node.
        node = node.setLabel((Label) stack.pop());

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates a marker-statement.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> label : Label </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : MarkerStatement </li>
     * </ul>
     * </p>
     */
    public void createStatementMarker()
    {
        Preconditions.checkState(stack.size() == 1);

        // Create the AST node.
        MarkerStatement node = new MarkerStatement();

        // Initialize the AST node.
        node = node.setLabel((Label) stack.pop());

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates a branch-statement.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> default-label : Label </li>
     * <li> label[n] : Label </li>
     * <li> label[.] : Label </li>
     * <li> label[3] : Label </li>
     * <li> label[2] : Label </li>
     * <li> label[1] : Label </li>
     * <li> label[0] : Label </li>
     * <li> index : IExpression </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : BranchStatement </li>
     * </ul>
     * </p>
     */
    public void createStatementBranch()
    {
        Preconditions.checkState(stack.size() >= 2);

        // Get the pieces off fo the stack.
        final Label default_label = (Label) stack.pop();

        final LinkedList<Label> labels = Lists.newLinkedList();

        while (stack.size() > 1)
        {
            labels.addFirst((Label) stack.pop());
        }

        final IExpression index = (IExpression) stack.pop();

        // Create the AST node.
        BranchStatement node = new BranchStatement();

        // Initialize the AST node.
        node = node.setIndex(index);
        node = node.setLabels(new ConstructList(labels));
        node = node.setDefaultLabel(default_label);

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates a when-statement.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> body : SequenceStatement </li>
     * <li> condition : IExpression </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : WhenStatement </li>
     * </ul>
     * </p>
     */
    public void createStatementWhen()
    {
        Preconditions.checkState(stack.size() == 2);

        // Create the AST node.
        WhenStatement node = new WhenStatement();

        // Initialize the AST node.
        node = node.setBody((IStatement) stack.pop());
        node = node.setCondition((IExpression) stack.pop());

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates a forever-loop statement.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> body : SequenceStatement </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : ForeverStatement </li>
     * </ul>
     * </p>
     */
    public void createStatementForever()
    {
        Preconditions.checkState(stack.size() == 1);

        // Create the AST node.
        ForeverStatement node = new ForeverStatement();

        // Initialize the AST node.
        node = node.setBody((SequenceStatement) stack.pop());

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates a do-while-loop statement.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> condition : IExpression </li>
     * <li> body : SequenceStatement </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : DoWhileStatement </li>
     * </ul>
     * </p>
     */
    public void createStatementDoWhile()
    {
        Preconditions.checkState(stack.size() == 2);

        // Create the AST node.
        DoWhileStatement node = new DoWhileStatement();

        // Initialize the AST node.
        node = node.setCondition((IExpression) stack.pop());
        node = node.setBody((SequenceStatement) stack.pop());

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates a do-until-loop statement.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> condition : IExpression </li>
     * <li> body : SequenceStatement </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : DoUntilStatement </li>
     * </ul>
     * </p>
     */
    public void createStatementDoUntil()
    {
        Preconditions.checkState(stack.size() == 2);

        // Create the AST node.
        DoUntilStatement node = new DoUntilStatement();

        // Initialize the AST node.
        node = node.setCondition((IExpression) stack.pop());
        node = node.setBody((SequenceStatement) stack.pop());

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates a break-statement.
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : BreakStatement </li>
     * </ul>
     * </p>
     */
    public void createStatementBreak()
    {
        Preconditions.checkState(stack.isEmpty());

        // Create the AST node.
        BreakStatement node = new BreakStatement();

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates a continue-statement.
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : ContinueStatement </li>
     * </ul>
     * </p>
     */
    public void createStatementContinue()
    {
        Preconditions.checkState(stack.isEmpty());

        // Create the AST node.
        ContinueStatement node = new ContinueStatement();

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates a redo-statement.
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : RedoStatement </li>
     * </ul>
     * </p>
     */
    public void createStatementRedo()
    {
        Preconditions.checkState(stack.isEmpty());

        // Create the AST node.
        RedoStatement node = new RedoStatement();

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates a foreach-statement.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> body : SequenceStatement </li>
     * <li> iterable : IExpression </li>
     * <li> type : TypeSpecifier </li>
     * <li> variable : Variable </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : ForeachStatement </li>
     * </ul>
     * </p>
     */
    public void createStatementForeach()
    {
        Preconditions.checkState(stack.size() == 4);

        // Create the AST node.
        ForeachStatement node = new ForeachStatement();

        // Initialize the AST node.
        node = node.setBody((SequenceStatement) stack.pop());
        node = node.setIterable((IExpression) stack.pop());
        node = node.setType((TypeSpecifier) stack.pop());
        node = node.setVariable((Variable) stack.pop());

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates a for-statement.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> body : SequenceStatement </li>
     * <li> next : IExpression </li>
     * <li> condition : IExpression </li>
     * <li> initializer : IExpression </li>
     * <li> variable : Variable </li>
     * </ul>
     * </p>
     *
     * <p>
     * Note: The step is null, iff the step is implicit.
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : ForStatement </li>
     * </ul>
     * </p>
     */
    public void createStatementFor()
    {
        Preconditions.checkState(stack.size() == 5);

        // Create the AST node.
        ForStatement node = new ForStatement();

        // Initialize the AST node.
        node = node.setBody((SequenceStatement) stack.pop());
        node = node.setNext((IExpression) stack.pop());
        node = node.setCondition((IExpression) stack.pop());
        node = node.setInitializer((IExpression) stack.pop());
        node = node.setVariable((Variable) stack.pop());

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates a while-statement.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> body : SequenceStatement </li>
     * <li> condition : IExpression </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : WhileStatement </li>
     * </ul>
     * </p>
     */
    public void createStatementWhile()
    {
        Preconditions.checkState(stack.size() == 2);

        // Create the AST node.
        WhileStatement node = new WhileStatement();

        // Initialize the AST node.
        node = node.setBody((SequenceStatement) stack.pop());
        node = node.setCondition((IExpression) stack.pop());

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates a until-statement.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> body : SequenceStatement </li>
     * <li> condition : IExpression </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : UntilStatement </li>
     * </ul>
     * </p>
     */
    public void createStatementUntil()
    {
        Preconditions.checkState(stack.size() == 2);

        // Create the AST node.
        UntilStatement node = new UntilStatement();

        // Initialize the AST node.
        node = node.setBody((SequenceStatement) stack.pop());
        node = node.setCondition((IExpression) stack.pop());

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates an assert-statement.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> message : IExpression </li>
     * <li> condition : IExpression </li>
     * </ul>
     * </p>
     *
     * <p>
     * Note: The message must be null, if the message is unspecified.
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : AssertStatement </li>
     * </ul>
     * </p>
     */
    public void createStatementAssert()
    {
        Preconditions.checkState(stack.size() == 2);

        // Create the AST node.
        AssertStatement node = new AssertStatement();

        // Initialize the AST node.
        node = node.setMessage((IExpression) stack.pop());
        node = node.setCondition((IExpression) stack.pop());

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates an assume-statement.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> message : IExpression </li>
     * <li> condition : IExpression </li>
     * </ul>
     * </p>
     *
     * <p>
     * Note: The message must be null, if the message is unspecified.
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : AssumeStatement </li>
     * </ul>
     * </p>
     */
    public void createStatementAssume()
    {
        Preconditions.checkState(stack.size() == 2);

        // Create the AST node.
        AssumeStatement node = new AssumeStatement();

        // Initialize the AST node.
        node = node.setMessage((IExpression) stack.pop());
        node = node.setCondition((IExpression) stack.pop());

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates a throw-statement.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> exception : IExpression </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : ThrowStatement </li>
     * </ul>
     * </p>
     */
    public void createStatementThrow()
    {
        Preconditions.checkState(stack.size() == 1);

        // Create the AST node.
        ThrowStatement node = new ThrowStatement();

        // Initialize the AST node.
        node = node.setValue((IExpression) stack.pop());

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates a try-catch statement.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> handler[n] : ExceptionHandler </li>
     * <li> handler[2] : ExceptionHandler </li>
     * <li> handler[1] : ExceptionHandler </li>
     * <li> handler[0] : ExceptionHandler </li>
     * <li> body : SequenceStatement </li>
     * </ul>
     * </p>
     *
     * <p>
     * Note: At least one exception-handler must be provided.
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : TryCatchStatement </li>
     * </ul>
     * </p>
     */
    public void createStatementTryCatch()
    {
        Preconditions.checkState(stack.size() >= 1);

        // Get the pieces off of the stack.
        final LinkedList<ExceptionHandler> list = Lists.newLinkedList();

        while (stack.size() > 1)
        {
            list.add(0, (ExceptionHandler) stack.pop());
        }

        final SequenceStatement body = (SequenceStatement) stack.pop();

        // Create the AST node.
        TryCatchStatement node = new TryCatchStatement();
        node = node.setBody(body);
        node = node.setHandlers((new ConstructList<ExceptionHandler>()).addAll(list));

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates an exception-handler for a try-catch statement.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> body : SequenceStatement </li>
     * <li> type : TypeSpecifier </li>
     * <li> variable : Variable </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : ExceptionHandler </li>
     * </ul>
     * </p>
     */
    public void createComponentExceptionHandler()
    {
        Preconditions.checkState(stack.size() == 3);

        // Create the AST node.
        ExceptionHandler node = new ExceptionHandler();

        // Initialize the AST node.
        node = node.setBody((SequenceStatement) stack.pop());
        node = node.setType((TypeSpecifier) stack.pop());
        node = node.setVariable((Variable) stack.pop());

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates a var-statement.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> value : IExpression </li>
     * <li> variable : Variable </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : VarStatement </li>
     * </ul>
     * </p>
     */
    public void createStatementVar()
    {
        Preconditions.checkState(stack.size() == 2);

        // Create the AST node.
        VarStatement node = new VarStatement();

        // Initialize the AST node.
        node = node.setValue((IExpression) stack.pop());
        node = node.setVariable((Variable) stack.pop());

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates a val-statement.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> value : IExpression </li>
     * <li> variable : Variable </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : ValStatement </li>
     * </ul>
     * </p>
     */
    public void createStatementVal()
    {
        Preconditions.checkState(stack.size() == 2);

        // Create the AST node.
        ValStatement node = new ValStatement();

        // Initialize the AST node.
        node = node.setValue((IExpression) stack.pop());
        node = node.setVariable((Variable) stack.pop());

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates a let-statement.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> value : IExpression </li>
     * <li> variable : Variable </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : LetStatement </li>
     * </ul>
     * </p>
     */
    public void createStatementLet()
    {
        Preconditions.checkState(stack.size() == 2);

        // Create the AST node.
        LetStatement node = new LetStatement();

        // Initialize the AST node.
        node = node.setValue((IExpression) stack.pop());
        node = node.setVariable((Variable) stack.pop());

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates a nop-statement.
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : NopStatement </li>
     * </ul>
     * </p>
     */
    public void createStatementNop()
    {
        Preconditions.checkState(stack.isEmpty());

        // Create the AST node.
        final NopStatement node = new NopStatement();

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates a return-value statement.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> value : IExpression </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : ReturnValueStatement </li>
     * </ul>
     * </p>
     */
    public void createStatementReturnValue()
    {
        Preconditions.checkState(stack.size() == 1);

        // Create the AST node.
        ReturnValueStatement node = new ReturnValueStatement();

        // Initialize the AST node.
        node = node.setValue((IExpression) stack.pop());

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates a return-void statement.
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : ReturnVoidStatement </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     */
    public void createStatementReturnVoid()
    {
        Preconditions.checkState(stack.isEmpty());

        // Create the AST node.
        ReturnVoidStatement node = new ReturnVoidStatement();

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates a recur-statement.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> argument[n] : IExpression </li>
     * <li> argument[2] : IExpression </li>
     * <li> argument[1] : IExpression </li>
     * <li> argument[0] : IExpression </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : RecurStatement </li>
     * </ul>
     * </p>
     */
    public void createStatementRecur()
    {
        // Create the AST node.
        RecurStatement node = new RecurStatement();
        node = node.setArguments(popExpressions(stack.size()));

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates an expression-statement.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> expression : IExpression </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : ExpressionStatement </li>
     * </ul>
     * </p>
     */
    public void createStatementExpression()
    {
        Preconditions.checkState(stack.size() == 1);

        // Create the AST node.
        ExpressionStatement node = new ExpressionStatement();

        // Initialize the AST node.
        node = node.setExpression((IExpression) stack.pop());

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates a sequence-statement.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> element[n] : IStatement </li>
     * <li> element[.] : IStatement </li>
     * <li> element[2] : IStatement </li>
     * <li> element[1] : IStatement </li>
     * <li> element[0] : IStatement </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : SequenceStatement </li>
     * </ul>
     * </p>
     */
    public void createStatementSequence()
    {
        // Create the AST node.
        SequenceStatement node = new SequenceStatement();

        // Initialize the AST node.
        final LinkedList<IStatement> list = Lists.newLinkedList();

        while (stack.isEmpty() == false)
        {
            list.add(0, (IStatement) stack.pop());
        }

        node = node.setElements((new ConstructList<IStatement>()).addAll(list));

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates an as-operation.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> type : TypeSpecifier </li>
     * <li> value : IExpression </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : AsOperation </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     */
    public void createOperationAs()
    {
        Preconditions.checkState(stack.size() >= 2);

        final int original_size = stack.size();
        {
            // Create the AST node.
            AsOperation node = new AsOperation();

            // Initialize the AST node.
            node = node.setType((TypeSpecifier) stack.pop());
            node = node.setValue((IExpression) stack.pop());

            // Push the AST node onto the stack.
            stack.push(node);
        }
        assert stack.size() == (original_size - 2 + 1);
    }

    /**
     * This method creates an is-operation.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> type : TypeSpecifier </li>
     * <li> value : IExpression </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : AsOperation </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     */
    public void createOperationIs()
    {
        Preconditions.checkState(stack.size() >= 1);

        final int original_size = stack.size();
        {
            // Create the AST node.
            IsOperation node = new IsOperation();

            // Initialize the AST node.
            node = node.setType((TypeSpecifier) stack.pop());
            node = node.setValue((IExpression) stack.pop());

            // Push the AST node onto the stack.
            stack.push(node);
        }
        assert stack.size() == (original_size - 2 + 1);
    }

    /**
     * This method creates a null-coalescing operation.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> right-operand : IExpression </li>
     * <li> left-operand : IExpression </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : NullCoalescingOperation </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     */
    public void createOperationNullCoalescing()
    {
        createBinaryOperation(new NullCoalescingOperation());
    }

    /**
     * This method creates an AND-operation.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> right-operand : IExpression </li>
     * <li> left-operand : IExpression </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : AndOperation </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     */
    public void createOperationAnd()
    {
        createBinaryOperation(new AndOperation());
    }

    /**
     * This method creates an OR-operation.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> right-operand : IExpression </li>
     * <li> left-operand : IExpression </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : OrOperation </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     */
    public void createOperationOr()
    {
        createBinaryOperation(new OrOperation());
    }

    /**
     * This method creates an XOR-operation.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> right-operand : IExpression </li>
     * <li> left-operand : IExpression </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : XorOperation </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     */
    public void createOperationXor()
    {
        createBinaryOperation(new XorOperation());
    }

    /**
     * This method creates an IMPLIES-operation.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> right-operand : IExpression </li>
     * <li> left-operand : IExpression </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : ImpliesOperation </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     */
    public void createOperationImplies()
    {
        createBinaryOperation(new ImpliesOperation());
    }

    /**
     * This method creates an identity-equals operation.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> right-operand : IExpression </li>
     * <li> left-operand : IExpression </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : IdentityEqualsOperation </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     */
    public void createOperationIdentityEquals()
    {
        createBinaryOperation(new IdentityEqualsOperation());
    }

    /**
     * This method creates an identity-not-equals operation.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> right-operand : IExpression </li>
     * <li> left-operand : IExpression </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : IdentityNotEqualsOperation </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     */
    public void createOperationIdentityNotEquals()
    {
        createBinaryOperation(new IdentityNotEqualsOperation());
    }

    /**
     * This method creates an equals-operation.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> right-operand : IExpression </li>
     * <li> left-operand : IExpression </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : EqualsOperation </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     */
    public void createOperationEquals()
    {
        createBinaryOperation(new EqualsOperation());
    }

    /**
     * This method creates an not-equals-operation.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> right-operand : IExpression </li>
     * <li> left-operand : IExpression </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : NotEqualsOperation </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     */
    public void createOperationNotEquals()
    {
        createBinaryOperation(new NotEqualsOperation());
    }

    /**
     * This method creates a less-than operation.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> right-operand : IExpression </li>
     * <li> left-operand : IExpression </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : LessThanOperation </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     */
    public void createOperationLessThan()
    {
        createBinaryOperation(new LessThanOperation());
    }

    /**
     * This method creates a less-than-or-equals operation.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> right-operand : IExpression </li>
     * <li> left-operand : IExpression </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : LessThanOrEqualsOperation </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     */
    public void createOperationLessThanOrEquals()
    {
        createBinaryOperation(new LessThanOrEqualsOperation());
    }

    /**
     * This method creates a greater-than operation.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> right-operand : IExpression </li>
     * <li> left-operand : IExpression </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : GreaterThanOperation </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     */
    public void createOperationGreaterThan()
    {
        createBinaryOperation(new GreaterThanOperation());
    }

    /**
     * This method creates a greater-than-or-equals operation.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> right-operand : IExpression </li>
     * <li> left-operand : IExpression </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : GreaterThanOrEqualsOperation </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     */
    public void createOperationGreaterThanOrEquals()
    {
        createBinaryOperation(new GreaterThanOrEqualsOperation());
    }

    /**
     * This method creates a add-operation.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> right-operand : IExpression </li>
     * <li> left-operand : IExpression </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : AddOperation </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     */
    public void createOperationAdd()
    {
        createBinaryOperation(new AddOperation());
    }

    /**
     * This method creates a subtract-operation.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> right-operand : IExpression </li>
     * <li> left-operand : IExpression </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : SubtractOperation </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     */
    public void createOperationSubtract()
    {
        createBinaryOperation(new SubtractOperation());
    }

    /**
     * This method creates a concat-operation.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> right-operand : IExpression </li>
     * <li> left-operand : IExpression </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : AndOperation </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     */
    public void createOperationConcat()
    {
        createBinaryOperation(new ConcatOperation());
    }

    /**
     * This method creates a multiply-operation.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> right-operand : IExpression </li>
     * <li> left-operand : IExpression </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : MultiplyOperation </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     */
    public void createOperationMultiply()
    {
        createBinaryOperation(new MultiplyOperation());
    }

    /**
     * This method creates a modulo-operation.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> right-operand : IExpression </li>
     * <li> left-operand : IExpression </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : ModuloOperation </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     */
    public void createOperationModulo()
    {
        createBinaryOperation(new ModuloOperation());
    }

    /**
     * This method creates a divide-operation.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> right-operand : IExpression </li>
     * <li> left-operand : IExpression </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : DivideOperation </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     */
    public void createOperationDivide()
    {
        createBinaryOperation(new DivideOperation());
    }

    /**
     * This method creates a negate-operation.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> operand : IExpression </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : NegateOperation </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     */
    public void createOperationNegate()
    {
        createUnaryOperation(new NegateOperation());
    }

    /**
     * This method creates a not-operation.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> operand : IExpression </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : NotOperation </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     */
    public void createOperationNot()
    {
        createUnaryOperation(new NotOperation());
    }

    /**
     * This method creates a dispatch-expression.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> argument[n] : IExpression </li>
     * <li> argument[2] : IExpression </li>
     * <li> argument[1] : IExpression </li>
     * <li> argument[0] : IExpression </li>
     * <li> name : Name </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : DispatchExpression </li>
     * </ul>
     * </p>
     */
    public void createExpressionDispatch()
    {
        Preconditions.checkState(stack.size() >= 1);

        // Create the AST node.
        DispatchExpression node = new DispatchExpression();

        // Initialize the AST node.
        node = node.setArguments(this.popExpressions(stack.size() - 1));
        node = node.setName((Name) stack.pop());

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates a new-expression.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> argument[n] : IExpression </li>
     * <li> argument[2] : IExpression </li>
     * <li> argument[1] : IExpression </li>
     * <li> argument[0] : IExpression </li>
     * <li> type : TypeSpecifier </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : NewExpression </li>
     * </ul>
     * </p>
     */
    public void createExpressionNew()
    {
        Preconditions.checkState(stack.size() >= 1);

        // Create the AST node.
        NewExpression node = new NewExpression();

        // Initialize the AST node.
        node = node.setArguments(popExpressions(stack.size() - 1));
        node = node.setType((TypeSpecifier) stack.pop());

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates a call-method expression.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> argument[n] : IExpression </li>
     * <li> argument[2] : IExpression </li>
     * <li> argument[1] : IExpression </li>
     * <li> argument[0] : IExpression </li>
     * <li> name : Name </li>
     * <li> owner : IExpression </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : CallMethodExpression </li>
     * </ul>
     * </p>
     */
    public void createExpressionCallMethod()
    {
        Preconditions.checkState(stack.size() >= 1);

        // Create the AST node.
        CallMethodExpression node = new CallMethodExpression();

        // Initialize the AST node.
        node = node.setArguments(popExpressions(stack.size() - 2));
        node = node.setName((Name) stack.pop());
        node = node.setOwner((IExpression) stack.pop());

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates a call-static-method expression.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> argument[n] : IExpression </li>
     * <li> argument[2] : IExpression </li>
     * <li> argument[1] : IExpression </li>
     * <li> argument[0] : IExpression </li>
     * <li> name : Name </li>
     * <li> owner : TypeSpecifier (null, if inferred) </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : CallStaticMethodExpression </li>
     * </ul>
     * </p>
     */
    public void createExpressionCallStaticMethod()
    {
        Preconditions.checkState(stack.size() >= 2);

        // Create the AST node.
        CallStaticMethodExpression node = new CallStaticMethodExpression();

        // Initialize the AST node.
        node = node.setArguments(this.popExpressions(stack.size() - 2));
        node = node.setName((Name) stack.pop());
        node = node.setOwner((TypeSpecifier) stack.pop());

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates a set-field expression.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> value : IExpression </li>
     * <li> name : Name </li>
     * <li> owner : IExpression </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : SetFieldExpression </li>
     * </ul>
     * </p>
     */
    public void createExpressionSetField()
    {
        Preconditions.checkState(stack.size() == 3);

        // Create the AST node.
        SetFieldExpression node = new SetFieldExpression();

        // Initialize the AST node.
        node = node.setValue((IExpression) stack.pop());
        node = node.setName((Name) stack.pop());
        node = node.setOwner((IExpression) stack.pop());

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates a get-field expression.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> name : Name </li>
     * <li> owner : IExpression </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : GetFieldExpression </li>
     * </ul>
     * </p>
     */
    public void createExpressionGetField()
    {
        Preconditions.checkState(stack.size() == 2);

        // Create the AST node.
        GetFieldExpression node = new GetFieldExpression();

        // Initialize the AST node.
        node = node.setName((Name) stack.pop());
        node = node.setOwner((IExpression) stack.pop());

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates a set-static-field expression.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> value : IExpression </li>
     * <li> name : Name </li>
     * <li> owner : TypeSpecifier (null, if inferred) </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : SetStaticFieldExpression </li>
     * </ul>
     * </p>
     */
    public void createExpressionSetStaticField()
    {
        Preconditions.checkState(stack.size() == 3);

        // Create the AST node.
        SetStaticFieldExpression node = new SetStaticFieldExpression();

        // Initialize the AST node.
        node = node.setValue((IExpression) stack.pop());
        node = node.setName((Name) stack.pop());
        node = node.setOwner((TypeSpecifier) stack.pop());

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates a get-static-field expression.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> name : Name </li>
     * <li> owner : TypeSpecifier (null, if inferred) </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : GetStaticFieldExpression </li>
     * </ul>
     * </p>
     */
    public void createExpressionGetStaticField()
    {
        Preconditions.checkState(stack.size() == 2);

        // Create the AST node.
        GetStaticFieldExpression node = new GetStaticFieldExpression();

        // Initialize the AST node.
        node = node.setName((Name) stack.pop());
        node = node.setOwner((TypeSpecifier) stack.pop());

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates an instanceof-expression.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> owner : TypeSpecifier </li>
     * <li> value : IExpression </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : InstanceOfExpression </li>
     * </ul>
     * </p>
     */
    public void createExpressionInstanceOf()
    {
        Preconditions.checkState(stack.size() == 2);

        // Create the AST node.
        InstanceOfExpression node = new InstanceOfExpression();

        // Initialize the AST node.
        node = node.setType((TypeSpecifier) stack.pop());
        node = node.setValue((IExpression) stack.pop());

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates an ternary-conditional expression.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> option-false : IExpression </li>
     * <li> option-true : IExpression </li>
     * <li> condition : IExpression </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : TernaryConditionalExpression </li>
     * </ul>
     * </p>
     */
    public void createExpressionTernaryConditional()
    {
        Preconditions.checkState(stack.size() == 3);

        // Create the AST node.
        TernaryConditionalExpression node = new TernaryConditionalExpression();

        // Initialize the AST node.
        node = node.setCaseFalse((IExpression) stack.pop());
        node = node.setCaseTrue((IExpression) stack.pop());
        node = node.setCondition((IExpression) stack.pop());

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates a progn-expression.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> expression[n] : IExpression </li>
     * <li> expression[2] : IExpression </li>
     * <li> expression[1] : IExpression </li>
     * <li> expression[0] : IExpression </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : PrognExpression </li>
     * </ul>
     * </p>
     */
    public void createExpressionProgn()
    {
        Preconditions.checkState(stack.size() >= 0);

        // Create the AST node.
        PrognExpression node = new PrognExpression();

        // Initialize the AST node.
        node = node.setElements(popExpressions(stack.size()));

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates a list-expression.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> element[n] : IExpression </li>
     * <li> element[.] : IExpression </li>
     * <li> element[2] : IExpression </li>
     * <li> element[1] : IExpression </li>
     * <li> element[0] : IExpression </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : ListExpression </li>
     * </ul>
     * </p>
     */
    public void createExpressionList()
    {
        // Get the pieces off of the stack.
        final LinkedList<IExpression> list = Lists.newLinkedList();

        while (stack.isEmpty() == false)
        {
            list.add(0, (IExpression) stack.pop());
        }

        // Create the AST node.
        ListExpression node = new ListExpression();
        node = node.setElements((new ConstructList()).addAll(list));

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates a list-comprehension-expression.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> condition : IExpression </li>
     * <li> iterable : IExpression </li>
     * <li> type : TypeSpecifier </li>
     * <li> variable : Variable </li>
     * <li> modifier : IExpression </li>
     * </ul>
     * </p>
     *
     * <p>
     * The <i>condition</i> is optional.
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : ListComprehensionExpression </li>
     * </ul>
     * </p>
     */
    public void createExpressionListComprehension()
    {
        Preconditions.checkState(stack.size() == 4 || stack.size() == 5);

        // Get the pieces off of the stack.
        final IExpression condition = (IExpression) (stack.size() == 5 ? stack.pop() : null);
        final IExpression iterable = (IExpression) stack.pop();
        final TypeSpecifier type = (TypeSpecifier) stack.pop();
        final Variable variable = (Variable) stack.pop();
        final IExpression modifier = (IExpression) stack.pop();

        // Create the AST node.
        ListComprehensionExpression node = new ListComprehensionExpression();
        node = node.setCondition(condition);
        node = node.setIterable(iterable);
        node = node.setType(type);
        node = node.setVariable(variable);
        node = node.setModifier(modifier);

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates a lambda-statement.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> body : IExpression </li>
     * <li> formal[n] : Variable </li>
     * <li> formal[3] : Variable </li>
     * <li> formal[2] : Variable </li>
     * <li> formal[1] : Variable </li>
     * <li> formal[0] : Variable </li>
     * <li> type : TypeSpecifier </li>
     * <li> variable : Variable </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : LambdaStatement </li>
     * </ul>
     * </p>
     */
    public void createStatementLambda()
    {
        Preconditions.checkState(stack.size() >= 3);

        // Get the pieces off of the stack.
        final IExpression body = (IExpression) stack.pop();

        final TypeSpecifier type = (TypeSpecifier) stack.pop();

        final Variable variable = (Variable) stack.pop();

        // Create the AST node.
        LambdaStatement node = new LambdaStatement();

        // Initialize the AST node.
        node = node.setVariable(variable);
        node = node.setType(type);
        node = node.setBody(body);

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates a delegate-statement.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> name : Name </li>
     * <li> owner : TypeSpecifier </li>
     * <li> type : TypeSpecifier </li>
     * <li> variable : Variable </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : DelegateStatement </li>
     * </ul>
     * </p>
     */
    public void createStatementDelegate()
    {
        Preconditions.checkState(stack.size() == 4);

        // Create the AST node.
        DelegateStatement node = new DelegateStatement();

        // Initialize the AST node.
        node = node.setMethod((Name) stack.pop());
        node = node.setOwner((TypeSpecifier) stack.pop());
        node = node.setType((TypeSpecifier) stack.pop());
        node = node.setVariable((Variable) stack.pop());

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates a locals-expression.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : LocalsExpression </li>
     * </ul>
     * </p>
     */
    public void createExpressionLocals()
    {
        Preconditions.checkState(stack.size() == 0);

        // Create the AST node.
        LocalsExpression node = new LocalsExpression();

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates a once-expression.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> value : IExpression </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : OnceExpression </li>
     * </ul>
     * </p>
     */
    public void createExpressionOnce()
    {
        Preconditions.checkState(stack.size() == 1);

        // Create the AST node.
        OnceExpression node = new OnceExpression();

        // Initialize the AST node.
        node = node.setValue((IExpression) stack.pop());

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates a boolean-datum.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : BooleanDatum </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * @param value is the value that the new datum represents.
     */
    public void createDatum(final boolean value)
    {
        final int original_size = stack.size();
        {
            // Create the AST node.
            BooleanDatum datum = new BooleanDatum();

            // Initialize the AST node.
            datum = datum.setValue(value);

            // Push the AST node onto the stack.
            stack.push(datum);
        }
        assert stack.size() == (original_size + 1);
    }

    /**
     * This method creates a char-datum.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : CharDatum </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * @param value is the value that the new datum represents.
     */
    public void createDatum(final CharLiteral value)
    {
        final int original_size = stack.size();
        {
            // Create the AST node.
            CharDatum datum = new CharDatum();

            // Initialize the AST node.
            datum = datum.setValue(value);

            // Push the AST node onto the stack.
            stack.push(datum);
        }
        assert stack.size() == (original_size + 1);
    }

    /**
     * This method creates a byte-datum.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : ByteDatum </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * @param value is the value that the new datum represents.
     */
    public void createDatum(final ByteLiteral value)
    {
        final int original_size = stack.size();
        {
            // Create the AST node.
            ByteDatum datum = new ByteDatum();

            // Initialize the AST node.
            datum = datum.setValue(value);

            // Push the AST node onto the stack.
            stack.push(datum);
        }
        assert stack.size() == (original_size + 1);
    }

    /**
     * This method creates a short-datum.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : ShortDatum </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * @param value is the value that the new datum represents.
     */
    public void createDatum(final ShortLiteral value)
    {
        final int original_size = stack.size();
        {
            // Create the AST node.
            ShortDatum datum = new ShortDatum();

            // Initialize the AST node.
            datum = datum.setValue(value);

            // Push the AST node onto the stack.
            stack.push(datum);
        }
        assert stack.size() == (original_size + 1);
    }

    /**
     * This method creates a int-datum.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : IntDatum </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * @param value is the value that the new datum represents.
     */
    public void createDatum(final IntLiteral value)
    {
        final int original_size = stack.size();
        {
            // Create the AST node.
            IntDatum datum = new IntDatum();

            // Initialize the AST node.
            datum = datum.setValue(value);

            // Push the AST node onto the stack.
            stack.push(datum);
        }
        assert stack.size() == (original_size + 1);
    }

    /**
     * This method creates a long-datum.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : LongDatum </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * @param value is the value that the new datum represents.
     */
    public void createDatum(final LongLiteral value)
    {
        final int original_size = stack.size();
        {
            // Create the AST node.
            LongDatum datum = new LongDatum();

            // Initialize the AST node.
            datum = datum.setValue(value);

            // Push the AST node onto the stack.
            stack.push(datum);
        }
        assert stack.size() == (original_size + 1);
    }

    /**
     * This method creates a float-datum.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : FloatDatum </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * @param value is the value that the new datum represents.
     */
    public void createDatum(final FloatLiteral value)
    {
        final int original_size = stack.size();
        {
            // Create the AST node.
            FloatDatum datum = new FloatDatum();

            // Initialize the AST node.
            datum = datum.setValue(value);

            // Push the AST node onto the stack.
            stack.push(datum);
        }
        assert stack.size() == (original_size + 1);
    }

    /**
     * This method creates a double-datum.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : DoubleDatum </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * @param value is the value that the new datum represents.
     */
    public void createDatum(final DoubleLiteral value)
    {
        final int original_size = stack.size();
        {
            // Create the AST node.
            DoubleDatum datum = new DoubleDatum();

            // Initialize the AST node.
            datum = datum.setValue(value);

            // Push the AST node onto the stack.
            stack.push(datum);
        }
        assert stack.size() == (original_size + 1);
    }

    /**
     * This method creates a big-integer-datum.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : BigIntegerDatum </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * @param value is the value that the new datum represents.
     */
    public void createDatum(final BigIntegerLiteral value)
    {
        final int original_size = stack.size();
        {
            // Create the AST node.
            BigIntegerDatum datum = new BigIntegerDatum();

            // Initialize the AST node.
            datum = datum.setValue(value);

            // Push the AST node onto the stack.
            stack.push(datum);
        }
        assert stack.size() == (original_size + 1);
    }

    /**
     * This method creates a big-decimal-datum.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : BigDecimalDatum </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * @param value is the value that the new datum represents.
     */
    public void createDatum(final BigDecimalLiteral value)
    {
        final int original_size = stack.size();
        {
            // Create the AST node.
            BigDecimalDatum datum = new BigDecimalDatum();

            // Initialize the AST node.
            datum = datum.setValue(value);

            // Push the AST node onto the stack.
            stack.push(datum);
        }
        assert stack.size() == (original_size + 1);
    }

    /**
     * This method creates a String-datum.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : StringDatum </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * @param verbatim is false, if the string may contain escape sequences.
     * @param value is the value that the new datum represents.
     */
    public void createDatum(final boolean verbatim,
                            final String value)
    {
        final int original_size = stack.size();
        {
            // Create the AST node.
            StringDatum datum = new StringDatum();

            // Initialize the AST node.
            datum = datum.setVerbatim(verbatim);
            datum = datum.setValue(value);

            // Push the AST node onto the stack.
            stack.push(datum);
        }
        assert stack.size() == (original_size + 1);
    }

    /**
     * This method creates a class-datum.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> type : TypeSpecifier </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : ClassDatum </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     */
    public void createDatumClass()
    {
        Preconditions.checkState(stack.size() >= 1);

        final int original_size = stack.size();
        {
            // Get the pieces off of the stack.
            final TypeSpecifier owner = (TypeSpecifier) stack.pop();

            // Create the AST node.
            ClassDatum datum = new ClassDatum();

            // Initialize the AST node.
            datum = datum.setType(owner);

            // Push the AST node onto the stack.
            stack.push(datum);
        }
        assert stack.size() == (original_size - 1 + 1);
    }

    /**
     * This method creates a null-datum.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : NullDatum </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     */
    public void createDatumNull()
    {
        final int original_size = stack.size();
        {
            // Create the AST node.
            final NullDatum datum = new NullDatum();

            // Push the AST node onto the stack.
            stack.push(datum);
        }
        assert stack.size() == (original_size + 1);
    }

    /**
     * This method creates a variable-datum.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> variable : Variable </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : VariableDatum </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     */
    public void createDatumVariable()
    {
        Preconditions.checkState(stack.size() >= 1);

        final int original_size = stack.size();
        {
            // Create the AST node.
            VariableDatum datum = new VariableDatum();

            // Initialize the AST node.
            final Variable variable = (Variable) stack.pop();
            datum = datum.setVariable(variable);

            // Push the AST node onto the stack.
            stack.push(datum);
        }
        assert stack.size() == (original_size - 1 + 1);
    }

    /**
     * This method creates a doc-comment.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> line[n] : DocCommentLine </li>
     * <li> line[2] : DocCommentLine </li>
     * <li> line[1] : DocCommentLine </li>
     * <li> line[0] : DocCommentLine </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * <p>
     * There may be zero or more lines.
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : DocComment </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     */
    public void createComponentDocComment()
    {
        // Get the pieces off of the stack.
        final LinkedList<DocCommentLine> lines = Lists.newLinkedList();

        while (!stack.isEmpty() && stack.peek() instanceof DocCommentLine)
        {
            lines.addFirst((DocCommentLine) stack.pop());
        }

        // Create the AST node.
        DocComment node = new DocComment();

        // Initialize the AST node.
        node = node.setLines(new ConstructList(lines));

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates a doc-comment-line.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * <p>
     * There may be zero or more lines.
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : DocCommentLine </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * @param text is the comment line itself.
     */
    public void createComponentDocCommentLine(final String text)
    {
        final int original_size = stack.size();
        {
            // Create the AST node.
            DocCommentLine node = new DocCommentLine();

            // Initialize the AST node.
            node = node.setText(text);

            // Push the AST node onto the stack.
            stack.push(node);
        }
        assert stack.size() == (original_size + 1);
    }

    /**
     * This method creates an annotation-usage.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> annotation-type : TypeSpecifier </li></li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : Annotation </li>
     * </ul>
     * </p>
     *
     * @param values are the values to store in the annotation.
     */
    public void createComponentAnnotation(final List<String> values)
    {
        Preconditions.checkState(stack.size() == 1);

        /**
         * Create the immutable list of values.
         */
        final List<String> list = new LinkedList<String>();

        if (values != null)
        {
            list.addAll(values);
        }

        /**
         * Pop the pieces off of the stack.
         */
        final TypeSpecifier type = (TypeSpecifier) stack.pop();

        /**
         * Create the AST node.
         */
        Annotation node = new Annotation();
        node = node.setType(type);
        node = node.setValues(values == null ? null : Collections.unmodifiableList(list));

        /**
         * Push the AST node onto the stack.
         */
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates an annotation-list.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> annotation[n] : Annotation </li></li>
     * <li> annotation[2] : Annotation </li></li>
     * <li> annotation[1] : Annotation </li></li>
     * <li> annotation[0] : Annotation </li></li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : AnnotationList </li>
     * </ul>
     * </p>
     */
    public void createComponentAnnotationList()
    {
        // Create the list itself.
        final LinkedList<Annotation> list = new LinkedList<Annotation>();

        while (stack.isEmpty() == false)
        {
            list.add(0, (Annotation) stack.pop());
        }

        // Create the AST node.
        AnnotationList node = new AnnotationList();
        node = node.setAnnotations((new ConstructList<Annotation>()).addAll(list));

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates a type-specifier for an array-type.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> simple-name : Name </li>
     * <li> namespace : Namespace </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * <p>
     * Note: The namespace must be null, if the namespace is implicit.
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : TypeSpecifier </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * @param dimensions are the the number of dimensions in the array type.
     */
    public void createComponentTypeSpecifier(final Integer dimensions)
    {
        Preconditions.checkState(stack.size() >= 2);
        Preconditions.checkState(dimensions == null || dimensions >= 1);

        final int original_size = stack.size();
        {
            // Get the pieces off of the stack.
            final Name simple = (Name) stack.pop();
            final Namespace namespace = (Namespace) stack.pop();

            // Create the AST node.
            TypeSpecifier type = new TypeSpecifier();

            // Initialize the AST node.
            type = type.setNamespace(namespace);
            type = type.setName(simple);
            type = type.setDimensions(dimensions);

            // Push the AST node onto the stack.
            stack.push(type);
        }
        assert stack.size() == (original_size - 2 + 1);
    }

    /**
     * This method creates a type-specifier for an array-type.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> simple-name : Name </li>
     * <li> namespace : Namespace </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * <p>
     * Note: The namespace must be null, if the namespace is implicit.
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : TypeSpecifier </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     */
    public void createComponentTypeSpecifier()
    {
        createComponentTypeSpecifier(null);
    }

    /**
     * This method creates a variable.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : Variable </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     */
    public void createComponentVariable(final String name)
    {
        final int original_size = stack.size();
        {
            // Create the AST node.
            Variable variable = new Variable();

            // Initialize the AST node.
            variable = variable.setName(name);

            // Push the AST node onto the stack.
            stack.push(variable);
        }
        assert stack.size() == (original_size + 1);
    }

    /**
     * This method creates a label.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : Label </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     */
    public void createComponentLabel(final String name)
    {
        final int original_size = stack.size();
        {
            // Create the AST node.
            Label label = new Label();

            // Initialize the AST node.
            label = label.setName(name);

            // Push the AST node onto the stack.
            stack.push(label);
        }
        assert stack.size() == (original_size + 1);
    }

    /**
     * This method creates a name.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> ..... </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : Name </li>
     * <li> ..... </li>
     * </ul>
     * </p>
     */
    public void createComponentName(final String name)
    {
        final int original_size = stack.size();
        {
            // Create the AST node.
            Name node = new Name();

            // Initialize the AST node.
            node = node.setName(name);

            // Push the AST node onto the stack.
            stack.push(node);
        }
        assert stack.size() == (original_size + 1);
    }

    /**
     * This method creates an element.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> type : TypeSpecifier </li>
     * <li> name : Name </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : Element </li>
     * </ul>
     * </p>
     */
    public void createComponentElement()
    {
        Preconditions.checkState(stack.size() == 2);

        // Pop the pieces off of the stack.
        final TypeSpecifier type = (TypeSpecifier) stack.pop();
        final Name name = (Name) stack.pop();

        // Create the AST node.
        Element node = new Element();
        node = node.setName(name);
        node = node.setType(type);

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates an element-list.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> element[n] : Element </li>
     * <li> element[2] : Element </li>
     * <li> element[1] : Element </li>
     * <li> element[0] : Element </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : ElementList </li>
     * </ul>
     * </p>
     */
    public void createComponentElementList()
    {
        // Create the list itself.
        final LinkedList<Element> list = new LinkedList<Element>();

        while (stack.isEmpty() == false)
        {
            list.add(0, (Element) stack.pop());
        }

        ConstructList<Element> elements = new ConstructList<Element>();
        elements = elements.addAll(list);

        // Create the AST node.
        ElementList node = new ElementList();
        node = node.setElements(elements);

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates a formal-parameter.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> type : TypeSpecifier </li>
     * <li> variable : Variable </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : FormalParameter </li>
     * </ul>
     * </p>
     */
    public void createComponentFormalParameter()
    {
        Preconditions.checkState(stack.size() == 2);

        // Pop the pieces off of the stack.
        final TypeSpecifier type = (TypeSpecifier) stack.pop();
        final Variable variable = (Variable) stack.pop();

        // Create the AST node.
        FormalParameter node = new FormalParameter();
        node = node.setVariable(variable);
        node = node.setType(type);

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates a formal-parameter.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> parameter[n] : FormalParameter </li>
     * <li> parameter[2] : FormalParameter </li>
     * <li> parameter[1] : FormalParameter </li>
     * <li> parameter[0] : FormalParameter </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : FormalParameter </li>
     * </ul>
     * </p>
     */
    public void createComponentFormalParameterList()
    {
        // Create the list itself.
        final LinkedList<FormalParameter> list = new LinkedList<FormalParameter>();

        while (stack.isEmpty() == false)
        {
            list.add(0, (FormalParameter) stack.pop());
        }

        ConstructList<FormalParameter> parameters = new ConstructList<FormalParameter>();
        parameters = parameters.addAll(list);

        // Create the AST node.
        FormalParameterList node = new FormalParameterList();
        node = node.setParameters(parameters);

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates a namespace component.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> name[n] : Name </li>
     * <li> name[2] : Name </li>
     * <li> name[1] : Name </li>
     * <li> name[0] : Name </li>
     * </ul>
     * </p>
     *
     * <p>
     * Example: If the namespace is "java.util", then name[0] = "java" and name[1] = "util".
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : Namespace </li>
     * </ul>
     * </p>
     */
    public void createComponentNamespace()
    {
        Preconditions.checkState(stack.size() >= 1);

        // Create the list itself.
        final LinkedList<Name> list = Lists.newLinkedList();

        while (stack.isEmpty() == false)
        {
            list.add(0, (Name) stack.pop());
        }

        ConstructList<Name> names = new ConstructList<Name>();
        names = names.addAll(list);

        // Create the AST node.
        Namespace node = new Namespace();
        node = node.setNames(names);

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method creates a conditional-case.
     *
     * <p>
     * <b>Precondition of the Stack</b>
     * <ul>
     * <li> body : SequenceStatement </li>
     * <li> condition : IExpression </li>
     * </ul>
     * </p>
     *
     * <p>
     * <b>Postcondition of the Stack</b>
     * <ul>
     * <li> result : ConditionalCase </li>
     * </ul>
     * </p>
     */
    public void createComponentConditionalCase()
    {
        Preconditions.checkState(stack.size() == 2);

        // Create the AST node.
        ConditionalCase node = new ConditionalCase();
        node = node.setBody((SequenceStatement) stack.pop());
        node = node.setCondition((IExpression) stack.pop());

        // Push the AST node onto the stack.
        stack.push(node);

        assert stack.size() == 1;
    }

    /**
     * This method provides a shared implementation of the methods that create unary-operations.
     *
     * @param operation is the operation being created.
     */
    private void createUnaryOperation(IUnaryOperation operation)
    {
        final int original_size = stack.size();
        {
            // Initialize the AST node.
            final IExpression operand = (IExpression) stack.pop();
            operation = operation.setOperand(operand);

            // Push the AST node onto the stack.
            stack.push(operation);
        }
        assert stack.size() == ((original_size - 1) + 1);
    }

    /**
     * This method provides a shared implementation of the methods that create binary-operations.
     *
     * @param operation is the operation being created.
     */
    private void createBinaryOperation(IBinaryOperation operation)
    {
        final int original_size = stack.size();
        {
            // Initialize the AST node.
            final IExpression right_operand = (IExpression) stack.pop();
            final IExpression left_operand = (IExpression) stack.pop();
            operation = operation.setLeftOperand(left_operand);
            operation = operation.setRightOperand(right_operand);

            // Push the AST node onto the stack.
            stack.push(operation);
        }
        assert stack.size() == ((original_size - 2) + 1);
    }

    /**
     * This method pops a specified number of expressions off of the stack and then returns them.
     *
     * @param count is the number of expressions to pop off of the stack.
     * @return the expressions.
     */
    private ConstructList<IExpression> popExpressions(final int count)
    {
        final LinkedList<IExpression> list = Lists.newLinkedList();

        for (int i = 0; i < count; i++)
        {
            list.add(0, (IExpression) stack.pop());
        }

        ConstructList<IExpression> result = new ConstructList<IExpression>();
        result = result.addAll(list);

        return result;
    }
}
