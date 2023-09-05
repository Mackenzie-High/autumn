# Function Definition

## Summary

An function-definition creates a new function within the enclosing module.

## Syntax

<div class="syntax">
@<i>annotation<sub>1</sub></i><br>
@<i>annotation<sub>2</sub></i><br>
@<i>annotation<sub>n</sub></i><br>
<b>defun</b> <i><a href="Name.md">name</a></i> ( <i><a href="Formal_Parameter.md">param</a><sub>1</sub></i> , ... , <i><a href="Formal_Parameter.md">param</a><sub>n</sub></i> ) : <i><a href="TypeSpecifier.md">return-type</a></i><br>
{<br>
&nbsp;&nbsp;&nbsp;&nbsp;<i><a href="Statement.md">body</a></i><br>
}<br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.FunctionDefinition](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/FunctionDefinition.html)

## Details

+ Special Topics:
  + <a href="Infer_Functions.md">Infer Functions</a>
  + <a href="Start_Functions.md">Start Functions</a>
  + <a href="Setup_Functions.md">Setup Functions</a>
  + <a href="Sync_Functions.md">Sync Functions</a>
  + <a href="Test_Functions.md">Test Functions</a>
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

+ [DUPLICATE_ANNOTATION](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#DUPLICATE_ANNOTATION): Each annotation in an annotation-list must be uniquely typed.
+ [TOO_MANY_STARTS](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#TOO_MANY_STARTS): A compilation-unit can only contain one @Start function.
+ [WRONG_SIGNATURE_FOR_START](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#WRONG_SIGNATURE_FOR_START): A start-function must have a signature of: main(String[]) : void
+ [WRONG_SIGNATURE_FOR_SETUP](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#WRONG_SIGNATURE_FOR_SETUP): A setup-function must have a signature of: () : void
+ [WRONG_SIGNATURE_FOR_TEST](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#WRONG_SIGNATURE_FOR_TEST): A test-function must have a signature of: ([TestCase](https://mackenzie-high.github.io/autumn/javadoc/autumn/util/test/TestCase.html)) : void
+ [WRONG_SIGNATURE_FOR_INFER](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#WRONG_SIGNATURE_FOR_INFER): An infer-function must have a signature of: (<i>T</i>, ...) : <i>T</i>, where <i>T</i> is some reference-type.
+ [NO_SUCH_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#NO_SUCH_TYPE): The type specified by <i><i>param<sub>i</sub></i>.type</i> must exist.
+ [INACCESSIBLE_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#INACCESSIBLE_TYPE): The type specified by <i><i>param<sub>i</sub></i>.type</i> must be accessible.
+ [EXPECTED_VARIABLE_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#EXPECTED_VARIABLE_TYPE): The type of each parameter must be a variable-type.
+ [NO_SUCH_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#NO_SUCH_TYPE): The type specified by <i><i>return-type</i></i> must exist.
+ [INACCESSIBLE_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#INACCESSIBLE_TYPE): The type specified by <i><i>return-type</i></i> must be accessible.

## Example

**Source Code:**

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

