# Doc Comment

## Summary

A doc-comment is a comment that can be processed by documentation generators.

## Syntax

<div id="syntax">
<i>[Doc Comment Line](ConstructPage.html?construct=Doc Comment Line)</i><br>
<i>[Doc Comment Line](ConstructPage.html?construct=Doc Comment Line)</i><br>
<i>[Doc Comment Line](ConstructPage.html?construct=Doc Comment Line)</i><br>
</div>

## AST Class

autumn.lang.compiler.ast.nodes.DocComment

## Details

+ Unlike regular comments, doc-comments are part of the Abstract-Syntax-Tree of a program.

## Static Checks


## Example

**Code:**

```plain
module Main in execution;

/// This is an example of a doc-comment. 
/// As you can see, each line of the comment starts with three slashes. 
/// These comments will embedded in the program's Abstract-Syntax-Tree. 
/// Thus, a documentation generator can obtain these comments. 
/// As a result, doc-comments can be transformed into HTML, etc. 
/// At least, that is the plan for a future version of Autumn.
///  
@Start
defun main (args : String[]) : void
{
    return;
}
```

**Output:**

```plain

```

