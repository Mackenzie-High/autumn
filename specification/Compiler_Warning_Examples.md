# Examples of Compiler Warnings
## AndOperation: EXPECTED_CONDITION

**Source Code:**
```plain
module * in typechecks;

@Start
defun main (args : String[]) : void
{
    2 & true;
}
```

**Output:**
```plain
Warning: EXPECTED_CONDITION
  File: file:/typechecks/T0028
  Line: #6
  Column: #5
  Message: A boolean condition was expected.
  Expected: boolean OR java.lang.Boolean
  Actual: int
```

## AndOperation: EXPECTED_CONDITION

**Source Code:**
```plain
module * in typechecks;

@Start
defun main (args : String[]) : void
{
    true & 3;
}
```

**Output:**
```plain
Warning: EXPECTED_CONDITION
  File: file:/typechecks/T0029
  Line: #6
  Column: #12
  Message: A boolean condition was expected.
  Expected: boolean OR java.lang.Boolean
  Actual: int
```

## AnnotationDefinition: DUPLICATE_ANNOTATION

**Source Code:**
```plain
module Main in typechecks;


@Start
@Start
defun main (args : String) : void
{
    nop;
}
```

**Output:**
```plain
Warning: DUPLICATE_ANNOTATION
  File: file:/typechecks/T0150
  Line: #4
  Column: #1
  Message: An annotation can only occur once in a list of annotations.
  Type: autumn.lang.annotations.Start
```

## AnnotationDefinition: DUPLICATE_TYPE

**Source Code:**
```plain
module Main in typechecks;

functor Moo () : void;

functor Moo () : void;
```

**Output:**
```plain
Warning: DUPLICATE_TYPE
  File: file:/typechecks/T0139
  Line: #5
  Column: #9
  Message: The fully-qualified name of a type must be unique.
  Type: Moo
```

## AsOperation: IMPOSSIBLE_CONVERSION

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    2 as List;
}
```

**Output:**
```plain
Warning: IMPOSSIBLE_CONVERSION
  File: file:/typechecks/T0034
  Line: #6
  Column: #7
  Message: An as-conversion is not possible.
```

## AsOperation: INACCESSIBLE_TYPE

**Source Code:**
```plain
module Main in typechecks;

import typechecks.inaccessible.InaccessibleClass;

@Start
defun main (args : String) : void
{
    3 as InaccessibleClass;
}
```

**Output:**
```plain
Warning: INACCESSIBLE_TYPE
  File: file:/typechecks/T0159
  Line: #8
  Column: #10
  Message: The specified type is not accessible from where it is used.
  Type: typechecks.inaccessible.InaccessibleClass
```

## AsOperation: NO_SUCH_TYPE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String) : void
{
    3 as ThisTypeDoesNotExist;
}
```

**Output:**
```plain
Warning: NO_SUCH_TYPE
  File: file:/typechecks/T0158
  Line: #6
  Column: #10
  Message: The specified type does not exist.
  Type: ThisTypeDoesNotExist
```

## AsOperation: NO_SUCH_TYPE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String) : void
{
    3 as ThisTypeDoesNotExist;
}
```

**Output:**
```plain
Warning: NO_SUCH_TYPE
  File: file:/typechecks/T0158
  Line: #6
  Column: #10
  Message: The specified type does not exist.
  Type: ThisTypeDoesNotExist
```

## AssertStatement: EXPECTED_CONDITION

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    assert 123;
}
```

**Output:**
```plain
Warning: EXPECTED_CONDITION
  File: file:/typechecks/T0007
  Line: #6
  Column: #12
  Message: A boolean condition was expected.
  Expected: boolean OR java.lang.Boolean
  Actual: int
```

## AssertStatement: EXPECTED_CONDITION

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    assert 123 echo "";
}
```

**Output:**
```plain
Warning: EXPECTED_CONDITION
  File: file:/typechecks/T0008
  Line: #6
  Column: #12
  Message: A boolean condition was expected.
  Expected: boolean OR java.lang.Boolean
  Actual: int
```

## AssertStatement: EXPECTED_STRING

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    assert true echo 123;
}
```

**Output:**
```plain
Warning: EXPECTED_STRING
  File: file:/typechecks/T0009
  Line: #6
  Column: #22
  Message: A java.lang.String was expected.
```

## BranchStatement: EXPECTED_INTEGER

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    branch (true) () default CASE_ELSE;

    marker CASE_ELSE;
}
```

**Output:**
```plain
Warning: EXPECTED_INTEGER
  File: file:/typechecks/T0126
  Line: #6
  Column: #13
  Message: An integer was expected.
```

## BranchStatement: NO_SUCH_LABEL

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    branch (1) (CASE_0) default CASE_ELSE;

    marker CASE_ELSE;
}
```

**Output:**
```plain
Warning: NO_SUCH_LABEL
  File: file:/typechecks/T0127
  Line: #6
  Column: #33
  Message: A label was used, but not declared.
  Label: CASE_ELSE
```

## BranchStatement: NO_SUCH_LABEL

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    branch (1) (CASE_0) default CASE_ELSE;

    marker CASE_0;
}
```

**Output:**
```plain
Warning: NO_SUCH_LABEL
  File: file:/typechecks/T0128
  Line: #6
  Column: #33
  Message: A label was used, but not declared.
  Label: CASE_ELSE
```

## BreakStatement: BREAK_OUTSIDE_OF_LOOP

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    break;
}
```

**Output:**
```plain
Warning: BREAK_OUTSIDE_OF_LOOP
  File: file:/typechecks/T0060
  Line: #6
  Column: #5
  Message: A break-statement can only be used inside of a loop construct.
```

## CallMethodExpression: EXPECTED_DECLARED_TYPE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    call null.print();
}
```

**Output:**
```plain
Warning: EXPECTED_DECLARED_TYPE
  File: file:/typechecks/T0086
  Line: #6
  Column: #5
  Message: A declared-type was expected.
```

## CallMethodExpression: NO_SUCH_METHOD

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    val list = [];

    call list.subtract();
}
```

**Output:**
```plain
Warning: NO_SUCH_METHOD
  File: file:/typechecks/T0087
  Line: #8
  Column: #5
  Message: No acceptable method overload was found.
  Invocation: List.subtract()
```

## CallStaticMethodExpression: EXPECTED_DECLARED_TYPE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    call String[]::println();
}
```

**Output:**
```plain
Warning: EXPECTED_DECLARED_TYPE
  File: file:/typechecks/T0084
  Line: #6
  Column: #10
  Message: A declared-type was expected.
```

## CallStaticMethodExpression: INACCESSIBLE_TYPE

**Source Code:**
```plain
module Main in typechecks;

import typechecks.inaccessible.InaccessibleClass;

@Start
defun main (args : String[]) : void
{
    call InaccessibleClass::println();
}
```

**Output:**
```plain
Warning: INACCESSIBLE_TYPE
  File: file:/typechecks/T0083
  Line: #8
  Column: #10
  Message: The specified type is not accessible from where it is used.
  Type: typechecks.inaccessible.InaccessibleClass
```

## CallStaticMethodExpression: NO_SUCH_METHOD

**Source Code:**
```plain
module Main in typechecks;

import typechecks.inaccessible.InaccessibleClass;

@Start
defun main (args : String[]) : void
{
    call F::print();
}
```

**Output:**
```plain
Warning: NO_SUCH_METHOD
  File: file:/typechecks/T0085
  Line: #8
  Column: #5
  Message: No acceptable method overload was found.
  Invocation: F::print()
```

## CallStaticMethodExpression: NO_SUCH_TYPE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    call ThisTypeDoesNotExist::println();
}
```

**Output:**
```plain
Warning: NO_SUCH_TYPE
  File: file:/typechecks/T0082
  Line: #6
  Column: #10
  Message: The specified type does not exist.
  Type: ThisTypeDoesNotExist
```

## ClassDatum: INACCESSIBLE_TYPE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    (class typechecks.inaccessible.InaccessibleClass);
}
```

**Output:**
```plain
Warning: INACCESSIBLE_TYPE
  File: file:/typechecks/T0079
  Line: #6
  Column: #12
  Message: The specified type is not accessible from where it is used.
  Type: typechecks.inaccessible.InaccessibleClass
```

## ClassDatum: NO_SUCH_TYPE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    (class Moo);
}
```

**Output:**
```plain
Warning: NO_SUCH_TYPE
  File: file:/typechecks/T0078
  Line: #6
  Column: #12
  Message: The specified type does not exist.
  Type: Moo
```

## ContinueStatement: CONTINUE_OUTSIDE_OF_LOOP

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    continue;
}
```

**Output:**
```plain
Warning: CONTINUE_OUTSIDE_OF_LOOP
  File: file:/typechecks/T0073
  Line: #6
  Column: #5
  Message: A continue-statement can only be used inside of a loop construct.
```

## DelegateStatement: DUPLICATE_VARIABLE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    val p = 5;

    delegate p : Predicate => Main::moo;
}

defun moo (value : Object) : boolean
{
    return false;
}
```

**Output:**
```plain
Warning: DUPLICATE_VARIABLE
  File: file:/typechecks/T0213
  Line: #8
  Column: #14
  Message: A variable was declared more than once in the same scope.
  Variable: p
```

