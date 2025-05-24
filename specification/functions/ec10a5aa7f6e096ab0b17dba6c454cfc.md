## Summary

This function retrieves the default value of a given type.

## Signature

defaultValueOf (type : [Class](https://docs.oracle.com/javase/7/docs/api/java/lang/Class.html)) : [Object](https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html)

## Formals

+ Parameter typeis the type whose default value will be returned.

## Returns

Return the default value of the <i>type</i>.

## Throws

+ [NullPointerException](https://docs.oracle.com/javase/7/docs/api/java/lang/NullPointerException.html): if the <i>type</i> is null.
+ [IllegalArgumentException](https://docs.oracle.com/javase/7/docs/api/java/lang/IllegalArgumentException.html): if the <i>type</i> is the void-type.

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

