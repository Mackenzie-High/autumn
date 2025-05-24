# Get Static Field Expression

## Summary

A get-static-field-expression gets the value of a static field.

## Syntax

<div class="syntax">
<b>field</b> <i><a href="TypeSpecifier.md">Owner</a></i>::<i><a href="Name.md">name</a></i> = <i><a href="Expression.md">value</a></i><br>
</div>

## AST Class

autumn.lang.compiler.ast.nodes.GetStaticFieldExpression

## Details

+ The field will be selected using the <a href="Resolution.md">Static Field Resolution Algorithm</a>.
+ Return Type: [type of the selected field]
+ Return the value stored in the field.

## Static Checks

[NO_SUCH_TYPE, The type specified by <i><i>owner</i></i> must exist., null]
[INACCESSIBLE_TYPE, The type specified by <i><i>owner</i></i> must be accessible., null]
[EXPECTED_DECLARED_TYPE, The type of <i>owner</i> must be a declared-type., null]
[NO_SUCH_FIELD, No acceptable field was found., null]

## Example

**Code:**

```plain
module Main in examples;


@Start
defun main (args : String[]) : void
{
    val min = (field Integer::MIN_VALUE);
    val max = (field Integer::MAX_VALUE);

    F::println("Integer Range: [" .. min .. ", " .. max .. "]");
}
```

**Output:**

```plain
Integer Range: [-2147483648, 2147483647]
```

