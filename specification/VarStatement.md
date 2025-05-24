# Var Statement

## Summary

A var-statement declares a new mutable local variable.

## Syntax

```plain
<span class=\"keyword\">var</span> <i>[assignee](ConstructPage.html?construct=Variable)</i> = <i>[value](TextPage.html?page=Expression)</i> ;
```

## AST Class

autumn.lang.compiler.ast.nodes.VarStatement

## Details
+ The scope of the <i>assignee</i> is anywhere in the enclosing function.
+ The <i>assignee</i> is alive precisely during an activation of the enclosing function.
+ The type of the newly declared variable is the type of the <i>value</i>.
  + In other words, the type of the variable is inferred.

