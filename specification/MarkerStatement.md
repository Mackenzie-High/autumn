# Marker Statement

## Summary

A marker-statement declares a label in the enclosing function.

## Syntax

```plain
<span class=\"keyword\">marker</span> <i>[label](ConstructPage.html?construct=Label)</i> ;
```

## AST Class

autumn.lang.compiler.ast.nodes.MarkerStatement

## Details
+ The <i>label</i> will be visible everywhere in the enclosing function.
+ Labels and variables are in distinct namespaces.
  + In other words, a variable can have the same name as a label.

