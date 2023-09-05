package autumnspecification;

import autumn.lang.compiler.ast.nodes.*;
import autumn.lang.compiler.errors.ErrorCode;
import static autumn.lang.compiler.errors.ErrorCode.*;
import static autumnspecification.JSONBuilder.*;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import high.mackenzie.autumn.util.json.JsonEncoder;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

/**
 * This class is used to generate page: https://www.mackenziehigh.me/autumn/TypeCheckingExamplesIndexPage.html
 *
 * @author Mackenzie High
 */
public final class TypeCheckIndex
{
    private static final List<List> table = Lists.newLinkedList();

    /**
     * This method adds an entry to the table of type-checking examples.
     *
     * @param test is the index of the test source-code file.
     * @param construct is the language construct performing the type-check.
     * @param error is the error-code resulting from the type-check.
     */
    public static void add(final Class construct,
                           final int test,
                           final ErrorCode error)
    {
        table.add(Lists.newArrayList(test, construct.getName(), error));
    }

    /**
     * This method writes the page's JSON file to the file-system.
     */
    public static void write()
            throws IOException
    {
        final List<Map> index = Lists.newArrayList();

        final SortedMap<String, List> sorted = Maps.newTreeMap();

        /**
         * Sort the rows.
         */
        for (List row : table)
        {
            final String key = row.get(1).toString() + " & " + row.get(2).toString() + " & " + sorted.size();

            sorted.put(key, row);
        }

        /**
         * Add the entries to the index.
         */
        for (String key : sorted.keySet())
        {
            final List row = sorted.get(key);

            final Map entry = Maps.newLinkedHashMap();

            entry.put("test", row.get(0));
            entry.put("construct", expand(row.get(1).toString()));
            entry.put("error-code", expand(row.get(2).toString()));

            index.add(entry);
        }

        /**
         * Write the JSON file.
         */
        final String content = (new JsonEncoder()).encode(index);
        final File file = new File(JSONBuilder.SPECIFICATION, "index-of-type-checking-examples.json");
        Files.write(content, file, Charset.defaultCharset());
    }

