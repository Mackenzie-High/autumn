 
# Type Systen

Autumn is a statically-typed language using a type-system that is closely related to the JVM type-system. <br>
In fact, one must understand the JVM type-stystem in order to fully understand the Autumn type-system.

## Hierarchy of Types

<img id="type-structure-image" alt="Diagram of Type Hierarchy" src="dot/TypeHeirarchy.png">

## Primitive Types

+ boolean
+ char
+ byte
+ short
+ int
+ long
+ float
+ double

## Structure of a Declared Type
+ *superclass*
+ *superinterfaces*
+ *fields*
+ *constructors*
+ *methods*

## Structure of an Annotation Type
+ The *superclass* is always $Object$.
+ The list of *superinterface* contains only $Annotation$. 
+ The list of *fields* is empty. 
+ The list of *constructors* is empty. 
+ The list of *methods* contains one method only specifically *value() : String*.

## Structure of a Class Type
+ *superclass*
+ *superinterfaces*
+ *fields*
+ *constructors*
+ *methods*

## Structure of an Enum Type
+ *superclass*
+ *superinterfaces*
+ *fields*
+ *constructors*
+ *methods*
+ *enum-constants*

## Structure of an Interface Type
+ The *superclass* is always $Object$.
+ *superinterfaces*
+ *fields*
+ *constructors*
+ *methods*

## Structure of an Array Type
+ *element-type*
+ *dimensions*

## Structure of a Field
+ *modifiers*
__+ *public* | *protected* | *package* | *private*
__+ *static*
__+ *final*
+ *name*
+ *type*

## Structure of a Constructor
+ *modifiers*
__+ *public* | *protected* | *package* | *private*
__+ *static*
__+ *final*
+ *parameters*
+ *throws-clause*

## Structure of a Method
+ *modifiers*
__+ *public* | *protected* | *package* | *private*
__+ *static*
__+ *final*
+ *name*
+ *parameters*
+ *return-type*
+ *throws-clause*










