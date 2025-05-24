# Assert Statement

## Summary

An assert-statement enforces an invariant.

## Syntax

```plain
<span class=\"keyword\">assert</span> <i>[condition](TextPage.html?page=Expression)</i> ;
<hr class=&#92%22syntax-hr&#92%22>
<span class=\"keyword\">assert</span> <i>[condition](TextPage.html?page=Expression)</i> <span class=\"keyword\">echo</span> <i>[message](TextPage.html?page=Expression)</i> ;
```

## AST Class

autumn.lang.compiler.ast.nodes.AssertStatement

## Details
+ Unlike an [Assume Statement](ConstructPage.html?construct=Assume Statement), assert-statements cannot be disabled.
+ If the <i>condition</i> evaluates to false, then an [AssertionFailedException](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/exceptions/AssertionFailedException.html) will be thrown.
+ If the <i>condition</i> evaluates to true, then execution simply continues onward.
+ The <i>condition</i> will be unboxed, if necessary.

