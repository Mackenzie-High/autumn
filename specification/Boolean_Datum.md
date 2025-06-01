# Boolean Datum

## Summary

A boolean-datum is a literal boolean value.

## Syntax

<div class="syntax">
<b>true</b><br>
<hr><br>
<b>false</b><br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.BooleanDatum](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/BooleanDatum.html)

## Details

+ Return Type: boolean
+ Return the value of the constant.
+ Related Boxed Type: java.lang.Boolean

## Example

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    F::println(true);
    F::println(false);
}
```

**Output:**

```plain
true
false
```

