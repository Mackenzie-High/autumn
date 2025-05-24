# Get Field Expression

## Summary

A get-field-expression gets the value of an instance field.

## Syntax

```plain
<span class=\"keyword\">field</span> <i>[owner](TextPage.html?page=Expression)</i>.<i>[name](ConstructPage.html?construct=Name)</i>
```

## AST Class

autumn.lang.compiler.ast.nodes.GetFieldExpression

## Details
+ The field will be selected using the [Instance Field Resolution Algorithm](TextPage.html?page=Resolution).
+ Return Type: [type of the selected field]
+ Return the value stored in the field.

