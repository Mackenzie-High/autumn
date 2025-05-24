# Double Datum

## Summary

A double-datum is a literal double value.

## Syntax

<div class="syntax">
<i>digits</i>**.**<i>digits</i><br>
<hr><br>
**-**<i>digits</i>**.**<i>digits</i><br>
<hr><br>
<i>digits</i>**.**<i>digits</i><b class='keyword'>e**<i>digits</i><br>
<hr><br>
**-**<i>digits</i>**.**<i>digits</i><b class='keyword'>e**<i>digits</i><br>
<hr><br>
<i>digits</i>**.**<i>digits</i><b class='keyword'>E**<i>digits</i><br>
<hr><br>
**-**<i>digits</i>**.**<i>digits</i><b class='keyword'>E**<i>digits</i><br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.DoubleDatum](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/DoubleDatum.html)

## Details

+ Return Type: double
+ Return the value of the constant.
+ Related Boxed Type: java.lang.Double

## Example

**Source Code:**

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

