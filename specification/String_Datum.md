# String Datum

## Summary

A string-datum is a literal text value.

## Syntax

<div class="syntax">
%22<i>string of characters</i>%22<br>
<hr><br>
@ %22<i>string of characters</i>%22<br>
<hr><br>
'''<i>string of characters</i>'''<br>
<hr><br>
@ '''<i>string of characters</i>'''<br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.StringDatum](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/StringDatum.html)

## Details

+ Escape Sequences
  + Tab: &#92;t
  + Backspace: &#92;b
  + Newline: &#92;n
  + Carriage Return: &#92;r
  + Form Feed: &#92;f
  + Single Quote: &#92;'
  + Double Quote: &#92;&#34;
  + Backslash: &#92;&#92;
  + Character Code: &#92;<i>D<sub>1</sub>D<sub>2</sub>D<sub>3</sub>D<sub>4</sub>D<sub>5</sub></i> (where <i>D<sub>i</sub></i> is a decimal digit)
    + The character-code must be between 0 and 65536.
+ The two syntactic forms that are prefixed with an '@' are verbose-strings.
+ Escape sequences are ignored in verbose strings.
+ String-literals can span multiple lines.
+ Return Type: [String](https://docs.oracle.com/javase/7/docs/api/java/lang/String.html)
+ Return the value of the constant.

## Static Checks

+ [MALFORMED_STRING_LITERAL](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#MALFORMED_STRING_LITERAL): A non-verbatim string cannot contain malformed escape-sequences.

## Example

**Source Code:**

```plain
module Main in examples;

tuple Pet (type : String, name : String);

@Start
defun main (args : String[]) : void
{
    val string1 = "C:\\planets\\Mercury.obj";
    val string2 = @"C:\planets\Venus.obj";

    val string3 = '''C:\\planets\\Earth.obj''';
    val string4 = @'''C:\planets\Mars.obj''';

    F::printlns([string1, string2, string3, string4]);
}
```

**Output:**

```plain
C:\planets\Mercury.obj
C:\planets\Venus.obj
C:\planets\Earth.obj
C:\planets\Mars.obj
```

