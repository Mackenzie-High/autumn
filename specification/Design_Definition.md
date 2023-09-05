# Design Definition

## Summary

A design-definition creates a new design-type in the enclosing package.

## Syntax

<div class="syntax">
@<i>annotation<sub>1</sub></i><br>
@<i>annotation<sub>2</sub></i><br>
@<i>annotation<sub>n</sub></i><br>
<b>design</b> <i><a href="Name.md">name</a></i> ( <i><a href="Element.md">element</a><sub>1</sub></i> , ... , <i><a href="Element.md">element</a><sub>n</sub></i> ) ;<br>
<hr><br>
@<i>annotation<sub>1</sub></i><br>
@<i>annotation<sub>2</sub></i><br>
@<i>annotation<sub>n</sub></i><br>
<b>design</b> <i><a href="Name.md">name</a></i> ( <i><a href="Element.md">element</a><sub>1</sub></i> , ... , <i><a href="Element.md">element</a><sub>n</sub></i> ) <b>extends</b> <i><a href="Type_Specifier.md">super</a><sub>1</sub></i> & ... & <i><a href="Type_Specifier.md">super</a><sub>n</sub></i>;<br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.DesignDefinition](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/DesignDefinition.html)

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

## Static Checks

+ [DUPLICATE_ANNOTATION](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#DUPLICATE_ANNOTATION): Each annotation in an annotation-list must be uniquely typed.
+ [DUPLICATE_ANNOTATION](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#DUPLICATE_ANNOTATION): Each annotation in an annotation-list must be uniquely typed.
+ [DUPLICATE_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#DUPLICATE_TYPE): No two types can share the same descriptor.
+ [DUPLICATE_ELEMENT](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#DUPLICATE_ELEMENT): The <i>name</i> of each element must be unique within the definition itself.
+ [NO_SUCH_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#NO_SUCH_TYPE): The type specified by <i>element.type</i> must exist.
+ [INACCESSIBLE_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#INACCESSIBLE_TYPE): The type specified by <i>element.type</i> must be accessible.
+ [EXPECTED_VARIABLE_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#EXPECTED_VARIABLE_TYPE): The <i>type</i> of each <i>element</i> must be a variable-type.
+ [RETYPED_ELEMENT](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#RETYPED_ELEMENT): The type of an element must be the same in all the declarations of the element.
+ [NAME_CONFLICT](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#NAME_CONFLICT): The name of an element cannot also be the name of an inherited method.

## Example

**Source Code:**

```plain
module Main in execution;

design Taxable (income : int, rate : int);

design Human (name : String);

design Citizen () extends Human & Taxable;

struct Employee (title : String) extends Citizen;

@Start
defun main (args : String[]) : void
{
    var p = Employee::instance();

    p = p.name("Sarah");
    p = p.income(75_000);
    p = p.rate(30);
    p = p.title("Virologist");
    
    F::println (p);
}
```

**Output:**

```plain
(75000, Sarah, 30, Virologist)
```

