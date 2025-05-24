# Or Operation

## Summary

This operator performs a non-short-circuit logical OR-operation.

## Syntax

```plain
<i>[left](TextPage.html?page=Expression)</i> | <i>[right](TextPage.html?page=Expression)</i>
```

## AST Class

autumn.lang.compiler.ast.nodes.OrOperation

## Details
+ Precedence: 7
+ Associativity: Left
+ This is a [short-circuit](https://en.wikipedia.org/wiki/Short-circuit_evaluation) operator:
  + The left-operand is always evaluated.
  + The right-operand is only evaluated when the left-operand produces false.
+ Return Type: boolean
+ Return true when either the left-operand **or** the right-operand produces true.

