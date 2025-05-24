# Variable

## Summary

A variable construct is used to represent a local variable.

## Syntax

<div class="syntax">
<i>name</i><br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.Variable](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/Variable.html)

## Details

+ The <i>name</i> is a sequence of letters, digits, underscores, and/or dollar signs.
+ The <i>name</i> cannot start with a digit.
+ The <i>name</i> is case-sensitive.

## Example

**Source Code:**

```plain
module Main in execution;

@Start
defun main (args : String[]) : void
{
    # Here are some example variables. 
    var age = 0;
    var Tree = 0;
    var _submarine = 0;
    var ZipCode = 0;
    var state17 = 0;
}
```

