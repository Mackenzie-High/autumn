# Negate Operation

## Summary

This operator performs an arithmetic negation operation.

## Syntax

<div class="syntax">
- <i><a href="Expression.md">value</a></i><br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.NegateOperation](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/NegateOperation.html)

## Details

+ Precedence: 1
+ Predefined Overloads:
  + (- byte) &#8614; byte
  + (- short) &#8614; short
  + (- int) &#8614; int
  + (- long) &#8614; long
  + (- float) &#8614; float
  + (- double) &#8614; double
  + (- [BigInteger](https://docs.oracle.com/javase/7/docs/api/java/math/BigInteger.html)) => [BigInteger](https://docs.oracle.com/javase/7/docs/api/java/math/BigInteger.html)
  + (- [BigDecimal](https://docs.oracle.com/javase/7/docs/api/java/math/BigDecimal.html)) => [BigDecimal](https://docs.oracle.com/javase/7/docs/api/java/math/BigDecimal.html)
+ The overload that best matches the operand will be selected.
  + Unboxing will be performed, if necessary.
  + Coercion will be performed, if necessary.
+ Return Type: Return-Type of Selected Overload
+ Return the result of the operation.

## Static Checks

+ [NO_SUCH_UNARY_OPERATOR](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#NO_SUCH_UNARY_OPERATOR): None of the overloads will accept the operand due to its type.

## Example

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    val B = 17B;
    val S = 17S;
    val I = 17;
    val L = 17L;
    val F = 17.0F;
    val D = 17.0;
    val BI = F::big(17);
    val BD = F::big(17.0);

    var value = null as Object;
    
    # Negate a byte and print it. 
    value = - B;
    F::println("byte = " .. value);

    # Negate a short and print it. 
    value = - S;
    F::println("short = " .. value);

    # Negate an int and print it. 
    value = - I;
    F::println("int = " .. value);

    # Negate a long and print it. 
    value = - L;
    F::println("long = " .. value);

    # Negate a float and print it. 
    value = - F;
    F::println("float = " .. value);

    # Negate a double and print it. 
    value = - D;
    F::println("double = " .. value);

    # Negate a BigInteger and print it. 
    value = - BI;
    F::println("BigInteger = " .. value);

    # Negate a BigDecimal and print it. 
    value = - BD;
    F::println("BigDecimal = " .. value);
}
```

**Output:**

```plain
byte = -17
short = -17
int = -17
long = -17
float = -17.0
double = -17.0
BigInteger = -17.00000000000000000000000000000000
BigDecimal = -17.00000000000000000000000000000000
```

