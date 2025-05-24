# Name

## Summary

A name construct is used to represent a name that does not denote a type.

## Syntax

<div id="syntax">
<i>name</i><br>
</div>

## AST Class

autumn.lang.compiler.ast.nodes.Name

## Details

+ A <i>name</i> is a sequence of letters, digits, underscores, and/or dollar signs.
+ A <i>name</i> cannot start with a digit.
+ A <i>name</i> is case-sensitive.

## Static Checks


## Example

**Code:**

```plain
module Main in execution;

/// This little tuple has a name that starts with an underscore.
tuple _P ();

/// This little tuple does not. 
tuple Q ();

/// This little tuple has a name containing digits.
tuple T2995 ();

/// This little tuple does not. 
tuple Big_Bad_Wolf ();

/// This little tuple has a name in CamelCase. 
tuple ThreeLittlePigs ();
```

**Output:**

```plain

```

