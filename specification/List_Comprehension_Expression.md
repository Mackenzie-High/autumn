# List Comprehension Expression

## Summary

A list-comprehension creates a new mutable list based on another iterable data-structure.

## Syntax

<div class="syntax">
[ <i><a href="Expression.md">modifier</a></i> <b>for</b> <a href="Variable.md">assignee</a> : <a href="Type_Specifier.md">type</a> in <a href="Expression.md">iterable</a> ]<br>
<hr><br>
[ <i><a href="Expression.md">modifier</a></i> <b>for</b> <a href="Variable.md">assignee</a> : <a href="Type_Specifier.md">type</a> in <a href="Expression.md">iterable</a> <b>if</b> <a href="Expression.md">condition</a> ]<br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.ListComprehensionExpression](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/ListComprehensionExpression.html)

## Details

+ Algorithm:
  + Let <i>L</i> be a new [LinkedList](https://docs.oracle.com/javase/7/docs/api/java/util/LinkedList.html).
  + Let <i>E</i> denote the value produced by evaluating <i>iterable</i>.
  + If <i>E</i> is null, throw a [NullPointerException](https://docs.oracle.com/javase/7/docs/api/java/lang/NullPointerException.html).
  + For each value <i>X</i> in <i>E</i> do:
    + Let <i>T</i> be the type specified by <i>type</i>.
    + If <i>X</i> cannot be cast to <i>T</i>, throw a [ClassCastException](https://docs.oracle.com/javase/7/docs/api/java/lang/ClassCastException.html).
    + Let <i>V</i> be the local variable specified by <i>assignee</i>.
    + Assign <i>X</i> to <i>V</i>.
    + If there is a <i>condition</i> then:
      + Let <i>C</i> be the value produced by evaluating the condition.
      + Unbox <i>C</i>, if necessary.
      + If <i>C</i> is false, then immediately start the next iteration of the loop.
    + Let <i>M</i> be the value produced by evaluating the <i>modifier</i>.
    + Box <i>M</i>, if necessary.
    + Append <i>M</i> to <i>L</i>.
  + Return <i>L</i>.
+ The scope of the <i>assignee</i> is anywhere in the enclosing function.
+ The <i>assignee</i> is alive precisely during an activation of the enclosing function.
+ The <i>assignee</i> is a readonly variable.
+ The <i>condition</i> will be unboxed, if necessary.
+ Return Type: [List](https://docs.oracle.com/javase/7/docs/api/java/util/List.html)
+ Return a new mutable linked-list data-structure..

## Static Checks

+ [VALUE_REQUIRED](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#VALUE_REQUIRED): The type of the <i>modifier</i> must be either a primitive-type or a reference-type.
+ [DUPLICATE_VARIABLE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#DUPLICATE_VARIABLE): The <i>assignee</i> cannot share its name with another variable declared in the same scope.
+ [NO_SUCH_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#NO_SUCH_TYPE): The type specified by <i><i>type</i></i> must exist.
+ [INACCESSIBLE_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#INACCESSIBLE_TYPE): The type specified by <i><i>type</i></i> must be accessible.
+ [EXPECTED_REFERENCE_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#EXPECTED_REFERENCE_TYPE): The type specified by the <i>type</i> must be a reference-type.
+ [EXPECTED_ITERABLE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#EXPECTED_ITERABLE): The type of the <i>iterable</i> must be a subtype of [Iterable](https://docs.oracle.com/javase/7/docs/api/java/lang/Iterable.html).
+ [EXPECTED_CONDITION](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#EXPECTED_CONDITION): The type of <i><i>condition</i></i> must be assignable to type boolean.

## Example 1

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    # Create an iterable that iterates over the interval [0, 100].
    val input = F::range(0, 100);

    # Select the numbers that are evenly divisible by three. 
    val selected = [n for n : Integer in input if n % 3 == 0];

    # Create a list of lists.
    # Each inner list is a number, its square, and its cube.
    val output = [[x, (x * x), (x * x * x)] for x : Integer in selected];

    F::printlns(output);
}
```

**Output:**

```plain
[0, 0, 0]
[3, 9, 27]
[6, 36, 216]
[9, 81, 729]
[12, 144, 1728]
[15, 225, 3375]
[18, 324, 5832]
[21, 441, 9261]
[24, 576, 13824]
[27, 729, 19683]
[30, 900, 27000]
[33, 1089, 35937]
[36, 1296, 46656]
[39, 1521, 59319]
[42, 1764, 74088]
[45, 2025, 91125]
[48, 2304, 110592]
[51, 2601, 132651]
[54, 2916, 157464]
[57, 3249, 185193]
[60, 3600, 216000]
[63, 3969, 250047]
[66, 4356, 287496]
[69, 4761, 328509]
[72, 5184, 373248]
[75, 5625, 421875]
[78, 6084, 474552]
[81, 6561, 531441]
[84, 7056, 592704]
[87, 7569, 658503]
[90, 8100, 729000]
[93, 8649, 804357]
[96, 9216, 884736]
[99, 9801, 970299]
```

## Example 2

**Source Code:**

```plain
module Main in examples;

enum PetType (DOG, CAT, CHICKEN, GOAT, SHEEP, DUCK);

tuple Pet (name : String, type : PetType);


@Start
defun main (args : String[]) : void
{
    # Create a list of my pets. 
    val pets = [];

    pets.add(new Pet("Eyeball",    (field PetType::SHEEP)));
    pets.add(new Pet("Michael",    (field PetType::SHEEP)));
    pets.add(new Pet("Molly",      (field PetType::SHEEP)));
    pets.add(new Pet("Chicky Sr.", (field PetType::CHICKEN)));
    pets.add(new Pet("Picky Sr.",  (field PetType::CHICKEN)));
    pets.add(new Pet("Daffy",      (field PetType::DUCK)));
    pets.add(new Pet("Sikorsky",   (field PetType::CHICKEN)));
    pets.add(new Pet("Lucky",      (field PetType::CHICKEN)));
    pets.add(new Pet("Jet",        (field PetType::DOG)));
    pets.add(new Pet("Chicky Jr.", (field PetType::CHICKEN)));
    pets.add(new Pet("Picky Jr.",  (field PetType::CHICKEN)));
    pets.add(new Pet("Fluffy",     (field PetType::CAT)));
    pets.add(new Pet("Angel",      (field PetType::CHICKEN)));
    pets.add(new Pet("Nanny",      (field PetType::GOAT)));

    # Select the pets that are chickens. 
    val chickens = [pet for pet : Pet in pets 
                         if pet.type() == (field PetType::CHICKEN)];

    # Print those pets. 
    F::printlns(chickens);
}
```

**Output:**

```plain
(Chicky Sr., CHICKEN)
(Picky Sr., CHICKEN)
(Sikorsky, CHICKEN)
(Lucky, CHICKEN)
(Chicky Jr., CHICKEN)
(Picky Jr., CHICKEN)
(Angel, CHICKEN)
```

