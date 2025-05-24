# Recur Statement

## Summary

A recur-statement performs a tail-recursive invocation of the enclosing function.

## Syntax

<div class="syntax">
<b>recur</b> <i><a href="Expression.md">argument</a><sub>1</sub></i> , <i><a href="Expression.md">argument</a><sub>2</sub></i> , ... , <i><a href="Expression.md">argument</a><sub>n</sub></i> ;<br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.RecurStatement](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/RecurStatement.html)

## Details

+ A recur-statement can be thought of as a combination of a return-statement and a call-expression.
+ Each <i>argument</i> will be boxed, if necessary.
+ Each <i>argument</i> will be unboxed, if necessary.
+ Each <i>argument</i> will be coerced, if necessary.
+ The <i>return-type</i> of the enclosing function can be void.
+ The <i>return-type</i> of the enclosing function can be non-void.

## Static Checks

+ [BAD_ARGUMENT_COUNT](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#BAD_ARGUMENT_COUNT): There must be exactly one <i>argument</i> for each <i>parameter</i> in the enclosing function.
+ [IMPOSSIBLE_ASSIGNMENT](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#IMPOSSIBLE_ASSIGNMENT): The type of <i>argument<sub>i</sub></i> must be assignable to the type of <i>parameter<sub>i</sub></i> of the enclosing function.

## Example

**Source Code:**

```plain
module Main in execution;

@Start
defun main (args : String[]) : void
{
    # Compute the sum of the integers less than ten million
    # that are evenly divisible by both two and three. 

    val result = My::compute(0, 0);

    F::println(result);
}

defun compute(index : int, sum : long) : long
{
    if (index == 10_000_000)
    {
        return sum;
    }
    elif ((index % 2 == 0) & (index % 3 == 0))
    {
        recur index + 1, sum + index;
    }
    else
    {
        recur index + 1, sum;
    }
}
```

**Output:**

```plain
8333333333333
```

