# Import Directive

## Summary

An import-directive simplifies access to a type within the enclosing module.

## Syntax

<div class="syntax">
<b>import</b> <i><a href="Type_Specifier.md">type</a></i> ;<br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.ImportDirective](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/ImportDirective.html)

## Details

+ After importation, the imported type is accessible using only its simple-name.
+ Import Directives are processed by the compiler linearly from the top of a module to the bottom.
  + If an import directive <i>X</i> occurs after an import directive <i>Y</i> and <i>X</i> and <i>Y</i> collide, then <i>X</i> overrides <i>Y</i>.
    + Two import-directives collide, if the simple-names of the types they import are the same.
  + If an import-directive <i>X</i> collides with a type <i>T</i> declared in the enclosing module, then <i>T</i> overrides <i>X</i>.
    + An import-directive <i>X</i> collides with a type <i>T</i>, if the simple-name of <i>X</i> is the same as that of <i>T</i>.
+ The type of the enclosing module is automatically imported twice.
  + First, the module's type is imported using its simple name.
  + Second, the module's type is imported using the name <i>My</i>.
+ **Predefined Imports:**
  + [DefinedFunctor](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/DefinedFunctor.html)
  + [Delegate](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Delegate.html)
  + [Functor](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Functor.html)
  + [Lambda](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Lambda.html)
  + [Local](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Local.html)
  + [LocalsMap](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/LocalsMap.html)
  + [Module](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Module.html)
  + [ModuleInfo](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/ModuleInfo.html)
  + [Record](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Record.html)
  + [TypedFunctor](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/TypedFunctor.html)
  + [Author](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/annotations/Author.html)
  + [Hide](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/annotations/Hide.html)
  + [Infer](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/annotations/Infer.html)
  + [Setup](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/annotations/Setup.html)
  + [Start](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/annotations/Start.html)
  + [Sync](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/annotations/Sync.html)
  + [Autumn](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/compiler/Autumn.html)
  + [AssertionFailedException](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/exceptions/AssertionFailedException.html)
  + [AssumptionFailedException](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/exceptions/AssumptionFailedException.html)
  + [CheckedException](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/exceptions/CheckedException.html)
  + [DispatchException](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/exceptions/DispatchException.html)
  + [UnexpectedTerminationException](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/exceptions/UnexpectedTerminationException.html)
  + [Bitwise](https://mackenzie-high.github.io/autumn/javadoc/autumn/util/Bitwise.html)
  + [F](https://mackenzie-high.github.io/autumn/javadoc/autumn/util/F.html)
  + [FileIO](https://mackenzie-high.github.io/autumn/javadoc/autumn/util/FileIO.html)
  + [Action](https://mackenzie-high.github.io/autumn/javadoc/autumn/util/functors/Action.html)
  + [Function0](https://mackenzie-high.github.io/autumn/javadoc/autumn/util/functors/Function0.html)
  + [Function1](https://mackenzie-high.github.io/autumn/javadoc/autumn/util/functors/Function1.html)
  + [Function2](https://mackenzie-high.github.io/autumn/javadoc/autumn/util/functors/Function2.html)
  + [Function3](https://mackenzie-high.github.io/autumn/javadoc/autumn/util/functors/Function3.html)
  + [Function4](https://mackenzie-high.github.io/autumn/javadoc/autumn/util/functors/Function4.html)
  + [Function5](https://mackenzie-high.github.io/autumn/javadoc/autumn/util/functors/Function5.html)
  + [Function6](https://mackenzie-high.github.io/autumn/javadoc/autumn/util/functors/Function6.html)
  + [Function7](https://mackenzie-high.github.io/autumn/javadoc/autumn/util/functors/Function7.html)
  + [Function8](https://mackenzie-high.github.io/autumn/javadoc/autumn/util/functors/Function8.html)
  + [Function9](https://mackenzie-high.github.io/autumn/javadoc/autumn/util/functors/Function9.html)
  + [Ordering](https://mackenzie-high.github.io/autumn/javadoc/autumn/util/functors/Ordering.html)
  + [Predicate](https://mackenzie-high.github.io/autumn/javadoc/autumn/util/functors/Predicate.html)
  + [ProxyHandler](https://mackenzie-high.github.io/autumn/javadoc/autumn/util/functors/ProxyHandler.html)
  + [MalformedTestException](https://mackenzie-high.github.io/autumn/javadoc/autumn/util/test/MalformedTestException.html)
  + [Test](https://mackenzie-high.github.io/autumn/javadoc/autumn/util/test/Test.html)
  + [TestCase](https://mackenzie-high.github.io/autumn/javadoc/autumn/util/test/TestCase.html)
  + [TestResult](https://mackenzie-high.github.io/autumn/javadoc/autumn/util/test/TestResult.html)
  + [TestResults](https://mackenzie-high.github.io/autumn/javadoc/autumn/util/test/TestResults.html)
  + [Tester](https://mackenzie-high.github.io/autumn/javadoc/autumn/util/test/Tester.html)
  + [UnitTester](https://mackenzie-high.github.io/autumn/javadoc/autumn/util/test/UnitTester.html)
  + [ArithmeticException](https://docs.oracle.com/javase/7/docs/api/java/lang/ArithmeticException.html)
  + [Boolean](https://docs.oracle.com/javase/7/docs/api/java/lang/Boolean.html)
  + [Byte](https://docs.oracle.com/javase/7/docs/api/java/lang/Byte.html)
  + [CharSequence](https://docs.oracle.com/javase/7/docs/api/java/lang/CharSequence.html)
  + [Character](https://docs.oracle.com/javase/7/docs/api/java/lang/Character.html)
  + [Class](https://docs.oracle.com/javase/7/docs/api/java/lang/Class.html)
  + [ClassCastException](https://docs.oracle.com/javase/7/docs/api/java/lang/ClassCastException.html)
  + [Comparable](https://docs.oracle.com/javase/7/docs/api/java/lang/Comparable.html)
  + [Double](https://docs.oracle.com/javase/7/docs/api/java/lang/Double.html)
  + [Enum](https://docs.oracle.com/javase/7/docs/api/java/lang/Enum.html)
  + [Exception](https://docs.oracle.com/javase/7/docs/api/java/lang/Exception.html)
  + [Float](https://docs.oracle.com/javase/7/docs/api/java/lang/Float.html)
  + [IllegalArgumentException](https://docs.oracle.com/javase/7/docs/api/java/lang/IllegalArgumentException.html)
  + [IllegalStateException](https://docs.oracle.com/javase/7/docs/api/java/lang/IllegalStateException.html)
  + [IndexOutOfBoundsException](https://docs.oracle.com/javase/7/docs/api/java/lang/IndexOutOfBoundsException.html)
  + [Integer](https://docs.oracle.com/javase/7/docs/api/java/lang/Integer.html)
  + [Iterable](https://docs.oracle.com/javase/7/docs/api/java/lang/Iterable.html)
  + [Long](https://docs.oracle.com/javase/7/docs/api/java/lang/Long.html)
  + [Math](https://docs.oracle.com/javase/7/docs/api/java/lang/Math.html)
  + [NegativeArraySizeException](https://docs.oracle.com/javase/7/docs/api/java/lang/NegativeArraySizeException.html)
  + [NullPointerException](https://docs.oracle.com/javase/7/docs/api/java/lang/NullPointerException.html)
  + [Number](https://docs.oracle.com/javase/7/docs/api/java/lang/Number.html)
  + [NumberFormatException](https://docs.oracle.com/javase/7/docs/api/java/lang/NumberFormatException.html)
  + [Object](https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html)
  + [RuntimeException](https://docs.oracle.com/javase/7/docs/api/java/lang/RuntimeException.html)
  + [Short](https://docs.oracle.com/javase/7/docs/api/java/lang/Short.html)
  + [String](https://docs.oracle.com/javase/7/docs/api/java/lang/String.html)
  + [StringBuilder](https://docs.oracle.com/javase/7/docs/api/java/lang/StringBuilder.html)
  + [System](https://docs.oracle.com/javase/7/docs/api/java/lang/System.html)
  + [Throwable](https://docs.oracle.com/javase/7/docs/api/java/lang/Throwable.html)
  + [UnsupportedOperationException](https://docs.oracle.com/javase/7/docs/api/java/lang/UnsupportedOperationException.html)
  + [Annotation](https://docs.oracle.com/javase/7/docs/api/java/lang/annotation/Annotation.html)
  + [BigDecimal](https://docs.oracle.com/javase/7/docs/api/java/math/BigDecimal.html)
  + [BigInteger](https://docs.oracle.com/javase/7/docs/api/java/math/BigInteger.html)
  + [ArrayList](https://docs.oracle.com/javase/7/docs/api/java/util/ArrayList.html)
  + [Collection](https://docs.oracle.com/javase/7/docs/api/java/util/Collection.html)
  + [Collections](https://docs.oracle.com/javase/7/docs/api/java/util/Collections.html)
  + [HashMap](https://docs.oracle.com/javase/7/docs/api/java/util/HashMap.html)
  + [HashSet](https://docs.oracle.com/javase/7/docs/api/java/util/HashSet.html)
  + [Iterator](https://docs.oracle.com/javase/7/docs/api/java/util/Iterator.html)
  + [LinkedList](https://docs.oracle.com/javase/7/docs/api/java/util/LinkedList.html)
  + [List](https://docs.oracle.com/javase/7/docs/api/java/util/List.html)
  + [Map](https://docs.oracle.com/javase/7/docs/api/java/util/Map.html)
  + [NoSuchElementException](https://docs.oracle.com/javase/7/docs/api/java/util/NoSuchElementException.html)
  + [Random](https://docs.oracle.com/javase/7/docs/api/java/util/Random.html)
  + [Set](https://docs.oracle.com/javase/7/docs/api/java/util/Set.html)
  + [TreeMap](https://docs.oracle.com/javase/7/docs/api/java/util/TreeMap.html)
  + [TreeSet](https://docs.oracle.com/javase/7/docs/api/java/util/TreeSet.html)

## Example 1

**Source Code:**

```plain
module Main in examples;

import java.util.zip.Deflater;

@Start
defun main (args : String[]) : void
{
    # Since the Deflator type was imported,
    # it can be used using only its simple-name.
    
    val name = (class Deflater).getName();

    F::println(name);
}
```

**Output:**

```plain
java.util.zip.Deflater
```

## Example 2

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    # This example shows that some types, 
    # such as ArrayList, are already imported. 
    
    val chickens = new ArrayList();

    chickens.add("Chicky Jr.");
    chickens.add("Picky Jr.");
    chickens.add("Sikorsky");
    chickens.add("Lucky");

    F::println(chickens);
}
```

**Output:**

```plain
[Chicky Jr., Picky Jr., Sikorsky, Lucky]
```

## Example 3

**Source Code:**

```plain
module Enterprise in starships;

@Start
defun main (args : String[]) : void
{
    # This example shows that the enclosing module's type
    # is imported using both its simple-name and the name My.
    
    F::println((class My).getName());
    F::println((class Enterprise).getName());
}
```

**Output:**

```plain
starships.Enterprise
starships.Enterprise
```

## Example 4

**Source Code:**

```plain
module Main in execution;

import java.util.List;

tuple List ();

@Start
defun main (args : String[]) : void
{
    # This example shows that locally declared types override imports.

    val name = (class List).getName();

    F::println(name);
}
```

**Output:**

```plain
execution.List
```

