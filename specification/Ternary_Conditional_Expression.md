# Ternary Conditional Expression

## Summary

A ternary-conditional-expression conditionally chooses one of two values.

## Syntax

<div class="syntax">
( <b>if</b> <i><a href="Expression.md">condition</a></i> <b>then</b> <i><a href="Expression.md">left</a></i> <b>else</b> <i><a href="Expression.md">right</a></i>)<br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.TernaryConditionalExpression](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/TernaryConditionalExpression.html)

## Details

+ The <i>left</i> expression is only evaluated, if the <i>condition</i> produces true.
+ The <i>right</i> expression is only evaluated, if the <i>condition</i> produces false.
+ The <i>condition</i> will be unboxed, if necessary.
+ Return Type: widest(typeof(<i>left</i>), typeof(<i>right</i>))
+ Return either the value of <i>left</i> or the value of <i>right</i>, depending on the value produced by the <i>condition</i>.

## Static Checks

+ [EXPECTED_CONDITION](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#EXPECTED_CONDITION): The type of <i><i>condition</i></i> must be assignable to type boolean.
+ [VALUE_REQUIRED](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#VALUE_REQUIRED): The type of the <i>left</i> must be either a primitive-type or a reference-type.
+ [VALUE_REQUIRED](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#VALUE_REQUIRED): The type of the <i>right</i> must be either a primitive-type or a reference-type.
+ [INCOMPATIBLE_OPERANDS](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#INCOMPATIBLE_OPERANDS): The type of one of the operands must be a subtype of the other.

## Example

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    My::print("Mercury", 1);
    My::print("Venus", 2);
    My::print("Earth", 3);
    My::print("Mars", 4);

    My::print("Jupiter", 5);
    My::print("Saturn", 6);
    My::print("Uranus", 7);
    My::print("Neptune", 8);
}

defun print (planet : String, index : int) : void
{
    val type = (if index <= 4 then "Terrestrial" else "Jovian");

    F::println(planet .. " is " .. type);

    F::println();
}
```

**Output:**

```plain
Mercury is Terrestrial

Venus is Terrestrial

Earth is Terrestrial

Mars is Terrestrial

Jupiter is Jovian

Saturn is Jovian

Uranus is Jovian

Neptune is Jovian
```

