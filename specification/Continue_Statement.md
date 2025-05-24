# Continue Statement

## Summary

A continue-statement causes execution to immediately enter the next iteration of the nearest enclosing loop.

## Syntax

<div id="syntax">
<span class=\"keyword\">continue</span> ;<br>
</div>

## AST Class

autumn.lang.compiler.ast.nodes.ContinueStatement

## Details

+ A continue-statement cannot be used to exit an invocation.
+ Types of Loops:
  + [Forever Statement](ConstructPage.html?construct=Forever Statement)s
  + [While Statement](ConstructPage.html?construct=While Statement)s
  + [Until Statement](ConstructPage.html?construct=Until Statement)s
  + [Do-While Statement](ConstructPage.html?construct=Do-While Statement)s
  + [Do-Until Statement](ConstructPage.html?construct=Do-Until Statement)s
  + [For Statement](ConstructPage.html?construct=For Statement)s
  + [Foreach Statement](ConstructPage.html?construct=Foreach Statement)s

## Static Checks

[CONTINUE_OUTSIDE_OF_LOOP, A continue-statement must be in the <i>body</i> of a loop., null]

## Example 1

**Code:**

```plain
module Main in examples;

import java.util.LinkedList;

tuple Pet (kind : String, name : String);

@Start
defun main (args : String[]) : void
{
    val barn = new LinkedList();

    barn.add(new Pet("Chicken", "Chicky")); 
    barn.add(new Pet("Chicken", "Picky"));
    barn.add(new Pet("Dog",     "Jet"));
    barn.add(new Pet("Chicken", "Sikorsky"));
    barn.add(new Pet("Cat",     "Fluffy"));
    barn.add(new Pet("Sheep",   "Eyeball"));
    barn.add(new Pet("Chicken", "Lucky"));

    foreach (pet : Pet in barn)
    {
        when (pet.kind() != "Chicken") then continue;

        F::println("Name of Chicken: " .. pet.name());
    }
}
```

**Output:**

```plain
Name of Chicken: Chicky
Name of Chicken: Picky
Name of Chicken: Sikorsky
Name of Chicken: Lucky
```

## Example 2

**Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    var i = 0;

    while (i < 5)
    {
        i = i + 1;

        when (i == 3) then continue;

        F::println("i = " .. i);
    }
}
```

**Output:**

```plain
i = 1
i = 2
i = 4
i = 5
```

