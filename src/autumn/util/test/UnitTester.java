package autumn.util.test;

import autumn.lang.Delegate;
import autumn.lang.Module;
import autumn.util.F;
import autumn.util.Reflect;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import high.mackenzie.autumn.resources.Finished;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/**
 * An instance of this class runs unit-tests contained in a group of modules.
 *
 * @author Mackenzie High
 */
@Finished("2014/08/21")
public final class UnitTester
        implements Tester
{
    /**
     * These are the modules that contain test functions.
     */
    private Set<Module> modules = Sets.newHashSet();

    /**
     * This method adds a module to the set of modules that will be unit-tested.
     *
     * <p>
     * The singleton instance of the module will be retrieved via reflection.
     * </p>
     *
     * @param module is the class-object that represents the type of the module.
     * @throws NullPointerException if module is null.
     * @throws IllegalArgumentException if an instance of the module could not be obtained.
     */
    public void add(final Class module)
    {
        Preconditions.checkNotNull(module);

        try
        {
            final Method method = module.getDeclaredMethod("instance");

            final Module instance = (Module) method.invoke(null);

            add(instance);
        }
        catch (NoSuchMethodException ex)
        {
            throw new IllegalArgumentException("No instance() method was found.", ex);
        }
        catch (IllegalAccessException ex)
        {
            throw new IllegalArgumentException("The instance() method is inaccessible.", ex);
        }
        catch (InvocationTargetException ex)
        {
            // This should never happen in reality.
            throw new IllegalArgumentException("Something went wrong in the instance() method.", ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(final Module module)
    {
        Preconditions.checkNotNull(module);

        modules.add(module);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Module> modules()
    {
        return Collections.unmodifiableSet(modules);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TestResults run()
    {
        // The test-results will be added to this list as the test-cases are run.
        final List<TestResult> results = Lists.newLinkedList();

        // This will be used to count the number of failed tests.
        final AtomicInteger failed = new AtomicInteger(0);

        // This will be used to sum the amount of time that the test-cases spent executing.
        final AtomicLong time = new AtomicLong(0);

        /**
         * Find and execute the test-cases.
         */
        for (Module module : modules)
        {
            test(results, failed, time, module);
        }

        /**
         * Create the object that describes the test-results.
         */
        return new TestResults()
        {
            @Override
            public boolean passed()
            {
                return !failed();
            }

            @Override
            public boolean failed()
            {
                return failed.get() != 0;
            }

            @Override
            public long executionTime()
            {
                return time.get();
            }

            @Override
            public void print(PrintStream out)
            {
                for (TestResult result : results)
                {
                    result.print(out);
                    out.println();
                }

                out.println("Execution Time = " + executionTime() + " Milliseconds");
                out.println();

                // Print the number of test-cases that passed and failed.
                out.println(this);
                out.println();
            }

            @Override
            public TestResult find(final String name)
            {
                Preconditions.checkNotNull(name);

                for (TestResult result : results())
                {
                    if (result.name().equals(name))
                    {
                        return result;
                    }
                }

                throw new NoSuchElementException(name);
            }

            @Override
            public List<TestResult> results()
            {
                return Collections.unmodifiableList(results);
            }

            @Override
            public Iterator<TestResult> iterator()
            {
                return results().iterator();
            }

            @Override
            public String toString()
            {
                // This is the number of test-cases that failed.
                final int d2 = failed.get();

                // This is the number of test-cases that passed.
                final int d1 = d2 - results.size();

                return d2 == 0
                        ? "All Tests Passed!"
                        : String.format("Test Results: Passed = %d, Failed = %d", d1, d2);
            }
        };
    }

    /**
     * This method runs the test functions in a single module.
     *
     * @param results is the mutable list to append the result onto.
     * @param failed is the running tally of failed test-cases.
     * @param time is the running sum of the execution times.
     * @param module is the module that contains the test-cases.
     */
    private void test(final List<TestResult> results,
                      final AtomicInteger failed,
                      final AtomicLong time,
                      final Module module)
    {
        for (Delegate function : module.moduleFunctions())
        {
            // Get the reflective view of the function.
            final Method method = function.method();

            // The function is a test-case, if the @Test annotation is applied to it.
            if (method.isAnnotationPresent(Test.class))
            {
                // Execute the test-case.
                test(results, failed, time, function);
            }
        }
    }

    /**
     * This method runs the a single test-case.
     *
     * @param results is the mutable list to append the result onto.
     * @param failed is the running tally of failed test-cases.
     * @param time is the running sum of the execution times.
     * @param delegate is a delegate that refers to the test function.
     */
    private void test(final List<TestResult> results,
                      final AtomicInteger failed,
                      final AtomicLong time,
                      final Delegate function)
    {

        // This will store the name of the test-case.
        final AtomicReference<String> test_name = new AtomicReference<String>();

        // This will store the description of the test-case.
        final AtomicReference<String> test_description = new AtomicReference<String>();

        // This will store the expected type of exception.
        final AtomicReference<Class> test_exception = new AtomicReference<Class>();

        // The default name of the test-case is of the form <module-class>::<function>.
        test_name.set(function.module().getClass().getSimpleName() + "::" + function.name());

        /**
         * This class provides a concrete implementation of the TestCase interface.
         */
        final TestCase testcase = new TestCase()
        {
            @Override
            public Delegate function()
            {
                return function;
            }

            @Override
            public void name(final String name)
            {
                Preconditions.checkNotNull(name);

                test_name.set(name);
            }

            @Override
            public void describe(final String text)
            {
                test_description.set(text);
            }

            @Override
            public void expect(final Class expected)
            {
                test_exception.set(expected);
            }

            @Override
            public String toString()
            {
                return "Test Case: " + test_name.get();
            }
        };

        long start_time = 0;
        Throwable ex = null;

        /**
         * Execute the test-case.
         */
        try
        {
            start_time = System.currentTimeMillis();

            validate(function);

            F.apply(function, Collections.singleton(testcase));
        }
        catch (Throwable t)
        {
            ex = t;
        }

        // Record the exception that was thrown, if any.
        final Throwable thrown = ex;

        // Calculate the total time this test-case spent executing.
        final long total_time = System.currentTimeMillis() - start_time;

        // Add the time this test-case spent executing to the total execution time.
        time.addAndGet(total_time);

        // Determine whether the test-case passed.
        final boolean case1 = thrown != null && test_exception.get() != null && Reflect.isSubtypeOf(thrown.getClass(), test_exception.get());
        final boolean case2 = thrown == null && test_exception.get() == null;
        final boolean passed = case1 || case2;

        // If the test failed, record the failure.
        failed.set(passed ? failed.get() : failed.get() + 1);

        /**
         * Create the object that represents the result of the test-case.
         */
        final TestResult result = new TestResult()
        {
            @Override
            public Delegate function()
            {
                return function;
            }

            @Override
            public String name()
            {
                return test_name.get();
            }

            @Override
            public String description()
            {
                return test_description.get();
            }

            @Override
            public Class expected()
            {
                return test_exception.get();
            }

            @Override
            public Throwable thrown()
            {
                return thrown;
            }

            @Override
            public boolean passed()
            {
                return passed;
            }

            @Override
            public boolean failed()
            {
                return !passed;
            }

            @Override
            public long executionTime()
            {
                return total_time;
            }

            @Override
            public void print(final PrintStream out)
            {
                if (passed())
                {
                    out.println("Test Passed: " + name());
                }
                else
                {
                    out.println("Test Failed: " + name());
                    thrown().printStackTrace(out);
                }
            }

            @Override
            public String toString()
            {
                return (passed() ? "Test Passed: " : "Test Failed: ") + name();
            }
        };

        // Record the result.
        results.add(result);
    }

    /**
     * This method ensures that a test function is acceptable.
     *
     * @param function is the function to validate.
     */
    private void validate(final Delegate function)
    {
        if (function.returnType().equals(void.class) == false)
        {
            throw new MalformedTestException("The return-type of a test function must be void.");
        }

        if (function.parameterTypes().size() != 1)
        {
            throw new MalformedTestException("A test function must take exactly one parameter.");
        }

        if (Reflect.isSubtypeOf(function.parameterTypes().get(0), TestCase.class) == false)
        {
            throw new MalformedTestException("The type of a test function's only parameter must be TestCase.");
        }
    }
}
