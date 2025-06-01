# As Operation

## Summary

An as-operation converts a value to another type.

## Syntax

<div class="syntax">
<i>value</i> <b>as</b> <i><a href="Type_Specifier.md">type</a></i><br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.AsOperation](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/AsOperation.html)

## Details

+ Precedence: 9
+ For every conversion provided by this operation, there is a similar conversion provided by the is-operation.
  + More formally, as-operations are symmetric to is-operations.
+ Predefined Conversions
  + Boxing Conversions
    + boolean &#8614; Boolean
    + char &#8614; Character
    + byte &#8614; Byte
    + short &#8614; Short
    + int &#8614; Integer
    + long &#8614; Long
    + float &#8614; Float
    + double &#8614; Double
    + boolean &#8614; Object
    + char &#8614; Object
    + byte &#8614; Object
    + short &#8614; Object
    + int &#8614; Object
    + long &#8614; Object
    + float &#8614; Object
    + double &#8614; Object
  + Unboxing Conversions
    + Boolean &#8614; boolean
    + Character &#8614; char
    + Byte &#8614; byte
    + Short &#8614; short
    + Integer &#8614; int
    + Long &#8614; long
    + Float &#8614; float
    + Double &#8614; double
  + To String Conversions
    + boolean &#8614; String
    + char &#8614; String
    + byte &#8614; String
    + short &#8614; String
    + int &#8614; String
    + long &#8614; String
    + float &#8614; String
    + double &#8614; String
    + Object &#8614; String
  + Primitive To Primitive Conversions
    + boolean &#8614; boolean
    + boolean &#8614; char
    + boolean &#8614; byte
    + boolean &#8614; short
    + boolean &#8614; int
    + boolean &#8614; long
    + boolean &#8614; float
    + boolean &#8614; double
    + char &#8614; boolean
    + char &#8614; char
    + char &#8614; byte
    + char &#8614; short
    + char &#8614; int
    + char &#8614; long
    + char &#8614; float
    + char &#8614; double
    + byte &#8614; boolean
    + byte &#8614; char
    + byte &#8614; byte
    + byte &#8614; short
    + byte &#8614; int
    + byte &#8614; long
    + byte &#8614; float
    + byte &#8614; double
    + short &#8614; boolean
    + short &#8614; char
    + short &#8614; byte
    + short &#8614; short
    + short &#8614; int
    + short &#8614; long
    + short &#8614; float
    + short &#8614; double
    + int &#8614; boolean
    + int &#8614; char
    + int &#8614; byte
    + int &#8614; short
    + int &#8614; int
    + int &#8614; long
    + int &#8614; float
    + int &#8614; double
    + long &#8614; boolean
    + long &#8614; char
    + long &#8614; byte
    + long &#8614; short
    + long &#8614; int
    + long &#8614; long
    + long &#8614; float
    + long &#8614; double
    + float &#8614; boolean
    + float &#8614; char
    + float &#8614; byte
    + float &#8614; short
    + float &#8614; int
    + float &#8614; long
    + float &#8614; float
    + float &#8614; double
    + double &#8614; boolean
    + double &#8614; char
    + double &#8614; byte
    + double &#8614; short
    + double &#8614; int
    + double &#8614; long
    + double &#8614; float
    + double &#8614; double
+ Resolution of a Predefined Conversion (X => T):
  + Sort the predefined conversions from most specific to most abstract.
  + Remove the conversions where the output is not type T.
  + For each remaining conversion C:
    + If X is a subtype of the input type of C, then C is the conversion to select.
  + No predefined conversion was found, so the conversion is a cast.
+ A cast (X => T) is impossible when both:
  + X is not a supertype of T
  + X is not a subtype of T
+ Return Type: <i>type</i>
+ Return the result of the predefined conversion or cast.
+ If the conversion is an unsuccessful cast, then null is returned.

## Static Checks

+ [NO_SUCH_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#NO_SUCH_TYPE): The type specified by <i>type</i> must exist.
+ [INACCESSIBLE_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#INACCESSIBLE_TYPE): The type specified by <i>type</i> must be accessible.
+ [IMPOSSIBLE_CONVERSION](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#IMPOSSIBLE_CONVERSION): The conversion must be either predefined or a valid cast.

## Example

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    # Case: Predefined Conversion (int ==> char). 
    val value1 = 65 as char;

    # Generalized Input
    val input = "Mars" is Object;

    # Case: Successful Cast
    val value2 = input as CharSequence;

    # Case: Unsuccessful Cast
    val value3 = input as List;

    # Print the results.
    F::println("Case 1: " .. value1);
    F::println("Case 2: " .. value2);
    F::println("Case 3: " .. value3);
}
```

**Output:**

```plain
Case 1: A
Case 2: Mars
Case 3: null
```

