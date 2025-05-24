# Lambda Statement

## Summary

(Under Development) - A lambda-statement creates an anonymous function.

## Syntax

<div class="syntax">
<span class="keyword">lambda</span> <a class="synvar" href="Variable.md">variable</a></i> : <a class="synvar" href="TypeSpecifier.md">type</a></i> => <a href="Expression.md">expression</a></i> ;<br>
</div>

## AST Class

autumn.lang.compiler.ast.nodes.LambdaStatement

## Details

+ The newly created anonymous function captures the state of the variables declared in the enclosing function.
  + In other words, the lambda function closes over the enclosing scope.
  + Changes to the captured variables are not visible to the lambda function after its creation.

## Static Checks

[NO_SUCH_TYPE, The type specified by <i>type</i> must exist., null]
[INACCESSIBLE_TYPE, The type specified by <i>type</i> must be accessible., null]

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

