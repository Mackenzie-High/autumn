# Break Statement

## Summary

A break-statement causes execution to immediately exit the nearest enclosing loop.

## Syntax

<div class="syntax">
<span class="keyword">break</span> ;<br>
</div>

## AST Class

autumn.lang.compiler.ast.nodes.BreakStatement

## Details

+ A break-statement cannot be used to exit an invocation.
+ Types of Loops:
  + <a class="synvar" href="Forever_Statement.md">Forever Statement</a>s
  + <a class="synvar" href="While_Statement.md">While Statement</a>s
  + <a class="synvar" href="Until_Statement.md">Until Statement</a>s
  + <a class="synvar" href="Do_While_Statement.md">Do-While Statement</a>s
  + <a class="synvar" href="Do_Until_Statement.md">Do-Until Statement</a>s
  + <a class="synvar" href="For_Statement.md">For Statement</a>s
  + <a class="synvar" href="Foreach_Statement.md">Foreach Statement</a>s

## Static Checks

[BREAK_OUTSIDE_OF_LOOP, A break-statement must be in the <i>body</i> of a loop., null]

## Example

**Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    var i = 0;

    while (i < 100)
    {
        F::println("i = " .. i);

        when (i == 5) then break;

        i = i + 1;
    }
}
```

**Output:**

```plain
i = 0
i = 1
i = 2
i = 3
i = 4
i = 5
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

