# Call Static Method Expression

## Summary

A call-static-expression performs an invocation of a static method or function.

## Syntax

```plain
<span class=\"keyword\">call</span> <i>[Owner](ConstructPage.html?construct=TypeSpecifier)</i>::<i>[name](ConstructPage.html?construct=Name)</i> ( <i>[argument](TextPage.html?page=Expression)<sub>1</sub></i> , ... , <i>[argument](TextPage.html?page=Expression)<sub>n</sub></i> )
<hr class=&#92%22syntax-hr&#92%22>
<i>[Owner](ConstructPage.html?construct=TypeSpecifier)</i>::<i>[name](ConstructPage.html?construct=Name)</i> ( <i>[argument](TextPage.html?page=Expression)<sub>1</sub></i> , ... , <i>[argument](TextPage.html?page=Expression)<sub>n</sub></i> )
```

## AST Class

autumn.lang.compiler.ast.nodes.CallStaticMethodExpression

## Details
+ The method overload will be selected using the [Static Method Resolution Algorithm](TextPage.html?page=Resolution).
+ The method overload is selected at compile-time.
+ Boxing of the arguments will be performed, when necessary.
+ Unboxing of the arguments will be performed, when necessary.
+ Coercion of the arguments will be performed, when necessary.
+ Remember, a function is technically a static method.
+ Return Type: [return-type of the selected method overload]
+ Return the value returned by the invoked method.

