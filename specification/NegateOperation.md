# Negate Operation

## Summary

This operator performs an arithmetic negation operation.

## Syntax

```plain
- <i>[value](TextPage.html?page=Expression)</i>
```

## AST Class

autumn.lang.compiler.ast.nodes.NegateOperation

## Details
+ Precedence: 1
+ Predefined Overloads:
  + (- byte) &#8614; byte
  + (- short) &#8614; short
  + (- int) &#8614; int
  + (- long) &#8614; long
  + (- float) &#8614; float
  + (- double) &#8614; double
  + (- [BigInteger](https://docs.oracle.com/javase/7/docs/api/java/math/BigInteger.html)) => [BigInteger](https://docs.oracle.com/javase/7/docs/api/java/math/BigInteger.html)
  + (- [BigDecimal](https://docs.oracle.com/javase/7/docs/api/java/math/BigDecimal.html)) => [BigDecimal](https://docs.oracle.com/javase/7/docs/api/java/math/BigDecimal.html)
+ The overload that best matches the operand will be selected.
  + Unboxing will be performed, if necessary.
  + Coercion will be performed, if necessary.
+ Return Type: Return-Type of Selected Overload
+ Return the result of the operation.

