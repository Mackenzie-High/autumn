# Design Definition

## Summary

A design-definition creates a new design-type in the enclosing package.

## Syntax

```plain
@<i>annotation<sub>1</sub></i>
@<i>annotation<sub>2</sub></i>
@<i>annotation<sub>n</sub></i>
<span class=\"keyword\">design</span> <i>[name](ConstructPage.html?construct=Name)</i> ( <i>[element](ConstructPage.html?construct=Element)<sub>1</sub></i> , ... , <i>[element](ConstructPage.html?construct=Element)<sub>n</sub></i> ) ;
<hr class=&#92%22syntax-hr&#92%22>
@<i>annotation<sub>1</sub></i>
@<i>annotation<sub>2</sub></i>
@<i>annotation<sub>n</sub></i>
<span class=\"keyword\">design</span> <i>[name](ConstructPage.html?construct=Name)</i> ( <i>[element](ConstructPage.html?construct=Element)<sub>1</sub></i> , ... , <i>[element](ConstructPage.html?construct=Element)<sub>n</sub></i> ) <span class=\"keyword\">extends</span> <i>[super](ConstructPage.html?construct=TypeSpecifier)<sub>1</sub></i> & ... & <i>[super](ConstructPage.html?construct=TypeSpecifier)<sub>n</sub></i>;
```

## AST Class

autumn.lang.compiler.ast.nodes.DesignDefinition

## Details
+ A design is an abstract record-style user-defined datatype.
+ Regarding the design-type T created by a definition:
  + T is form of interface-type.
  + T has the [DesignDefinition](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/internals/annotations/DesignDefinition.html) annotation applied directly to it.
  + T is both [public](https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/Modifier.html#PUBLIC) and [abstract](https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/Modifier.html#ABSTRACT).
  + T is a subtype of interface [Record](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Record.html).
  + T inherits all the elements that are declared in its supertypes.
  + T declares the following methods:
    + For each element E in T:
      + T contains a setter method S for element E.
        + The name of S is the name of E.
        + S takes a single formal-parameter P.
          + The static-type of P is the static-type of E.
        + The return-type of S is T.
      + T contains a getter method G for element E.
        + The name of G is the name of E.
        + G does not take any formal-parameters.
        + The return-type of G is the static-type of element E.
    + T provides bridge setter methods for each inherited element E.
      + For X, where X is a supertype of T, such that X also declares E:
        + S : T is a setter method declared in T.
    + T declares bridge methods for method set(int, [Object](https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html)).
      + For X, where X is T or a supertype thereof, such that X is also a subtype of [Record](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Record.html):
        + set(int, [Object](https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html)) : X is a bridge method in T.
    + T inherits the following method declarations from its supertypes.
      + <a href='https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Record.html#compareTo(autumn.lang.Record)'>compareTo ([Record](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Record.html))</a>
      + <a href='https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Record.html#compareTo(java.lang.Object)'>compareTo ([Object](https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html))</a>
      + <a href='https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Record.html#equals(java.lang.Object)'>equals ([Object](https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html))</a>
      + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Iterable.html#forEach(java.util.function.Consumer)'>forEach ([Consumer](https://docs.oracle.com/javase/7/docs/api/java/util/function/Consumer.html))</a>
      + <a href='https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Record.html#get(int)'>get (int)</a>
      + <a href='https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Record.html#hashCode()'>hashCode ()</a>
      + <a href='https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Record.html#isEmpty()'>isEmpty ()</a>
      + <a href='https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Record.html#isStruct()'>isStruct ()</a>
      + <a href='https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Record.html#isTuple()'>isTuple ()</a>
      + <a href='https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Record.html#iterator()'>iterator ()</a>
      + <a href='https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Record.html#keys()'>keys ()</a>
      + <a href='https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Record.html#set(int, java.lang.Object)'>set (int, [Object](https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html))</a>
      + <a href='https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Record.html#size()'>size ()</a>
      + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Iterable.html#spliterator()'>spliterator ()</a>
      + <a href='https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Record.html#toString()'>toString ()</a>
      + <a href='https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Record.html#types()'>types ()</a>
      + <a href='https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Record.html#values()'>values ()</a>