    /**
     * Add the entries to the index.
     */
    static
    {
        add(AnnotationDefinition.class, 139, DUPLICATE_TYPE);
        add(AnnotationDefinition.class, 150, DUPLICATE_ANNOTATION);

        add(ExceptionDefinition.class, 41, DUPLICATE_TYPE);
        add(ExceptionDefinition.class, 151, DUPLICATE_ANNOTATION);
        add(ExceptionDefinition.class, 43, NO_SUCH_TYPE);
        add(ExceptionDefinition.class, 44, INACCESSIBLE_TYPE);
        add(ExceptionDefinition.class, 138, EXPECTED_CLASS_TYPE);
        add(ExceptionDefinition.class, 42, EXPECTED_THROWABLE);
        add(ExceptionDefinition.class, 45, CIRCULAR_INHERITANCE);
        add(ExceptionDefinition.class, 46, CIRCULAR_INHERITANCE);

        add(EnumDefinition.class, 49, DUPLICATE_TYPE);
        add(EnumDefinition.class, 152, DUPLICATE_ANNOTATION);
        add(EnumDefinition.class, 50, DUPLICATE_ELEMENT);

        add(DesignDefinition.class, 168, DUPLICATE_TYPE);
        add(DesignDefinition.class, 153, DUPLICATE_ANNOTATION);
        add(DesignDefinition.class, 170, NO_SUCH_TYPE);
        add(DesignDefinition.class, 171, INACCESSIBLE_TYPE);
        add(DesignDefinition.class, 178, DUPLICATE_SUPERTYPE);
        add(DesignDefinition.class, 181, EXPECTED_INTERFACE_TYPE);
        add(DesignDefinition.class, 173, EXPECTED_INTERFACE_TYPE);
        add(DesignDefinition.class, 172, EXPECTED_INTERFACE_TYPE);
        add(DesignDefinition.class, 180, EXPECTED_DESIGN_TYPE);
        add(DesignDefinition.class, 174, DUPLICATE_ELEMENT);
        add(DesignDefinition.class, 175, NO_SUCH_TYPE);
        add(DesignDefinition.class, 176, INACCESSIBLE_TYPE);
        add(DesignDefinition.class, 177, EXPECTED_VARIABLE_TYPE);
        add(DesignDefinition.class, 179, COVARIANCE_VIOLATION);
        add(DesignDefinition.class, 162, CIRCULAR_INHERITANCE);
        add(DesignDefinition.class, 163, CIRCULAR_INHERITANCE);

        add(StructDefinition.class, 121, DUPLICATE_TYPE);
        add(StructDefinition.class, 155, DUPLICATE_ANNOTATION);
        add(StructDefinition.class, 122, NO_SUCH_TYPE);
        add(StructDefinition.class, 123, INACCESSIBLE_TYPE);
        add(StructDefinition.class, 124, DUPLICATE_SUPERTYPE);
        add(StructDefinition.class, 191, EXPECTED_INTERFACE_TYPE);
        add(StructDefinition.class, 192, EXPECTED_DESIGN_TYPE);
        add(StructDefinition.class, 125, DUPLICATE_ELEMENT);
        add(StructDefinition.class, 193, NO_SUCH_TYPE);
        add(StructDefinition.class, 194, INACCESSIBLE_TYPE);
        add(StructDefinition.class, 195, EXPECTED_VARIABLE_TYPE);
        add(StructDefinition.class, 164, CIRCULAR_INHERITANCE);

        add(TupleDefinition.class, 169, DUPLICATE_TYPE);
        add(TupleDefinition.class, 196, NO_SUCH_TYPE);
        add(TupleDefinition.class, 197, INACCESSIBLE_TYPE);
        add(TupleDefinition.class, 198, EXPECTED_INTERFACE_TYPE);
        add(TupleDefinition.class, 199, EXPECTED_DESIGN_TYPE);
        add(TupleDefinition.class, 200, DUPLICATE_SUPERTYPE);
        add(TupleDefinition.class, 201, DUPLICATE_ELEMENT);
        add(TupleDefinition.class, 202, NO_SUCH_TYPE);
        add(TupleDefinition.class, 203, INACCESSIBLE_TYPE);
        add(TupleDefinition.class, 204, EXPECTED_VARIABLE_TYPE);
        add(TupleDefinition.class, 205, COVARIANCE_VIOLATION);
        add(TupleDefinition.class, 166, CIRCULAR_INHERITANCE);

        add(FunctorDefinition.class, 139, DUPLICATE_TYPE);
        add(FunctorDefinition.class, 156, DUPLICATE_ANNOTATION);
        add(FunctorDefinition.class, 140, NO_SUCH_TYPE);
        add(FunctorDefinition.class, 141, INACCESSIBLE_TYPE);
        add(FunctorDefinition.class, 142, EXPECTED_CLASS_TYPE);
        add(FunctorDefinition.class, 143, EXPECTED_DEFINED_FUNCTOR_TYPE);
        add(FunctorDefinition.class, 144, NO_SUCH_TYPE);
        add(FunctorDefinition.class, 145, INACCESSIBLE_TYPE);
        add(FunctorDefinition.class, 146, EXPECTED_VARIABLE_TYPE);
        add(FunctorDefinition.class, 147, NO_SUCH_TYPE);
        add(FunctorDefinition.class, 148, INACCESSIBLE_TYPE);
        add(FunctorDefinition.class, 149, CIRCULAR_INHERITANCE);

        //add(FunctionDefinition.class, 169, DUPLICATE_FUNCTION);
        add(FunctionDefinition.class, 157, DUPLICATE_ANNOTATION);
        add(FunctionDefinition.class, 182, NO_SUCH_TYPE);
        add(FunctionDefinition.class, 183, INACCESSIBLE_TYPE);
        add(FunctionDefinition.class, 184, EXPECTED_VARIABLE_TYPE);
        add(FunctionDefinition.class, 185, NO_SUCH_TYPE);
        add(FunctionDefinition.class, 186, INACCESSIBLE_TYPE);

        add(IfStatement.class, 1, EXPECTED_CONDITION);
        add(IfStatement.class, 2, EXPECTED_CONDITION);

        add(WhenStatement.class, 10, EXPECTED_CONDITION);

        add(GotoStatement.class, 21, NO_SUCH_LABEL);

        add(MarkerStatement.class, 21, DUPLICATE_LABEL);

        add(BranchStatement.class, 126, EXPECTED_INTEGER);
        add(BranchStatement.class, 127, NO_SUCH_LABEL);
        add(BranchStatement.class, 128, NO_SUCH_LABEL);

        add(WhileStatement.class, 3, EXPECTED_CONDITION);

        add(UntilStatement.class, 4, EXPECTED_CONDITION);

        add(DoWhileStatement.class, 5, EXPECTED_CONDITION);

        add(DoUntilStatement.class, 6, EXPECTED_CONDITION);

        add(ForStatement.class, 56, DUPLICATE_VARIABLE);
        add(ForStatement.class, 57, EXPECTED_INTEGER);
        add(ForStatement.class, 58, EXPECTED_INTEGER);
        add(ForStatement.class, 59, EXPECTED_CONDITION);
        add(ForStatement.class, 69, VARIABLE_OUTSIDE_OF_SCOPE);
        add(ForStatement.class, 70, VARIABLE_OUTSIDE_OF_SCOPE);

        add(BreakStatement.class, 60, BREAK_OUTSIDE_OF_LOOP);

        add(ContinueStatement.class, 73, CONTINUE_OUTSIDE_OF_LOOP);

        add(RedoStatement.class, 74, REDO_OUTSIDE_OF_LOOP);

        add(VarStatement.class, 15, DUPLICATE_VARIABLE);

        add(ValStatement.class, 16, DUPLICATE_VARIABLE);

        add(LetStatement.class, 17, NO_SUCH_VARIABLE);

        add(TryCatchStatement.class, 11, DUPLICATE_VARIABLE);
        add(TryCatchStatement.class, 12, EXPECTED_THROWABLE);
        add(TryCatchStatement.class, 13, DUPLICATE_EXCEPTION_HANDLER);
        add(TryCatchStatement.class, 14, NO_SUCH_TYPE);
        add(TryCatchStatement.class, 72, INACCESSIBLE_TYPE);
        add(TryCatchStatement.class, 71, VARIABLE_OUTSIDE_OF_SCOPE);

        add(ThrowStatement.class, 10, EXPECTED_THROWABLE);

        add(AssertStatement.class, 7, EXPECTED_CONDITION);
        add(AssertStatement.class, 8, EXPECTED_CONDITION);
        add(AssertStatement.class, 9, EXPECTED_STRING);

        add(DelegateStatement.class, 213, DUPLICATE_VARIABLE);
        add(DelegateStatement.class, 214, NO_SUCH_TYPE);
        add(DelegateStatement.class, 215, INACCESSIBLE_TYPE);
        add(DelegateStatement.class, 216, EXPECTED_DEFINED_FUNCTOR_TYPE);
        add(DelegateStatement.class, 217, NO_SUCH_TYPE);
        add(DelegateStatement.class, 218, INACCESSIBLE_TYPE);
        add(DelegateStatement.class, 219, EXPECTED_MODULE_TYPE);
        add(DelegateStatement.class, 220, NO_SUCH_METHOD);

        add(LambdaStatement.class, 206, DUPLICATE_VARIABLE);
        add(LambdaStatement.class, 207, NO_SUCH_TYPE);
        add(LambdaStatement.class, 208, INACCESSIBLE_TYPE);
        add(LambdaStatement.class, 209, EXPECTED_DEFINED_FUNCTOR_TYPE);
        add(LambdaStatement.class, 210, WRONG_TYPE);
        add(LambdaStatement.class, 211, WRONG_TYPE);
        //add(LambdaStatement.class, 212, ErrorCode.);

        add(ReturnVoidStatement.class, 187, EXPECTED_VOID);

        add(ReturnValueStatement.class, 189, WRONG_TYPE);

        // TODO: dispatch

        add(NewExpression.class, 63, NO_SUCH_TYPE);
        add(NewExpression.class, 64, INACCESSIBLE_TYPE);
        add(NewExpression.class, 65, EXPECTED_CLASS_TYPE);
        add(NewExpression.class, 66, NO_SUCH_CONSTRUCTOR);

        add(CallStaticMethodExpression.class, 82, NO_SUCH_TYPE);
        add(CallStaticMethodExpression.class, 83, INACCESSIBLE_TYPE);
        add(CallStaticMethodExpression.class, 84, EXPECTED_DECLARED_TYPE);
        add(CallStaticMethodExpression.class, 85, NO_SUCH_METHOD);

        add(CallMethodExpression.class, 86, EXPECTED_DECLARED_TYPE);
        add(CallMethodExpression.class, 87, NO_SUCH_METHOD);

        add(SetStaticFieldExpression.class, 88, NO_SUCH_TYPE);
        add(SetStaticFieldExpression.class, 89, INACCESSIBLE_TYPE);
        add(SetStaticFieldExpression.class, 90, EXPECTED_DECLARED_TYPE);
        add(SetStaticFieldExpression.class, 91, NO_SUCH_FIELD);
        add(SetStaticFieldExpression.class, 97, ASSIGNMENT_TO_READONLY);
        add(SetStaticFieldExpression.class, 92, IMPOSSIBLE_ASSIGNMENT);

        add(GetStaticFieldExpression.class, 93, NO_SUCH_TYPE);
        add(GetStaticFieldExpression.class, 94, INACCESSIBLE_TYPE);
        add(GetStaticFieldExpression.class, 95, EXPECTED_DECLARED_TYPE);
        add(GetStaticFieldExpression.class, 96, NO_SUCH_FIELD);

        add(SetFieldExpression.class, 98, EXPECTED_DECLARED_TYPE);
        add(SetFieldExpression.class, 99, NO_SUCH_FIELD);
        add(SetFieldExpression.class, 100, ASSIGNMENT_TO_READONLY);
        add(SetFieldExpression.class, 101, IMPOSSIBLE_ASSIGNMENT);

        add(GetFieldExpression.class, 102, EXPECTED_DECLARED_TYPE);
        add(GetFieldExpression.class, 103, NO_SUCH_FIELD);

        add(InstanceOfExpression.class, 107, NO_SUCH_TYPE);
        add(InstanceOfExpression.class, 106, INACCESSIBLE_TYPE);
        add(InstanceOfExpression.class, 104, EXPECTED_DECLARED_TYPE);
        add(InstanceOfExpression.class, 105, EXPECTED_DECLARED_TYPE);
        add(InstanceOfExpression.class, 108, NON_VIABLE_INSTANCEOF);

        add(TernaryConditionalExpression.class, 112, EXPECTED_CONDITION);
        add(TernaryConditionalExpression.class, 114, INCOMPATIBLE_OPERANDS);
        add(TernaryConditionalExpression.class, 115, VALUE_REQUIRED);
        add(TernaryConditionalExpression.class, 116, VALUE_REQUIRED);

        // TODO: progn

        add(ListExpression.class, 36, VALUE_REQUIRED);

        add(ListComprehensionExpression.class, 129, VALUE_REQUIRED);
        add(ListComprehensionExpression.class, 130, DUPLICATE_VARIABLE);
        add(ListComprehensionExpression.class, 131, NO_SUCH_TYPE);
        add(ListComprehensionExpression.class, 132, INACCESSIBLE_TYPE);
        add(ListComprehensionExpression.class, 133, EXPECTED_REFERENCE_TYPE);
        add(ListComprehensionExpression.class, 134, EXPECTED_ITERABLE);
        add(ListComprehensionExpression.class, 137, EXPECTED_CONDITION);
        add(ListComprehensionExpression.class, 135, VARIABLE_OUTSIDE_OF_SCOPE);
        add(ListComprehensionExpression.class, 136, VARIABLE_OUTSIDE_OF_SCOPE);

        // TODO: other unary and binary operators

        add(IdentityEqualsOperation.class, 24, EXPECTED_REFERENCE_TYPE);
        add(IdentityEqualsOperation.class, 25, EXPECTED_REFERENCE_TYPE);

        add(IdentityNotEqualsOperation.class, 32, EXPECTED_REFERENCE_TYPE);
        add(IdentityNotEqualsOperation.class, 33, EXPECTED_REFERENCE_TYPE);

        add(AndOperation.class, 28, EXPECTED_CONDITION);
        add(AndOperation.class, 29, EXPECTED_CONDITION);

        add(OrOperation.class, 30, EXPECTED_CONDITION);
        add(OrOperation.class, 31, EXPECTED_CONDITION);

        add(XorOperation.class, 117, EXPECTED_CONDITION);
        add(XorOperation.class, 118, EXPECTED_CONDITION);

        add(ImpliesOperation.class, 119, EXPECTED_CONDITION);
        add(ImpliesOperation.class, 120, EXPECTED_CONDITION);

        add(NullCoalescingOperation.class, 26, EXPECTED_REFERENCE_TYPE);
        add(NullCoalescingOperation.class, 113, EXPECTED_REFERENCE_TYPE);
        add(NullCoalescingOperation.class, 27, INCOMPATIBLE_OPERANDS);

        add(AsOperation.class, 158, NO_SUCH_TYPE);
        add(AsOperation.class, 159, INACCESSIBLE_TYPE);
        add(AsOperation.class, 34, IMPOSSIBLE_CONVERSION);

        add(IsOperation.class, 35, IMPOSSIBLE_CONVERSION);

        add(AsOperation.class, 158, NO_SUCH_TYPE);

        add(StringDatum.class, 77, MALFORMED_STRING_LITERAL);

        add(ClassDatum.class, 78, NO_SUCH_TYPE);
        add(ClassDatum.class, 79, INACCESSIBLE_TYPE);

        add(VariableDatum.class, 80, NO_SUCH_VARIABLE);
        add(VariableDatum.class, 81, VARIABLE_OUTSIDE_OF_SCOPE);

    }
}
