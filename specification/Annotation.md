# Annotation

## Summary

An annotation allows metadata to be applied to a module, function, etc.

## Syntax

<div class="syntax">
@<i><a href="TypeSpecifier.md">type</a></i><br>
<hr><br>
@<i><a href="TypeSpecifier.md">type</a></i> ( <i><a href="String_Datum.md">string</a><sub>1</sub></i> , ... , <i><a href="String_Datum.md">string</a><sub>n</sub></i> )<br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.Annotation](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/Annotation.html)

## Example

**Source Code:**

```plain
module Main in execution;

/// This is the definition of a new annotation type. 
/// 
annotation Location;



/// This function has three annotations applied to it. 
@Author ("Mackenzie High")
@Location ("United States" , "Virginia")
@Hide
@Start
defun main (args : String[]) : void
{
    # Get the reflection of this function. 
    val function = F::findMethod(class Main, "main", [class String[]]);

    # Get the author and location annotations that are applied thereto. 
    val anno1 = F::findAnnotation(function, class Author);
    val anno2 = F::findAnnotation(function, class Location);

    # Retrieve the information about the author. 
    val name = F::get(anno1, 0);
    val country = F::get(anno2, 0);
    val state = F::get(anno2, 1);

    # Print the information to standard-output. 
    F::println("Author: " .. name);
    F::println("Country: " .. country);
    F::println("State: " .. state);
}
```

**Output:**

```plain
Author: Mackenzie High
Country: United States
State: Virginia
```