## DelegateStatement: EXPECTED_DEFINED_FUNCTOR_TYPE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    val p = 5;

    delegate p : String => Main::moo;
}

defun moo (value : Object) : boolean
{
    return false;
}
```

**Output:**
```plain
Warning: EXPECTED_DEFINED_FUNCTOR_TYPE
  File: file:/typechecks/T0216
  Line: #8
  Column: #18
  Message: An autumn.lang.DefinedFunctor was expected.
```

## DelegateStatement: EXPECTED_MODULE_TYPE

**Source Code:**
```plain
module Main in typechecks;

import typechecks.inaccessible.InaccessibleClass;

@Start
defun main (args : String[]) : void
{
    delegate p : Predicate => String::moo;
}
```

**Output:**
```plain
Warning: EXPECTED_MODULE_TYPE
  File: file:/typechecks/T0219
  Line: #8
  Column: #31
  Message: An autumn.lang.Module was expected.
```

## DelegateStatement: INACCESSIBLE_TYPE

**Source Code:**
```plain
module Main in typechecks;

import typechecks.inaccessible.InaccessibleClass;

@Start
defun main (args : String[]) : void
{
    val p = 5;

    delegate p : InaccessibleClass => Main::moo;
}

defun moo (value : Object) : boolean
{
    return false;
}
```

**Output:**
```plain
Warning: INACCESSIBLE_TYPE
  File: file:/typechecks/T0215
  Line: #10
  Column: #18
  Message: The specified type is not accessible from where it is used.
  Type: typechecks.inaccessible.InaccessibleClass
```

## DelegateStatement: INACCESSIBLE_TYPE

**Source Code:**
```plain
module Main in typechecks;

import typechecks.inaccessible.InaccessibleClass;

@Start
defun main (args : String[]) : void
{
    delegate p : Predicate => InaccessibleClass::moo;
}
```

**Output:**
```plain
Warning: INACCESSIBLE_TYPE
  File: file:/typechecks/T0218
  Line: #8
  Column: #31
  Message: The specified type is not accessible from where it is used.
  Type: typechecks.inaccessible.InaccessibleClass
```

## DelegateStatement: NO_SUCH_METHOD

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    delegate p : Predicate => Main::moo;
}
```

**Output:**
```plain
Warning: NO_SUCH_METHOD
  File: file:/typechecks/T0220
  Line: #6
  Column: #5
  Message: The handler function does not exist.
  Owner: typechecks.Main
  Name: moo
```

## DelegateStatement: NO_SUCH_TYPE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    val p = 5;

    delegate p : ThisTypeDoesNotExist => Main::moo;
}

defun moo (value : Object) : boolean
{
    return false;
}
```

**Output:**
```plain
Warning: NO_SUCH_TYPE
  File: file:/typechecks/T0214
  Line: #8
  Column: #18
  Message: The specified type does not exist.
  Type: ThisTypeDoesNotExist
```

## DelegateStatement: NO_SUCH_TYPE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    delegate p : Predicate => ThisTypeDoesNotExist::moo;
}
```

**Output:**
```plain
Warning: NO_SUCH_TYPE
  File: file:/typechecks/T0217
  Line: #6
  Column: #31
  Message: The specified type does not exist.
  Type: ThisTypeDoesNotExist
```

## DesignDefinition: CIRCULAR_INHERITANCE

**Source Code:**
```plain
module Main in typechecks;

design T () extends T;
```

**Output:**
```plain
Warning: CIRCULAR_INHERITANCE
  File: file:/typechecks/T0162
  Line: #3
  Column: #1
  Message: A type cannot be a subtype of itself.
  Type: typechecks.T
```

## DesignDefinition: CIRCULAR_INHERITANCE

**Source Code:**
```plain
module Main in typechecks;

design P () extends Q;

design Q () extends P;
```

**Output:**
```plain
Warning: CIRCULAR_INHERITANCE
  File: file:/typechecks/T0163
  Line: #5
  Column: #1
  Message: A type cannot be a subtype of itself.
  Type: typechecks.Q
```

## DesignDefinition: COVARIANCE_VIOLATION

**Source Code:**
```plain
module Main in typechecks;

design Person (name : String);

design Employee (name : Object) extends Person;
```

**Output:**
```plain
Warning: COVARIANCE_VIOLATION
  File: file:/typechecks/T0179
  Line: #5
  Column: #1
  Message: A covariance violation was encountered.
  Element: name
  Upper: java.lang.String
  Lower: java.lang.Object
```

## DesignDefinition: DUPLICATE_ANNOTATION

**Source Code:**
```plain
module Main in typechecks;

annotation Anno;

@Anno
@Anno
design E ();
```

**Output:**
```plain
Warning: DUPLICATE_ANNOTATION
  File: file:/typechecks/T0153
  Line: #5
  Column: #1
  Message: An annotation can only occur once in a list of annotations.
  Type: typechecks.Anno
```

## DesignDefinition: DUPLICATE_ELEMENT

**Source Code:**
```plain
module Main in typechecks;

design Person (name : String,
               name : String);
```

**Output:**
```plain
Warning: DUPLICATE_ELEMENT
  File: file:/typechecks/T0174
  Line: #4
  Column: #16
  Message: A record can only declare an element once per definition
  Element: name
```

## DesignDefinition: DUPLICATE_SUPERTYPE

**Source Code:**
```plain
module Main in typechecks;

design Taxable ();

design Person () extends Taxable & Taxable;
```

**Output:**
```plain
Warning: DUPLICATE_SUPERTYPE
  File: file:/typechecks/T0178
  Line: #5
  Column: #1
  Message: A supertype can appear only once in an extends-clause.
  Super Type: typechecks.Taxable
```

## DesignDefinition: DUPLICATE_TYPE

**Source Code:**
```plain
module Main in typechecks;

design T ();

design T ();
```

**Output:**
```plain
Warning: DUPLICATE_TYPE
  File: file:/typechecks/T0168
  Line: #5
  Column: #8
  Message: The fully-qualified name of a type must be unique.
  Type: T
```

## DesignDefinition: EXPECTED_DESIGN_TYPE

**Source Code:**
```plain
module Main in typechecks;

design Places () extends List;
```

**Output:**
```plain
Warning: EXPECTED_DESIGN_TYPE
  File: file:/typechecks/T0180
  Line: #3
  Column: #1
  Message: A design-type was expected.
```

## DesignDefinition: EXPECTED_INTERFACE_TYPE

**Source Code:**
```plain
module Main in typechecks;

design Person () extends void;
```

**Output:**
```plain
Warning: EXPECTED_INTERFACE_TYPE
  File: file:/typechecks/T0181
  Line: #3
  Column: #26
  Message: An interface-type was expected.
```

## DesignDefinition: EXPECTED_INTERFACE_TYPE

**Source Code:**
```plain
module Main in typechecks;

design T () extends int;
```

**Output:**
```plain
Warning: EXPECTED_INTERFACE_TYPE
  File: file:/typechecks/T0173
  Line: #3
  Column: #21
  Message: An interface-type was expected.
```

## DesignDefinition: EXPECTED_INTERFACE_TYPE

**Source Code:**
```plain
module Main in typechecks;

design T () extends String;
```

**Output:**
```plain
Warning: EXPECTED_INTERFACE_TYPE
  File: file:/typechecks/T0172
  Line: #3
  Column: #21
  Message: An interface-type was expected.
```

## DesignDefinition: EXPECTED_VARIABLE_TYPE

**Source Code:**
```plain
module Main in typechecks;

design Person (name : void);
```

**Output:**
```plain
Warning: EXPECTED_VARIABLE_TYPE
  File: file:/typechecks/T0177
  Line: #3
  Column: #23
  Message: A variable-type was expected.
```

## DesignDefinition: INACCESSIBLE_TYPE

**Source Code:**
```plain
module Main in typechecks;

import typechecks.inaccessible.InaccessibleInterface;

design T () extends InaccessibleInterface;
```

**Output:**
```plain
Warning: INACCESSIBLE_TYPE
  File: file:/typechecks/T0171
  Line: #5
  Column: #21
  Message: The specified type is not accessible from where it is used.
  Type: typechecks.inaccessible.InaccessibleInterface
```

## DesignDefinition: INACCESSIBLE_TYPE

**Source Code:**
```plain
module Main in typechecks;

import typechecks.inaccessible.InaccessibleClass;

design Person (name : InaccessibleClass);
```

**Output:**
```plain
Warning: INACCESSIBLE_TYPE
  File: file:/typechecks/T0176
  Line: #5
  Column: #23
  Message: The specified type is not accessible from where it is used.
  Type: typechecks.inaccessible.InaccessibleClass
```

## DesignDefinition: NO_SUCH_TYPE

**Source Code:**
```plain
module Main in typechecks;

design T () extends ThisTypeDoesNotExist;
```

**Output:**
```plain
Warning: NO_SUCH_TYPE
  File: file:/typechecks/T0170
  Line: #3
  Column: #21
  Message: The specified type does not exist.
  Type: ThisTypeDoesNotExist
```

## DesignDefinition: NO_SUCH_TYPE

