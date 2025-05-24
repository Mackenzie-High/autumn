# String Datum

## Summary

A string-datum is a literal text value.

## Syntax

```plain
%22<i>string of characters</i>%22
<hr class=&#92%22syntax-hr&#92%22>
@ %22<i>string of characters</i>%22
<hr class=&#92%22syntax-hr&#92%22>
'''<i>string of characters</i>'''
<hr class=&#92%22syntax-hr&#92%22>
@ '''<i>string of characters</i>'''
```

## AST Class

autumn.lang.compiler.ast.nodes.StringDatum

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

