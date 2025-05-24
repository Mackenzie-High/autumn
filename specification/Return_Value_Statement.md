# Return Value Statement

## Summary

A return-value statement causes execution to immediately exit the invocation of a function.

## Syntax

<div class="syntax">
<b>return</b> <i><a href="Expression.md">value</a></i> ;<br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.ReturnValueStatement](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/ReturnValueStatement.html)

## Details

+ A return-value statement cannot be used in a function whose return-type is void.
+ The <i>value</i> will be boxed, if necessary.
+ The <i>value</i> will be unboxed, if necessary.
+ The <i>value</i> will be coerced, if necessary.

## Static Checks

+ [VALUE_REQUIRED](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#VALUE_REQUIRED): The type of the <i>value</i> must be either a primitive-type or a reference-type.
+ [WRONG_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#WRONG_TYPE): The type of the <i>value</i> must be assignable to the <i>return-type</i> of the enclosing function.

## Example

**Source Code:**

```plain
module Main in program;

@Start
defun main (args : String[]) : void
{
    val x = My::cube(1);
    val y = My::cube(2);
    val z = My::cube(3);

    F::println(x);
    F::println(y);
    F::println(z);
}

defun cube (n : int) : int
{
    return n * n * n; 
}
```

**Output:**

```plain
1
8
27
```

