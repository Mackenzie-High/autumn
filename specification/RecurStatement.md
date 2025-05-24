# Recur Statement

## Summary

A recur-statement performs a tail-recursive invocation of the enclosing function.

## Syntax

```plain
<span class=\"keyword\">recur</span> <i>[argument](TextPage.html?page=Expression)<sub>1</sub></i> , <i>[argument](TextPage.html?page=Expression)<sub>2</sub></i> , ... , <i>[argument](TextPage.html?page=Expression)<sub>n</sub></i> ;
```

## AST Class

autumn.lang.compiler.ast.nodes.RecurStatement

## Details
+ A recur-statement can be thought of as a combination of a return-statement and a call-expression.
+ Each <i>argument</i> will be boxed, if necessary.
+ Each <i>argument</i> will be unboxed, if necessary.
+ Each <i>argument</i> will be coerced, if necessary.
+ The <i>return-type</i> of the enclosing function can be void.
+ The <i>return-type</i> of the enclosing function can be non-void.

