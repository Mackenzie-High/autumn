# Redo Statement

## Summary

A redo-statement causes execution to immediately restart the current iteration of the nearest enclosing loop.

## Syntax

<div id="syntax">
<span class=\"keyword\">redo</span> ;<br>
</div>

## AST Class

autumn.lang.compiler.ast.nodes.RedoStatement

## Details

+ A redo-statement cannot be used to exit an invocation.
+ Types of Loops:
  + [Forever Statement](ConstructPage.html?construct=Forever Statement)s
  + [While Statement](ConstructPage.html?construct=While Statement)s
  + [Until Statement](ConstructPage.html?construct=Until Statement)s
  + [Do-While Statement](ConstructPage.html?construct=Do-While Statement)s
  + [Do-Until Statement](ConstructPage.html?construct=Do-Until Statement)s
  + [For Statement](ConstructPage.html?construct=For Statement)s
  + [Foreach Statement](ConstructPage.html?construct=Foreach Statement)s

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

