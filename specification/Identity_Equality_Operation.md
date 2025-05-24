# Identity Equality Operation

## Summary

This operator performs an equality comparison operation based on object identity.

## Syntax

<div class="syntax">
<i><a href="Expression.md">left</a></i> === <i><a href="Expression.md">right</a></i><br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.IdentityEqualsOperation](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/IdentityEqualsOperation.html)

## Details

+ Precedence: 6
+ Associativity: Left
+ Both operands are greedily evaluated.
  + The left-operand is evaluated first.
  + The right-operand is evaluated second.
+ Return Type: boolean
+ Return true when the identities of the two operands are equal.

## Static Checks

+ [EXPECTED_REFERENCE_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#EXPECTED_REFERENCE_TYPE): The type of the left-operand must be a reference-type.
+ [EXPECTED_REFERENCE_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#EXPECTED_REFERENCE_TYPE): The type of the right-operand must be a reference-type.

## Example

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    val object1 = new Object();
    val object2 = new Object();

    val case1 = object1 === object1;
    val case2 = object1 === object2;

    F::println(case1);
    F::println(case2);
}
```

**Output:**

```plain
true
false
```

