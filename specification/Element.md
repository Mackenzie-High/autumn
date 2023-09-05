# Element

## Summary

An element declares an entry in a struct, design, or tuple.

## Syntax

<div class="syntax">
<i><a href="Name.md">name</a></i> : <i><a href="Type_Specifier.md">type</a></i><br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.Element](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/Element.html)

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

