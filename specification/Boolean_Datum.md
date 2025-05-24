# Boolean Datum

## Summary

A boolean-datum is a literal boolean value.

## Syntax

<div class="syntax">
<span class="keyword">true</span><br>
<hr class=&#92%22syntax-hr&#92%22><br>
<span class="keyword">false</span><br>
</div>

## AST Class

autumn.lang.compiler.ast.nodes.BooleanDatum

## Details

+ Return Type: boolean
+ Return the value of the constant.
+ Related Boxed Type: java.lang.Boolean

## Static Checks


## Example

**Code:**

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

