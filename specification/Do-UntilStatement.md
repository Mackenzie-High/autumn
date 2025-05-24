# Do-Until Statement

## Summary

A do-until-statement is a loop that iterates until a postcondition becomes true.

## Syntax

```plain
<span class=\"keyword\">do</span>
{
    <i>[body](TextPage.html?page=Statement)</i>
}
<span class=\"keyword\">until</span> ( <i>[condition](TextPage.html?page=Expression)</i> )
```

## AST Class

autumn.lang.compiler.ast.nodes.DoUntilStatement

## Details
+ The <i>body</i> will be executed at least once.
+ The <i>condition</i> will be unboxed, if necessary.
+ The <i>body</i> of a loop can contain break-statements.
+ The <i>body</i> of a loop can contain continue-statements.
+ The <i>body</i> of a loop can contain redo-statements.

