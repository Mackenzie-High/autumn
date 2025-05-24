# Formal Parameter List

## Summary

A formal-parameter-list is a list of formal-parameters.

## Syntax

<div class="syntax">
( <i><a href="Formal_Parameter.md">param</a><sub>1</sub></i> , ... , <i><a href="Formal_Parameter.md">param</a><sub>n</sub></i> )<br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.FormalParameterList](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/FormalParameterList.html)

## Example

**Source Code:**

```plain
module Main in execution;


/// This function has a formal-parameter-list with three elements. 
///
defun f (x : int, 
         y : int, 
         z : int) : int
{
    return x * y + z;
}

@Start
defun main (args : String[]) : void
{
    F::println(My::f(2, 3, 4));
    F::println(My::f(2, 3, 5));
    F::println(My::f(2, 3, 6));
    F::println(My::f(2, 3, 7));
}
```

**Output:**

```plain
10
11
12
13
```

