# Type Specifier

## Summary

A type-specifier specifies a type by its name and dimensions.

## Syntax

<div class="syntax">
<br>
</div>

## AST Class

autumn.lang.compiler.ast.nodes.TypeSpecifier

## Details

+ If the number of dimensions is non-zero, then an array-type is specified.
+ One cannot specify the null-type using a type-specifier.

## Static Checks


## Example

**Code:**

```plain
module Main in execution;


@Start
defun main (args : String[]) : void
{
    # Primitive Type
    # Type Specifier: int
    val type1 = class int;

    # Array Type: 1-Dimensional
    # Type Specifier: int[]
    val type2 = class int[];

    # Array Type: 2-Dimensional
    # Type Specifier: int[][]
    val type3 = class int;

    # Array Type: 3-Dimensional
    # Type Specifier: int[][][]
    val type4 = class int[][][];

    # Reference Type
    # Type Specifier: String
    val type5 = class String;

    # Fully Qualified
    val type6 = class java.lang.String;
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

