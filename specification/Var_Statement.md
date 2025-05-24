# Var Statement

## Summary

A var-statement declares a new mutable local variable.

## Syntax

<div class="syntax">
<b>var</b> <i><a href="Variable.md">assignee</a></i> = <i><a href="Expression.md">value</a></i> ;<br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.VarStatement](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/VarStatement.html)

## Details

+ The scope of the <i>assignee</i> is anywhere in the enclosing function.
+ The <i>assignee</i> is alive precisely during an activation of the enclosing function.
+ The type of the newly declared variable is the type of the <i>value</i>.
  + In other words, the type of the variable is inferred.

## Static Checks

+ [DUPLICATE_VARIABLE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#DUPLICATE_VARIABLE): The <i>assignee</i> cannot share its name with another variable declared in the same scope.
+ [EXPECTED_VARIABLE_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#EXPECTED_VARIABLE_TYPE): The type of the <i>value</i> must be a variable-type.

## Example

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    # Create a boolean variable.
    var a = true;

    # Create a char variable.
    var b = 'A';

    # Create a byte variable.
    var c = 11B;

    # Create a short variable.
    var d = 13S;

    # Create a int variable.
    var e = 17;

    # Create a long variable.
    var f = 19L;

    # Create a float variable.
    var g = 23.0F;

    # Create a double variable.
    var h = 27.0;

    # Create a string variable.
    var i = "Venus";

    # Create a list variable.
    var j = ["Jupiter", "Saturn", "Uranus", "Neptune"];

    # Create another int variable.
    var x = e + 3;

    # Create yet another int variable.
    var y = x + e;

    # Create a list containing the values of the previous variables.
    var values = [a, b, c, d, e, f, g, i, j, x, y];

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

