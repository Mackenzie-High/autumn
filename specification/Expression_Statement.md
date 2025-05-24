# Expression Statement

## Summary

An expression-statement facilitates the use of an expression as a statement.

## Syntax

<div class="syntax">
<i><a href="Expression.md">expression</a></i> ;<br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.ExpressionStatement](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/ExpressionStatement.html)

## Details

+ The return-value, if any, of the expression is simply ignored.

## Example

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    # The following line is an expression used as a statement.
    My::printNumber(17);
}

defun printNumber (x : int) : void
{
    F::println("x = " .. x);
}
```

**Output:**

```plain
x = 17
```

