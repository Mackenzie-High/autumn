# Short Datum

## Summary

A short-datum is a literal short value.

## Syntax

<div class="syntax">
<i>digits</i><b class='keyword'>S**<br>
<hr><br>
**-**<i>digits</i><b class='keyword'>S**<br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.ShortDatum](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/ShortDatum.html)

## Details

+ Return Type: short
+ Return the value of the constant.
+ Minimum Value = -32768
+ Maximum Value = 32767
+ Related Boxed Type: java.lang.Short

## Static Checks

+ [INACCURATE_NUMERIC_LITERAL](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#INACCURATE_NUMERIC_LITERAL): The value must be inclusively between the minimum and maximum.

## Example

**Source Code:**

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

