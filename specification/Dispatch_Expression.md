# Dispatch Expression

## Summary

A dispatch-expression dispatches an invocation to a nearby function using multiple dispatch.

## Syntax

<div class="syntax">
<b>dispatch</b> <a href="Name.md">name</a> ( <i><a href="Expression.md">argument</a><sub>1</sub></i> , ... , <i><a href="Expression.md">argument</a><sub>n</sub></i> )<br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.DispatchExpression](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/DispatchExpression.html)

## Details

+ At compile-time, the compiler creates a dispatch table containing the overloads of the named function.
  + The overloads will be sorted topologically from the most specific to the most generalized.
  + In order for an overload to be included in the dispatch table:
    + The number of provided arguments must equal the number of parameters.
    + The type of each parameter must be a reference-type.
+ At runtime, the overload to invoke is selected as follows:
  + Let A<sub>1</sub> ... A<sub>n</sub> denote the arguments.
  + Select the first overload from the sorted list of overloads, where each argument matches the related parameter.
    + Let P<sub>1</sub> ... P<sub>n</sub> denote the types of an overload's parameters.
      + A<sub>i</sub> matches P<sub>i</sub>, iff:
      + A<sub>i</sub> is null.
      + A<sub>i</sub> is an instance of P<sub>i</sub>.
+ Boxing of the arguments will be performed, when necessary.
+ Unboxing of the arguments will not be performed.
+ Coercion of the arguments will not be performed.
+ Return Type: [Object](https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html)
  + If the return-type of the dynamically selected overload is the void-type, then return null.
  + Otherwise, return the value returned by invoking the dynamically selected overload.
    + Box the return-value, if the return-type is a primitive-type.
+ A [DispatchException](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/exceptions/DispatchException.html) will be thrown, if none of the selected overloads will accept the arguments at runtime.

## Static Checks

+ [NO_SUCH_METHOD](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#NO_SUCH_METHOD): No applicable function overload(s) exist.

## Example 1

**Source Code:**

```plain
module Main in execution;

design Animal (name : String);

tuple Cat (name : String) extends Animal;

tuple Dog (name : String) extends Animal;

tuple Cow (name : String) extends Animal;

@Start
defun main (args : String[]) : void
{
    # Create some animals. 
    val owner_1 = new Cat("Fluffy");
    val owner_2 = new Dog("Jet");
    val owner_3 = new Cow("Moo");

    # Print a description of each animal. 
    My::describe(owner_1);
    My::describe(owner_2);
    My::describe(owner_3);
}

defun describe (owner : Animal) : void
{
    val message = dispatch about (owner) is String;

    F::println(message);
}

defun about (owner : Cat) : String
{
    return "Cat: " .. owner.name();
}

defun about (owner : Dog) : String
{
    return "Dog: " .. owner.name();
}

defun about (owner : Cow) : String
{
    return "Cow: " .. owner.name();
}
```

**Output:**

```plain
Cat: Fluffy
Dog: Jet
Cow: Moo
```

## Example 2

**Source Code:**

```plain
module Main in execution;

design Animal (name : String);

tuple Cat (name : String) extends Animal;

tuple Dog (name : String) extends Animal;

tuple Cow (name : String) extends Animal;

tuple AnimalHouse (owner : Animal, area : int);


@Start
defun main (args : String[]) : void
{
    # Create some animals. 
    val owner_1 = new Cat("Fluffy");
    val owner_2 = new Dog("Jet");
    val owner_3 = new Cow("Moo");

    # Place them in appropriately sized houses.
    My::provideHome(owner_1);
    My::provideHome(owner_2);
    My::provideHome(owner_3);
}

defun provideHome (owner : Animal) : void
{
    val house = dispatch create (owner) is AnimalHouse;

    F::println("Home Owner: " .. owner.name());
    F::println("Home Size:  " .. house.area());
    F::println();
}

defun create (owner : Cat) : AnimalHouse
{
    # Assume that a cat needs a 25 sqft. house. 
    return new AnimalHouse(owner, 5 * 5);
}

defun create (owner : Dog) : AnimalHouse
{
    # Assume that a dog needs a 100 sqft. house. 
    return new AnimalHouse(owner, 10 * 10);
}

defun create (owner : Cow) : AnimalHouse
{
    # Assume that a cow needs a 2500 sqft. house. 
    return new AnimalHouse(owner, 50 * 50);
}
```

**Output:**

```plain
Home Owner: Fluffy
Home Size:  25

Home Owner: Jet
Home Size:  100

Home Owner: Moo
Home Size:  2500
```

