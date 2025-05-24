# Xor Operation

## Summary

This operator performs a logical XOR-operation.

## Syntax

```plain
<i>[left](TextPage.html?page=Expression)</i> ^ <i>[right](TextPage.html?page=Expression)</i>
```

## AST Class

autumn.lang.compiler.ast.nodes.XorOperation

## Details
+ Precedence: 7
+ Associativity: Left
+ Both operands are greedily evaluated.
  + The left-operand is evaluated first.
  + The right-operand is evaluated second.
+ Return Type: boolean
+ Return true when the left-operand is true or the right-operand is true.

