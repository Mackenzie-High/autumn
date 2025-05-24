# Once Expression

## Summary

(Under Development) A once-expression can be used to cache a value.

## Syntax

<div id="syntax">
once [value](TextPage.html?page=Expression)<br>
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


