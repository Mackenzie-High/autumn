# Element

## Summary

An element declares an entry in a struct, design, or tuple.

## Syntax

<div id="syntax">
<i>[name](ConstructPage.html?construct=Name)</i> : <i>[type](ConstructPage.html?construct=TypeSpecifier)</i><br>
</div>

## AST Class

autumn.lang.compiler.ast.nodes.Element

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

