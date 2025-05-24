# Get Static Field Expression

## Summary

A get-static-field-expression gets the value of a static field.

## Syntax

```plain
<span class=\"keyword\">field</span> <i>[Owner](ConstructPage.html?construct=TypeSpecifier)</i>::<i>[name](ConstructPage.html?construct=Name)</i> = <i>[value](TextPage.html?page=Expression)</i>
```

## AST Class

autumn.lang.compiler.ast.nodes.GetStaticFieldExpression

## Details
+ The field will be selected using the [Static Field Resolution Algorithm](TextPage.html?page=Resolution).
+ Return Type: [type of the selected field]
+ Return the value stored in the field.

