# Module Directive

## Summary

A module-directive is used to specify the name and namespace of the enclosing module.

## Syntax

```plain
@<i>annotation<sub>1</sub></i>
@<i>annotation<sub>2</sub></i>
@<i>annotation<sub>n</sub></i>
<span class=\"keyword\">module</span> <i>[name](ConstructPage.html?construct=Name)</i> <span class=\"keyword\">in</span> <i>[namespace](ConstructPage.html?construct=Namespace)</i> ;
<hr class=&#92%22syntax-hr&#92%22>
@<i>annotation<sub>1</sub></i>
@<i>annotation<sub>2</sub></i>
@<i>annotation<sub>n</sub></i>
<span class=\"keyword\">module</span> * <span class=\"keyword\">in</span> <i>[namespace](ConstructPage.html?construct=Namespace)</i> ;
```

## AST Class

autumn.lang.compiler.ast.nodes.ModuleDirective

## Details
+ There are two forms of module-directive, as indicated syntactically above.
  + The first form indicates that the enclosing module is a named module.
  + The second form indicates that the enclosing module is an anonymous module.
    + An anonymous module is simply a module with a compiler generated name.
+ The annotations applied to the directive will be applied to the type of the enclosing module.

