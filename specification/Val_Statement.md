# Val Statement

## Summary

A val-statement declares a new readonly local variable.

## Syntax

<div class="syntax">
<b>val</b> <i><a href="Variable.md">assignee</a></i> = <i><a href="Expression.md">value</a></i> ;<br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.ValStatement](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/ValStatement.html)

## Details

+ The value stored in the variable will be reassigned, if the val-statement is executed again.
+ The scope of the <i>assignee</i> is anywhere in the enclosing function.
+ The <i>assignee</i> is alive precisely during an activation of the enclosing function.
+ The type of the newly declared variable is the type of the <i>value</i>.
  + In other words, the type of the variable is inferred.

## Static Checks

+ [DUPLICATE_VARIABLE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#DUPLICATE_VARIABLE): The <i>assignee</i> cannot share its name with another variable declared in the same scope.
+ [EXPECTED_VARIABLE_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#EXPECTED_VARIABLE_TYPE): The type of the <i>value</i> must be a variable-type.

## Example 1

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    # Create a boolean variable.
    val a = true;

    # Create a char variable.
    val b = 'A';

    # Create a byte variable.
    val c = 11B;

    # Create a short variable.
    val d = 13S;

    # Create a int variable.
    val e = 17;

    # Create a long variable.
    val f = 19L;

    # Create a float variable.
    val g = 23.0F;

    # Create a double variable.
    val h = 27.0;

    # Create a string variable.
    val i = "Venus";

    # Create a list variable.
    val j = ["Jupiter", "Saturn", "Uranus", "Neptune"];

    # Create another int variable.
    val x = e + 3;

    # Create yet another int variable.
    val y = x + e;

    # Create a list containing the values of the previous variables.
    val values = [a, b, c, d, e, f, g, i, j, x, y];

    foreach (value : Object in values)
    {
        F::print("Value = ");
        F::println(value);
    }
}
```

**Output:**

```plain
Value = true
Value = A
Value = 11
Value = 13
Value = 17
Value = 19
Value = 23.0
Value = Venus
Value = [Jupiter, Saturn, Uranus, Neptune]
Value = 20
Value = 37
```

## Example 2

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    # Variable square can only be assigned to at its declaration site.
    # In other words, variable square is readonly. 
    # However, the declaration site may be executed multiple times. 
    # The value of the variable may be updated each time. 

    for (i = 1; i < 5; i + 1)
    {
        val square = i * i;

        F::println (square);
    }
}
```

**Output:**

```plain
1
4
9
16
```

