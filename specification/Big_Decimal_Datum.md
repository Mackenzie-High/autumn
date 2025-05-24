# Big Decimal Datum

## Summary

A big-decimal datum is a literal $BigDecimal$ value.

## Syntax

<div class="syntax">
digits</i>**.**digits</i><br>
<hr class=&#92%22syntax-hr&#92%22><br>
**-**digits</i>**.**digits</i><b class='keyword'>BD**<br>
<hr class=&#92%22syntax-hr&#92%22><br>
digits</i>**.**digits</i><b class='keyword'>e**digits</i><b class='keyword'>BD**<br>
</div>

## AST Class

autumn.lang.compiler.ast.nodes.BigDecimalDatum

## Details

+ Return Type: [BigInteger](https://docs.oracle.com/javase/7/docs/api/java/math/BigInteger.html)
+ Return the value of the constant.

## Static Checks


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

