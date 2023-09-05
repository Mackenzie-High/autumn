# Null Coalescing Operation

## Summary

A null-coalescing provides an alternate value when a primary value is null.

## Syntax

<div class="syntax">
<i><a href="Expression.md">left</a></i> ?? <i><a href="Expression.md">right</a></i><br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.NullCoalescingOperation](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/NullCoalescingOperation.html)

## Details

+ Precedence: 8
+ Associativity: Left
+ The left-operand is always evaluated.
+ The right-operand is only evaluated when the left-operand produces null.
+ Return Type: widest(typeof(<i>left</i>), typeof(<i>right</i>))
+ Return the left-operand, if the value is not null; otherwise, the right-operand.

## Static Checks

+ [EXPECTED_REFERENCE_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#EXPECTED_REFERENCE_TYPE): The type of the left-operand must be a reference-type.
+ [EXPECTED_REFERENCE_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#EXPECTED_REFERENCE_TYPE): The type of the right-operand must be a reference-type.
+ [INCOMPATIBLE_OPERANDS](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#INCOMPATIBLE_OPERANDS): The type of one of the operands must be a subtype of the other.

## Example 1

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    val value1 = "Mercury";
    val value2 = "Venus";
    val value3 = "Earth";

    # Case: The left operand is non-null.
    val value4 = (value1 ?? value2) is String;

    # Case: The left-operand is null.
    val value5 = (null ?? value3) is String;

    # Case: Both operands are null.
    val value6 = (null ?? null) is String;

    # Print the results.
    F::println(value4);
    F::println(value5);
    F::println(value6);
}
```

**Output:**

```plain
Mercury
Earth
null
```

## Example 2

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    val value1 = null is Object;
    val value2 = "Not Null"; 

    F::print("Case 1: ");
    My::operand1(value1) ?? My::operand2(value1);
    F::println();

    F::print("Case 2: ");
    My::operand1(value1) ?? My::operand2(value2);
    F::println();

    F::print("Case 3: ");
    My::operand1(value2) ?? My::operand2(value1);
    F::println();

    F::print("Case 4: ");
    My::operand1(value2) ?? My::operand2(value2);
    F::println();
}

defun operand1(value : Object) : Object
{
    F::print("L");
    return value;
}

defun operand2(value : Object) : Object
{
    F::print("R");
    return value;
}
```

**Output:**

```plain
Case 1: LR
Case 2: LR
Case 3: L
Case 4: L
```

