# Short Datum

## Summary

A short-datum is a literal short value.

## Syntax

<div class="syntax">
digits</i><b class='keyword'>S**<br>
<hr class=&#92%22syntax-hr&#92%22><br>
**-**digits</i><b class='keyword'>S**<br>
</div>

## AST Class

autumn.lang.compiler.ast.nodes.ShortDatum

## Details

+ Return Type: short
+ Return the value of the constant.
+ Minimum Value = -32768
+ Maximum Value = 32767
+ Related Boxed Type: java.lang.Short

## Static Checks

[INACCURATE_NUMERIC_LITERAL, The value must be inclusively between the minimum and maximum., null]

## Example

**Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    val value = 23S + -5S + 1S;

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

