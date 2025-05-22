/**
 * This packages contains data-structures for representing literals, which may be malformed.
 *
 * <p>
 * It is possible that literals in source-code may be malformed.
 * For example, the byte literal "300" is malformed, because it is too large for a byte.
 * As a result, instances of the Byte, Integer, Long, etc classes cannot be used to store literals.
 * </p>
 */
package autumn.lang.compiler.ast.literals;
