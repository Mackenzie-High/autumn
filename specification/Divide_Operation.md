# Divide Operation

## Summary

This operator performs an arithmetic division operation.

## Syntax

<div class="syntax">
<i><a href="Expression.md">left</a></i> / <i><a href="Expression.md">right</a></i><br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.DivideOperation](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/DivideOperation.html)

## Details

+ Precedence: 2
+ Associativity: Left
+ Predefined Overloads:
  + (char / char) &#8614; char
  + (byte / byte) &#8614; byte
  + (short / short) &#8614; short
  + (int / int) &#8614; int
  + (long / long) &#8614; long
  + (float / float) &#8614; float
  + (double / double) &#8614; double
  + ([BigInteger](https://docs.oracle.com/javase/7/docs/api/java/math/BigInteger.html) / [BigInteger](https://docs.oracle.com/javase/7/docs/api/java/math/BigInteger.html)) &#8614; [BigInteger](https://docs.oracle.com/javase/7/docs/api/java/math/BigInteger.html)
  + ([BigDecimal](https://docs.oracle.com/javase/7/docs/api/java/math/BigDecimal.html) / [BigDecimal](https://docs.oracle.com/javase/7/docs/api/java/math/BigDecimal.html)) &#8614; [BigDecimal](https://docs.oracle.com/javase/7/docs/api/java/math/BigDecimal.html)
+ The overload that best matches the operands will be selected.
  + Unboxing will be performed, if necessary.
  + Coercion will be performed, if necessary.
+ Both operands are greedily evaluated.
  + The left-operand is evaluated first.
  + The right-operand is evaluated second.
+ Return Type: Return-Type of Selected Overload
+ Return the result of the operation.

## Static Checks

+ [NO_SUCH_BINARY_OPERATOR](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#NO_SUCH_BINARY_OPERATOR): None of the overloads will accept the left-operand due to its type.
+ [NO_SUCH_BINARY_OPERATOR](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#NO_SUCH_BINARY_OPERATOR): None of the overloads will accept the right-operand due to its type.

## Example

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    var value = null as Object;

    value = 150C / 2C;
    F::println("char = " .. value);

    value = 10B / 2B;
    F::println("byte = " .. value);

    value = 10S / 2S;
    F::println("short = " .. value);

    value = 10 / 2;
    F::println("int = " .. value);

    value = 10L / 2L;
    F::println("long = " .. value);

    value = 10.0F / 2.0F;
    F::println("float = " .. value);

    value = 10.0 / 2.0;
    F::println("double = " .. value);

    value = F::big(10) / F::big(2);
    F::println("BigInteger = " .. value);

    value = F::big(10.0) / F::big(2.0);
    F::println("BigDecimal = " .. value);

    value = 64 / 8 / 4;
    F::println("Associativity = " .. value);
}
```

**Output:**

```plain
char = K
byte = 5
short = 5
int = 5
long = 5
float = 5.0
double = 5.0
BigInteger = 5.00000000000000000000000000000000
BigDecimal = 5.00000000000000000000000000000000
Associativity = 2
```

