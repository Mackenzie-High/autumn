# Let Statement

## Summary

A let-statement assigns a value to a local variable.

## Syntax

<div class="syntax">
<b>let</b> <i><a href="Variable.md">assignee</a></i> = <i><a href="Expression.md">value</a></i> ;<br>
<hr><br>
<i><a href="Variable.md">assignee</a></i> = <i><a href="Expression.md">value</a></i> ;<br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.LetStatement](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/LetStatement.html)

## Details

+ The <i>value</i> will be boxed, if necessary.
+ The <i>value</i> will be unboxed, if necessary.
+ The <i>value</i> will be coerced, if necessary.

## Static Checks

+ [NO_SUCH_VARIABLE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#NO_SUCH_VARIABLE): The <i>assignee</i> must be declared somewhere in the enclosing function.
+ [MUTABLE_VARIABLE_REQUIRED](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#MUTABLE_VARIABLE_REQUIRED): The <i>assignee</i> must be a mutable variable.
+ [IMPOSSIBLE_ASSIGNMENT](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#IMPOSSIBLE_ASSIGNMENT): The type of the <i>value</i> must be assignable to the type of the <i>assignee</i>.

## Example

**Source Code:**

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

