# Identity Equality Operation

## Summary

This operator performs an equality comparison operation based on object identity.

## Syntax

```plain
<i>[left](TextPage.html?page=Expression)</i> === <i>[right](TextPage.html?page=Expression)</i>
```

## AST Class

autumn.lang.compiler.ast.nodes.IdentityEqualsOperation

## Details
+ Precedence: 6
+ Associativity: Left
+ Both operands are greedily evaluated.
  + The left-operand is evaluated first.
  + The right-operand is evaluated second.
+ Return Type: boolean
+ Return true when the identities of the two operands are equal.

