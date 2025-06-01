# Modulo Operation

## Summary

This operator performs an arithmetic modulus operation.

## Syntax

<div class="syntax">
<i><a href="Expression.md">left</a></i> &$37; <i><a href="Expression.md">right</a></i><br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.ModuloOperation](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/ModuloOperation.html)

## Details

+ Precedence: 2
+ Associativity: Left
+ Predefined Overloads:
  + (char &$37; char) &#8614; char
  + (byte &$37; byte) &#8614; byte
  + (short &$37; short) &#8614; short
  + (int &$37; int) &#8614; int
  + (long &$37; long) &#8614; long
  + (float &$37; float) &#8614; float
  + (double &$37; double) &#8614; double
  + ([BigInteger](https://docs.oracle.com/javase/7/docs/api/java/math/BigInteger.html) &$37; [BigInteger](https://docs.oracle.com/javase/7/docs/api/java/math/BigInteger.html)) &#8614; [BigInteger](https://docs.oracle.com/javase/7/docs/api/java/math/BigInteger.html)
  + ([BigDecimal](https://docs.oracle.com/javase/7/docs/api/java/math/BigDecimal.html) &$37; [BigDecimal](https://docs.oracle.com/javase/7/docs/api/java/math/BigDecimal.html)) &#8614; [BigDecimal](https://docs.oracle.com/javase/7/docs/api/java/math/BigDecimal.html)
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

    value = 231C % 83C;
    F::println("char = " .. value);

    value = 32B % 3B;
    F::println("byte = " .. value);

    value = 32S % 3S;
    F::println("short = " .. value);

    value = 32 % 3;
    F::println("int = " .. value);

    value = 32L % 3L;
    F::println("long = " .. value);

    value = 32.0F % 3.0F;
    F::println("float = " .. value);

    value = 32.0 % 3.0;
    F::println("double = " .. value);

    value = F::big(32) % F::big(3);
    F::println("BigInteger = " .. value);

    value = F::big(32.0) % F::big(3.0);
    F::println("BigDecimal = " .. value);
}
```

**Output:**

```plain
char = A
byte = 2
short = 2
int = 2
long = 2
float = 2.0
double = 2.0
BigInteger = 2.00000000000000000000000000000000
BigDecimal = 2.00000000000000000000000000000000
```

