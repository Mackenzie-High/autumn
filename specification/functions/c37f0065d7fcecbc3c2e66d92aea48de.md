## Summary

This function retrieves the <i>value</i> part of an entry in a record.

## Signature

get (record : [Record](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Record.html), index : int) : [Object](https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html)

## Formals

+ Parameter recordcontains the entry.
+ Parameter indexis the index of the entry.

## Returns

Return the value stored in the <i>record</i> entry that is identified by the given <i>index</i>.

## Throws

+ [NullPointerException](https://docs.oracle.com/javase/7/docs/api/java/lang/NullPointerException.html), if <i>record</i> is null.
+ [IndexOutOfBoundsException](https://docs.oracle.com/javase/7/docs/api/java/lang/IndexOutOfBoundsException.html), if <i>index</i> is out of bounds.

## Example

**Source Code:**

```plain
module Main in execution;%0A%0Atuple Volcano (name : String, range : String, elevation : int);%0A%0A@Start%0Adefun main (args : String[]) : void%0A{%0A    val volcano = new Volcano(%22Mount Baker%22, %22Cascade%22, 10_781);%0A%0A    F::println(%22Name: %22 .. F::get(volcano, 0));%0A    F::println(%22Range: %22 .. F::get(volcano, 1));%0A    F::println(%22Elevation: %22 .. F::get(volcano, 2));%0A}
```

**Output:**

```plain
Name: Mount Baker%0ARange: Cascade%0AElevation: 10781
```

