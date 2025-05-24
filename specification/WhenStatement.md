# When Statement

## Summary

A when-statement makes the execution of another statement conditional.

## Syntax

```plain
<span class=\"keyword\">when</span> ( <i>[condition](TextPage.html?page=Expression)</i> ) <span class=\"keyword\">then</span> <i>[statement](TextPage.html?page=Statement)</i>
```

## AST Class

autumn.lang.compiler.ast.nodes.WhenStatement

## Details
+ The condition will be unboxed, if necessary.
+ The statement will be executed, only if the <i>condition</i> produces true.
+ The <i>condition</i> will be unboxed, if necessary.

