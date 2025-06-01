# When Statement

## Summary

A when-statement makes the execution of another statement conditional.

## Syntax

<div class="syntax">
<b>when</b> ( <i><a href="Expression.md">condition</a></i> ) <b>then</b> <i><a href="Statement.md">statement</a></i><br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.WhenStatement](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/WhenStatement.html)

## Details

+ The condition will be unboxed, if necessary.
+ The statement will be executed, only if the <i>condition</i> produces true.
+ The <i>condition</i> will be unboxed, if necessary.

## Static Checks

+ [EXPECTED_CONDITION](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#EXPECTED_CONDITION): The type of <i><i>condition</i></i> must be assignable to type boolean.

## Example 1

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    My::write("Europa", false);
    My::write("Titan", false);
    My::write("Ceres", false);
    My::write("Mimas", false);

    My::write("Mercury", true);
    My::write("Venus", true);
    My::write("Earth", true);
    My::write("Mars", true);
}

defun write(name : String, planet : boolean) : void
{
    when (planet) then F::println(name .. " is a planet.");
}
```

**Output:**

```plain
Mercury is a planet.
Venus is a planet.
Earth is a planet.
Mars is a planet.
```

## Example 2

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    val emma = 5000;
    val elle = 23000;
    val evie = 127000;
    val erin = 230000;

    My::write("Emma", emma);
    My::write("Elle", elle);
    My::write("Evie", evie);
    My::write("Erin", erin);
}

defun write(name : String, income : int) : void
{
    val tax = My::tax(income);

    F::println(name .. "'s Tax = " .. tax);
}

defun tax(income : int) : int
{
    // No tax, if income is less than $10000.
    when (income < 10000) then return 0;

    // 10% tax, if income is less than $100,000.
    when (income < 100000) then return income / 10;

    // 20% tax, if income is less than $200,000.
    when (income < 200000) then return income / 5;

    // Otherwise, 50% tax.
    return income / 2;
}
```

**Output:**

```plain
Emma's Tax = 0
Elle's Tax = 2300
Evie's Tax = 25400
Erin's Tax = 115000
```

