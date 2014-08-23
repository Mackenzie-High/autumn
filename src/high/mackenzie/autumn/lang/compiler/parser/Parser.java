package high.mackenzie.autumn.lang.compiler.parser;

import high.mackenzie.snowflake.Grammar;
import high.mackenzie.snowflake.GrammarBuilder;
import high.mackenzie.snowflake.IParser;
import high.mackenzie.snowflake.ParserOutput;


/**
 * This class was auto-generated using the Snowflake parser-generator.
 *
 * <p>Generated On: Sat Aug 23 06:16:09 EDT 2014</p>
 */
public final class Parser implements IParser
{

    private static final Grammar grammar = grammar();


    /**
     * This method constructs the grammar object.
     */
    private static Grammar grammar()
    {
        final GrammarBuilder g = new GrammarBuilder();

        // Character Classes
        g.range("@class0", (char) 58, (char) 58);
        g.range("@class1", (char) 123, (char) 123);
        g.range("@class2", (char) 125, (char) 125);
        g.range("@class3", (char) 58, (char) 58);
        g.range("@class4", (char) 58, (char) 58);
        g.range("@class5", (char) 40, (char) 40);
        g.range("@class6", (char) 44, (char) 44);
        g.range("@class7", (char) 41, (char) 41);
        g.range("@class8", (char) 123, (char) 123);
        g.range("@class9", (char) 125, (char) 125);
        g.range("@class10", (char) 40, (char) 40);
        g.range("@class11", (char) 58, (char) 58);
        g.range("@class12", (char) 41, (char) 41);
        g.range("@class13", (char) 40, (char) 40);
        g.range("@class14", (char) 41, (char) 41);
        g.range("@class15", (char) 61, (char) 61);
        g.range("@class16", (char) 40, (char) 40);
        g.range("@class17", (char) 41, (char) 41);
        g.range("@class18", (char) 58, (char) 58);
        g.range("@class19", (char) 61, (char) 61);
        g.range("@class20", (char) 61, (char) 61);
        g.range("@class21", (char) 61, (char) 61);
        g.range("@class22", (char) 58, (char) 58);
        g.range("@class23", (char) 58, (char) 58);
        g.range("@class24", (char) 46, (char) 46);
        g.range("@class25", (char) 46, (char) 46);
        g.range("@class26", (char) 46, (char) 46);
        g.range("@class27", (char) 38, (char) 38);
        g.range("@class28", (char) 124, (char) 124);
        g.range("@class29", (char) 94, (char) 94);
        g.range("@class30", (char) 62, (char) 62);
        g.range("@class31", (char) 60, (char) 60);
        g.range("@class32", (char) 43, (char) 43);
        g.range("@class33", (char) 45, (char) 45);
        g.range("@class34", (char) 47, (char) 47);
        g.range("@class35", (char) 37, (char) 37);
        g.range("@class36", (char) 42, (char) 42);
        g.range("@class37", (char) 45, (char) 45);
        g.range("@class38", (char) 33, (char) 33);
        g.range("@class39", (char) 40, (char) 40);
        g.range("@class40", (char) 41, (char) 41);
        g.range("@class41", (char) 40, (char) 40);
        g.range("@class42", (char) 41, (char) 41);
        g.range("@class43", (char) 91, (char) 91);
        g.range("@class44", (char) 93, (char) 93);
        g.range("@class45", (char) 61, (char) 61);
        g.range("@class46", (char) 61, (char) 61);
        g.range("@class47", (char) 58, (char) 58);
        g.range("@class48", (char) 10, (char) 10);
        g.range("@class49", (char) 13, (char) 13);
        g.combine("@class50", "@class48", "@class49");
        g.negate("@class51", "@class50");
        g.range("@class52", (char) 64, (char) 64);
        g.range("@class53", (char) 46, (char) 46);
        g.range("@class54", (char) 58, (char) 58);
        g.range("@class55", (char) 40, (char) 40);
        g.range("@class56", (char) 44, (char) 44);
        g.range("@class57", (char) 41, (char) 41);
        g.range("@class58", (char) 58, (char) 58);
        g.range("@class59", (char) 44, (char) 44);
        g.range("@class60", (char) 91, (char) 91);
        g.range("@class61", (char) 93, (char) 93);
        g.range("@class62", (char) 46, (char) 46);
        g.range("@class63", (char) 46, (char) 46);
        g.range("@class64", (char) 0, (char) 65535);
        g.range("@class65", (char) 67, (char) 67);
        g.range("@class66", (char) 45, (char) 45);
        g.range("@class67", (char) 66, (char) 66);
        g.range("@class68", (char) 45, (char) 45);
        g.range("@class69", (char) 83, (char) 83);
        g.range("@class70", (char) 45, (char) 45);
        g.range("@class71", (char) 45, (char) 45);
        g.range("@class72", (char) 76, (char) 76);
        g.range("@class73", (char) 45, (char) 45);
        g.range("@class74", (char) 46, (char) 46);
        g.range("@class75", (char) 70, (char) 70);
        g.range("@class76", (char) 45, (char) 45);
        g.range("@class77", (char) 46, (char) 46);
        g.range("@class78", (char) 64, (char) 64);
        g.range("@class79", (char) 40, (char) 40);
        g.range("@class80", (char) 41, (char) 41);
        g.range("@class81", (char) 59, (char) 59);
        g.range("@class82", (char) 65, (char) 90);
        g.range("@class83", (char) 97, (char) 122);
        g.range("@class84", (char) 95, (char) 95);
        g.range("@class85", (char) 36, (char) 36);
        g.combine("@class86", "@class82", "@class83", "@class84", "@class85");
        g.range("@class87", (char) 65, (char) 90);
        g.range("@class88", (char) 97, (char) 122);
        g.range("@class89", (char) 95, (char) 95);
        g.range("@class90", (char) 36, (char) 36);
        g.range("@class91", (char) 48, (char) 57);
        g.combine("@class92", "@class87", "@class88", "@class89", "@class90", "@class91");
        g.range("@class93", (char) 48, (char) 57);
        g.range("@class94", (char) 101, (char) 101);
        g.range("@class95", (char) 69, (char) 69);
        g.combine("@class96", "@class94", "@class95");
        g.range("@class97", (char) 45, (char) 45);
        g.range("@class98", (char) 48, (char) 57);
        g.combine("@class99", "@class97", "@class98");
        g.range("@class100", (char) 48, (char) 57);
        g.range("@class101", (char) 34, (char) 34);
        g.range("@class102", (char) 34, (char) 34);
        g.combine("@class103", "@class102");
        g.negate("@class104", "@class103");
        g.range("@class105", (char) 39, (char) 39);
        g.combine("@class106", "@class105");
        g.negate("@class107", "@class106");
        g.range("@class108", (char) 35, (char) 35);
        g.range("@class109", (char) 10, (char) 10);
        g.range("@class110", (char) 13, (char) 13);
        g.combine("@class111", "@class109", "@class110");
        g.negate("@class112", "@class111");
        g.range("@class113", (char) 10, (char) 10);
        g.range("@class114", (char) 13, (char) 13);
        g.combine("@class115", "@class113", "@class114");
        g.range("@class116", (char) 0, (char) 65535);
        g.combine("@class117", "@class116");
        g.range("@class118", (char) 10, (char) 10);
        g.range("@class119", (char) 13, (char) 13);
        g.combine("@class120", "@class118", "@class119");
        g.range("@class121", (char) 10, (char) 10);
        g.range("@class122", (char) 13, (char) 13);
        g.combine("@class123", "@class121", "@class122");
        g.negate("@class124", "@class123");
        g.range("@class125", (char) 32, (char) 32);
        g.range("@class126", (char) 9, (char) 9);
        g.range("@class127", (char) 10, (char) 10);
        g.range("@class128", (char) 11, (char) 11);
        g.range("@class129", (char) 12, (char) 12);
        g.range("@class130", (char) 13, (char) 13);
        g.combine("@class131", "@class125", "@class126", "@class127", "@class128", "@class129", "@class130");

        // Grammar Rules
        g.choose("@224", "SP", "COMMENT");
        g.choose("@227", "@225", "@226");
        g.choose("@64", "do_until", "do_while");
        g.choose("COMMENT", "MULTI_LINE_COMMENT", "SINGLE_LINE_COMMENT");
        g.choose("NON_TRIPLE_QUOTE", "@222", "@223");
        g.choose("STRING_LITERAL", "STRING_LITERAL_STYLE1", "STRING_LITERAL_STYLE2");
        g.choose("assertion_echo_opt", "assertion_echo", "assertion_echo_empty");
        g.choose("assumption_echo_opt", "assumption_echo", "assumption_echo_empty");
        g.choose("boolean_value", "true", "false");
        g.choose("char_value", "literal_char_value", "numeric_char_value");
        g.choose("datum", "string_datum", "boolean_datum", "char_datum", "float_datum", "double_datum", "long_datum", "short_datum", "byte_datum", "int_datum", "null_datum", "class_datum", "variable_datum");
        g.choose("definition", "function_definition", "exception_definition", "tuple_definition", "functor_definition", "design_definition", "enum_definition", "annotation_definition");
        g.choose("design_extends_opt", "design_extends", "design_extends_empty");
        g.choose("design_member", "property", "method");
        g.choose("expression", "p1", "keyword_expression", "nested_expression");
        g.choose("extends_list", "extends_list_cons", "extends_list_end");
        g.choose("keyword_expression_body", "locals_expression", "dispatch_expression", "new_expression", "call_static_method_expression", "call_method_expression", "set_static_expression", "get_static_expression", "set_field_expression", "get_field_expression", "instanceof_expression", "create_expression", "ternary_conditional_expression", "progn_expression");
        g.choose("module_member", "module_directive", "import_directive", "definition");
        g.choose("module_name", "name_of_anonymous_module", "name_of_named_module");
        g.choose("namespace", "namespace_explicit", "namespace_implicit");
        g.choose("p1_operation", "as_operation", "is_operation");
        g.choose("p3_operation", "short_circuit_and_operation", "short_circuit_or_operation", "and_operation", "or_operation", "xor_operation", "implies_operation");
        g.choose("p4_operation", "identity_equals_operation", "identity_not_equals_operation", "equals_operation", "not_equals_operation", "greater_or_equals_operation", "less_or_equals_operation", "greater_operation", "less_operation");
        g.choose("p6_operation", "add_operation", "subtract_operation");
        g.choose("p7_operation", "divide_operation", "modulo_operation", "multiply_operation");
        g.choose("p8", "datum", "negate_operation", "not_operation", "keyword_expression", "nested_expression", "list_expression");
        g.choose("statement", "sequence_statement", "if_statement", "when_statement", "goto_statement", "marker_statement", "do_statement", "break_statement", "continue_statement", "redo_statement", "foreach_statement", "for_statement", "until_statement", "while_statement", "forever_statement", "assert_statement", "assume_statement", "throw_statement", "try_catch_statement", "var_statement", "val_statement", "let_statement", "lambda_statement", "delegate_statement", "setter_statement", "getter_statement", "method_statement", "nop_statement", "debug_statement", "return_value_statement", "return_void_statement", "recur_statement", "yield_value_statement", "yield_void_statement", "expression_statement");
        g.choose("string_value", "verbatim_string_value", "regular_string_value");
        g.choose("type_namespace", "type_namespace_explicit", "type_namespace_implicit");
        g.chr("@10", "@class1");
        g.chr("@100", "@class27");
        g.chr("@101", "@class28");
        g.chr("@102", "@class29");
        g.chr("@11", "@class2");
        g.chr("@110", "@class30");
        g.chr("@111", "@class31");
        g.chr("@113", "@class32");
        g.chr("@114", "@class33");
        g.chr("@115", "@class34");
        g.chr("@116", "@class35");
        g.chr("@117", "@class36");
        g.chr("@118", "@class37");
        g.chr("@119", "@class38");
        g.chr("@120", "@class39");
        g.chr("@121", "@class40");
        g.chr("@122", "@class41");
        g.chr("@123", "@class42");
        g.chr("@130", "@class43");
        g.chr("@131", "@class44");
        g.chr("@143", "@class45");
        g.chr("@146", "@class46");
        g.chr("@148", "@class47");
        g.chr("@15", "@class3");
        g.chr("@152", "@class51");
        g.chr("@154", "@class52");
        g.chr("@155", "@class53");
        g.chr("@159", "@class54");
        g.chr("@160", "@class55");
        g.chr("@161", "@class56");
        g.chr("@166", "@class57");
        g.chr("@167", "@class58");
        g.chr("@168", "@class59");
        g.chr("@17", "@class4");
        g.chr("@173", "@class60");
        g.chr("@174", "@class61");
        g.chr("@175", "@class62");
        g.chr("@178", "@class63");
        g.chr("@185", "@class64");
        g.chr("@187", "@class65");
        g.chr("@188", "@class66");
        g.chr("@19", "@class5");
        g.chr("@190", "@class67");
        g.chr("@191", "@class68");
        g.chr("@193", "@class69");
        g.chr("@194", "@class70");
        g.chr("@196", "@class71");
        g.chr("@198", "@class72");
        g.chr("@199", "@class73");
        g.chr("@20", "@class6");
        g.chr("@201", "@class74");
        g.chr("@202", "@class75");
        g.chr("@203", "@class76");
        g.chr("@205", "@class77");
        g.chr("@206", "@class78");
        g.chr("@207", "@class79");
        g.chr("@209", "@class80");
        g.chr("@210", "@class81");
        g.chr("@211", "@class86");
        g.chr("@212", "@class92");
        g.chr("@214", "@class93");
        g.chr("@215", "@class96");
        g.chr("@216", "@class99");
        g.chr("@217", "@class100");
        g.chr("@223", "@class107");
        g.chr("@226", "@class108");
        g.chr("@228", "@class112");
        g.chr("@230", "@class115");
        g.chr("@234", "@class117");
        g.chr("@25", "@class7");
        g.chr("@27", "@class8");
        g.chr("@28", "@class9");
        g.chr("@45", "@class10");
        g.chr("@46", "@class11");
        g.chr("@47", "@class12");
        g.chr("@52", "@class13");
        g.chr("@53", "@class14");
        g.chr("@54", "@class15");
        g.chr("@56", "@class16");
        g.chr("@58", "@class17");
        g.chr("@59", "@class18");
        g.chr("@68", "@class19");
        g.chr("@70", "@class20");
        g.chr("@74", "@class21");
        g.chr("@76", "@class22");
        g.chr("@79", "@class23");
        g.chr("@8", "@class0");
        g.chr("@82", "@class24");
        g.chr("@85", "@class25");
        g.chr("@88", "@class26");
        g.chr("NEWLINE", "@class120");
        g.chr("NON_NEWLINE", "@class124");
        g.chr("NON_QUOTE", "@class104");
        g.chr("QUOTE", "@class101");
        g.chr("SP", "@class131");
        g.not("@125", "ID");
        g.not("@181", "ID");
        g.not("@183", "ID");
        g.not("@221", "@220");
        g.not("@233", "@232");
        g.repeat("@137", "@136", 0, 1);
        g.repeat("@140", "@139", 0, 1);
        g.repeat("@150", "api_comment_line", 0, 2147483647);
        g.repeat("@153", "annotation", 0, 2147483647);
        g.repeat("@157", "@156", 0, 2147483647);
        g.repeat("@163", "@162", 0, 2147483647);
        g.repeat("@165", "@164", 0, 1);
        g.repeat("@170", "@169", 0, 2147483647);
        g.repeat("@177", "@176", 1, 2147483647);
        g.repeat("@189", "@188", 0, 1);
        g.repeat("@192", "@191", 0, 1);
        g.repeat("@195", "@194", 0, 1);
        g.repeat("@197", "@196", 0, 1);
        g.repeat("@200", "@199", 0, 1);
        g.repeat("@204", "@203", 0, 1);
        g.repeat("@213", "@212", 0, 2147483647);
        g.repeat("@218", "@217", 0, 2147483647);
        g.repeat("@22", "@21", 0, 2147483647);
        g.repeat("@229", "@228", 0, 2147483647);
        g.repeat("@236", "@235", 0, 2147483647);
        g.repeat("@24", "@23", 0, 1);
        g.repeat("@73", "@72", 0, 1);
        g.repeat("DIGITS", "@214", 1, 2147483647);
        g.repeat("EXPONENT_OPT", "EXPONENT", 0, 1);
        g.repeat("NON_NEWLINES", "NON_NEWLINE", 0, 2147483647);
        g.repeat("NON_QUOTES", "NON_QUOTE", 0, 2147483647);
        g.repeat("NON_TRIPLE_QUOTES", "NON_TRIPLE_QUOTE", 0, 2147483647);
        g.repeat("WS", "@224", 0, 2147483647);
        g.repeat("api_comment_text", "@152", 0, 2147483647);
        g.repeat("arguments", "@171", 0, 1);
        g.repeat("design_members", "design_member", 0, 2147483647);
        g.repeat("dimensions", "@172", 0, 2147483647);
        g.repeat("elif_cases_opt", "elif_case", 0, 2147483647);
        g.repeat("else_case_opt", "else_case", 0, 1);
        g.repeat("module_members", "module_member", 0, 2147483647);
        g.repeat("p1_operations", "p1_operation", 0, 2147483647);
        g.repeat("p2_operations", "p2_operation", 0, 2147483647);
        g.repeat("p3_operations", "p3_operation", 0, 2147483647);
        g.repeat("p4_operations", "p4_operation", 0, 2147483647);
        g.repeat("p5_operations", "p5_operation", 0, 2147483647);
        g.repeat("p6_operations", "p6_operation", 0, 2147483647);
        g.repeat("p7_operations", "p7_operation", 0, 2147483647);
        g.repeat("statements", "statement", 0, 2147483647);
        g.repeat("trycatch_handlers", "trycatch_handler", 0, 2147483647);
        g.sequence("@136", "@135", "SP");
        g.sequence("@139", "@138", "SP");
        g.sequence("@156", "@155", "name_id");
        g.sequence("@158", "name_id", "@157");
        g.sequence("@162", "WS", "@161", "WS", "parameter");
        g.sequence("@164", "parameter", "@163");
        g.sequence("@169", "WS", "@168", "WS", "WS", "argument");
        g.sequence("@171", "argument", "@170", "WS");
        g.sequence("@172", "dimension", "WS");
        g.sequence("@176", "name_id", "@175");
        g.sequence("@21", "WS", "@20", "WS", "enum_constant");
        g.sequence("@222", "@219", "@221");
        g.sequence("@23", "enum_constant", "@22");
        g.sequence("@235", "@233", "@234");
        g.sequence("@72", "@71", "SP");
        g.sequence("EXPONENT", "@215", "@216", "@218");
        g.sequence("ID", "@211", "@213");
        g.sequence("MULTI_LINE_COMMENT", "@231", "@236", "@237", "WS");
        g.sequence("SEMICOLON", "@210", "WS");
        g.sequence("SINGLE_LINE_COMMENT", "@227", "@229", "@230");
        g.sequence("STRING_LITERAL_STYLE1", "QUOTE", "NON_QUOTES", "QUOTE");
        g.sequence("STRING_LITERAL_STYLE2", "TRIPLE_QUOTE", "NON_TRIPLE_QUOTES", "TRIPLE_QUOTE");
        g.sequence("add_operation", "@113", "WS", "p6_operand");
        g.sequence("and_operation", "@100", "WS", "p3_operand");
        g.sequence("annotation", "PS", "@154", "WS", "annotation_type", "WS");
        g.sequence("annotation_definition", "PS", "api_comment", "annotation_list", "WS", "@3", "SP", "WS", "name", "WS", "SEMICOLON", "WS");
        g.sequence("annotation_list", "PS", "@153");
        g.sequence("annotation_type", "type");
        g.sequence("api_comment", "PS", "@150");
        g.sequence("api_comment_line", "PS", "WS", "@151", "api_comment_text", "WS");
        g.sequence("argument", "expression");
        g.sequence("as_operation", "@95", "SP", "WS", "type", "WS");
        g.sequence("assert_statement", "PS", "@38", "SP", "WS", "condition", "WS", "assertion_echo_opt", "WS", "SEMICOLON");
        g.sequence("assertion_echo", "@39", "SP", "WS", "expression", "WS");
        g.sequence("assume_statement", "PS", "@40", "SP", "WS", "condition", "WS", "assumption_echo_opt", "WS", "SEMICOLON");
        g.sequence("assumption_echo", "@41", "SP", "WS", "expression", "WS");
        g.sequence("boolean_datum", "boolean_value");
        g.sequence("break_statement", "PS", "@48", "WS", "SEMICOLON");
        g.sequence("byte_datum", "byte_value");
        g.sequence("byte_value", "@189", "DIGITS", "@190", "WS");
        g.sequence("call_method_expression", "PS", "@137", "WS", "instance_member_access", "arguments", "WS");
        g.sequence("call_static_method_expression", "PS", "@140", "WS", "static_member_access", "arguments", "WS");
        g.sequence("char_datum", "char_value");
        g.sequence("class_datum", "class_value", "WS");
        g.sequence("class_value", "@207", "WS", "@208", "SP", "WS", "type", "WS", "@209", "WS");
        g.sequence("concat_operation", "@112", "WS", "p5_operand");
        g.sequence("condition", "expression");
        g.sequence("conditional_case", "PS", "paren_condition", "WS", "sequence_statement", "WS");
        g.sequence("continue_statement", "PS", "@49", "WS", "SEMICOLON");
        g.sequence("create_expression", "PS", "@149", "SP", "WS", "type", "WS");
        g.sequence("debug_statement", "PS", "@37", "WS", "SEMICOLON");
        g.sequence("delegate_statement", "PS", "@78", "SP", "WS", "variable", "WS", "@79", "WS", "type", "WS", "@80", "WS", "static_member_access", "WS", "SEMICOLON");
        g.sequence("design_definition", "PS", "api_comment", "annotation_list", "WS", "@9", "SP", "WS", "name", "WS", "design_extends_opt", "WS", "@10", "WS", "design_members", "@11", "WS");
        g.sequence("design_extends", "@12", "SP", "WS", "extends_list");
        g.sequence("dimension", "@173", "WS", "@174");
        g.sequence("dispatch_expression", "PS", "@133", "SP", "WS", "name", "WS", "arguments", "WS");
        g.sequence("divide_operation", "@115", "WS", "p7_operand");
        g.sequence("do_statement", "PS", "@63", "WS", "sequence_statement", "WS", "@64", "WS");
        g.sequence("do_until", "@65", "WS", "paren_condition", "WS", "do_until_trigger");
        g.sequence("do_while", "@66", "WS", "paren_condition", "WS", "do_while_trigger");
        g.sequence("double_datum", "double_value");
        g.sequence("double_value", "@204", "DIGITS", "@205", "DIGITS", "EXPONENT_OPT", "WS");
        g.sequence("elif_case", "@31", "WS", "conditional_case", "WS");
        g.sequence("else_case", "@32", "WS", "sequence_statement", "WS");
        g.sequence("enum_constant", "name");
        g.sequence("enum_constant_list", "@19", "WS", "@24", "WS", "@25");
        g.sequence("enum_definition", "PS", "api_comment", "annotation_list", "WS", "@18", "SP", "WS", "name", "WS", "enum_constant_list", "WS", "SEMICOLON", "WS");
        g.sequence("equals_operation", "@106", "WS", "p4_operand");
        g.sequence("exception_definition", "PS", "api_comment", "annotation_list", "WS", "@4", "SP", "WS", "name", "WS", "@5", "SP", "WS", "type", "WS", "SEMICOLON", "WS");
        g.sequence("expression_statement", "PS", "expression", "SEMICOLON");
        g.sequence("extends_list_cons", "type", "WS", "@13", "WS", "extends_list");
        g.sequence("extends_list_end", "type");
        g.sequence("false", "@182", "@183", "WS");
        g.sequence("filepath", "string_value");
        g.sequence("float_datum", "float_value");
        g.sequence("float_value", "@200", "DIGITS", "@201", "DIGITS", "EXPONENT_OPT", "@202", "WS");
        g.sequence("for_body", "sequence_statement");
        g.sequence("for_condition", "expression", "WS");
        g.sequence("for_controller", "@52", "WS", "for_init", "WS", "SEMICOLON", "WS", "for_condition", "WS", "SEMICOLON", "WS", "for_modifier", "WS", "@53", "WS");
        g.sequence("for_init", "variable", "WS", "@54", "WS", "expression", "WS");
        g.sequence("for_modifier", "expression");
        g.sequence("for_statement", "PS", "@51", "WS", "for_controller", "WS", "for_body", "WS");
        g.sequence("foreach_body", "sequence_statement");
        g.sequence("foreach_controller", "@56", "WS", "foreach_vardec", "@57", "WS", "foreach_iterator", "@58");
        g.sequence("foreach_iterator", "expression", "WS");
        g.sequence("foreach_statement", "PS", "@55", "WS", "foreach_controller", "WS", "foreach_body", "WS");
        g.sequence("foreach_vardec", "variable", "WS", "@59", "WS", "type", "WS");
        g.sequence("forever_statement", "PS", "@60", "WS", "sequence_statement", "WS");
        g.sequence("formals", "PS", "@160", "WS", "@165", "WS", "@166", "WS");
        g.sequence("function_definition", "PS", "api_comment", "annotation_list", "WS", "@26", "SP", "WS", "signature", "WS", "sequence_statement", "WS");
        g.sequence("functor_definition", "PS", "api_comment", "annotation_list", "WS", "@7", "SP", "WS", "name", "WS", "formals", "WS", "@8", "WS", "type", "WS", "SEMICOLON", "WS");
        g.sequence("get_field_expression", "PS", "@141", "SP", "WS", "instance_member_access", "WS");
        g.sequence("get_static_expression", "PS", "@144", "SP", "WS", "static_member_access", "WS");
        g.sequence("getter_statement", "PS", "@84", "SP", "WS", "variable", "WS", "@85", "WS", "name", "WS", "@86", "WS", "static_member_access", "WS", "SEMICOLON");
        g.sequence("goto_statement", "PS", "@35", "SP", "WS", "label", "WS", "SEMICOLON");
        g.sequence("greater_operation", "@110", "WS", "p4_operand");
        g.sequence("greater_or_equals_operation", "@108", "WS", "p4_operand");
        g.sequence("identity_equals_operation", "@104", "WS", "p4_operand");
        g.sequence("identity_not_equals_operation", "@105", "WS", "p4_operand");
        g.sequence("if_case", "@30", "WS", "conditional_case", "WS");
        g.sequence("if_statement", "PS", "if_case", "elif_cases_opt", "WS", "else_case_opt", "WS");
        g.sequence("implies_operation", "@103", "WS", "p3_operand");
        g.sequence("import_directive", "PS", "@2", "SP", "WS", "type", "WS", "SEMICOLON");
        g.sequence("instance_member_access", "expression", "WS", "@178", "WS", "name", "WS");
        g.sequence("instanceof_expression", "PS", "@147", "SP", "WS", "expression", "WS", "@148", "WS", "type", "WS");
        g.sequence("int_datum", "int_value");
        g.sequence("int_value", "@195", "DIGITS", "WS");
        g.sequence("is_operation", "@96", "SP", "WS", "type", "WS");
        g.sequence("keyword_expression", "@120", "WS", "keyword_expression_body", "WS", "@121", "WS");
        g.sequence("label", "ID", "WS");
        g.sequence("lambda_statement", "PS", "@75", "SP", "WS", "variable", "WS", "@76", "WS", "type", "WS", "formals", "WS", "@77", "WS", "expression", "WS", "SEMICOLON", "WS");
        g.sequence("less_operation", "@111", "WS", "p4_operand");
        g.sequence("less_or_equals_operation", "@109", "WS", "p4_operand");
        g.sequence("let_statement", "PS", "@73", "WS", "variable", "WS", "@74", "WS", "expression", "WS", "SEMICOLON");
        g.sequence("list_expression", "PS", "@130", "WS", "arguments", "WS", "@131", "WS");
        g.sequence("literal_char_value", "@184", "@185", "@186", "WS");
        g.sequence("locals_expression", "PS", "@126", "WS");
        g.sequence("long_datum", "long_value");
        g.sequence("long_value", "@197", "DIGITS", "@198", "WS");
        g.sequence("marker_statement", "PS", "@36", "SP", "WS", "label", "WS", "SEMICOLON");
        g.sequence("method", "PS", "api_comment", "annotation_list", "WS", "@16", "SP", "WS", "name", "WS", "formals", "WS", "@17", "WS", "type", "WS", "SEMICOLON", "WS");
        g.sequence("method_statement", "PS", "@87", "SP", "WS", "variable", "WS", "@88", "WS", "name", "WS", "@89", "WS", "static_member_access", "WS", "SEMICOLON");
        g.sequence("module", "WS", "module_members", "END");
        g.sequence("module_directive", "PS", "api_comment", "annotation_list", "WS", "@0", "SP", "WS", "module_name", "WS", "@1", "WS", "namespace", "WS", "SEMICOLON");
        g.sequence("modulo_operation", "@116", "WS", "p7_operand");
        g.sequence("multiply_operation", "@117", "WS", "p7_operand");
        g.sequence("name", "name_id", "WS");
        g.sequence("name_id", "ID");
        g.sequence("name_of_named_module", "name");
        g.sequence("namespace_explicit", "PS", "@158");
        g.sequence("negate_operation", "@118", "WS", "p8", "WS");
        g.sequence("nested_expression", "@122", "WS", "expression", "WS", "@123", "WS");
        g.sequence("new_expression", "PS", "@134", "SP", "WS", "type", "WS", "arguments", "WS");
        g.sequence("nop_statement", "PS", "@29", "SEMICOLON");
        g.sequence("not_equals_operation", "@107", "WS", "p4_operand");
        g.sequence("not_operation", "@119", "WS", "p8", "WS");
        g.sequence("null_coalescing_operation", "@97", "WS", "p2_operand");
        g.sequence("null_datum", "@124", "@125", "WS");
        g.sequence("numeric_char_value", "DIGITS", "@187", "WS");
        g.sequence("or_operation", "@101", "WS", "p3_operand");
        g.sequence("p1", "p1_operand", "p1_operations");
        g.sequence("p1_operand", "p2");
        g.sequence("p2", "p2_operand", "p2_operations");
        g.sequence("p2_operand", "p3");
        g.sequence("p2_operation", "null_coalescing_operation");
        g.sequence("p3", "p3_operand", "p3_operations");
        g.sequence("p3_operand", "p4");
        g.sequence("p4", "p4_operand", "p4_operations");
        g.sequence("p4_operand", "p5");
        g.sequence("p5", "p5_operand", "p5_operations");
        g.sequence("p5_operand", "p6");
        g.sequence("p5_operation", "concat_operation");
        g.sequence("p6", "p6_operand", "p6_operations");
        g.sequence("p6_operand", "p7");
        g.sequence("p7", "p7_operand", "p7_operations");
        g.sequence("p7_operand", "p8");
        g.sequence("parameter", "PS", "variable", "WS", "@167", "WS", "type", "WS");
        g.sequence("paren_condition", "nested_expression");
        g.sequence("paren_expression", "nested_expression");
        g.sequence("progn_expression", "PS", "@132", "SP", "WS", "arguments", "WS");
        g.sequence("property", "PS", "api_comment", "annotation_list", "WS", "@14", "SP", "WS", "name", "WS", "@15", "WS", "type", "WS", "SEMICOLON", "WS");
        g.sequence("recur_statement", "PS", "@90", "WS", "arguments", "WS", "SEMICOLON");
        g.sequence("redo_statement", "PS", "@50", "WS", "SEMICOLON");
        g.sequence("regular_string_value", "STRING_LITERAL", "WS");
        g.sequence("return_type", "type");
        g.sequence("return_value_statement", "PS", "@92", "SP", "WS", "expression", "WS", "SEMICOLON");
        g.sequence("return_void_statement", "PS", "@91", "WS", "SEMICOLON");
        g.sequence("sequence_statement", "PS", "@27", "WS", "statements", "WS", "@28", "WS");
        g.sequence("set_field_expression", "PS", "@142", "SP", "WS", "instance_member_access", "@143", "WS", "expression", "WS");
        g.sequence("set_static_expression", "PS", "@145", "SP", "WS", "static_member_access", "WS", "@146", "WS", "expression", "WS");
        g.sequence("setter_statement", "PS", "@81", "SP", "WS", "variable", "WS", "@82", "WS", "name", "WS", "@83", "WS", "static_member_access", "WS", "SEMICOLON");
        g.sequence("short_circuit_and_operation", "@98", "WS", "p3_operand");
        g.sequence("short_circuit_or_operation", "@99", "WS", "p3_operand");
        g.sequence("short_datum", "short_value");
        g.sequence("short_value", "@192", "DIGITS", "@193", "WS");
        g.sequence("signature", "name", "WS", "formals", "WS", "@159", "WS", "return_type", "WS");
        g.sequence("simple_name", "name_id");
        g.sequence("static_member_access", "static_owner", "WS", "name", "WS");
        g.sequence("static_owner", "static_owner_explicit");
        g.sequence("static_owner_explicit", "type", "WS", "@179");
        g.sequence("string_datum", "string_value");
        g.sequence("subtract_operation", "@114", "WS", "p6_operand");
        g.sequence("ternary_conditional_expression", "PS", "@127", "SP", "WS", "condition", "WS", "@128", "SP", "WS", "expression", "@129", "SP", "WS", "expression", "WS");
        g.sequence("throw_statement", "PS", "@42", "SP", "WS", "expression", "WS", "SEMICOLON");
        g.sequence("true", "@180", "@181", "WS");
        g.sequence("try_catch_statement", "PS", "@43", "WS", "trycatch_body", "WS", "trycatch_handlers", "WS");
        g.sequence("trycatch_body", "sequence_statement");
        g.sequence("trycatch_handler", "PS", "@44", "WS", "trycatch_vardec", "WS", "sequence_statement", "WS");
        g.sequence("trycatch_vardec", "@45", "WS", "variable", "WS", "@46", "WS", "type", "WS", "@47");
        g.sequence("tuple_definition", "PS", "api_comment", "annotation_list", "WS", "@6", "SP", "WS", "name", "WS", "formals", "WS", "SEMICOLON", "WS");
        g.sequence("type", "type_name", "WS", "dimensions", "WS");
        g.sequence("type_name", "type_namespace", "simple_name", "WS");
        g.sequence("type_namespace_explicit", "PS", "@177");
        g.sequence("until_statement", "PS", "@61", "WS", "paren_condition", "WS", "sequence_statement", "WS");
        g.sequence("val_statement", "PS", "@69", "SP", "WS", "variable", "WS", "@70", "WS", "expression", "WS", "SEMICOLON");
        g.sequence("var_statement", "PS", "@67", "SP", "WS", "variable", "WS", "@68", "WS", "expression", "WS", "SEMICOLON");
        g.sequence("variable", "ID", "WS");
        g.sequence("variable_datum", "variable");
        g.sequence("verbatim_string_value", "@206", "WS", "STRING_LITERAL", "WS");
        g.sequence("when_statement", "PS", "@33", "SP", "WS", "paren_condition", "WS", "@34", "WS", "statement", "WS");
        g.sequence("while_statement", "PS", "@62", "WS", "paren_condition", "WS", "sequence_statement", "WS");
        g.sequence("xor_operation", "@102", "WS", "p3_operand");
        g.sequence("yield_value_statement", "PS", "@94", "SP", "WS", "expression", "WS", "SEMICOLON");
        g.sequence("yield_void_statement", "PS", "@93", "WS", "SEMICOLON");
        g.str("@0", "module");
        g.str("@1", "in");
        g.str("@103", "->");
        g.str("@104", "===");
        g.str("@105", "!==");
        g.str("@106", "==");
        g.str("@107", "!=");
        g.str("@108", ">=");
        g.str("@109", "<=");
        g.str("@112", "..");
        g.str("@12", "extends");
        g.str("@124", "null");
        g.str("@126", "locals");
        g.str("@127", "if");
        g.str("@128", "then");
        g.str("@129", "else");
        g.str("@13", "&");
        g.str("@132", "progn");
        g.str("@133", "dispatch");
        g.str("@134", "new");
        g.str("@135", "call");
        g.str("@138", "call");
        g.str("@14", "data");
        g.str("@141", "field");
        g.str("@142", "field");
        g.str("@144", "field");
        g.str("@145", "field");
        g.str("@147", "instanceof");
        g.str("@149", "create");
        g.str("@151", "''");
        g.str("@16", "method");
        g.str("@179", "::");
        g.str("@18", "enum");
        g.str("@180", "true");
        g.str("@182", "false");
        g.str("@184", "'");
        g.str("@186", "'");
        g.str("@2", "import");
        g.str("@208", "class");
        g.str("@219", "'");
        g.str("@220", "''");
        g.str("@225", "//");
        g.str("@231", "/*");
        g.str("@232", "*/");
        g.str("@237", "*/");
        g.str("@26", "defun");
        g.str("@29", "nop");
        g.str("@3", "annotation");
        g.str("@30", "if");
        g.str("@31", "elif");
        g.str("@32", "else");
        g.str("@33", "when");
        g.str("@34", "then");
        g.str("@35", "goto");
        g.str("@36", "marker");
        g.str("@37", "debug");
        g.str("@38", "assert");
        g.str("@39", "echo");
        g.str("@4", "exception");
        g.str("@40", "assume");
        g.str("@41", "echo");
        g.str("@42", "throw");
        g.str("@43", "try");
        g.str("@44", "catch");
        g.str("@48", "break");
        g.str("@49", "continue");
        g.str("@5", "extends");
        g.str("@50", "redo");
        g.str("@51", "for");
        g.str("@55", "foreach");
        g.str("@57", "in");
        g.str("@6", "tuple");
        g.str("@60", "forever");
        g.str("@61", "until");
        g.str("@62", "while");
        g.str("@63", "do");
        g.str("@65", "until");
        g.str("@66", "while");
        g.str("@67", "var");
        g.str("@69", "val");
        g.str("@7", "functor");
        g.str("@71", "let");
        g.str("@75", "lambda");
        g.str("@77", "=>");
        g.str("@78", "delegate");
        g.str("@80", "=>");
        g.str("@81", "setter");
        g.str("@83", "=>");
        g.str("@84", "getter");
        g.str("@86", "=>");
        g.str("@87", "method");
        g.str("@89", "=>");
        g.str("@9", "design");
        g.str("@90", "recur");
        g.str("@91", "return");
        g.str("@92", "return");
        g.str("@93", "yield");
        g.str("@94", "yield");
        g.str("@95", "as");
        g.str("@96", "is");
        g.str("@97", "??");
        g.str("@98", "&&");
        g.str("@99", "||");
        g.str("PS", "");
        g.str("TRIPLE_QUOTE", "'''");
        g.str("assertion_echo_empty", "");
        g.str("assumption_echo_empty", "");
        g.str("design_extends_empty", "");
        g.str("do_until_trigger", "");
        g.str("do_while_trigger", "");
        g.str("name_of_anonymous_module", "*");
        g.str("namespace_implicit", "");
        g.str("static_owner_implicit", "");
        g.str("type_namespace_implicit", "");

        // Specify which rule is the root of the grammar.
        g.setRoot("module");

        // Specify the number of tracing records to store concurrently.
        g.setTraceCount(1024);

        // Perform the actual construction of the grammar object.
        return g.build();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public ParserOutput parse(final char[] input)
    {
        return grammar.newParser().parse(input);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public ParserOutput parse(final String input)
    {
        return parse(input.toCharArray());
    }
}
