## Summary

This function retrieves the <i>value</i> part of an entry in a record.

## Signature

get (record : [Record](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Record.html), index : int) : [Object](https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html)

## Formals

+ Parameter <i>record</i> contains the entry.
+ Parameter <i>index</i> is the index of the entry.

## Returns

Return the value stored in the <i>record</i> entry that is identified by the given <i>index</i>.

## Throws

+ [NullPointerException](https://docs.oracle.com/javase/7/docs/api/java/lang/NullPointerException.html), if <i>record</i> is null.
+ [IndexOutOfBoundsException](https://docs.oracle.com/javase/7/docs/api/java/lang/IndexOutOfBoundsException.html), if <i>index</i> is out of bounds.

## Example

**Source Code:**

```plain
module Main in execution;

tuple Volcano (name : String, range : String, elevation : int);

@Start
defun main (args : String[]) : void
{
    val volcano = new Volcano("Mount Baker", "Cascade", 10_781);

    F::println("Name: " .. F::get(volcano, 0));
    F::println("Range: " .. F::get(volcano, 1));
    F::println("Elevation: " .. F::get(volcano, 2));
}
```

**Output:**

```plain
Name: Mount Baker
Range: Cascade
Elevation: 10781
```

