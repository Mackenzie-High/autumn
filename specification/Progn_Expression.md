# Progn Expression

## Summary

A progn-expression executes a series of expressions sequentially.

## Syntax

<div class="syntax">
<b>progn</b> ( <i><a href="Expression.md">argument</a><sub>1</sub></i> , ... , <i><a href="Expression.md">argument</a><sub>n</sub></i> )<br>
</div>

## AST Class

autumn.lang.compiler.ast.nodes.PrognExpression

## Details

+ Return Type: type of <i><a href="Expression.md">argument</a><sub>n</sub></i>
+ Return the value produced by the last argument.

## Static Checks

[EMPTY_PROGN, There must be at least one element in the sequence., null]
[VALUE_REQUIRED, The type of each <i>argument</i> must be either a primitive-type or a reference-type., null]

## Example

**Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    val value = progn (F::println("X"), 
                       F::println("Y"), 
                       F::println("Z"),
                       1010);

    F::println("value = " .. value);
}
```

**Output:**

```plain
X
Y
Z
value = 1010
```

