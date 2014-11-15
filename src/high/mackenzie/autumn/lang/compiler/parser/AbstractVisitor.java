package high.mackenzie.autumn.lang.compiler.parser;

import high.mackenzie.snowflake.ITreeNode;
import high.mackenzie.snowflake.ITreeNodeVisitor;


/**
 * This class was auto-generated using the Snowflake parser-generator.
 *
 * <p>Generated On: Sat Nov 08 20:18:50 EST 2014</p>
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

        if("module".equals(name)) { visit_module(node); }
        else if("module_members".equals(name)) { visit_module_members(node); }
        else if("module_member".equals(name)) { visit_module_member(node); }
        else if("module_directive".equals(name)) { visit_module_directive(node); }
        else if("module_name".equals(name)) { visit_module_name(node); }
        else if("name_of_anonymous_module".equals(name)) { visit_name_of_anonymous_module(node); }
        else if("name_of_named_module".equals(name)) { visit_name_of_named_module(node); }
        else if("import_directive".equals(name)) { visit_import_directive(node); }
        else if("definition".equals(name)) { visit_definition(node); }
        else if("annotation_definition".equals(name)) { visit_annotation_definition(node); }
        else if("exception_definition".equals(name)) { visit_exception_definition(node); }
        else if("enum_definition".equals(name)) { visit_enum_definition(node); }
        else if("enum_constant_list".equals(name)) { visit_enum_constant_list(node); }
        else if("enum_constant".equals(name)) { visit_enum_constant(node); }
        else if("design_definition".equals(name)) { visit_design_definition(node); }
        else if("tuple_definition".equals(name)) { visit_tuple_definition(node); }
        else if("struct_definition".equals(name)) { visit_struct_definition(node); }
        else if("functor_definition".equals(name)) { visit_functor_definition(node); }
        else if("super_functor".equals(name)) { visit_super_functor(node); }
        else if("function_definition".equals(name)) { visit_function_definition(node); }
        else if("statement".equals(name)) { visit_statement(node); }
        else if("sequence_statement".equals(name)) { visit_sequence_statement(node); }
        else if("statements".equals(name)) { visit_statements(node); }
        else if("expression_statement".equals(name)) { visit_expression_statement(node); }
        else if("nop_statement".equals(name)) { visit_nop_statement(node); }
        else if("if_statement".equals(name)) { visit_if_statement(node); }
        else if("if_case".equals(name)) { visit_if_case(node); }
        else if("elif_cases_opt".equals(name)) { visit_elif_cases_opt(node); }
        else if("elif_case".equals(name)) { visit_elif_case(node); }
        else if("else_case_opt".equals(name)) { visit_else_case_opt(node); }
        else if("else_case".equals(name)) { visit_else_case(node); }
        else if("when_statement".equals(name)) { visit_when_statement(node); }
        else if("goto_statement".equals(name)) { visit_goto_statement(node); }
        else if("marker_statement".equals(name)) { visit_marker_statement(node); }
        else if("branch_statement".equals(name)) { visit_branch_statement(node); }
        else if("index".equals(name)) { visit_index(node); }
        else if("labels".equals(name)) { visit_labels(node); }
        else if("debug_statement".equals(name)) { visit_debug_statement(node); }
        else if("assert_statement".equals(name)) { visit_assert_statement(node); }
        else if("assertion_echo_opt".equals(name)) { visit_assertion_echo_opt(node); }
        else if("assertion_echo_empty".equals(name)) { visit_assertion_echo_empty(node); }
        else if("assertion_echo".equals(name)) { visit_assertion_echo(node); }
        else if("assume_statement".equals(name)) { visit_assume_statement(node); }
        else if("assumption_echo_opt".equals(name)) { visit_assumption_echo_opt(node); }
        else if("assumption_echo_empty".equals(name)) { visit_assumption_echo_empty(node); }
        else if("assumption_echo".equals(name)) { visit_assumption_echo(node); }
        else if("throw_statement".equals(name)) { visit_throw_statement(node); }
        else if("try_catch_statement".equals(name)) { visit_try_catch_statement(node); }
        else if("trycatch_body".equals(name)) { visit_trycatch_body(node); }
        else if("trycatch_handlers".equals(name)) { visit_trycatch_handlers(node); }
        else if("trycatch_handler".equals(name)) { visit_trycatch_handler(node); }
        else if("trycatch_vardec".equals(name)) { visit_trycatch_vardec(node); }
        else if("break_statement".equals(name)) { visit_break_statement(node); }
        else if("continue_statement".equals(name)) { visit_continue_statement(node); }
        else if("redo_statement".equals(name)) { visit_redo_statement(node); }
        else if("for_statement".equals(name)) { visit_for_statement(node); }
        else if("for_controller".equals(name)) { visit_for_controller(node); }
        else if("for_init".equals(name)) { visit_for_init(node); }
        else if("for_condition".equals(name)) { visit_for_condition(node); }
        else if("for_modifier".equals(name)) { visit_for_modifier(node); }
        else if("for_body".equals(name)) { visit_for_body(node); }
        else if("foreach_statement".equals(name)) { visit_foreach_statement(node); }
        else if("foreach_controller".equals(name)) { visit_foreach_controller(node); }
        else if("foreach_vardec".equals(name)) { visit_foreach_vardec(node); }
        else if("foreach_iterator".equals(name)) { visit_foreach_iterator(node); }
        else if("foreach_body".equals(name)) { visit_foreach_body(node); }
        else if("forever_statement".equals(name)) { visit_forever_statement(node); }
        else if("until_statement".equals(name)) { visit_until_statement(node); }
        else if("while_statement".equals(name)) { visit_while_statement(node); }
        else if("do_statement".equals(name)) { visit_do_statement(node); }
        else if("do_until".equals(name)) { visit_do_until(node); }
        else if("do_until_trigger".equals(name)) { visit_do_until_trigger(node); }
        else if("do_while".equals(name)) { visit_do_while(node); }
        else if("do_while_trigger".equals(name)) { visit_do_while_trigger(node); }
        else if("var_statement".equals(name)) { visit_var_statement(node); }
        else if("val_statement".equals(name)) { visit_val_statement(node); }
        else if("let_statement".equals(name)) { visit_let_statement(node); }
        else if("lambda_statement".equals(name)) { visit_lambda_statement(node); }
        else if("delegate_statement".equals(name)) { visit_delegate_statement(node); }
        else if("recur_statement".equals(name)) { visit_recur_statement(node); }
        else if("return_void_statement".equals(name)) { visit_return_void_statement(node); }
        else if("return_value_statement".equals(name)) { visit_return_value_statement(node); }
        else if("yield_void_statement".equals(name)) { visit_yield_void_statement(node); }
        else if("yield_value_statement".equals(name)) { visit_yield_value_statement(node); }
        else if("expression".equals(name)) { visit_expression(node); }
        else if("p1".equals(name)) { visit_p1(node); }
        else if("p1_operand".equals(name)) { visit_p1_operand(node); }
        else if("p1_operations".equals(name)) { visit_p1_operations(node); }
        else if("p1_operation".equals(name)) { visit_p1_operation(node); }
        else if("as_operation".equals(name)) { visit_as_operation(node); }
        else if("is_operation".equals(name)) { visit_is_operation(node); }
        else if("p2".equals(name)) { visit_p2(node); }
        else if("p2_operand".equals(name)) { visit_p2_operand(node); }
        else if("p2_operations".equals(name)) { visit_p2_operations(node); }
        else if("p2_operation".equals(name)) { visit_p2_operation(node); }
        else if("null_coalescing_operation".equals(name)) { visit_null_coalescing_operation(node); }
        else if("p3".equals(name)) { visit_p3(node); }
        else if("p3_operand".equals(name)) { visit_p3_operand(node); }
        else if("p3_operations".equals(name)) { visit_p3_operations(node); }
        else if("p3_operation".equals(name)) { visit_p3_operation(node); }
        else if("and_operation".equals(name)) { visit_and_operation(node); }
        else if("or_operation".equals(name)) { visit_or_operation(node); }
        else if("xor_operation".equals(name)) { visit_xor_operation(node); }
        else if("implies_operation".equals(name)) { visit_implies_operation(node); }
        else if("p4".equals(name)) { visit_p4(node); }
        else if("p4_operand".equals(name)) { visit_p4_operand(node); }
        else if("p4_operations".equals(name)) { visit_p4_operations(node); }
        else if("p4_operation".equals(name)) { visit_p4_operation(node); }
        else if("identity_equals_operation".equals(name)) { visit_identity_equals_operation(node); }
        else if("identity_not_equals_operation".equals(name)) { visit_identity_not_equals_operation(node); }
        else if("equals_operation".equals(name)) { visit_equals_operation(node); }
        else if("not_equals_operation".equals(name)) { visit_not_equals_operation(node); }
        else if("greater_or_equals_operation".equals(name)) { visit_greater_or_equals_operation(node); }
        else if("less_or_equals_operation".equals(name)) { visit_less_or_equals_operation(node); }
        else if("greater_operation".equals(name)) { visit_greater_operation(node); }
        else if("less_operation".equals(name)) { visit_less_operation(node); }
        else if("p5".equals(name)) { visit_p5(node); }
        else if("p5_operand".equals(name)) { visit_p5_operand(node); }
        else if("p5_operations".equals(name)) { visit_p5_operations(node); }
        else if("p5_operation".equals(name)) { visit_p5_operation(node); }
        else if("concat_operation".equals(name)) { visit_concat_operation(node); }
        else if("p6".equals(name)) { visit_p6(node); }
        else if("p6_operand".equals(name)) { visit_p6_operand(node); }
        else if("p6_operations".equals(name)) { visit_p6_operations(node); }
        else if("p6_operation".equals(name)) { visit_p6_operation(node); }
        else if("add_operation".equals(name)) { visit_add_operation(node); }
        else if("subtract_operation".equals(name)) { visit_subtract_operation(node); }
        else if("p7".equals(name)) { visit_p7(node); }
        else if("p7_operand".equals(name)) { visit_p7_operand(node); }
        else if("p7_operations".equals(name)) { visit_p7_operations(node); }
        else if("p7_operation".equals(name)) { visit_p7_operation(node); }
        else if("divide_operation".equals(name)) { visit_divide_operation(node); }
        else if("modulo_operation".equals(name)) { visit_modulo_operation(node); }
        else if("multiply_operation".equals(name)) { visit_multiply_operation(node); }
        else if("p8".equals(name)) { visit_p8(node); }
        else if("negate_operation".equals(name)) { visit_negate_operation(node); }
        else if("not_operation".equals(name)) { visit_not_operation(node); }
        else if("keyword_expression".equals(name)) { visit_keyword_expression(node); }
        else if("keyword_expression_body".equals(name)) { visit_keyword_expression_body(node); }
        else if("nested_expression".equals(name)) { visit_nested_expression(node); }
        else if("paren_expression".equals(name)) { visit_paren_expression(node); }
        else if("datum".equals(name)) { visit_datum(node); }
        else if("boolean_datum".equals(name)) { visit_boolean_datum(node); }
        else if("char_datum".equals(name)) { visit_char_datum(node); }
        else if("string_datum".equals(name)) { visit_string_datum(node); }
        else if("byte_datum".equals(name)) { visit_byte_datum(node); }
        else if("short_datum".equals(name)) { visit_short_datum(node); }
        else if("int_datum".equals(name)) { visit_int_datum(node); }
        else if("long_datum".equals(name)) { visit_long_datum(node); }
        else if("float_datum".equals(name)) { visit_float_datum(node); }
        else if("double_datum".equals(name)) { visit_double_datum(node); }
        else if("class_datum".equals(name)) { visit_class_datum(node); }
        else if("null_datum".equals(name)) { visit_null_datum(node); }
        else if("variable_datum".equals(name)) { visit_variable_datum(node); }
        else if("locals_expression".equals(name)) { visit_locals_expression(node); }
        else if("ternary_conditional_expression".equals(name)) { visit_ternary_conditional_expression(node); }
        else if("list_comprehension_expression".equals(name)) { visit_list_comprehension_expression(node); }
        else if("list_comprehension_body".equals(name)) { visit_list_comprehension_body(node); }
        else if("if_opt".equals(name)) { visit_if_opt(node); }
        else if("list_expression".equals(name)) { visit_list_expression(node); }
        else if("progn_expression".equals(name)) { visit_progn_expression(node); }
        else if("dispatch_expression".equals(name)) { visit_dispatch_expression(node); }
        else if("new_expression".equals(name)) { visit_new_expression(node); }
        else if("call_method_expression".equals(name)) { visit_call_method_expression(node); }
        else if("call_static_method_expression".equals(name)) { visit_call_static_method_expression(node); }
        else if("get_field_expression".equals(name)) { visit_get_field_expression(node); }
        else if("set_field_expression".equals(name)) { visit_set_field_expression(node); }
        else if("get_static_expression".equals(name)) { visit_get_static_expression(node); }
        else if("set_static_expression".equals(name)) { visit_set_static_expression(node); }
        else if("instanceof_expression".equals(name)) { visit_instanceof_expression(node); }
        else if("api_comment".equals(name)) { visit_api_comment(node); }
        else if("api_comment_line".equals(name)) { visit_api_comment_line(node); }
        else if("api_comment_text".equals(name)) { visit_api_comment_text(node); }
        else if("annotation_list".equals(name)) { visit_annotation_list(node); }
        else if("annotation".equals(name)) { visit_annotation(node); }
        else if("annotation_type".equals(name)) { visit_annotation_type(node); }
        else if("annotation_assignment_opt".equals(name)) { visit_annotation_assignment_opt(node); }
        else if("annotation_assignment".equals(name)) { visit_annotation_assignment(node); }
        else if("annotation_value".equals(name)) { visit_annotation_value(node); }
        else if("filepath".equals(name)) { visit_filepath(node); }
        else if("namespace".equals(name)) { visit_namespace(node); }
        else if("namespace_explicit".equals(name)) { visit_namespace_explicit(node); }
        else if("namespace_implicit".equals(name)) { visit_namespace_implicit(node); }
        else if("condition".equals(name)) { visit_condition(node); }
        else if("paren_condition".equals(name)) { visit_paren_condition(node); }
        else if("signature".equals(name)) { visit_signature(node); }
        else if("return_type".equals(name)) { visit_return_type(node); }
        else if("supers_opt".equals(name)) { visit_supers_opt(node); }
        else if("supers".equals(name)) { visit_supers(node); }
        else if("supers_list".equals(name)) { visit_supers_list(node); }
        else if("supers_list_cons".equals(name)) { visit_supers_list_cons(node); }
        else if("supers_list_end".equals(name)) { visit_supers_list_end(node); }
        else if("elements".equals(name)) { visit_elements(node); }
        else if("element".equals(name)) { visit_element(node); }
        else if("formals".equals(name)) { visit_formals(node); }
        else if("parameter".equals(name)) { visit_parameter(node); }
        else if("arguments".equals(name)) { visit_arguments(node); }
        else if("argument".equals(name)) { visit_argument(node); }
        else if("name".equals(name)) { visit_name(node); }
        else if("name_id".equals(name)) { visit_name_id(node); }
        else if("type".equals(name)) { visit_type(node); }
        else if("dimensions".equals(name)) { visit_dimensions(node); }
        else if("dimension".equals(name)) { visit_dimension(node); }
        else if("type_name".equals(name)) { visit_type_name(node); }
        else if("type_namespace".equals(name)) { visit_type_namespace(node); }
        else if("type_namespace_explicit".equals(name)) { visit_type_namespace_explicit(node); }
        else if("type_namespace_implicit".equals(name)) { visit_type_namespace_implicit(node); }
        else if("simple_name".equals(name)) { visit_simple_name(node); }
        else if("instance_member_access".equals(name)) { visit_instance_member_access(node); }
        else if("static_member_access".equals(name)) { visit_static_member_access(node); }
        else if("static_owner".equals(name)) { visit_static_owner(node); }
        else if("static_owner_explicit".equals(name)) { visit_static_owner_explicit(node); }
        else if("static_owner_implicit".equals(name)) { visit_static_owner_implicit(node); }
        else if("conditional_case".equals(name)) { visit_conditional_case(node); }
        else if("variable".equals(name)) { visit_variable(node); }
        else if("label".equals(name)) { visit_label(node); }
        else if("boolean_value".equals(name)) { visit_boolean_value(node); }
        else if("true".equals(name)) { visit_true(node); }
        else if("false".equals(name)) { visit_false(node); }
        else if("char_value".equals(name)) { visit_char_value(node); }
        else if("literal_char_value".equals(name)) { visit_literal_char_value(node); }
        else if("numeric_char_value".equals(name)) { visit_numeric_char_value(node); }
        else if("byte_value".equals(name)) { visit_byte_value(node); }
        else if("short_value".equals(name)) { visit_short_value(node); }
        else if("int_value".equals(name)) { visit_int_value(node); }
        else if("long_value".equals(name)) { visit_long_value(node); }
        else if("float_value".equals(name)) { visit_float_value(node); }
        else if("double_value".equals(name)) { visit_double_value(node); }
        else if("string_value".equals(name)) { visit_string_value(node); }
        else if("verbatim_string_value".equals(name)) { visit_verbatim_string_value(node); }
        else if("regular_string_value".equals(name)) { visit_regular_string_value(node); }
        else if("class_value".equals(name)) { visit_class_value(node); }
        else if("null_value".equals(name)) { visit_null_value(node); }
        else if("SEMICOLON".equals(name)) { visit_SEMICOLON(node); }
        else if("ID".equals(name)) { visit_ID(node); }
        else if("DIGITS".equals(name)) { visit_DIGITS(node); }
        else if("DIGIT".equals(name)) { visit_DIGIT(node); }
        else if("EXPONENT_OPT".equals(name)) { visit_EXPONENT_OPT(node); }
        else if("EXPONENT".equals(name)) { visit_EXPONENT(node); }
        else if("STRING_LITERAL".equals(name)) { visit_STRING_LITERAL(node); }
        else if("STRING_LITERAL_STYLE1".equals(name)) { visit_STRING_LITERAL_STYLE1(node); }
        else if("QUOTE".equals(name)) { visit_QUOTE(node); }
        else if("NON_QUOTES".equals(name)) { visit_NON_QUOTES(node); }
        else if("NON_QUOTE".equals(name)) { visit_NON_QUOTE(node); }
        else if("STRING_LITERAL_STYLE2".equals(name)) { visit_STRING_LITERAL_STYLE2(node); }
        else if("TRIPLE_QUOTE".equals(name)) { visit_TRIPLE_QUOTE(node); }
        else if("NON_TRIPLE_QUOTES".equals(name)) { visit_NON_TRIPLE_QUOTES(node); }
        else if("NON_TRIPLE_QUOTE".equals(name)) { visit_NON_TRIPLE_QUOTE(node); }
        else if("ESCAPE_SEQUENCE".equals(name)) { visit_ESCAPE_SEQUENCE(node); }
        else if("ESCAPE_CHAR".equals(name)) { visit_ESCAPE_CHAR(node); }
        else if("WS".equals(name)) { visit_WS(node); }
        else if("COMMENT".equals(name)) { visit_COMMENT(node); }
        else if("SINGLE_LINE_COMMENT".equals(name)) { visit_SINGLE_LINE_COMMENT(node); }
        else if("MULTI_LINE_COMMENT".equals(name)) { visit_MULTI_LINE_COMMENT(node); }
        else if("NEWLINE".equals(name)) { visit_NEWLINE(node); }
        else if("NON_NEWLINE".equals(name)) { visit_NON_NEWLINE(node); }
        else if("NON_NEWLINES".equals(name)) { visit_NON_NEWLINES(node); }
        else if("SP".equals(name)) { visit_SP(node); }
        else if("PS".equals(name)) { visit_PS(node); }
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
     * This method visits a parse-tree node created by rule "COMMENT".
     */
    protected void visit_COMMENT(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
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
     * This method visits a parse-tree node created by rule "ESCAPE_CHAR".
     */
    protected void visit_ESCAPE_CHAR(ITreeNode node)
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
     * This method visits a parse-tree node created by rule "EXPONENT".
     */
    protected void visit_EXPONENT(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "EXPONENT_OPT".
     */
    protected void visit_EXPONENT_OPT(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "ID".
     */
    protected void visit_ID(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "MULTI_LINE_COMMENT".
     */
    protected void visit_MULTI_LINE_COMMENT(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "NEWLINE".
     */
    protected void visit_NEWLINE(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "NON_NEWLINE".
     */
    protected void visit_NON_NEWLINE(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "NON_NEWLINES".
     */
    protected void visit_NON_NEWLINES(ITreeNode node)
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
     * This method visits a parse-tree node created by rule "NON_QUOTES".
     */
    protected void visit_NON_QUOTES(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "NON_TRIPLE_QUOTE".
     */
    protected void visit_NON_TRIPLE_QUOTE(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "NON_TRIPLE_QUOTES".
     */
    protected void visit_NON_TRIPLE_QUOTES(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "PS".
     */
    protected void visit_PS(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "QUOTE".
     */
    protected void visit_QUOTE(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "SEMICOLON".
     */
    protected void visit_SEMICOLON(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "SINGLE_LINE_COMMENT".
     */
    protected void visit_SINGLE_LINE_COMMENT(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "SP".
     */
    protected void visit_SP(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "STRING_LITERAL".
     */
    protected void visit_STRING_LITERAL(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "STRING_LITERAL_STYLE1".
     */
    protected void visit_STRING_LITERAL_STYLE1(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "STRING_LITERAL_STYLE2".
     */
    protected void visit_STRING_LITERAL_STYLE2(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "TRIPLE_QUOTE".
     */
    protected void visit_TRIPLE_QUOTE(ITreeNode node)
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
     * This method visits a parse-tree node created by rule "add_operation".
     */
    protected void visit_add_operation(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "and_operation".
     */
    protected void visit_and_operation(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "annotation".
     */
    protected void visit_annotation(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "annotation_assignment".
     */
    protected void visit_annotation_assignment(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "annotation_assignment_opt".
     */
    protected void visit_annotation_assignment_opt(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "annotation_definition".
     */
    protected void visit_annotation_definition(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "annotation_list".
     */
    protected void visit_annotation_list(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "annotation_type".
     */
    protected void visit_annotation_type(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "annotation_value".
     */
    protected void visit_annotation_value(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "api_comment".
     */
    protected void visit_api_comment(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "api_comment_line".
     */
    protected void visit_api_comment_line(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "api_comment_text".
     */
    protected void visit_api_comment_text(ITreeNode node)
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
     * This method visits a parse-tree node created by rule "as_operation".
     */
    protected void visit_as_operation(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "assert_statement".
     */
    protected void visit_assert_statement(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "assertion_echo".
     */
    protected void visit_assertion_echo(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "assertion_echo_empty".
     */
    protected void visit_assertion_echo_empty(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "assertion_echo_opt".
     */
    protected void visit_assertion_echo_opt(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "assume_statement".
     */
    protected void visit_assume_statement(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "assumption_echo".
     */
    protected void visit_assumption_echo(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "assumption_echo_empty".
     */
    protected void visit_assumption_echo_empty(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "assumption_echo_opt".
     */
    protected void visit_assumption_echo_opt(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "boolean_datum".
     */
    protected void visit_boolean_datum(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "boolean_value".
     */
    protected void visit_boolean_value(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "branch_statement".
     */
    protected void visit_branch_statement(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "break_statement".
     */
    protected void visit_break_statement(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "byte_datum".
     */
    protected void visit_byte_datum(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "byte_value".
     */
    protected void visit_byte_value(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "call_method_expression".
     */
    protected void visit_call_method_expression(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "call_static_method_expression".
     */
    protected void visit_call_static_method_expression(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "char_datum".
     */
    protected void visit_char_datum(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "char_value".
     */
    protected void visit_char_value(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "class_datum".
     */
    protected void visit_class_datum(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "class_value".
     */
    protected void visit_class_value(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "concat_operation".
     */
    protected void visit_concat_operation(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "condition".
     */
    protected void visit_condition(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "conditional_case".
     */
    protected void visit_conditional_case(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "continue_statement".
     */
    protected void visit_continue_statement(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "datum".
     */
    protected void visit_datum(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "debug_statement".
     */
    protected void visit_debug_statement(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "definition".
     */
    protected void visit_definition(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "delegate_statement".
     */
    protected void visit_delegate_statement(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "design_definition".
     */
    protected void visit_design_definition(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "dimension".
     */
    protected void visit_dimension(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "dimensions".
     */
    protected void visit_dimensions(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "dispatch_expression".
     */
    protected void visit_dispatch_expression(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "divide_operation".
     */
    protected void visit_divide_operation(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "do_statement".
     */
    protected void visit_do_statement(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "do_until".
     */
    protected void visit_do_until(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "do_until_trigger".
     */
    protected void visit_do_until_trigger(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "do_while".
     */
    protected void visit_do_while(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "do_while_trigger".
     */
    protected void visit_do_while_trigger(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "double_datum".
     */
    protected void visit_double_datum(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "double_value".
     */
    protected void visit_double_value(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "element".
     */
    protected void visit_element(ITreeNode node)
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
     * This method visits a parse-tree node created by rule "elif_case".
     */
    protected void visit_elif_case(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "elif_cases_opt".
     */
    protected void visit_elif_cases_opt(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "else_case".
     */
    protected void visit_else_case(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "else_case_opt".
     */
    protected void visit_else_case_opt(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "enum_constant".
     */
    protected void visit_enum_constant(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "enum_constant_list".
     */
    protected void visit_enum_constant_list(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "enum_definition".
     */
    protected void visit_enum_definition(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "equals_operation".
     */
    protected void visit_equals_operation(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "exception_definition".
     */
    protected void visit_exception_definition(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "expression".
     */
    protected void visit_expression(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "expression_statement".
     */
    protected void visit_expression_statement(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "false".
     */
    protected void visit_false(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "filepath".
     */
    protected void visit_filepath(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "float_datum".
     */
    protected void visit_float_datum(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "float_value".
     */
    protected void visit_float_value(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "for_body".
     */
    protected void visit_for_body(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "for_condition".
     */
    protected void visit_for_condition(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "for_controller".
     */
    protected void visit_for_controller(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "for_init".
     */
    protected void visit_for_init(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "for_modifier".
     */
    protected void visit_for_modifier(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "for_statement".
     */
    protected void visit_for_statement(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "foreach_body".
     */
    protected void visit_foreach_body(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "foreach_controller".
     */
    protected void visit_foreach_controller(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "foreach_iterator".
     */
    protected void visit_foreach_iterator(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "foreach_statement".
     */
    protected void visit_foreach_statement(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "foreach_vardec".
     */
    protected void visit_foreach_vardec(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "forever_statement".
     */
    protected void visit_forever_statement(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "formals".
     */
    protected void visit_formals(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "function_definition".
     */
    protected void visit_function_definition(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "functor_definition".
     */
    protected void visit_functor_definition(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "get_field_expression".
     */
    protected void visit_get_field_expression(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "get_static_expression".
     */
    protected void visit_get_static_expression(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "goto_statement".
     */
    protected void visit_goto_statement(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "greater_operation".
     */
    protected void visit_greater_operation(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "greater_or_equals_operation".
     */
    protected void visit_greater_or_equals_operation(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "identity_equals_operation".
     */
    protected void visit_identity_equals_operation(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "identity_not_equals_operation".
     */
    protected void visit_identity_not_equals_operation(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "if_case".
     */
    protected void visit_if_case(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "if_opt".
     */
    protected void visit_if_opt(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "if_statement".
     */
    protected void visit_if_statement(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "implies_operation".
     */
    protected void visit_implies_operation(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "import_directive".
     */
    protected void visit_import_directive(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "index".
     */
    protected void visit_index(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "instance_member_access".
     */
    protected void visit_instance_member_access(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "instanceof_expression".
     */
    protected void visit_instanceof_expression(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "int_datum".
     */
    protected void visit_int_datum(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "int_value".
     */
    protected void visit_int_value(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "is_operation".
     */
    protected void visit_is_operation(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "keyword_expression".
     */
    protected void visit_keyword_expression(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "keyword_expression_body".
     */
    protected void visit_keyword_expression_body(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "label".
     */
    protected void visit_label(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "labels".
     */
    protected void visit_labels(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "lambda_statement".
     */
    protected void visit_lambda_statement(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "less_operation".
     */
    protected void visit_less_operation(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "less_or_equals_operation".
     */
    protected void visit_less_or_equals_operation(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "let_statement".
     */
    protected void visit_let_statement(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "list_comprehension_body".
     */
    protected void visit_list_comprehension_body(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "list_comprehension_expression".
     */
    protected void visit_list_comprehension_expression(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "list_expression".
     */
    protected void visit_list_expression(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "literal_char_value".
     */
    protected void visit_literal_char_value(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "locals_expression".
     */
    protected void visit_locals_expression(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "long_datum".
     */
    protected void visit_long_datum(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "long_value".
     */
    protected void visit_long_value(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "marker_statement".
     */
    protected void visit_marker_statement(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "module".
     */
    protected void visit_module(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "module_directive".
     */
    protected void visit_module_directive(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "module_member".
     */
    protected void visit_module_member(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "module_members".
     */
    protected void visit_module_members(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "module_name".
     */
    protected void visit_module_name(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "modulo_operation".
     */
    protected void visit_modulo_operation(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "multiply_operation".
     */
    protected void visit_multiply_operation(ITreeNode node)
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
     * This method visits a parse-tree node created by rule "name_id".
     */
    protected void visit_name_id(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "name_of_anonymous_module".
     */
    protected void visit_name_of_anonymous_module(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "name_of_named_module".
     */
    protected void visit_name_of_named_module(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "namespace".
     */
    protected void visit_namespace(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "namespace_explicit".
     */
    protected void visit_namespace_explicit(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "namespace_implicit".
     */
    protected void visit_namespace_implicit(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "negate_operation".
     */
    protected void visit_negate_operation(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "nested_expression".
     */
    protected void visit_nested_expression(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "new_expression".
     */
    protected void visit_new_expression(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "nop_statement".
     */
    protected void visit_nop_statement(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "not_equals_operation".
     */
    protected void visit_not_equals_operation(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "not_operation".
     */
    protected void visit_not_operation(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "null_coalescing_operation".
     */
    protected void visit_null_coalescing_operation(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "null_datum".
     */
    protected void visit_null_datum(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "null_value".
     */
    protected void visit_null_value(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "numeric_char_value".
     */
    protected void visit_numeric_char_value(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "or_operation".
     */
    protected void visit_or_operation(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "p1".
     */
    protected void visit_p1(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "p1_operand".
     */
    protected void visit_p1_operand(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "p1_operation".
     */
    protected void visit_p1_operation(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "p1_operations".
     */
    protected void visit_p1_operations(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "p2".
     */
    protected void visit_p2(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "p2_operand".
     */
    protected void visit_p2_operand(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "p2_operation".
     */
    protected void visit_p2_operation(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "p2_operations".
     */
    protected void visit_p2_operations(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "p3".
     */
    protected void visit_p3(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "p3_operand".
     */
    protected void visit_p3_operand(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "p3_operation".
     */
    protected void visit_p3_operation(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "p3_operations".
     */
    protected void visit_p3_operations(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "p4".
     */
    protected void visit_p4(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "p4_operand".
     */
    protected void visit_p4_operand(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "p4_operation".
     */
    protected void visit_p4_operation(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "p4_operations".
     */
    protected void visit_p4_operations(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "p5".
     */
    protected void visit_p5(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "p5_operand".
     */
    protected void visit_p5_operand(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "p5_operation".
     */
    protected void visit_p5_operation(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "p5_operations".
     */
    protected void visit_p5_operations(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "p6".
     */
    protected void visit_p6(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "p6_operand".
     */
    protected void visit_p6_operand(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "p6_operation".
     */
    protected void visit_p6_operation(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "p6_operations".
     */
    protected void visit_p6_operations(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "p7".
     */
    protected void visit_p7(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "p7_operand".
     */
    protected void visit_p7_operand(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "p7_operation".
     */
    protected void visit_p7_operation(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "p7_operations".
     */
    protected void visit_p7_operations(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "p8".
     */
    protected void visit_p8(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "parameter".
     */
    protected void visit_parameter(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "paren_condition".
     */
    protected void visit_paren_condition(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "paren_expression".
     */
    protected void visit_paren_expression(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "progn_expression".
     */
    protected void visit_progn_expression(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "recur_statement".
     */
    protected void visit_recur_statement(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "redo_statement".
     */
    protected void visit_redo_statement(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "regular_string_value".
     */
    protected void visit_regular_string_value(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "return_type".
     */
    protected void visit_return_type(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "return_value_statement".
     */
    protected void visit_return_value_statement(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "return_void_statement".
     */
    protected void visit_return_void_statement(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "sequence_statement".
     */
    protected void visit_sequence_statement(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "set_field_expression".
     */
    protected void visit_set_field_expression(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "set_static_expression".
     */
    protected void visit_set_static_expression(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "short_datum".
     */
    protected void visit_short_datum(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "short_value".
     */
    protected void visit_short_value(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "signature".
     */
    protected void visit_signature(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "simple_name".
     */
    protected void visit_simple_name(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "statement".
     */
    protected void visit_statement(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "statements".
     */
    protected void visit_statements(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "static_member_access".
     */
    protected void visit_static_member_access(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "static_owner".
     */
    protected void visit_static_owner(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "static_owner_explicit".
     */
    protected void visit_static_owner_explicit(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "static_owner_implicit".
     */
    protected void visit_static_owner_implicit(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "string_datum".
     */
    protected void visit_string_datum(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "string_value".
     */
    protected void visit_string_value(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "struct_definition".
     */
    protected void visit_struct_definition(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "subtract_operation".
     */
    protected void visit_subtract_operation(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "super_functor".
     */
    protected void visit_super_functor(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "supers".
     */
    protected void visit_supers(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "supers_list".
     */
    protected void visit_supers_list(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "supers_list_cons".
     */
    protected void visit_supers_list_cons(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "supers_list_end".
     */
    protected void visit_supers_list_end(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "supers_opt".
     */
    protected void visit_supers_opt(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "ternary_conditional_expression".
     */
    protected void visit_ternary_conditional_expression(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "throw_statement".
     */
    protected void visit_throw_statement(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "true".
     */
    protected void visit_true(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "try_catch_statement".
     */
    protected void visit_try_catch_statement(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "trycatch_body".
     */
    protected void visit_trycatch_body(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "trycatch_handler".
     */
    protected void visit_trycatch_handler(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "trycatch_handlers".
     */
    protected void visit_trycatch_handlers(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "trycatch_vardec".
     */
    protected void visit_trycatch_vardec(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "tuple_definition".
     */
    protected void visit_tuple_definition(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "type".
     */
    protected void visit_type(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "type_name".
     */
    protected void visit_type_name(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "type_namespace".
     */
    protected void visit_type_namespace(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "type_namespace_explicit".
     */
    protected void visit_type_namespace_explicit(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "type_namespace_implicit".
     */
    protected void visit_type_namespace_implicit(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "until_statement".
     */
    protected void visit_until_statement(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "val_statement".
     */
    protected void visit_val_statement(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "var_statement".
     */
    protected void visit_var_statement(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "variable".
     */
    protected void visit_variable(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "variable_datum".
     */
    protected void visit_variable_datum(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "verbatim_string_value".
     */
    protected void visit_verbatim_string_value(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "when_statement".
     */
    protected void visit_when_statement(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "while_statement".
     */
    protected void visit_while_statement(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "xor_operation".
     */
    protected void visit_xor_operation(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "yield_value_statement".
     */
    protected void visit_yield_value_statement(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

    /**
     * This method visits a parse-tree node created by rule "yield_void_statement".
     */
    protected void visit_yield_void_statement(ITreeNode node)
    {
        // You should *not* place your code right here. 
        // Instead, you should override this method via a subclass.
        visitUnknown(node); // Default Behavior
    }

}
