# Namespace

## Summary

A namespace construct is used to represent the name of a package.

## Syntax

<div class="syntax">
</i><br>
</div>

## AST Class

autumn.lang.compiler.ast.nodes.Namespace

## Details


## Static Checks


## Example

**Code:**

```plain
/// Namespace: execution.examples;
///
module Main in execution.examples;


@Start
defun main (args : String[]) : void
{
    nop;
}
```

**Output:**

```plain

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

