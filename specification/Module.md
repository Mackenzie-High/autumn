# Module

## Summary

A module creates a new module-type in the enclosing package.

## Syntax

<div class="syntax">
<i><a href="Module_Member.md">module-member</a><sub>1</sub></i><br>
<i><a href="Module_Member.md">module-member</a><sub>2</sub></i><br>
<i><a href="Module_Member.md">module-member</a><sub>n</sub></i><br>
</div>

## AST Class

[autumn.lang.Module](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/Module.html)

## Details

+ A module is a component of a compilation-unit that usually corresponds to a source file.
+ Regarding the module-type T created by a definition M:
  + T is a form of class-type.
  + T has the [ModuleDefinition](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/internals/annotations/ModuleDefinition.html) annotation applied directly to it.
  + T is both [public](https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/Modifier.html#PUBLIC) and [final](https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/Modifier.html#FINAL).
  + T's fully-qualified name is obtained from the one and only module-directive in M.
  + T is a subtype of [Module](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Module.html).
  + T is a subtype of [AbstractModule](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/internals/AbstractModule.html).
  + T does not define any [public](https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/Modifier.html#PUBLIC) constructors.
    + This is because an instance of a module is a singleton object.
  + T defines a special method: instance() : T
    + This method is [public](https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/Modifier.html#PUBLIC) and [static](https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/Modifier.html#STATIC) and [final](https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/Modifier.html#FINAL).
    + This method always returns the singleton instance of the module.
  + T defines a special method: info () : [ModuleInfo](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/ModuleInfo.html)
    + This method is [public](https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/Modifier.html#PUBLIC) and [final](https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/Modifier.html#FINAL).
    + This method returns a singleton object that describes the module.
  + T defines a special method: invoke ([ArgumentStack](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/internals/ArgumentStack.html), int) : [Object](https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html)
    + This method is [public](https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/Modifier.html#PUBLIC) and [final](https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/Modifier.html#FINAL).
      + This method is a low-level method that is not intended for direct use by programmers.
      + This method is used to implement delegates.
  + T inherits the following method declarations from its supertypes.
    + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#equals(java.lang.Object)'>equals ([Object](https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html))</a>
    + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#getClass()'>getClass ()</a>
    + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#hashCode()'>hashCode ()</a>
    + <a href='https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Module.html#info()'>info ()</a>
    + <a href='https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Module.html#invoke(int, autumn.lang.internals.ArgumentStack)'>invoke (int, [ArgumentStack](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/internals/ArgumentStack.html))</a>
    + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#notify()'>notify ()</a>
    + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#notifyAll()'>notifyAll ()</a>
    + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#toString()'>toString ()</a>
    + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#wait()'>wait ()</a>
    + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#wait(long)'>wait (long)</a>
    + <a href='https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#wait(long, int)'>wait (long, int)</a>

## Static Checks

+ [MISSING_MODULE_DIRECTIVE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#MISSING_MODULE_DIRECTIVE): A module must contain a module-directive.
+ [DUPLICATE_MODULE_DIRECTIVE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#DUPLICATE_MODULE_DIRECTIVE): A module can only contain one module-directive.
+ [DUPLICATE_TYPE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#DUPLICATE_TYPE): No two types can share the same descriptor.
+ [DUPLICATE_FUNCTION](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#DUPLICATE_FUNCTION): No two functions in the same module can share their name and parameter-list descriptor.
+ [NAME_CONFLICT](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#NAME_CONFLICT): The name of a user-defined function cannot also be the name of a predefined [static](https://docs.oracle.com/javase/7/docs/api/java/lang/reflect/Modifier.html#STATIC) method.

## Example

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    F::println("Hello World!");
}
```

**Output:**

```plain
Hello World!
```

