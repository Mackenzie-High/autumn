# Null Coalescing Operation

## Summary

A null-coalescing provides an alternate value when a primary value is null.

## Syntax

```plain
<i>[left](TextPage.html?page=Expression)</i> ?? <i>[right](TextPage.html?page=Expression)</i>
```

## AST Class

autumn.lang.compiler.ast.nodes.NullCoalescingOperation

## Details
+ Precedence: 8
+ Associativity: Left
+ The left-operand is always evaluated.
+ The right-operand is only evaluated when the left-operand produces null.
+ Return Type: widest(typeof(<i>left</i>), typeof(<i>right</i>))
+ Return the left-operand, if the value is not null; otherwise, the right-operand.

