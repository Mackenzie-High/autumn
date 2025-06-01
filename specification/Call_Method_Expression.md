# Call Method Expression

## Summary

A call-expression performs an invocation of an instance method.

## Syntax

<div class="syntax">
<b>call</b> <i><a href="Expression.md">owner</a></i>.<i><a href="Name.md">name</a></i> ( <i><a href="Expression.md">argument</a><sub>1</sub></i> , ... , <i><a href="Expression.md">argument</a><sub>n</sub></i> )<br>
<hr><br>
<i><a href="Expression.md">owner</a></i>.<i><a href="Name.md">name</a></i> ( <i><a href="Expression.md">argument</a><sub>1</sub></i> , ... , <i><a href="Expression.md">argument</a><sub>n</sub></i> )<br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.CallMethodExpression](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/CallMethodExpression.html)

## Details

+ The method overload will be selected using the <a href="Resolution.md">Instance Method Resolution Algorithm</a>.
+ The method overload is selected at compile-time.
+ Boxing of the arguments will be performed, when necessary.
+ Unboxing of the arguments will be performed, when necessary.
+ Coercion of the arguments will be performed, when necessary.
+ Runtime Check: If <i>owner</i> is null, then a [NullPointerException](https://docs.oracle.com/javase/7/docs/api/java/lang/NullPointerException.html) will be thrown.
+ Return Type: [return-type of the selected method overload]
+ Return the value returned by the invoked method.

## Static Checks

+ [EXPECTED_DECLARED_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#EXPECTED_DECLARED_TYPE): The type of <i>owner</i> must be a declared-type.
+ [NO_SUCH_METHOD](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#NO_SUCH_METHOD): No acceptable method overload was found.

## Example 1

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    # Create an object.
    val sentence = "Hello Alien World";

    # Invoke an instance method. 
    val word = sentence.substring(6, 11);

    # Print the result.
    F::println("Type of World: " .. word);
}
```

**Output:**

```plain
Type of World: Alien
```

## Example 2

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    # Create an object.
    val sentence = "Hello Home World";

    # Invoke an instance method. 
    val word = call sentence.substring(6, 10);

    # Print the result.
    F::println("Type of World: " .. word);
}
```

**Output:**

```plain
Type of World: Home
```

