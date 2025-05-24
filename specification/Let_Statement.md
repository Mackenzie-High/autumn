# Let Statement

## Summary

A let-statement assigns a value to a local variable.

## Syntax

<div id="syntax">
<span class=\"keyword\">let</span> <i>[assignee](ConstructPage.html?construct=Variable)</i> = <i>[value](TextPage.html?page=Expression)</i> ;<br>
<hr class=&#92%22syntax-hr&#92%22><br>
<i>[assignee](ConstructPage.html?construct=Variable)</i> = <i>[value](TextPage.html?page=Expression)</i> ;<br>
</div>

## AST Class

autumn.lang.compiler.ast.nodes.LetStatement

## Details

+ The <i>value</i> will be boxed, if necessary.
+ The <i>value</i> will be unboxed, if necessary.
+ The <i>value</i> will be coerced, if necessary.

## Static Checks

[NO_SUCH_VARIABLE, The <i>assignee</i> must be declared somewhere in the enclosing function., null]
[MUTABLE_VARIABLE_REQUIRED, The <i>assignee</i> must be a mutable variable., null]
[IMPOSSIBLE_ASSIGNMENT, The type of the <i>value</i> must be assignable to the type of the <i>assignee</i>., null]

## Example

**Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    # Declare a new variable.
    var planet = "Venus";

    # Print the name of the planet.
    F::println(planet);

    # Change the value of the variable. 
    let planet = "Mars";

    # Print the name of the planet.
    F::println(planet);

    # Change the value of the variable. 
    planet = "Pluto";

    # Print the name of the planet.
    F::println(planet);
}
```

**Output:**

```plain
Venus
Mars
Pluto
```

