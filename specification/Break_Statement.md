# Break Statement

## Summary

A break-statement causes execution to immediately exit the nearest enclosing loop.

## Syntax

<div id="syntax">
<span class=\"keyword\">break</span> ;<br>
</div>

## AST Class

autumn.lang.compiler.ast.nodes.BreakStatement

## Details

+ A break-statement cannot be used to exit an invocation.
+ Types of Loops:
  + [Forever Statement](ConstructPage.html?construct=Forever Statement)s
  + [While Statement](ConstructPage.html?construct=While Statement)s
  + [Until Statement](ConstructPage.html?construct=Until Statement)s
  + [Do-While Statement](ConstructPage.html?construct=Do-While Statement)s
  + [Do-Until Statement](ConstructPage.html?construct=Do-Until Statement)s
  + [For Statement](ConstructPage.html?construct=For Statement)s
  + [Foreach Statement](ConstructPage.html?construct=Foreach Statement)s

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

