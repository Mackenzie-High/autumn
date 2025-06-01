# Instance-Of Expression

## Summary

An instance-of-expression determines whether a value is an instance of a particular type.

## Syntax

<div class="syntax">
<b>instanceof</b> <i><a href="Expression.md">value</a></i> : <i><a href="Type_Specifier.md">type</a></i><br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.InstanceOfExpression](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/InstanceOfExpression.html)

## Details

+ An instance-of operation is viable, iff:
  + The type of <i>value</i> is not the null-type.
  + and:
    + The type of <i>value</i> is a subtype of the type specified by <i>type</i>.
    + The type specified  by <i>type</i> is a subtype of the type of <i>value</i>.
+ Return Type: boolean
+ Return Return true, iff the <i>value</i> at runtime is both non-null and a subtype of the <i>type</i>.

## Static Checks

+ [NO_SUCH_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#NO_SUCH_TYPE): The type specified by <i><i>type</i></i> must exist.
+ [INACCESSIBLE_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#INACCESSIBLE_TYPE): The type specified by <i><i>type</i></i> must be accessible.
+ [EXPECTED_DECLARED_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#EXPECTED_DECLARED_TYPE): The type of <i>value</i> must be a declared-type.
+ [EXPECTED_DECLARED_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#EXPECTED_DECLARED_TYPE): The type of <i>type</i> must be a declared-type.
+ [NON_VIABLE_INSTANCEOF](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#NON_VIABLE_INSTANCEOF): The operation must be viable at compile-time.

## Example

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    # String
    My::print("Text");

    # Integer
    My::print(17);

    # List
    My::print([2, 4, 6, 8]);
}

defun print(x : Object) : void
{
    F::println("Object? = "  .. (instanceof x : Object));
    F::println("Integer? = " .. (instanceof x : Integer));
    F::println("String? = "  .. (instanceof x : String));
    F::println("List? = "    .. (instanceof x : List));
    F::println();
}
```

**Output:**

```plain
Object? = true
Integer? = false
String? = true
List? = false

Object? = true
Integer? = true
String? = false
List? = false

Object? = true
Integer? = false
String? = false
List? = true
```

