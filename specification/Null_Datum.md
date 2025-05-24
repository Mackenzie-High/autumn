# Null Datum

## Summary

A null-datum is the literal value null.

## Syntax

<div class="syntax">
<b>null</b><br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.NullDatum](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/NullDatum.html)

## Details

+ Return Type: null-type
+ Return the value of the constant.

## Example

**Source Code:**

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

