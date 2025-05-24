# Call Method Expression

## Summary

A call-expression performs an invocation of an instance method.

## Syntax

```plain
<span class=\"keyword\">call</span> <i>[owner](TextPage.html?page=Expression)</i>.<i>[name](ConstructPage.html?construct=Name)</i> ( <i>[argument](TextPage.html?page=Expression)<sub>1</sub></i> , ... , <i>[argument](TextPage.html?page=Expression)<sub>n</sub></i> )
<hr class=&#92%22syntax-hr&#92%22>
<i>[owner](TextPage.html?page=Expression)</i>.<i>[name](ConstructPage.html?construct=Name)</i> ( <i>[argument](TextPage.html?page=Expression)<sub>1</sub></i> , ... , <i>[argument](TextPage.html?page=Expression)<sub>n</sub></i> )
```

## AST Class

autumn.lang.compiler.ast.nodes.CallMethodExpression

## Details
+ The method overload will be selected using the [Instance Method Resolution Algorithm](TextPage.html?page=Resolution).
+ The method overload is selected at compile-time.
+ Boxing of the arguments will be performed, when necessary.
+ Unboxing of the arguments will be performed, when necessary.
+ Coercion of the arguments will be performed, when necessary.
+ Runtime Check: If <i>owner</i> is null, then a [NullPointerException](https://docs.oracle.com/javase/7/docs/api/java/lang/NullPointerException.html) will be thrown.
+ Return Type: [return-type of the selected method overload]
+ Return the value returned by the invoked method.

