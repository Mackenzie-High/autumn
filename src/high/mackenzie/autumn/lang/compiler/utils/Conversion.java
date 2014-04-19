/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.lang.compiler.utils;

import high.mackenzie.autumn.lang.compiler.compilers.TypeSystem;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IDeclaredType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IExpressionType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IPrimitiveType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IReturnType;

/**
 * An instance of this class describes either an as-conversion or an is-conversion.
 *
 * @author Mackenzie High
 */
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
        final IDeclaredType B_bool = typesystem.utils.BOXED_BOOLEAN;
        final IDeclaredType B_char = typesystem.utils.BOXED_CHAR;
        final IDeclaredType B_byte = typesystem.utils.BOXED_BYTE;
        final IDeclaredType B_short = typesystem.utils.BOXED_SHORT;
        final IDeclaredType B_int = typesystem.utils.BOXED_INT;
        final IDeclaredType B_long = typesystem.utils.BOXED_LONG;
        final IDeclaredType B_float = typesystem.utils.BOXED_FLOAT;
        final IDeclaredType B_double = typesystem.utils.BOXED_DOUBLE;

        // Other Types
        final IDeclaredType BIG_INTEGER = typesystem.utils.BIG_INTEGER;
        final IDeclaredType BIG_DECIMAL = typesystem.utils.BIG_DECIMAL;
        final IDeclaredType STRING = typesystem.utils.STRING;
        final IDeclaredType OBJECT = typesystem.utils.OBJECT;

        Conversion best = null;

        // The order of the following method calls is important.
        // I did this using a helper method, rather than an if-elif-else statement, for readability.

        best = isPredefined(best, "convertToPrimitiveBoolean", P_boolean, P_boolean, expression, type);
        best = isPredefined(best, "convertToPrimitiveBoolean", P_boolean, P_char, expression, type);
        best = isPredefined(best, "convertToPrimitiveBoolean", P_boolean, P_byte, expression, type);
        best = isPredefined(best, "convertToPrimitiveBoolean", P_boolean, P_short, expression, type);
        best = isPredefined(best, "convertToPrimitiveBoolean", P_boolean, P_int, expression, type);
        best = isPredefined(best, "convertToPrimitiveBoolean", P_boolean, P_long, expression, type);
        best = isPredefined(best, "convertToPrimitiveBoolean", P_boolean, P_float, expression, type);
        best = isPredefined(best, "convertToPrimitiveBoolean", P_boolean, P_double, expression, type);
        best = isPredefined(best, "convertToPrimitiveBoolean", P_boolean, B_bool, expression, type);
        best = isPredefined(best, "convertToPrimitiveBoolean", P_boolean, B_char, expression, type);
        best = isPredefined(best, "convertToPrimitiveBoolean", P_boolean, B_byte, expression, type);
        best = isPredefined(best, "convertToPrimitiveBoolean", P_boolean, B_short, expression, type);
        best = isPredefined(best, "convertToPrimitiveBoolean", P_boolean, B_int, expression, type);
        best = isPredefined(best, "convertToPrimitiveBoolean", P_boolean, B_long, expression, type);
        best = isPredefined(best, "convertToPrimitiveBoolean", P_boolean, B_float, expression, type);
        best = isPredefined(best, "convertToPrimitiveBoolean", P_boolean, B_double, expression, type);
        best = isPredefined(best, "convertToPrimitiveBoolean", P_boolean, BIG_INTEGER, expression, type);
        best = isPredefined(best, "convertToPrimitiveBoolean", P_boolean, BIG_DECIMAL, expression, type);
        best = isPredefined(best, "convertToPrimitiveBoolean", P_boolean, STRING, expression, type);
        best = isPredefined(best, "convertToPrimitiveBoolean", P_boolean, OBJECT, expression, type);

        best = isPredefined(best, "convertToPrimitiveChar", P_char, P_boolean, expression, type);
        best = isPredefined(best, "convertToPrimitiveChar", P_char, P_char, expression, type);
        best = isPredefined(best, "convertToPrimitiveChar", P_char, P_byte, expression, type);
        best = isPredefined(best, "convertToPrimitiveChar", P_char, P_short, expression, type);
        best = isPredefined(best, "convertToPrimitiveChar", P_char, P_int, expression, type);
        best = isPredefined(best, "convertToPrimitiveChar", P_char, P_long, expression, type);
        best = isPredefined(best, "convertToPrimitiveChar", P_char, P_float, expression, type);
        best = isPredefined(best, "convertToPrimitiveChar", P_char, P_double, expression, type);
        best = isPredefined(best, "convertToPrimitiveChar", P_char, B_bool, expression, type);
        best = isPredefined(best, "convertToPrimitiveChar", P_char, B_char, expression, type);
        best = isPredefined(best, "convertToPrimitiveChar", P_char, B_byte, expression, type);
        best = isPredefined(best, "convertToPrimitiveChar", P_char, B_short, expression, type);
        best = isPredefined(best, "convertToPrimitiveChar", P_char, B_int, expression, type);
        best = isPredefined(best, "convertToPrimitiveChar", P_char, B_long, expression, type);
        best = isPredefined(best, "convertToPrimitiveChar", P_char, B_float, expression, type);
        best = isPredefined(best, "convertToPrimitiveChar", P_char, B_double, expression, type);
        best = isPredefined(best, "convertToPrimitiveChar", P_char, BIG_INTEGER, expression, type);
        best = isPredefined(best, "convertToPrimitiveChar", P_char, BIG_DECIMAL, expression, type);
        best = isPredefined(best, "convertToPrimitiveChar", P_char, STRING, expression, type);
        best = isPredefined(best, "convertToPrimitiveChar", P_char, OBJECT, expression, type);

        best = isPredefined(best, "convertToPrimitiveByte", P_byte, P_boolean, expression, type);
        best = isPredefined(best, "convertToPrimitiveByte", P_byte, P_char, expression, type);
        best = isPredefined(best, "convertToPrimitiveByte", P_byte, P_byte, expression, type);
        best = isPredefined(best, "convertToPrimitiveByte", P_byte, P_short, expression, type);
        best = isPredefined(best, "convertToPrimitiveByte", P_byte, P_int, expression, type);
        best = isPredefined(best, "convertToPrimitiveByte", P_byte, P_long, expression, type);
        best = isPredefined(best, "convertToPrimitiveByte", P_byte, P_float, expression, type);
        best = isPredefined(best, "convertToPrimitiveByte", P_byte, P_double, expression, type);
        best = isPredefined(best, "convertToPrimitiveByte", P_byte, B_bool, expression, type);
        best = isPredefined(best, "convertToPrimitiveByte", P_byte, B_char, expression, type);
        best = isPredefined(best, "convertToPrimitiveByte", P_byte, B_byte, expression, type);
        best = isPredefined(best, "convertToPrimitiveByte", P_byte, B_short, expression, type);
        best = isPredefined(best, "convertToPrimitiveByte", P_byte, B_int, expression, type);
        best = isPredefined(best, "convertToPrimitiveByte", P_byte, B_long, expression, type);
        best = isPredefined(best, "convertToPrimitiveByte", P_byte, B_float, expression, type);
        best = isPredefined(best, "convertToPrimitiveByte", P_byte, B_double, expression, type);
        best = isPredefined(best, "convertToPrimitiveByte", P_byte, BIG_INTEGER, expression, type);
        best = isPredefined(best, "convertToPrimitiveByte", P_byte, BIG_DECIMAL, expression, type);
        best = isPredefined(best, "convertToPrimitiveByte", P_byte, STRING, expression, type);
        best = isPredefined(best, "convertToPrimitiveByte", P_byte, OBJECT, expression, type);

        best = isPredefined(best, "convertToPrimitiveShort", P_short, P_boolean, expression, type);
        best = isPredefined(best, "convertToPrimitiveShort", P_short, P_char, expression, type);
        best = isPredefined(best, "convertToPrimitiveShort", P_short, P_byte, expression, type);
        best = isPredefined(best, "convertToPrimitiveShort", P_short, P_short, expression, type);
        best = isPredefined(best, "convertToPrimitiveShort", P_short, P_int, expression, type);
        best = isPredefined(best, "convertToPrimitiveShort", P_short, P_long, expression, type);
        best = isPredefined(best, "convertToPrimitiveShort", P_short, P_float, expression, type);
        best = isPredefined(best, "convertToPrimitiveShort", P_short, P_double, expression, type);
        best = isPredefined(best, "convertToPrimitiveShort", P_short, B_bool, expression, type);
        best = isPredefined(best, "convertToPrimitiveShort", P_short, B_char, expression, type);
        best = isPredefined(best, "convertToPrimitiveShort", P_short, B_byte, expression, type);
        best = isPredefined(best, "convertToPrimitiveShort", P_short, B_short, expression, type);
        best = isPredefined(best, "convertToPrimitiveShort", P_short, B_int, expression, type);
        best = isPredefined(best, "convertToPrimitiveShort", P_short, B_long, expression, type);
        best = isPredefined(best, "convertToPrimitiveShort", P_short, B_float, expression, type);
        best = isPredefined(best, "convertToPrimitiveShort", P_short, B_double, expression, type);
        best = isPredefined(best, "convertToPrimitiveShort", P_short, BIG_INTEGER, expression, type);
        best = isPredefined(best, "convertToPrimitiveShort", P_short, BIG_DECIMAL, expression, type);
        best = isPredefined(best, "convertToPrimitiveShort", P_short, STRING, expression, type);
        best = isPredefined(best, "convertToPrimitiveShort", P_short, OBJECT, expression, type);

        best = isPredefined(best, "convertToPrimitiveInt", P_int, P_boolean, expression, type);
        best = isPredefined(best, "convertToPrimitiveInt", P_int, P_char, expression, type);
        best = isPredefined(best, "convertToPrimitiveInt", P_int, P_byte, expression, type);
        best = isPredefined(best, "convertToPrimitiveInt", P_int, P_short, expression, type);
        best = isPredefined(best, "convertToPrimitiveInt", P_int, P_int, expression, type);
        best = isPredefined(best, "convertToPrimitiveInt", P_int, P_long, expression, type);
        best = isPredefined(best, "convertToPrimitiveInt", P_int, P_float, expression, type);
        best = isPredefined(best, "convertToPrimitiveInt", P_int, P_double, expression, type);
        best = isPredefined(best, "convertToPrimitiveInt", P_int, B_bool, expression, type);
        best = isPredefined(best, "convertToPrimitiveInt", P_int, B_char, expression, type);
        best = isPredefined(best, "convertToPrimitiveInt", P_int, B_byte, expression, type);
        best = isPredefined(best, "convertToPrimitiveInt", P_int, B_short, expression, type);
        best = isPredefined(best, "convertToPrimitiveInt", P_int, B_int, expression, type);
        best = isPredefined(best, "convertToPrimitiveInt", P_int, B_long, expression, type);
        best = isPredefined(best, "convertToPrimitiveInt", P_int, B_float, expression, type);
        best = isPredefined(best, "convertToPrimitiveInt", P_int, B_double, expression, type);
        best = isPredefined(best, "convertToPrimitiveInt", P_int, BIG_INTEGER, expression, type);
        best = isPredefined(best, "convertToPrimitiveInt", P_int, BIG_DECIMAL, expression, type);
        best = isPredefined(best, "convertToPrimitiveInt", P_int, STRING, expression, type);
        best = isPredefined(best, "convertToPrimitiveInt", P_int, OBJECT, expression, type);

        best = isPredefined(best, "convertToPrimitiveLong", P_long, P_boolean, expression, type);
        best = isPredefined(best, "convertToPrimitiveLong", P_long, P_char, expression, type);
        best = isPredefined(best, "convertToPrimitiveLong", P_long, P_byte, expression, type);
        best = isPredefined(best, "convertToPrimitiveLong", P_long, P_short, expression, type);
        best = isPredefined(best, "convertToPrimitiveLong", P_long, P_int, expression, type);
        best = isPredefined(best, "convertToPrimitiveLong", P_long, P_long, expression, type);
        best = isPredefined(best, "convertToPrimitiveLong", P_long, P_float, expression, type);
        best = isPredefined(best, "convertToPrimitiveLong", P_long, P_double, expression, type);
        best = isPredefined(best, "convertToPrimitiveLong", P_long, B_bool, expression, type);
        best = isPredefined(best, "convertToPrimitiveLong", P_long, B_char, expression, type);
        best = isPredefined(best, "convertToPrimitiveLong", P_long, B_byte, expression, type);
        best = isPredefined(best, "convertToPrimitiveLong", P_long, B_short, expression, type);
        best = isPredefined(best, "convertToPrimitiveLong", P_long, B_int, expression, type);
        best = isPredefined(best, "convertToPrimitiveLong", P_long, B_long, expression, type);
        best = isPredefined(best, "convertToPrimitiveLong", P_long, B_float, expression, type);
        best = isPredefined(best, "convertToPrimitiveLong", P_long, B_double, expression, type);
        best = isPredefined(best, "convertToPrimitiveLong", P_long, BIG_INTEGER, expression, type);
        best = isPredefined(best, "convertToPrimitiveLong", P_long, BIG_DECIMAL, expression, type);
        best = isPredefined(best, "convertToPrimitiveLong", P_long, STRING, expression, type);
        best = isPredefined(best, "convertToPrimitiveLong", P_long, OBJECT, expression, type);

        best = isPredefined(best, "convertToPrimitiveFloat", P_float, P_boolean, expression, type);
        best = isPredefined(best, "convertToPrimitiveFloat", P_float, P_char, expression, type);
        best = isPredefined(best, "convertToPrimitiveFloat", P_float, P_byte, expression, type);
        best = isPredefined(best, "convertToPrimitiveFloat", P_float, P_short, expression, type);
        best = isPredefined(best, "convertToPrimitiveFloat", P_float, P_int, expression, type);
        best = isPredefined(best, "convertToPrimitiveFloat", P_float, P_long, expression, type);
        best = isPredefined(best, "convertToPrimitiveFloat", P_float, P_float, expression, type);
        best = isPredefined(best, "convertToPrimitiveFloat", P_float, P_double, expression, type);
        best = isPredefined(best, "convertToPrimitiveFloat", P_float, B_bool, expression, type);
        best = isPredefined(best, "convertToPrimitiveFloat", P_float, B_char, expression, type);
        best = isPredefined(best, "convertToPrimitiveFloat", P_float, B_byte, expression, type);
        best = isPredefined(best, "convertToPrimitiveFloat", P_float, B_short, expression, type);
        best = isPredefined(best, "convertToPrimitiveFloat", P_float, B_int, expression, type);
        best = isPredefined(best, "convertToPrimitiveFloat", P_float, B_long, expression, type);
        best = isPredefined(best, "convertToPrimitiveFloat", P_float, B_float, expression, type);
        best = isPredefined(best, "convertToPrimitiveFloat", P_float, B_double, expression, type);
        best = isPredefined(best, "convertToPrimitiveFloat", P_float, BIG_INTEGER, expression, type);
        best = isPredefined(best, "convertToPrimitiveFloat", P_float, BIG_DECIMAL, expression, type);
        best = isPredefined(best, "convertToPrimitiveFloat", P_float, STRING, expression, type);
        best = isPredefined(best, "convertToPrimitiveFloat", P_float, OBJECT, expression, type);

        best = isPredefined(best, "convertToPrimitiveDouble", P_double, P_boolean, expression, type);
        best = isPredefined(best, "convertToPrimitiveDouble", P_double, P_char, expression, type);
        best = isPredefined(best, "convertToPrimitiveDouble", P_double, P_byte, expression, type);
        best = isPredefined(best, "convertToPrimitiveDouble", P_double, P_short, expression, type);
        best = isPredefined(best, "convertToPrimitiveDouble", P_double, P_int, expression, type);
        best = isPredefined(best, "convertToPrimitiveDouble", P_double, P_long, expression, type);
        best = isPredefined(best, "convertToPrimitiveDouble", P_double, P_float, expression, type);
        best = isPredefined(best, "convertToPrimitiveDouble", P_double, P_double, expression, type);
        best = isPredefined(best, "convertToPrimitiveDouble", P_double, B_bool, expression, type);
        best = isPredefined(best, "convertToPrimitiveDouble", P_double, B_char, expression, type);
        best = isPredefined(best, "convertToPrimitiveDouble", P_double, B_byte, expression, type);
        best = isPredefined(best, "convertToPrimitiveDouble", P_double, B_short, expression, type);
        best = isPredefined(best, "convertToPrimitiveDouble", P_double, B_int, expression, type);
        best = isPredefined(best, "convertToPrimitiveDouble", P_double, B_long, expression, type);
        best = isPredefined(best, "convertToPrimitiveDouble", P_double, B_float, expression, type);
        best = isPredefined(best, "convertToPrimitiveDouble", P_double, B_double, expression, type);
        best = isPredefined(best, "convertToPrimitiveDouble", P_double, BIG_INTEGER, expression, type);
        best = isPredefined(best, "convertToPrimitiveDouble", P_double, BIG_DECIMAL, expression, type);
        best = isPredefined(best, "convertToPrimitiveDouble", P_double, STRING, expression, type);
        best = isPredefined(best, "convertToPrimitiveDouble", P_double, OBJECT, expression, type);

        best = isPredefined(best, "convertToBoxedBoolean", B_bool, P_boolean, expression, type);
        best = isPredefined(best, "convertToBoxedBoolean", B_bool, P_char, expression, type);
        best = isPredefined(best, "convertToBoxedBoolean", B_bool, P_byte, expression, type);
        best = isPredefined(best, "convertToBoxedBoolean", B_bool, P_short, expression, type);
        best = isPredefined(best, "convertToBoxedBoolean", B_bool, P_int, expression, type);
        best = isPredefined(best, "convertToBoxedBoolean", B_bool, P_long, expression, type);
        best = isPredefined(best, "convertToBoxedBoolean", B_bool, P_float, expression, type);
        best = isPredefined(best, "convertToBoxedBoolean", B_bool, P_double, expression, type);
        best = isPredefined(best, "convertToBoxedBoolean", B_bool, B_bool, expression, type);
        best = isPredefined(best, "convertToBoxedBoolean", B_bool, B_char, expression, type);
        best = isPredefined(best, "convertToBoxedBoolean", B_bool, B_byte, expression, type);
        best = isPredefined(best, "convertToBoxedBoolean", B_bool, B_short, expression, type);
        best = isPredefined(best, "convertToBoxedBoolean", B_bool, B_int, expression, type);
        best = isPredefined(best, "convertToBoxedBoolean", B_bool, B_long, expression, type);
        best = isPredefined(best, "convertToBoxedBoolean", B_bool, B_float, expression, type);
        best = isPredefined(best, "convertToBoxedBoolean", B_bool, B_double, expression, type);
        best = isPredefined(best, "convertToBoxedBoolean", B_bool, BIG_INTEGER, expression, type);
        best = isPredefined(best, "convertToBoxedBoolean", B_bool, BIG_DECIMAL, expression, type);
        best = isPredefined(best, "convertToBoxedBoolean", B_bool, STRING, expression, type);
        best = isPredefined(best, "convertToBoxedBoolean", B_bool, OBJECT, expression, type);

        best = isPredefined(best, "convertToBoxedChar", B_char, P_boolean, expression, type);
        best = isPredefined(best, "convertToBoxedChar", B_char, P_char, expression, type);
        best = isPredefined(best, "convertToBoxedChar", B_char, P_byte, expression, type);
        best = isPredefined(best, "convertToBoxedChar", B_char, P_short, expression, type);
        best = isPredefined(best, "convertToBoxedChar", B_char, P_int, expression, type);
        best = isPredefined(best, "convertToBoxedChar", B_char, P_long, expression, type);
        best = isPredefined(best, "convertToBoxedChar", B_char, P_float, expression, type);
        best = isPredefined(best, "convertToBoxedChar", B_char, P_double, expression, type);
        best = isPredefined(best, "convertToBoxedChar", B_char, B_bool, expression, type);
        best = isPredefined(best, "convertToBoxedChar", B_char, B_char, expression, type);
        best = isPredefined(best, "convertToBoxedChar", B_char, B_byte, expression, type);
        best = isPredefined(best, "convertToBoxedChar", B_char, B_short, expression, type);
        best = isPredefined(best, "convertToBoxedChar", B_char, B_int, expression, type);
        best = isPredefined(best, "convertToBoxedChar", B_char, B_long, expression, type);
        best = isPredefined(best, "convertToBoxedChar", B_char, B_float, expression, type);
        best = isPredefined(best, "convertToBoxedChar", B_char, B_double, expression, type);
        best = isPredefined(best, "convertToBoxedChar", B_char, BIG_INTEGER, expression, type);
        best = isPredefined(best, "convertToBoxedChar", B_char, BIG_DECIMAL, expression, type);
        best = isPredefined(best, "convertToBoxedChar", B_char, STRING, expression, type);
        best = isPredefined(best, "convertToBoxedChar", B_char, OBJECT, expression, type);

        best = isPredefined(best, "convertToBoxedByte", B_byte, P_boolean, expression, type);
        best = isPredefined(best, "convertToBoxedByte", B_byte, P_char, expression, type);
        best = isPredefined(best, "convertToBoxedByte", B_byte, P_byte, expression, type);
        best = isPredefined(best, "convertToBoxedByte", B_byte, P_short, expression, type);
        best = isPredefined(best, "convertToBoxedByte", B_byte, P_int, expression, type);
        best = isPredefined(best, "convertToBoxedByte", B_byte, P_long, expression, type);
        best = isPredefined(best, "convertToBoxedByte", B_byte, P_float, expression, type);
        best = isPredefined(best, "convertToBoxedByte", B_byte, P_double, expression, type);
        best = isPredefined(best, "convertToBoxedByte", B_byte, B_bool, expression, type);
        best = isPredefined(best, "convertToBoxedByte", B_byte, B_char, expression, type);
        best = isPredefined(best, "convertToBoxedByte", B_byte, B_byte, expression, type);
        best = isPredefined(best, "convertToBoxedByte", B_byte, B_short, expression, type);
        best = isPredefined(best, "convertToBoxedByte", B_byte, B_int, expression, type);
        best = isPredefined(best, "convertToBoxedByte", B_byte, B_long, expression, type);
        best = isPredefined(best, "convertToBoxedByte", B_byte, B_float, expression, type);
        best = isPredefined(best, "convertToBoxedByte", B_byte, B_double, expression, type);
        best = isPredefined(best, "convertToBoxedByte", B_byte, BIG_INTEGER, expression, type);
        best = isPredefined(best, "convertToBoxedByte", B_byte, BIG_DECIMAL, expression, type);
        best = isPredefined(best, "convertToBoxedByte", B_byte, STRING, expression, type);
        best = isPredefined(best, "convertToBoxedByte", B_byte, OBJECT, expression, type);

        best = isPredefined(best, "convertToBoxedShort", B_short, P_boolean, expression, type);
        best = isPredefined(best, "convertToBoxedShort", B_short, P_char, expression, type);
        best = isPredefined(best, "convertToBoxedShort", B_short, P_byte, expression, type);
        best = isPredefined(best, "convertToBoxedShort", B_short, P_short, expression, type);
        best = isPredefined(best, "convertToBoxedShort", B_short, P_int, expression, type);
        best = isPredefined(best, "convertToBoxedShort", B_short, P_long, expression, type);
        best = isPredefined(best, "convertToBoxedShort", B_short, P_float, expression, type);
        best = isPredefined(best, "convertToBoxedShort", B_short, P_double, expression, type);
        best = isPredefined(best, "convertToBoxedShort", B_short, B_bool, expression, type);
        best = isPredefined(best, "convertToBoxedShort", B_short, B_char, expression, type);
        best = isPredefined(best, "convertToBoxedShort", B_short, B_byte, expression, type);
        best = isPredefined(best, "convertToBoxedShort", B_short, B_short, expression, type);
        best = isPredefined(best, "convertToBoxedShort", B_short, B_int, expression, type);
        best = isPredefined(best, "convertToBoxedShort", B_short, B_long, expression, type);
        best = isPredefined(best, "convertToBoxedShort", B_short, B_float, expression, type);
        best = isPredefined(best, "convertToBoxedShort", B_short, B_double, expression, type);
        best = isPredefined(best, "convertToBoxedShort", B_short, BIG_INTEGER, expression, type);
        best = isPredefined(best, "convertToBoxedShort", B_short, BIG_DECIMAL, expression, type);
        best = isPredefined(best, "convertToBoxedShort", B_short, STRING, expression, type);
        best = isPredefined(best, "convertToBoxedShort", B_short, OBJECT, expression, type);

        best = isPredefined(best, "convertToBoxedInt", B_int, P_boolean, expression, type);
        best = isPredefined(best, "convertToBoxedInt", B_int, P_char, expression, type);
        best = isPredefined(best, "convertToBoxedInt", B_int, P_byte, expression, type);
        best = isPredefined(best, "convertToBoxedInt", B_int, P_short, expression, type);
        best = isPredefined(best, "convertToBoxedInt", B_int, P_int, expression, type);
        best = isPredefined(best, "convertToBoxedInt", B_int, P_long, expression, type);
        best = isPredefined(best, "convertToBoxedInt", B_int, P_float, expression, type);
        best = isPredefined(best, "convertToBoxedInt", B_int, P_double, expression, type);
        best = isPredefined(best, "convertToBoxedInt", B_int, B_bool, expression, type);
        best = isPredefined(best, "convertToBoxedInt", B_int, B_char, expression, type);
        best = isPredefined(best, "convertToBoxedInt", B_int, B_byte, expression, type);
        best = isPredefined(best, "convertToBoxedInt", B_int, B_short, expression, type);
        best = isPredefined(best, "convertToBoxedInt", B_int, B_int, expression, type);
        best = isPredefined(best, "convertToBoxedInt", B_int, B_long, expression, type);
        best = isPredefined(best, "convertToBoxedInt", B_int, B_float, expression, type);
        best = isPredefined(best, "convertToBoxedInt", B_int, B_double, expression, type);
        best = isPredefined(best, "convertToBoxedInt", B_int, BIG_INTEGER, expression, type);
        best = isPredefined(best, "convertToBoxedInt", B_int, BIG_DECIMAL, expression, type);
        best = isPredefined(best, "convertToBoxedInt", B_int, STRING, expression, type);
        best = isPredefined(best, "convertToBoxedInt", B_int, OBJECT, expression, type);

        best = isPredefined(best, "convertToBoxedLong", B_long, P_boolean, expression, type);
        best = isPredefined(best, "convertToBoxedLong", B_long, P_char, expression, type);
        best = isPredefined(best, "convertToBoxedLong", B_long, P_byte, expression, type);
        best = isPredefined(best, "convertToBoxedLong", B_long, P_short, expression, type);
        best = isPredefined(best, "convertToBoxedLong", B_long, P_int, expression, type);
        best = isPredefined(best, "convertToBoxedLong", B_long, P_long, expression, type);
        best = isPredefined(best, "convertToBoxedLong", B_long, P_float, expression, type);
        best = isPredefined(best, "convertToBoxedLong", B_long, P_double, expression, type);
        best = isPredefined(best, "convertToBoxedLong", B_long, B_bool, expression, type);
        best = isPredefined(best, "convertToBoxedLong", B_long, B_char, expression, type);
        best = isPredefined(best, "convertToBoxedLong", B_long, B_byte, expression, type);
        best = isPredefined(best, "convertToBoxedLong", B_long, B_short, expression, type);
        best = isPredefined(best, "convertToBoxedLong", B_long, B_int, expression, type);
        best = isPredefined(best, "convertToBoxedLong", B_long, B_long, expression, type);
        best = isPredefined(best, "convertToBoxedLong", B_long, B_float, expression, type);
        best = isPredefined(best, "convertToBoxedLong", B_long, B_double, expression, type);
        best = isPredefined(best, "convertToBoxedLong", B_long, BIG_INTEGER, expression, type);
        best = isPredefined(best, "convertToBoxedLong", B_long, BIG_DECIMAL, expression, type);
        best = isPredefined(best, "convertToBoxedLong", B_long, STRING, expression, type);
        best = isPredefined(best, "convertToBoxedLong", B_long, OBJECT, expression, type);

        best = isPredefined(best, "convertToBoxedFloat", B_float, P_boolean, expression, type);
        best = isPredefined(best, "convertToBoxedFloat", B_float, P_char, expression, type);
        best = isPredefined(best, "convertToBoxedFloat", B_float, P_byte, expression, type);
        best = isPredefined(best, "convertToBoxedFloat", B_float, P_short, expression, type);
        best = isPredefined(best, "convertToBoxedFloat", B_float, P_int, expression, type);
        best = isPredefined(best, "convertToBoxedFloat", B_float, P_long, expression, type);
        best = isPredefined(best, "convertToBoxedFloat", B_float, P_float, expression, type);
        best = isPredefined(best, "convertToBoxedFloat", B_float, P_double, expression, type);
        best = isPredefined(best, "convertToBoxedFloat", B_float, B_bool, expression, type);
        best = isPredefined(best, "convertToBoxedFloat", B_float, B_char, expression, type);
        best = isPredefined(best, "convertToBoxedFloat", B_float, B_byte, expression, type);
        best = isPredefined(best, "convertToBoxedFloat", B_float, B_short, expression, type);
        best = isPredefined(best, "convertToBoxedFloat", B_float, B_int, expression, type);
        best = isPredefined(best, "convertToBoxedFloat", B_float, B_long, expression, type);
        best = isPredefined(best, "convertToBoxedFloat", B_float, B_float, expression, type);
        best = isPredefined(best, "convertToBoxedFloat", B_float, B_double, expression, type);
        best = isPredefined(best, "convertToBoxedFloat", B_float, BIG_INTEGER, expression, type);
        best = isPredefined(best, "convertToBoxedFloat", B_float, BIG_DECIMAL, expression, type);
        best = isPredefined(best, "convertToBoxedFloat", B_float, STRING, expression, type);
        best = isPredefined(best, "convertToBoxedFloat", B_float, OBJECT, expression, type);

        best = isPredefined(best, "convertToBoxedDouble", B_double, P_boolean, expression, type);
        best = isPredefined(best, "convertToBoxedDouble", B_double, P_char, expression, type);
        best = isPredefined(best, "convertToBoxedDouble", B_double, P_byte, expression, type);
        best = isPredefined(best, "convertToBoxedDouble", B_double, P_short, expression, type);
        best = isPredefined(best, "convertToBoxedDouble", B_double, P_int, expression, type);
        best = isPredefined(best, "convertToBoxedDouble", B_double, P_long, expression, type);
        best = isPredefined(best, "convertToBoxedDouble", B_double, P_float, expression, type);
        best = isPredefined(best, "convertToBoxedDouble", B_double, P_double, expression, type);
        best = isPredefined(best, "convertToBoxedDouble", B_double, B_bool, expression, type);
        best = isPredefined(best, "convertToBoxedDouble", B_double, B_char, expression, type);
        best = isPredefined(best, "convertToBoxedDouble", B_double, B_byte, expression, type);
        best = isPredefined(best, "convertToBoxedDouble", B_double, B_short, expression, type);
        best = isPredefined(best, "convertToBoxedDouble", B_double, B_int, expression, type);
        best = isPredefined(best, "convertToBoxedDouble", B_double, B_long, expression, type);
        best = isPredefined(best, "convertToBoxedDouble", B_double, B_float, expression, type);
        best = isPredefined(best, "convertToBoxedDouble", B_double, B_double, expression, type);
        best = isPredefined(best, "convertToBoxedDouble", B_double, BIG_INTEGER, expression, type);
        best = isPredefined(best, "convertToBoxedDouble", B_double, BIG_DECIMAL, expression, type);
        best = isPredefined(best, "convertToBoxedDouble", B_double, STRING, expression, type);
        best = isPredefined(best, "convertToBoxedDouble", B_double, OBJECT, expression, type);

        best = isPredefined(best, "convertToBigInteger", BIG_INTEGER, P_boolean, expression, type);
        best = isPredefined(best, "convertToBigInteger", BIG_INTEGER, P_char, expression, type);
        best = isPredefined(best, "convertToBigInteger", BIG_INTEGER, P_byte, expression, type);
        best = isPredefined(best, "convertToBigInteger", BIG_INTEGER, P_short, expression, type);
        best = isPredefined(best, "convertToBigInteger", BIG_INTEGER, P_int, expression, type);
        best = isPredefined(best, "convertToBigInteger", BIG_INTEGER, P_long, expression, type);
        best = isPredefined(best, "convertToBigInteger", BIG_INTEGER, P_float, expression, type);
        best = isPredefined(best, "convertToBigInteger", BIG_INTEGER, P_double, expression, type);
        best = isPredefined(best, "convertToBigInteger", BIG_INTEGER, B_bool, expression, type);
        best = isPredefined(best, "convertToBigInteger", BIG_INTEGER, B_char, expression, type);
        best = isPredefined(best, "convertToBigInteger", BIG_INTEGER, B_byte, expression, type);
        best = isPredefined(best, "convertToBigInteger", BIG_INTEGER, B_short, expression, type);
        best = isPredefined(best, "convertToBigInteger", BIG_INTEGER, B_int, expression, type);
        best = isPredefined(best, "convertToBigInteger", BIG_INTEGER, B_long, expression, type);
        best = isPredefined(best, "convertToBigInteger", BIG_INTEGER, B_float, expression, type);
        best = isPredefined(best, "convertToBigInteger", BIG_INTEGER, B_double, expression, type);
        best = isPredefined(best, "convertToBigInteger", BIG_INTEGER, BIG_INTEGER, expression, type);
        best = isPredefined(best, "convertToBigInteger", BIG_INTEGER, BIG_DECIMAL, expression, type);
        best = isPredefined(best, "convertToBigInteger", BIG_INTEGER, STRING, expression, type);
        best = isPredefined(best, "convertToBigInteger", BIG_INTEGER, OBJECT, expression, type);

        best = isPredefined(best, "convertToBigDecimal", BIG_DECIMAL, P_boolean, expression, type);
        best = isPredefined(best, "convertToBigDecimal", BIG_DECIMAL, P_char, expression, type);
        best = isPredefined(best, "convertToBigDecimal", BIG_DECIMAL, P_byte, expression, type);
        best = isPredefined(best, "convertToBigDecimal", BIG_DECIMAL, P_short, expression, type);
        best = isPredefined(best, "convertToBigDecimal", BIG_DECIMAL, P_int, expression, type);
        best = isPredefined(best, "convertToBigDecimal", BIG_DECIMAL, P_long, expression, type);
        best = isPredefined(best, "convertToBigDecimal", BIG_DECIMAL, P_float, expression, type);
        best = isPredefined(best, "convertToBigDecimal", BIG_DECIMAL, P_double, expression, type);
        best = isPredefined(best, "convertToBigDecimal", BIG_DECIMAL, B_bool, expression, type);
        best = isPredefined(best, "convertToBigDecimal", BIG_DECIMAL, B_char, expression, type);
        best = isPredefined(best, "convertToBigDecimal", BIG_DECIMAL, B_byte, expression, type);
        best = isPredefined(best, "convertToBigDecimal", BIG_DECIMAL, B_short, expression, type);
        best = isPredefined(best, "convertToBigDecimal", BIG_DECIMAL, B_int, expression, type);
        best = isPredefined(best, "convertToBigDecimal", BIG_DECIMAL, B_long, expression, type);
        best = isPredefined(best, "convertToBigDecimal", BIG_DECIMAL, B_float, expression, type);
        best = isPredefined(best, "convertToBigDecimal", BIG_DECIMAL, B_double, expression, type);
        best = isPredefined(best, "convertToBigDecimal", BIG_DECIMAL, BIG_INTEGER, expression, type);
        best = isPredefined(best, "convertToBigDecimal", BIG_DECIMAL, BIG_DECIMAL, expression, type);
        best = isPredefined(best, "convertToBigDecimal", BIG_DECIMAL, STRING, expression, type);
        best = isPredefined(best, "convertToBigDecimal", BIG_DECIMAL, OBJECT, expression, type);

        best = isPredefined(best, "convertToString", STRING, P_boolean, expression, type);
        best = isPredefined(best, "convertToString", STRING, P_char, expression, type);
        best = isPredefined(best, "convertToString", STRING, P_byte, expression, type);
        best = isPredefined(best, "convertToString", STRING, P_short, expression, type);
        best = isPredefined(best, "convertToString", STRING, P_int, expression, type);
        best = isPredefined(best, "convertToString", STRING, P_long, expression, type);
        best = isPredefined(best, "convertToString", STRING, P_float, expression, type);
        best = isPredefined(best, "convertToString", STRING, P_double, expression, type);
        best = isPredefined(best, "convertToString", STRING, B_bool, expression, type);
        best = isPredefined(best, "convertToString", STRING, B_char, expression, type);
        best = isPredefined(best, "convertToString", STRING, B_byte, expression, type);
        best = isPredefined(best, "convertToString", STRING, B_short, expression, type);
        best = isPredefined(best, "convertToString", STRING, B_int, expression, type);
        best = isPredefined(best, "convertToString", STRING, B_long, expression, type);
        best = isPredefined(best, "convertToString", STRING, B_float, expression, type);
        best = isPredefined(best, "convertToString", STRING, B_double, expression, type);
        best = isPredefined(best, "convertToString", STRING, BIG_INTEGER, expression, type);
        best = isPredefined(best, "convertToString", STRING, BIG_DECIMAL, expression, type);
        best = isPredefined(best, "convertToString", STRING, STRING, expression, type);
        best = isPredefined(best, "convertToString", STRING, OBJECT, expression, type);

        best = isPredefined(best, "convertToObject", OBJECT, P_boolean, expression, type);
        best = isPredefined(best, "convertToObject", OBJECT, P_char, expression, type);
        best = isPredefined(best, "convertToObject", OBJECT, P_byte, expression, type);
        best = isPredefined(best, "convertToObject", OBJECT, P_short, expression, type);
        best = isPredefined(best, "convertToObject", OBJECT, P_int, expression, type);
        best = isPredefined(best, "convertToObject", OBJECT, P_long, expression, type);
        best = isPredefined(best, "convertToObject", OBJECT, P_float, expression, type);
        best = isPredefined(best, "convertToObject", OBJECT, P_double, expression, type);
        best = isPredefined(best, "convertToObject", OBJECT, B_bool, expression, type);
        best = isPredefined(best, "convertToObject", OBJECT, B_char, expression, type);
        best = isPredefined(best, "convertToObject", OBJECT, B_byte, expression, type);
        best = isPredefined(best, "convertToObject", OBJECT, B_short, expression, type);
        best = isPredefined(best, "convertToObject", OBJECT, B_int, expression, type);
        best = isPredefined(best, "convertToObject", OBJECT, B_long, expression, type);
        best = isPredefined(best, "convertToObject", OBJECT, B_float, expression, type);
        best = isPredefined(best, "convertToObject", OBJECT, B_double, expression, type);
        best = isPredefined(best, "convertToObject", OBJECT, BIG_INTEGER, expression, type);
        best = isPredefined(best, "convertToObject", OBJECT, BIG_DECIMAL, expression, type);
        best = isPredefined(best, "convertToObject", OBJECT, STRING, expression, type);
        best = isPredefined(best, "convertToObject", OBJECT, OBJECT, expression, type);

        if (best != null)
        {
            return best;
        }

        // The conversion must be a cast.

        /**
         * A type can be upcast to a supertype and a supertype can be downcast to a subtype.
         * However, there is no such thing as a cross-cast.
         */
        final boolean case1 = !(value.isSubtypeOf(type) || type.isSubtypeOf(value));

        /**
         * The input-type must be a reference-type,
         * because a primitive-type cannot be *cast* to another type.
         */
        final boolean case2 = value.isReferenceType() == false;

        /**
         * The output-type must be an reference-type,
         * because a value cannot be *cast* to a primitive-type.
         */
        final boolean case3 = value.isReferenceType() == false;

        if (case1 || case2 || case3)
        {
            return null;
        }

        // The conversion is an apparently valid cast.
        return new Conversion(null, value, type, true);
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
