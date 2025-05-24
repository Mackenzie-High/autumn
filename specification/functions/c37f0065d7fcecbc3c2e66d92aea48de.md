## Summary

This function retrieves the <i>value</i> part of an entry in a record.

## Signature

get (record : [Record](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Record.html), index : int) : [Object](https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html)

## Formals

Parameterrecordcontains the entry.
Parameterindexis the index of the entry.

## Returns

Return the value stored in the <i>record</i> entry that is identified by the given <i>index</i>.

## Throws

[NullPointerException](https://docs.oracle.com/javase/7/docs/api/java/lang/NullPointerException.html)if <i>record</i> is null.
[IndexOutOfBoundsException](https://docs.oracle.com/javase/7/docs/api/java/lang/IndexOutOfBoundsException.html)if <i>index</i> is out of bounds.

