# Long Datum

## Summary

A long-datum is a literal long value.

## Syntax

<div class="syntax">
<i>digits</i><b class='keyword'>L**<br>
<hr><br>
**-**<i>digits</i><b class='keyword'>L**<br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.LongDatum](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/LongDatum.html)

## Details

+ Return Type: long
+ Return the value of the constant.
+ Minimum Value = -9223372036854775808
+ Maximum Value = 9223372036854775807
+ Related Boxed Type: java.lang.Long

## Static Checks

+ [INACCURATE_NUMERIC_LITERAL](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#INACCURATE_NUMERIC_LITERAL): The value must be inclusively between the minimum and maximum.

## Example

**Source Code:**

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

