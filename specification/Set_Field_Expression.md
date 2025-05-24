# Set Field Expression

## Summary

A set-field-expression sets the value of an instance field.

## Syntax

<div class="syntax">
<b>field</b> <i><a href="Expression.md">owner</a></i>.<i><a href="Name.md">name</a></i> = <i><a href="Expression.md">value</a></i><br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.SetFieldExpression](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/SetFieldExpression.html)

## Details

+ The field will be selected using the <a href="Resolution.md">Instance Field Resolution Algorithm</a>.
+ Boxing of the value will be performed, when necessary.
+ Unboxing of the value will be performed, when necessary.
+ Coercion of the value will be performed, when necessary.
+ Runtime Check: If <i>owner</i> is null, then a [NullPointerException](https://docs.oracle.com/javase/7/docs/api/java/lang/NullPointerException.html) will be thrown.
+ Return Type: void
+ Return nothing, because the return-type is void.

## Static Checks

+ [EXPECTED_DECLARED_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#EXPECTED_DECLARED_TYPE): The type of <i>owner</i> must be a declared-type.
+ [NO_SUCH_FIELD](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#NO_SUCH_FIELD): No acceptable field was found.
+ [ASSIGNMENT_TO_READONLY](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#ASSIGNMENT_TO_READONLY): A value cannot be assigned to a final field, because it is readonly.
+ [IMPOSSIBLE_ASSIGNMENT](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#IMPOSSIBLE_ASSIGNMENT): The type of the <i>value</i> must be assignable to the <i>type</i> of the selected field.

## Example

**Source Code:**

```plain
module Main in examples;

import high.mackenzie.autumn.resources.InstanceFieldTester;

@Start
defun main (args : String[]) : void
{
    # Create an object that has a mutable field. 
    val object = new InstanceFieldTester();

    # Set the field.
    field object.value5 = 17;

    # Print the value stored in the field. 
    F::println(field object.value5);

    # Set the field.
    field object.value5 = 23;

    # Print the value stored in the field. 
    F::println(field object.value5);
}
```

**Output:**

```plain
17
23
```

