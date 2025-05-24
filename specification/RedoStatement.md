# Redo Statement

## Summary

A redo-statement causes execution to immediately restart the current iteration of the nearest enclosing loop.

## Syntax

```plain
<span class=\"keyword\">redo</span> ;
```

## AST Class

autumn.lang.compiler.ast.nodes.RedoStatement

## Details
+ A redo-statement cannot be used to exit an invocation.
+ Types of Loops:
  + [Forever Statement](ConstructPage.html?construct=Forever Statement)s
  + [While Statement](ConstructPage.html?construct=While Statement)s
  + [Until Statement](ConstructPage.html?construct=Until Statement)s
  + [Do-While Statement](ConstructPage.html?construct=Do-While Statement)s
  + [Do-Until Statement](ConstructPage.html?construct=Do-Until Statement)s
  + [For Statement](ConstructPage.html?construct=For Statement)s
  + [Foreach Statement](ConstructPage.html?construct=Foreach Statement)s

