# Instance-Of Expression

## Summary

An instance-of-expression determines whether a value is an instance of a particular type.

## Syntax

```plain
<span class=\"keyword\">instanceof</span> <i>[value](TextPage.html?page=Expression)</i> : <i>[type](ConstructPage.html?construct=TypeSpecifier)</i>
```

## AST Class

autumn.lang.compiler.ast.nodes.InstanceOfExpression

## Details
+ An instance-of operation is viable, iff:
  + The type of <i>value</i> is not the null-type.
  + and:
    + The type of <i>value</i> is a subtype of the type specified by <i>type</i>.
    + The type specified  by <i>type</i> is a subtype of the type of <i>value</i>.
+ Return Type: boolean
+ Return Return true, iff the <i>value</i> at runtime is both non-null and a subtype of the <i>type</i>.

