# Nop Statement

## Summary

A nop-statement does not perform any operation.

## Syntax

<div id="syntax">
<span class=\"keyword\">nop</span> ;<br>
</div>

## AST Class

autumn.lang.compiler.ast.nodes.NopStatement

## Details

+ A nop-statement should generate a nop bytecode instruction.
+ An optimizer is free to remove nop bytecode instructions.

## Static Checks


## Example

**Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    F::println("Before NOP");

    nop;

    F::println("After NOP");
}
```

**Output:**

```plain
Before NOP
After NOP
```

