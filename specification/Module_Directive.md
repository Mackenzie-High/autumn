# Module Directive

## Summary

A module-directive is used to specify the name and namespace of the enclosing module.

## Syntax

<div class="syntax">
@<i>annotation<sub>1</sub></i><br>
@<i>annotation<sub>2</sub></i><br>
@<i>annotation<sub>n</sub></i><br>
<b>module</b> <i><a href="Name.md">name</a></i> <b>in</b> <i><a href="Namespace.md">namespace</a></i> ;<br>
<hr><br>
@<i>annotation<sub>1</sub></i><br>
@<i>annotation<sub>2</sub></i><br>
@<i>annotation<sub>n</sub></i><br>
<b>module</b> * <b>in</b> <i><a href="Namespace.md">namespace</a></i> ;<br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.ModuleDirective](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/ModuleDirective.html)

## Details

+ There are two forms of module-directive, as indicated syntactically above.
  + The first form indicates that the enclosing module is a named module.
  + The second form indicates that the enclosing module is an anonymous module.
    + An anonymous module is simply a module with a compiler generated name.
+ The annotations applied to the directive will be applied to the type of the enclosing module.

## Static Checks

+ [DUPLICATE_ANNOTATION](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#DUPLICATE_ANNOTATION): Each annotation in an annotation-list must be uniquely typed.
+ [DUPLICATE_ANNOTATION](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#DUPLICATE_ANNOTATION): Each annotation in an annotation-list must be uniquely typed.

## Example 1

**Source Code:**

```plain
module Enterprise in starships;

@Start
defun main (args : String[]) : void
{
    val mod = My::instance();

    F::println (mod.getClass());
}
```

**Output:**

```plain
class starships.Enterprise
```

## Example 2

**Source Code:**

```plain
module * in starships;

@Start
defun main (args : String[]) : void
{
    val mod = My::instance();

    F::println (mod.getClass());
}
```

**Output:**

```plain
class starships.Module$0
```

## Example 3

**Source Code:**

```plain
@Captain ("Jonathan Archer")
@Scientist ("T'Pol")
module Enterprise in starships;

annotation Captain;

annotation Scientist;

@Start
defun main (args : String[]) : void
{
    val klass = My::instance().getClass();

    val anno1 = klass.getAnnotation((class Captain));
    val anno2 = klass.getAnnotation((class Scientist));

    val captain = F::get(anno1, 0);
    val scientist = F::get(anno2, 0);
    
    F::println("Starship Captain = " .. captain);
    F::println("Science  Officer = " .. scientist);
}
```

**Output:**

```plain
Starship Captain = Jonathan Archer
Science  Officer = T'Pol
```

