# For Statement

## Summary

A for-statement is a loop that iterates based on a control variable and a condition.

## Syntax

<div class="syntax">
<b>for</b> ( <i><a href="Variable.md">assignee</a></i> = <i><a href="Expression.md">initializer</a></i> ; <i><a href="Expression.md">condition</a></i> ; <i><a href="Expression.md">modifier</a></i> )<br>
{<br>
&nbsp;&nbsp;&nbsp;&nbsp;<i><a href="Statement.md">body</a></i><br>
}<br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.ForStatement](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/ForStatement.html)

## Details

+ The scope of the <i>assignee</i> is anywhere in the enclosing function.
+ The <i>assignee</i> is alive precisely during an activation of the enclosing function.
+ The type of the <i>assignee</i> variable will be primitive-type int.
+ The <i>assignee</i> is a readonly variable.
+ The <i>initializer</i> will be unboxed, if necessary.
+ The <i>condition</i> will be unboxed, if necessary.
+ The <i>modifier</i> will be unboxed, if necessary.
+ The <i>body</i> of a loop can contain break-statements.
+ The <i>body</i> of a loop can contain continue-statements.
+ The <i>body</i> of a loop can contain redo-statements.

## Static Checks

+ [DUPLICATE_VARIABLE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#DUPLICATE_VARIABLE): The <i>assignee</i> cannot share its name with another variable declared in the same scope.
+ [EXPECTED_INTEGER](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#EXPECTED_INTEGER): The type of <i>initializer</i> must be assignable to type int.
+ [EXPECTED_CONDITION](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#EXPECTED_CONDITION): The type of <i><i>condition</i></i> must be assignable to type boolean.
+ [EXPECTED_INTEGER](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#EXPECTED_INTEGER): The type of <i>modifier</i> must be assignable to type int.

## Example

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    for (i = 0; i < 5; i + 1)
    {
        F::print("X = ");
        F::println(i);
    }
}
```

**Output:**

```plain
X = 0
X = 1
X = 2
X = 3
X = 4
```

