# Call Static Method Expression

## Summary

A call-static-expression performs an invocation of a static method or function.

## Syntax

<div class="syntax">
<b>call</b> <i><a href="TypeSpecifier.md">Owner</a></i>::<i><a href="Name.md">name</a></i> ( <i><a href="Expression.md">argument</a><sub>1</sub></i> , ... , <i><a href="Expression.md">argument</a><sub>n</sub></i> )<br>
<hr><br>
<i><a href="TypeSpecifier.md">Owner</a></i>::<i><a href="Name.md">name</a></i> ( <i><a href="Expression.md">argument</a><sub>1</sub></i> , ... , <i><a href="Expression.md">argument</a><sub>n</sub></i> )<br>
</div>

## AST Class

autumn.lang.compiler.ast.nodes.CallStaticMethodExpression

## Details

+ The method overload will be selected using the <a href="Resolution.md">Static Method Resolution Algorithm</a>.
+ The method overload is selected at compile-time.
+ Boxing of the arguments will be performed, when necessary.
+ Unboxing of the arguments will be performed, when necessary.
+ Coercion of the arguments will be performed, when necessary.
+ Remember, a function is technically a static method.
+ Return Type: [return-type of the selected method overload]
+ Return the value returned by the invoked method.

## Static Checks

[NO_SUCH_TYPE, The type specified by <i><i>owner</i></i> must exist., null]
[INACCESSIBLE_TYPE, The type specified by <i><i>owner</i></i> must be accessible., null]
[EXPECTED_DECLARED_TYPE, The type specified by <i>owner</i> must be a declared-type., null]
[NO_SUCH_METHOD, No acceptable method overload was found., null]

## Example

**Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    call My::printNumber(17);

    My::printNumber(23);
}

defun printNumber (x : int) : void
{
    F::println("x = " .. x);
}
```

**Output:**

```plain
x = 17
x = 23
```

