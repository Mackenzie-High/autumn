# Float Datum

## Summary

A float-datum is a literal float value.

## Syntax

<div class="syntax">
digits</i>**.**digits</i><b class='keyword'>F**<br>
<hr class=&#92%22syntax-hr&#92%22><br>
**-**digits</i>**.**digits</i><b class='keyword'>F**<br>
<hr class=&#92%22syntax-hr&#92%22><br>
digits</i>**.**digits</i><b class='keyword'>e**digits</i><b class='keyword'>F**<br>
<hr class=&#92%22syntax-hr&#92%22><br>
**-**digits</i>**.**digits</i><b class='keyword'>e**digits</i><b class='keyword'>F**<br>
<hr class=&#92%22syntax-hr&#92%22><br>
digits</i>**.**digits</i><b class='keyword'>E**digits</i><b class='keyword'>F**<br>
<hr class=&#92%22syntax-hr&#92%22><br>
**-**digits</i>**.**digits</i><b class='keyword'>E**digits</i><b class='keyword'>F**<br>
</div>

## AST Class

autumn.lang.compiler.ast.nodes.FloatDatum

## Details

+ Return Type: float
+ Return the value of the constant.
+ Related Boxed Type: java.lang.Float

## Static Checks


## Example

**Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    val value = 23.0F + -5.0F + 1.0e2F + 2.0E3F;

    F::println(value);
}
```

**Output:**

```plain
2118.0
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

