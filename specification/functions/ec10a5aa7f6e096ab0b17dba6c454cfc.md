## Summary

This function retrieves the default value of a given type.

## Signature

defaultValueOf (type : [Class](https://docs.oracle.com/javase/7/docs/api/java/lang/Class.html)) : [Object](https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html)

## Formals

+ Parameter <i>type</i> is the type whose default value will be returned.

## Returns

Return the default value of the <i>type</i>.

## Throws

+ [NullPointerException](https://docs.oracle.com/javase/7/docs/api/java/lang/NullPointerException.html), if the <i>type</i> is null.
+ [IllegalArgumentException](https://docs.oracle.com/javase/7/docs/api/java/lang/IllegalArgumentException.html), if the <i>type</i> is the void-type.

## Details

+ Default Values:
+     boolean-type => false
+     char-type => null character
+     byte-type => zero
+     short-type => zero
+     int-type => zero
+     long-type => zero
+     float-type => zero
+     double-type => zero
+     reference-type => null

## Example

**Source Code:**

```plain
module Main in execution;

@Start
defun main (args : String[]) : void
{
    val value00 = F::defaultValueOf(class boolean);
    val value01 = F::defaultValueOf(class char);
    val value02 = F::defaultValueOf(class byte);
    val value03 = F::defaultValueOf(class short);
    val value04 = F::defaultValueOf(class int);
    val value05 = F::defaultValueOf(class long);
    val value06 = F::defaultValueOf(class float);
    val value07 = F::defaultValueOf(class double);
    val value08 = F::defaultValueOf(class BigInteger);
    val value09 = F::defaultValueOf(class BigDecimal);
    val value10 = F::defaultValueOf(class String);
    val value11 = F::defaultValueOf(class Object);

    F::println(value00);
    F::println(value01 as Integer);
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
}
```

**Output:**

```plain
false
null
0
0
0
0
0.0
0.0
null
null
null
null
```

