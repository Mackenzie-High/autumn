# Sequence Statement

## Summary

A sequence-statement executes a series of statements one after another.

## Syntax

<div class="syntax">
{<br>
&nbsp;&nbsp;&nbsp;&nbsp;<a href="Statement.md">statement</a><sub>1</sub></i><br>
&nbsp;&nbsp;&nbsp;&nbsp;<a href="Statement.md">statement</a><sub>2</sub></i><br>
&nbsp;&nbsp;&nbsp;&nbsp;<a href="Statement.md">statement</a><sub>n</sub></i><br>
}<br>
</div>

## AST Class

autumn.lang.compiler.ast.nodes.SequenceStatement

## Details

+ There can be zero or more statements in the sequence.

## Static Checks


## Example

**Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    {
        F::println("A");
        F::println("B");
        F::println("C");
    }
}
```

**Output:**

```plain
A
B
C
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

