package com.mackenziehigh.autumn.lang.compiler.utils;

import com.mackenziehigh.autumn.lang.compiler.compilers.TypeSystem;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IDeclaredType;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IExpressionType;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IPrimitiveType;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IReturnType;
import com.mackenziehigh.autumn.resources.Finished;

/**
 * An instance of this class describes either an as-conversion or an is-conversion.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public final class Conversion
{
    /**
     * This is the name of the conversion method, if the conversion is predefined.
     */
    public final String name;

    /**
     * This is the type of value that the conversion method takes as input.
     * If this conversion is really a cast, then this is the type of the object being cast.
     */
    public final IExpressionType value;

    /**
     * This is the type of values that the conversion method returns.
     * If this conversion is really a cast, then this is the type that the object is being cast to.
     */
    public final IReturnType type;

    /**
     * This flag is true, if this conversion is really a cast.
     */
    public final boolean cast;

    /**
     * Sole Constructor.
     *
     * @param name is the name of the conversion method.
     * @param value is the input type of the conversion.
     * @param type is the output type of the conversion.
     * @param cast is true, iff this conversion is really a cast.
     */
    public Conversion(final String name,
                      final IExpressionType value,
                      final IReturnType type,
                      final boolean cast)
    {
        this.name = name;
        this.value = value;
        this.type = type;
        this.cast = cast;

        assert (cast == true && name == null) || (cast == false && name != null);
    }

    /**
     * This method creates a description of a conversion between two types.
     *
     * @param typesystem is the type-system containing the types.
     * @param value is the type of the conversion's input.
     * @param type is the type of the conversion's output.
     * @return an object that describes the conversion; or null, if the conversion is impossible.
     */
    public static Conversion findConversion(final TypeSystem typesystem,
                                            final IExpressionType value,
                                            final IReturnType type)
    {
        final IExpressionType expression = value;

        // Primitive Types
        final IPrimitiveType P_boolean = typesystem.utils.PRIMITIVE_BOOLEAN;
        final IPrimitiveType P_char = typesystem.utils.PRIMITIVE_CHAR;
        final IPrimitiveType P_byte = typesystem.utils.PRIMITIVE_BYTE;
        final IPrimitiveType P_short = typesystem.utils.PRIMITIVE_SHORT;
        final IPrimitiveType P_int = typesystem.utils.PRIMITIVE_INT;
        final IPrimitiveType P_long = typesystem.utils.PRIMITIVE_LONG;
        final IPrimitiveType P_float = typesystem.utils.PRIMITIVE_FLOAT;
        final IPrimitiveType P_double = typesystem.utils.PRIMITIVE_DOUBLE;

        // Boxed Types
        final IDeclaredType B_boolean = typesystem.utils.BOXED_BOOLEAN;
        final IDeclaredType B_char = typesystem.utils.BOXED_CHAR;
        final IDeclaredType B_byte = typesystem.utils.BOXED_BYTE;
        final IDeclaredType B_short = typesystem.utils.BOXED_SHORT;
        final IDeclaredType B_int = typesystem.utils.BOXED_INT;
        final IDeclaredType B_long = typesystem.utils.BOXED_LONG;
        final IDeclaredType B_float = typesystem.utils.BOXED_FLOAT;
        final IDeclaredType B_double = typesystem.utils.BOXED_DOUBLE;

        // Other Types
        final IDeclaredType STRING = typesystem.utils.STRING;
        final IDeclaredType OBJECT = typesystem.utils.OBJECT;

        Conversion best = null;

        // The order of the following method calls is important.
        // I did this using a helper method, rather than an if-elif-else statement, for readability.

        best = isPredefined(best, "box", B_boolean, P_boolean, expression, type);
        best = isPredefined(best, "box", B_char, P_char, expression, type);
        best = isPredefined(best, "box", B_byte, P_byte, expression, type);
        best = isPredefined(best, "box", B_short, P_short, expression, type);
        best = isPredefined(best, "box", B_int, P_int, expression, type);
        best = isPredefined(best, "box", B_long, P_long, expression, type);
        best = isPredefined(best, "box", B_float, P_float, expression, type);
        best = isPredefined(best, "box", B_double, P_double, expression, type);

        best = isPredefined(best, "boxToObject", OBJECT, P_boolean, expression, type);
        best = isPredefined(best, "boxToObject", OBJECT, P_char, expression, type);
        best = isPredefined(best, "boxToObject", OBJECT, P_byte, expression, type);
        best = isPredefined(best, "boxToObject", OBJECT, P_short, expression, type);
        best = isPredefined(best, "boxToObject", OBJECT, P_int, expression, type);
        best = isPredefined(best, "boxToObject", OBJECT, P_long, expression, type);
        best = isPredefined(best, "boxToObject", OBJECT, P_float, expression, type);
        best = isPredefined(best, "boxToObject", OBJECT, P_double, expression, type);

        best = isPredefined(best, "unbox", P_boolean, B_boolean, expression, type);
        best = isPredefined(best, "unbox", P_char, B_char, expression, type);
        best = isPredefined(best, "unbox", P_byte, B_byte, expression, type);
        best = isPredefined(best, "unbox", P_short, B_short, expression, type);
        best = isPredefined(best, "unbox", P_int, B_int, expression, type);
        best = isPredefined(best, "unbox", P_long, B_long, expression, type);
        best = isPredefined(best, "unbox", P_float, B_float, expression, type);
        best = isPredefined(best, "unbox", P_double, B_double, expression, type);

        best = isPredefined(best, "convertToBoolean", P_boolean, P_boolean, expression, type);
        best = isPredefined(best, "convertToBoolean", P_boolean, P_char, expression, type);
        best = isPredefined(best, "convertToBoolean", P_boolean, P_byte, expression, type);
        best = isPredefined(best, "convertToBoolean", P_boolean, P_short, expression, type);
        best = isPredefined(best, "convertToBoolean", P_boolean, P_int, expression, type);
        best = isPredefined(best, "convertToBoolean", P_boolean, P_long, expression, type);
        best = isPredefined(best, "convertToBoolean", P_boolean, P_float, expression, type);
        best = isPredefined(best, "convertToBoolean", P_boolean, P_double, expression, type);

        best = isPredefined(best, "convertToChar", P_char, P_boolean, expression, type);
        best = isPredefined(best, "convertToChar", P_char, P_char, expression, type);
        best = isPredefined(best, "convertToChar", P_char, P_byte, expression, type);
        best = isPredefined(best, "convertToChar", P_char, P_short, expression, type);
        best = isPredefined(best, "convertToChar", P_char, P_int, expression, type);
        best = isPredefined(best, "convertToChar", P_char, P_long, expression, type);
        best = isPredefined(best, "convertToChar", P_char, P_float, expression, type);
        best = isPredefined(best, "convertToChar", P_char, P_double, expression, type);

        best = isPredefined(best, "convertToByte", P_byte, P_boolean, expression, type);
        best = isPredefined(best, "convertToByte", P_byte, P_char, expression, type);
        best = isPredefined(best, "convertToByte", P_byte, P_byte, expression, type);
        best = isPredefined(best, "convertToByte", P_byte, P_short, expression, type);
        best = isPredefined(best, "convertToByte", P_byte, P_int, expression, type);
        best = isPredefined(best, "convertToByte", P_byte, P_long, expression, type);
        best = isPredefined(best, "convertToByte", P_byte, P_float, expression, type);
        best = isPredefined(best, "convertToByte", P_byte, P_double, expression, type);

        best = isPredefined(best, "convertToShort", P_short, P_boolean, expression, type);
        best = isPredefined(best, "convertToShort", P_short, P_char, expression, type);
        best = isPredefined(best, "convertToShort", P_short, P_byte, expression, type);
        best = isPredefined(best, "convertToShort", P_short, P_short, expression, type);
        best = isPredefined(best, "convertToShort", P_short, P_int, expression, type);
        best = isPredefined(best, "convertToShort", P_short, P_long, expression, type);
        best = isPredefined(best, "convertToShort", P_short, P_float, expression, type);
        best = isPredefined(best, "convertToShort", P_short, P_double, expression, type);

        best = isPredefined(best, "convertToInt", P_int, P_boolean, expression, type);
        best = isPredefined(best, "convertToInt", P_int, P_char, expression, type);
        best = isPredefined(best, "convertToInt", P_int, P_byte, expression, type);
        best = isPredefined(best, "convertToInt", P_int, P_short, expression, type);
        best = isPredefined(best, "convertToInt", P_int, P_int, expression, type);
        best = isPredefined(best, "convertToInt", P_int, P_long, expression, type);
        best = isPredefined(best, "convertToInt", P_int, P_float, expression, type);
        best = isPredefined(best, "convertToInt", P_int, P_double, expression, type);

        best = isPredefined(best, "convertToLong", P_long, P_boolean, expression, type);
        best = isPredefined(best, "convertToLong", P_long, P_char, expression, type);
        best = isPredefined(best, "convertToLong", P_long, P_byte, expression, type);
        best = isPredefined(best, "convertToLong", P_long, P_short, expression, type);
        best = isPredefined(best, "convertToLong", P_long, P_int, expression, type);
        best = isPredefined(best, "convertToLong", P_long, P_long, expression, type);
        best = isPredefined(best, "convertToLong", P_long, P_float, expression, type);
        best = isPredefined(best, "convertToLong", P_long, P_double, expression, type);

        best = isPredefined(best, "convertToFloat", P_float, P_boolean, expression, type);
        best = isPredefined(best, "convertToFloat", P_float, P_char, expression, type);
        best = isPredefined(best, "convertToFloat", P_float, P_byte, expression, type);
        best = isPredefined(best, "convertToFloat", P_float, P_short, expression, type);
        best = isPredefined(best, "convertToFloat", P_float, P_int, expression, type);
        best = isPredefined(best, "convertToFloat", P_float, P_long, expression, type);
        best = isPredefined(best, "convertToFloat", P_float, P_float, expression, type);
        best = isPredefined(best, "convertToFloat", P_float, P_double, expression, type);

        best = isPredefined(best, "convertToDouble", P_double, P_boolean, expression, type);
        best = isPredefined(best, "convertToDouble", P_double, P_char, expression, type);
        best = isPredefined(best, "convertToDouble", P_double, P_byte, expression, type);
        best = isPredefined(best, "convertToDouble", P_double, P_short, expression, type);
        best = isPredefined(best, "convertToDouble", P_double, P_int, expression, type);
        best = isPredefined(best, "convertToDouble", P_double, P_long, expression, type);
        best = isPredefined(best, "convertToDouble", P_double, P_float, expression, type);
        best = isPredefined(best, "convertToDouble", P_double, P_double, expression, type);

        best = isPredefined(best, "convertToString", STRING, P_boolean, expression, type);
        best = isPredefined(best, "convertToString", STRING, P_char, expression, type);
        best = isPredefined(best, "convertToString", STRING, P_byte, expression, type);
        best = isPredefined(best, "convertToString", STRING, P_short, expression, type);
        best = isPredefined(best, "convertToString", STRING, P_int, expression, type);
        best = isPredefined(best, "convertToString", STRING, P_long, expression, type);
        best = isPredefined(best, "convertToString", STRING, P_float, expression, type);
        best = isPredefined(best, "convertToString", STRING, P_double, expression, type);
        best = isPredefined(best, "convertToString", STRING, OBJECT, expression, type);

        if (best != null)
        {
            return best;
        }

        // The conversion must be a cast.

        /**
         * A type can be upcast to a supertype and a supertype can be downcast to a subtype.
         * However, there is no such thing as a cross-cast.
         */
        final boolean case1 = value.isSubtypeOf(type) || type.isSubtypeOf(value);

        /**
         * The input-type must be a reference-type,
         * because a primitive-type cannot be *cast* to another type.
         */
        final boolean case2 = value.isReferenceType();

        /**
         * The output-type must be an reference-type,
         * because a value cannot be *cast* to a primitive-type.
         */
        final boolean case3 = value.isReferenceType();

        if (case1 && case2 && case3)
        {
            // The conversion is an apparently valid cast.
            return new Conversion(null, value, type, true);
        }
        else
        {
            return null;
        }
    }

    /**
     * This method helps simplify the selection of a conversion method
     *
     * @param best is selected conversion, or null, if no conversion has been selected.
     * @param name is the name of the conversion method.
     * @param method_output is the return-type of the possible conversion method.
     * @param method_input is the type of value that the possible conversion method accepts.
     * @param value is the expression that produces the value to convert.
     * @param type is the type that the value will be converted to, as requested by the user.
     * @return a new conversion object, if the specified method is applicable; otherwise, null.
     */
    private static Conversion isPredefined(final Conversion best,
                                           final String name,
                                           final IReturnType method_output,
                                           final IExpressionType method_input,
                                           final IExpressionType value,
                                           final IReturnType type)
    {
        if (best != null)
        {
            return best;
        }
        else if (method_output.equals(type) && value.isSubtypeOf(method_input))
        {
            return new Conversion(name, method_input, method_output, false);
        }
        else
        {
            return null;
        }
    }
}
