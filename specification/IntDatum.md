# Int Datum

## Summary

A int-datum is a literal int value.

## Syntax

<div id="syntax">
<i>digits</i><br>
<hr class=&#92%22syntax-hr&#92%22><br>
**-**<i>digits</i><br>
</div>

## AST Class

autumn.lang.compiler.ast.nodes.IntDatum

## Details

+ Return Type: int
+ Return the value of the constant.
+ Minimum Value = -2147483648
+ Maximum Value = 2147483647
+ Related Boxed Type: java.lang.Integer

## Static Checks

[INACCURATE_NUMERIC_LITERAL, The value must be inclusively between the minimum and maximum., null]

## Example

**Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    val value = 23 + -5 + 1;

    F::println(value);
}
```

**Output:**

```plain
19
```