**Source Code:**
```plain
module Main in typechecks;

design Person (name : ThisTypeDoesNotExist);
```

**Output:**
```plain
Warning: NO_SUCH_TYPE
  File: file:/typechecks/T0175
  Line: #3
  Column: #23
  Message: The specified type does not exist.
  Type: ThisTypeDoesNotExist
```

## DoUntilStatement: EXPECTED_CONDITION

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    do
    {
        nop;
    }
    until(123)
}
```

**Output:**
```plain
Warning: EXPECTED_CONDITION
  File: file:/typechecks/T0006
  Line: #10
  Column: #11
  Message: A boolean condition was expected.
  Expected: boolean OR java.lang.Boolean
  Actual: int
```

## DoWhileStatement: EXPECTED_CONDITION

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    do
    {
        nop;
    }
    while(123)
}
```

**Output:**
```plain
Warning: EXPECTED_CONDITION
  File: file:/typechecks/T0005
  Line: #10
  Column: #11
  Message: A boolean condition was expected.
  Expected: boolean OR java.lang.Boolean
  Actual: int
```

## EnumDefinition: DUPLICATE_ANNOTATION

**Source Code:**
```plain
module Main in typechecks;

annotation Anno;

@Anno
@Anno
enum E ();
```

**Output:**
```plain
Warning: DUPLICATE_ANNOTATION
  File: file:/typechecks/T0152
  Line: #5
  Column: #1
  Message: An annotation can only occur once in a list of annotations.
  Type: typechecks.Anno
```

## EnumDefinition: DUPLICATE_ELEMENT

**Source Code:**
```plain
module * in typechecks;

enum City ( Paris, Paris  );
```

**Output:**
```plain
Warning: DUPLICATE_CONSTANT
  File: file:/typechecks/T0050
  Line: #3
  Column: #20
  Message: No two enum-constants, in the same enum, can share their name.
  Enum Type: typechecks.City
  Constant: Paris
```

## EnumDefinition: DUPLICATE_TYPE

**Source Code:**
```plain
module * in typechecks;

enum City ();

enum City ();
```

**Output:**
```plain
Warning: DUPLICATE_TYPE
  File: file:/typechecks/T0049
  Line: #5
  Column: #6
  Message: The fully-qualified name of a type must be unique.
  Type: City
```

## ExceptionDefinition: CIRCULAR_INHERITANCE

**Source Code:**
```plain
module * in typechecks;

exception Moo extends Moo;
```

**Output:**
```plain
Warning: CIRCULAR_INHERITANCE
  File: file:/typechecks/T0045
  Line: #3
  Column: #1
  Message: A type cannot be a subtype of itself.
  Type: typechecks.Moo
```

## ExceptionDefinition: CIRCULAR_INHERITANCE

**Source Code:**
```plain
module * in typechecks;

exception Moo extends Cow;

exception Cow extends Animal;

exception Animal extends Moo;
```

**Output:**
```plain
Warning: CIRCULAR_INHERITANCE
  File: file:/typechecks/T0046
  Line: #7
  Column: #1
  Message: A type cannot be a subtype of itself.
  Type: typechecks.Animal
```

## ExceptionDefinition: DUPLICATE_ANNOTATION

**Source Code:**
```plain
module Main in typechecks;

annotation Anno;

@Anno
@Anno
exception E extends RuntimeException;
```

**Output:**
```plain
Warning: DUPLICATE_ANNOTATION
  File: file:/typechecks/T0151
  Line: #5
  Column: #1
  Message: An annotation can only occur once in a list of annotations.
  Type: typechecks.Anno
```

## ExceptionDefinition: DUPLICATE_TYPE

**Source Code:**
```plain
module * in typechecks;

exception Moo extends Throwable;
exception Moo extends Throwable;
```

**Output:**
```plain
Warning: DUPLICATE_TYPE
  File: file:/typechecks/T0041
  Line: #4
  Column: #11
  Message: The fully-qualified name of a type must be unique.
  Type: Moo
```

## ExceptionDefinition: EXPECTED_CLASS_TYPE

**Source Code:**
```plain
module Main in typechecks;

exception Problem extends List;
```

**Output:**
```plain
Warning: EXPECTED_CLASS_TYPE
  File: file:/typechecks/T0138
  Line: #3
  Column: #27
  Message: A class-type was expected.
```

## ExceptionDefinition: EXPECTED_THROWABLE

**Source Code:**
```plain
module * in typechecks;

exception Moo extends Object;
```

**Output:**
```plain
Warning: EXPECTED_THROWABLE
  File: file:/typechecks/T0042
  Line: #3
  Column: #23
  Message: A java.lang.Throwable was expected.
```

## ExceptionDefinition: INACCESSIBLE_TYPE

**Source Code:**
```plain
module * in typechecks;

import typechecks.inaccessible.InaccessibleClass;

exception Moo extends InaccessibleClass;
```

**Output:**
```plain
Warning: INACCESSIBLE_TYPE
  File: file:/typechecks/T0044
  Line: #5
  Column: #23
  Message: The specified type is not accessible from where it is used.
  Type: typechecks.inaccessible.InaccessibleClass
```

## ExceptionDefinition: NO_SUCH_TYPE

**Source Code:**
```plain
module * in typechecks;

exception Moo extends ThisTypeDoesNotExist;
```

**Output:**
```plain
Warning: NO_SUCH_TYPE
  File: file:/typechecks/T0043
  Line: #3
  Column: #23
  Message: The specified type does not exist.
  Type: ThisTypeDoesNotExist
```

## ForStatement: DUPLICATE_VARIABLE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    var i = 0;

    for (i = 0; i < 5; i + 1)
    {
        nop;
    }
}
```

**Output:**
```plain
Warning: DUPLICATE_VARIABLE
  File: file:/typechecks/T0056
  Line: #8
  Column: #10
  Message: A variable was declared more than once in the same scope.
  Variable: i
```

## ForStatement: EXPECTED_CONDITION

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    for (i = 0; "Three"; i + 1)
    {
        nop;
    }
}
```

**Output:**
```plain
Warning: EXPECTED_CONDITION
  File: file:/typechecks/T0059
  Line: #6
  Column: #17
  Message: A boolean condition was expected.
  Expected: boolean OR java.lang.Boolean
  Actual: java.lang.String
```

## ForStatement: EXPECTED_INTEGER

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    for (i = "Two"; i < 5; i + 1)
    {
        nop;
    }
}
```

**Output:**
```plain
Warning: EXPECTED_INTEGER
  File: file:/typechecks/T0057
  Line: #6
  Column: #14
  Message: An integer was expected.
```

## ForStatement: EXPECTED_INTEGER

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    for (i = 0; i < 5; "Four")
    {
        nop;
    }
}
```

**Output:**
```plain
Warning: EXPECTED_INTEGER
  File: file:/typechecks/T0058
  Line: #6
  Column: #24
  Message: An integer was expected.
```

## ForStatement: VARIABLE_OUTSIDE_OF_SCOPE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    for (i = i; false; 0)
    {
        nop;
    }
}
```

**Output:**
```plain
Warning: NO_SUCH_VARIABLE
  File: file:/typechecks/T0069
  Line: #6
  Column: #14
  Message: A variable was used before it was declared.
  Variable: i
```

## ForStatement: VARIABLE_OUTSIDE_OF_SCOPE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    for (i = 0; false; 0)
    {
        nop;
    }

    i;
}
```

**Output:**
```plain
Warning: VARIABLE_OUTSIDE_OF_SCOPE
  File: file:/typechecks/T0070
  Line: #11
  Column: #5
  Message: A variable was outside of its declared scope.
  Variable: i
```

## FunctionDefinition: DUPLICATE_ANNOTATION

**Source Code:**
```plain
module Main in typechecks;

annotation Anno;

@Anno
@Anno
defun main (args : String) : void
{
    nop;
}
```

**Output:**
```plain
Warning: DUPLICATE_ANNOTATION
  File: file:/typechecks/T0157
  Line: #5
  Column: #1
  Message: An annotation can only occur once in a list of annotations.
  Type: typechecks.Anno
```

## FunctionDefinition: EXPECTED_VARIABLE_TYPE

**Source Code:**
```plain
module Main in typechecks;

defun moo (message : void) : void
{
    nop;
}
```

**Output:**
```plain
Warning: EXPECTED_VARIABLE_TYPE
  File: file:/typechecks/T0184
  Line: #3
  Column: #22
  Message: A variable-type was expected.
```

## FunctionDefinition: INACCESSIBLE_TYPE

**Source Code:**
```plain
module Main in typechecks;

import typechecks.inaccessible.InaccessibleClass;

defun moo (message : InaccessibleClass) : void
{
    nop;
}
```

**Output:**
```plain
Warning: INACCESSIBLE_TYPE
  File: file:/typechecks/T0183
  Line: #5
  Column: #22
  Message: The specified type is not accessible from where it is used.
  Type: typechecks.inaccessible.InaccessibleClass
```

## FunctionDefinition: INACCESSIBLE_TYPE

**Source Code:**
```plain
module Main in typechecks;

import typechecks.inaccessible.InaccessibleClass;

defun moo () : InaccessibleClass
{
    nop;
}
```

