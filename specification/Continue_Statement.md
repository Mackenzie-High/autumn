# Continue Statement

## Summary

A continue-statement causes execution to immediately enter the next iteration of the nearest enclosing loop.

## Syntax

<div class="syntax">
<b>continue</b> ;<br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.ContinueStatement](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/ContinueStatement.html)

## Details

+ A continue-statement cannot be used to exit an invocation.
+ Types of Loops:
  + <a href="Forever_Statement.md">Forever Statement</a>s
  + <a href="While_Statement.md">While Statement</a>s
  + <a href="Until_Statement.md">Until Statement</a>s
  + <a href="Do_While_Statement.md">Do-While Statement</a>s
  + <a href="Do_Until_Statement.md">Do-Until Statement</a>s
  + <a href="For_Statement.md">For Statement</a>s
  + <a href="Foreach_Statement.md">Foreach Statement</a>s

## Static Checks

+ [CONTINUE_OUTSIDE_OF_LOOP](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#CONTINUE_OUTSIDE_OF_LOOP): A continue-statement must be in the <i>body</i> of a loop.

## Example 1

**Source Code:**

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

**Source Code:**

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

