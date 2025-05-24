# Locals Expression

## Summary

A locals-expression creates an object that describes the local variables in the enclosing scope.

## Syntax

```plain
( <span class=\"keyword\">locals</span> )
```

## AST Class

autumn.lang.compiler.ast.nodes.LocalsExpression

## Details
+ All user-visible variables in the enclosing scope will be captured.
  + This includes user-visible variables in outer scopes, if any.
  + This excludes temporary variables created by the compiler.
+ Return Type: [LocalsMap](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/LocalsMap.html)
+ Return an object that describes the local variables in the enclosing scope.

