# Double Datum

## Summary

A double-datum is a literal double value.

## Syntax

<div class="syntax">
digits</i>**.**digits</i><br>
<hr class=&#92%22syntax-hr&#92%22><br>
**-**digits</i>**.**digits</i><br>
<hr class=&#92%22syntax-hr&#92%22><br>
digits</i>**.**digits</i><b class='keyword'>e**digits</i><br>
<hr class=&#92%22syntax-hr&#92%22><br>
**-**digits</i>**.**digits</i><b class='keyword'>e**digits</i><br>
<hr class=&#92%22syntax-hr&#92%22><br>
digits</i>**.**digits</i><b class='keyword'>E**digits</i><br>
<hr class=&#92%22syntax-hr&#92%22><br>
**-**digits</i>**.**digits</i><b class='keyword'>E**digits</i><br>
</div>

## AST Class

autumn.lang.compiler.ast.nodes.DoubleDatum

## Details

+ Return Type: double
+ Return the value of the constant.
+ Related Boxed Type: java.lang.Double

## Static Checks


## Example

**Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    val value = 23.0 + -5.0 + 1.0e2 + 2.0E3;

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

