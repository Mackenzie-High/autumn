# Element List

## Summary

An element-list is a list of elements in a struct, design, or tuple.

## Syntax

<div class="syntax">
( <a class="synvar" href="Element.md">element</a><sub>1</sub></i> , ... , <a class="synvar" href="Element.md">element</a><sub>n</sub></i> )<br>
</div>

## AST Class

autumn.lang.compiler.ast.nodes.ElementList

## Details


## Static Checks


## Example

**Code:**

```plain
module Main in execution;


/// A tuple definition contains an element-list construct.
///
tuple Triple (element1 : Object, 
              element2 : Object,
              element3 : Object);
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

