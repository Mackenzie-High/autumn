# Assume Statement

## Summary

An assume-statement enforces an invariant.

## Syntax

<div class="syntax">
<b>assume</b> <i><a href="Expression.md">condition</a></i> ;<br>
<hr><br>
<b>assume</b> <i><a href="Expression.md">condition</a></i> <b>echo</b> <i><a href="Expression.md">message</a></i> ;<br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.AssertStatement](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/AssertStatement.html)

## Details

+ Unlike an <a href="Assert_Statement.md">Assert Statement</a>, assume-statements can be disabled.
  + By default, assume-statements are enabled.
  + If assume-statements are disabled:
    + The runtime simply ignores all assume-statements.
    + The <i>condition</i> is not evaluated.
    + The <i>message</i> is not evaluated.
  + To enable assume-statements use: [enableAssume()](https://www.mackenziehigh.me/autumn/javadoc/autumn/lang/compiler/Autumn.html#enableAssume())
  + To disable assume-statements use: [disableAssume()](https://www.mackenziehigh.me/autumn/javadoc/autumn/lang/compiler/Autumn.html#disableAssume())
+ If the <i>condition</i> evaluates to false, then an [AssumptionFailedException](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/exceptions/AssumptionFailedException.html) will be thrown.
+ If the <i>condition</i> evaluates to true, then execution simply continues onward.
+ The <i>condition</i> will be unboxed, if necessary.

## Static Checks

+ [EXPECTED_CONDITION](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#EXPECTED_CONDITION): The type of <i><i>condition</i></i> must be assignable to type boolean.
+ [EXPECTED_STRING](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#EXPECTED_STRING): The type of <i>message</i> must be assignable to type java.lang.String.

## Example 1

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    Autumn::enableAssume();

    Main::irs(-1000);
    Main::irs(-2000);
    Main::irs(-3000);

    Main::irs(1000);
    Main::irs(2000);
    Main::irs(3000);

    Autumn::disableAssume();

    Main::irs(-1000);
    Main::irs(-2000);
    Main::irs(-3000);

    Main::irs(1000);
    Main::irs(2000);
    Main::irs(3000);
}

defun irs (income : int) : void
{
    try
    {
        F::println("Income = " .. income);

        val taxes = My::tax(income);

        F::println("Taxes = " .. taxes);
    }
    catch (ex : AssumptionFailedException)
    {
        F::println("Error - Tax Computation Failed");
    }

    F::println();
}

defun tax (income : int) : int
{
    assume income >= 0;

    # Flat Tax of 25%.
    return income / 4; 
}
```

**Output:**

```plain
Income = -1000
Error - Tax Computation Failed

Income = -2000
Error - Tax Computation Failed

Income = -3000
Error - Tax Computation Failed

Income = 1000
Taxes = 250

Income = 2000
Taxes = 500

Income = 3000
Taxes = 750

Income = -1000
Taxes = -250

Income = -2000
Taxes = -500

Income = -3000
Taxes = -750

Income = 1000
Taxes = 250

Income = 2000
Taxes = 500

Income = 3000
Taxes = 750
```

## Example 2

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    Autumn::enableAssume();

    for (i = 1; i <= 9; i + 1)
    {
        try
        {
            My::oddSquare(i);
        }
        catch (ex : AssumptionFailedException)
        {
            F::println("Error - " .. ex.getMessage());
        }
    }
}

defun oddSquare (n : int) : void
{
    assume n % 2 != 0 echo (n .. " is even.");

    F::println(n .. " => " .. n * n);
}
```

**Output:**

```plain
1 => 1
Error - 2 is even.
3 => 9
Error - 4 is even.
5 => 25
Error - 6 is even.
7 => 49
Error - 8 is even.
9 => 81
```

