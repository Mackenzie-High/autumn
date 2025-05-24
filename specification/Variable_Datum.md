# Variable Datum

## Summary

A variable-datum retrieves the values stored in a variable.

## Syntax

<div class="syntax">
<i><a href="Variable.md">variable</a></i><br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.VariableDatum](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/VariableDatum.html)

## Details

+ A variable will return its default-value, if it is accessed before its first assignment.
+ Return Type: (type of the referenced variable per the variable's declaration)
+ Return the value stored in the referenced variable.

## Static Checks

+ [NO_SUCH_VARIABLE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#NO_SUCH_VARIABLE): The <i>variable</i> must be declared somewhere within the enclosing scope.
+ [VARIABLE_OUTSIDE_OF_SCOPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#VARIABLE_OUTSIDE_OF_SCOPE): The <i>variable</i> cannot be used outside of the scope in which it was declared.

## Example 1

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    var planet1 = "Mercury";
    var planet2 = "Venus";
    var planet3 = "Earth";
    var planet4 = "Mars";

    val planets = [planet1, planet2, planet3, planet4];

    F::println(planets);
}
```

**Output:**

```plain
[Mercury, Venus, Earth, Mars]
```

## Example 2

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{

    goto ENTER;


    # Create a nested scope.
    {
        val a = true;
        val b = 'X';
        val c = 10B;
        val d = 20S;
        val e = 30;
        val f = 40L;
        val g = 50.0F;
        val h = 60.0;
        val i = "Text";

        marker ENTER;

        F::printlns([a, (b is int), c, d, e, f, g, h, i]);
    }
}
```

**Output:**

```plain
false
0
0
0
0
0
0.0
0.0
null
```

