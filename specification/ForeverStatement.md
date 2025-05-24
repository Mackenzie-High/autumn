# Forever Statement

## Summary

A forever-statement is an infinite loop.

## Syntax

```plain
<span class=\"keyword\">forever</span>
{
    <i>[body](TextPage.html?page=Statement)</i>
}
```

## AST Class

autumn.lang.compiler.ast.nodes.ForeverStatement

## Details
+ The <i>body</i> of a loop can contain break-statements.
+ The <i>body</i> of a loop can contain continue-statements.
+ The <i>body</i> of a loop can contain redo-statements.

