# Ternary Conditional Expression

## Summary

A ternary-conditional-expression conditionally chooses one of two values.

## Syntax

```plain
( <span class=\"keyword\">if</span> <i>[condition](TextPage.html?page=Expression)</i> <span class=\"keyword\">then</span> <i>[left](TextPage.html?page=Expression)</i> <span class=\"keyword\">else</span> <i>[right](TextPage.html?page=Expression)</i>)
```

## AST Class

autumn.lang.compiler.ast.nodes.TernaryConditionalExpression

## Details
+ The <i>left</i> expression is only evaluated, if the <i>condition</i> produces true.
+ The <i>right</i> expression is only evaluated, if the <i>condition</i> produces false.
+ The <i>condition</i> will be unboxed, if necessary.
+ Return Type: widest(typeof(<i>left</i>), typeof(<i>right</i>))
+ Return either the value of <i>left</i> or the value of <i>right</i>, depending on the value produced by the <i>condition</i>.

