# Doc Comment Line

## Summary

A doc-comment-line that is a single line in a doc-comment.

## Syntax

<div class="syntax">
/// <i>text</i><br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.DocCommentLine](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/DocCommentLine.html)

## Example

**Source Code:**

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