**Output:**
```plain
Warning: INACCESSIBLE_TYPE
  File: file:/typechecks/T0186
  Line: #5
  Column: #16
  Message: The specified type is not accessible from where it is used.
  Type: typechecks.inaccessible.InaccessibleClass
```

## FunctionDefinition: NO_SUCH_TYPE

**Source Code:**
```plain
module Main in typechecks;

defun moo (message : ThisTypeDoesNotExist) : void
{
    nop;
}
```

**Output:**
```plain
Warning: NO_SUCH_TYPE
  File: file:/typechecks/T0182
  Line: #3
  Column: #22
  Message: The specified type does not exist.
  Type: ThisTypeDoesNotExist
```

## FunctionDefinition: NO_SUCH_TYPE

**Source Code:**
```plain
module Main in typechecks;

defun moo () : ThisTypeDoesNotExist
{
    nop;
}
```

**Output:**
```plain
Warning: NO_SUCH_TYPE
  File: file:/typechecks/T0185
  Line: #3
  Column: #16
  Message: The specified type does not exist.
  Type: ThisTypeDoesNotExist
```

## FunctorDefinition: CIRCULAR_INHERITANCE

**Source Code:**
```plain
module Main in typechecks;

functor Boo () : void extends Moo;

functor Foo () : void extends Boo;

functor Moo () : void extends Foo;
```

**Output:**
```plain
Warning: CIRCULAR_INHERITANCE
  File: file:/typechecks/T0149
  Line: #7
  Column: #1
  Message: A type cannot be a subtype of itself.
  Type: typechecks.Moo
```

## FunctorDefinition: DUPLICATE_ANNOTATION

**Source Code:**
```plain
module Main in typechecks;

annotation Anno;

@Anno
@Anno
functor F () : String;
```

**Output:**
```plain
Warning: DUPLICATE_ANNOTATION
  File: file:/typechecks/T0156
  Line: #5
  Column: #1
  Message: An annotation can only occur once in a list of annotations.
  Type: typechecks.Anno
```

## FunctorDefinition: DUPLICATE_TYPE

**Source Code:**
```plain
module Main in typechecks;

functor Moo () : void;

functor Moo () : void;
```

**Output:**
```plain
Warning: DUPLICATE_TYPE
  File: file:/typechecks/T0139
  Line: #5
  Column: #9
  Message: The fully-qualified name of a type must be unique.
  Type: Moo
```

## FunctorDefinition: EXPECTED_CLASS_TYPE

**Source Code:**
```plain
module Main in typechecks;

functor Moo () : void extends List;
```

**Output:**
```plain
Warning: EXPECTED_CLASS_TYPE
  File: file:/typechecks/T0142
  Line: #3
  Column: #31
  Message: A class-type was expected.
```

## FunctorDefinition: EXPECTED_DEFINED_FUNCTOR_TYPE

**Source Code:**
```plain
module Main in typechecks;

functor Moo () : void extends String;
```

**Output:**
```plain
Warning: EXPECTED_DEFINED_FUNCTOR_TYPE
  File: file:/typechecks/T0143
  Line: #3
  Column: #31
  Message: An autumn.lang.DefinedFunctor was expected.
```

## FunctorDefinition: EXPECTED_VARIABLE_TYPE

**Source Code:**
```plain
module Main in typechecks;

functor Moo (x : void) : void;
```

**Output:**
```plain
Warning: EXPECTED_VARIABLE_TYPE
  File: file:/typechecks/T0146
  Line: #3
  Column: #18
  Message: A variable-type was expected.
```

## FunctorDefinition: INACCESSIBLE_TYPE

**Source Code:**
```plain
module Main in typechecks;

import typechecks.inaccessible.InaccessibleClass;

functor Moo () : void extends InaccessibleClass;
```

**Output:**
```plain
Warning: INACCESSIBLE_TYPE
  File: file:/typechecks/T0141
  Line: #5
  Column: #31
  Message: The specified type is not accessible from where it is used.
  Type: typechecks.inaccessible.InaccessibleClass
```

## FunctorDefinition: INACCESSIBLE_TYPE

**Source Code:**
```plain
module Main in typechecks;

import typechecks.inaccessible.InaccessibleClass;

functor Moo (x : InaccessibleClass) : void;
```

**Output:**
```plain
Warning: INACCESSIBLE_TYPE
  File: file:/typechecks/T0145
  Line: #5
  Column: #18
  Message: The specified type is not accessible from where it is used.
  Type: typechecks.inaccessible.InaccessibleClass
```

## FunctorDefinition: INACCESSIBLE_TYPE

**Source Code:**
```plain
module Main in typechecks;

import typechecks.inaccessible.InaccessibleClass;

functor Moo () : InaccessibleClass;
```

**Output:**
```plain
Warning: INACCESSIBLE_TYPE
  File: file:/typechecks/T0148
  Line: #5
  Column: #18
  Message: The specified type is not accessible from where it is used.
  Type: typechecks.inaccessible.InaccessibleClass
```

## FunctorDefinition: NO_SUCH_TYPE

**Source Code:**
```plain
module Main in typechecks;

functor Moo () : void extends ThisTypeDoesNotExist;
```

**Output:**
```plain
Warning: NO_SUCH_TYPE
  File: file:/typechecks/T0140
  Line: #3
  Column: #31
  Message: The specified type does not exist.
  Type: ThisTypeDoesNotExist
```

## FunctorDefinition: NO_SUCH_TYPE

**Source Code:**
```plain
module Main in typechecks;

functor Moo (x : ThisTypeDoesNotExist) : void;
```

**Output:**
```plain
Warning: NO_SUCH_TYPE
  File: file:/typechecks/T0144
  Line: #3
  Column: #18
  Message: The specified type does not exist.
  Type: ThisTypeDoesNotExist
```

## FunctorDefinition: NO_SUCH_TYPE

**Source Code:**
```plain
module Main in typechecks;

functor Moo () : ThisTypeDoesNotExist;
```

**Output:**
```plain
Warning: NO_SUCH_TYPE
  File: file:/typechecks/T0147
  Line: #3
  Column: #18
  Message: The specified type does not exist.
  Type: ThisTypeDoesNotExist
```

## GetFieldExpression: EXPECTED_DECLARED_TYPE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    val x = 3;

    (field x.age);
}
```

**Output:**
```plain
Warning: EXPECTED_DECLARED_TYPE
  File: file:/typechecks/T0102
  Line: #8
  Column: #12
  Message: A declared-type was expected.
```

## GetFieldExpression: NO_SUCH_FIELD

**Source Code:**
```plain
module Main in typechecks;

import high.mackenzie.autumn.resources.InstanceFieldTester;

@Start
defun main (args : String[]) : void
{
    val object = new InstanceFieldTester();

    field object.age;
}
```

**Output:**
```plain
Warning: NO_SUCH_FIELD
  File: file:/typechecks/T0103
  Line: #10
  Column: #5
  Message: No acceptable field was found.
  Field: InstanceFieldTester.age
```

## GetStaticFieldExpression: EXPECTED_DECLARED_TYPE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    (field String[]::age);
}
```

**Output:**
```plain
Warning: EXPECTED_DECLARED_TYPE
  File: file:/typechecks/T0095
  Line: #6
  Column: #12
  Message: A declared-type was expected.
```

## GetStaticFieldExpression: INACCESSIBLE_TYPE

**Source Code:**
```plain
module Main in typechecks;

import typechecks.inaccessible.InaccessibleClass;

@Start
defun main (args : String[]) : void
{
    (field InaccessibleClass::age);
}
```

**Output:**
```plain
Warning: INACCESSIBLE_TYPE
  File: file:/typechecks/T0094
  Line: #8
  Column: #12
  Message: The specified type is not accessible from where it is used.
  Type: typechecks.inaccessible.InaccessibleClass
```

## GetStaticFieldExpression: NO_SUCH_FIELD

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    (field String::age);
}
```

**Output:**
```plain
Warning: NO_SUCH_FIELD
  File: file:/typechecks/T0096
  Line: #6
  Column: #5
  Message: No acceptable field was found.
  Field: String::age
```

## GetStaticFieldExpression: NO_SUCH_TYPE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    (field ThisTypeDoesNotExist::age);
}
```

**Output:**
```plain
Warning: NO_SUCH_TYPE
  File: file:/typechecks/T0093
  Line: #6
  Column: #12
  Message: The specified type does not exist.
  Type: ThisTypeDoesNotExist
```

## GotoStatement: NO_SUCH_LABEL

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    goto Moo;
}
```

**Output:**
```plain
Warning: NO_SUCH_LABEL
  File: file:/typechecks/T0021
  Line: #6
  Column: #10
  Message: A label was used, but not declared.
  Label: Moo
```

## IdentityEqualsOperation: EXPECTED_REFERENCE_TYPE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    2 === "Venus";
}
```

**Output:**
```plain
Warning: EXPECTED_REFERENCE_TYPE
  File: file:/typechecks/T0024
  Line: #6
  Column: #5
  Message: The type of a particular expression must be a reference-type.
```

## IdentityEqualsOperation: EXPECTED_REFERENCE_TYPE

**Source Code:**
```plain
module * in typechecks;

@Start
defun main (args : String[]) : void
{
    "Venus" === 2;
}
```

