# If-Then Statement

## Summary

An if-statement conditionally executes one or more other statements.

## Syntax

<div class="syntax">
<b>if</b> ( <i><a href="Expression.md">condition</a><sub>1</sub></i> )<br>
{<br>
&nbsp;&nbsp;&nbsp;&nbsp;<i>body<sub>1</sub></i><br>
}<br>
<b>elif</b> ( <i><a href="Expression.md">condition</a><sub>2</sub></i> )<sub>1</sub><br>
{<br>
&nbsp;&nbsp;&nbsp;&nbsp;<i>body<sub>2</sub></i><br>
}<br>
<b>elif</b> ( <i><a href="Expression.md">condition</a><sub>3</sub></i> )<sub>2</sub><br>
{<br>
&nbsp;&nbsp;&nbsp;&nbsp;<i>body<sub>3</sub></i><br>
}<br>
<b>elif</b> ( <i><a href="Expression.md">condition</a><sub>4</sub></i> )<sub>n</sub><br>
{<br>
&nbsp;&nbsp;&nbsp;&nbsp;<i>body<sub>4</sub></i><br>
}<br>
<b>else</b><sub>opt</sub><br>
{<br>
&nbsp;&nbsp;&nbsp;&nbsp;<i>body<sub>5</sub></i><br>
}<br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.IfStatement](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/IfStatement.html)

## Details

+ An if-statement can have zero or more elif-clauses.
+ An if-statement can omit the else-clause.
+ For all <i>i</i> &gt; 1, <i>condition<sub>i</sub></i> will be evaluated, only if <i>condition<sub>i - 1</sub></i> produces false.
+ For all <i>i</i> &gt; 0, <i>body<sub>i</sub></i> will only be executed, only if <i>condition<sub>i</sub></i> produces true.
+ The body of the else-clause will be executed, only if all of the conditions produce false.
+ The conditions will be unboxed, if necessary.

## Static Checks

+ [EXPECTED_CONDITION](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#EXPECTED_CONDITION): The type of each condition must be assignable to primitive-type boolean.

## Example 1

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    for(i = 10; i < 25; i + 1)
    {
        My::write(i);
    }
} 

defun write(index : int) : void
{
    val case1 = index % 3 == 0; // Fizz
    val case2 = index % 5 == 0; // Buzz
    val case3 = case1 & case2;  // Both

    if(case3)
    {
        F::println(index .. " = Both");
    }
    elif(case2)
    {
        F::println(index .. " = Buzz");
    }
    elif(case1)
    {
        F::println(index .. " = Fizz");
    }
    else
    {
        F::println(index);
    }
}
```

**Output:**

```plain
10 = Buzz
11
12 = Fizz
13
14
15 = Both
16
17
18 = Fizz
19
20 = Buzz
21 = Fizz
22
23
24 = Fizz
```

## Example 2

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    My::write("Mercury", false);
    My::write("Venus", false);
    My::write("Earth", false);
    My::write("Mars", false);

    F::println();

    My::write("Jupiter", true);
    My::write("Saturn", true);
    My::write("Uranus", true);
    My::write("Neptune", true);
}

defun write(name : String, jovian : boolean) : void
{
    if(jovian)
    {
        F::println( name .. " is a jovian planet.");
    }
    else
    {
        F::println( name .. " is a terrestrial planet.");
    }
}
```

**Output:**

```plain
Mercury is a terrestrial planet.
Venus is a terrestrial planet.
Earth is a terrestrial planet.
Mars is a terrestrial planet.

Jupiter is a jovian planet.
Saturn is a jovian planet.
Uranus is a jovian planet.
Neptune is a jovian planet.
```

## Example 3

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    My::write(0);
    My::write(1);
    My::write(2);
    My::write(3);
    My::write(4);
    My::write(5);
    My::write(6);
    My::write(7);
    My::write(8);
    My::write(9);
}

defun write(number : int) : void
{
    if(number % 2 != 0)
    {
        F::println(number .. " is very odd.");
    }
}
```

**Output:**

```plain
1 is very odd.
3 is very odd.
5 is very odd.
7 is very odd.
9 is very odd.
```

