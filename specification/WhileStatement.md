# While Statement

## Summary

A while-statement is a loop that iterates while a condition holds true.

## Syntax

```plain
<span class=\"keyword\">while</span> ( <i>[condition](TextPage.html?page=Expression)</i> )
{
    <i>[body](TextPage.html?page=Statement)</i>
}
```

## AST Class

autumn.lang.compiler.ast.nodes.WhileStatement

## Details
+ The <i>condition</i> will be unboxed, if necessary.
+ The <i>body</i> of a loop can contain break-statements.
+ The <i>body</i> of a loop can contain continue-statements.
+ The <i>body</i> of a loop can contain redo-statements.

