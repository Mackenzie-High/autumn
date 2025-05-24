# Annotation Definition

## Summary

An annotation-definition creates a new annotation-type in the enclosing package.

## Syntax

```plain
@<i>annotation<sub>1</sub></i>
@<i>annotation<sub>2</sub></i>
@<i>annotation<sub>n</sub></i>
<span class=\"keyword\">annotation</span> <i>[name](ConstructPage.html?construct=Name)</i> ;
```

## AST Class

autumn.lang.compiler.ast.nodes.AnnotationDefinition

## Details
+ Regarding the annotation-type T defined by a definition:
  + T has the [AnnotationDefinition](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/internals/annotations/AnnotationDefinition.html) annotation applied directly to it.
  + T is [public](https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/Modifier.html#PUBLIC).
  + T is a subtype of class [Object](https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html).
  + T is a subtype of interface [Annotation](https://docs.oracle.com/javase/7/docs/api/java/lang/annotation/Annotation.html).
  + T has a [RetentionPolicy](https://docs.oracle.com/javase/7/docs/api/java/lang/annotation/RetentionPolicy.html) of RUNTIME.
  + T does not declare any fields.
  + T does not declare any constructors.
  + T declares a single method.
    + The name of the method is 'value'.
    + The method does not take any formal-parameters.
    + The return-type of the method is [String](https://docs.oracle.com/javase/7/docs/api/java/lang/String.html)[].
    + The method is an annotation-method.
  + T inherits the following special methods from its supertypes.
    + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/annotation/Annotation.html#annotationType()'>annotationType ()</a>
    + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/annotation/Annotation.html#equals(java.lang.Object)'>equals ([Object](https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html))</a>
    + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/annotation/Annotation.html#hashCode()'>hashCode ()</a>
    + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/annotation/Annotation.html#toString()'>toString ()</a>

