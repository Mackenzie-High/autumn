## Summary

This function counts the value(s) stored in an Autumn-style annotation.

## Signature

len (anno : [Annotation](https://docs.oracle.com/javase/7/docs/api/java/lang/annotation/Annotation.html)) : int

## Formals

+ Parameter annocontains the values to count, if any.

## Returns

Return the number of values that are stored in <i>anno</i>.

## Throws

+ [NullPointerException](https://docs.oracle.com/javase/7/docs/api/java/lang/NullPointerException.html), if <i>anno</i> is null.

## Example

**Source Code:**

```plain
@Authors(%22Herman%22, %22Lillian%22, %22Grandpa%22)%0Amodule Main in execution;%0A%0Aannotation Authors;%0A%0A@Start%0Adefun main (args : String[]) : void%0A{%0A    val anno = F::findAnnotation(class Main, class Authors);%0A%0A    val size = F::len(anno);%0A%0A    F::println(%22Size = %22 .. size);%0A}
```

**Output:**

```plain
Size = 3
```

