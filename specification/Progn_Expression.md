# Progn Expression

## Summary

A progn-expression executes a series of expressions sequentially.

## Syntax

<div class="syntax">
<b>progn</b> ( <i><a href="Expression.md">argument</a><sub>1</sub></i> , ... , <i><a href="Expression.md">argument</a><sub>n</sub></i> )<br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.PrognExpression](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/PrognExpression.html)

## Details

+ Return Type: type of <i><a href="Expression.md">argument</a><sub>n</sub></i>
+ Return the value produced by the last argument.

## Static Checks

+ [EMPTY_PROGN](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#EMPTY_PROGN): There must be at least one element in the sequence.
+ [VALUE_REQUIRED](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#VALUE_REQUIRED): The type of the <i><a href="Expression.md">argument</a><sub>n</sub></i> must be either a primitive-type or a reference-type.

## Example

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    val value = progn (F::println("X"), 
                       F::println("Y"), 
                       F::println("Z"),
                       1010);

    F::println("value = " .. value);
}
```

**Output:**

```plain
X
Y
Z
value = 1010
```

