# Byte Datum

## Summary

A byte-datum is a literal byte value.

## Syntax

<div class="syntax">
<i>digits</i><b class='keyword'>B**<br>
<hr><br>
**-**<i>digits</i><b class='keyword'>B**<br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.ByteDatum](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/ByteDatum.html)

## Details

+ Return Type: byte
+ Return the value of the constant.
+ Minimum Value = -128
+ Maximum Value = 127
+ Related Boxed Type: java.lang.Byte

## Static Checks

+ [INACCURATE_NUMERIC_LITERAL](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#INACCURATE_NUMERIC_LITERAL): The value must be inclusively between the minimum and maximum.

## Example

**Source Code:**

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

