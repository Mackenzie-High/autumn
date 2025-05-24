# For Statement

## Summary

A for-statement is a loop that iterates based on a control variable and a condition.

## Syntax

```plain
<span class=\"keyword\">for</span> ( <i>[assignee](ConstructPage.html?construct=Variable)</i> = <i>[initializer](TextPage.html?page=Expression)</i> ; <i>[condition](TextPage.html?page=Expression)</i> ; <i>[modifier](TextPage.html?page=Expression)</i> )
{
    <i>[body](TextPage.html?page=Statement)</i>
}
```

## AST Class

autumn.lang.compiler.ast.nodes.ForStatement

## Details
+ The scope of the <i>assignee</i> is anywhere in the enclosing function.
+ The <i>assignee</i> is alive precisely during an activation of the enclosing function.
+ The type of the <i>assignee</i> variable will be primitive-type int.
+ The <i>assignee</i> is a readonly variable.
+ The <i>initializer</i> will be unboxed, if necessary.
+ The <i>condition</i> will be unboxed, if necessary.
+ The <i>modifier</i> will be unboxed, if necessary.
+ The <i>body</i> of a loop can contain break-statements.
+ The <i>body</i> of a loop can contain continue-statements.
+ The <i>body</i> of a loop can contain redo-statements.

