# Foreach Statement

## Summary

A foreach-statement is a loop that iterates over the elements in an iterable entity, such as a data-structure.

## Syntax

<div class="syntax">
<span class="keyword">foreach</span> ( <a class="synvar" href="Variable.md">assignee</a></i> : <a class="synvar" href="TypeSpecifier.md">type</a></i> <span class="keyword">in</span> <a href="Expression.md">iterable</a></i> )<br>
{<br>
&nbsp;&nbsp;&nbsp;&nbsp;<a href="Statement.md">body</a></i><br>
}<br>
</div>

## AST Class

autumn.lang.compiler.ast.nodes.ForeachStatement

## Details

+ The scope of the <i>assignee</i> is anywhere in the enclosing function.
+ The <i>assignee</i> is alive precisely during an activation of the enclosing function.
+ The <i>assignee</i> is a readonly variable.
+ The <i>body</i> of a loop can contain break-statements.
+ The <i>body</i> of a loop can contain continue-statements.
+ The <i>body</i> of a loop can contain redo-statements.
+ A [ClassCastException](https://docs.oracle.com/javase/7/docs/api/java/lang/ClassCastException.html) will result at runtime, if the value returned by the iterator cannot be cast to the specified <i>type</i>.

## Static Checks

[DUPLICATE_VARIABLE, The <i>assignee</i> cannot share its name with another variable declared in the same scope., null]
[NO_SUCH_TYPE, The type specified by <i><i>type</i></i> must exist., null]
[INACCESSIBLE_TYPE, The type specified by <i><i>type</i></i> must be accessible., null]
[EXPECTED_REFERENCE_TYPE, The type specified by the <i>type</i> must be a reference-type., null]
[EXPECTED_ITERABLE, The type of the <i>iterable</i> must be a subtype of [Iterable](https://docs.oracle.com/javase/7/docs/api/java/lang/Iterable.html)., null]

## Example

**Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    var pets = ["Jet", "Fluffy", "Eyeball", "Molly"];

    foreach (x : String in pets)
    {
        F::print("Pet Name = ");
        F::println(x);
    }
}
```

**Output:**

```plain
Pet Name = Jet
Pet Name = Fluffy
Pet Name = Eyeball
Pet Name = Molly
```

<style>
    .syntax
    {
        font-family: monospace, monospace;
    }

    .keyword
    {
        color: blue;
        font-weight: bold;
    }

    .synvar
    {
        font-style: italic;
    }
</style>

