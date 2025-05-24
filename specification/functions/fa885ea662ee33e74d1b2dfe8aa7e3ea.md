## Summary

This function counts the value(s) stored in an Autumn-style annotation.

## Signature

len (anno : [Annotation](https://docs.oracle.com/javase/7/docs/api/java/lang/annotation/Annotation.html)) : int

## Formals

+ Parameter <i>anno</i> contains the values to count, if any.

## Returns

Return the number of values that are stored in <i>anno</i>.

## Throws

+ [NullPointerException](https://docs.oracle.com/javase/7/docs/api/java/lang/NullPointerException.html), if <i>anno</i> is null.

## Example

**Source Code:**

```plain
@Authors("Herman", "Lillian", "Grandpa")
module Main in execution;

annotation Authors;

@Start
defun main (args : String[]) : void
{
    val anno = F::findAnnotation(class Main, class Authors);

    val size = F::len(anno);

    F::println("Size = " .. size);
}
```

**Output:**

```plain
Size = 3
```

