# Do-While Statement

## Summary

A do-while-statement is a loop that iterates while a postcondition holds true.

## Syntax

```plain
<span class=\"keyword\">do</span>
{
    <i>[body](TextPage.html?page=Statement)</i>
}
<span class=\"keyword\">while</span> ( <i>[condition](TextPage.html?page=Expression)</i> )
```

## AST Class

autumn.lang.compiler.ast.nodes.DoWhileStatement

## Details
+ The <i>body</i> will be executed at least once.
+ The <i>condition</i> will be unboxed, if necessary.
+ The <i>body</i> of a loop can contain break-statements.
+ The <i>body</i> of a loop can contain continue-statements.
+ The <i>body</i> of a loop can contain redo-statements.

