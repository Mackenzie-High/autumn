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
     * Meaning: Something completely unexpected happened.
     */
    UNKNOWN_ERROR,
    /**
     * Meaning: Parsing failed.
     */
    SYNTAX_ERROR,
    /**
     * Meaning: No accessible overload of the unary operator will accept the operand type.
     */
    NO_SUCH_UNARY_OPERATOR,
    /**
     * Meaning: No accessible overload of the binary operator will accept the operand types.
     */
    NO_SUCH_BINARY_OPERATOR,
    /**
     * Meaning: No accessible overload of the constructor will accept the arguments' type(s).
     */
    NO_SUCH_CONSTRUCTOR,
    /**
     * Meaning: No method can be found with the given name, whether accessible or inaccessible.
     */
    NO_SUCH_METHOD,
    /**
     * Meaning: No field can be found with the given name, whether accessible or inaccessible.
     */
    NO_SUCH_FIELD,
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
    EXPECTED_INTERFACE_TYPE,
    /**
     * Meaning: An enum-type was expected.
     */
    EXPECTED_ENUM_TYPE,
    /**
     * Meaning: A primitive-type or a reference-type was expected.
     */
    VALUE_REQUIRED,
    /**
     * Meaning: A reference-type was expected.
     */
    EXPECTED_REFERENCE_TYPE,
    /**
     * Meaning: A class-type was expected.
     */
    EXPECTED_CLASS_TYPE,
    /**
     * Meaning: A primitive-type was expected.
     */
    EXPECTED_PRIMITIVE_TYPE,
    /**
     * Meaning: A variable-type was expected.
     */
    EXPECTED_VARIABLE_TYPE,
    /**
     * Meaning: A return-type was expected.
     */
    EXPECTED_RETURN_TYPE,
    /**
     * Meaning: A declared-type was expected.
     */
    EXPECTED_DECLARED_TYPE,
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
     * Meaning: A functor (i.e. autumn.lang.Functor) was expected.
     */
    EXPECTED_FUNCTOR,
    /**
     * Meaning: A tuple (i.e. autumn.lang.Tuple) was expected.
     */
    EXPECTED_TUPLE,
    /**
     * Meaning: A design-type (i.e. autumn.lang.Design) was expected. // TODO: right?
     */
    EXPECTED_DESIGN,
    /**
     * Meaning: A module-type (i.e. autumn.lang.Module) was expected. // TODO: right?
     */
    EXPECTED_MODULE,
    /**
     * Meaning: The static-type of an expression was void were it is not allowed.
     */
    EXPECTED_NON_VOID_EXPRESSION,
    /**
     * Meaning: The null-type was found somewhere that it is forbidden to be.
     */
    EXPECTED_NON_NULL,
    /**
     * Meaning: A variable must be mutable in a particular circumstance.
     */
    EXPECTED_MUTABLE_VARIABLLE,
    /**
     * Meaning: The variable was already previously declared elsewhere.
     */
    DUPLICATE_VARIABLE,
    /**
     * Meaning: The label was already previously declared elsewhere.
     */
    DUPLICATE_LABEL,
    /**
     * Meaning: The method was already declared elsewhere.
     */
    DUPLICATE_METHOD,
    /**
     * Meaning: The property was already declared elsewhere.
     */
    DUPLICATE_PROPERTY,
    /**
     * Meaning: The function was already declared elsewhere.
     */
    DUPLICATE_FUNCTION,
    /**
     * Meaning: The type was already declared elsewhere.
     */
    DUPLICATE_TYPE,
    /**
     * Meaning: The exception handler catches an the exact type as another exception handler.
     */
    DUPLICATE_EXCEPTION_HANDLER,
    /**
     * Meaning: A superinterface was specified more than once.
     */
    DUPLICATE_SUPERINTERFACE,
    /**
     * Meaning: A module contains more than one module-directive.
     */
    DUPLICATE_MODULE_DIRECTIVE,
    /**
     * Meaning: An enum-definition contains multiple enum-constants with the same name.
     */
    DUPLICATE_CONSTANT,
    /**
     * Meaning: An annotation can only appear once in a single annotation-list.
     */
    DUPLICATE_ANNOTATION,
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
    COVARIANCE_VIOLATION,
    /**
     * Meaning: A method is attempting to override another method that is declared 'final'.
     */
    CANNOT_OVERRIDE_FINAL_METHOD,
    /**
     * Meaning: A type is exposed in the API that should not be, due to its access-level.
     */
    OVERLY_EXPOSED_TYPE,
    /**
     * Meaning: A type does not have adequate access privileges.
     */
    INACCESSIBLE_TYPE,
    /**
     * Meaning: An expression produces a value of an unexpected static-type.
     */
    WRONG_TYPE,
    /**
     * Meaning: An incorrect number of arguments was given to in an invocation.
     */
    BAD_ARGUMENT_COUNT,
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
    /**
     * Meaning: The declaration of a method conflicts with the declaration of a property.
     */
    METHOD_CONFLICTS_WITH_PROPERTY,
    /**
     * Meaning: A compilation-unit contains more than one function with the
     * <code>@Start</code> annotation applied to them.
     */
    TOO_MANY_STARTS,
    /**
     * Meaning: Given two operands X and Y, either X must be a subtype of Y or vice versa.
     */
    INCOMPATIBLE_OPERANDS,
    /**
     * Meaning: The compiler determined that an instanceof will never result in true.
     */
    NON_VIABLE_INSTANCEOF,
    /**
     * Meaning: A set of selected method overloads do not share their return-types.
     */
    UNEQUAL_RETURN_TYPES,
    /**
     * Meaning: A input value cannot be converted to an output type due to its type.
     */
    IMPOSSIBLE_CONVERSION,
    /**
     * Meaning: A variable was used outside the scope that it was declared in.
     */
    VARIABLE_OUTSIDE_OF_SCOPE,
    /**
     * Meaning: A string-literal contains a malformed escape-sequence.
     */
    MALFORMED_STRING_LITERAL,
}
