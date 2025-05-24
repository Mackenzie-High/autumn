# If-Then Statement

## Summary

An if-statement conditionally executes one or more other statements.

## Syntax

```plain
<span class=\"keyword\">if</span> ( <i>[condition](TextPage.html?page=Expression)<sub>1</sub></i> )
{
    <i>body<sub>1</sub></i>
}
<span class=\"keyword\">elif</span> ( <i>[condition](TextPage.html?page=Expression)<sub>2</sub></i> )<sub>1</sub>
{
    <i>body<sub>2</sub></i>
}
<span class=\"keyword\">elif</span> ( <i>[condition](TextPage.html?page=Expression)<sub>3</sub></i> )<sub>2</sub>
{
    <i>body<sub>3</sub></i>
}
<span class=\"keyword\">elif</span> ( <i>[condition](TextPage.html?page=Expression)<sub>4</sub></i> )<sub>n</sub>
{
    <i>body<sub>4</sub></i>
}
<span class=\"keyword\">else</span><sub>opt</sub>
{
    <i>body<sub>5</sub></i>
}
```

## AST Class

autumn.lang.compiler.ast.nodes.IfStatement

## Details
+ An if-statement can have zero or more elif-clauses.
+ An if-statement can omit the else-clause.
+ For all <i>i</i> &gt; 1, <i>condition<sub>i</sub></i> will be evaluated, only if <i>condition<sub>i - 1</sub></i> produces false.
+ For all <i>i</i> &gt; 0, <i>body<sub>i</sub></i> will only be executed, only if <i>condition<sub>i</sub></i> produces true.
+ The body of the else-clause will be executed, only if all of the conditions produce false.
+ The conditions will be unboxed, if necessary.

