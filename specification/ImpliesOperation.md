# Implies Operation

## Summary

This operator performs a  logical implication operation.

## Syntax

```plain
<i>[left](TextPage.html?page=Expression)</i> -> <i>[right](TextPage.html?page=Expression)</i>
```

## AST Class

autumn.lang.compiler.ast.nodes.XorOperation

## Details
+ Precedence: 7
+ Associativity: Left
+ This is a [short-circuit](https://en.wikipedia.org/wiki/Short-circuit_evaluation) operator:
  + The left-operand is always evaluated.
  + The right-operand is only evaluated when the left-operand produces true.
+ Return Type: boolean
+ Return true when the left-operand implies the right-operand.

