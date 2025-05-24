# List Expression

## Summary

A list-expression creates a new mutable list from a list of expressions.

## Syntax

```plain
[ <i>[value](TextPage.html?page=Expression)<sub>1</sub></i> , <i>[value](TextPage.html?page=Expression)<sub>2</sub></i> , ... , <i>[value](TextPage.html?page=Expression)<sub>n</sub></i> ]
```

## AST Class

autumn.lang.compiler.ast.nodes.ListExpression

## Details
+ The values of the elements will be boxed when necessary.
+ Return Type: [List](https://docs.oracle.com/javase/7/docs/api/java/util/List.html)
+ Return a new mutable list object is returned that contains the values of the arguments.

