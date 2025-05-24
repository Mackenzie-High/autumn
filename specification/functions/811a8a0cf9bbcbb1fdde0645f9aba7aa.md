## Summary

This function creates an unmodifiable map from another map.

## Signature

unmodifiable (original : [Map](https://docs.oracle.com/javase/7/docs/api/java/util/Map.html)) : [Map](https://docs.oracle.com/javase/7/docs/api/java/util/Map.html)

## Formals

+ Parameter <i>original</i> is the data-structure to copy.

## Returns

Return an unmodifiable view of the <i>original</i>.

## Throws

+ [NullPointerException](https://docs.oracle.com/javase/7/docs/api/java/lang/NullPointerException.html), if <i>original</i> is null.

## Example

**Source Code:**

```plain
module Main in execution;

@Start
defun main (args : String[]) : void
{
    val original = new HashMap();

    original.put(100, 13);
    original.put(200, 17);

    val derived = F::unmodifiable(original is Map);

    original.put(300, 19);
    original.put(400, 21);

    F::println(original);
    F::println(derived);
}
```

**Output:**

```plain
{100=13, 200=17, 400=21, 300=19}
{100=13, 200=17, 400=21, 300=19}
```

