# Equality Operation

## Summary

This operator performs an equality comparison operation.

## Syntax

<div class="syntax">
<i><a href="Expression.md">left</a></i> == <i><a href="Expression.md">right</a></i><br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.EqualsOperation](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/EqualsOperation.html)

## Details

+ Precedence: 6
+ Associativity: Left
+ Predefined Overloads:
  + (boolean == boolean) &#8614; boolean
  + (char == char) &#8614; boolean
  + (byte == byte) &#8614; boolean
  + (short == short) &#8614; boolean
  + (int == int) &#8614; boolean
  + (long == long) &#8614; boolean
  + (float == float) &#8614; boolean
  + (double == double) &#8614; boolean
  + ([BigInteger](https://docs.oracle.com/javase/7/docs/api/java/math/BigInteger.html) == [BigInteger](https://docs.oracle.com/javase/7/docs/api/java/math/BigInteger.html)) &#8614; [BigInteger](https://docs.oracle.com/javase/7/docs/api/java/math/BigInteger.html)
  + ([BigDecimal](https://docs.oracle.com/javase/7/docs/api/java/math/BigDecimal.html) == [BigDecimal](https://docs.oracle.com/javase/7/docs/api/java/math/BigDecimal.html)) &#8614; [BigDecimal](https://docs.oracle.com/javase/7/docs/api/java/math/BigDecimal.html)
  + ([Object](https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html) == [Object](https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html)) &#8614; boolean
+ The overload that best matches the operands will be selected.
  + Boxing will be performed, if necessary.
  + Unboxing will be performed, if necessary.
  + Coercion will be performed, if necessary.
+ Both operands are greedily evaluated.
  + The left-operand is evaluated first.
  + The right-operand is evaluated second.
+ Return Type: boolean
+ Return true when the two operands are equal.

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
    var case1 = false;
    var case2 = false;
    var case3 = false;
    var case4 = false;


    F::println("Case: boolean");

    case1 = false == false;
    case2 = false == true;
    case3 = true == false;
    case4 = true == true;

    F::println(case1);
    F::println(case2);
    F::println(case3);
    F::println(case4);

    F::println();
    F::println("Case: char");

    case1 = 2C == 3C;
    case2 = 3C == 3C;
    case3 = 3C == 2C;

    F::println(case1);
    F::println(case2);
    F::println(case3);
   
    F::println();
    F::println("Case: byte");

    case1 = 2B == 3B;
    case2 = 3B == 3B;
    case3 = 3B == 2B;

    F::println(case1);
    F::println(case2);
    F::println(case3);

    F::println();
    F::println("Case: short");

    case1 = 2S == 3S;
    case2 = 3S == 3S;
    case3 = 3S == 2S;

    F::println(case1);
    F::println(case2);
    F::println(case3);

    F::println();
    F::println("Case: int");

    case1 = 2 == 3;
    case2 = 3 == 3;
    case3 = 3 == 2;

    F::println(case1);
    F::println(case2);
    F::println(case3);

    F::println();
    F::println("Case: long");

    case1 = 2L == 3L;
    case2 = 3L == 3L;
    case3 = 3L == 2L;

    F::println(case1);
    F::println(case2);
    F::println(case3);

    F::println();
    F::println("Case: float");

    case1 = 2.0F == 3.0F;
    case2 = 3.0F == 3.0F;
    case3 = 3.0F == 2.0F;

    F::println(case1);
    F::println(case2);
    F::println(case3);

    F::println();
    F::println("Case: double");

    case1 = 2.0 == 3.0;
    case2 = 3.0 == 3.0;
    case3 = 3.0 == 2.0;

    F::println(case1);
    F::println(case2);
    F::println(case3);

    F::println();
    F::println("Case: Comparable");

    case1 = "A" == "B";
    case2 = "B" == "B";
    case3 = "B" == "A";

    F::println(case1);
    F::println(case2);
    F::println(case3);
}
```

**Output:**

```plain
Case: boolean
true
false
false
true

Case: char
false
true
false

Case: byte
false
true
false

Case: short
false
true
false

Case: int
false
true
false

Case: long
false
true
false

Case: float
false
true
false

Case: double
false
true
false

Case: Comparable
false
true
false
```

