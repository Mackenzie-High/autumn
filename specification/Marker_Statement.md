# Marker Statement

## Summary

A marker-statement declares a label in the enclosing function.

## Syntax

<div id="syntax">
<span class=\"keyword\">marker</span> <i>[label](ConstructPage.html?construct=Label)</i> ;<br>
</div>

## AST Class

autumn.lang.compiler.ast.nodes.MarkerStatement

## Details

+ The <i>label</i> will be visible everywhere in the enclosing function.
+ Labels and variables are in distinct namespaces.
  + In other words, a variable can have the same name as a label.

## Static Checks

[DUPLICATE_LABEL, No two labels in the same function can share a name., null]

## Example

**Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    F::println("Virginia");

    goto WEST;

    F::println("Kansas");

    marker WEST;

    F::println("California");
}
```

**Output:**

```plain
Virginia
California
```

