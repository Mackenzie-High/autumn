# Not Operation

## Summary

This operator performs a logical-NOT operation.

## Syntax

```plain
! <i>[value](TextPage.html?page=Expression)</i>
```

## AST Class

autumn.lang.compiler.ast.nodes.NotOperation

## Details
+ Precedence: 1
+ Predefined Overload:
  + (! boolean) &#8614; boolean
+ Unboxing will be performed, if necessary.
+ Return Type: boolean
+ Return the result of the operation.

