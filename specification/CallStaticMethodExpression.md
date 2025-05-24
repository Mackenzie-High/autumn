# Call Static Method Expression

## Summary

A call-static-expression performs an invocation of a static method or function.

## Syntax

<div id="syntax">
<span class=\"keyword\">call</span> <i>[Owner](ConstructPage.html?construct=TypeSpecifier)</i>::<i>[name](ConstructPage.html?construct=Name)</i> ( <i>[argument](TextPage.html?page=Expression)<sub>1</sub></i> , ... , <i>[argument](TextPage.html?page=Expression)<sub>n</sub></i> )<br>
<hr class=&#92%22syntax-hr&#92%22><br>
<i>[Owner](ConstructPage.html?construct=TypeSpecifier)</i>::<i>[name](ConstructPage.html?construct=Name)</i> ( <i>[argument](TextPage.html?page=Expression)<sub>1</sub></i> , ... , <i>[argument](TextPage.html?page=Expression)<sub>n</sub></i> )<br>
</div>

## AST Class

autumn.lang.compiler.ast.nodes.CallStaticMethodExpression

## Details

+ The method overload will be selected using the [Static Method Resolution Algorithm](TextPage.html?page=Resolution).
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

