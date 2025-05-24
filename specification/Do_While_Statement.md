# Do-While Statement

## Summary

A do-while-statement is a loop that iterates while a postcondition holds true.

## Syntax

<div class="syntax">
<span class="keyword">do</span><br>
{<br>
&nbsp;&nbsp;&nbsp;&nbsp;<a href="Statement.md">body</a></i><br>
}<br>
<span class="keyword">while</span> ( <a href="Expression.md">condition</a></i> )<br>
</div>

## AST Class

autumn.lang.compiler.ast.nodes.DoWhileStatement

## Details

+ The <i>body</i> will be executed at least once.
+ The <i>condition</i> will be unboxed, if necessary.
+ The <i>body</i> of a loop can contain break-statements.
+ The <i>body</i> of a loop can contain continue-statements.
+ The <i>body</i> of a loop can contain redo-statements.

## Static Checks

[EXPECTED_CONDITION, The type of <i><i>condition</i></i> must be assignable to type boolean., null]

## Example

**Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    var i = 0;
    
    do
    {
        F::println("i = " .. i);

        i = i + 1;
    }
    while (i < 5)
}
```

**Output:**

```plain
i = 0
i = 1
i = 2
i = 3
i = 4
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

