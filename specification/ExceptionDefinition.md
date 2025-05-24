# Exception Definition

## Summary

An exception-definition creates a new exception-type in the enclosing package.

## Syntax

<div id="syntax">
@<i>annotation<sub>1</sub></i><br>
@<i>annotation<sub>2</sub></i><br>
@<i>annotation<sub>n</sub></i><br>
<span class=\"keyword\">exception</span> <i>[name](ConstructPage.html?construct=Name)</i> <span class=\"keyword\">extends</span> <i>[super](ConstructPage.html?construct=TypeSpecifier)</i> ;<br>
</div>

## AST Class

autumn.lang.compiler.ast.nodes.ExceptionDefinition

## Details

+ Regarding the exception-type T created by a definition:
  + T is a form of class-type.
  + T has the [ExceptionDefinition](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/internals/annotations/ExceptionDefinition.html) annotation applied directly to it.
  + T is [public](https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/Modifier.html#PUBLIC).
  + T must be a subtype of [Throwable](https://docs.oracle.com/javase/7/docs/api/java/lang/Throwable.html).
  + T inherits all the [public](https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/Modifier.html#PUBLIC) constructors of its direct supertype.
    + Really, T declares equivalent constructors that simply invoke the super constructors.
    + Consequently, T is not instantiatable, if no constructors are actually inherited.
  + T does not have any direct superinterfaces.
  + T does not directly declare any fields or methods.
    + T inherits all the non [private](https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/Modifier.html#PRIVATE) fields of its supertypes.
    + T inherits all the non [private](https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/Modifier.html#PRIVATE) methods of its supertypes.
    + In particular, T inherits the following special methods from its supertypes.
      + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Throwable.html#addSuppressed(java.lang.Throwable)'>addSuppressed ([Throwable](https://docs.oracle.com/javase/7/docs/api/java/lang/Throwable.html))</a>
      + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#equals(java.lang.Object)'>equals ([Object](https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html))</a>
      + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Throwable.html#fillInStackTrace()'>fillInStackTrace ()</a>
      + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Throwable.html#getCause()'>getCause ()</a>
      + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#getClass()'>getClass ()</a>
      + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Throwable.html#getLocalizedMessage()'>getLocalizedMessage ()</a>
      + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Throwable.html#getMessage()'>getMessage ()</a>
      + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Throwable.html#getStackTrace()'>getStackTrace ()</a>
      + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Throwable.html#getSuppressed()'>getSuppressed ()</a>
      + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#hashCode()'>hashCode ()</a>
      + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Throwable.html#initCause(java.lang.Throwable)'>initCause ([Throwable](https://docs.oracle.com/javase/7/docs/api/java/lang/Throwable.html))</a>
      + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#notify()'>notify ()</a>
      + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#notifyAll()'>notifyAll ()</a>
      + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Throwable.html#printStackTrace()'>printStackTrace ()</a>
      + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Throwable.html#printStackTrace(java.io.PrintStream)'>printStackTrace ([PrintStream](https://docs.oracle.com/javase/7/docs/api/java/io/PrintStream.html))</a>
      + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Throwable.html#printStackTrace(java.io.PrintWriter)'>printStackTrace ([PrintWriter](https://docs.oracle.com/javase/7/docs/api/java/io/PrintWriter.html))</a>
      + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Throwable.html#setStackTrace([Ljava.lang.StackTraceElement;)'>setStackTrace ([StackTraceElement](https://docs.oracle.com/javase/7/docs/api/java/lang/StackTraceElement.html)[])</a>
      + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Throwable.html#toString()'>toString ()</a>
      + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#wait()'>wait ()</a>
      + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#wait(long)'>wait (long)</a>
      + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#wait(long, int)'>wait (long, int)</a>

## Static Checks

[DUPLICATE_ANNOTATION, Each annotation in an annotation-list must be uniquely typed., null]
[DUPLICATE_TYPE, No two types can share the same descriptor., null]
[NO_SUCH_TYPE, The type specified by <i>super</i> must exist., null]
[INACCESSIBLE_TYPE, The type specified by <i>super</i> must be accessible., null]
[EXPECTED_CLASS_TYPE, The type of <i>super</i> must be a class-type., null]
[EXPECTED_THROWABLE, The <i>super</i> must be a subtype of [Throwable](https://docs.oracle.com/javase/7/docs/api/java/lang/Throwable.html)., null]
[CIRCULAR_INHERITANCE, The new type cannot be a subtype of itself either directly or indirectly., null]

## Example

**Code:**

```plain
module Main in examples;

exception Apocalypse extends RuntimeException;

exception Apophis99942 extends Apocalypse;

@Start
defun main (args : String[]) : void
{
    try
    {
        F::println("Oh look, a meteor?");

        throw new Apophis99942("Oh no, an asteroid!");

        F::println("The world survived!");
    }
    catch (problem : Apocalypse)
    {
        F::println(problem.getMessage());
        F::println("Ah well, back to the stone age.");
    }
}
```

**Output:**

```plain
Oh look, a meteor?
Oh no, an asteroid!
Ah well, back to the stone age.
```

