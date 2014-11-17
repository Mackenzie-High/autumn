# Type Conversions and Assignability

## Boxing Conversions
+ boolean ==> #JavaLangBoolean#
+ char ==> #JavaLangCharacter#
+ byte ==> #JavaLangByte#
+ short ==> #JavaLangShort#
+ int ==> #JavaLangInteger#
+ long ==> #JavaLangLong#
+ float ==> #JavaLangFloat#
+ double ==> #JavaLangDouble# 



## Unboxing Conversions
+ #JavaLangBoolean# ==> boolean
+ #JavaLangCharacter# ==> char
+ #JavaLangByte# ==> byte
+ #JavaLangShort# ==> short
+ #JavaLangInteger# ==> int
+ #JavaLangLong# ==> long
+ #JavaLangFloat# ==> float
+ #JavaLangDouble# ==> double


## Coercions
+ char ==> int
+ char ==> long
+ byte ==> short
+ byte ==> short
+ byte ==> int
+ byte ==> long
+ short ==> int
+ short ==> long
+ int ==> long
+ float ==> double


## Identity Conversion
Any value of a type X can is a value of type X via an identity conversion. 



## Subtyping
A type X is a subtype of a type T, iff:

+ X is T
+ X is a reference-type and the direct superclass of X is a subtype of T.
+ X is a reference-type and a direct superinterface of X is a subtype of T.
+ X is the null-type and T is a reference-type. 
+ X and T are array-types of N dimensions and the element-type of X is a subtype of the element-type T


## Assignability
A value of type X is assignable to a location of type T, iff:

+ X is T
+ X is a subtype of T
+ X is convertable to T via a boxing conversion
+ X is convertable to T via an unboxing conversion
+ X is the null-type and T is a reference-type 
