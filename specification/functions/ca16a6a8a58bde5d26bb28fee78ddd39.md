## Summary

This function creates an iterable whose iterator can iterate over the names of the entries in a record.

## Signature

iter (input : [Record](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Record.html)) : [List](https://docs.oracle.com/javase/7/docs/api/java/util/List.html)

## Formals

+ Parameter inputis the record itself.

## Returns

Return an unmodifiable list containing the names of the entries in the <i>input</i>.

## Throws

+ [NullPointerException](https://docs.oracle.com/javase/7/docs/api/java/lang/NullPointerException.html), if <i>input</i> is null.

## Example

**Source Code:**

```plain
module Main in execution;%0A%0Atuple Capital (city : String, country : String);%0A%0A@Start%0Adefun main (args : String[]) : void%0A{%0A    val capital = new Capital(%22Ottawa%22, %22Canada%22);%0A%0A    val result = F::iter(capital);%0A%0A    foreach (key : String in result)%0A    {%0A        val value = F::get(capital, key);%0A%0A        F::println(%22Key: %22 .. key);%0A        F::println(%22Value: %22 .. value);%0A        F::println();%0A    }%0A}
```

**Output:**

```plain
Key: city%0AValue: Ottawa%0A%0AKey: country%0AValue: Canada
```

