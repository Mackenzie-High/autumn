# Float Datum

## Summary

A float-datum is a literal float value.

## Syntax

```plain
<i>digits</i>**.**<i>digits</i><b class='keyword'>F**
<hr class=&#92%22syntax-hr&#92%22>
**-**<i>digits</i>**.**<i>digits</i><b class='keyword'>F**
<hr class=&#92%22syntax-hr&#92%22>
<i>digits</i>**.**<i>digits</i><b class='keyword'>e**<i>digits</i><b class='keyword'>F**
<hr class=&#92%22syntax-hr&#92%22>
**-**<i>digits</i>**.**<i>digits</i><b class='keyword'>e**<i>digits</i><b class='keyword'>F**
<hr class=&#92%22syntax-hr&#92%22>
<i>digits</i>**.**<i>digits</i><b class='keyword'>E**<i>digits</i><b class='keyword'>F**
<hr class=&#92%22syntax-hr&#92%22>
**-**<i>digits</i>**.**<i>digits</i><b class='keyword'>E**<i>digits</i><b class='keyword'>F**
```

## AST Class

autumn.lang.compiler.ast.nodes.FloatDatum

## Details
+ Return Type: float
+ Return the value of the constant.
+ Related Boxed Type: java.lang.Float

