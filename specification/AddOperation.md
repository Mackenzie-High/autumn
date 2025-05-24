# Add Operation

## Summary

This operator performs an arithmetic addition operation.

## Syntax

```plain
<i>[left](TextPage.html?page=Expression)</i> + <i>[right](TextPage.html?page=Expression)</i>
```

## AST Class

autumn.lang.compiler.ast.nodes.AddOperation

## Details
+ Precedence: 4
+ Associativity: Left
+ Predefined Overloads:
  + (char + char) &#8614; char
  + (byte + byte) &#8614; byte
  + (short + short) &#8614; short
  + (int + int) &#8614; int
  + (long + long) &#8614; long
  + (float + float) &#8614; float
  + (double + double) &#8614; double
  + ([BigInteger](https://docs.oracle.com/javase/7/docs/api/java/math/BigInteger.html) + [BigInteger](https://docs.oracle.com/javase/7/docs/api/java/math/BigInteger.html)) &#8614; [BigInteger](https://docs.oracle.com/javase/7/docs/api/java/math/BigInteger.html)
  + ([BigDecimal](https://docs.oracle.com/javase/7/docs/api/java/math/BigDecimal.html) + [BigDecimal](https://docs.oracle.com/javase/7/docs/api/java/math/BigDecimal.html)) &#8614; [BigDecimal](https://docs.oracle.com/javase/7/docs/api/java/math/BigDecimal.html)
+ The overload that best matches the operands will be selected.
  + Unboxing will be performed, if necessary.
  + Coercion will be performed, if necessary.
+ Both operands are greedily evaluated.
  + The left-operand is evaluated first.
  + The right-operand is evaluated second.
+ Return Type: Return-Type of Selected Overload
+ Return the result of the operation.

