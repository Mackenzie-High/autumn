# Locals Expression

## Summary

A locals-expression creates an object that describes the local variables in the enclosing scope.

## Syntax

<div class="syntax">
( <b>locals</b> )<br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.LocalsExpression](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/LocalsExpression.html)

## Details

+ All user-visible variables in the enclosing scope will be captured.
  + This includes user-visible variables in outer scopes, if any.
  + This excludes temporary variables created by the compiler.
+ Return Type: [LocalsMap](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/LocalsMap.html)
+ Return an object that describes the local variables in the enclosing scope.

## Example

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    val city = "London";
    val year = 2014;
    val primes = [2, 3, 5, 7, 11, 13, 17];

    val map = (locals);

    foreach (x : Local in map.locals())
    {
        F::println(x);
    }
}
```

**Output:**

```plain
args : String[] = [Ljava.lang.String;@3c4bdccc
city : String = London
primes : List = [2, 3, 5, 7, 11, 13, 17]
year : int = 2014
```

