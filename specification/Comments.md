# Comments

## Summary

Comments allow programmers to leave notes inside of code.

## Syntax

<div class="syntax">
//&nbsp;&nbsp;&nbsp;<i>body of single-line comment</i><br>
<hr><br>
#&nbsp;&nbsp;&nbsp;<i>body of single-line comment</i><br>
<hr><br>
/*&nbsp;&nbsp;&nbsp;<i>body of multi-line comment</i>&nbsp;&nbsp;&nbsp;*/<br>
</div>

## Details

+ Comments are simply ignored by the compiler.

## Example

**Source Code:**

```plain
module Main in execution;


@Start
defun main (args : String[]) : void
{
    # This is a single-line comment. 
    nop;

    // This is also a single-line comment. 
    nop;

    /* This is a multi-line comment. */
    nop;

    /*
     This is also a multi-line comment, but is badly formatted. 
     */
    nop;

    /*
     * This is a nicely formatted multi-line comment. 
     */
    nop;
}
```

