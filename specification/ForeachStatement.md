# Foreach Statement

## Summary

A foreach-statement is a loop that iterates over the elements in an iterable entity, such as a data-structure.

## Syntax

```plain
<span class=\"keyword\">foreach</span> ( <i>[assignee](ConstructPage.html?construct=Variable)</i> : <i>[type](ConstructPage.html?construct=TypeSpecifier)</i> <span class=\"keyword\">in</span> <i>[iterable](TextPage.html?page=Expression)</i> )
{
    <i>[body](TextPage.html?page=Statement)</i>
}
```

## AST Class

autumn.lang.compiler.ast.nodes.ForeachStatement

## Details
+ The scope of the <i>assignee</i> is anywhere in the enclosing function.
+ The <i>assignee</i> is alive precisely during an activation of the enclosing function.
+ The <i>assignee</i> is a readonly variable.
+ The <i>body</i> of a loop can contain break-statements.
+ The <i>body</i> of a loop can contain continue-statements.
+ The <i>body</i> of a loop can contain redo-statements.
+ A [ClassCastException](https://docs.oracle.com/javase/7/docs/api/java/lang/ClassCastException.html) will result at runtime, if the value returned by the iterator cannot be cast to the specified <i>type</i>.

