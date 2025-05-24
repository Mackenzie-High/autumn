# Lambda Statement

## Summary

(Under Development) - A lambda-statement creates an anonymous function.

## Syntax

<div class="syntax">
<b>lambda</b> <i><a href="Variable.md">variable</a></i> : <i><a href="TypeSpecifier.md">type</a></i> => <i><a href="Expression.md">expression</a></i> ;<br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.LambdaStatement](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/LambdaStatement.html)

## Details

+ The newly created anonymous function captures the state of the variables declared in the enclosing function.
  + In other words, the lambda function closes over the enclosing scope.
  + Changes to the captured variables are not visible to the lambda function after its creation.

## Static Checks

+ [NO_SUCH_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#NO_SUCH_TYPE): The type specified by <i>type</i> must exist.
+ [INACCESSIBLE_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#INACCESSIBLE_TYPE): The type specified by <i>type</i> must be accessible.

