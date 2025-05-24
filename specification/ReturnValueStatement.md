# Return Value Statement

## Summary

A return-value statement causes execution to immediately exit the invocation of a function.

## Syntax

```plain
<span class=\"keyword\">return</span> <i>[value](TextPage.html?page=Expression)</i> ;
```

## AST Class

autumn.lang.compiler.ast.nodes.ReturnValueStatement

## Details
+ A return-value statement cannot be used in a function whose return-type is void.
+ The <i>value</i> will be boxed, if necessary.
+ The <i>value</i> will be unboxed, if necessary.
+ The <i>value</i> will be coerced, if necessary.

