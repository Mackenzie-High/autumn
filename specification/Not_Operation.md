# Not Operation

## Summary

This operator performs a logical-NOT operation.

## Syntax

<div class="syntax">
! <i><a href="Expression.md">value</a></i><br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.NotOperation](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/NotOperation.html)

## Details

+ Precedence: 1
+ Predefined Overload:
  + (! boolean) &#8614; boolean
+ Unboxing will be performed, if necessary.
+ Return Type: boolean
+ Return the result of the operation.

## Static Checks

+ [NO_SUCH_UNARY_OPERATOR](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#NO_SUCH_UNARY_OPERATOR): The overload will not accept the operand due to its type.

## Example

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    val case1 = ! false;
    val case2 = ! true;

    F::println(case1);
    F::println(case2);
}
```

**Output:**

```plain
true
false
```