**Output:**
```plain
Warning: EXPECTED_REFERENCE_TYPE
  File: file:/typechecks/T0025
  Line: #6
  Column: #17
  Message: The type of a particular expression must be a reference-type.
```

## IdentityNotEqualsOperation: EXPECTED_REFERENCE_TYPE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    2 !== "Venus";
}
```

**Output:**
```plain
Warning: EXPECTED_REFERENCE_TYPE
  File: file:/typechecks/T0032
  Line: #6
  Column: #5
  Message: The type of a particular expression must be a reference-type.
```

## IdentityNotEqualsOperation: EXPECTED_REFERENCE_TYPE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    "Venus" !== 2;
}
```

**Output:**
```plain
Warning: EXPECTED_REFERENCE_TYPE
  File: file:/typechecks/T0033
  Line: #6
  Column: #17
  Message: The type of a particular expression must be a reference-type.
```

## IfStatement: EXPECTED_CONDITION

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    if(123)
    {
        nop;
    }
}
```

**Output:**
```plain
Warning: EXPECTED_CONDITION
  File: file:/typechecks/T0001
  Line: #6
  Column: #8
  Message: A boolean condition was expected.
  Expected: boolean OR java.lang.Boolean
  Actual: int
```

## IfStatement: EXPECTED_CONDITION

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    if(true)
    {
        nop;
    }
    elif(123)
    {
        nop;
    }
}
```

**Output:**
```plain
Warning: EXPECTED_CONDITION
  File: file:/typechecks/T0002
  Line: #10
  Column: #10
  Message: A boolean condition was expected.
  Expected: boolean OR java.lang.Boolean
  Actual: int
```

## ImpliesOperation: EXPECTED_CONDITION

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    2 -> true;
}
```

**Output:**
```plain
Warning: EXPECTED_CONDITION
  File: file:/typechecks/T0119
  Line: #6
  Column: #5
  Message: A boolean condition was expected.
  Expected: boolean OR java.lang.Boolean
  Actual: int
```

## ImpliesOperation: EXPECTED_CONDITION

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    true -> 2;
}
```

**Output:**
```plain
Warning: EXPECTED_CONDITION
  File: file:/typechecks/T0120
  Line: #6
  Column: #13
  Message: A boolean condition was expected.
  Expected: boolean OR java.lang.Boolean
  Actual: int
```

## InstanceOfExpression: EXPECTED_DECLARED_TYPE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    (instanceof [] : int);
}
```

**Output:**
```plain
Warning: EXPECTED_DECLARED_TYPE
  File: file:/typechecks/T0104
  Line: #6
  Column: #22
  Message: A declared-type was expected.
```

## InstanceOfExpression: EXPECTED_DECLARED_TYPE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    (instanceof 3 : String);
}
```

**Output:**
```plain
Warning: EXPECTED_DECLARED_TYPE
  File: file:/typechecks/T0105
  Line: #6
  Column: #5
  Message: A declared-type was expected.
```

## InstanceOfExpression: INACCESSIBLE_TYPE

**Source Code:**
```plain
module Main in typechecks;

import typechecks.inaccessible.InaccessibleClass;

@Start
defun main (args : String[]) : void
{
    (instanceof [] : InaccessibleClass);
}
```

**Output:**
```plain
Warning: INACCESSIBLE_TYPE
  File: file:/typechecks/T0106
  Line: #8
  Column: #22
  Message: The specified type is not accessible from where it is used.
  Type: typechecks.inaccessible.InaccessibleClass
```

## InstanceOfExpression: NON_VIABLE_INSTANCEOF

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    (instanceof [] : Integer);
}
```

**Output:**
```plain
Warning: NON_VIABLE_INSTANCEOF
  File: file:/typechecks/T0108
  Line: #6
  Column: #5
  Message: An instance-of operation must be viable.
  Problem: List is never an instance-of Integer
```

## InstanceOfExpression: NO_SUCH_TYPE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    (instanceof [] : ThisTypeDoesNotExist);
}
```

**Output:**
```plain
Warning: NO_SUCH_TYPE
  File: file:/typechecks/T0107
  Line: #6
  Column: #22
  Message: The specified type does not exist.
  Type: ThisTypeDoesNotExist
```

## IsOperation: IMPOSSIBLE_CONVERSION

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    2 is List;
}
```

**Output:**
```plain
Warning: IMPOSSIBLE_CONVERSION
  File: file:/typechecks/T0035
  Line: #6
  Column: #7
  Message: An is-conversion is not possible.
```

## LambdaStatement: DUPLICATE_VARIABLE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    val p = 5;

    lambda p : Predicate => true;
}
```

**Output:**
```plain
Warning: DUPLICATE_VARIABLE
  File: file:/typechecks/T0206
  Line: #8
  Column: #12
  Message: A variable was declared more than once in the same scope.
  Variable: p
```

## LambdaStatement: EXPECTED_DEFINED_FUNCTOR_TYPE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    lambda p : String => true;
}
```

**Output:**
```plain
Warning: EXPECTED_DEFINED_FUNCTOR_TYPE
  File: file:/typechecks/T0209
  Line: #6
  Column: #16
  Message: An autumn.lang.DefinedFunctor was expected.
```

## LambdaStatement: INACCESSIBLE_TYPE

**Source Code:**
```plain
module Main in typechecks;

import typechecks.inaccessible.InaccessibleClass;

@Start
defun main (args : String[]) : void
{
    lambda p : InaccessibleClass => true;
}
```

**Output:**
```plain
Warning: INACCESSIBLE_TYPE
  File: file:/typechecks/T0208
  Line: #8
  Column: #16
  Message: The specified type is not accessible from where it is used.
  Type: typechecks.inaccessible.InaccessibleClass
```

## LambdaStatement: NO_SUCH_TYPE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    lambda p : ThisTypeDoesNotExist => true;
}
```

**Output:**
```plain
Warning: NO_SUCH_TYPE
  File: file:/typechecks/T0207
  Line: #6
  Column: #16
  Message: The specified type does not exist.
  Type: ThisTypeDoesNotExist
```

## LambdaStatement: WRONG_TYPE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    lambda p : Predicate => 5;
}
```

**Output:**
```plain
Warning: WRONG_TYPE
  File: file:/typechecks/T0210
  Line: #6
  Column: #5
  Message: The value being returned must be assignable to the return-type.
  Return Type: boolean
  Actual Type: int
```

## LambdaStatement: WRONG_TYPE

**Source Code:**
```plain
module Main in typechecks;

functor Action () : void;

@Start
defun main (args : String[]) : void
{
    lambda p : Action => 5;
}
```

**Output:**
```plain
Warning: WRONG_TYPE
  File: file:/typechecks/T0211
  Line: #8
  Column: #5
  Message: The value being returned must be assignable to the return-type.
  Return Type: void
  Actual Type: int
```

## LetStatement: NO_SUCH_VARIABLE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    let moo = 100;
}
```

**Output:**
```plain
Warning: NO_SUCH_VARIABLE
  File: file:/typechecks/T0017
  Line: #6
  Column: #9
  Message: A variable was used before it was declared.
  Variable: moo
```

## ListComprehensionExpression: DUPLICATE_VARIABLE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    var x = 17;

    [ null for x : Object in [] ];
}
```

**Output:**
```plain
Warning: DUPLICATE_VARIABLE
  File: file:/typechecks/T0130
  Line: #8
  Column: #16
  Message: A variable was declared more than once in the same scope.
  Variable: x
```

## ListComprehensionExpression: EXPECTED_CONDITION

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    [ null for x : Object in [] if 17 ];
}
```

**Output:**
```plain
Warning: EXPECTED_CONDITION
  File: file:/typechecks/T0137
  Line: #6
  Column: #36
  Message: A boolean condition was expected.
  Expected: boolean OR java.lang.Boolean
  Actual: int
```

## ListComprehensionExpression: EXPECTED_ITERABLE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    [ null for x : Object in "A string is not an iterable." ];
}
```

**Output:**
```plain
Warning: EXPECTED_ITERABLE
  File: file:/typechecks/T0134
  Line: #6
  Column: #30
  Message: A java.lang.Iterable was expected.
```

## ListComprehensionExpression: EXPECTED_REFERENCE_TYPE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    [ null for x : int in [] ];
}
```

**Output:**
```plain
Warning: EXPECTED_REFERENCE_TYPE
  File: file:/typechecks/T0133
  Line: #6
  Column: #20
  Message: A reference-type was expected.
```

## ListComprehensionExpression: INACCESSIBLE_TYPE

**Source Code:**
```plain
module Main in typechecks;

import typechecks.inaccessible.InaccessibleClass;

@Start
defun main (args : String[]) : void
{
    [ null for x : InaccessibleClass in [] ];
}
```

**Output:**
```plain
Warning: INACCESSIBLE_TYPE
  File: file:/typechecks/T0132
  Line: #8
  Column: #20
  Message: The specified type is not accessible from where it is used.
  Type: typechecks.inaccessible.InaccessibleClass
```

