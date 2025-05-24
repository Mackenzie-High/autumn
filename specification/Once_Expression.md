# Once Expression

## Summary

(Under Development) A once-expression can be used to cache a value.

## Syntax

<div class="syntax">
once <a href="Expression.md">value</a><br>
</div>

## AST Class

autumn.lang.compiler.ast.nodes.OnceExpression

## Details

+ Behavior:
  + During the first time the once-expression is evaluated:
    + Evaluate the <i>value</i>.
    + Store the result for later.
    + Return the result.
  + During all subsequent evaluations of the once-expression:
    + Return the previously stored result.
+ Only one thread can be evaluating the once-expression at a time.
  + In other words, the once-expression is synchronized.
+ The result obtained from the <i>value</i> may be void.
+ The result is stored for the lifetime of the enclosing module.

## Static Checks


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

