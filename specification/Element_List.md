# Element List

## Summary

An element-list is a list of elements in a struct, design, or tuple.

## Syntax

<div class="syntax">
( <i><a href="Element.md">element</a><sub>1</sub></i> , ... , <i><a href="Element.md">element</a><sub>n</sub></i> )<br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.ElementList](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/ElementList.html)

## Example

**Source Code:**

```plain
module Main in execution;


/// A tuple definition contains an element-list construct.
///
tuple Triple (element1 : Object, 
              element2 : Object,
              element3 : Object);
```

