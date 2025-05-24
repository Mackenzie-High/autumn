# Dispatch Expression

## Summary

A dispatch-expression dispatches an invocation to a nearby function using multiple dispatch.

## Syntax

```plain
<span class=\"keyword\">dispatch</span> [name](ConstructPage.html?construct=Name) ( <i>[argument](TextPage.html?page=Expression)<sub>1</sub></i> , ... , <i>[argument](TextPage.html?page=Expression)<sub>n</sub></i> )
```

## AST Class

autumn.lang.compiler.ast.nodes.DispatchExpression

## Details
+ At compile-time, the compiler creates a dispatch table containing the overloads of the named function.
  + The overloads will be sorted topologically from the most specific to the most generalized.
  + In order for an overload to be included in the dispatch table:
    + The number of provided arguments must equal the number of parameters.
    + The type of each parameter must be a reference-type.
+ At runtime, the overload to invoke is selected as follows:
  + Let A<sub>1</sub> ... A<sub>n</sub> denote the arguments.
  + Select the first overload from the sorted list of overloads, where each argument matches the related parameter.
    + Let P<sub>1</sub> ... P<sub>n</sub> denote the types of an overload's parameters.
      + A<sub>i</sub> matches P<sub>i</sub>, iff:
      + A<sub>i</sub> is null.
      + A<sub>i</sub> is an instance of P<sub>i</sub>.
+ Boxing of the arguments will be performed, when necessary.
+ Unboxing of the arguments will not be performed.
+ Coercion of the arguments will not be performed.
+ Return Type: [Object](https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html)
  + If the return-type of the dynamically selected overload is the void-type, then return null.
  + Otherwise, return the value returned by invoking the dynamically selected overload.
    + Box the return-value, if the return-type is a primitive-type.
+ A [DispatchException](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/exceptions/DispatchException.html) will be thrown, if none of the selected overloads will accept the arguments at runtime.

