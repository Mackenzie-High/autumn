# Branch Statement

## Summary

A branch-statement creates an unstructured jump-table.

## Syntax

<div class="syntax">
<b>branch</b> ( <a href="Expression.md">index</a> ) ( <i><a href="Label.md">label</a><sub>0</sub></i> , ... , <i><a href="Label.md">label</a><sub>n - 1</sub></i> ) <b>default</b> <i><a href="Label.md">label</a><sub>n</sub></i>;<br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.BranchStatement](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/BranchStatement.html)

## Details

+ A branch-statement is a low-level operation that should usually be avoided by programmers.
+ A branch-statement cannot be used to jump out of a function.
+ The <i>index</i> will be unboxed, if necessary.
+ The <i>index</i> will be coerced, if necessary.
+ Algorithm:
  + Let <i>X</i> be the result of evaluating the <i>index</i>.
  + Let <i>N</i> be the number of labels in the branch-statement, including the default label, minus one.
  + Unbox <i>X</i>, if necessary.
  + Coerce <i>X</i>, if necessary.
  + If <i>X</i> &lt; 0, then jump to the location denoted by <i><a href="Label.md">label</a><sub>N</sub></i>, which is the default label.
  + If <i>X</i> &gt= <i>N</i>, then jump to the location denoted by <i><a href="Label.md">label</a><sub>N</sub></i>, which is the default label.
  + Otherwise, jump to the location denoted by <i><a href="Label.md">label</a><sub>X</sub></i>.

## Static Checks

+ [EXPECTED_INTEGER](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#EXPECTED_INTEGER): The type of <i>index</i> must be assignable to type int.
+ [NO_SUCH_LABEL](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#NO_SUCH_LABEL): Each <i>label<sub>i</sub></i> must be declared somewhere in the enclosing function.

## Example

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    My::act(0, 2);
    My::act(1, 2);
    My::act(2, 2);
    My::act(3, 2);

    My::act(0, 3);
    My::act(1, 3);
    My::act(2, 3);
    My::act(3, 3);

    My::act(0, 4);
    My::act(1, 4);
    My::act(2, 4);
    My::act(3, 4);

    My::act(0, 5);
    My::act(1, 5);
    My::act(2, 5);
    My::act(3, 5);
}

defun act (x : int, n : int) : void
{
    branch (x) (ACTION_1, ACTION_2, ACTION_3) default ELSE;

    marker ACTION_1;
    {
        F::println(n);
        return;
    }

    marker ACTION_2;
    {
        F::println(n * n);
        return;
    }

    marker ACTION_3;
    {
        F::println(n * n * n);
        return;
    }

    marker ELSE;
    {
        F::println();
        return;
    }
}
```

**Output:**

```plain
2
4
8

3
9
27

4
16
64

5
25
125
```

