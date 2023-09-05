# Try-Catch Statement

## Summary

A try-statement provides a mechanism for catching exceptions.

## Syntax

<div class="syntax">
<b>try</b><br>
{<br>
&nbsp;&nbsp;&nbsp;&nbsp;<i><a href="Statement.md">body</a></i><br>
}<br>
<b>catch</b> ( <i><a href="Variable.md">variable</a><sub>1</sub></i> : <i><a href="Type_Specifier.md">type</a><sub>1</sub></i> )<sub>1</sub><br>
{<br>
&nbsp;&nbsp;&nbsp;&nbsp;<i><a href="Statement.md">handler</a><sub>1</sub></i><br>
}<br>
<b>catch</b> ( <i><a href="Variable.md">variable</a><sub>2</sub></i> : <i><a href="Type_Specifier.md">type</a><sub>2</sub></i> )<sub>2</sub><br>
{<br>
&nbsp;&nbsp;&nbsp;&nbsp;<i><a href="Statement.md">handler</a><sub>2</sub></i><br>
}<br>
<b>catch</b> ( <i><a href="Variable.md">variable</a><sub>n</sub></i> : <i><a href="Type_Specifier.md">type</a><sub>n</sub></i> )<sub>n</sub><br>
{<br>
&nbsp;&nbsp;&nbsp;&nbsp;<i><a href="Statement.md">handler</a><sub>n</sub></i><br>
}<br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.TryCatchStatement](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/TryCatchStatement.html)

## Details

+ Exceptions can be thrown using a <a href="Throw_Statement.md">Throw Statement</a>, <a href="Assert_Statement.md">Assert Statement</a>, etc.
+ At least one handler is syntactically required.
+ An exception will only be caught when an applicable handler is available.
  + A handler is applicable when the exception's type is a subtype of the handler's <i>type</i>.
+ The scope of the <i><i>variable<sub>i</sub></i></i> is anywhere in the enclosing function.
+ The <i><i>variable<sub>i</sub></i></i> is alive precisely during an activation of the enclosing function.
+ The compiler will automatically reorder the handlers based on their specificity.
  + Reordering ensures that the handler that best matches an exception will be used to handle it.
  + A handler <i>X</i> is more specific than a handler <i>Y</i>, if <i>type<sub>X</sub></i> is a subclass of <i>type<sub>Y</sub></i>.

## Static Checks

+ [DUPLICATE_VARIABLE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#DUPLICATE_VARIABLE): The <i><i>variable<sub>i</sub></i></i> cannot share its name with another variable declared in the same scope.
+ [NO_SUCH_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#NO_SUCH_TYPE): The type specified by <i><i>type<sub>i</sub></i></i> must exist.
+ [INACCESSIBLE_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#INACCESSIBLE_TYPE): The type specified by <i><i>type<sub>i</sub></i></i> must be accessible.
+ [EXPECTED_THROWABLE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#EXPECTED_THROWABLE): Each <i>type<sub>i</sub></i> must be a subtype of [Throwable](https://docs.oracle.com/javase/7/docs/api/java/lang/Throwable.html).
+ [DUPLICATE_EXCEPTION_HANDLER](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#DUPLICATE_EXCEPTION_HANDLER): No two handlers can catch exactly the same type.

## Example 1

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    for (i = 1; i < 9; i + 1)
    {
        try
        {
            # This invocation throws an exception,
            # whenever the argument is an even number. 
            My::oddCube(i);
        }
        catch (ex : IllegalArgumentException)
        {
            # Handle the exception, by issuing an error message. 
            F::println ("Error: " .. i .. " is even.");
        }
    }
}

defun oddCube(x : int) : void
{
    if (x % 2 == 0)
    {
        # Since the argument is even, throw an exception.
        throw new IllegalArgumentException();
    }
    else
    {
        # Print the argument and its cube. 
        F::println(x .. " => " .. x * x * x);
    }
}
```

**Output:**

```plain
1 => 1
Error: 2 is even.
3 => 27
Error: 4 is even.
5 => 125
Error: 6 is even.
7 => 343
Error: 8 is even.
```

## Example 2

**Source Code:**

```plain
module Main in examples;

exception Disaster extends RuntimeException;

exception Invasion extends Disaster;

exception NaturalDisaster extends Disaster;

exception Asteroid extends NaturalDisaster;

exception Storm extends NaturalDisaster;

exception Tornado extends Storm;

exception Hurricane extends Storm;

exception Volcano extends NaturalDisaster;


@Start
defun main (args : String[]) : void
{
    Main::fema(1);
    Main::fema(2);
    Main::fema(3);
    Main::fema(4);
    Main::fema(5);
    Main::fema(6);
}

defun fema (n : int) : void
{
    try 
    {
        F::println ("Day " .. n);

        My::world(n);

        F::println("Good. No Disaster.");
    }
    catch (natural : NaturalDisaster)
    {
        F::println ("Handle Natural Disaster - " .. natural.getClass());
    }
    catch (storm : Storm)
    {
        F::println ("Handle Storm - " .. storm.getClass());
    }
    catch (disaster : Disaster)
    {
        F::println ("Handle Disaster - " .. disaster.getClass());
    }

    F::println();
}

defun world (x : int) : void
{
    when (x == 1) then throw new Asteroid();

    when (x == 2) then throw new Invasion();

    when (x == 3) then throw new Hurricane();

    when (x == 4) then throw new Tornado();

    when (x == 5) then throw new Volcano();
}
```

**Output:**

```plain
Day 1
Handle Natural Disaster - class examples.Asteroid

Day 2
Handle Disaster - class examples.Invasion

Day 3
Handle Storm - class examples.Hurricane

Day 4
Handle Storm - class examples.Tornado

Day 5
Handle Natural Disaster - class examples.Volcano

Day 6
Good. No Disaster.
```

## Example 3

**Source Code:**

```plain
module Main in examples;

exception Disaster extends RuntimeException;

exception BankRun extends Disaster;

exception CivilUnrest extends Disaster;

exception Invasion extends Disaster;

exception Tornado extends Disaster;


@Start
defun main (args : String[]) : void
{
    Main::goverment(1);
    Main::goverment(2);
    Main::goverment(3);
    Main::goverment(4);
    Main::goverment(5);
}

defun goverment (n : int) : void
{
    try
    {
        F::println("Day " .. n);

        My::fema(n);

        F::println("Pander for votes.");
    }
    catch (problem1 : BankRun)
    {
        F::println("Issue Banker Bailout.");
    }
    catch (problem2 : Disaster)
    {
        F::println("Handle Disaster - " .. problem2.getClass());
    }

    F::println();
}

defun fema (n : int) : void
{
    try 
    {
        My::world(n);
    }
    catch (tornado : Tornado)
    {
        F::println ("Handle Tornado.");
    }
}

defun world (x : int) : void
{
    when (x == 1) then throw new BankRun();

    when (x == 2) then throw new CivilUnrest();

    when (x == 3) then throw new Invasion();

    when (x == 4) then throw new Tornado();
}
```

**Output:**

```plain
Day 1
Issue Banker Bailout.

Day 2
Handle Disaster - class examples.CivilUnrest

Day 3
Handle Disaster - class examples.Invasion

Day 4
Handle Tornado.
Pander for votes.

Day 5
Pander for votes.
```

