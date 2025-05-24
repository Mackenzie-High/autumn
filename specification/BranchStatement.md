# Branch Statement

## Summary

A branch-statement creates an unstructured jump-table.

## Syntax

```plain
<span class=\"keyword\">branch</span> ( [index](TextPage.html?page=Expression) ) ( <i>[label](ConstructPage.html?construct=Label)<sub>0</sub></i> , ... , <i>[label](ConstructPage.html?construct=Label)<sub>n - 1</sub></i> ) <span class=\"keyword\">default</span> <i>[label](ConstructPage.html?construct=Label)<sub>n</sub></i>;
```

## AST Class

autumn.lang.compiler.ast.nodes.BranchStatement

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
  + If <i>X</i> &lt; 0, then jump to the location denoted by <i>[label](ConstructPage.html?construct=Label)<sub>N</sub></i>, which is the default label.
  + If <i>X</i> &gt= <i>N</i>, then jump to the location denoted by <i>[label](ConstructPage.html?construct=Label)<sub>N</sub></i>, which is the default label.
  + Otherwise, jump to the location denoted by <i>[label](ConstructPage.html?construct=Label)<sub>X</sub></i>.

