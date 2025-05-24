# Variable Datum

## Summary

A variable-datum retrieves the values stored in a variable.

## Syntax

```plain
<i>[variable](ConstructPage.html?construct=Variable)</i>
```

## AST Class

autumn.lang.compiler.ast.nodes.VariableDatum

## Details
+ A variable will return its default-value, if it is accessed before its first assignment.
+ Return Type: (type of the referenced variable per the variable's declaration)
+ Return the value stored in the referenced variable.

