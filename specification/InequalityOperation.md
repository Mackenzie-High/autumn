# Inequality Operation

## Summary

This operator performs an inequality comparison operation.

## Syntax

```plain
<i>[left](TextPage.html?page=Expression)</i> != <i>[right](TextPage.html?page=Expression)</i>
```

## AST Class

autumn.lang.compiler.ast.nodes.NotEqualsOperation

## Details
+ Precedence: 6
+ Associativity: Left
+ Predefined Overloads:
  + (boolean != boolean) &#8614; boolean
  + (char != char) &#8614; boolean
  + (byte != byte) &#8614; boolean
  + (short != short) &#8614; boolean
  + (int != int) &#8614; boolean
  + (long != long) &#8614; boolean
  + (float != float) &#8614; boolean
  + (double != double) &#8614; boolean
  + ([BigInteger](https://docs.oracle.com/javase/7/docs/api/java/math/BigInteger.html) != [BigInteger](https://docs.oracle.com/javase/7/docs/api/java/math/BigInteger.html)) &#8614; [BigInteger](https://docs.oracle.com/javase/7/docs/api/java/math/BigInteger.html)
  + ([BigDecimal](https://docs.oracle.com/javase/7/docs/api/java/math/BigDecimal.html) != [BigDecimal](https://docs.oracle.com/javase/7/docs/api/java/math/BigDecimal.html)) &#8614; [BigDecimal](https://docs.oracle.com/javase/7/docs/api/java/math/BigDecimal.html)
  + ([Object](https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html) != [Object](https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html)) &#8614; boolean
+ The overload that best matches the operands will be selected.
  + Boxing will be performed, if necessary.
  + Unboxing will be performed, if necessary.
  + Coercion will be performed, if necessary.
+ Both operands are greedily evaluated.
  + The left-operand is evaluated first.
  + The right-operand is evaluated second.
+ Return Type: boolean
+ Return true when the two operands are unequal.

