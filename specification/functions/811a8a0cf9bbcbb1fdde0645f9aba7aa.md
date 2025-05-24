## Summary

This function creates an unmodifiable map from another map.

## Signature

unmodifiable (original : [Map](https://docs.oracle.com/javase/7/docs/api/java/util/Map.html)) : [Map](https://docs.oracle.com/javase/7/docs/api/java/util/Map.html)

## Formals

+ Parameter originalis the data-structure to copy.

## Returns

Return an unmodifiable view of the <i>original</i>.

## Throws

+ [NullPointerException](https://docs.oracle.com/javase/7/docs/api/java/lang/NullPointerException.html), if <i>original</i> is null.

## Example

**Source Code:**

```plain
module Main in execution;%0A%0A@Start%0Adefun main (args : String[]) : void%0A{%0A    val original = new HashMap();%0A%0A    original.put(100, 13);%0A    original.put(200, 17);%0A%0A    val derived = F::unmodifiable(original is Map);%0A%0A    original.put(300, 19);%0A    original.put(400, 21);%0A%0A    F::println(original);%0A    F::println(derived);%0A}
```

**Output:**

```plain
{100=13, 200=17, 400=21, 300=19}%0A{100=13, 200=17, 400=21, 300=19}
```

