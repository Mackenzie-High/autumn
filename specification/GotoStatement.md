# Goto Statement

## Summary

A goto-statement causes execution to immediately jump to a labeled location.

## Syntax

```plain
<span class=\"keyword\">goto</span> <i>[label](ConstructPage.html?construct=Label)</i> ;
```

## AST Class

autumn.lang.compiler.ast.nodes.GotoStatement

## Details
+ A goto-statement cannot be used to jump out of a function.

