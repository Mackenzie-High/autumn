# Set Static Field Expression

## Summary

A set-static-field-expression sets the value of a static field.

## Syntax

<div class="syntax">
<b>field</b> <i><a href="Type_Specifier.md">Owner</a></i>::<i><a href="Name.md">name</a></i> = <i><a href="Expression.md">value</a></i><br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.SetStaticFieldExpression](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/SetStaticFieldExpression.html)

## Details

+ The field will be selected using the <a href="Resolution.md">Static Field Resolution Algorithm</a>.
+ Boxing of the value will be performed, when necessary.
+ Unboxing of the value will be performed, when necessary.
+ Coercion of the value will be performed, when necessary.
+ Return Type: void
+ Return nothing, because the return-type is void.

## Static Checks

+ [NO_SUCH_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#NO_SUCH_TYPE): The type specified by <i><i>owner</i></i> must exist.
+ [INACCESSIBLE_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#INACCESSIBLE_TYPE): The type specified by <i><i>owner</i></i> must be accessible.
+ [EXPECTED_DECLARED_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#EXPECTED_DECLARED_TYPE): The type of <i>owner</i> must be a declared-type.
+ [NO_SUCH_FIELD](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#NO_SUCH_FIELD): No acceptable field was found.
+ [ASSIGNMENT_TO_READONLY](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#ASSIGNMENT_TO_READONLY): A value cannot be assigned to a final field, because it is readonly.
+ [IMPOSSIBLE_ASSIGNMENT](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#IMPOSSIBLE_ASSIGNMENT): The type of the <i>value</i> must be assignable to the <i>type</i> of the selected field.

## Example

**Source Code:**

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

