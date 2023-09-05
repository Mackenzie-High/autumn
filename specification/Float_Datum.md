# Float Datum

## Summary

A float-datum is a literal float value.

## Syntax

<div class="syntax">
<i>digits</i>**.**<i>digits</i><b class='keyword'>F**<br>
<hr><br>
**-**<i>digits</i>**.**<i>digits</i><b class='keyword'>F**<br>
<hr><br>
<i>digits</i>**.**<i>digits</i><b class='keyword'>e**<i>digits</i><b class='keyword'>F**<br>
<hr><br>
**-**<i>digits</i>**.**<i>digits</i><b class='keyword'>e**<i>digits</i><b class='keyword'>F**<br>
<hr><br>
<i>digits</i>**.**<i>digits</i><b class='keyword'>E**<i>digits</i><b class='keyword'>F**<br>
<hr><br>
**-**<i>digits</i>**.**<i>digits</i><b class='keyword'>E**<i>digits</i><b class='keyword'>F**<br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.FloatDatum](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/FloatDatum.html)

## Details

+ Return Type: float
+ Return the value of the constant.
+ Related Boxed Type: java.lang.Float

## Example

**Source Code:**

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

