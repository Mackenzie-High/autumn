# Sequence Statement

## Summary

A sequence-statement executes a series of statements one after another.

## Syntax

<div class="syntax">
{<br>
&nbsp;&nbsp;&nbsp;&nbsp;<i><a href="Statement.md">statement</a><sub>1</sub></i><br>
&nbsp;&nbsp;&nbsp;&nbsp;<i><a href="Statement.md">statement</a><sub>2</sub></i><br>
&nbsp;&nbsp;&nbsp;&nbsp;<i><a href="Statement.md">statement</a><sub>n</sub></i><br>
}<br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.SequenceStatement](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/SequenceStatement.html)

## Details

+ There can be zero or more statements in the sequence.

## Example

**Source Code:**

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

