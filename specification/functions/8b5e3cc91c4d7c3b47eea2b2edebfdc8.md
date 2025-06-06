## Summary

This function resets the entries in a record to their default values.

## Signature

reset (record : [Record](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Record.html)) : [Record](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Record.html)

## Formals

+ Parameter <i>record</i> is the record to clear.

## Returns

Return a cleared copy of the <i>record</i>.

## Throws

+ [NullPointerException](https://docs.oracle.com/javase/7/docs/api/java/lang/NullPointerException.html), if <i>record</i> is null.

## Example

**Source Code:**

```plain
module Main in execution;

struct T (a : boolean,
          b : char,
          c : byte,
          d : short,
          e : int,
          f : long,
          g : float,
          h : double,
          i : Object);

@Start
defun main (args : String[]) : void
{
    var p = T::instance();
    p = p.a(true);
    p = p.b(65C);
    p = p.c(100B);
    p = p.d(200S);
    p = p.e(300);
    p = p.f(400L);
    p = p.g(500.0F);
    p = p.h(600.0);
    p = p.i("Venus");

    F::println(p);
    p = F::reset(p);
    F::println(p);
}
```

**Output:**

```plain
(true, A, 100, 200, 300, 400, 500.0, 600.0, Venus)
(false,  , 0, 0, 0, 0, 0.0, 0.0, null)
```

