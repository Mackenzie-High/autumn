package autumn.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.List;

/**
 * This class provides static utility methods for working with strings.
 *
 * @author Mackenzie High
 */
public final class Strings
{
    /**
     * This method replaces escape-sequences in a string with the characters they represent.
     *
     * @param string the string that may contain escape-sequences.
     * @return the modified string.
     * @throws NullPointerException if string is null.
     * @throws IllegalArgumentException if an escape-sequence is malformed.
     */
    public static String escape(final CharSequence string)
    {
        Preconditions.checkNotNull(string);

        // This is the string being built.
        final StringBuilder result = new StringBuilder();

        // This is true, when an escape-sequence is being processed.
        boolean in_escape = false;

        // This is the number of characters to advance forward.
        int increment;

        for (int i = 0; i < string.length(); i = i + increment)
        {
            increment = 1;

            final char chr = string.charAt(i);

            if (in_escape == false && chr == '\\')
            {
                in_escape = true;
            }
            else if (in_escape && chr == 't')
            {
                result.append('\t');
                in_escape = false;
            }
            else if (in_escape && chr == 'b')
            {
                result.append('\b');
                in_escape = false;
            }
            else if (in_escape && chr == 'n')
            {
                result.append('\n');
                in_escape = false;
            }
            else if (in_escape && chr == 'r')
            {
                result.append('\r');
                in_escape = false;
            }
            else if (in_escape && chr == 'f')
            {
                result.append('\f');
                in_escape = false;
            }
            else if (in_escape && chr == '\'')
            {
                result.append('\'');
                in_escape = false;
            }
            else if (in_escape && chr == '"')
            {
                result.append('\"');
                in_escape = false;
            }
            else if (in_escape && chr == '\\')
            {
                result.append('\\');
                in_escape = false;
            }
            else if (in_escape && string.length() >= i + 5)
            {
                /**
                 * Get the character-code, which consist of five digits.
                 */
                final String escape_string = string.subSequence(i, i + 5).toString();

                /**
                 * The string must contain exactly five digits.
                 */
                if (!escape_string.matches("[0-9][0-9][0-9][0-9][0-9]"))
                {
                    Preconditions.checkArgument(false, "Invalid Escape Sequence: \\" + escape_string);
                }

                /**
                 * The character-code must be an integer in range [0, Character.MAX_VALUE].
                 */
                final int value = Integer.parseInt(escape_string);

                if (value < 0 || value > Character.MAX_VALUE)
                {
                    Preconditions.checkArgument(false, "Invalid Escape Sequence: \\" + escape_string);
                }

                /**
                 * Add the specified character to the string.
                 */
                final char character = (char) value;
                result.append(character);

                /**
                 * Skip over the escape-string and exit the escape-sequence.
                 */
                increment = increment + 4;
                in_escape = false;
            }
            else if (in_escape)
            {
                Preconditions.checkArgument(false, "Invalid Escape Sequence: \\" + chr);
            }
            else
            {
                result.append(chr);
            }
        }

        return result.toString();
    }

    /**
     * This method replaces escapable characters with escape-sequences.
     *
     * @param string is the string to un-escape.
     * @return the string with escapable characters replaced with escape-sequences.
     */
    public static String unescape(final CharSequence string)
    {
        Preconditions.checkNotNull(string);

        // These are the characters that can be replaced with an escape-sequence.
        final String escapable = "\t\b\n\r\f\'\"\\";

        final String escaped = "tbnrf'\"\\";

        final StringBuilder result = new StringBuilder();

        for (int i = 0; i < string.length(); i++)
        {
            final char chr = string.charAt(i);

            if (escapable.indexOf(chr) >= 0)
            {
                result.append('\\');
                result.append(escaped.charAt(escapable.indexOf(chr)));
            }
            else if (chr == 0)
            {
                result.append("\\00000");
            }
            else
            {
                result.append(chr);
            }
        }

        return result.toString();
    }

    /**
     * This method pads the start of a string.
     *
     * @param string is the string to pad.
     * @param character is the padding character.
     * @param length is the required minimum length of the string.
     * @return the padded string.
     */
    public static String padStart(final CharSequence string,
                                  final char character,
                                  final int length)
    {
        Preconditions.checkNotNull(string);
        Preconditions.checkArgument(length >= 0);

        final int size = string.length();

        final StringBuilder result = new StringBuilder();

        // Add the padding characters.
        while ((result.length() + size) < length)
        {
            result.append(character);
        }

        result.append(string);

        return result.toString();
    }

    /**
     * This method pads the end of a string.
     *
     * @param string is the string to pad.
     * @param character is the padding character.
     * @param length is the required minimum length of the string.
     * @return the padded string.
     */
    public static String padEnd(final CharSequence string,
                                final char character,
                                final int length)
    {
        Preconditions.checkNotNull(string);
        Preconditions.checkArgument(length >= 0);

        final int size = string.length();

        final StringBuilder result = new StringBuilder(string);

        // Add the padding characters.
        while ((result.length() + size) < length)
        {
            result.append(character);
        }

        return result.toString();
    }

