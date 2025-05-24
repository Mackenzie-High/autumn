# Functor Definition

## Summary

A functor-definition creates a new functor-type in the enclosing package.

## Syntax

```plain
@<i>annotation<sub>1</sub></i>
@<i>annotation<sub>2</sub></i>
@<i>annotation<sub>n</sub></i>
<span class=\"keyword\">functor</span> <i>[name](ConstructPage.html?construct=Name)</i> ( <i>[param](ConstructPage.html?construct=Formal Parameter)<sub>1</sub></i> , ... , <i>[param](ConstructPage.html?construct=Formal Parameter)<sub>n</sub></i> ) : <i>[return-type](ConstructPage.html?construct=TypeSpecifier)</i> ;
<hr class=&#92%22syntax-hr&#92%22>
@<i>annotation<sub>1</sub></i>
@<i>annotation<sub>2</sub></i>
@<i>annotation<sub>n</sub></i>
<span class=\"keyword\">functor</span> <i>[name](ConstructPage.html?construct=Name)</i> ( <i>[param](ConstructPage.html?construct=Formal Parameter)<sub>1</sub></i> , ... , <i>[param](ConstructPage.html?construct=Formal Parameter)<sub>n</sub></i> ) : <i>[return-type](ConstructPage.html?construct=TypeSpecifier)</i> <span class=\"keyword\">extends</span> </i>[super](ConstructPage.html?construct=TypeSpecifier)</i> ;
```

## AST Class

autumn.lang.compiler.ast.nodes.FunctorDefinition

## Details
+ Regarding the functor-type T created by a functor-definition:
  + T is a form of class-type.
  + T has the [FunctorDefinition](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/internals/annotations/FunctorDefinition.html) annotation applied directly to it.
  + T is [public](https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/Modifier.html#PUBLIC).
  + T is a subtype of interface [DefinedFunctor](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/DefinedFunctor.html)
  + T is a subtype of interface [TypedFunctor](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/TypedFunctor.html)
  + T is a subtype of interface [Functor](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Functor.html)
  + T is a subtype of class [AbstractDefinedFunctor](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/internals/AbstractDefinedFunctor.html)
  + T is a subtype of the functor-type specified by <i>super</i>, if <i>super</i> is present.
  + T defines the following methods and constructors:
    + constructor: T ([TypedFunctor](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/TypedFunctor.html))
      + parameter - inner : [TypedFunctor](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/TypedFunctor.html)
      + This constructor creates a new instance of T that wraps another functor.
    + method: invoke (<i>parameter-types</i>) : <i>return-type</i>
      + This method provides a type-safe mechanism for invoking the functor.
      + This is the method that programmers will use most frequently.
    + method: parameterTypes () : [List](https://docs.oracle.com/javase/7/docs/api/java/util/List.html)&lt;[Class](https://docs.oracle.com/javase/7/docs/api/java/lang/Class.html)&gt;
      + This method creates a list containing the <i>parameter-types</i> as provided in the declaration.
    + method: returnType () : [Class](https://docs.oracle.com/javase/7/docs/api/java/lang/Class.html)
      + This method retrieves the <i>return-type</i> that was provided in the declaration.
    + method: inner () : [TypedFunctor](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/TypedFunctor.html)
      + This method retrieves the inner functor that was provided to the constructor.
    + method: apply ([ArgumentStack](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/internals/ArgumentStack.html)) : void
      + This method is the low-level method that handles invocations of the functor.
      + This method is not intended for direct use by programmers.
  + T is covariant in terms of a supertype S, iff either:
    + Ǝ <i>i</i> such that T.formals<sub>i</sub> is a proper subtype of S.formals<sub>i</sub>
    + T.return-type is a proper subtype of S.return-type
  + Subtyping Requirements:
    + Let S be any of the super functor-types of T.
    + T.formals.length must equal S.formals.length
    + T.formals<sub>i</sub> must be a subtype of S.formals<sub>i</sub> ∀ <i>i</i>
    + T.return-type must be a subtype of S.return-type
  + T inherits the following special method declarations from its supertypes.
    + <a href='https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Functor.html#apply(autumn.lang.internals.ArgumentStack)'>apply ([ArgumentStack](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/internals/ArgumentStack.html))</a>
    + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#equals(java.lang.Object)'>equals ([Object](https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html))</a>
    + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#getClass()'>getClass ()</a>
    + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#hashCode()'>hashCode ()</a>
    + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#notify()'>notify ()</a>
    + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#notifyAll()'>notifyAll ()</a>
    + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#toString()'>toString ()</a>
    + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#wait()'>wait ()</a>
    + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#wait(long)'>wait (long)</a>
    + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#wait(long, int)'>wait (long, int)</a>

