# Nop Statement

## Summary

A nop-statement does not perform any operation.

## Syntax

<div class="syntax">
<b>nop</b> ;<br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.NopStatement](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/NopStatement.html)

## Details

+ A nop-statement should generate a nop bytecode instruction.
+ An optimizer is free to remove nop bytecode instructions.

## Example

**Source Code:**

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

