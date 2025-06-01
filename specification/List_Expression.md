# List Expression

## Summary

A list-expression creates a new mutable list from a list of expressions.

## Syntax

<div class="syntax">
[ <i><a href="Expression.md">value</a><sub>1</sub></i> , <i><a href="Expression.md">value</a><sub>2</sub></i> , ... , <i><a href="Expression.md">value</a><sub>n</sub></i> ]<br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.ListExpression](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/ListExpression.html)

## Details

+ The values of the elements will be boxed when necessary.
+ Return Type: [List](https://docs.oracle.com/javase/7/docs/api/java/util/List.html)
+ Return a new mutable list object is returned that contains the values of the arguments.

## Static Checks

+ [VALUE_REQUIRED](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#VALUE_REQUIRED): The type of each <i>element</i> must be either a primitive-type or a reference-type.

## Example 1

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    # Create an empty list.
    val empty = [];

    # Create a list with a single element. 
    val home = ["Earth"];

    # Create a list with two elements. 
    val hot = ["Mercury", "Venus"];

    # Create a list with multiple elements.
    val jovian = ["Jupiter", "Saturn", "Uranus", "Neptune"];

    # Print the results.
    F::println(empty);
    F::println(home);
    F::println(hot);
    F::println(jovian);
}
```

**Output:**

```plain
[]
[Earth]
[Mercury, Venus]
[Jupiter, Saturn, Uranus, Neptune]
```

## Example 2

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    # Create an empty list.
    val pets = [];

    # Add elements to the list. 
    pets.add("Jet");
    pets.add("Fluffy");
    pets.add("Chicky");
    pets.add("Picky");
    pets.add("Sikorsky");
    pets.add("Lucky");
    pets.add("Eyeball");

    # Print the results.
    F::println(pets);
}
```

**Output:**

```plain
[Jet, Fluffy, Chicky, Picky, Sikorsky, Lucky, Eyeball]
```

