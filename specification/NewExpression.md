# New Expression

## Summary

A new-expression creates a new instance of a specified class-type.

## Syntax

<div id="syntax">
<span class=\"keyword\">new</span> <i>[type](ConstructPage.html?construct=TypeSpecifier)</i> ( <i>[argument](TextPage.html?page=Expression)<sub>1</sub></i> , ... , <i>[argument](TextPage.html?page=Expression)<sub>n</sub></i> )<br>
</div>

## AST Class

autumn.lang.compiler.ast.nodes.NewExpression

## Details

+ The constructor overload will be selected using the [Constructor Resolution Algorithm](TextPage.html?page=Resolution).
+ The constructor overload is selected at compile-time.
+ Boxing of the arguments will be performed, when necessary.
+ Unboxing of the arguments will be performed, when necessary.
+ Coercion of the arguments will be performed, when necessary.
+ Return Type: type of <i>type</i>
+ Return a new instance of the <i>type</i>.

## Static Checks

[NO_SUCH_TYPE, The type specified by <i><i>type</i></i> must exist., null]
[INACCESSIBLE_TYPE, The type specified by <i><i>type</i></i> must be accessible., null]
[EXPECTED_CLASS_TYPE, The <i>type</i> must be a class-type., null]
[NO_SUCH_CONSTRUCTOR, No acceptable constructor overload was found., null]

## Example

**Code:**

```plain
module Main in examples;

tuple Pet (type : String, name : String);

@Start
defun main (args : String[]) : void
{
    val animal1 = new Pet("Sheep", "Eyeball");

    val animal2 = new Pet("German Shepherd", "Jet");

    val animal3 = new Pet("Cat", "Fluffy");

    F::printlns([animal1, animal2, animal3]);
}
```

**Output:**

```plain
(Sheep, Eyeball)
(German Shepherd, Jet)
(Cat, Fluffy)
```

