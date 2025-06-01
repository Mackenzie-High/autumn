# Break Statement

## Summary

A break-statement causes execution to immediately exit the nearest enclosing loop.

## Syntax

<div class="syntax">
<b>break</b> ;<br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.BreakStatement](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/BreakStatement.html)

## Details

+ A break-statement cannot be used to exit an invocation.
+ Types of Loops:
  + <a href="Forever_Statement.md">Forever Statement</a>s
  + <a href="While_Statement.md">While Statement</a>s
  + <a href="Until_Statement.md">Until Statement</a>s
  + <a href="Do_While_Statement.md">Do-While Statement</a>s
  + <a href="Do_Until_Statement.md">Do-Until Statement</a>s
  + <a href="For_Statement.md">For Statement</a>s
  + <a href="Foreach_Statement.md">Foreach Statement</a>s

## Static Checks

+ [BREAK_OUTSIDE_OF_LOOP](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#BREAK_OUTSIDE_OF_LOOP): A break-statement must be in the <i>body</i> of a loop.

## Example

**Source Code:**

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

