# Throw Statement

## Summary

A throw-statement throws an exception that is provided as an argument thereto.

## Syntax

```plain
<span class=\"keyword\">throw</span> <i>[argument](TextPage.html?page=Expression)</i> ;
```

## AST Class

autumn.lang.compiler.ast.nodes.ThrowStatement

## Details
+ In order to catch an exception, use a [Try-Catch Statement](ConstructPage.html?construct=Try-Catch Statement).
+ An uncaught exception will cause the enclosing invocation to terminate.
  + An uncaught exception will propogate until it is caught.
  + If an exception is caught by the runtime, then the program will be terminated.
+ Runtime Check: A [NullPointerException](https://docs.oracle.com/javase/7/docs/api/java/lang/NullPointerException.html) will be thrown, if the <i>argument</i> is null.