## ListComprehensionExpression: NO_SUCH_TYPE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    [ null for x : ThisTypeDoesNotExist in [] ];
}
```

**Output:**
```plain
Warning: NO_SUCH_TYPE
  File: file:/typechecks/T0131
  Line: #6
  Column: #20
  Message: The specified type does not exist.
  Type: ThisTypeDoesNotExist
```

## ListComprehensionExpression: VALUE_REQUIRED

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    [ My::moo() for x : Object in [] ];
}

defun moo () : void
{
    nop;
}
```

**Output:**
```plain
Warning: VALUE_REQUIRED
  File: file:/typechecks/T0129
  Line: #6
  Column: #7
  Message: The type of an expression is void when it is forbidden to be.
```

## ListComprehensionExpression: VARIABLE_OUTSIDE_OF_SCOPE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    [ null for x : Object in x ];
}
```

**Output:**
```plain
Warning: NO_SUCH_VARIABLE
  File: file:/typechecks/T0135
  Line: #6
  Column: #30
  Message: A variable was used before it was declared.
  Variable: x
```

## ListComprehensionExpression: VARIABLE_OUTSIDE_OF_SCOPE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    [ null for x : Object in [] ];

    x;
}
```

**Output:**
```plain
Warning: VARIABLE_OUTSIDE_OF_SCOPE
  File: file:/typechecks/T0136
  Line: #8
  Column: #5
  Message: A variable was outside of its declared scope.
  Variable: x
```

## ListExpression: VALUE_REQUIRED

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    [My::moo()];
}

defun moo() : void
{
    // The purpose of this function is to return void.
}
```

**Output:**
```plain
Warning: VALUE_REQUIRED
  File: file:/typechecks/T0036
  Line: #6
  Column: #6
  Message: The type of an expression is void when it is forbidden to be.
```

## MarkerStatement: DUPLICATE_LABEL

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    goto Moo;
}
```

**Output:**
```plain
Warning: NO_SUCH_LABEL
  File: file:/typechecks/T0021
  Line: #6
  Column: #10
  Message: A label was used, but not declared.
  Label: Moo
```

## NewExpression: EXPECTED_CLASS_TYPE

**Source Code:**
```plain
module Main in typechecks;

import typechecks.inaccessible.InaccessibleClass;

@Start
defun main (args : String[]) : void
{
    new List();
}
```

**Output:**
```plain
Warning: EXPECTED_CLASS_TYPE
  File: file:/typechecks/T0065
  Line: #8
  Column: #9
  Message: A class-type was expected.
```

## NewExpression: INACCESSIBLE_TYPE

**Source Code:**
```plain
module Main in typechecks;

import typechecks.inaccessible.InaccessibleClass;

@Start
defun main (args : String[]) : void
{
    new InaccessibleClass();
}
```

**Output:**
```plain
Warning: INACCESSIBLE_TYPE
  File: file:/typechecks/T0064
  Line: #8
  Column: #9
  Message: The specified type is not accessible from where it is used.
  Type: typechecks.inaccessible.InaccessibleClass
```

## NewExpression: NO_SUCH_CONSTRUCTOR

**Source Code:**
```plain
module Main in typechecks;

import java.util.LinkedList;

@Start
defun main (args : String[]) : void
{
    new LinkedList("string of characters");
}
```

**Output:**
```plain
Warning: NO_SUCH_CONSTRUCTOR
  File: file:/typechecks/T0066
  Line: #8
  Column: #5
  Message: No accessible constructor was found that will accept the given arguments.
  Type: java.util.LinkedList
```

## NewExpression: NO_SUCH_TYPE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    new Moo();
}
```

**Output:**
```plain
Warning: NO_SUCH_TYPE
  File: file:/typechecks/T0063
  Line: #6
  Column: #9
  Message: The specified type does not exist.
  Type: Moo
```

## NullCoalescingOperation: EXPECTED_REFERENCE_TYPE

**Source Code:**
```plain
module * in typechecks;

@Start
defun main (args : String[]) : void
{
    2 ?? "Venus";
}
```

**Output:**
```plain
Warning: EXPECTED_REFERENCE_TYPE
  File: file:/typechecks/T0026
  Line: #6
  Column: #5
  Message: The type of a particular expression must be a reference-type.
```

## NullCoalescingOperation: EXPECTED_REFERENCE_TYPE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    ("Venus" ?? 3);
}
```

**Output:**
```plain
Warning: EXPECTED_REFERENCE_TYPE
  File: file:/typechecks/T0113
  Line: #6
  Column: #17
  Message: The type of a particular expression must be a reference-type.
```

## NullCoalescingOperation: INCOMPATIBLE_OPERANDS

**Source Code:**
```plain
module * in typechecks;

@Start
defun main (args : String[]) : void
{
    val x = null is Long;
    val y = null is String;

    x ?? y;
}
```

**Output:**
```plain
Warning: INCOMPATIBLE_OPERANDS
  File: file:/typechecks/T0027
  Line: #9
  Column: #7
  Message: One of the operands must be a subtype of the other.
  left.type: Long
  right.type: String
```

## OrOperation: EXPECTED_CONDITION

**Source Code:**
```plain
module * in typechecks;

@Start
defun main (args : String[]) : void
{
    2 | true;
}
```

**Output:**
```plain
Warning: EXPECTED_CONDITION
  File: file:/typechecks/T0030
  Line: #6
  Column: #5
  Message: A boolean condition was expected.
  Expected: boolean OR java.lang.Boolean
  Actual: int
```

## OrOperation: EXPECTED_CONDITION

**Source Code:**
```plain
module * in typechecks;

@Start
defun main (args : String[]) : void
{
    true | 3;
}
```

**Output:**
```plain
Warning: EXPECTED_CONDITION
  File: file:/typechecks/T0031
  Line: #6
  Column: #12
  Message: A boolean condition was expected.
  Expected: boolean OR java.lang.Boolean
  Actual: int
```

## RedoStatement: REDO_OUTSIDE_OF_LOOP

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    redo;
}
```

**Output:**
```plain
Warning: REDO_OUTSIDE_OF_LOOP
  File: file:/typechecks/T0074
  Line: #6
  Column: #5
  Message: A redo-statement can only be used inside of a loop construct.
```

## ReturnValueStatement: WRONG_TYPE

**Source Code:**
```plain
module Main in typechecks;

defun moo () : int
{
    return true;
}
```

**Output:**
```plain
Warning: WRONG_TYPE
  File: file:/typechecks/T0189
  Line: #5
  Column: #5
  Message: The value being returned must be assignable to the return-type.
  Return Type: int
  Actual Type: boolean
```

## ReturnVoidStatement: EXPECTED_VOID

**Source Code:**
```plain
module Main in typechecks;

defun moo () : int
{
    return;
}
```

**Output:**
```plain
Warning: EXPECTED_VOID
  File: file:/typechecks/T0187
  Line: #5
  Column: #5
  Message: Type void was expected.
```

## SetFieldExpression: ASSIGNMENT_TO_READONLY

**Source Code:**
```plain
module Main in typechecks;

import high.mackenzie.autumn.resources.InstanceFieldTester;

@Start
defun main (args : String[]) : void
{
    val object = new InstanceFieldTester();

    field object.answer = 3;
}
```

**Output:**
```plain
Warning: ASSIGNMENT_TO_READONLY
  File: file:/typechecks/T0100
  Line: #10
  Column: #5
  Message: A value cannot be assigned to a final field.
  Field: InstanceFieldTester.answer
```

## SetFieldExpression: EXPECTED_DECLARED_TYPE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    val x = 3;

    (field x.age = 42);
}
```

**Output:**
```plain
Warning: EXPECTED_DECLARED_TYPE
  File: file:/typechecks/T0098
  Line: #8
  Column: #12
  Message: A declared-type was expected.
```

## SetFieldExpression: IMPOSSIBLE_ASSIGNMENT

**Source Code:**
```plain
module Main in typechecks;

import high.mackenzie.autumn.resources.InstanceFieldTester;

@Start
defun main (args : String[]) : void
{
    val object = new InstanceFieldTester();

    # An integer cannot be assigned to a boolean field.
    field object.value1 = 13;
}
```

**Output:**
```plain
Warning: IMPOSSIBLE_ASSIGNMENT
  File: file:/typechecks/T0101
  Line: #11
  Column: #5
  Message: An impossible assignment operation was detected.
  Assignment: boolean (Target) = int (Value)
```

## SetFieldExpression: NO_SUCH_FIELD

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    val object = [];

    (field object.age = 42);
}
```

**Output:**
```plain
Warning: NO_SUCH_FIELD
  File: file:/typechecks/T0099
  Line: #8
  Column: #5
  Message: No acceptable field was found.
  Field: List.age
```

## SetStaticFieldExpression: ASSIGNMENT_TO_READONLY

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    (field Integer::MAX_VALUE = 42);
}
```

**Output:**
```plain
Warning: ASSIGNMENT_TO_READONLY
  File: file:/typechecks/T0097
  Line: #6
  Column: #5
  Message: A value cannot be assigned to a final field.
  Field: Integer::MAX_VALUE
```

## SetStaticFieldExpression: EXPECTED_DECLARED_TYPE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    (field String[]::age = 23);
}
```

**Output:**
```plain
Warning: EXPECTED_DECLARED_TYPE
  File: file:/typechecks/T0090
  Line: #6
  Column: #12
  Message: A declared-type was expected.
