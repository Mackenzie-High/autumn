# List Comprehension Expression

## Summary

A list-comprehension creates a new mutable list based on another iterable data-structure.

## Syntax

```plain
[ <i>[modifier](TextPage.html?page=Expression)</i> <span class=\"keyword\">for</span> [assignee](ConstructPage.html?construct=Variable) : [type](ConstructPage.html?construct=TypeSpecifier) in [iterable](TextPage.html?page=Expression) ]
<hr class=&#92%22syntax-hr&#92%22>
[ <i>[modifier](TextPage.html?page=Expression)</i> <span class=\"keyword\">for</span> [assignee](ConstructPage.html?construct=Variable) : [type](ConstructPage.html?construct=TypeSpecifier) in [iterable](TextPage.html?page=Expression) <span class=\"keyword\">if</span> [condition](TextPage.html?page=Expression) ]
```

## AST Class

autumn.lang.compiler.ast.nodes.ListComprehensionExpression

## Details
+ Algorithm:
  + Let <i>L</i> be a new [LinkedList](https://docs.oracle.com/javase/7/docs/api/java/util/LinkedList.html).
  + Let <i>E</i> denote the value produced by evaluating <i>iterable</i>.
  + If <i>E</i> is null, throw a [NullPointerException](https://docs.oracle.com/javase/7/docs/api/java/lang/NullPointerException.html).
  + For each value <i>X</i> in <i>E</i> do:
    + Let <i>T</i> be the type specified by <i>type</i>.
    + If <i>X</i> cannot be cast to <i>T</i>, throw a [ClassCastException](https://docs.oracle.com/javase/7/docs/api/java/lang/ClassCastException.html).
    + Let <i>V</i> be the local variable specified by <i>assignee</i>.
    + Assign <i>X</i> to <i>V</i>.
    + If there is a <i>condition</i> then:
      + Let <i>C</i> be the value produced by evaluating the condition.
      + Unbox <i>C</i>, if necessary.
      + If <i>C</i> is false, then immediately start the next iteration of the loop.
    + Let <i>M</i> be the value produced by evaluating the <i>modifier</i>.
    + Box <i>M</i>, if necessary.
    + Append <i>M</i> to <i>L</i>.
  + Return <i>L</i>.
+ The scope of the <i>assignee</i> is anywhere in the enclosing function.
+ The <i>assignee</i> is alive precisely during an activation of the enclosing function.
+ The <i>assignee</i> is a readonly variable.
+ The <i>condition</i> will be unboxed, if necessary.
+ Return Type: [List](https://docs.oracle.com/javase/7/docs/api/java/util/List.html)
+ Return a new mutable linked-list data-structure..

