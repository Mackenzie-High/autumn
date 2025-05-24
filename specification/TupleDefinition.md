# Tuple Definition

## Summary

A tuple-definition creates a new tuple-type in the enclosing package.

## Syntax

```plain
@<i>annotation<sub>1</sub></i>
@<i>annotation<sub>2</sub></i>
@<i>annotation<sub>n</sub></i>
<span class=\"keyword\">tuple</span> <i>[name](ConstructPage.html?construct=Name)</i> ( <i>[element](ConstructPage.html?construct=Element)<sub>1</sub></i> , ... , <i>[element](ConstructPage.html?construct=Element)<sub>n</sub></i> ) ;
<hr class=&#92%22syntax-hr&#92%22>
@<i>annotation<sub>1</sub></i>
@<i>annotation<sub>2</sub></i>
@<i>annotation<sub>n</sub></i>
<span class=\"keyword\">tuple</span> <i>[name](ConstructPage.html?construct=Name)</i> ( <i>[element](ConstructPage.html?construct=Element)<sub>1</sub></i> , ... , <i>[element](ConstructPage.html?construct=Element)<sub>n</sub></i> ) <span class=\"keyword\">extends</span> <i>[super](ConstructPage.html?construct=TypeSpecifier)<sub>1</sub></i> & ... & <i>[super](ConstructPage.html?construct=TypeSpecifier)<sub>n</sub></i> ;
```

## AST Class

autumn.lang.compiler.ast.nodes.TupleDefinition

## Details
+ A tuple is an immutable user-defined datatype.
+ Regarding the tuple-type T created by a definition:
  + T is form of class-type.
  + T has the [TupleDefinition](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/internals/annotations/TupleDefinition.html) annotation applied directly to it.
  + T is both [public](https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/Modifier.html#PUBLIC) and [final](https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/Modifier.html#FINAL).
  + T is a subtype of interface [Record](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Record.html).
  + T is a subtype of class [AbstractRecord](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/internals/AbstractRecord.html).
  + T inherits all the elements that are declared in its supertypes.
  + Because T is a tuple-type, there must exist an explicit declaration <i>element<sub>i</sub></i> in T for every element.
    + As a consequence, a tuple imposes a user-defined total-ordering on its elements.
      + The order of the elements in a tuple is the same as in the list of elements.
      + On the other hand, the order of the elements in a struct is lexicographical.
  + T declares the following methods and constructors:
    + Let C denote the sole constructor declared by T:
      + C creates an immutable instance of T.
      + C takes one formal-parameter P for each element E.
        + P's static-type is the static-type of the element E.
        + C will assign the value of P to element E in the new instance.
      + The order of the formal-parameters in C is the same as the order of the elements in T.
    + For each element E in T:
      + Let I represent an instance of T.
      + T contains a setter method S for element E.
        + The name of S is the name of E.
        + S takes a single formal-parameter P.
          + The static-type of P is the static-type of E.
        + S obtains a copy M of I.
        + S assigns the value of P to element E in instance M.
        + The return-type of S is T.
        + S returns M.
      + T contains a getter method G for element E.
        + The name of G is the name of E.
        + G does not take any formal-parameters.
        + The return-type of G is the static-type of element E.
        + G returns the value stored in element E in instance I.
    + T provides bridge setter methods for each inherited element E.
      + For X, where X is a supertype of T, such that X also declares E:
        + S : X is a bridge method in T.
        + S : T is invoked by the bridge method.
    + T provides bridge methods for method set(int, [Object](https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html)).
      + For X, where X is T or a supertype thereof, such that X is also a subtype of [Record](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Record.html):
        + set(int, [Object](https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html)) : X is a bridge method in T.
        + set(int, [Object](https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html)) : [Record](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Record.html) is invoked by the bridge method.
        + Although the bridge method must downcast the return-value, the cast always succeeds.
    + T provides a special static method instance() : T.
      + The method returns an instance of T in which each element is set to is default value.
      + The method always returns the same object.
    + T inherits the following special method declarations from its supertypes.
      + <a href='https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/internals/AbstractRecord.html#compareTo(autumn.lang.Record)'>compareTo ([Record](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Record.html))</a>
      + <a href='https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/internals/AbstractRecord.html#compareTo(java.lang.Object)'>compareTo ([Object](https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html))</a>
      + <a href='https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/internals/AbstractRecord.html#equals(java.lang.Object)'>equals ([Object](https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html))</a>
      + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Iterable.html#forEach(java.util.function.Consumer)'>forEach ([Consumer](https://docs.oracle.com/javase/7/docs/api/java/util/function/Consumer.html))</a>
      + <a href='https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/internals/AbstractRecord.html#get(int)'>get (int)</a>
      + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#getClass()'>getClass ()</a>
      + <a href='https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/internals/AbstractRecord.html#hashCode()'>hashCode ()</a>
      + <a href='https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/internals/AbstractRecord.html#isEmpty()'>isEmpty ()</a>
      + <a href='https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/internals/AbstractRecord.html#isStruct()'>isStruct ()</a>
      + <a href='https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/internals/AbstractRecord.html#isTuple()'>isTuple ()</a>
      + <a href='https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/internals/AbstractRecord.html#iterator()'>iterator ()</a>
      + <a href='https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/internals/AbstractRecord.html#keys()'>keys ()</a>
      + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#notify()'>notify ()</a>
      + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#notifyAll()'>notifyAll ()</a>
      + <a href='https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/internals/AbstractRecord.html#set(int, java.lang.Object)'>set (int, [Object](https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html))</a>
      + <a href='https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/internals/AbstractRecord.html#size()'>size ()</a>
      + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Iterable.html#spliterator()'>spliterator ()</a>
      + <a href='https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/internals/AbstractRecord.html#toString()'>toString ()</a>
      + <a href='https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/internals/AbstractRecord.html#types()'>types ()</a>
      + <a href='https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/internals/AbstractRecord.html#values()'>values ()</a>
      + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#wait()'>wait ()</a>
      + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#wait(long)'>wait (long)</a>
      + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#wait(long, int)'>wait (long, int)</a>