```

## SetStaticFieldExpression: IMPOSSIBLE_ASSIGNMENT

**Source Code:**
```plain
module Main in typechecks;

import high.mackenzie.autumn.resources.StaticFieldTester;

@Start
defun main (args : String[]) : void
{
    # An integer cannot be assigned to a boolean.

    (field StaticFieldTester::value1 = 23);
}
```

**Output:**
```plain
Warning: IMPOSSIBLE_ASSIGNMENT
  File: file:/typechecks/T0092
  Line: #10
  Column: #5
  Message: An impossible assignment operation was detected.
  Assignment: boolean (Target) = int (Value)
```

## SetStaticFieldExpression: INACCESSIBLE_TYPE

**Source Code:**
```plain
module Main in typechecks;

import typechecks.inaccessible.InaccessibleClass;

@Start
defun main (args : String[]) : void
{
    (field InaccessibleClass::age = 23);
}
```

**Output:**
```plain
Warning: INACCESSIBLE_TYPE
  File: file:/typechecks/T0089
  Line: #8
  Column: #12
  Message: The specified type is not accessible from where it is used.
  Type: typechecks.inaccessible.InaccessibleClass
```

## SetStaticFieldExpression: NO_SUCH_FIELD

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    (field String::age = 23);
}
```

**Output:**
```plain
Warning: NO_SUCH_FIELD
  File: file:/typechecks/T0091
  Line: #6
  Column: #5
  Message: No acceptable field was found.
  Field: String::age
```

## SetStaticFieldExpression: NO_SUCH_TYPE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    (field ThisTypeDoesNotExist::age = 23);
}
```

**Output:**
```plain
Warning: NO_SUCH_TYPE
  File: file:/typechecks/T0088
  Line: #6
  Column: #12
  Message: The specified type does not exist.
  Type: ThisTypeDoesNotExist
```

## StringDatum: MALFORMED_STRING_LITERAL

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    var x = "\a";
}
```

**Output:**
```plain
Warning: MALFORMED_STRING_LITERAL
  File: file:/typechecks/T0077
  Line: #6
  Column: #13
  Message: A non-verbatim string cannot contain malformed escape-sequences.
  Detail: Invalid Escape Sequence: \a
```

## StructDefinition: DUPLICATE_ANNOTATION

**Source Code:**
```plain
module Main in typechecks;

annotation Anno;

@Anno
@Anno
struct E ();
```

**Output:**
```plain
Warning: DUPLICATE_ANNOTATION
  File: file:/typechecks/T0155
  Line: #5
  Column: #1
  Message: An annotation can only occur once in a list of annotations.
  Type: typechecks.Anno
```

## StructDefinition: DUPLICATE_ELEMENT

**Source Code:**
```plain
module Main in typechecks;

struct Person (name : String,
               name : String);
```

**Output:**
```plain
Warning: DUPLICATE_ELEMENT
  File: file:/typechecks/T0125
  Line: #4
  Column: #16
  Message: A record can only declare an element once per definition
  Element: name
```

## StructDefinition: DUPLICATE_SUPERTYPE

**Source Code:**
```plain
module Main in typechecks;

struct Taxable ();

struct Person () extends Taxable & Taxable;
```

**Output:**
```plain
Warning: EXPECTED_INTERFACE_TYPE
  File: file:/typechecks/T0124
  Line: #5
  Column: #26
  Message: An interface-type was expected.
```

## StructDefinition: DUPLICATE_TYPE

**Source Code:**
```plain
module Main in typechecks;

struct Person ();

struct Person ();
```

**Output:**
```plain
Warning: DUPLICATE_TYPE
  File: file:/typechecks/T0121
  Line: #5
  Column: #8
  Message: The fully-qualified name of a type must be unique.
  Type: Person
```

## StructDefinition: EXPECTED_DESIGN_TYPE

**Source Code:**
```plain
module Main in typechecks;

struct Places () extends List;
```

**Output:**
```plain
Warning: EXPECTED_DESIGN_TYPE
  File: file:/typechecks/T0192
  Line: #3
  Column: #1
  Message: A design-type was expected.
```

## StructDefinition: EXPECTED_INTERFACE_TYPE

**Source Code:**
```plain
module Main in typechecks;

struct Name () extends String;
```

**Output:**
```plain
Warning: EXPECTED_INTERFACE_TYPE
  File: file:/typechecks/T0191
  Line: #3
  Column: #24
  Message: An interface-type was expected.
```

## StructDefinition: EXPECTED_VARIABLE_TYPE

**Source Code:**
```plain
module Main in typechecks;

struct Person (name : void);
```

**Output:**
```plain
Warning: EXPECTED_VARIABLE_TYPE
  File: file:/typechecks/T0195
  Line: #3
  Column: #23
  Message: A variable-type was expected.
```

## StructDefinition: INACCESSIBLE_TYPE

**Source Code:**
```plain
module Main in typechecks;

import typechecks.inaccessible.InaccessibleClass;

struct Person () extends InaccessibleClass;
```

**Output:**
```plain
Warning: INACCESSIBLE_TYPE
  File: file:/typechecks/T0123
  Line: #5
  Column: #26
  Message: The specified type is not accessible from where it is used.
  Type: typechecks.inaccessible.InaccessibleClass
```

## StructDefinition: INACCESSIBLE_TYPE

**Source Code:**
```plain
module Main in typechecks;

import typechecks.inaccessible.InaccessibleClass;

struct Person (name : InaccessibleClass);
```

**Output:**
```plain
Warning: INACCESSIBLE_TYPE
  File: file:/typechecks/T0194
  Line: #5
  Column: #23
  Message: The specified type is not accessible from where it is used.
  Type: typechecks.inaccessible.InaccessibleClass
```

## StructDefinition: NO_SUCH_TYPE

**Source Code:**
```plain
module Main in typechecks;

struct Person () extends ThisTypeDoesNotExist;
```

**Output:**
```plain
Warning: NO_SUCH_TYPE
  File: file:/typechecks/T0122
  Line: #3
  Column: #26
  Message: The specified type does not exist.
  Type: ThisTypeDoesNotExist
```

## StructDefinition: NO_SUCH_TYPE

**Source Code:**
```plain
module Main in typechecks;

struct Person (name : ThisTypeDoesNotExist);
```

**Output:**
```plain
Warning: NO_SUCH_TYPE
  File: file:/typechecks/T0193
  Line: #3
  Column: #23
  Message: The specified type does not exist.
  Type: ThisTypeDoesNotExist
```

## TernaryConditionalExpression: EXPECTED_CONDITION

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    (if 3 then 5 else 7);
}
```

**Output:**
```plain
Warning: EXPECTED_CONDITION
  File: file:/typechecks/T0112
  Line: #6
  Column: #9
  Message: A boolean condition was expected.
  Expected: boolean OR java.lang.Boolean
  Actual: int
```

## TernaryConditionalExpression: INCOMPATIBLE_OPERANDS

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    (if true then "Venus" else []);
}
```

**Output:**
```plain
Warning: INCOMPATIBLE_OPERANDS
  File: file:/typechecks/T0114
  Line: #6
  Column: #5
  Message: One of the operands must be a subtype of the other.
  left.type: String
  right.type: List
```

## TernaryConditionalExpression: VALUE_REQUIRED

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    (if true then My::moo() else 17);
}

defun moo () : void
{
    nop;
}
```

**Output:**
```plain
Warning: VALUE_REQUIRED
  File: file:/typechecks/T0115
  Line: #6
  Column: #19
  Message: The type of an expression is void when it is forbidden to be.
```

## TernaryConditionalExpression: VALUE_REQUIRED

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    (if true then 17 else My::moo());
}

defun moo () : void
{
    nop;
}
```

**Output:**
```plain
Warning: VALUE_REQUIRED
  File: file:/typechecks/T0116
  Line: #6
  Column: #27
  Message: The type of an expression is void when it is forbidden to be.
```

## ThrowStatement: EXPECTED_THROWABLE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    throw 123;
}
```

**Output:**
```plain
Warning: EXPECTED_THROWABLE
  File: file:/typechecks/T0010
  Line: #6
  Column: #11
  Message: A java.lang.Throwable was expected.
```

## TryCatchStatement: DUPLICATE_EXCEPTION_HANDLER

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    try
    {
        nop;
    }
    catch(x : RuntimeException)
    {
        nop;
    }
    catch(y : RuntimeException)
    {
        nop;
    }
}
```

**Output:**
```plain
Warning: DUPLICATE_EXCEPTION_HANDLER
  File: file:/typechecks/T0013
  Line: #10
  Column: #5
  Message: Exception handlers in a try-catch block share a type.
```

## TryCatchStatement: DUPLICATE_VARIABLE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    try
    {
        nop;
    }
    catch(ex : Exception)
    {
        nop;
    }
    catch(ex : RuntimeException)
    {
        nop;
    }
}
```

**Output:**
```plain
Warning: DUPLICATE_VARIABLE
  File: file:/typechecks/T0011
  Line: #14
  Column: #11
  Message: A variable was declared more than once in the same scope.
  Variable: ex
