# Implies Operation

## Summary

This operator performs a  logical implication operation.

## Syntax

<div class="syntax">
<i><a href="Expression.md">left</a></i> -> <i><a href="Expression.md">right</a></i><br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.XorOperation](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/XorOperation.html)

## Details

+ Precedence: 7
+ Associativity: Left
+ This is a [short-circuit](https://en.wikipedia.org/wiki/Short-circuit_evaluation) operator:
  + The left-operand is always evaluated.
  + The right-operand is only evaluated when the left-operand produces true.
+ Return Type: boolean
+ Return true when the left-operand implies the right-operand.

## Static Checks

+ [EXPECTED_CONDITION](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#EXPECTED_CONDITION): The type of the left-operand must be either boolean or [Boolean](https://docs.oracle.com/javase/7/docs/api/java/lang/Boolean.html).
+ [EXPECTED_CONDITION](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#EXPECTED_CONDITION): The type of the right-operand must be either boolean or [Boolean](https://docs.oracle.com/javase/7/docs/api/java/lang/Boolean.html).

## Example 1

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    val case1 = false -> false;
    val case2 = false -> true;
    val case3 = true -> false;
    val case4 = true -> true;

    F::println("F -> F = " .. case1);
    F::println("F -> T = " .. case2);
    F::println("T -> F = " .. case3);
    F::println("T -> T = " .. case4);
}
```

**Output:**

```plain
F -> F = true
F -> T = true
T -> F = false
T -> T = true
```

## Example 2

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    val case1 = false -> false;
    val case2 = false -> true;
    val case3 = true -> false;
    val case4 = true -> true;

    F::println("Truth Table:");
    F::println("  F -> F = " .. case1);
    F::println("  F -> T = " .. case2);
    F::println("  T -> F = " .. case3);
    F::println("  T -> T = " .. case4);

    F::println();

    F::println("Short Circuit:");
    My::sc(false, false);
    My::sc(false, true);
    My::sc(true, false);
    My::sc(true, true);
}

defun sc(left : boolean, right : boolean) : void
{
    F::print("  ");
    My::operand1(left) -> My::operand2(left);
    F::println();
}

defun operand1(value : boolean) : boolean
{
    F::print("L");
    return value;
}

defun operand2(value : boolean) : boolean
{
    F::print("R");
    return value;
}
```

**Output:**

```plain
Truth Table:
  F -> F = true
  F -> T = true
  T -> F = false
  T -> T = true

Short Circuit:
  L
  L
  LR
  LR
```

