# Long Datum

## Summary

A long-datum is a literal long value.

## Syntax

<div class="syntax">
digits</i><b class='keyword'>L**<br>
<hr class=&#92%22syntax-hr&#92%22><br>
**-**digits</i><b class='keyword'>L**<br>
</div>

## AST Class

autumn.lang.compiler.ast.nodes.LongDatum

## Details

+ Return Type: long
+ Return the value of the constant.
+ Minimum Value = -9223372036854775808
+ Maximum Value = 9223372036854775807
+ Related Boxed Type: java.lang.Long

## Static Checks

[INACCURATE_NUMERIC_LITERAL, The value must be inclusively between the minimum and maximum., null]

## Example

**Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    val value = 23L + -5L + 1L;

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

