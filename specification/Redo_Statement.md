# Redo Statement

## Summary

A redo-statement causes execution to immediately restart the current iteration of the nearest enclosing loop.

## Syntax

<div class="syntax">
<span class="keyword">redo</span> ;<br>
</div>

## AST Class

autumn.lang.compiler.ast.nodes.RedoStatement

## Details

+ A redo-statement cannot be used to exit an invocation.
+ Types of Loops:
  + <a class="synvar" href="Forever_Statement.md">Forever Statement</a>s
  + <a class="synvar" href="While_Statement.md">While Statement</a>s
  + <a class="synvar" href="Until_Statement.md">Until Statement</a>s
  + <a class="synvar" href="Do_While_Statement.md">Do-While Statement</a>s
  + <a class="synvar" href="Do_Until_Statement.md">Do-Until Statement</a>s
  + <a class="synvar" href="For_Statement.md">For Statement</a>s
  + <a class="synvar" href="Foreach_Statement.md">Foreach Statement</a>s

## Static Checks

[REDO_OUTSIDE_OF_LOOP, A redo-statement must be in the <i>body</i> of a loop., null]

## Example

**Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    var i = 1;

    while (My::condition(i))
    {
        F::println("i = " .. i);
        F::println();

        i = i + 1;

        when (i == 2) then redo;
    }
}

defun condition(x : int) : boolean
{
    F::println("x = " .. x);

    return x < 4;
}
```

**Output:**

```plain
x = 1
i = 1

i = 2

x = 3
i = 3

x = 4
```

<style>
    .syntax
    {
        font-family: monospace, monospace;
    }

    .keyword
    {
        color: blue;
        font-weight: bold;
    }

    .synvar
    {
        font-style: italic;
    }
</style>

