# Concat Operation

## Summary

This operator performs a string concatenation operation.

## Syntax

<div class="syntax">
<i><a href="Expression.md">left</a></i> .. <i><a href="Expression.md">right</a></i><br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.ConcatOperation](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/ConcatOperation.html)

## Details

+ Precedence: 5
+ Associativity: Left
+ Predefined Overload:
  + ([Object](https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html) .. [Object](https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html)) &#8614; String
+ The operands will be boxed when necessary.
+ Both operands are greedily evaluated.
  + The left-operand is evaluated first.
  + The right-operand is evaluated second.
+ Return Type: [String](https://docs.oracle.com/javase/7/docs/api/java/lang/String.html)
+ Return the string representation of the left-operand prepended onto the string-representation of the right-operand..

## Static Checks

+ [NO_SUCH_BINARY_OPERATOR](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#NO_SUCH_BINARY_OPERATOR): None of the overloads will accept the left-operand due to its type.
+ [NO_SUCH_BINARY_OPERATOR](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#NO_SUCH_BINARY_OPERATOR): None of the overloads will accept the right-operand due to its type.

## Example

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    val output = "X " .. 2 .. " Y " .. 3 .. " Z";

    F::println(output);
}
```

**Output:**

```plain
X 2 Y 3 Z
```

