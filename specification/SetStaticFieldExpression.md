# Set Static Field Expression

## Summary

A set-static-field-expression sets the value of a static field.

## Syntax

```plain
<span class=\"keyword\">field</span> <i>[Owner](ConstructPage.html?construct=TypeSpecifier)</i>::<i>[name](ConstructPage.html?construct=Name)</i> = <i>[value](TextPage.html?page=Expression)</i>
```

## AST Class

autumn.lang.compiler.ast.nodes.SetStaticFieldExpression

## Details
+ The field will be selected using the [Static Field Resolution Algorithm](TextPage.html?page=Resolution).
+ Boxing of the value will be performed, when necessary.
+ Unboxing of the value will be performed, when necessary.
+ Coercion of the value will be performed, when necessary.
+ Return Type: void
+ Return nothing, because the return-type is void.

