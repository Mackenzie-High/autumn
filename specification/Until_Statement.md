# Until Statement

## Summary

An until-statement is a loop that iterates until a condition becomes true.

## Syntax

<div class="syntax">
<b>until</b> ( <i><a href="Expression.md">condition</a></i> )<br>
{<br>
&nbsp;&nbsp;&nbsp;&nbsp;<i><a href="Statement.md">body</a></i><br>
}<br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.UntilStatement](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/UntilStatement.html)

## Details

+ The <i>condition</i> will be unboxed, if necessary.
+ The <i>body</i> of a loop can contain break-statements.
+ The <i>body</i> of a loop can contain continue-statements.
+ The <i>body</i> of a loop can contain redo-statements.

## Static Checks

+ [EXPECTED_CONDITION](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#EXPECTED_CONDITION): The type of <i><i>condition</i></i> must be assignable to type boolean.

## Example

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    var i = 0;

    until (i > 5)
    {
        F::println("i = " .. i);

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

