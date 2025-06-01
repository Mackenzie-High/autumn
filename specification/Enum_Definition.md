# Enum Definition

## Summary

An enum-definition creates a new enum-type in the enclosing package.

## Syntax

<div class="syntax">
@<i>annotation<sub>1</sub></i><br>
@<i>annotation<sub>2</sub></i><br>
@<i>annotation<sub>n</sub></i><br>
<b>enum</b> <i><a href="Name.md">name</a></i> ( <i><a href="Name.md">constant</a><sub>1</sub></i> , ... , <i><a href="Name.md">constant</a><sub>n</sub></i> ) ;<br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.EnumDefinition](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/EnumDefinition.html)

## Details

+ Regarding the enum-type T created by a definition:
  + T has the [EnumDefinition](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/internals/annotations/EnumDefinition.html) annotation applied directly to it.
  + T is both [public](https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/Modifier.html#PUBLIC) and [final](https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/Modifier.html#FINAL).
  + T is a subtype of class [Enum](https://docs.oracle.com/javase/7/docs/api/java/lang/Enum.html).
  + T does not have any direct superinterfaces.
  + For each enum-constant X in T, there is a field F in T such that:
    + The name of F is the name of X.
    + The type of F is T.
    + F is [public](https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/Modifier.html#PUBLIC), [static](https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/Modifier.html#STATIC), and [final](https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/Modifier.html#FINAL).
    + F is an enum-constant, according to reflection.
  + T declares two [public](https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/Modifier.html#PUBLIC) [static](https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/Modifier.html#STATIC) methods:
    + values() : T[]
      + This method creates an array of the enum-constants declared in T.
      + Each invocation of the method creates a new array instance.
      + The elements in the array are in declaration order.
    + valueOf(name : String) : T
      + This method retrieves an enum-constant based on its name.
      + This method throws a [NullPointerException](https://docs.oracle.com/javase/7/docs/api/java/lang/NullPointerException.html), if name is null.
      + This method throws a [IllegalArgumentException](https://docs.oracle.com/javase/7/docs/api/java/lang/IllegalArgumentException.html), if the named enum-constant cannot be found.
  + T inherits the following special methods from its supertypes.
    + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Enum.html#compareTo(java.lang.Enum)'>compareTo ([Enum](https://docs.oracle.com/javase/7/docs/api/java/lang/Enum.html))</a>
    + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Enum.html#compareTo(java.lang.Object)'>compareTo ([Object](https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html))</a>
    + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Enum.html#describeConstable()'>describeConstable ()</a>
    + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Enum.html#equals(java.lang.Object)'>equals ([Object](https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html))</a>
    + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#getClass()'>getClass ()</a>
    + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Enum.html#getDeclaringClass()'>getDeclaringClass ()</a>
    + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Enum.html#hashCode()'>hashCode ()</a>
    + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Enum.html#name()'>name ()</a>
    + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#notify()'>notify ()</a>
    + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#notifyAll()'>notifyAll ()</a>
    + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Enum.html#ordinal()'>ordinal ()</a>
    + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Enum.html#toString()'>toString ()</a>
    + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Enum.html#valueOf(java.lang.Class, java.lang.String)'>valueOf ([Class](https://docs.oracle.com/javase/7/docs/api/java/lang/Class.html), [String](https://docs.oracle.com/javase/7/docs/api/java/lang/String.html))</a>
    + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#wait()'>wait ()</a>
    + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#wait(long)'>wait (long)</a>
    + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#wait(long, int)'>wait (long, int)</a>

## Static Checks

+ [DUPLICATE_ANNOTATION](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#DUPLICATE_ANNOTATION): Each annotation in an annotation-list must be uniquely typed.
+ [DUPLICATE_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#DUPLICATE_TYPE): No two types can share the same descriptor.
+ [DUPLICATE_CONSTANT](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#DUPLICATE_CONSTANT): Enum constants cannot share their name.

## Example 1

**Source Code:**

```plain
module Main in examples;


enum City ( Paris, London, Rome );


@Start
defun main (args : String[]) : void
{
    My::print((field City::Paris));

    My::print((field City::London));

    My::print((field City::Rome));
}


defun print (constant : Enum) : void
{
    F::println("Name: " .. constant.name());
    F::println("Ordinal: " .. constant.ordinal());
    F::println();
}
```

**Output:**

```plain
Name: Paris
Ordinal: 0

Name: London
Ordinal: 1

Name: Rome
Ordinal: 2
```

## Example 2

**Source Code:**

```plain
module Main in examples;


enum City ( Paris, London, Rome );


@Start
defun main (args : String[]) : void
{
    My::print(City::valueOf("Paris"));

    My::print(City::valueOf("London"));

    My::print(City::valueOf("Rome"));
}


defun print (constant : Enum) : void
{
    F::println("Name: " .. constant.name());
    F::println("Ordinal: " .. constant.ordinal());
    F::println();
}
```

**Output:**

```plain
Name: Paris
Ordinal: 0

Name: London
Ordinal: 1

Name: Rome
Ordinal: 2
```

## Example 3

**Source Code:**

```plain
module Main in examples;


enum City ( Paris, London, Rome );


@Start
defun main (args : String[]) : void
{
    val array = City::values();

    for(i = 0; i < F::len(array); i + 1)
    {
        val constant = F::get(array, i) is Enum;

        My::print(constant);
    }
}


defun print (constant : Enum) : void
{
    F::println("Name: " .. constant.name());
    F::println("Ordinal: " .. constant.ordinal());
    F::println();
}
```

**Output:**

```plain
Name: Paris
Ordinal: 0

Name: London
Ordinal: 1

Name: Rome
Ordinal: 2
```

