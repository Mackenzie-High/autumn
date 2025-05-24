# Until Statement

## Summary

An until-statement is a loop that iterates until a condition becomes true.

## Syntax

```plain
<span class=\"keyword\">until</span> ( <i>[condition](TextPage.html?page=Expression)</i> )
{
    <i>[body](TextPage.html?page=Statement)</i>
}
```

## AST Class

autumn.lang.compiler.ast.nodes.UntilStatement

## Details
+ The <i>condition</i> will be unboxed, if necessary.
+ The <i>body</i> of a loop can contain break-statements.
+ The <i>body</i> of a loop can contain continue-statements.
+ The <i>body</i> of a loop can contain redo-statements.

