# Nop Statement

## Summary

A nop-statement does not perform any operation.

## Syntax

```plain
<span class=\"keyword\">nop</span> ;
```

## AST Class

autumn.lang.compiler.ast.nodes.NopStatement

## Details
+ A nop-statement should generate a nop bytecode instruction.
+ An optimizer is free to remove nop bytecode instructions.

