# Formal Parameter

## Summary

A formal-parameter is an explicitly typed variable declaration.

## Syntax

<div class="syntax">
<i><a href="Variable.md">variable</a></i> : <i><a href="Type_Specifier.md">type</a></i><br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.FormalParameter](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/FormalParameter.html)

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

