# Char Datum

## Summary

A char-datum is a literal character.

## Syntax

<div class="syntax">
**'**<i>character</i>**'**<br>
<hr><br>
<i>digits</i><b class='keyword'>C**<br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.CharDatum](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/CharDatum.html)

## Details

+ Return Type: char
+ Return the value of the constant.
+ Minimum Value = 0
+ Maximum Value = 65535
+ Related Boxed Type: java.lang.Character

## Static Checks

+ [INACCURATE_NUMERIC_LITERAL](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#INACCURATE_NUMERIC_LITERAL): The value must be inclusively between the minimum and maximum.

## Example

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    val value = 'A' .. 'u' .. 116C .. 117C .. 'm' .. 'n';

    F::println(value);
}
```

**Output:**

```plain
Autumn
```

