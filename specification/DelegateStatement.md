# Delegate Statement

## Summary

(Under Development) - A delegate-statement creates function-object that is essentially a type-safe function-pointer.

## Syntax

```plain
<span class=\"keyword\">delegate</span> <i>[assignee](ConstructPage.html?construct=Variable)</i> : <i>[type](ConstructPage.html?construct=TypeSpecifier)</i> => <i>[Owner](ConstructPage.html?construct=TypeSpecifier)</i>::<i>[name](ConstructPage.html?construct=Name)</i> ;
```

## AST Class

autumn.lang.compiler.ast.nodes.DelegateStatement

## Details
+ The <i>assignee</i> will be declared in the enclosing scope as a readonly local variable.
+ The scope of the <i>assignee</i> is anywhere in the enclosing function.
+ The <i>assignee</i> is alive precisely during an activation of the enclosing function.
+ A delegate-statement can create a delegate that refers to the special instance() method.
+ The function <i>X</i> that the delegate will refer to must be compatible with the functor-type <i>T</i>.
  + <i>T</i> is the functor-type specified by the <i>type</i>.
  + The return-type of <i>X</i> must be a subtype of the return-type of <i>T</i>.
  + The number of parameters of <i>X</i> must match the number of parameters of <i>T</i>.
  + The type of each parameter<sub>i</sub> of <i>X</i> must be a subtype of parameter<sub>i</sub> of <i>T</i>.
+ Return Type: type of <i>type</i>
+ Return a [Delegate](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Delegate.html) that refers to the specified function.

