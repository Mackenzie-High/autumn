# Let Statement

## Summary

A let-statement assigns a value to a local variable.

## Syntax

```plain
<span class=\"keyword\">let</span> <i>[assignee](ConstructPage.html?construct=Variable)</i> = <i>[value](TextPage.html?page=Expression)</i> ;
<hr class=&#92%22syntax-hr&#92%22>
<i>[assignee](ConstructPage.html?construct=Variable)</i> = <i>[value](TextPage.html?page=Expression)</i> ;
```

## AST Class

autumn.lang.compiler.ast.nodes.LetStatement

## Details
+ The <i>value</i> will be boxed, if necessary.
+ The <i>value</i> will be unboxed, if necessary.
+ The <i>value</i> will be coerced, if necessary.

