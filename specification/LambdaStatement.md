# Lambda Statement

## Summary

(Under Development) - A lambda-statement creates an anonymous function.

## Syntax

```plain
<span class=\"keyword\">lambda</span> <i>[variable](ConstructPage.html?construct=Variable)</i> : <i>[type](ConstructPage.html?construct=TypeSpecifier)</i> => <i>[expression](TextPage.html?page=Expression)</i> ;
```

## AST Class

autumn.lang.compiler.ast.nodes.LambdaStatement

## Details
+ The newly created anonymous function captures the state of the variables declared in the enclosing function.
  + In other words, the lambda function closes over the enclosing scope.
  + Changes to the captured variables are not visible to the lambda function after its creation.

