# New Expression

## Summary

A new-expression creates a new instance of a specified class-type.

## Syntax

<div class="syntax">
<b>new</b> <i><a href="Type_Specifier.md">type</a></i> ( <i><a href="Expression.md">argument</a><sub>1</sub></i> , ... , <i><a href="Expression.md">argument</a><sub>n</sub></i> )<br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.NewExpression](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/NewExpression.html)

## Details

+ The constructor overload will be selected using the <a href="Resolution.md">Constructor Resolution Algorithm</a>.
+ The constructor overload is selected at compile-time.
+ Boxing of the arguments will be performed, when necessary.
+ Unboxing of the arguments will be performed, when necessary.
+ Coercion of the arguments will be performed, when necessary.
+ Return Type: type of <i>type</i>
+ Return a new instance of the <i>type</i>.

## Static Checks

+ [NO_SUCH_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#NO_SUCH_TYPE): The type specified by <i><i>type</i></i> must exist.
+ [INACCESSIBLE_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#INACCESSIBLE_TYPE): The type specified by <i><i>type</i></i> must be accessible.
+ [EXPECTED_CLASS_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#EXPECTED_CLASS_TYPE): The <i>type</i> must be a class-type.
+ [NO_SUCH_CONSTRUCTOR](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#NO_SUCH_CONSTRUCTOR): No acceptable constructor overload was found.

## Example

**Source Code:**

```plain
module Main in examples;

tuple Pet (type : String, name : String);

@Start
defun main (args : String[]) : void
{
    val animal1 = new Pet("Sheep", "Eyeball");

    val animal2 = new Pet("German Shepherd", "Jet");

    val animal3 = new Pet("Cat", "Fluffy");

    F::printlns([animal1, animal2, animal3]);
}
```

**Output:**

```plain
(Sheep, Eyeball)
(German Shepherd, Jet)
(Cat, Fluffy)
```

