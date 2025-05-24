# Return Void Statement

## Summary

A return-statement causes execution to immediately exit the invocation of a function.

## Syntax

<div class="syntax">
<b>return</b> ;<br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.ReturnVoidStatement](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/ReturnVoidStatement.html)

## Details

+ A return-void statement can only be used in a function whose return-type is void.

## Static Checks

+ [EXPECTED_VOID](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#EXPECTED_VOID): The <i>return-type</i> of the enclosing function must be void.

## Example

**Source Code:**

```plain
module Main in program;

@Start
defun main (args : String[]) : void
{
    My::check(1);
    My::check(2);
    My::check(3);
    My::check(4);
    My::check(5);
    My::check(6);
    My::check(7);
    My::check(8);
    My::check(9);
}

defun check (n : int) : void
{
    if (n % 2 == 0)
    {
        return;
    }

    F::println(n .. " is very odd.");
}
```

**Output:**

```plain
1 is very odd.
3 is very odd.
5 is very odd.
7 is very odd.
9 is very odd.
```

