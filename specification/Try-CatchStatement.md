# Try-Catch Statement

## Summary

A try-statement provides a mechanism for catching exceptions.

## Syntax

```plain
<span class=\"keyword\">try</span>
{
    <i>[body](TextPage.html?page=Statement)</i>
}
<span class=\"keyword\">catch</span> ( <i>[variable](ConstructPage.html?construct=Variable)<sub>1</sub></i> : <i>[type](ConstructPage.html?construct=TypeSpecifier)<sub>1</sub></i> )<sub>1</sub>
{
    <i>[handler](TextPage.html?page=Statement)<sub>1</sub></i>
}
<span class=\"keyword\">catch</span> ( <i>[variable](ConstructPage.html?construct=Variable)<sub>2</sub></i> : <i>[type](ConstructPage.html?construct=TypeSpecifier)<sub>2</sub></i> )<sub>2</sub>
{
    <i>[handler](TextPage.html?page=Statement)<sub>2</sub></i>
}
<span class=\"keyword\">catch</span> ( <i>[variable](ConstructPage.html?construct=Variable)<sub>n</sub></i> : <i>[type](ConstructPage.html?construct=TypeSpecifier)<sub>n</sub></i> )<sub>n</sub>
{
    <i>[handler](TextPage.html?page=Statement)<sub>n</sub></i>
}
```

## AST Class

autumn.lang.compiler.ast.nodes.TryCatchStatement

## Details
+ Exceptions can be thrown using a [Throw Statement](ConstructPage.html?construct=Throw Statement), [Assert Statement](ConstructPage.html?construct=Assert Statement), etc.
+ At least one handler is syntactically required.
+ An exception will only be caught when an applicable handler is available.
  + A handler is applicable when the exception's type is a subtype of the handler's <i>type</i>.
+ The scope of the <i><i>variable<sub>i</sub></i></i> is anywhere in the enclosing function.
+ The <i><i>variable<sub>i</sub></i></i> is alive precisely during an activation of the enclosing function.
+ The compiler will automatically reorder the handlers based on their specificity.
  + Reordering ensures that the handler that best matches an exception will be used to handle it.
  + A handler <i>X</i> is more specific than a handler <i>Y</i>, if <i>type<sub>X</sub></i> is a subclass of <i>type<sub>Y</sub></i>.

