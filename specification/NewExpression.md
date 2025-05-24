# New Expression

## Summary

A new-expression creates a new instance of a specified class-type.

## Syntax

```plain
<span class=\"keyword\">new</span> <i>[type](ConstructPage.html?construct=TypeSpecifier)</i> ( <i>[argument](TextPage.html?page=Expression)<sub>1</sub></i> , ... , <i>[argument](TextPage.html?page=Expression)<sub>n</sub></i> )
```

## AST Class

autumn.lang.compiler.ast.nodes.NewExpression

## Details
+ The constructor overload will be selected using the [Constructor Resolution Algorithm](TextPage.html?page=Resolution).
+ The constructor overload is selected at compile-time.
+ Boxing of the arguments will be performed, when necessary.
+ Unboxing of the arguments will be performed, when necessary.
+ Coercion of the arguments will be performed, when necessary.
+ Return Type: type of <i>type</i>
+ Return a new instance of the <i>type</i>.

