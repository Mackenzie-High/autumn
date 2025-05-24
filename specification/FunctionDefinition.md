# Function Definition

## Summary

An function-definition creates a new function within the enclosing module.

## Syntax

```plain
@<i>annotation<sub>1</sub></i>
@<i>annotation<sub>2</sub></i>
@<i>annotation<sub>n</sub></i>
<span class=\"keyword\">defun</span> <i>[name](ConstructPage.html?construct=Name)</i> ( <i>[param](ConstructPage.html?construct=Formal Parameter)<sub>1</sub></i> , ... , <i>[param](ConstructPage.html?construct=Formal Parameter)<sub>n</sub></i> ) : <i>[return-type](ConstructPage.html?construct=TypeSpecifier)</i>
{
    <i>[body](TextPage.html?page=Statement)</i>
}
```

## AST Class

autumn.lang.compiler.ast.nodes.FunctionDefinition

## Details
+ Special Topics:
  + [Infer Functions](TextPage.html?page=Infer Functions)
  + [Start Functions](TextPage.html?page=Start Functions)
  + [Setup Functions](TextPage.html?page=Setup Functions)
  + [Sync Functions](TextPage.html?page=Sync Functions)
  + [Test Functions](TextPage.html?page=Test Functions)
+ Let T denote the type system representation of a function F.
  + T has the [FunctionDefinition](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/internals/annotations/FunctionDefinition.html) annotation applied directly to it.
  + T is the type of a [public](https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/Modifier.html#PUBLIC) [static](https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/Modifier.html#STATIC) [final](https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/Modifier.html#FINAL) method.
  + T is [synchronized](https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/Modifier.html#SYNCHRONIZED), if F is a sync-function.
  + T's throws clause implicitly includes [Throwable](https://docs.oracle.com/javase/7/docs/api/java/lang/Throwable.html).
  + T is a member of the enclosing module-type.
+ Scopes:
  + A function creates a new scope for variables.
  + A function creates a new scope for labels.
+ Runtime Checks:
  + A [UnexpectedTerminationException](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/exceptions/UnexpectedTerminationException.html) is thrown automatically, if execution reaches the end of a function.

