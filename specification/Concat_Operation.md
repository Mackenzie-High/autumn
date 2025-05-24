# Concat Operation

## Summary

This operator performs a string concatenation operation.

## Syntax

<div id="syntax">
<i>[left](TextPage.html?page=Expression)</i> .. <i>[right](TextPage.html?page=Expression)</i><br>
</div>

## AST Class

autumn.lang.compiler.ast.nodes.ConcatOperation

## Details

+ Precedence: 5
+ Associativity: Left
+ Predefined Overload:
  + ([Object](https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html) .. [Object](https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html)) &#8614; String
+ The operands will be boxed when necessary.
+ Both operands are greedily evaluated.
  + The left-operand is evaluated first.
  + The right-operand is evaluated second.
+ Return Type: [String](https://docs.oracle.com/javase/7/docs/api/java/lang/String.html)
+ Return the string representation of the left-operand prepended onto the string-representation of the right-operand..

## Static Checks

[NO_SUCH_BINARY_OPERATOR, None of the overloads will accept the left-operand due to its type., null]
[NO_SUCH_BINARY_OPERATOR, None of the overloads will accept the right-operand due to its type., null]

## Example

**Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    val output = "X " .. 2 .. " Y " .. 3 .. " Z";

    F::println(output);
}
```

**Output:**

```plain
X 2 Y 3 Z
```

