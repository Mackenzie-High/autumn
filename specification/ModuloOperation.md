# Modulo Operation

## Summary

This operator performs an arithmetic modulus operation.

## Syntax

```plain
<i>[left](TextPage.html?page=Expression)</i> &$37; <i>[right](TextPage.html?page=Expression)</i>
```

## AST Class

autumn.lang.compiler.ast.nodes.ModuloOperation

## Details
+ Precedence: 2
+ Associativity: Left
+ Predefined Overloads:
  + (char &$37; char) &#8614; char
  + (byte &$37; byte) &#8614; byte
  + (short &$37; short) &#8614; short
  + (int &$37; int) &#8614; int
  + (long &$37; long) &#8614; long
  + (float &$37; float) &#8614; float
  + (double &$37; double) &#8614; double
  + ([BigInteger](https://docs.oracle.com/javase/7/docs/api/java/math/BigInteger.html) &$37; [BigInteger](https://docs.oracle.com/javase/7/docs/api/java/math/BigInteger.html)) &#8614; [BigInteger](https://docs.oracle.com/javase/7/docs/api/java/math/BigInteger.html)
  + ([BigDecimal](https://docs.oracle.com/javase/7/docs/api/java/math/BigDecimal.html) &$37; [BigDecimal](https://docs.oracle.com/javase/7/docs/api/java/math/BigDecimal.html)) &#8614; [BigDecimal](https://docs.oracle.com/javase/7/docs/api/java/math/BigDecimal.html)
+ The overload that best matches the operands will be selected.
  + Unboxing will be performed, if necessary.
  + Coercion will be performed, if necessary.
+ Both operands are greedily evaluated.
  + The left-operand is evaluated first.
  + The right-operand is evaluated second.
+ Return Type: Return-Type of Selected Overload
+ Return the result of the operation.

