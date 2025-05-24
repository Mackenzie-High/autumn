# Set Static Field Expression

## Summary

A set-static-field-expression sets the value of a static field.

## Syntax

<div id="syntax">
<span class=\"keyword\">field</span> <i>[Owner](ConstructPage.html?construct=TypeSpecifier)</i>::<i>[name](ConstructPage.html?construct=Name)</i> = <i>[value](TextPage.html?page=Expression)</i><br>
</div>

## AST Class

autumn.lang.compiler.ast.nodes.SetStaticFieldExpression

## Details

+ The field will be selected using the [Static Field Resolution Algorithm](TextPage.html?page=Resolution).
+ Boxing of the value will be performed, when necessary.
+ Unboxing of the value will be performed, when necessary.
+ Coercion of the value will be performed, when necessary.
+ Return Type: void
+ Return nothing, because the return-type is void.

## Static Checks

[NO_SUCH_TYPE, The type specified by <i><i>owner</i></i> must exist., null]
[INACCESSIBLE_TYPE, The type specified by <i><i>owner</i></i> must be accessible., null]
[EXPECTED_DECLARED_TYPE, The type of <i>owner</i> must be a declared-type., null]
[NO_SUCH_FIELD, No acceptable field was found., null]
[ASSIGNMENT_TO_READONLY, A value cannot be assigned to a final field, because it is readonly., null]
[IMPOSSIBLE_ASSIGNMENT, The type of the <i>value</i> must be assignable to the <i>type</i> of the selected field., null]

## Example

**Code:**

```plain
module Main in examples;

import high.mackenzie.autumn.resources.StaticFieldTester;


@Start
defun main (args : String[]) : void
{
    (field StaticFieldTester::value9 = "Venus");

    F::println("Output = " .. (field StaticFieldTester::value9));
}
```

**Output:**

```plain
Output = Venus
```

