## Summary

This function retrieves the default value of a given type.

## Signature

defaultValueOf (type : [Class](https://docs.oracle.com/javase/7/docs/api/java/lang/Class.html)) : [Object](https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html)

## Formals

+ Parameter typeis the type whose default value will be returned.

## Returns

Return the default value of the <i>type</i>.

## Throws

+ [NullPointerException](https://docs.oracle.com/javase/7/docs/api/java/lang/NullPointerException.html)if the <i>type</i> is null.
+ [IllegalArgumentException](https://docs.oracle.com/javase/7/docs/api/java/lang/IllegalArgumentException.html)if the <i>type</i> is the void-type.

## Details

+ 0Default Values:
+ 1boolean-type => false
+ 1char-type => null character
+ 1byte-type => zero
+ 1short-type => zero
+ 1int-type => zero
+ 1long-type => zero
+ 1float-type => zero
+ 1double-type => zero
+ 1reference-type => null

