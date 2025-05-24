# Assume Statement

## Summary

An assume-statement enforces an invariant.

## Syntax

```plain
<span class=\"keyword\">assume</span> <i>[condition](TextPage.html?page=Expression)</i> ;
<hr class=&#92%22syntax-hr&#92%22>
<span class=\"keyword\">assume</span> <i>[condition](TextPage.html?page=Expression)</i> <span class=\"keyword\">echo</span> <i>[message](TextPage.html?page=Expression)</i> ;
```

## AST Class

autumn.lang.compiler.ast.nodes.AssertStatement

## Details
+ Unlike an [Assert Statement](ConstructPage.html?construct=Assert Statement), assume-statements can be disabled.
  + By default, assume-statements are enabled.
  + If assume-statements are disabled:
    + The runtime simply ignores all assume-statements.
    + The <i>condition</i> is not evaluated.
    + The <i>message</i> is not evaluated.
  + To enable assume-statements use: [enableAssume()](https://www.mackenziehigh.me/autumn/javadoc/autumn/lang/compiler/Autumn.html#enableAssume())
  + To disable assume-statements use: [disableAssume()](https://www.mackenziehigh.me/autumn/javadoc/autumn/lang/compiler/Autumn.html#disableAssume())
+ If the <i>condition</i> evaluates to false, then an [AssumptionFailedException](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/exceptions/AssumptionFailedException.html) will be thrown.
+ If the <i>condition</i> evaluates to true, then execution simply continues onward.
+ The <i>condition</i> will be unboxed, if necessary.

