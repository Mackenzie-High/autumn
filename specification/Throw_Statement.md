# Throw Statement

## Summary

A throw-statement throws an exception that is provided as an argument thereto.

## Syntax

<div class="syntax">
<b>throw</b> <i><a href="Expression.md">argument</a></i> ;<br>
</div>

## AST Class

[autumn.lang.compiler.ast.nodes.ThrowStatement](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/ast/nodes/ThrowStatement.html)

## Details

+ In order to catch an exception, use a <a href="Try_Catch_Statement.md">Try-Catch Statement</a>.
+ An uncaught exception will cause the enclosing invocation to terminate.
  + An uncaught exception will propogate until it is caught.
  + If an exception is caught by the runtime, then the program will be terminated.
+ Runtime Check: A [NullPointerException](https://docs.oracle.com/javase/7/docs/api/java/lang/NullPointerException.html) will be thrown, if the <i>argument</i> is null.

## Static Checks

+ [EXPECTED_THROWABLE](https://www.mackenziehigh.com/autumn/javadoc/autumn/lang/compiler/errors/ErrorCode.html#EXPECTED_THROWABLE): The type of <i>argument</i> must be assignable to type java.lang.Throwable.

## Example 1

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    # This example shows how to throw and catch an exception. 

    try
    {
        F::println ("before");

        throw new Exception();

        F::println ("after");
    }
    catch (ex : Exception)
    {
        F::println ("handle");
    }
}
```

**Output:**

```plain
before
handle
```

## Example 2

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    # An exception can cause an invocation to terminate early.

    try
    {
        F::println ("before");

        Main::trouble();

        F::println ("after");
    }
    catch (ex : Exception)
    {
        F::println ("handle");
    }
}

defun trouble () : void
{
    F::println ("enter");

    throw new Exception();

    F::println ("exit");
}
```

**Output:**

```plain
before
enter
handle
```

## Example 3

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    # Exceptions can propagate through multiple invocations.

    try
    {
        F::println ("before");

        Main::trouble(0);

        F::println ("after");
    }
    catch (ex : Exception)
    {
        F::println ("handle");
    }
}

defun trouble (n : int) : void
{
    F::println ("enter");

    if (n == 5)
    {
        throw new Exception();
    }
    else
    {
        Main::trouble(n + 1);
    }

    F::println ("exit");
}
```

**Output:**

```plain
before
enter
enter
enter
enter
enter
enter
handle
```

## Example 4

**Source Code:**

```plain
module Main in examples;

@Start
defun main (args : String[]) : void
{
    # This throw will crash the entire program,
    # because the exception is caught by the runtime.

    throw new Exception();
}
```

**Output:**

```plain
java.lang.Exception
	at examples.Main.main(file:/media/disk/Code/EclipseProjects/AutumnSpecification/autumn/examples/E0099/project/src/Main.leaf:9)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:606)
	at autumn.lang.compiler.DynamicLoader.invokeMain(DynamicLoader.java:121)
	at autumn.lang.compiler.Autumn.run(Autumn.java:393)
	at autumn.lang.compiler.AutumnProject.run(AutumnProject.java:180)
	at high.mackenzie.autumn.lang.compiler.args.Visitor$1.invoke(Visitor.java:312)
	at high.mackenzie.autumn.lang.compiler.args.Visitor.run(Visitor.java:176)
	at high.mackenzie.autumn.lang.compiler.args.Visitor.visit_case_run(Visitor.java:316)
	at high.mackenzie.autumn.lang.compiler.args.AbstractVisitor.visit(AbstractVisitor.java:32)
	at high.mackenzie.autumn.lang.compiler.args.Visitor.visitChildren(Visitor.java:67)
	at high.mackenzie.autumn.lang.compiler.args.Visitor.visitUnknown(Visitor.java:241)
	at high.mackenzie.autumn.lang.compiler.args.AbstractVisitor.visit_cases(AbstractVisitor.java:326)
	at high.mackenzie.autumn.lang.compiler.args.AbstractVisitor.visit(AbstractVisitor.java:25)
	at high.mackenzie.autumn.lang.compiler.args.Visitor.visitChildren(Visitor.java:67)
	at high.mackenzie.autumn.lang.compiler.args.Visitor.visitUnknown(Visitor.java:241)
	at high.mackenzie.autumn.lang.compiler.args.AbstractVisitor.visit_accept(AbstractVisitor.java:206)
	at high.mackenzie.autumn.lang.compiler.args.AbstractVisitor.visit(AbstractVisitor.java:24)
	at high.mackenzie.autumn.lang.compiler.args.Visitor.visitChildren(Visitor.java:67)
	at high.mackenzie.autumn.lang.compiler.args.Visitor.visitUnknown(Visitor.java:241)
	at high.mackenzie.autumn.lang.compiler.args.AbstractVisitor.visit(AbstractVisitor.java:56)
	at high.mackenzie.autumn.lang.compiler.args.Visitor.visitChildren(Visitor.java:67)
	at high.mackenzie.autumn.lang.compiler.args.Visitor.visitUnknown(Visitor.java:241)
	at high.mackenzie.autumn.lang.compiler.args.AbstractVisitor.visit_line(AbstractVisitor.java:346)
	at high.mackenzie.autumn.lang.compiler.args.AbstractVisitor.visit(AbstractVisitor.java:23)
	at high.mackenzie.autumn.Main.main(Main.java:46)
```

