# Forever Statement

## Summary

A forever-statement is an infinite loop.

## Syntax

<div class="syntax">
<b>forever</b><br>
{<br>
&nbsp;&nbsp;&nbsp;&nbsp;<i><a href="Statement.md">body</a></i><br>
}<br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.ForeverStatement](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/ForeverStatement.html)

## Details

+ The <i>body</i> of a loop can contain break-statements.
+ The <i>body</i> of a loop can contain continue-statements.
+ The <i>body</i> of a loop can contain redo-statements.

## Example

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    var i = 0;

    var very = "";

    forever 
    {
        F::println("Forever is a" .. very .. " long time.");

        very = very .. " very";

        i = i + 1;

        when (i == 8) then break;
    }
}
```

**Output:**

```plain
Forever is a long time.
Forever is a very long time.
Forever is a very very long time.
Forever is a very very very long time.
Forever is a very very very very long time.
Forever is a very very very very very long time.
Forever is a very very very very very very long time.
Forever is a very very very very very very very long time.
```

