# Set Field Expression

## Summary

A set-field-expression sets the value of an instance field.

## Syntax

```plain
<span class=\"keyword\">field</span> <i>[owner](TextPage.html?page=Expression)</i>.<i>[name](ConstructPage.html?construct=Name)</i> = <i>[value](TextPage.html?page=Expression)</i>
```

## AST Class

autumn.lang.compiler.ast.nodes.SetFieldExpression

## Details
+ The field will be selected using the [Instance Field Resolution Algorithm](TextPage.html?page=Resolution).
+ Boxing of the value will be performed, when necessary.
+ Unboxing of the value will be performed, when necessary.
+ Coercion of the value will be performed, when necessary.
+ Runtime Check: If <i>owner</i> is null, then a [NullPointerException](https://docs.oracle.com/javase/7/docs/api/java/lang/NullPointerException.html) will be thrown.
+ Return Type: void
+ Return nothing, because the return-type is void.

