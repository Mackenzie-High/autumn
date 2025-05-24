# Byte Datum

## Summary

A byte-datum is a literal byte value.

## Syntax

<div class="syntax">
digits</i><b class='keyword'>B**<br>
<hr class=&#92%22syntax-hr&#92%22><br>
**-**digits</i><b class='keyword'>B**<br>
</div>

## AST Class

autumn.lang.compiler.ast.nodes.ByteDatum

## Details

+ Return Type: byte
+ Return the value of the constant.
+ Minimum Value = -128
+ Maximum Value = 127
+ Related Boxed Type: java.lang.Byte

## Static Checks

[INACCURATE_NUMERIC_LITERAL, The value must be inclusively between the minimum and maximum., null]

## Example

**Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    val value = 23B + -5B + 1B;

    F::println(value);
}
```

**Output:**

```plain
19
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

