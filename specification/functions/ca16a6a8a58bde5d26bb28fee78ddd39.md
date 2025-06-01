## Summary

This function creates an iterable whose iterator can iterate over the names of the entries in a record.

## Signature

iter (input : [Record](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Record.html)) : [List](https://docs.oracle.com/javase/7/docs/api/java/util/List.html)

## Formals

+ Parameter <i>input</i> is the record itself.

## Returns

Return an unmodifiable list containing the names of the entries in the <i>input</i>.

## Throws

+ [NullPointerException](https://docs.oracle.com/javase/7/docs/api/java/lang/NullPointerException.html), if <i>input</i> is null.

## Example

**Source Code:**

```plain
module Main in execution;

tuple Capital (city : String, country : String);

@Start
defun main (args : String[]) : void
{
    val capital = new Capital("Ottawa", "Canada");

    val result = F::iter(capital);

    foreach (key : String in result)
    {
        val value = F::get(capital, key);

        F::println("Key: " .. key);
        F::println("Value: " .. value);
        F::println();
    }
}
```

**Output:**

```plain
Key: city
Value: Ottawa

Key: country
Value: Canada
```

