# Get Field Expression

## Summary

A get-field-expression gets the value of an instance field.

## Syntax

<div id="syntax">
<span class=\"keyword\">field</span> <i>[owner](TextPage.html?page=Expression)</i>.<i>[name](ConstructPage.html?construct=Name)</i><br>
</div>

## AST Class

autumn.lang.compiler.ast.nodes.GetFieldExpression

## Details

+ The field will be selected using the [Instance Field Resolution Algorithm](TextPage.html?page=Resolution).
+ Return Type: [type of the selected field]
+ Return the value stored in the field.

## Static Checks

[EXPECTED_DECLARED_TYPE, The type of <i>owner</i> must be a declared-type., null]
[NO_SUCH_FIELD, No acceptable field was found., null]

## Example

**Code:**

```plain
module Main in examples;

import high.mackenzie.autumn.resources.InstanceFieldTester;

@Start
defun main (args : String[]) : void
{
    val object = new InstanceFieldTester();

    val value = field object.answer;

    F::println("Life and Everything = " .. value);
}
```

**Output:**

```plain
Life and Everything = 42
```

