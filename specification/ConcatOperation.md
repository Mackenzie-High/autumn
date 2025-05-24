# Concat Operation

## Summary

This operator performs a string concatenation operation.

## Syntax

```plain
<i>[left](TextPage.html?page=Expression)</i> .. <i>[right](TextPage.html?page=Expression)</i>
```

## AST Class

autumn.lang.compiler.ast.nodes.ConcatOperation

## Details
+ Precedence: 5
+ Associativity: Left
+ Predefined Overload:
  + ([Object](https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html) .. [Object](https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html)) &#8614; String
+ The operands will be boxed when necessary.
+ Both operands are greedily evaluated.
  + The left-operand is evaluated first.
  + The right-operand is evaluated second.
+ Return Type: [String](https://docs.oracle.com/javase/7/docs/api/java/lang/String.html)
+ Return the string representation of the left-operand prepended onto the string-representation of the right-operand..

