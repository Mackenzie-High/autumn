package autumn.util.data.sexpr;

import java.util.List;

/**
 * An instance of this interface defines a language consisting of symbolic-expressions.
 *
 * @author Mackenzie High
 */
public interface SexprLanguage
{
    /**
     * This method declares a rule that matches an atom.
     *
     * @param lhs is the name of the new grammar rule.
     */
    public void atom(final String lhs);

    /**
     * This method declares a rule that conditionally matches an atom.
     *
     * @param lhs is the name of the new grammar rule.
     * @param regex is a regular expression that the atom must match.
     */
    public void atom(final String lhs,
                     final String regex);

    /**
     * This method declares a rule that will match a string.
     *
     * @param lhs is the name of the new grammar rule.
     */
    public void string(final String lhs);

    /**
     * This method declares a rule that will conditionally match a string.
     *
     * @param lhs is the name of the new grammar rule.
     * @param regex is a regular expression that the string must match.
     */
    public void string(final String lhs,
                       final String regex);

    /**
     * This method declares a rule that matches a symbolic-list.
     *
     * @param lhs is the name of the grammar rule.
     * @param elements are the names of the rules that describe the elements of the symbolic-list.
     */
    public void sequence(final String lhs,
                         final List<String> elements);

    /**
     * This method declares a rule that matches a symbolic-list.
     *
     * @param lhs is the name of the grammar rule.
     * @param item is the name of a rule that describes the elements in the symbolic-list.
     */
    public void sequence(final String lhs,
                         final String item);

    /**
     * This method declares a rule that matches a symbolic-list.
     *
     * @param lhs is the name of the grammar rule.
     * @param item is the name of a rule that describes the elements in the symbolic-list.
     * @param minimum is the minimum length of the symbolic-list.
     * @param minimum is the maximum length of the symbolic-list.
     */
    public void sequence(final String lhs,
                         final String item,
                         final int minimum,
                         final int maximum);

    /**
     * This method declares a rule that based on an ordered choice using other rules.
     *
     * @param lhs is the name of the grammar rule.
     * @param options are the names of the grammar rules that will be chosen from.
     */
    public void choice(final String lhs,
                       final List<String> options);

    /**
     * This method declares a rule that matches a symbolic-list that is a function-call.
     *
     * @param name is the name of the function and the name of the grammar rule.
     * @param arguments are rules that describe the arguments passed to the function.
     */
    public void function(final String name,
                         final List<String> arguments);
}
