## Summary

This function retrieves the default value of a given type.

## Signature

defaultValueOf (type : [Class](https://docs.oracle.com/javase/7/docs/api/java/lang/Class.html)) : [Object](https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html)

## Formals

+ Parameter typeis the type whose default value will be returned.

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
module Main in execution;%0A%0A@Start%0Adefun main (args : String[]) : void%0A{%0A    val value00 = F::defaultValueOf(class boolean);%0A    val value01 = F::defaultValueOf(class char);%0A    val value02 = F::defaultValueOf(class byte);%0A    val value03 = F::defaultValueOf(class short);%0A    val value04 = F::defaultValueOf(class int);%0A    val value05 = F::defaultValueOf(class long);%0A    val value06 = F::defaultValueOf(class float);%0A    val value07 = F::defaultValueOf(class double);%0A    val value08 = F::defaultValueOf(class BigInteger);%0A    val value09 = F::defaultValueOf(class BigDecimal);%0A    val value10 = F::defaultValueOf(class String);%0A    val value11 = F::defaultValueOf(class Object);%0A%0A    F::println(value00);%0A    F::println(value01 as Integer);%0A    F::println(value02);%0A    F::println(value03);%0A    F::println(value04);%0A    F::println(value05);%0A    F::println(value06);%0A    F::println(value07);%0A    F::println(value08);%0A    F::println(value09);%0A    F::println(value10);%0A    F::println(value11);%0A}
```

**Output:**

```plain
false%0Anull%0A0%0A0%0A0%0A0%0A0.0%0A0.0%0Anull%0Anull%0Anull%0Anull
```

