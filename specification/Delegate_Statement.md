# Delegate Statement

## Summary

(Under Development) - A delegate-statement creates function-object that is essentially a type-safe function-pointer.

## Syntax

<div class="syntax">
<span class="keyword">delegate</span> <a class="synvar" href="Variable.md">assignee</a></i> : <a class="synvar" href="TypeSpecifier.md">type</a></i> => <a class="synvar" href="TypeSpecifier.md">Owner</a></i>::<a class="synvar" href="Name.md">name</a></i> ;<br>
</div>

## AST Class

autumn.lang.compiler.ast.nodes.DelegateStatement

## Details

+ The <i>assignee</i> will be declared in the enclosing scope as a readonly local variable.
+ The scope of the <i>assignee</i> is anywhere in the enclosing function.
+ The <i>assignee</i> is alive precisely during an activation of the enclosing function.
+ A delegate-statement can create a delegate that refers to the special instance() method.
+ The function <i>X</i> that the delegate will refer to must be compatible with the functor-type <i>T</i>.
  + <i>T</i> is the functor-type specified by the <i>type</i>.
  + The return-type of <i>X</i> must be a subtype of the return-type of <i>T</i>.
  + The number of parameters of <i>X</i> must match the number of parameters of <i>T</i>.
  + The type of each parameter<sub>i</sub> of <i>X</i> must be a subtype of parameter<sub>i</sub> of <i>T</i>.
+ Return Type: type of <i>type</i>
+ Return a [Delegate](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Delegate.html) that refers to the specified function.

## Static Checks

[DUPLICATE_VARIABLE, The <i>assignee</i> cannot share its name with another variable declared in the same scope., null]
[NO_SUCH_TYPE, The type specified by <i>type</i> must exist., null]
[INACCESSIBLE_TYPE, The type specified by <i>type</i> must be accessible., null]
[EXPECTED_DEFINED_FUNCTOR_TYPE, The type specified by <i>type</i> must be a subtype of [DefinedFunctor](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/DefinedFunctor.html)., null]
[EXPECTED_CLASS_TYPE, The type specified by <i>type</i> must be a class-type., null]
[NO_SUCH_TYPE, The type specified by <i>Owner</i> must exist., null]
[INACCESSIBLE_TYPE, The type specified by <i>Owner</i> must be accessible., null]
[EXPECTED_MODULE_TYPE, The type specified by <i>Owner</i> must be a subtype of [Module](https://mackenzie-high.github.io/autumn/javadoc/autumn/lang/Module.html)., null]
[NO_SUCH_METHOD, The type specified by <i>Owner</i> must contain a function with the given <i>name</i>., null]
[OVERLOADED_METHOD, The <i>name</i> can only refer to one function in the type specified by <i>Owner</i>., null]
[INCOMPATIBLE_DELEGATE, The signature of the targeted function must be compatible with the functor-type specified by <i>type</i>., null]

## Example

**Code:**

```plain
module Main in execution;

functor TaxCalculator (income : int) : int;

@Start
defun main (args : String[]) : void
{
    delegate poor : TaxCalculator => My::poorTax;
    delegate rich : TaxCalculator => My::richTax;

    My::printTax(10_000, poor);
    My::printTax(20_000, poor);
    My::printTax(30_000, poor);

    My::printTax(100_000, rich);
    My::printTax(200_000, rich);
    My::printTax(300_000, rich);
}

defun printTax (income : int, 
                irs : TaxCalculator) : void
{
    val tax = irs.invoke(income);

    F::println("Income = " .. income);
    F::println("Tax = " .. tax);
    F::println();
}

defun poorTax (income : int) : int
{
    // Flat Rat = 10%
    return income / 10;
}

defun richTax (income : int) : int
{
    // Flat Rat = 25%
    return income / 4;
}
```

**Output:**

```plain
Income = 10000
Tax = 1000

Income = 20000
Tax = 2000

Income = 30000
Tax = 3000

Income = 100000
Tax = 25000

Income = 200000
Tax = 50000

Income = 300000
Tax = 75000
```

<style>
    .syntax
    {
        font-family: monospace, monospace;
    }

    .keyword
    {
        color: blue;
        font-weight: bold;
    }

    .synvar
    {
        font-style: italic;
    }
</style>

