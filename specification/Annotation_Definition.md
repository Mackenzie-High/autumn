# Annotation Definition

## Summary

An annotation-definition creates a new annotation-type in the enclosing package.

## Syntax

<div class="syntax">
@<i>annotation<sub>1</sub></i><br>
@<i>annotation<sub>2</sub></i><br>
@<i>annotation<sub>n</sub></i><br>
<b>annotation</b> <i><a href="Name.md">name</a></i> ;<br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.AnnotationDefinition](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/AnnotationDefinition.html)

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

## Static Checks

+ [DUPLICATE_ANNOTATION](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#DUPLICATE_ANNOTATION): Each annotation in an annotation-list must be uniquely typed.
+ [DUPLICATE_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#DUPLICATE_TYPE): No two types can share the same descriptor.

## Example 1

**Source Code:**

```plain
module Main in examples;

annotation Alien;

@Alien
@Start
defun main (args : String[]) : void
{
    val owner = (class Main);
    val name = "main";
    val params = [ (class String[]) ];

    val func = F::findMethod(owner, name, params);

    val applied = func.isAnnotationPresent((class Alien));

    F::println("@Alien Present = " .. applied);
}
```

**Output:**

```plain
@Alien Present = true
```

## Example 2

**Source Code:**

```plain
module Main in examples;

import java.lang.reflect.Modifier;

annotation Alien;

@Start
defun main (args : String[]) : void
{
    val klass = (class Alien);

    val modifiers = Modifier::toString(klass.getModifiers());

    F::println("Name = " .. klass.getName());

    F::println("Annotation? = " .. klass.isAnnotation());

    F::println("Modifiers = " .. modifiers);
}
```

**Output:**

```plain
Name = examples.Alien
Annotation? = true
Modifiers = public abstract interface
```

