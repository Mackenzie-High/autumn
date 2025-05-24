# Val Statement

## Summary

A val-statement declares a new readonly local variable.

## Syntax

```plain
<span class=\"keyword\">val</span> <i>[assignee](ConstructPage.html?construct=Variable)</i> = <i>[value](TextPage.html?page=Expression)</i> ;
```

## AST Class

autumn.lang.compiler.ast.nodes.ValStatement

## Details
+ The value stored in the variable will be reassigned, if the val-statement is executed again.
+ The scope of the <i>assignee</i> is anywhere in the enclosing function.
+ The <i>assignee</i> is alive precisely during an activation of the enclosing function.
+ The type of the newly declared variable is the type of the <i>value</i>.
  + In other words, the type of the variable is inferred.

