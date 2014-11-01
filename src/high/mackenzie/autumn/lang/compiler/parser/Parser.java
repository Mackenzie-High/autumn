package high.mackenzie.autumn.lang.compiler.parser;

import high.mackenzie.snowflake.Grammar;
import high.mackenzie.snowflake.GrammarBuilder;
import high.mackenzie.snowflake.IParser;
import high.mackenzie.snowflake.ParserOutput;


/**
 * This class was auto-generated using the Snowflake parser-generator.
 *
 * <p>Generated On: Sat Oct 25 01:04:07 EDT 2014</p>
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
        g.range("@class1", (char) 40, (char) 40);
        g.range("@class2", (char) 44, (char) 44);
        g.range("@class3", (char) 41, (char) 41);
        g.range("@class4", (char) 123, (char) 123);
        g.range("@class5", (char) 125, (char) 125);
        g.range("@class6", (char) 40, (char) 40);
        g.range("@class7", (char) 41, (char) 41);
        g.range("@class8", (char) 40, (char) 40);
        g.range("@class9", (char) 44, (char) 44);
        g.range("@class10", (char) 41, (char) 41);
        g.range("@class11", (char) 40, (char) 40);
        g.range("@class12", (char) 58, (char) 58);
        g.range("@class13", (char) 41, (char) 41);
        g.range("@class14", (char) 40, (char) 40);
        g.range("@class15", (char) 41, (char) 41);
        g.range("@class16", (char) 61, (char) 61);
        g.range("@class17", (char) 40, (char) 40);
        g.range("@class18", (char) 41, (char) 41);
        g.range("@class19", (char) 58, (char) 58);
        g.range("@class20", (char) 61, (char) 61);
        g.range("@class21", (char) 61, (char) 61);
        g.range("@class22", (char) 61, (char) 61);
        g.range("@class23", (char) 58, (char) 58);
        g.range("@class24", (char) 40, (char) 40);
        g.range("@class25", (char) 44, (char) 44);
        g.range("@class26", (char) 41, (char) 41);
        g.range("@class27", (char) 58, (char) 58);
        g.range("@class28", (char) 38, (char) 38);
        g.range("@class29", (char) 124, (char) 124);
        g.range("@class30", (char) 94, (char) 94);
        g.range("@class31", (char) 62, (char) 62);
        g.range("@class32", (char) 60, (char) 60);
        g.range("@class33", (char) 43, (char) 43);
        g.range("@class34", (char) 45, (char) 45);
        g.range("@class35", (char) 47, (char) 47);
        g.range("@class36", (char) 37, (char) 37);
        g.range("@class37", (char) 42, (char) 42);
        g.range("@class38", (char) 45, (char) 45);
        g.range("@class39", (char) 33, (char) 33);
        g.range("@class40", (char) 40, (char) 40);
        g.range("@class41", (char) 41, (char) 41);
        g.range("@class42", (char) 40, (char) 40);
        g.range("@class43", (char) 41, (char) 41);
        g.range("@class44", (char) 91, (char) 91);
        g.range("@class45", (char) 93, (char) 93);
        g.range("@class46", (char) 58, (char) 58);
        g.range("@class47", (char) 91, (char) 91);
        g.range("@class48", (char) 93, (char) 93);
        g.range("@class49", (char) 61, (char) 61);
        g.range("@class50", (char) 61, (char) 61);
        g.range("@class51", (char) 58, (char) 58);
        g.range("@class52", (char) 10, (char) 10);
        g.range("@class53", (char) 13, (char) 13);
        g.combine("@class54", "@class52", "@class53");
        g.negate("@class55", "@class54");
        g.range("@class56", (char) 64, (char) 64);
        g.range("@class57", (char) 46, (char) 46);
        g.range("@class58", (char) 58, (char) 58);
        g.range("@class59", (char) 40, (char) 40);
        g.range("@class60", (char) 44, (char) 44);
        g.range("@class61", (char) 41, (char) 41);
        g.range("@class62", (char) 58, (char) 58);
        g.range("@class63", (char) 40, (char) 40);
        g.range("@class64", (char) 44, (char) 44);
        g.range("@class65", (char) 41, (char) 41);
        g.range("@class66", (char) 58, (char) 58);
        g.range("@class67", (char) 44, (char) 44);
        g.range("@class68", (char) 91, (char) 91);
        g.range("@class69", (char) 93, (char) 93);
        g.range("@class70", (char) 46, (char) 46);
        g.range("@class71", (char) 46, (char) 46);
        g.range("@class72", (char) 0, (char) 65535);
        g.range("@class73", (char) 67, (char) 67);
        g.range("@class74", (char) 45, (char) 45);
        g.range("@class75", (char) 66, (char) 66);
        g.range("@class76", (char) 45, (char) 45);
        g.range("@class77", (char) 83, (char) 83);
        g.range("@class78", (char) 45, (char) 45);
        g.range("@class79", (char) 45, (char) 45);
        g.range("@class80", (char) 76, (char) 76);
        g.range("@class81", (char) 45, (char) 45);
        g.range("@class82", (char) 46, (char) 46);
        g.range("@class83", (char) 70, (char) 70);
        g.range("@class84", (char) 45, (char) 45);
        g.range("@class85", (char) 46, (char) 46);
        g.range("@class86", (char) 64, (char) 64);
        g.range("@class87", (char) 40, (char) 40);
        g.range("@class88", (char) 41, (char) 41);
        g.range("@class89", (char) 59, (char) 59);
        g.range("@class90", (char) 65, (char) 90);
        g.range("@class91", (char) 97, (char) 122);
        g.range("@class92", (char) 95, (char) 95);
        g.range("@class93", (char) 36, (char) 36);
        g.combine("@class94", "@class90", "@class91", "@class92", "@class93");
        g.range("@class95", (char) 65, (char) 90);
        g.range("@class96", (char) 97, (char) 122);
        g.range("@class97", (char) 95, (char) 95);
        g.range("@class98", (char) 36, (char) 36);
        g.range("@class99", (char) 48, (char) 57);
        g.combine("@class100", "@class95", "@class96", "@class97", "@class98", "@class99");
        g.range("@class101", (char) 48, (char) 57);
        g.range("@class102", (char) 101, (char) 101);
        g.range("@class103", (char) 69, (char) 69);
        g.combine("@class104", "@class102", "@class103");
        g.range("@class105", (char) 45, (char) 45);
        g.range("@class106", (char) 48, (char) 57);
        g.combine("@class107", "@class105", "@class106");
        g.range("@class108", (char) 48, (char) 57);
        g.range("@class109", (char) 34, (char) 34);
        g.range("@class110", (char) 34, (char) 34);
        g.combine("@class111", "@class110");
        g.negate("@class112", "@class111");
        g.range("@class113", (char) 39, (char) 39);
        g.combine("@class114", "@class113");
        g.negate("@class115", "@class114");
        g.range("@class116", (char) 92, (char) 92);
        g.range("@class117", (char) 116, (char) 116);
        g.range("@class118", (char) 98, (char) 98);
        g.range("@class119", (char) 110, (char) 110);
        g.range("@class120", (char) 114, (char) 114);
        g.range("@class121", (char) 102, (char) 102);
        g.range("@class122", (char) 34, (char) 34);
        g.range("@class123", (char) 92, (char) 92);
        g.range("@class124", (char) 35, (char) 35);
        g.range("@class125", (char) 10, (char) 10);
        g.range("@class126", (char) 13, (char) 13);
        g.combine("@class127", "@class125", "@class126");
        g.negate("@class128", "@class127");
        g.range("@class129", (char) 10, (char) 10);
        g.range("@class130", (char) 13, (char) 13);
        g.combine("@class131", "@class129", "@class130");
        g.range("@class132", (char) 0, (char) 65535);
        g.combine("@class133", "@class132");
        g.range("@class134", (char) 10, (char) 10);
        g.range("@class135", (char) 13, (char) 13);
        g.combine("@class136", "@class134", "@class135");
        g.range("@class137", (char) 10, (char) 10);
        g.range("@class138", (char) 13, (char) 13);
        g.combine("@class139", "@class137", "@class138");
        g.negate("@class140", "@class139");
        g.range("@class141", (char) 32, (char) 32);
        g.range("@class142", (char) 9, (char) 9);
        g.range("@class143", (char) 10, (char) 10);
        g.range("@class144", (char) 11, (char) 11);
        g.range("@class145", (char) 12, (char) 12);
        g.range("@class146", (char) 13, (char) 13);
        g.combine("@class147", "@class141", "@class142", "@class143", "@class144", "@class145", "@class146");

        // Grammar Rules
        g.choose("@252", "SP", "COMMENT");
        g.choose("@255", "@253", "@254");
        g.choose("@70", "do_until", "do_while");
        g.choose("COMMENT", "MULTI_LINE_COMMENT", "SINGLE_LINE_COMMENT");
        g.choose("ESCAPE_CHAR", "@243", "@244", "@245", "@246", "@247", "@248", "@249", "@250", "@251");
        g.choose("NON_QUOTE", "ESCAPE_SEQUENCE", "@236");
        g.choose("NON_TRIPLE_QUOTE", "ESCAPE_SEQUENCE", "@240", "@241");
        g.choose("STRING_LITERAL", "STRING_LITERAL_STYLE1", "STRING_LITERAL_STYLE2");
        g.choose("assertion_echo_opt", "assertion_echo", "assertion_echo_empty");
        g.choose("assumption_echo_opt", "assumption_echo", "assumption_echo_empty");
        g.choose("boolean_value", "true", "false");
        g.choose("char_value", "literal_char_value", "numeric_char_value");
        g.choose("datum", "string_datum", "boolean_datum", "char_datum", "float_datum", "double_datum", "long_datum", "short_datum", "byte_datum", "int_datum", "null_datum", "class_datum", "variable_datum");
        g.choose("definition", "function_definition", "exception_definition", "design_definition", "tuple_definition", "struct_definition", "functor_definition", "enum_definition", "annotation_definition");
        g.choose("expression", "p1", "keyword_expression", "nested_expression");
        g.choose("keyword_expression_body", "locals_expression", "dispatch_expression", "new_expression", "call_static_method_expression", "call_method_expression", "set_static_expression", "get_static_expression", "set_field_expression", "get_field_expression", "instanceof_expression", "ternary_conditional_expression", "progn_expression");
        g.choose("module_member", "module_directive", "import_directive", "definition");
        g.choose("module_name", "name_of_anonymous_module", "name_of_named_module");
        g.choose("namespace", "namespace_explicit", "namespace_implicit");
        g.choose("p1_operation", "as_operation", "is_operation");
        g.choose("p3_operation", "and_operation", "or_operation", "xor_operation", "implies_operation");
        g.choose("p4_operation", "identity_equals_operation", "identity_not_equals_operation", "equals_operation", "not_equals_operation", "greater_or_equals_operation", "less_or_equals_operation", "greater_operation", "less_operation");
        g.choose("p6_operation", "add_operation", "subtract_operation");
        g.choose("p7_operation", "divide_operation", "modulo_operation", "multiply_operation");
        g.choose("p8", "datum", "negate_operation", "not_operation", "keyword_expression", "nested_expression", "list_expression", "list_comprehension_expression");
        g.choose("statement", "sequence_statement", "if_statement", "when_statement", "goto_statement", "marker_statement", "branch_statement", "do_statement", "break_statement", "continue_statement", "redo_statement", "foreach_statement", "for_statement", "until_statement", "while_statement", "forever_statement", "assert_statement", "assume_statement", "throw_statement", "try_catch_statement", "var_statement", "val_statement", "let_statement", "lambda_statement", "delegate_statement", "nop_statement", "debug_statement", "return_value_statement", "return_void_statement", "recur_statement", "yield_value_statement", "yield_void_statement", "expression_statement");
        g.choose("string_value", "verbatim_string_value", "regular_string_value");
        g.choose("supers_list", "supers_list_cons", "supers_list_end");
        g.choose("type_namespace", "type_namespace_explicit", "type_namespace_implicit");
        g.chr("@10", "@class0");
        g.chr("@102", "@class28");
        g.chr("@103", "@class29");
        g.chr("@104", "@class30");
        g.chr("@112", "@class31");
        g.chr("@113", "@class32");
        g.chr("@115", "@class33");
        g.chr("@116", "@class34");
        g.chr("@117", "@class35");
        g.chr("@118", "@class36");
        g.chr("@119", "@class37");
        g.chr("@120", "@class38");
        g.chr("@121", "@class39");
        g.chr("@122", "@class40");
        g.chr("@123", "@class41");
        g.chr("@124", "@class42");
        g.chr("@125", "@class43");
        g.chr("@132", "@class44");
        g.chr("@133", "@class45");
        g.chr("@135", "@class46");
        g.chr("@139", "@class47");
        g.chr("@14", "@class1");
        g.chr("@140", "@class48");
        g.chr("@15", "@class2");
        g.chr("@152", "@class49");
        g.chr("@155", "@class50");
        g.chr("@157", "@class51");
        g.chr("@160", "@class55");
        g.chr("@162", "@class56");
        g.chr("@163", "@class57");
        g.chr("@167", "@class58");
        g.chr("@170", "@class59");
        g.chr("@171", "@class60");
        g.chr("@176", "@class61");
        g.chr("@177", "@class62");
        g.chr("@178", "@class63");
        g.chr("@179", "@class64");
        g.chr("@184", "@class65");
        g.chr("@185", "@class66");
        g.chr("@186", "@class67");
        g.chr("@191", "@class68");
        g.chr("@192", "@class69");
        g.chr("@193", "@class70");
        g.chr("@196", "@class71");
        g.chr("@20", "@class3");
        g.chr("@203", "@class72");
        g.chr("@205", "@class73");
        g.chr("@206", "@class74");
        g.chr("@208", "@class75");
        g.chr("@209", "@class76");
        g.chr("@211", "@class77");
        g.chr("@212", "@class78");
        g.chr("@214", "@class79");
        g.chr("@216", "@class80");
        g.chr("@217", "@class81");
        g.chr("@219", "@class82");
        g.chr("@22", "@class4");
        g.chr("@220", "@class83");
        g.chr("@221", "@class84");
        g.chr("@223", "@class85");
        g.chr("@224", "@class86");
        g.chr("@225", "@class87");
        g.chr("@227", "@class88");
        g.chr("@228", "@class89");
        g.chr("@229", "@class94");
        g.chr("@23", "@class5");
        g.chr("@230", "@class100");
        g.chr("@232", "@class104");
        g.chr("@233", "@class107");
        g.chr("@234", "@class108");
        g.chr("@236", "@class112");
        g.chr("@241", "@class115");
        g.chr("@242", "@class116");
        g.chr("@243", "@class117");
        g.chr("@244", "@class118");
        g.chr("@245", "@class119");
        g.chr("@246", "@class120");
        g.chr("@247", "@class121");
        g.chr("@249", "@class122");
        g.chr("@250", "@class123");
        g.chr("@254", "@class124");
        g.chr("@256", "@class128");
        g.chr("@258", "@class131");
        g.chr("@262", "@class133");
        g.chr("@34", "@class6");
        g.chr("@35", "@class7");
        g.chr("@36", "@class8");
        g.chr("@37", "@class9");
        g.chr("@42", "@class10");
        g.chr("@51", "@class11");
        g.chr("@52", "@class12");
        g.chr("@53", "@class13");
        g.chr("@58", "@class14");
        g.chr("@59", "@class15");
        g.chr("@60", "@class16");
        g.chr("@62", "@class17");
        g.chr("@64", "@class18");
        g.chr("@65", "@class19");
        g.chr("@74", "@class20");
        g.chr("@76", "@class21");
        g.chr("@80", "@class22");
        g.chr("@82", "@class23");
        g.chr("@84", "@class24");
        g.chr("@85", "@class25");
        g.chr("@90", "@class26");
        g.chr("@92", "@class27");
        g.chr("DIGIT", "@class101");
        g.chr("NEWLINE", "@class136");
        g.chr("NON_NEWLINE", "@class140");
        g.chr("QUOTE", "@class109");
        g.chr("SP", "@class147");
        g.not("@127", "ID");
        g.not("@199", "ID");
        g.not("@201", "ID");
        g.not("@239", "@238");
        g.not("@261", "@260");
        g.repeat("@146", "@145", 0, 1);
        g.repeat("@149", "@148", 0, 1);
        g.repeat("@158", "api_comment_line", 0, 2147483647);
        g.repeat("@161", "annotation", 0, 2147483647);
        g.repeat("@165", "@164", 0, 2147483647);
        g.repeat("@17", "@16", 0, 2147483647);
        g.repeat("@173", "@172", 0, 2147483647);
        g.repeat("@175", "@174", 0, 1);
        g.repeat("@181", "@180", 0, 2147483647);
        g.repeat("@183", "@182", 0, 1);
        g.repeat("@188", "@187", 0, 2147483647);
        g.repeat("@19", "@18", 0, 1);
        g.repeat("@195", "@194", 1, 2147483647);
        g.repeat("@207", "@206", 0, 1);
        g.repeat("@210", "@209", 0, 1);
        g.repeat("@213", "@212", 0, 1);
        g.repeat("@215", "@214", 0, 1);
        g.repeat("@218", "@217", 0, 1);
        g.repeat("@222", "@221", 0, 1);
        g.repeat("@231", "@230", 0, 2147483647);
        g.repeat("@235", "@234", 0, 2147483647);
        g.repeat("@257", "@256", 0, 2147483647);
        g.repeat("@264", "@263", 0, 2147483647);
        g.repeat("@39", "@38", 0, 2147483647);
        g.repeat("@41", "@40", 0, 1);
        g.repeat("@79", "@78", 0, 1);
        g.repeat("@87", "@86", 0, 2147483647);
        g.repeat("@89", "@88", 0, 1);
        g.repeat("DIGITS", "DIGIT", 1, 2147483647);
        g.repeat("EXPONENT_OPT", "EXPONENT", 0, 1);
        g.repeat("NON_NEWLINES", "NON_NEWLINE", 0, 2147483647);
        g.repeat("NON_QUOTES", "NON_QUOTE", 0, 2147483647);
        g.repeat("NON_TRIPLE_QUOTES", "NON_TRIPLE_QUOTE", 0, 2147483647);
        g.repeat("WS", "@252", 0, 2147483647);
        g.repeat("api_comment_text", "@160", 0, 2147483647);
        g.repeat("arguments", "@189", 0, 1);
        g.repeat("dimensions", "@190", 0, 2147483647);
        g.repeat("elif_cases_opt", "elif_case", 0, 2147483647);
        g.repeat("else_case_opt", "else_case", 0, 1);
        g.repeat("if_opt", "@138", 0, 1);
        g.repeat("module_members", "module_member", 0, 2147483647);
        g.repeat("p1_operations", "p1_operation", 0, 2147483647);
        g.repeat("p2_operations", "p2_operation", 0, 2147483647);
        g.repeat("p3_operations", "p3_operation", 0, 2147483647);
        g.repeat("p4_operations", "p4_operation", 0, 2147483647);
        g.repeat("p5_operations", "p5_operation", 0, 2147483647);
        g.repeat("p6_operations", "p6_operation", 0, 2147483647);
        g.repeat("p7_operations", "p7_operation", 0, 2147483647);
        g.repeat("statements", "statement", 0, 2147483647);
        g.repeat("super_functor", "@12", 0, 1);
        g.repeat("supers_opt", "supers", 0, 1);
        g.repeat("trycatch_handlers", "trycatch_handler", 0, 2147483647);
        g.sequence("@12", "@11", "SP", "WS", "type", "WS");
        g.sequence("@138", "@137", "SP", "expression", "WS");
        g.sequence("@145", "@144", "SP");
        g.sequence("@148", "@147", "SP");
        g.sequence("@16", "WS", "@15", "WS", "enum_constant");
        g.sequence("@164", "@163", "name_id");
        g.sequence("@166", "name_id", "@165");
        g.sequence("@172", "WS", "@171", "WS", "element");
        g.sequence("@174", "element", "@173");
        g.sequence("@18", "enum_constant", "@17");
        g.sequence("@180", "WS", "@179", "WS", "parameter");
        g.sequence("@182", "parameter", "@181");
        g.sequence("@187", "WS", "@186", "WS", "WS", "argument");
        g.sequence("@189", "argument", "@188", "WS");
        g.sequence("@190", "dimension", "WS");
        g.sequence("@194", "name_id", "@193");
        g.sequence("@240", "@237", "@239");
        g.sequence("@251", "DIGIT", "DIGIT", "DIGIT", "DIGIT", "DIGIT");
        g.sequence("@263", "@261", "@262");
        g.sequence("@38", "WS", "@37", "WS", "label");
        g.sequence("@40", "label", "@39");
        g.sequence("@78", "@77", "SP");
        g.sequence("@86", "WS", "@85", "WS", "variable");
        g.sequence("@88", "variable", "@87");
        g.sequence("ESCAPE_SEQUENCE", "@242", "ESCAPE_CHAR");
        g.sequence("EXPONENT", "@232", "@233", "@235");
        g.sequence("ID", "@229", "@231");
        g.sequence("MULTI_LINE_COMMENT", "@259", "@264", "@265", "WS");
        g.sequence("SEMICOLON", "@228", "WS");
        g.sequence("SINGLE_LINE_COMMENT", "@255", "@257", "@258");
        g.sequence("STRING_LITERAL_STYLE1", "QUOTE", "NON_QUOTES", "QUOTE");
        g.sequence("STRING_LITERAL_STYLE2", "TRIPLE_QUOTE", "NON_TRIPLE_QUOTES", "TRIPLE_QUOTE");
        g.sequence("add_operation", "@115", "WS", "p6_operand");
        g.sequence("and_operation", "@102", "WS", "p3_operand");
        g.sequence("annotation", "PS", "@162", "WS", "annotation_type", "WS");
        g.sequence("annotation_definition", "PS", "api_comment", "annotation_list", "WS", "@3", "SP", "WS", "name", "WS", "SEMICOLON", "WS");
        g.sequence("annotation_list", "PS", "@161");
        g.sequence("annotation_type", "type");
        g.sequence("api_comment", "PS", "@158");
        g.sequence("api_comment_line", "PS", "WS", "@159", "api_comment_text", "WS");
        g.sequence("argument", "expression");
        g.sequence("as_operation", "@99", "SP", "WS", "type", "WS");
        g.sequence("assert_statement", "PS", "@44", "SP", "WS", "condition", "WS", "assertion_echo_opt", "WS", "SEMICOLON");
        g.sequence("assertion_echo", "@45", "SP", "WS", "expression", "WS");
        g.sequence("assume_statement", "PS", "@46", "SP", "WS", "condition", "WS", "assumption_echo_opt", "WS", "SEMICOLON");
        g.sequence("assumption_echo", "@47", "SP", "WS", "expression", "WS");
        g.sequence("boolean_datum", "boolean_value");
        g.sequence("branch_statement", "PS", "@32", "SP", "index", "WS", "labels", "WS", "@33", "SP", "label", "WS", "SEMICOLON");
        g.sequence("break_statement", "PS", "@54", "WS", "SEMICOLON");
        g.sequence("byte_datum", "byte_value");
        g.sequence("byte_value", "@207", "DIGITS", "@208", "WS");
        g.sequence("call_method_expression", "PS", "@146", "WS", "instance_member_access", "arguments", "WS");
        g.sequence("call_static_method_expression", "PS", "@149", "WS", "static_member_access", "arguments", "WS");
        g.sequence("char_datum", "char_value");
        g.sequence("class_datum", "class_value", "WS");
        g.sequence("class_value", "@225", "WS", "@226", "SP", "WS", "type", "WS", "@227", "WS");
        g.sequence("concat_operation", "@114", "WS", "p5_operand");
        g.sequence("condition", "expression");
        g.sequence("conditional_case", "PS", "paren_condition", "WS", "sequence_statement", "WS");
        g.sequence("continue_statement", "PS", "@55", "WS", "SEMICOLON");
        g.sequence("debug_statement", "PS", "@43", "WS", "SEMICOLON");
        g.sequence("delegate_statement", "PS", "@91", "SP", "WS", "variable", "WS", "@92", "WS", "type", "WS", "@93", "WS", "static_member_access", "WS", "SEMICOLON");
        g.sequence("design_definition", "PS", "api_comment", "annotation_list", "WS", "@6", "SP", "WS", "name", "WS", "elements", "WS", "supers_opt", "WS", "SEMICOLON", "WS");
        g.sequence("dimension", "@191", "WS", "@192");
        g.sequence("dispatch_expression", "PS", "@142", "SP", "WS", "name", "WS", "arguments", "WS");
        g.sequence("divide_operation", "@117", "WS", "p7_operand");
        g.sequence("do_statement", "PS", "@69", "WS", "sequence_statement", "WS", "@70", "WS");
        g.sequence("do_until", "@71", "WS", "paren_condition", "WS", "do_until_trigger");
        g.sequence("do_while", "@72", "WS", "paren_condition", "WS", "do_while_trigger");
        g.sequence("double_datum", "double_value");
        g.sequence("double_value", "@222", "DIGITS", "@223", "DIGITS", "EXPONENT_OPT", "WS");
        g.sequence("element", "PS", "name", "WS", "@177", "WS", "type", "WS");
        g.sequence("elements", "PS", "@170", "WS", "@175", "WS", "@176", "WS");
        g.sequence("elif_case", "@26", "WS", "conditional_case", "WS");
        g.sequence("else_case", "@27", "WS", "sequence_statement", "WS");
        g.sequence("enum_constant", "name");
        g.sequence("enum_constant_list", "@14", "WS", "@19", "WS", "@20");
        g.sequence("enum_definition", "PS", "api_comment", "annotation_list", "WS", "@13", "SP", "WS", "name", "WS", "enum_constant_list", "WS", "SEMICOLON", "WS");
        g.sequence("equals_operation", "@108", "WS", "p4_operand");
        g.sequence("exception_definition", "PS", "api_comment", "annotation_list", "WS", "@4", "SP", "WS", "name", "WS", "@5", "SP", "WS", "type", "WS", "SEMICOLON", "WS");
        g.sequence("expression_statement", "PS", "expression", "SEMICOLON");
        g.sequence("false", "@200", "@201", "WS");
        g.sequence("filepath", "string_value");
        g.sequence("float_datum", "float_value");
        g.sequence("float_value", "@218", "DIGITS", "@219", "DIGITS", "EXPONENT_OPT", "@220", "WS");
        g.sequence("for_body", "sequence_statement");
        g.sequence("for_condition", "expression", "WS");
        g.sequence("for_controller", "@58", "WS", "for_init", "WS", "SEMICOLON", "WS", "for_condition", "WS", "SEMICOLON", "WS", "for_modifier", "WS", "@59", "WS");
        g.sequence("for_init", "variable", "WS", "@60", "WS", "expression", "WS");
        g.sequence("for_modifier", "expression");
        g.sequence("for_statement", "PS", "@57", "WS", "for_controller", "WS", "for_body", "WS");
        g.sequence("foreach_body", "sequence_statement");
        g.sequence("foreach_controller", "@62", "WS", "foreach_vardec", "@63", "WS", "foreach_iterator", "@64");
        g.sequence("foreach_iterator", "expression", "WS");
        g.sequence("foreach_statement", "PS", "@61", "WS", "foreach_controller", "WS", "foreach_body", "WS");
        g.sequence("foreach_vardec", "variable", "WS", "@65", "WS", "type", "WS");
        g.sequence("forever_statement", "PS", "@66", "WS", "sequence_statement", "WS");
        g.sequence("formals", "PS", "@178", "WS", "@183", "WS", "@184", "WS");
        g.sequence("function_definition", "PS", "api_comment", "annotation_list", "WS", "@21", "SP", "WS", "signature", "WS", "sequence_statement", "WS");
        g.sequence("functor_definition", "PS", "api_comment", "annotation_list", "WS", "@9", "SP", "WS", "name", "WS", "formals", "WS", "@10", "WS", "type", "WS", "super_functor", "WS", "SEMICOLON", "WS");
        g.sequence("get_field_expression", "PS", "@150", "SP", "WS", "instance_member_access", "WS");
        g.sequence("get_static_expression", "PS", "@153", "SP", "WS", "static_member_access", "WS");
        g.sequence("goto_statement", "PS", "@30", "SP", "WS", "label", "WS", "SEMICOLON");
        g.sequence("greater_operation", "@112", "WS", "p4_operand");
        g.sequence("greater_or_equals_operation", "@110", "WS", "p4_operand");
        g.sequence("identity_equals_operation", "@106", "WS", "p4_operand");
        g.sequence("identity_not_equals_operation", "@107", "WS", "p4_operand");
        g.sequence("if_case", "@25", "WS", "conditional_case", "WS");
        g.sequence("if_statement", "PS", "if_case", "elif_cases_opt", "WS", "else_case_opt", "WS");
        g.sequence("implies_operation", "@105", "WS", "p3_operand");
        g.sequence("import_directive", "PS", "@2", "SP", "WS", "type", "WS", "SEMICOLON");
        g.sequence("index", "@34", "WS", "expression", "WS", "@35");
        g.sequence("instance_member_access", "expression", "WS", "@196", "WS", "name", "WS");
        g.sequence("instanceof_expression", "PS", "@156", "SP", "WS", "expression", "WS", "@157", "WS", "type", "WS");
        g.sequence("int_datum", "int_value");
        g.sequence("int_value", "@213", "DIGITS", "WS");
        g.sequence("is_operation", "@100", "SP", "WS", "type", "WS");
        g.sequence("keyword_expression", "@122", "WS", "keyword_expression_body", "WS", "@123", "WS");
        g.sequence("label", "ID", "WS");
        g.sequence("labels", "@36", "WS", "@41", "WS", "@42", "WS");
        g.sequence("lambda_formals", "@84", "WS", "@89", "WS", "@90", "WS");
        g.sequence("lambda_statement", "PS", "@81", "SP", "WS", "variable", "WS", "@82", "WS", "type", "WS", "lambda_formals", "WS", "@83", "WS", "expression", "WS", "SEMICOLON", "WS");
        g.sequence("less_operation", "@113", "WS", "p4_operand");
        g.sequence("less_or_equals_operation", "@111", "WS", "p4_operand");
        g.sequence("let_statement", "PS", "@79", "WS", "variable", "WS", "@80", "WS", "expression", "WS", "SEMICOLON");
        g.sequence("list_comprehension_body", "expression", "WS", "@134", "SP", "variable", "WS", "@135", "WS", "type", "WS", "@136", "SP", "expression", "WS", "if_opt", "WS");
        g.sequence("list_comprehension_expression", "PS", "@132", "WS", "list_comprehension_body", "WS", "@133", "WS");
        g.sequence("list_expression", "PS", "@139", "WS", "arguments", "WS", "@140", "WS");
        g.sequence("literal_char_value", "@202", "@203", "@204", "WS");
        g.sequence("locals_expression", "PS", "@128", "WS");
        g.sequence("long_datum", "long_value");
        g.sequence("long_value", "@215", "DIGITS", "@216", "WS");
        g.sequence("marker_statement", "PS", "@31", "SP", "WS", "label", "WS", "SEMICOLON");
        g.sequence("module", "WS", "module_members", "END");
        g.sequence("module_directive", "PS", "api_comment", "annotation_list", "WS", "@0", "SP", "WS", "module_name", "WS", "@1", "WS", "namespace", "WS", "SEMICOLON");
        g.sequence("modulo_operation", "@118", "WS", "p7_operand");
        g.sequence("multiply_operation", "@119", "WS", "p7_operand");
        g.sequence("name", "name_id", "WS");
        g.sequence("name_id", "ID");
        g.sequence("name_of_named_module", "name");
        g.sequence("namespace_explicit", "PS", "@166");
        g.sequence("negate_operation", "@120", "WS", "p8", "WS");
        g.sequence("nested_expression", "@124", "WS", "expression", "WS", "@125", "WS");
        g.sequence("new_expression", "PS", "@143", "SP", "WS", "type", "WS", "arguments", "WS");
        g.sequence("nop_statement", "PS", "@24", "SEMICOLON");
        g.sequence("not_equals_operation", "@109", "WS", "p4_operand");
        g.sequence("not_operation", "@121", "WS", "p8", "WS");
        g.sequence("null_coalescing_operation", "@101", "WS", "p2_operand");
        g.sequence("null_datum", "@126", "@127", "WS");
        g.sequence("numeric_char_value", "DIGITS", "@205", "WS");
        g.sequence("or_operation", "@103", "WS", "p3_operand");
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
        g.sequence("parameter", "PS", "variable", "WS", "@185", "WS", "type", "WS");
        g.sequence("paren_condition", "nested_expression");
        g.sequence("paren_expression", "nested_expression");
        g.sequence("progn_expression", "PS", "@141", "SP", "WS", "arguments", "WS");
        g.sequence("recur_statement", "PS", "@94", "WS", "arguments", "WS", "SEMICOLON");
        g.sequence("redo_statement", "PS", "@56", "WS", "SEMICOLON");
        g.sequence("regular_string_value", "STRING_LITERAL", "WS");
        g.sequence("return_type", "type");
        g.sequence("return_value_statement", "PS", "@96", "SP", "WS", "expression", "WS", "SEMICOLON");
        g.sequence("return_void_statement", "PS", "@95", "WS", "SEMICOLON");
        g.sequence("sequence_statement", "PS", "@22", "WS", "statements", "WS", "@23", "WS");
        g.sequence("set_field_expression", "PS", "@151", "SP", "WS", "instance_member_access", "@152", "WS", "expression", "WS");
        g.sequence("set_static_expression", "PS", "@154", "SP", "WS", "static_member_access", "WS", "@155", "WS", "expression", "WS");
        g.sequence("short_datum", "short_value");
        g.sequence("short_value", "@210", "DIGITS", "@211", "WS");
        g.sequence("signature", "name", "WS", "formals", "WS", "@167", "WS", "return_type", "WS");
        g.sequence("simple_name", "name_id");
        g.sequence("static_member_access", "static_owner", "WS", "name", "WS");
        g.sequence("static_owner", "static_owner_explicit");
        g.sequence("static_owner_explicit", "type", "WS", "@197");
        g.sequence("string_datum", "string_value");
        g.sequence("struct_definition", "PS", "api_comment", "annotation_list", "WS", "@8", "SP", "WS", "name", "WS", "elements", "WS", "supers_opt", "WS", "SEMICOLON", "WS");
        g.sequence("subtract_operation", "@116", "WS", "p6_operand");
        g.sequence("supers", "@168", "WS", "supers_list", "WS");
        g.sequence("supers_list_cons", "type", "WS", "@169", "WS", "supers_list");
        g.sequence("supers_list_end", "type");
        g.sequence("ternary_conditional_expression", "PS", "@129", "SP", "WS", "condition", "WS", "@130", "SP", "WS", "expression", "@131", "SP", "WS", "expression", "WS");
        g.sequence("throw_statement", "PS", "@48", "SP", "WS", "expression", "WS", "SEMICOLON");
        g.sequence("true", "@198", "@199", "WS");
        g.sequence("try_catch_statement", "PS", "@49", "WS", "trycatch_body", "WS", "trycatch_handlers", "WS");
        g.sequence("trycatch_body", "sequence_statement");
        g.sequence("trycatch_handler", "PS", "@50", "WS", "trycatch_vardec", "WS", "sequence_statement", "WS");
        g.sequence("trycatch_vardec", "@51", "WS", "variable", "WS", "@52", "WS", "type", "WS", "@53");
        g.sequence("tuple_definition", "PS", "api_comment", "annotation_list", "WS", "@7", "SP", "WS", "name", "WS", "elements", "WS", "supers_opt", "WS", "SEMICOLON", "WS");
        g.sequence("type", "type_name", "WS", "dimensions", "WS");
        g.sequence("type_name", "type_namespace", "simple_name", "WS");
        g.sequence("type_namespace_explicit", "PS", "@195");
        g.sequence("until_statement", "PS", "@67", "WS", "paren_condition", "WS", "sequence_statement", "WS");
        g.sequence("val_statement", "PS", "@75", "SP", "WS", "variable", "WS", "@76", "WS", "expression", "WS", "SEMICOLON");
        g.sequence("var_statement", "PS", "@73", "SP", "WS", "variable", "WS", "@74", "WS", "expression", "WS", "SEMICOLON");
        g.sequence("variable", "ID", "WS");
        g.sequence("variable_datum", "variable");
        g.sequence("verbatim_string_value", "@224", "WS", "STRING_LITERAL", "WS");
        g.sequence("when_statement", "PS", "@28", "SP", "WS", "paren_condition", "WS", "@29", "WS", "statement", "WS");
        g.sequence("while_statement", "PS", "@68", "WS", "paren_condition", "WS", "sequence_statement", "WS");
        g.sequence("xor_operation", "@104", "WS", "p3_operand");
        g.sequence("yield_value_statement", "PS", "@98", "SP", "WS", "expression", "WS", "SEMICOLON");
        g.sequence("yield_void_statement", "PS", "@97", "WS", "SEMICOLON");
        g.str("@0", "module");
        g.str("@1", "in");
        g.str("@100", "is");
        g.str("@101", "??");
        g.str("@105", "->");
        g.str("@106", "===");
        g.str("@107", "!==");
        g.str("@108", "==");
        g.str("@109", "!=");
        g.str("@11", "is");
        g.str("@110", ">=");
        g.str("@111", "<=");
        g.str("@114", "..");
        g.str("@126", "null");
        g.str("@128", "locals");
        g.str("@129", "if");
        g.str("@13", "enum");
        g.str("@130", "then");
        g.str("@131", "else");
        g.str("@134", "for");
        g.str("@136", "in");
        g.str("@137", "if");
        g.str("@141", "progn");
        g.str("@142", "dispatch");
        g.str("@143", "new");
        g.str("@144", "call");
        g.str("@147", "call");
        g.str("@150", "field");
        g.str("@151", "field");
        g.str("@153", "field");
        g.str("@154", "field");
        g.str("@156", "instanceof");
        g.str("@159", "''");
        g.str("@168", "is");
        g.str("@169", "&");
        g.str("@197", "::");
        g.str("@198", "true");
        g.str("@2", "import");
        g.str("@200", "false");
        g.str("@202", "'");
        g.str("@204", "'");
        g.str("@21", "defun");
        g.str("@226", "class");
        g.str("@237", "'");
        g.str("@238", "''");
        g.str("@24", "nop");
        g.str("@248", "'");
        g.str("@25", "if");
        g.str("@253", "//");
        g.str("@259", "/*");
        g.str("@26", "elif");
        g.str("@260", "*/");
        g.str("@265", "*/");
        g.str("@27", "else");
        g.str("@28", "when");
        g.str("@29", "then");
        g.str("@3", "annotation");
        g.str("@30", "goto");
        g.str("@31", "marker");
        g.str("@32", "branch");
        g.str("@33", "default");
        g.str("@4", "exception");
        g.str("@43", "debug");
        g.str("@44", "assert");
        g.str("@45", "echo");
        g.str("@46", "assume");
        g.str("@47", "echo");
        g.str("@48", "throw");
        g.str("@49", "try");
        g.str("@5", "is");
        g.str("@50", "catch");
        g.str("@54", "break");
        g.str("@55", "continue");
        g.str("@56", "redo");
        g.str("@57", "for");
        g.str("@6", "design");
        g.str("@61", "foreach");
        g.str("@63", "in");
        g.str("@66", "forever");
        g.str("@67", "until");
        g.str("@68", "while");
        g.str("@69", "do");
        g.str("@7", "tuple");
        g.str("@71", "until");
        g.str("@72", "while");
        g.str("@73", "var");
        g.str("@75", "val");
        g.str("@77", "let");
        g.str("@8", "struct");
        g.str("@81", "lambda");
        g.str("@83", "=>");
        g.str("@9", "functor");
        g.str("@91", "delegate");
        g.str("@93", "=>");
        g.str("@94", "recur");
        g.str("@95", "return");
        g.str("@96", "return");
        g.str("@97", "yield");
        g.str("@98", "yield");
        g.str("@99", "as");
        g.str("PS", "");
        g.str("TRIPLE_QUOTE", "'''");
        g.str("assertion_echo_empty", "");
        g.str("assumption_echo_empty", "");
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
