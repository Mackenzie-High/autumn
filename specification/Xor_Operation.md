# Xor Operation

## Summary

This operator performs a logical XOR-operation.

## Syntax

<div class="syntax">
<i><a href="Expression.md">left</a></i> ^ <i><a href="Expression.md">right</a></i><br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.XorOperation](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/XorOperation.html)

## Details

+ Precedence: 7
+ Associativity: Left
+ Both operands are greedily evaluated.
  + The left-operand is evaluated first.
  + The right-operand is evaluated second.
+ Return Type: boolean
+ Return true when the left-operand is true or the right-operand is true.

## Static Checks

+ [EXPECTED_CONDITION](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#EXPECTED_CONDITION): The type of the left-operand must be either boolean or [Boolean](https://docs.oracle.com/javase/7/docs/api/java/lang/Boolean.html).
+ [EXPECTED_CONDITION](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#EXPECTED_CONDITION): The type of the right-operand must be either boolean or [Boolean](https://docs.oracle.com/javase/7/docs/api/java/lang/Boolean.html).

## Example

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    val case1 = false ^ false;
    val case2 = false ^ true;
    val case3 = true ^ false;
    val case4 = true ^ true;

    F::println("F ^ F = " .. case1);
    F::println("F ^ T = " .. case2);
    F::println("T ^ F = " .. case3);
    F::println("T ^ T = " .. case4);
}
```

**Output:**

```plain
F ^ F = false
F ^ T = true
T ^ F = true
T ^ T = false
```

