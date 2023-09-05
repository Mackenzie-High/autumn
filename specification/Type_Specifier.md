# Type Specifier

## Summary

A type-specifier specifies a type by its name and dimensions.

## AST Class

[autumn.lang.compiler.ast.nodes.TypeSpecifier](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/TypeSpecifier.html)

## Details

+ If the number of dimensions is non-zero, then an array-type is specified.
+ One cannot specify the null-type using a type-specifier.

## Example

**Source Code:**

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

