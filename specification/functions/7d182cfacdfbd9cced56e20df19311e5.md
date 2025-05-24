## Summary

This function retrieves the value of an array element.

## Signature

get (array : float[], index : int) : float

## Formals

+ Parameter <i>array</i> is the array that contains the element.
+ Parameter <i>index</i> is the location of the element in the array.

## Returns

Return the value stored in the <i>array</i> element that is identified by the given <i>index</i>.

## Throws

+ [NullPointerException](https://docs.oracle.com/javase/7/docs/api/java/lang/NullPointerException.html), if <i>array</i> is null.
+ [IndexOutOfBoundsException](https://docs.oracle.com/javase/7/docs/api/java/lang/IndexOutOfBoundsException.html), if <i>index</i> is out of bounds.

## Example

**Source Code:**

```plain
module Main in execution;

@Start
defun main (args : String[]) : void
{
    # Create a new array. 
    val array = F::newArray((class float), 5) is float[];

    # Compute the length of the array. 
    val length = F::len(array);

    # Print the length of the array. 
    F::println("Length = " .. length);

    # Set the values of the elements in the array.
    F::set(array, 0, 10.0F);
    F::set(array, 1, 20.0F);
    F::set(array, 2, 30.0F);
    F::set(array, 3, 40.0F);
    F::set(array, 4, 50.0F);

    # Retrieve the values of the elements in the array.
    val value0 = F::get(array, 0);
    val value1 = F::get(array, 1);
    val value2 = F::get(array, 2);
    val value3 = F::get(array, 3);
    val value4 = F::get(array, 4);

    # Print the aforesaid values. 
    F::println("Value[0] = " .. value0);
    F::println("Value[1] = " .. value1);
    F::println("Value[2] = " .. value2);
    F::println("Value[3] = " .. value3);
    F::println("Value[4] = " .. value4);
}
```

**Output:**

```plain
Length = 5
Value[0] = 10.0
Value[1] = 20.0
Value[2] = 30.0
Value[3] = 40.0
Value[4] = 50.0
```

