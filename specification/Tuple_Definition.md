# Tuple Definition

## Summary

A tuple-definition creates a new tuple-type in the enclosing package.

## Syntax

<div class="syntax">
@<i>annotation<sub>1</sub></i><br>
@<i>annotation<sub>2</sub></i><br>
@<i>annotation<sub>n</sub></i><br>
<b>tuple</b> <i><a href="Name.md">name</a></i> ( <i><a href="Element.md">element</a><sub>1</sub></i> , ... , <i><a href="Element.md">element</a><sub>n</sub></i> ) ;<br>
<hr><br>
@<i>annotation<sub>1</sub></i><br>
@<i>annotation<sub>2</sub></i><br>
@<i>annotation<sub>n</sub></i><br>
<b>tuple</b> <i><a href="Name.md">name</a></i> ( <i><a href="Element.md">element</a><sub>1</sub></i> , ... , <i><a href="Element.md">element</a><sub>n</sub></i> ) <b>extends</b> <i><a href="Type_Specifier.md">super</a><sub>1</sub></i> & ... & <i><a href="Type_Specifier.md">super</a><sub>n</sub></i> ;<br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.TupleDefinition](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/TupleDefinition.html)

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

## Static Checks

+ [DUPLICATE_ANNOTATION](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#DUPLICATE_ANNOTATION): Each annotation in an annotation-list must be uniquely typed.
+ [DUPLICATE_ANNOTATION](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#DUPLICATE_ANNOTATION): Each annotation in an annotation-list must be uniquely typed.
+ [DUPLICATE_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#DUPLICATE_TYPE): No two types can share the same descriptor.
+ [DUPLICATE_ELEMENT](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#DUPLICATE_ELEMENT): The <i>name</i> of each element must be unique within the enclosing definition.
+ [NO_SUCH_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#NO_SUCH_TYPE): The type specified by <i>element.type</i> must exist.
+ [INACCESSIBLE_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#INACCESSIBLE_TYPE): The type specified by <i>element.type</i> must be accessible.
+ [EXPECTED_VARIABLE_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#EXPECTED_VARIABLE_TYPE): The <i>type</i> of each <i>element</i> must be a variable-type.
+ [RETYPED_ELEMENT](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#RETYPED_ELEMENT): The type of an element must be the same in all the declarations of the element.
+ [TOTAL_ORDERING_REQUIRED](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#TOTAL_ORDERING_REQUIRED): A tuple must explicitly declare all of its elements, including inherited elements.
+ [NAME_CONFLICT](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#NAME_CONFLICT): The name of an element cannot also be the name of an inherited method.

## Example 1

**Source Code:**

```plain
module Main in execution;

tuple City (name : String, 
            country : String);

@Start
defun main (args : String[]) : void
{
    val x = new City ("Paris", "France");
    
    val y = x.country ("United States");

    val z = y.country ("France");

    F::println ("X = " .. x);
    F::println ("X.name = " .. x.name());
    F::println ("X.country = " .. x.country());
    F::println ();

    F::println ("Y = " .. y);
    F::println ("Y.name = " .. y.name());
    F::println ("Y.country = " .. y.country());
    F::println ();

    F::println ("Z = " .. z);
    F::println ("Z.name = " .. z.name());
    F::println ("Z.country = " .. z.country());
    F::println ();

    F::println ("Value Equality: " .. (x == z));
    F::println ("Identity Equality: " .. (x === z));
}
```

**Output:**

```plain
X = (Paris, France)
X.name = Paris
X.country = France

Y = (Paris, United States)
Y.name = Paris
Y.country = United States

Z = (Paris, France)
Z.name = Paris
Z.country = France

Value Equality: true
Identity Equality: false
```

## Example 2

**Source Code:**

```plain
module Main in execution;

tuple City (name : String, 
            country : String);

@Start
defun main (args : String[]) : void
{
    val city = new City ("Paris", "France");
    
    F::println ("Keys = " .. city.keys());
    F::println ();
    F::println ("Values = " .. city.values());
    F::println ();
    F::println ("Size = " .. city.size());
    F::println ();
    F::println ("Tuple? = " .. city.isTuple());
    F::println ();
    F::println ("Struct? = " .. city.isStruct());
    F::println ();

    foreach (value : String in city)
    {
        F::println ("Value = " .. value);
    }
}
```

**Output:**

```plain
Keys = [name, country]

Values = [Paris, France]

Size = 2

Tuple? = true

Struct? = false

Value = Paris
Value = France
```

## Example 3

**Source Code:**

```plain
module Main in execution;

design Taxable (income : int);

design Citizen (id : int);

tuple Person (name : String, 
              id : int, 
              income : int) extends Citizen & Taxable;

@Start
defun main (args : String[]) : void
{
    val anna = new Person ("Anna", 7_433_9_7452, 25_000);
    val emma = new Person ("Emma", 9_214_7_6357, 50_000);
    val kate = new Person ("Kate", 8_123_3_8721, 10_000);

    My::printTax(anna);
    My::printTax(emma);
    My::printTax(kate);
}

defun printTax (person : Person) : void
{
    val name = person.name();

    val id = person.id();

    val tax = My::computeTax(person);

    F::println("Name = " .. name);
    F::println("ID   = " .. id);
    F::println("Tax  = " .. tax);
    F::println();
}

defun computeTax (taxable : Taxable) : int
{
    val income = taxable.income();

    # 25% Tax Rate
    return income / 4; 
}
```

**Output:**

```plain
Name = Anna
ID   = 743397452
Tax  = 6250

Name = Emma
ID   = 921476357
Tax  = 12500

Name = Kate
ID   = 812338721
Tax  = 2500
```