```

## TryCatchStatement: EXPECTED_THROWABLE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    try
    {
        nop;
    }
    catch(ex : String)
    {
        nop;
    }
}
```

**Output:**
```plain
Warning: EXPECTED_THROWABLE
  File: file:/typechecks/T0012
  Line: #10
  Column: #16
  Message: A java.lang.Throwable was expected.
```

## TryCatchStatement: INACCESSIBLE_TYPE

**Source Code:**
```plain
module Main in typechecks;

import typechecks.inaccessible.InaccessibleClass;

@Start
defun main (args : String[]) : void
{
    try
    {
        nop;
    }
    catch (x : InaccessibleClass)
    {
        nop;
    }
}
```

**Output:**
```plain
Warning: INACCESSIBLE_TYPE
  File: file:/typechecks/T0072
  Line: #12
  Column: #16
  Message: The specified type is not accessible from where it is used.
  Type: typechecks.inaccessible.InaccessibleClass
```

## TryCatchStatement: NO_SUCH_TYPE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    try
    {
        nop;
    }
    catch(x : Pluto)
    {
        nop;
    }
}
```

**Output:**
```plain
Warning: NO_SUCH_TYPE
  File: file:/typechecks/T0014
  Line: #10
  Column: #15
  Message: The specified type does not exist.
  Type: Pluto
```

## TryCatchStatement: VARIABLE_OUTSIDE_OF_SCOPE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    try
    {
        nop;
    }
    catch (x : Throwable)
    {
        nop;
    }

    x;
}
```

**Output:**
```plain
Warning: VARIABLE_OUTSIDE_OF_SCOPE
  File: file:/typechecks/T0071
  Line: #15
  Column: #5
  Message: A variable was outside of its declared scope.
  Variable: x
```

## TupleDefinition: COVARIANCE_VIOLATION

**Source Code:**
```plain
module Main in typechecks;

design Person (name : String);

tuple Employee (name : Object) extends Person;
```

**Output:**
```plain
Warning: COVARIANCE_VIOLATION
  File: file:/typechecks/T0205
  Line: #5
  Column: #1
  Message: A covariance violation was encountered.
  Element: name
  Upper: java.lang.String
  Lower: java.lang.Object
```

## TupleDefinition: DUPLICATE_ELEMENT

**Source Code:**
```plain
module Main in typechecks;

tuple Person (name : String,
              name : String);
```

**Output:**
```plain
Warning: DUPLICATE_ELEMENT
  File: file:/typechecks/T0201
  Line: #4
  Column: #15
  Message: A record can only declare an element once per definition
  Element: name
```

## TupleDefinition: DUPLICATE_SUPERTYPE

**Source Code:**
```plain
module Main in typechecks;

design Taxable ();

tuple Person () extends Taxable & Taxable;
```

**Output:**
```plain
Warning: DUPLICATE_SUPERTYPE
  File: file:/typechecks/T0200
  Line: #5
  Column: #1
  Message: A supertype can appear only once in an extends-clause.
  Super Type: typechecks.Taxable
```

## TupleDefinition: DUPLICATE_TYPE

**Source Code:**
```plain
module Main in typechecks;

tuple T ();

tuple T ();
```

**Output:**
```plain
Warning: DUPLICATE_TYPE
  File: file:/typechecks/T0169
  Line: #3
  Column: #7
  Message: The fully-qualified name of a type must be unique.
  Type: T
```

## TupleDefinition: EXPECTED_DESIGN_TYPE

**Source Code:**
```plain
module Main in typechecks;

tuple Places () extends List;
```

**Output:**
```plain
Warning: EXPECTED_DESIGN_TYPE
  File: file:/typechecks/T0199
  Line: #3
  Column: #1
  Message: A design-type was expected.
```

## TupleDefinition: EXPECTED_INTERFACE_TYPE

**Source Code:**
```plain
module Main in typechecks;

tuple Name () extends String;
```

**Output:**
```plain
Warning: EXPECTED_INTERFACE_TYPE
  File: file:/typechecks/T0198
  Line: #3
  Column: #23
  Message: An interface-type was expected.
```

## TupleDefinition: EXPECTED_VARIABLE_TYPE

**Source Code:**
```plain
module Main in typechecks;

tuple Person (name : void);
```

**Output:**
```plain
Warning: EXPECTED_VARIABLE_TYPE
  File: file:/typechecks/T0204
  Line: #3
  Column: #22
  Message: A variable-type was expected.
```

## TupleDefinition: INACCESSIBLE_TYPE

**Source Code:**
```plain
module Main in typechecks;

import typechecks.inaccessible.InaccessibleClass;

tuple Employee () extends InaccessibleClass;
```

**Output:**
```plain
Warning: INACCESSIBLE_TYPE
  File: file:/typechecks/T0197
  Line: #5
  Column: #27
  Message: The specified type is not accessible from where it is used.
  Type: typechecks.inaccessible.InaccessibleClass
```

## TupleDefinition: INACCESSIBLE_TYPE

**Source Code:**
```plain
module Main in typechecks;

import typechecks.inaccessible.InaccessibleClass;

tuple Person (name : InaccessibleClass);
```

**Output:**
```plain
Warning: INACCESSIBLE_TYPE
  File: file:/typechecks/T0203
  Line: #5
  Column: #22
  Message: The specified type is not accessible from where it is used.
  Type: typechecks.inaccessible.InaccessibleClass
```

## TupleDefinition: NO_SUCH_TYPE

**Source Code:**
```plain
module Main in typechecks;

tuple Employee () extends ThisTypeDoesNotExist;
```

**Output:**
```plain
Warning: NO_SUCH_TYPE
  File: file:/typechecks/T0196
  Line: #3
  Column: #27
  Message: The specified type does not exist.
  Type: ThisTypeDoesNotExist
```

## TupleDefinition: NO_SUCH_TYPE

**Source Code:**
```plain
module Main in typechecks;

tuple Person (name : ThisTypeDoesNotExist);
```

**Output:**
```plain
Warning: NO_SUCH_TYPE
  File: file:/typechecks/T0202
  Line: #3
  Column: #22
  Message: The specified type does not exist.
  Type: ThisTypeDoesNotExist
```

## UntilStatement: EXPECTED_CONDITION

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    until(123)
    {
        nop;
    }
}
```

**Output:**
```plain
Warning: EXPECTED_CONDITION
  File: file:/typechecks/T0004
  Line: #6
  Column: #11
  Message: A boolean condition was expected.
  Expected: boolean OR java.lang.Boolean
  Actual: int
```

## ValStatement: DUPLICATE_VARIABLE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    val moo = 100;
    val moo = 200;
}
```

**Output:**
```plain
Warning: DUPLICATE_VARIABLE
  File: file:/typechecks/T0016
  Line: #7
  Column: #9
  Message: A variable was declared more than once in the same scope.
  Variable: moo
```

## VarStatement: DUPLICATE_VARIABLE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    var moo = 100;
    var moo = 200;
}
```

**Output:**
```plain
Warning: DUPLICATE_VARIABLE
  File: file:/typechecks/T0015
  Line: #7
  Column: #9
  Message: A variable was declared more than once in the same scope.
  Variable: moo
```

## VariableDatum: NO_SUCH_VARIABLE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    moo;
}
```

**Output:**
```plain
Warning: NO_SUCH_VARIABLE
  File: file:/typechecks/T0080
  Line: #6
  Column: #5
  Message: A variable was used before it was declared.
  Variable: moo
```

## VariableDatum: VARIABLE_OUTSIDE_OF_SCOPE

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    # Create a nested scope.
    {
        val moo = 0;
    }

    moo;
}
```

**Output:**
```plain
Warning: VARIABLE_OUTSIDE_OF_SCOPE
  File: file:/typechecks/T0081
  Line: #11
  Column: #5
  Message: A variable was outside of its declared scope.
  Variable: moo
```

## WhenStatement: EXPECTED_CONDITION

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    throw 123;
}
```

**Output:**
```plain
Warning: EXPECTED_THROWABLE
  File: file:/typechecks/T0010
  Line: #6
  Column: #11
  Message: A java.lang.Throwable was expected.
```

## WhileStatement: EXPECTED_CONDITION

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    while(123)
    {
        nop;
    }
}
```

**Output:**
```plain
Warning: EXPECTED_CONDITION
  File: file:/typechecks/T0003
  Line: #6
  Column: #11
  Message: A boolean condition was expected.
  Expected: boolean OR java.lang.Boolean
  Actual: int
```

## XorOperation: EXPECTED_CONDITION

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    2 ^ true;
}
```

**Output:**
```plain
Warning: EXPECTED_CONDITION
  File: file:/typechecks/T0117
  Line: #6
  Column: #5
  Message: A boolean condition was expected.
  Expected: boolean OR java.lang.Boolean
  Actual: int
```

## XorOperation: EXPECTED_CONDITION

**Source Code:**
```plain
module Main in typechecks;

@Start
defun main (args : String[]) : void
{
    true ^ 2;
}
```

**Output:**
```plain
Warning: EXPECTED_CONDITION
  File: file:/typechecks/T0118
  Line: #6
  Column: #12
  Message: A boolean condition was expected.
  Expected: boolean OR java.lang.Boolean
  Actual: int
```

