# Goto Statement

## Summary

A goto-statement causes execution to immediately jump to a labeled location.

## Syntax

<div class="syntax">
<b>goto</b> <i><a href="Label.md">label</a></i> ;<br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.GotoStatement](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/GotoStatement.html)

## Details

+ A goto-statement cannot be used to jump out of a function.

## Static Checks

+ [NO_SUCH_LABEL](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#NO_SUCH_LABEL): The <i>label</i> must be declared somewhere in the enclosing function.

## Example 1

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    F::println("Virginia");

    goto WEST;

    F::println("Kansas");

    marker WEST;

    F::println("California");
}
```

**Output:**

```plain
Virginia
California
```

## Example 2

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    for (x = 0; x < 3; x + 1)
    {
        for (y = 0; y < 3; y + 1)
        {
            for (z = 0; z < 3; z + 1)
            {
                F::println([x, y, z]);

                when (x == 3 & y == 2 & z == 1) then goto END;
            }
        }
    }

    marker END;
}
```

**Output:**

```plain
[0, 0, 0]
[0, 0, 1]
[0, 0, 2]
[0, 1, 0]
[0, 1, 1]
[0, 1, 2]
[0, 2, 0]
[0, 2, 1]
[0, 2, 2]
[1, 0, 0]
[1, 0, 1]
[1, 0, 2]
[1, 1, 0]
[1, 1, 1]
[1, 1, 2]
[1, 2, 0]
[1, 2, 1]
[1, 2, 2]
[2, 0, 0]
[2, 0, 1]
[2, 0, 2]
[2, 1, 0]
[2, 1, 1]
[2, 1, 2]
[2, 2, 0]
[2, 2, 1]
[2, 2, 2]
```

## Example 3

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    F::print("This");

    goto IS;

    marker DONOT;

    F::print("do not");

    goto DO;

    marker PASTA;

    F::print("spaghetti");

    goto CODE;

    marker IS;

    F::print(" is ");

    goto PASTA;

    marker DO;

    F::print(" do ");

    goto THIS;

    marker CODE;

    F::print(" code ");

    goto DONOT;

    marker THIS;

    F::print("this!!!");
}
```

**Output:**

```plain
This is spaghetti code do not do this!!!
```

