# Get Field Expression

## Summary

A get-field-expression gets the value of an instance field.

## Syntax

<div class="syntax">
<b>field</b> <i><a href="Expression.md">owner</a></i>.<i><a href="Name.md">name</a></i><br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.GetFieldExpression](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/GetFieldExpression.html)

## Details

+ The field will be selected using the <a href="Resolution.md">Instance Field Resolution Algorithm</a>.
+ Return Type: [type of the selected field]
+ Return the value stored in the field.

## Static Checks

+ [EXPECTED_DECLARED_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#EXPECTED_DECLARED_TYPE): The type of <i>owner</i> must be a declared-type.
+ [NO_SUCH_FIELD](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#NO_SUCH_FIELD): No acceptable field was found.

## Example

**Source Code:**

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