    /**
     * This method pads the beginning of a string with zeros, if needed.
     *
     * @param string is the string to possibly pad.
     * @param length is the desired minimum length of the result.
     * @return the padded string.
     */
    public static String zfill(final CharSequence string,
                               final int length)
    {
        return padStart(string, '0', length);
    }

    /**
     * This method creates a string that is another string repeated zero or more times.
     *
     * @param string is the string to repeat.
     * @param times is the number of times to repeat the string.
     * @return the aforedescribed string.
     */
    public static String repeat(final CharSequence string,
                                final int times)
    {
        Preconditions.checkNotNull(string, "string");
        Preconditions.checkArgument(times >= 0, "times");

        final StringBuilder result = new StringBuilder();

        for (int i = 0; i < times; i++)
        {
            result.append(string);
        }

        return string.toString();
    }

    /**
     * This method creates the string representation of a value.
     *
     * @param value is the value itself.
     * @return value.toString(), if the value is not null; otherwise return "null".
     */
    public static String str(final Object value)
    {
        return "" + value;
    }

    /**
     * This method creates a string representation for an iterable.
     *
     * @param iterable is the iterable itself.
     * @param prefix is a string to prepend onto the result.
     * @param separator is the substring used to separate elements in the result.
     * @param suffix is a string to append onto the result.
     * @return the aforedescribed result.
     */
    public static String str(final Iterable<?> iterable,
                             final String prefix,
                             final String separator,
                             final String suffix)
    {
        Preconditions.checkNotNull(iterable);
        Preconditions.checkNotNull(prefix);
        Preconditions.checkNotNull(separator);
        Preconditions.checkNotNull(suffix);

        final List<?> elements = Lists.newArrayList(iterable);

        final StringBuilder result = new StringBuilder();

        result.append(prefix);
        {
            int count = 0;

            for (Object arg : elements)
            {
                ++count;

                result.append(arg);

                if (count < elements.size())
                {
                    result.append(separator);
                }
            }
        }
        result.append(suffix);

        return result.toString();
    }

    /**
     * This method finds the longest-common-substring in two strings.
     *
     * @param string1 is the first string.
     * @param string2 is the second string.
     * @return the longest-common-substring in the two strings.
     */
    public static String longestCommonSubstring(final CharSequence string1,
                                                final CharSequence string2)
    {
        return null; // TODO - dynamic programming
    }

    /**
     * This method determines whether a string value is either null or the empty string.
     *
     * @param string is the value to check.
     * @return true, iff the argument is null or the empty string.
     */
    public static boolean isNullOrEmpty(final CharSequence string)
    {
        return string == null || string.length() == 0;
    }

    /**
     * This method finds the common prefix of two strings.
     *
     * @param string1 is the first string.
     * @param string2 is the second string.
     * @return the prefix that string1 and string2 share.
     */
    public static String commonPrefix(final CharSequence string1,
                                      final CharSequence string2)
    {
        Preconditions.checkNotNull(string1, "string1");
        Preconditions.checkNotNull(string1, "string2");

        final StringBuilder common = new StringBuilder();

        for (int i = 0; i < string1.length() && i < string2.length(); i++)
        {
            if (string1.charAt(i) == string2.charAt(i))
            {
                common.append(string1.charAt(i));
            }
            else
            {
                break;
            }
        }

        return common.toString();
    }

    /**
     * This method finds the common suffix of two strings.
     *
     * @param string1 is the first string.
     * @param string2 is the second string.
     * @return the suffix that string1 and string2 share.
     */
    public static String commonSuffix(final CharSequence string1,
                                      final CharSequence string2)
    {
        Preconditions.checkNotNull(string1, "string1");
        Preconditions.checkNotNull(string1, "string2");

        final StringBuilder common = new StringBuilder();

        for (int i = 1; string1.length() - i >= 0 && string2.length() - i >= 0; i++)
        {
            if (string1.charAt(string1.length() - i) == string2.charAt(string2.length() - i))
            {
                common.append(string1.charAt(i));
            }
            else
            {
                break;
            }
        }

        return common.reverse().toString();
    }

    /**
     * This method computes the Levenshtein distance between two strings.
     *
     * <p>
     * This is a string similarity algorithm.
     * The more similar the two strings are, the lower the Levenshtein distance.
     * </p>
     *
     * @param string1 is the first string.
     * @param string2 is the second string.
     * @return the Levenshtein distance between the two strings.
     */
    public static int levenshteinDistance(final String string1,
                                          final String string2)
    {
        return 0;
    }
}
