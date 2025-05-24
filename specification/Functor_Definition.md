# Functor Definition

## Summary

A functor-definition creates a new functor-type in the enclosing package.

## Syntax

<div class="syntax">
@<i>annotation<sub>1</sub></i><br>
@<i>annotation<sub>2</sub></i><br>
@<i>annotation<sub>n</sub></i><br>
<b>functor</b> <i><a href="Name.md">name</a></i> ( <i><a href="Formal_Parameter.md">param</a><sub>1</sub></i> , ... , <i><a href="Formal_Parameter.md">param</a><sub>n</sub></i> ) : <i><a href="TypeSpecifier.md">return-type</a></i> ;<br>
<hr><br>
@<i>annotation<sub>1</sub></i><br>
@<i>annotation<sub>2</sub></i><br>
@<i>annotation<sub>n</sub></i><br>
<b>functor</b> <i><a href="Name.md">name</a></i> ( <i><a href="Formal_Parameter.md">param</a><sub>1</sub></i> , ... , <i><a href="Formal_Parameter.md">param</a><sub>n</sub></i> ) : <i><a href="TypeSpecifier.md">return-type</a></i> <b>extends</b> </i><a href="TypeSpecifier.md">super</a></i> ;<br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.FunctorDefinition](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/FunctorDefinition.html)

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

## Static Checks

+ [DUPLICATE_ANNOTATION](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#DUPLICATE_ANNOTATION): Each annotation in an annotation-list must be uniquely typed.
+ [DUPLICATE_ANNOTATION](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#DUPLICATE_ANNOTATION): Each annotation in an annotation-list must be uniquely typed.
+ [DUPLICATE_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#DUPLICATE_TYPE): No two types can share the same descriptor.
+ [NO_SUCH_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#NO_SUCH_TYPE): The type specified by <i>param<sub>i</sub></i> must exist.
+ [INACCESSIBLE_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#INACCESSIBLE_TYPE): The type specified by <i>param<sub>i</sub></i> must be accessible.
+ [EXPECTED_VARIABLE_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#EXPECTED_VARIABLE_TYPE): The type of param<sub>i</sub> must be a variable-type.
+ [NO_SUCH_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#NO_SUCH_TYPE): The type specified by <i>return-type</i> must exist.
+ [INACCESSIBLE_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#INACCESSIBLE_TYPE): The type specified by <i>return-type</i> must be accessible.
+ [NO_SUCH_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#NO_SUCH_TYPE): The type specified by <i>super</i> must exist.
+ [INACCESSIBLE_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#INACCESSIBLE_TYPE): The type specified by <i>super</i> must be accessible.
+ [EXPECTED_CLASS_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#EXPECTED_CLASS_TYPE): The type of <i>super</i> must be a class-type.
+ [EXPECTED_DEFINED_FUNCTOR_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#EXPECTED_DEFINED_FUNCTOR_TYPE): The type of <i>super</i> must have the FunctorDefinition annotation applied directly to it.
+ [CIRCULAR_INHERITANCE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#CIRCULAR_INHERITANCE): The new type cannot be a subtype of itself either directly or indirectly.
+ [COVARIANCE_VIOLATION](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#COVARIANCE_VIOLATION): The subtyping requirements must be obeyed.

## Example

**Source Code:**

```plain
module Main in examples;

functor Calculation (x : int) : int;

@Start
defun main (args : String[]) : void
{
    # Create a function object that refers to function square. 
    delegate p : Calculation => My::square;

    # Create a function object that refers to function cube. 
    delegate q : Calculation => My::cube;

    for (i = 0; i < 10; i + 1)
    {
        # Perform a calculation using the first function object.
        val x = My::compute(p, i);

        # Perform a calculation using the second function object.
        val y = My::compute(q, i);

        # Print the results.
        F::println([i, x, y]);
    }
}

defun compute (function : Calculation, 
               value : int) : int
{
    return function.invoke(value);
}

defun square (x : int) : int
{
    return x * x;
}

defun cube (x : int) : int
{
    return x * x * x;
}
```

**Output:**

```plain
[0, 0, 0]
[1, 1, 1]
[2, 4, 8]
[3, 9, 27]
[4, 16, 64]
[5, 25, 125]
[6, 36, 216]
[7, 49, 343]
[8, 64, 512]
[9, 81, 729]
```

