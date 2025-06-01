# Class Datum

## Summary

A class-datum is a literal [Class](https://docs.oracle.com/javase/7/docs/api/java/lang/Class.html) value.

## Syntax

<div class="syntax">
( <b>class</b> <i><a href="Type_Specifier.md">type</a></i> )<br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.ClassDatum](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/ClassDatum.html)

## Details

+ Return Type: [Class](https://docs.oracle.com/javase/7/docs/api/java/lang/Class.html)
+ Return the Class object representation of the <i>type</i>.

## Static Checks

+ [NO_SUCH_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#NO_SUCH_TYPE): The type specified by <i><i>type</i></i> must exist.
+ [INACCESSIBLE_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#INACCESSIBLE_TYPE): The type specified by <i><i>type</i></i> must be accessible.
+ [EXPECTED_RETURN_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#EXPECTED_RETURN_TYPE): The type specified by <i>type</i> must be a return-type.

## Example

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    var value00 = (class void);
    var value01 = (class boolean);
    var value02 = (class char);
    var value03 = (class byte);
    var value04 = (class short);
    var value05 = (class int);
    var value06 = (class long);
    var value07 = (class float);
    var value08 = (class double);
    var value09 = (class String);
    var value10 = (class String[]);
    var value11 = (class String[][]);
    var value12 = (class String[][][]);

    F::println(value00);
    F::println(value01);
    F::println(value02);
    F::println(value03);
    F::println(value04);
    F::println(value05);
    F::println(value06);
    F::println(value07);
    F::println(value08);
    F::println(value09);
    F::println(value10);
    F::println(value11);
    F::println(value12);
}
```

**Output:**

```plain
void
boolean
char
byte
short
int
long
float
double
class java.lang.String
class [Ljava.lang.String;
class [[Ljava.lang.String;
class [[[Ljava.lang.String;
```

