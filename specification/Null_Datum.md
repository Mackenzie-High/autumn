# Null Datum

## Summary

A null-datum is the literal value null.

## Syntax

<div id="syntax">
<span class=\"keyword\">null</span><br>
</div>

## AST Class

autumn.lang.compiler.ast.nodes.NullDatum

## Details

+ Return Type: null-type
+ Return the value of the constant.

## Static Checks


## Example

**Code:**

```plain
module Main in examples;

tuple Pet (type : String, name : String);

@Start
defun main (args : String[]) : void
{
    var x = "nil is ";

    F::print(x);

    x = null;

    F::print(x);
}
```

**Output:**

```plain
nil is null
```

