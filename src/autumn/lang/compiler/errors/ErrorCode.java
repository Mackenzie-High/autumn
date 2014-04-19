/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autumn.lang.compiler.errors;

/**
 * An instance of this enum identifies the type of a compile-time error.
 *
 * <p>
 * Note: The names of these constants are intended to infer their meaning.
 * </p>
 *
 * @author Mackenzie High
 */
public enum ErrorCode
{
    /**
     * Meaning: Parsing failed.
     */
    SYNTAX_ERROR,
    /**
     * Meaning: No accessible overload of the operator will accept the arguments' type(s).
     */
    NO_SUCH_OPERATOR_OVERLOAD,
    /**
     * Meaning: No accessible overload of the method will accept the arguments' type(s).
     */
    NO_SUCH_METHOD_OVERLOAD,
    /**
     * Meaning: No accessible overload of the constructor will accept the arguments' type(s).
     */
    NO_SUCH_CONSTRUCTOR_OVERLOAD,
    /**
     * Meaning: No method can be found with the given name, whether accessible or inaccessible.
     */
    NO_SUCH_METHOD,
    /**
     * Meaning: No field can be found with the given name, whether accessible or inaccessible.
     */
    NO_SUCH_FIELD,
    /**
     * Meaning: No accessible field can be found with the given name.
     */
    NO_SUCH_ACCESSIBLE_FIELD,
    /**
     * Meaning: The variable was not declared (i.e. Undeclared Variable).
     */
    NO_SUCH_VARIABLE,
    /**
     * Meaning: The label was not declared (i.e. Undeclared Label).
     */
    NO_SUCH_LABEL,
    /**
     * Meaning: The given type-name does not refer to any known type.
     */
    NO_SUCH_TYPE,
    /**
     * Meaning: The given type-name does not refer to a type that is accessible.
     */
    NO_SUCH_ACCESSIBLE_TYPE,
    /**
     * Meaning: An annotation-type was expected.
     */
    EXPECTED_ANNOTATION,
    /**
     * Meaning: An exception type was expected (i.e. a subclass of java.lang.Throwable).
     */
    EXPECTED_THROWABLE,
    /**
     * Meaning: An invokable object was expected (i.e. autumn.lang.ICallable).
     */
    EXPECTED_CALLABLE,
    /**
     * Meaning: An interface-type was expected.
     */
    EXPECTED_INTERFACE,
    /**
     * Meaning: An enum-type was expected.
     */
    EXPECTED_ENUM,
    /**
     * Meaning: A primitive-type or a reference-type was expected.
     */
    EXPECTED_PRIMITIVE_OR_REFERENCE,
    /**
     * Meaning: A reference-type was expected.
     */
    EXPECTED_REFERENCE,
    /**
     * Meaning: A primitive-type was expected.
     */
    EXPECTED_PRIMITIVE,
    /**
     * Meaning: A primitive boolean or a java.lang.Boolean was expected.
     */
    EXPECTED_CONDITION,
    /**
     * Meaning: An integral type was expected.
     */
    EXPECTED_INTEGER,
    /**
     * Meaning: The void-type was expected.
     */
    EXPECTED_VOID,
    /**
     * Meaning: A string (i.e. java.lang.String) was expected.
     */
    EXPECTED_STRING,
    /**
     * Meaning: An iterable (i.e. java.lang.Iterable) was expected.
     */
    EXPECTED_ITERABLE,
    /**
     * Meaning: The static-type of an expression was void were it is not allowed.
     */
    EXPECTED_NON_VOID_EXPRESSION,
    /**
     * Meaning: The variable was already previously declared elsewhere.
     */
    DUPLICATE_DECLARATION_OF_VARIABLE,
    /**
     * Meaning: The label was already previously declared elsewhere.
     */
    DUPLICATE_DECLARATION_OF_LABEL,
    /**
     * Meaning: The method was already declared elsewhere.
     */
    DUPLICATE_DECLARATION_OF_METHOD,
    /**
     * Meaning: The property was already declared elsewhere.
     */
    DUPLICATE_DECLARATION_OF_PROPERTY,
    /**
     * Meaning: The function was already declared elsewhere.
     */
    DUPLICATE_DECLARATION_OF_FUNCTION,
    /**
     * Meaning: The type was already declared elsewhere.
     */
    DUPLICATE_DECLARATION_OF_TYPE,
    /**
     * Meaning: The exception handler catches an the exact type as another exception handler.
     */
    DUPLICATE_EXCEPTION_HANDLER,
    /**
     * Meaning: A superinterface was specified more than once.
     */
    DUPLICATE_SUPERINTERFACE,
    /**
     * Meaning: A construct is used in a context that it is forbidden.
     */
    FORBIDDEN_CONSTRUCT,
    /**
     * Meaning:
     */
    BAD_LITERAL,
    /**
     * Meaning: The assignment is not possible based on the static-types.
     */
    IMPOSSIBLE_ASSIGNMENT,
    /**
     * Meaning: A type directly or indirectly inherits from itself.
     */
    CIRCULAR_INHERITANCE,
    /**
     * Meaning: An override method's return-type is not a subtype of the the return-type of
     * the method that is overridden.
     */
    RETURN_TYPE_IS_NOT_COVARIANT,
    /**
     * Meaning: A method is attempting to override another method that is declared 'final'.
     */
    CANNOT_OVERRIDE_FINAL_METHOD,
    /**
     * Meaning: A type is exposed in the API that should not be, due to its access-level.
     */
    OVERLY_EXPOSED_TYPE,
    /**
     * Meaning: An expression produces a value of an unexpected static-type.
     */
    WRONG_TYPE,
    /**
     * Meaning: A break-statement is located outside of a loop construct.
     */
    BREAK_OUTSIDE_OF_LOOP,
    /**
     * Meaning: A break-statement is located outside of a loop construct.
     */
    CONTINUE_OUTSIDE_OF_LOOP,
    /**
     * Meaning: A break-statement is located outside of a loop construct.
     */
    REDO_OUTSIDE_OF_LOOP,
}
