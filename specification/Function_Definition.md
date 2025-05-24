# Function Definition

## Summary

An function-definition creates a new function within the enclosing module.

## Syntax

<div id="syntax">
@<i>annotation<sub>1</sub></i><br>
@<i>annotation<sub>2</sub></i><br>
@<i>annotation<sub>n</sub></i><br>
<span class=\"keyword\">defun</span> <i>[name](ConstructPage.html?construct=Name)</i> ( <i>[param](ConstructPage.html?construct=Formal Parameter)<sub>1</sub></i> , ... , <i>[param](ConstructPage.html?construct=Formal Parameter)<sub>n</sub></i> ) : <i>[return-type](ConstructPage.html?construct=TypeSpecifier)</i><br>
{<br>
&nbsp;&nbsp;&nbsp;&nbsp;<i>[body](TextPage.html?page=Statement)</i><br>
}<br>
</div>

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

## Static Checks

[DUPLICATE_ANNOTATION, Each annotation in an annotation-list must be uniquely typed., null]
[TOO_MANY_STARTS, A compilation-unit can only contain one @Start function., null]
[WRONG_SIGNATURE_FOR_START, A start-function must have a signature of: main(String[]) : void, null]
[WRONG_SIGNATURE_FOR_SETUP, A setup-function must have a signature of: () : void, null]
[WRONG_SIGNATURE_FOR_TEST, A test-function must have a signature of: ([TestCase](https://mackenzie-high.github.io/autumn/javadoc/autumn/util/test/TestCase.html)) : void, null]
[WRONG_SIGNATURE_FOR_INFER, An infer-function must have a signature of: (<i>T</i>, ...) : <i>T</i>, where <i>T</i> is some reference-type., null]
[NO_SUCH_TYPE, The type specified by <i><i>param<sub>i</sub></i>.type</i> must exist., null]
[INACCESSIBLE_TYPE, The type specified by <i><i>param<sub>i</sub></i>.type</i> must be accessible., null]
[EXPECTED_VARIABLE_TYPE, The type of each parameter must be a variable-type., null]
[NO_SUCH_TYPE, The type specified by <i><i>return-type</i></i> must exist., null]
[INACCESSIBLE_TYPE, The type specified by <i><i>return-type</i></i> must be accessible., null]

## Example

**Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    F::println("Hello World!");
}
```

**Output:**

```plain
Hello World!
```

