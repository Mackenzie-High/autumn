package autumnspecification;

import autumn.lang.Module;
import autumn.lang.Record;
import autumn.lang.TypedFunctor;
import autumn.lang.exceptions.CheckedException;
import autumn.util.functors.Action;
import autumn.util.functors.Function1;
import autumn.util.functors.Predicate;
import autumn.util.functors.ProxyHandler;
import autumnspecification.Function.Group;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import high.mackenzie.autumn.util.json.JsonEncoder;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Collections;
import java.util.IllegalFormatException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * This class is used to generate page: http://www.mackenziehigh.me/autumn/FunctionIndexPage.html
 *
 * @author Mackenzie High
 */
public final class FunctionIndex
{
    private static final int EXAMPLE_1 = 1;

    private static final int EXAMPLE_2 = 2;

    private static final int EXAMPLE_3 = 3;

    private static final int EXAMPLE_4 = 4;

    private static final int EXAMPLE_5 = 5;

    public static List<Function> functions = Lists.newLinkedList();

    public static Function add()
    {
        final Function function = new Function();

        functions.add(function);

        return function;
    }

    public static List<Function> functions()
    {

        Function f;

        // Finished!
        f = add();
        f.summary = "This function determines whether a predicate is true for all the elements in an iterable.";
        f.name = "all";
        f.formal("condition", Predicate.class, "is the predicate itself.");
        f.formal("elements", Iterable.class, "is the iterable itself.");
        f.returns(boolean.class, "Return true, iff the predicate returns true for all the elements.");
        f.raise(NullPointerException.class, "if <i>condition</i> is null.", "condition == null");
        f.raise(NullPointerException.class, "if <i>elements</i> is null.", "elements == null");
        f.raise(Throwable.class, "in order to propagate exceptions thrown from within the <i>predicate</i>.");
        f.body = "return T.all(elements, condition);";
        f.example(EXAMPLE_1, 146);

        // Finished!
        f = add();
        f.summary = "This function determines whether a predicate is true for any of the elements in an iterable.";
        f.name = "any";
        f.formal("condition", Predicate.class, "is the predicate itself.");
        f.formal("elements", Iterable.class, "is the iterable itself.");
        f.returns(boolean.class, "Return true, iff the predicate is true for any of the elements.");
        f.raise(NullPointerException.class, "if <i>condition</i> is null.", "condition == null");
        f.raise(NullPointerException.class, "if <i>elements</i> is null.", "elements == null");
        f.raise(Throwable.class, "in order to propagate exceptions thrown from within the <i>predicate</i>.");
        f.body = "return T.any(elements, condition);";
        f.example(EXAMPLE_1, 147);

        // Finished!
        f = add();
        f.summary = "This function applies a functor to a list of arguments and then returns the result.";
        f.name = "apply";
        f.formal("functor", TypedFunctor.class, "is the function object itself.");
        f.formal("arguments", Iterable.class, "contains the arguments to pass to the functor.");
        f.returns(Object.class, "Return the value produced by invoking the <i>functor</i>.");
        f.raise(NullPointerException.class, "if <i>functor</i> is null.", "functor == null");
        f.raise(NullPointerException.class, "if <i>arguments</i> is null.", "arguments == null");
        f.raise(IllegalArgumentException.class, "if the number of arguments differs from the number of parameters.");
        f.raise(Throwable.class, "in order to propagate exceptions thrown from within the <i>predicate</i>.");
        f.body = "return T.apply(functor, arguments);";
        f.example(EXAMPLE_1, 151);

        // TODO: Future Release?
//        f = add();
//        f.summary = "(Under Development) - This function creates, but does not run, a new asynchronous task.";
//        f.name = "async";
//        f.formal("action", Action.class, "is the task to perform asynchronously.");
//        f.returns(AsyncTask.class, "Return an object that can perform the action asynchronously.");
//        f.raise(NullPointerException.class, "if <i>action</i> is null.", "action == null");
//        f.body = "return T.async(action);";

        // Finished!
        f = add();
        f.summary = "This function computes the average of a series of numbers.";
        f.name = "average";
        f.formal("values", Iterable.class, "contains the numbers themselves.");
        f.returns(BigDecimal.class, "Return the average of the numbers.");
        f.raise(NullPointerException.class, "if <i>values</i> is null.", "values == null");
        f.raise(NullPointerException.class, "if any of the <i>values</i> are null.");
        f.raise(IllegalArgumentException.class, "if any of the <i>values</i> cannot be widened.");
        f.detail(0, "Each value will be converted to a $JavaMathBigDecimal$ using the $big$ function.");
        f.detail(0, "If <i>values</i> is empty, then zero is returned.");
        f.body = "return T.average(values);";
        f.example(EXAMPLE_1, 152);

        // Finished!
        f = add();
        f.summary = "This function converts a number to a $JavaMathBigDecimal$.";
        f.name = "big";
        f.formal("value", Object.class, "is the number to convert.");
        f.returns(BigDecimal.class, "Return the <i>value</i> as a $JavaMathBigDecimal$.");
        f.raise(NullPointerException.class, "if <i>value</i> is null.", "value == null");
        f.raise(IllegalArgumentException.class, "if the conversion is not possible.");
        f.detail(0, "A value may be of any of the following types:");
        f.detail(1, "$JavaLangCharacter$");
        f.detail(1, "$JavaLangByte$");
        f.detail(1, "$JavaLangShort$");
        f.detail(1, "$JavaLangInteger$");
        f.detail(1, "$JavaLangLong$");
        f.detail(1, "$JavaLangFloat$");
        f.detail(1, "$JavaLangDouble$");
        f.detail(1, "$JavaMathBigInteger$");
        f.detail(1, "$JavaMathBigDecimal$");
        f.detail(0, "The result will have the same scale as literals created using Autumn.");
        f.body = "return T.big(value);";
        f.example(EXAMPLE_1, 153);

        // Finished!
        f = add();
        f.summary = "This function compares two values and then returns the result.";
        f.name = "compare";
        f.formal("left", Comparable.class, "is the left operand.");
        f.formal("right", Comparable.class, "is the right operand.");
        f.detail(0, "A null value is considered to be less-than a non-null value.");
        f.returns(int.class, "The result is negative when (<i>left</i> < <i>right</i>), zero when (<i>left</i> == <i>right</i>), and positive when (<i>left</i> > <i>right</i>).");
        f.body = "return T.compare(left, right);";
        f.example(EXAMPLE_1, 150);

        // Finished!
        f = add();
        f.summary = "This function counts the number of elements of an iterable that obey a given predicate.";
        f.name = "count";
        f.formal("condition", Predicate.class, "is the predicate itself.");
        f.formal("elements", Iterable.class, "contains the elements.");
        f.returns(int.class, "Return the aforedescribed count.");
        f.raise(NullPointerException.class, "if <i>condition</i> is null.", "condition == null");
        f.raise(NullPointerException.class, "if <i>elements</i> is null.", "elements == null");
        f.raise(Throwable.class, "in order to propagate exceptions thrown from within the <i>predicate</i>.");
        f.body = "return T.count(elements, condition);";
        f.example(EXAMPLE_1, 148);

        f = add();
        f.summary = "(Under Development) - This function converts a string to an object.";
        f.name = "decodeJson";
        f.formal("module", Module.class, "is the module that defines the tuples and structs that will be used.");
        f.formal("string", String.class, "is the string to convert to an object.");
        f.returns(Object.class, "Return the <i>string</i> as an object.");
        f.raise(NullPointerException.class, "if <i>module</i> is null.", "module == null");
        f.raise(NullPointerException.class, "if <i>string</i> is null.", "string == null");
        f.body = "return T.json(string, module);";

        // Finished!
        f = add();
        f.summary = "This function retrieves the default value of a given type.";
        f.name = "defaultValueOf";
        f.formal("type", Class.class, "is the type whose default value will be returned.");
        f.returns(Object.class, "Return the default value of the <i>type</i>.");
        f.raise(NullPointerException.class, "if the <i>type</i> is null.", "type == null");
        f.raise(IllegalArgumentException.class, "if the <i>type</i> is the void-type.", "type == void.class");
        f.detail(0, "Default Values:");
        f.detail(1, "boolean-type => false");
        f.detail(1, "char-type => null character");
        f.detail(1, "byte-type => zero");
        f.detail(1, "short-type => zero");
        f.detail(1, "int-type => zero");
        f.detail(1, "long-type => zero");
        f.detail(1, "float-type => zero");
        f.detail(1, "double-type => zero");
        f.detail(1, "reference-type => null");
        f.body = "return T.defaultValue(type);";
        f.example(EXAMPLE_1, 157);

        f = add();
        f.summary = "(Under Development) - This function converts an object to a JSON string.";
        f.name = "encodeJson";
        f.formal("object", Object.class, "is the object to convert to a JSON string.");
        f.returns(String.class, "Return the <i>object</i> as a JSON string.");
        f.body = "return T.json(object);";

        // Finished!
        f = add();
        f.summary = "Given a sequence of elements, this function creates a list of [<i>index</i>, <i>element</i>] pairs.";
        f.name = "enumerate";
        f.formal("iterable", Iterable.class, "is the sequence of elements.");
        f.returns(List.class, "Return an immutable list of lists such that each inner list is a [<i>index</i>, <i>element</i>] pair.");
        f.raise(NullPointerException.class, "if <i>iterable</i> is null.", "iterable == null");
        f.detail(0, "The inner lists of pairs are immutable.");
        f.detail(0, "The outer list is immutable.");
        f.body = "return T.enumerate(iterable);";
        f.example(EXAMPLE_1, 158);

        f = add();
        f.summary = "(Under Development) - This function replaces escape sequences with their expansions.";
        f.name = "escape";
        f.formal("string", String.class, "is the string that contains the escape sequences.");
        f.returns(String.class, "Return the <i>string</i> with the escape sequences therein replaced with their expansions.");
        f.raise(NullPointerException.class, "if <i>string</i> is null.", "string == null");
        f.body = "return T.escape(string);";

        // Finished!
        f = add();
        f.summary = "This function creates a list containing the elements from an iterable which match a predicate.";
        f.name = "filter";
        f.formal("condition", Predicate.class, "is used to determine whether to keep each element.");
        f.formal("elements", Iterable.class, "contains the elements to filter.");
        f.returns(List.class, "Return an immutable list containing the elements that were kept.");
        f.raise(NullPointerException.class, "if <i>condition</i> is null.", "condition == null");
        f.raise(NullPointerException.class, "if <i>elements</i> is null.", "elements == null");
        f.raise(Throwable.class, "in order to propagate exceptions thrown from within the <i>predicate</i>.");
        f.body = "return T.filter(elements, condition);";
        f.example(EXAMPLE_1, 156);

        // Finished!
        f = add();
        f.summary = "This function searches through an iterable for a value.";
        f.name = "find";
        f.formal("condition", Predicate.class, "is used to identify the sought after value.");
        f.formal("skip", int.class, "is the number of matching elements to skip.");
        f.formal("elements", Iterable.class, "contains the elements to search through.");
        f.returns(Object.class, "Return the sought after value, if it is found; otherwise, return null.");
        f.raise(NullPointerException.class, "if <i>condition</i> is null.", "condition == null");
        f.raise(NullPointerException.class, "if <i>elements</i> is null.", "elements == null");
        f.raise(IllegalArgumentException.class, "if <i>skip</i> is less-than zero", "skip < 0");
        f.raise(Throwable.class, "in order to propagate exceptions thrown from within the <i>predicate</i>.");
        f.body = "return T.find(elements, condition, skip);";
        f.example(EXAMPLE_1, 173);

        // Finished!
        f = add();
        f.group = Group.Reflection;
        f.summary = "This function searches for an annotation.";
        f.name = "findAnnotation";
        f.formal("owner", AnnotatedElement.class, "will be searched.");
        f.formal("type", Class.class, "is the type of the annotation.");
        f.returns(Annotation.class, "Return the sought after annotation, if it exists; otherwise, return null.");
        f.raise(NullPointerException.class, "if <i>owner</i> is null.", "owner == null");
        f.raise(NullPointerException.class, "if <i>type</i> is null.", "type == null");
        f.body = "return owner.getAnnotation(type);";
        f.example(EXAMPLE_1, 159);

        // Finished!
        f = add();
        f.summary = "This function searches for a constructor.";
        f.name = "findConstructor";
        f.formal("owner", Class.class, "will be searched.");
        f.formal("formals", Iterable.class, "are the types of the constructor's formal parameters.");
        f.returns(Constructor.class, "Return an object that represents the constructor, if the constructor exists; otherwise, return null.");
        f.raise(NullPointerException.class, "if <i>owner</i> is null.", "owner == null");
        f.raise(NullPointerException.class, "if <i>formals</i> is null.", "formals == null");
        f.body = "return T.findConstructor(owner, formals);";
        f.example(EXAMPLE_1, 160);

        // Finished!
        f = add();
        f.summary = "This function searches for a field.";
        f.name = "findField";
        f.formal("owner", Class.class, "will be searched.");
        f.formal("name", String.class, "is the name of the field.");
        f.returns(Field.class, "Return an object that represents the field, if the field exists; otherwise, return null.");
        f.raise(NullPointerException.class, "if <i>owner</i> is null.", "owner == null");
        f.raise(NullPointerException.class, "if <i>name</i> is null.", "name == null");
        f.body = "return T.findField(owner, name);";
        f.example(EXAMPLE_1, 161);

        // Finished!
        f = add();
        f.summary = "This function searches for a method.";
        f.name = "findMethod";
        f.formal("owner", Class.class, "will be searched.");
        f.formal("name", String.class, "is the name of the method.");
        f.formal("formals", Iterable.class, "are the types of the method's formal parameters.");
        f.returns(Method.class, "Return an object the represents the method, if the method exists; otherwise, return null.");
        f.raise(NullPointerException.class, "if <i>owner</i> is null.", "owner == null");
        f.raise(NullPointerException.class, "if <i>name</i> is null.", "name == null");
        f.raise(NullPointerException.class, "if <i>formals</i> is null.", "formals == null");
        f.body = "return T.findMethod(owner, name, formals);";
        f.example(EXAMPLE_1, 162);

        // Finished!
        f = add();
        f.summary = "This function retrieves the first element from a list.";
        f.name = "first";
        f.formal("list", List.class, "contains the element.");
        f.returns(Object.class, "Return the first element of the <i>list</i>.");
        f.raise(NullPointerException.class, "if <i>list</i> is null.", "list == null");
        f.body = "return list.get(0);";
        f.example(EXAMPLE_1, 163);

        // Finished!
        f = add();
        f.summary = "This function substitutes formatted arguments into a given string.";
        f.name = "format";
        f.formal("format", String.class, "is the string to substitute the <i>arguments</i> into.");
        f.formal("args", Iterable.class, "are arguments to format and then substitute into the <i>string</i>.");
        f.returns(String.class, "Return the formatted string.");
        f.raise(NullPointerException.class, "if <i>format</i> is null.", "format == null");
        f.raise(NullPointerException.class, "if <i>args</i> is null.", "args == null");
        f.raise(IllegalFormatException.class, "if the <i>string</i> is illegally formatted.");
        f.detail(0, "Really, this function is just a thin wrapper around the $format$ function in $JavaLangString$.");
        f.detail(1, "This function is provided, because the $format$ function takes an array rather than a list.");
        f.body = "return String.format(format, immutable(args).toArray());";
        f.example(EXAMPLE_1, 164);

        // Finished!
        f = add();
        f.summary = "This function retrieves the value of an array element.";
        f.name = "get";
        f.formal("array", boolean[].class, "is the array that contains the element.");
        f.formal("index", int.class, "is the location of the element in the array.");
        f.returns(boolean.class, "Return the value stored in the <i>array</i> element that is identified by the given <i>index</i>.");
        f.raise(NullPointerException.class, "if <i>array</i> is null.", "array == null");
        f.raise(IndexOutOfBoundsException.class, "if <i>index</i> is out of bounds.");
        f.body = "return array[index];";
        f.example(EXAMPLE_1, 125);

        // Finished!
        f = add();
        f.summary = "This function retrieves the value of an array element.";
        f.name = "get";
        f.formal("array", char[].class, "is the array that contains the element.");
        f.formal("index", int.class, "is the location of the element in the array.");
        f.returns(char.class, "Return the value stored in the <i>array</i> element that is identified by the given <i>index</i>.");
        f.raise(NullPointerException.class, "if <i>array</i> is null.", "array == null");
        f.raise(IndexOutOfBoundsException.class, "if <i>index</i> is out of bounds.");
        f.body = "return array[index];";
        f.example(EXAMPLE_1, 126);

        // Finished!
        f = add();
        f.summary = "This function retrieves the value of an array element.";
        f.name = "get";
        f.formal("array", byte[].class, "is the array that contains the element.");
        f.formal("index", int.class, "is the location of the element in the array.");
        f.returns(byte.class, "Return the value stored in the <i>array</i> element that is identified by the given <i>index</i>.");
        f.raise(NullPointerException.class, "if <i>array</i> is null.", "array == null");
        f.raise(IndexOutOfBoundsException.class, "if <i>index</i> is out of bounds.");
        f.body = "return array[index];";
        f.example(EXAMPLE_1, 127);

        // Finished!
        f = add();
        f.summary = "This function retrieves the value of an array element.";
        f.name = "get";
        f.formal("array", short[].class, "is the array that contains the element.");
        f.formal("index", int.class, "is the location of the element in the array.");
        f.returns(short.class, "Return the value stored in the <i>array</i> element that is identified by the given <i>index</i>.");
        f.raise(NullPointerException.class, "if <i>array</i> is null.", "array == null");
        f.raise(IndexOutOfBoundsException.class, "if <i>index</i> is out of bounds.");
        f.body = "return array[index];";
        f.example(EXAMPLE_1, 128);

        // Finished!
        f = add();
        f.summary = "This function retrieves the value of an array element.";
        f.name = "get";
        f.formal("array", int[].class, "is the array that contains the element.");
        f.formal("index", int.class, "is the location of the element in the array.");
        f.returns(int.class, "Return the value stored in the <i>array</i> element that is identified by the given <i>index</i>.");
        f.raise(NullPointerException.class, "if <i>array</i> is null.", "array == null");
        f.raise(IndexOutOfBoundsException.class, "if <i>index</i> is out of bounds.");
        f.body = "return array[index];";
        f.example(EXAMPLE_1, 129);

        // Finished!
        f = add();
        f.summary = "This function retrieves the value of an array element.";
        f.name = "get";
        f.formal("array", long[].class, "is the array that contains the element.");
        f.formal("index", int.class, "is the location of the element in the array.");
        f.returns(long.class, "Return the value stored in the <i>array</i> element that is identified by the given <i>index</i>.");
        f.raise(NullPointerException.class, "if <i>array</i> is null.", "array == null");
        f.raise(IndexOutOfBoundsException.class, "if <i>index</i> is out of bounds.");
        f.body = "return array[index];";
        f.example(EXAMPLE_1, 130);

        // Finished!
        f = add();
        f.summary = "This function retrieves the value of an array element.";
        f.name = "get";
        f.formal("array", float[].class, "is the array that contains the element.");
        f.formal("index", int.class, "is the location of the element in the array.");
        f.returns(float.class, "Return the value stored in the <i>array</i> element that is identified by the given <i>index</i>.");
        f.raise(NullPointerException.class, "if <i>array</i> is null.", "array == null");
        f.raise(IndexOutOfBoundsException.class, "if <i>index</i> is out of bounds.");
        f.body = "return array[index];";
        f.example(EXAMPLE_1, 131);

        // Finished!
        f = add();
        f.summary = "This function retrieves the value of an array element.";
        f.name = "get";
        f.formal("array", double[].class, "is the array that contains the element.");
        f.formal("index", int.class, "is the location of the element in the array.");
        f.returns(double.class, "Return the value stored in the <i>array</i> element that is identified by the given <i>index</i>.");
        f.raise(NullPointerException.class, "if <i>array</i> is null.", "array == null");
        f.raise(IndexOutOfBoundsException.class, "if <i>index</i> is out of bounds.");
        f.body = "return array[index];";
        f.example(EXAMPLE_1, 132);

        // Finished!
        f = add();
        f.summary = "This function retrieves the value of an array element.";
        f.name = "get";
        f.formal("array", BigInteger[].class, "is the array that contains the element.");
        f.formal("index", int.class, "is the location of the element in the array.");
        f.returns(BigInteger.class, "Return the value stored in the <i>array</i> element that is identified by the given <i>index</i>.");
        f.raise(NullPointerException.class, "if <i>array</i> is null.", "array == null");
        f.raise(IndexOutOfBoundsException.class, "if <i>index</i> is out of bounds.");
        f.body = "return array[index];";
        f.example(EXAMPLE_1, 133);

        // Finished!
        f = add();
        f.summary = "This function retrieves the value of an array element.";
        f.name = "get";
        f.formal("array", BigDecimal[].class, "is the array that contains the element.");
        f.formal("index", int.class, "is the location of the element in the array.");
        f.returns(BigDecimal.class, "Return the value stored in the <i>array</i> element that is identified by the given <i>index</i>.");
        f.raise(NullPointerException.class, "if <i>array</i> is null.", "array == null");
        f.raise(IndexOutOfBoundsException.class, "if <i>index</i> is out of bounds.");
        f.body = "return array[index];";
        f.example(EXAMPLE_1, 134);

        // Finished!
        f = add();
        f.summary = "This function retrieves the value of an array element.";
        f.name = "get";
        f.formal("array", String[].class, "is the array that contains the element.");
        f.formal("index", int.class, "is the location of the element in the array.");
        f.returns(String.class, "Return the value stored in the <i>array</i> element that is identified by the given <i>index</i>.");
        f.raise(NullPointerException.class, "if <i>array</i> is null.", "array == null");
        f.raise(IndexOutOfBoundsException.class, "if <i>index</i> is out of bounds.");
        f.body = "return array[index];";
        f.example(EXAMPLE_1, 135);

        // Finished!
        f = add();
        f.summary = "This function retrieves the value of an array element.";
        f.name = "get";
        f.formal("array", Object[].class, "is the array that contains the element.");
        f.formal("index", int.class, "is the location of the element in the array.");
        f.returns(Object.class, "Return the value stored in the <i>array</i> element that is identified by the given <i>index</i>.");
        f.raise(NullPointerException.class, "if <i>array</i> is null.", "array == null");
        f.raise(IndexOutOfBoundsException.class, "if <i>index</i> is out of bounds.");
        f.body = "return array[index];";
        f.example(EXAMPLE_1, 136);

        // Finished!
        f = add();
        f.summary = "This function retrieves the value of a list element.";
        f.name = "get";
        f.formal("list", List.class, "is the array that contains the element.");
        f.formal("index", int.class, "is the location of the element in the list.");
        f.returns(Object.class, "Return the value stored in the <i>list</i> element that is identified by the given <i>index</i>.");
        f.raise(NullPointerException.class, "if <i>list</i> is null.", "list == null");
        f.raise(IndexOutOfBoundsException.class, "if <i>index</i> is out of bounds.");
        f.body = "return list.get(index);";

        // Finished!
        f = add();
        f.summary = "This function retrieves the <i>value</i> part of an entry in a map.";
        f.name = "get";
        f.formal("map", Map.class, "contains the entry.");
        f.formal("key", Object.class, "is the key that identifies the entry.");
        f.returns(Object.class, "Return the value stored in the <i>map</i> entry that is identified by the given <i>key</i>.");
        f.raise(NullPointerException.class, "if <i>map</i> is null.", "map == null");
        f.body = "return map.get(key);";
        f.example(EXAMPLE_1, 175);

        // Finished!
        f = add();
        f.summary = "This function retrieves the <i>value</i> part of an entry in a record.";
        f.name = "get";
        f.formal("record", Record.class, "contains the entry.");
        f.formal("index", int.class, "is the index of the entry.");
        f.returns(Object.class, "Return the value stored in the <i>record</i> entry that is identified by the given <i>index</i>.");
        f.raise(NullPointerException.class, "if <i>record</i> is null.", "record == null");
        f.raise(IndexOutOfBoundsException.class, "if <i>index</i> is out of bounds.");
        f.body = "return record.get(index);";
        f.example(EXAMPLE_1, 176);

        // Finished!
        f = add();
        f.summary = "This function retrieves the <i>value</i> part of an entry in a record.";
        f.name = "get";
        f.formal("record", Record.class, "contains the entry.");
        f.formal("key", String.class, "is the name that identifies the entry.");
        f.returns(Object.class, "Return the value stored in the <i>record</i> entry that is identified by the given <i>key</i>.");
        f.raise(NullPointerException.class, "if <i>record</i> is null.", "record == null");
        f.raise(NoSuchElementException.class, "if <i>record</i> does not contain an element with the given <i>name</i>.");
        f.body = "return get(record, record.keys().indexOf(key));";
        f.example(EXAMPLE_1, 177);

        // Finished!
        f = add();
        f.summary = "This function retrieves a value that is stored an Autumn-style annotation.";
        f.name = "get";
        f.formal("anno", Annotation.class, "contains the value.");
        f.formal("index", int.class, "is the index of the value within the annotation.");
        f.returns(String.class, "Return the value stored in <i>anno</i> at the given <i>index</i>.");
        f.raise(NullPointerException.class, "if <i>anno</i> is null.", "anno == null");
        f.raise(IndexOutOfBoundsException.class, "if no value in the <i>anno</i> is identifed by the <i>index</i>.");
        f.body = "return (String) iter(anno).get(index);";
        f.example(EXAMPLE_1, 174);

        // Finished!
        f = add();
        f.summary = "This function computes the hash-code of a value.";
        f.name = "hash";
        f.formal("value", Object.class, "is the value whose hash-code will be computed. (may be null)");
        f.returns(int.class, "Return the hash-code of the value.");
        f.body = "return value == null ? 0 : value.hashCode();";
        f.detail(0, "The hash-code of a null is always zero.");
        f.example(EXAMPLE_1, 165);

        // Finished!
        f = add();
        f.summary = "This function creates an immutable collection from another collection.";
        f.name = "immutable";
        f.formal("original", Iterable.class, "is the data-structure to copy.");
        f.returns(List.class, "Return an immutable copy of the <i>original</i>.");
        f.raise(NullPointerException.class, "if <i>original</i> is null.", "original == null");
        f.body = "return immutable(iter(original));";
        f.example(EXAMPLE_1, 178);

        // Finished!
        f = add();
        f.summary = "This function creates an immutable list from another list.";
        f.name = "immutable";
        f.formal("original", List.class, "is the data-structure to copy.");
        f.returns(List.class, "Return an immutable copy of the <i>original</i>.");
        f.raise(NullPointerException.class, "if <i>original</i> is null.", "original == null");
        f.body = "return Collections.unmodifiableList(new ArrayList(original));";
        f.example(EXAMPLE_1, 179);

        // Finished!
        f = add();
        f.summary = "This function creates an immutable set from another set.";
        f.name = "immutable";
        f.formal("original", Set.class, "is the data-structure to copy.");
        f.returns(Set.class, "Return an immutable copy of the <i>original</i>.");
        f.raise(NullPointerException.class, "if <i>original</i> is null.", "original == null");
        f.body = "return Collections.unmodifiableSet(new HashSet(original));";
        f.example(EXAMPLE_1, 180);

        // Finished!
        f = add();
        f.summary = "This function creates an immutable map from another map.";
        f.name = "immutable";
        f.formal("original", Map.class, "is the data-structure to copy.");
        f.returns(Map.class, "Return an immutable copy of the <i>original</i>.");
        f.raise(NullPointerException.class, "if <i>original</i> is null.", "original == null");
        f.body = "return Collections.unmodifiableMap(new HashMap(original));";
        f.example(EXAMPLE_1, 181);

        f = add();
        f.summary = "(Under Development) - This method determines whether a $JavaLangClass$ represents the type of an annotation.";
        f.name = "isAnnotationType";
        f.formal("type", Class.class, "may represent the type of an annotation.");
        f.returns(boolean.class, "Return true, iff <i>type</i> represents the type of an annotation.");
        f.raise(NullPointerException.class, "if <i>type</i> is null.", "type == null");
        f.body = "return type.isAnnotation();";
        f.example(EXAMPLE_1, 166);

        f = add();
        f.summary = "(Under Development) - This function determines whether one type is assignable to annother type in Autumn.";
        f.name = "isAssignableTo";
        f.formal("assignee", Class.class, "is the the type of the entity being assigned to.");
        f.formal("value", Class.class, "is the type of the value that is being assigned to the assignee.");
        f.returns(boolean.class, "Return true, iff Autumn allows the <i>value</i> type to be assigned to the <i>assignee</i> type.");
        f.body = "return T.isAssignableTo(assignee, value);";

        f = add();
        f.summary = "(Under Development) - This function determines whether a $JavaLangClass$ represents the type of a design.";
        f.name = "isDesignType";
        f.formal("type", Class.class, "may be a design-type.");
        f.returns(boolean.class, "Return true, iff <i>type</i> is a design-type.");
        f.raise(NullPointerException.class, "if <i>type</i> is null.", "type == null");
        f.body = "return type.isInterface() && Record.class.isAssignableFrom(type);";
        f.example(EXAMPLE_1, 166);

        f = add();
        f.summary = "(Under Development) - This function determines whether a $JavaLangClass$ represents the type of an enum.";
        f.name = "isEnumType";
        f.formal("type", Class.class, "may be an enum-type.");
        f.returns(boolean.class, "Return true, iff <i>type</i> is an enum-type.");
        f.raise(NullPointerException.class, "if <i>type</i> is null.", "type == null");
        f.body = "return type.isEnum();";
        f.example(EXAMPLE_1, 166);

        f = add();
        f.summary = "(Under Development) - This function determines whether a $JavaLangClass$ represents the type of an exception.";
        f.name = "isExceptionType";
        f.formal("type", Class.class, "may be an exception-type.");
        f.returns(boolean.class, "Return true, iff <i>type</i> is an exception-type.");
        f.raise(NullPointerException.class, "if <i>type</i> is null.", "type == null");
        f.body = "return Throwable.class.isAssignableFrom(type);";
        f.example(EXAMPLE_1, 166);

        f = add();
        f.summary = "(Under Development) - This function determines whether a $JavaLangClass$ represents the type of a functor.";
        f.name = "isFunctorType";
        f.formal("type", Class.class, "may be a functor-type.");
        f.returns(boolean.class, "Return true, iff <i>type</i> is a functor-type.");
        f.raise(NullPointerException.class, "if <i>type</i> is null.", "type == null");
        f.detail(0, "Only concrete funtor-type are recognized.");
        f.body = "return Functor.class.isAssignableFrom(type);";
        f.example(EXAMPLE_1, 166);

        f = add();
        f.summary = "(Under Development) - This function determines whether a $JavaLangClass$ represents the type of a module.";
        f.name = "isModuleType";
        f.formal("type", Class.class, "may be a module-type.");
        f.returns(boolean.class, "Return true, iff <i>type</i> is a module-type.");
        f.raise(NullPointerException.class, "if <i>type</i> is null.", "type == null");
        f.body = "return Module.class.isAssignableFrom(type);";
        f.example(EXAMPLE_1, 166);

        f = add();
        f.summary = "(Under Development) - This function determines whether a $JavaLangClass$ represents the type of a record.";
        f.name = "isRecordType";
        f.formal("type", Class.class, "may be a record-type.");
        f.returns(boolean.class, "Return true, iff <i>type</i> is a record-type.");
        f.raise(NullPointerException.class, "if <i>type</i> is null.", "type == null");
        f.body = "return isDesignType(type) || isStructType(type) || isTupleType(type);";
        f.example(EXAMPLE_1, 166);

        f = add();
        f.summary = "(Under Development) - This function determines whether a $JavaLangClass$ represents the type of a struct.";
        f.name = "isStructType";
        f.formal("type", Class.class, "may be a struct-type.");
        f.returns(boolean.class, "Return true, iff <i>type</i> is a struct-type.");
        f.raise(NullPointerException.class, "if <i>type</i> is null.", "type == null");
        f.body = "return type.isAnnotationPresent(StructDefinition.class);";
        f.example(EXAMPLE_1, 166);

        f = add();
        f.summary = "(Under Development) - This function determines whether one type is a subtype of another.";
        f.name = "isSubtypeOf";
        f.formal("subtype", Class.class, "may be a subtype of the <i>supertype</i>.");
        f.formal("supertype", Class.class, "may be a supertype of the <i>subtype</i>.");
        f.returns(boolean.class, "Return true, iff <i>subtype</i> is in fact a subtype of <i>supertype</i>.");
        f.body = "return T.isSubtypeOf(subtype, supertype);";

        f = add();
        f.summary = "(Under Development) - This function determines whether a $JavaLangClass$ represents the type of a tuple.";
        f.name = "isTupleType";
        f.formal("type", Class.class, "may be a tuple-type.");
        f.returns(boolean.class, "Return true, iff <i>type</i> is a tuple-type.");
        f.raise(NullPointerException.class, "if <i>type</i> is null.", "type == null");
        f.body = "return type.isAnnotationPresent(TupleDefinition.class);";
        f.example(EXAMPLE_1, 166);

        // Finished!
        f = add();
        f.summary = "This function creates an iterable whose iterator can iterate over the given data-structure.";
        f.name = "iter";
        f.formal("input", boolean[].class, "is the given data-structure itself.");
        f.returns(List.class, "Return an unmodifiable view of the <i>input</i>.");
        f.raise(NullPointerException.class, "if <i>input</i> is null.", "input == null");
        f.body = "return T.iter(input);";
        f.example(EXAMPLE_1, 187);

        // Finished!
        f = add();
        f.summary = "This function creates an iterable whose iterator can iterate over the given data-structure.";
        f.name = "iter";
        f.formal("input", char[].class, "is the given data-structure itself.");
        f.returns(List.class, "Return an unmodifiable view of the <i>input</i>.");
        f.raise(NullPointerException.class, "if <i>input</i> is null.", "input == null");
        f.body = "return T.iter(input);";
        f.example(EXAMPLE_1, 188);

        // Finished!
        f = add();
        f.summary = "This function creates an iterable whose iterator can iterate over the given data-structure.";
        f.name = "iter";
        f.formal("input", byte[].class, "is the given data-structure itself.");
        f.returns(List.class, "Return an unmodifiable view of the <i>input</i>.");
        f.raise(NullPointerException.class, "if <i>input</i> is null.", "input == null");
        f.body = "return T.iter(input);";
        f.example(EXAMPLE_1, 189);

        // Finished!
        f = add();
        f.summary = "This function creates an iterable whose iterator can iterate over the given data-structure.";
        f.name = "iter";
        f.formal("input", short[].class, "is the given data-structure itself.");
        f.returns(List.class, "Return an unmodifiable view of the <i>input</i>.");
        f.raise(NullPointerException.class, "if <i>input</i> is null.", "input == null");
        f.body = "return T.iter(input);";
        f.example(EXAMPLE_1, 190);

        // Finished!
        f = add();
        f.summary = "This function creates an iterable whose iterator can iterate over the given data-structure.";
        f.name = "iter";
        f.formal("input", int[].class, "is the given data-structure itself.");
        f.returns(List.class, "Return an unmodifiable view of the <i>input</i>.");
        f.raise(NullPointerException.class, "if <i>input</i> is null.", "input == null");
        f.body = "return T.iter(input);";
        f.example(EXAMPLE_1, 191);

        // Finished!
        f = add();
        f.summary = "This function creates an iterable whose iterator can iterate over the given data-structure.";
        f.name = "iter";
        f.formal("input", long[].class, "is the given data-structure itself.");
        f.returns(List.class, "Return an unmodifiable view of the <i>input</i>.");
        f.raise(NullPointerException.class, "if <i>input</i> is null.", "input == null");
        f.body = "return T.iter(input);";
        f.example(EXAMPLE_1, 192);

        // Finished!
        f = add();
        f.summary = "This function creates an iterable whose iterator can iterate over the given data-structure.";
        f.name = "iter";
        f.formal("input", float[].class, "is the given data-structure itself.");
        f.returns(List.class, "Return an unmodifiable view of the <i>input</i>.");
        f.raise(NullPointerException.class, "if <i>input</i> is null.", "input == null");
        f.body = "return T.iter(input);";
        f.example(EXAMPLE_1, 193);

        // Finished!
        f = add();
        f.summary = "This function creates an iterable whose iterator can iterate over the given data-structure.";
        f.name = "iter";
        f.formal("input", double[].class, "is the given data-structure itself.");
        f.returns(List.class, "Return an unmodifiable view of the <i>input</i>.");
        f.raise(NullPointerException.class, "if <i>input</i> is null.", "input == null");
        f.body = "return T.iter(input);";
        f.example(EXAMPLE_1, 194);

        // Finished!
        f = add();
        f.summary = "This function creates an iterable whose iterator can iterate over the given data-structure.";
        f.name = "iter";
        f.formal("input", Object[].class, "is the given data-structure itself.");
        f.returns(List.class, "Return an unmodifiable view of the <i>input</i>.");
        f.raise(NullPointerException.class, "if <i>input</i> is null.", "input == null");
        f.body = "return T.iter(input);";
        f.example(EXAMPLE_1, 195);

        // Finished!
        f = add();
        f.summary = "This function creates an iterable whose iterator can iterate over the given data-structure.";
        f.name = "iter";
        f.formal("input", Iterable.class, "is the given data-structure itself.");
        f.returns(List.class, "Return an unmodifiable view of the <i>input</i>.");
        f.raise(NullPointerException.class, "if <i>input</i> is null.", "input == null");
        f.body = "return T.iter(input);";
        f.example(EXAMPLE_1, 183);

        // Finished!
        f = add();
        f.summary = "This function creates an iterable whose iterator is the given iterator.";
        f.name = "iter";
        f.formal("input", Iterator.class, "is the given iterator itself.");
        f.returns(Iterable.class, "Return an unmodifiable view of the <i>input</i>.");
        f.raise(NullPointerException.class, "if <i>input</i> is null.", "input == null");
        f.body = "return T.iter(input);";
        f.example(EXAMPLE_1, 185);

        // Finished!
        f = add();
        f.summary = "This function creates an iterable whose iterator can iterate over the keys in a map.";
        f.name = "iter";
        f.formal("input", Map.class, "is the map itself.");
        f.returns(Set.class, "Return an unmodifiable view of the <i>input</i>.");
        f.raise(NullPointerException.class, "if <i>input</i> is null.", "input == null");
        f.body = "return input.keySet();";
        f.example(EXAMPLE_1, 186);

        // Finished!
        f = add();
        f.summary = "This function creates an iterable whose iterator can iterate over the names of the entries in a record.";
        f.name = "iter";
        f.formal("input", Record.class, "is the record itself.");
        f.returns(List.class, "Return an unmodifiable list containing the names of the entries in the <i>input</i>.");
        f.raise(NullPointerException.class, "if <i>input</i> is null.", "input == null");
        f.body = "return input.keys();";
        f.example(EXAMPLE_1, 184);

        // Finished!
        f = add();
        f.summary = "This function retrieves the value(s) stored in an Autumn-style annotation.";
        f.name = "iter";
        f.formal("anno", Annotation.class, "contains the value(s), if any.");
        f.returns(List.class, "Return an unmodifiable list containing the values stored in <i>anno</i>.");
        f.raise(NullPointerException.class, "if <i>anno</i> is null.", "anno == null");
        f.body = "return T.iter(anno);";
        f.example(EXAMPLE_1, 182);

        // Finished!
        f = add();
        f.summary = "This function retrieves the last element of a list.";
        f.name = "last";
        f.formal("list", List.class, "contains the element.");
        f.returns(Object.class, "Return the last element of the <i>list</i>.");
        f.raise(NullPointerException.class, "if <i>list</i> is null.", "list == null");
        f.body = "return list.get(list.size() - 1);";
        f.example(EXAMPLE_1, 163);

        // Finished!
        f = add();
        f.summary = "This function retrieves the length of an array.";
        f.name = "len";
        f.formal("array", boolean[].class, "is the array itself.");
        f.returns(int.class, "Return the length of the <i>array</i>.");
        f.raise(NullPointerException.class, "if <i>array</i> is null.", "array == null");
        f.body = "return array.length;";
        f.example(EXAMPLE_1, 125);

        // Finished!
        f = add();
        f.summary = "This function retrieves the length of an array.";
        f.name = "len";
        f.formal("array", char[].class, "is the array itself.");
        f.returns(int.class, "Return the length of the <i>array</i>.");
        f.raise(NullPointerException.class, "if <i>array</i> is null.", "array == null");
        f.body = "return array.length;";
        f.example(EXAMPLE_1, 126);

        // Finished!
        f = add();
        f.summary = "This function retrieves the length of an array.";
        f.name = "len";
        f.formal("array", byte[].class, "is the array itself.");
        f.returns(int.class, "Return the length of the <i>array</i>.");
        f.raise(NullPointerException.class, "if <i>array</i> is null.", "array == null");
        f.body = "return array.length;";
        f.example(EXAMPLE_1, 127);

        // Finished!
        f = add();
        f.summary = "This function retrieves the length of an array.";
        f.name = "len";
        f.formal("array", short[].class, "is the array itself.");
        f.returns(int.class, "Return the length of the <i>array</i>.");
        f.raise(NullPointerException.class, "if <i>array</i> is null.", "array == null");
        f.body = "return array.length;";
        f.example(EXAMPLE_1, 128);

        // Finished!
        f = add();
        f.summary = "This function retrieves the length of an array.";
        f.name = "len";
        f.formal("array", int[].class, "is the array itself.");
        f.returns(int.class, "Return the length of the <i>array</i>.");
        f.raise(NullPointerException.class, "if <i>array</i> is null.", "array == null");
        f.body = "return array.length;";
        f.example(EXAMPLE_1, 129);

        // Finished!
        f = add();
        f.summary = "This function retrieves the length of an array.";
        f.name = "len";
        f.formal("array", long[].class, "is the array itself.");
        f.returns(int.class, "Return the length of the <i>array</i>.");
        f.raise(NullPointerException.class, "if <i>array</i> is null.", "array == null");
        f.body = "return array.length;";
        f.example(EXAMPLE_1, 130);

        // Finished!
        f = add();
        f.summary = "This function retrieves the length of an array.";
        f.name = "len";
        f.formal("array", float[].class, "is the array itself.");
        f.returns(int.class, "Return the length of the <i>array</i>.");
        f.raise(NullPointerException.class, "if <i>array</i> is null.", "array == null");
        f.body = "return array.length;";
        f.example(EXAMPLE_1, 131);

        // Finished!
        f = add();
        f.summary = "This function retrieves the length of an array.";
        f.name = "len";
        f.formal("array", double[].class, "is the array itself.");
        f.returns(int.class, "Return the length of the <i>array</i>.");
        f.raise(NullPointerException.class, "if <i>array</i> is null.", "array == null");
        f.body = "return array.length;";
        f.example(EXAMPLE_1, 132);

        // Finished!
        f = add();
        f.summary = "This function retrieves the length of an array.";
        f.name = "len";
        f.formal("array", Object[].class, "is the array itself.");
        f.returns(int.class, "Return the length of the <i>array</i>.");
        f.raise(NullPointerException.class, "if <i>array</i> is null.", "array == null");
        f.body = "return array.length;";
        f.example(EXAMPLE_1, 136);
        f.example(EXAMPLE_2, 133);

        // Finished!
        f = add();
        f.summary = "This function retrieves the size of a collection.";
        f.name = "len";
        f.formal("collection", Collection.class, "is the collection itself.");
        f.returns(int.class, "Return the length of the <i>collection</i>.");
        f.raise(NullPointerException.class, "if <i>collection</i> is null.", "collection == null");
        f.body = "return collection.size();";
        f.example(EXAMPLE_1, 196);

        // Finished!
        f = add();
        f.summary = "This function retrieves the size of a map.";
        f.name = "len";
        f.formal("map", Map.class, "is the map itself.");
        f.returns(int.class, "Return the size of the <i>map</i>.");
        f.raise(NullPointerException.class, "if <i>map</i> is null.", "map == null");
        f.body = "return map.size();";
        f.example(EXAMPLE_1, 197);

        // Finished!
        f = add();
        f.summary = "This function retrieves the size of a record.";
        f.name = "len";
        f.formal("record", Record.class, "is the record itself.");
        f.returns(int.class, "Return the size of the <i>record</i>.");
        f.raise(NullPointerException.class, "if <i>record</i> is null.", "record == null");
        f.body = "return record.size();";
        f.example(EXAMPLE_1, 198);

        // Finished!
        f = add();
        f.summary = "This function retrieves the length of a $JavaLangCharSequence$.";
        f.name = "len";
        f.formal("string", CharSequence.class, "is the string of characters.");
        f.returns(int.class, "Return the number of characters in the string.");
        f.raise(NullPointerException.class, "if <i>string</i> is null.", "string == null");
        f.body = "return string.length();";
        f.example(EXAMPLE_1, 199);

        // Finished!
        f = add();
        f.summary = "This function counts the value(s) stored in an Autumn-style annotation.";
        f.name = "len";
        f.formal("anno", Annotation.class, "contains the values to count, if any.");
        f.returns(int.class, "Return the number of values that are stored in <i>anno</i>.");
        f.raise(NullPointerException.class, "if <i>anno</i> is null.", "anno == null");
        f.body = "return iter(anno).size();";
        f.example(EXAMPLE_1, 200);

        // Finished!
        f = add();
        f.summary = "This function applies a transformation to the elements in an iterable.";
        f.name = "map";
        f.formal("functor", Function1.class, "is the transformation itself.");
        f.formal("elements", Iterable.class, "contains the elements to transform.");
        f.returns(List.class, "Return an immutable list containing the results of the transformations.");
        f.raise(NullPointerException.class, "if <i>elements</i> is null.", "elements == null");
        f.raise(NullPointerException.class, "if <i>functor</i> is null.", "functor == null");
        f.raise(Throwable.class, "in order to propagate exceptions thrown from within the <i>functor</i>.");
        f.body = "return T.map(elements, functor);";
        f.example(EXAMPLE_1, 201);

        // Finished!
        f = add();
        f.summary = "This function transverses an iterable in order to find the maximum value therein.";
        f.name = "maximum";
        f.formal("values", Iterable.class, "is the iterable to transverse.");
        f.returns(Object.class, "Return the maximum value found in the iterable.");
        f.raise(NullPointerException.class, "if <i>values</i> is null.", "values == null");
        f.body = "return T.maximum(values);";
        f.example(EXAMPLE_1, 152);
        f.example(EXAMPLE_2, 163);

        // Finished!
        f = add();
        f.summary = "This function transverses an iterable in order to find the minimum value therein.";
        f.name = "minimum";
        f.formal("values", Iterable.class, "is the iterable to transverse.");
        f.returns(Object.class, "Return the minimum value found in the iterable.");
        f.raise(NullPointerException.class, "if <i>values</i> is null.", "values == null");
        f.body = "return T.minimum(values);";
        f.example(EXAMPLE_1, 152);
        f.example(EXAMPLE_2, 163);

        // Finished!
        f = add();
        f.summary = "This function creates an mutable collection from another collection.";
        f.name = "mutable";
        f.formal("original", Iterable.class, "is the data-structure to copy.");
        f.returns(List.class, "Return an iutable copy of the <i>original</i>.");
        f.raise(NullPointerException.class, "if <i>original</i> is null.", "original == null");
        f.body = "return mutable(iter(original));";
        f.example(EXAMPLE_1, 202);

        // Finished!
        f = add();
        f.summary = "This function creates a mutable list from another list.";
        f.name = "mutable";
        f.formal("original", List.class, "is the data-structure to copy.");
        f.returns(List.class, "Return an mutable copy of the <i>original</i>.");
        f.raise(NullPointerException.class, "if <i>original</i> is null.", "original == null");
        f.body = "return new ArrayList(original);";
        f.example(EXAMPLE_1, 203);

        // Finished!
        f = add();
        f.summary = "This function creates a mutable set from another set.";
        f.name = "mutable";
        f.formal("original", Set.class, "is the data-structure to copy.");
        f.returns(Set.class, "Return a mutable copy of the <i>original</i>.");
        f.raise(NullPointerException.class, "if <i>original</i> is null.", "original == null");
        f.body = "return new HashSet(original);";
        f.example(EXAMPLE_1, 204);

        // Finished!
        f = add();
        f.summary = "This function creates a mutable map from another map.";
        f.name = "mutable";
        f.formal("original", Map.class, "is the data-structure to copy.");
        f.returns(Map.class, "Return an mutable copy of the <i>original</i>.");
        f.raise(NullPointerException.class, "if <i>original</i> is null.", "original == null");
        f.body = "return new HashMap(original);";
        f.example(EXAMPLE_1, 205);

        // Finished!
        f = add();
        f.summary = "This function creates an array.";
        f.name = "newArray";
        f.formal("type", Class.class, "is the type of the elements in the new array.");
        f.formal("size", int.class, "is the number of elements in the new array.");
        f.returns(Object.class, "Return the newly created array object.");
        f.raise(NullPointerException.class, "if <i>type</i> is null.", "type == null");
        f.raise(NegativeArraySizeException.class, "if <i>size</i> is less-than zero.", "size < 0");
        f.body = "return java.lang.reflect.Array.newInstance(type, size);";
        f.example(EXAMPLE_1, 136);

        f = add();
        f.summary = "(Under Development) - This function can be used to implement interfaces created using other languages.";
        f.name = "newProxy";
        f.formal("type", Class.class, "is the type of the interface.");
        f.formal("handler", ProxyHandler.class, "is used to implement interface's method.");
        f.returns(Object.class, "Return an instance of the interface.");
        f.raise(NullPointerException.class, "if <i>type</i> is null.", "type == null");
        f.raise(NullPointerException.class, "if <i>handler</i> is null.", "handler == null");
        f.body = "return null;";

        // Finished!
        f = add();
        f.summary = "This function adds padding characters to the rear of a string, as needed.";
        f.name = "padEnd";
        f.formal("string", String.class, "is the string to pad.");
        f.formal("length", int.class, "is the desired length of the string.");
        f.formal("pad", char.class, "is the padding character to use.");
        f.returns(String.class, "Return the result of repeatedly adding the <i>pad</i> character to the <i>string</i> until the <i>string</i> is the required <i>length</i>.");
        f.raise(NullPointerException.class, "if <i>string</i> is null.", "string == null");
        f.raise(IllegalArgumentException.class, "if <i>length</i> is less-than zero.", "length < 0");
        f.body = "return Strings.padEnd(string, length, pad);";
        f.example(EXAMPLE_1, 169);

        // Finished!
        f = add();
        f.summary = "This function adds padding characters to the front of a string, as needed.";
        f.name = "padStart";
        f.formal("string", String.class, "is the string to pad.");
        f.formal("length", int.class, "is the desired length of the string.");
        f.formal("pad", char.class, "is the padding character to use.");
        f.returns(String.class, "Return the result of repeatedly adding the <i>pad</i> character to the <i>string</i> until the <i>string</i> is the required <i>length</i>.");
        f.raise(NullPointerException.class, "if <i>string</i> is null.", "string == null");
        f.raise(IllegalArgumentException.class, "if <i>length</i> is less-than zero.", "length < 0");
        f.body = "return Strings.padStart(string, length, pad);";
        f.example(EXAMPLE_1, 168);

        // Finished!
        f = add();
        f.summary = "This function converts a string to a boolean.";
        f.name = "parseBoolean";
        f.formal("value", String.class, "is the string representation of the value.");
        f.returns(boolean.class, "Return the <i>value</i> as a boolean.");
        f.raise(NullPointerException.class, "if <i>value</i> is null.", "value == null");
        f.raise(IllegalArgumentException.class, "if the <i>value</i> is not either the word 'true' or the word 'false'.", "!value.equals(\"true\") && !value.equals(\"false\")");
        f.body = "return Boolean.parseBoolean(value);";
        f.example(EXAMPLE_1, 171);

        // Finished!
        f = add();
        f.summary = "This function converts a string to a byte.";
        f.name = "parseByte";
        f.formal("value", String.class, "is the string representation of the value.");
        f.returns(byte.class, "Return the <i>value</i> as a number.");
        f.raise(NullPointerException.class, "if <i>value</i> is null.", "value == null");
        f.raise(NumberFormatException.class, "if the <i>value</i> cannot be parsed.");
        f.body = "return Byte.parseByte(value);";
        f.example(EXAMPLE_1, 171);

        // Finished!
        f = add();
        f.summary = "This function converts a string to a short.";
        f.name = "parseShort";
        f.formal("value", String.class, "is the string representation of the value.");
        f.returns(short.class, "Return the <i>value</i> as a number.");
        f.raise(NullPointerException.class, "if <i>value</i> is null.", "value == null");
        f.raise(NumberFormatException.class, "if the <i>value</i> cannot be parsed.");
        f.body = "return Short.parseShort(value);";
        f.example(EXAMPLE_1, 171);

        // Finished!
        f = add();
        f.summary = "This function converts a string to an int.";
        f.name = "parseInt";
        f.formal("value", String.class, "is the string representation of the value.");
        f.returns(int.class, "Return the <i>value</i> as a number.");
        f.raise(NullPointerException.class, "if <i>value</i> is null.", "value == null");
        f.raise(NumberFormatException.class, "if the <i>value</i> cannot be parsed.");
        f.body = "return Integer.parseInt(value);";
        f.example(EXAMPLE_1, 171);

        // Finished!
        f = add();
        f.summary = "This function converts a string to a long.";
        f.name = "parseLong";
        f.formal("value", String.class, "is the string representation of the value.");
        f.returns(long.class, "Return the <i>value</i> as a number.");
        f.raise(NullPointerException.class, "if <i>value</i> is null.", "value == null");
        f.raise(NumberFormatException.class, "if the <i>value</i> cannot be parsed.");
        f.body = "return Long.parseLong(value);";
        f.example(EXAMPLE_1, 171);

        // Finished!
        f = add();
        f.summary = "This function converts a string to a float.";
        f.name = "parseFloat";
        f.formal("value", String.class, "is the string representation of the value.");
        f.returns(float.class, "Return the <i>value</i> as a number.");
        f.raise(NullPointerException.class, "if <i>value</i> is null.", "value == null");
        f.raise(NumberFormatException.class, "if the <i>value</i> cannot be parsed.");
        f.body = "return Float.parseFloat(value);";
        f.example(EXAMPLE_1, 171);

        // Finished!
        f = add();
        f.summary = "This function converts a string to a double.";
        f.name = "parseDouble";
        f.formal("value", String.class, "is the string representation of the value.");
        f.returns(double.class, "Return the <i>value</i> as a number.");
        f.raise(NullPointerException.class, "if <i>value</i> is null.", "value == null");
        f.raise(NumberFormatException.class, "if the <i>value</i> cannot be parsed.");
        f.body = "return Double.parseDouble(value);";
        f.example(EXAMPLE_1, 171);

        // Finished!
        f = add();
        f.summary = "This function converts a string to a $JavaMathBigInteger$.";
        f.name = "parseBigInteger";
        f.formal("value", String.class, "is the string representation of the value.");
        f.returns(BigInteger.class, "Return the <i>value</i> as a number.");
        f.raise(NullPointerException.class, "if <i>value</i> is null.", "value == null");
        f.raise(NumberFormatException.class, "if the <i>value</i> cannot be parsed.");
        f.body = "return new BigInteger(value);";
        f.example(EXAMPLE_1, 171);

        // Finished!
        f = add();
        f.summary = "This function converts a string to a $JavaMathBigDecimal$.";
        f.name = "parseBigDecimal";
        f.formal("value", String.class, "is the string representation of the value.");
        f.returns(BigDecimal.class, "Return the <i>value</i> as a number.");
        f.raise(NullPointerException.class, "if <i>value</i> is null.", "value == null");
        f.raise(NumberFormatException.class, "if the <i>value</i> cannot be parsed.");
        f.detail(0, "The result will have the same scale as literals created using Autumn.");
        f.body = "return autumn.lang.internals.Helpers.createBigDecimal(value);";
        f.example(EXAMPLE_1, 171);

        // Finished!
        f = add();
        f.summary = "This function prints a value to standard-output.";
        f.name = "print";
        f.formal("value", Object.class, "is the value to print.");
        f.returns(void.class, "Nothing.");
        f.body = "System.out.print(value);";
        f.example(EXAMPLE_1, 212);

        // Finished!
        f = add();
        f.summary = "This function prints a value to standard-output followed by a newline.";
        f.name = "println";
        f.formal("value", Object.class, "is the value to print.");
        f.returns(void.class, "Nothing.");
        f.body = "System.out.println(value);";
        f.example(EXAMPLE_1, 124);
        f.example(EXAMPLE_1, 212);

        // Finished!
        f = add();
        f.summary = "This function prints a newline to standard-output.";
        f.name = "println";
        f.returns(void.class, "Nothing.");
        f.body = "System.out.println();";
        f.example(EXAMPLE_1, 212);

        // Finished!
        f = add();
        f.summary = "This function prints a series of lines to standard-output.";
        f.name = "printlns";
        f.formal("lines", Iterable.class, "are the values to print.");
        f.returns(void.class, "Nothing.");
        f.body = "for(Object line : lines) { println(line); }";
        f.example(EXAMPLE_1, 213);

        // Finished!
        f = add();
        f.summary = "This function prints a value to standard-error.";
        f.name = "printerr";
        f.formal("value", Object.class, "is the value to print.");
        f.returns(void.class, "Nothing.");
        f.body = "System.err.print(value);";
        f.example(EXAMPLE_1, 212);

        // Finished!
        f = add();
        f.summary = "This function prints a value to standard-error followed by a newline.";
        f.name = "printerrln";
        f.formal("value", Object.class, "is the value to print.");
        f.returns(void.class, "Nothing.");
        f.body = "System.err.println(value);";
        f.example(EXAMPLE_1, 212);

        // Finished!
        f = add();
        f.summary = "This function prints a newline to standard-error.";
        f.name = "printerrln";
        f.returns(void.class, "Nothing.");
        f.body = "System.err.println();";
        f.example(EXAMPLE_1, 212);

        // Finished!
        f = add();
        f.summary = "This function prints a series of lines to standard-output.";
        f.name = "printerrlns";
        f.formal("lines", Iterable.class, "are the values to print.");
        f.returns(void.class, "Nothing.");
        f.body = "for(Object line : lines) { printerrln(line); }";
        f.example(EXAMPLE_1, 213);

        f = add();
        f.summary = "(Under Development) - This function prints a formated string to standard-output.";
        f.name = "printf";
        f.formal("format", String.class, "is the format specifier.");
        f.formal("args", Iterable.class, "are the formatting arguments.");
        f.returns(void.class, "Nothing.");
        f.body = "System.out.printf(format, immutable(args).toArray());";

        f = add();
        f.summary = "(Under Development) - This function prints a formated string to standard-error.";
        f.name = "printerrf";
        f.formal("format", String.class, "is the format specifier.");
        f.formal("args", Iterable.class, "are the formatting arguments.");
        f.returns(void.class, "Nothing.");
        f.body = "System.err.printf(format, immutable(args).toArray());";

        // Finished!
        f = add();
        f.summary = "This function throws the exception that it is given as an argument.";
        f.name = "raise";
        f.formal("exception", Throwable.class, "is the exception to throw.");
        f.returns(void.class, "This function does not return, because the <i>exception</i> is thrown.");
        f.raise(Throwable.class, "in order to be capable of throwing any type of exception.");
        f.raise(NullPointerException.class, "if <i>exception</i> is null.", "exception == null");
        f.body = "throw exception;";
        f.example(EXAMPLE_1, 214);

        f = add();
        f.summary = "(Under Development) - This function creates an iterable whose iterator produces a sequence of integers.";
        f.name = "range";
        f.formal("minimum", int.class, "is the minimum integer in the sequence.");
        f.formal("maximum", int.class, "is the maximum integer in the sequence.");
        f.returns(Iterable.class, "The newly created iterable is returned.");
        f.body = "return T.range(minimum, maximum, 1);";

        f = add();
        f.summary = "(Under Development) - This function creates an iterable whose iterator produces a sequence of integers.";
        f.name = "range";
        f.formal("minimum", int.class, "is the minimum integer in the sequence.");
        f.formal("maximum", int.class, "is the maximum integer in the sequence.");
        f.formal("step", int.class, "is the size of each increment in the sequence.");
        f.returns(Iterable.class, "The newly created iterable is returned.");
        f.body = "return T.range(minimum, maximum, step);";

        f = add();
        f.summary = "(Under Development) - This function reads a line of input from standard-input.";
        f.name = "readln";
        f.returns(String.class, "The line of input, excluding the trailing newline.");
        f.body = "final Scanner stdin = new Scanner(System.in); return stdin.nextLine();";

        f = add();
        f.summary = "(Under Development) - This function retrieves a previously interned value.";
        f.name = "recall";
        f.formal("target", Object.class, "is the object that the key-value pair will be weakly attached to.");
        f.formal("key", Object.class, "is the key part of the key-value pair.");
        f.returns(Object.class, "Return the value part of the key-value pair, or null, if no such pair exists.");
        f.raise(NullPointerException.class, "if <i>target</i> is null.", "target == null");
        f.body = "return T.recall(target, key);";

        f = add();
        f.summary = "(Under Development) - This function weakly interns a key-value pair for later recollection.";
        f.name = "remember";
        f.formal("target", Object.class, "is the object that the key-value pair will be weakly attached to.");
        f.formal("key", Object.class, "is the key part of the key-value pair.");
        f.formal("value", Object.class, "is the value part of the key-value pair.");
        f.returns(void.class, "Nothing.");
        f.raise(NullPointerException.class, "if <i>target</i> is null.", "target == null");
        f.body = "T.remember(target, key, value);";

        // Finished!
        f = add();
        f.summary = "This function resets the entries in a record to their default values.";
        f.name = "reset";
        f.infer = true; // TODO: document? what about the set functions?
        f.formal("record", Record.class, "is the record to clear.");
        f.returns(Record.class, "Return a cleared copy of the <i>record</i>.");
        f.raise(NullPointerException.class, "if <i>record</i> is null.", "record == null");
        f.body = "return T.clear(record);";
        f.example(EXAMPLE_1, 216);

        // Finished!
        f = add();
        f.summary = "This function wraps an exception inside of a $AutumnLangExceptionsCheckedException$.";
        f.name = "rethrow";
        f.formal("exception", Throwable.class, "is the exception to rethrow.");
        f.returns(void.class, "Nothing.");
        f.raise(NullPointerException.class, "if <i>exception</i> is null.", "exception == null");
        f.raise(CheckedException.class, "is the wrapped exception.");
        f.body = "throw new CheckedException(exception);";

        // Finished!
        f = add();
        f.summary = "This function sets the value of an array element.";
        f.name = "set";
        f.formal("array", boolean[].class, "is the array that contains the element to set.");
        f.formal("index", int.class, "is the location of the element in the array.");
        f.formal("value", boolean.class, "is the value to replace the element with.");
        f.returns(boolean[].class, "The modified array is returned.");
        f.raise(NullPointerException.class, "if <i>array</i> is null.", "array == null");
        f.raise(IndexOutOfBoundsException.class, "if <i>index</i> is out of bounds.");
        f.body = "array[index] = value; return array;";
        f.example(EXAMPLE_1, 125);

        // Finished!
        f = add();
        f.summary = "This function sets the value of an array element.";
        f.name = "set";
        f.formal("array", char[].class, "is the array that contains the element to set.");
        f.formal("index", int.class, "is the location of the element in the array.");
        f.formal("value", char.class, "is the value to replace the element with.");
        f.returns(char[].class, "The modified array is returned.");
        f.raise(NullPointerException.class, "if <i>array</i> is null.", "array == null");
        f.raise(IndexOutOfBoundsException.class, "if <i>index</i> is out of bounds.");
        f.body = "array[index] = value; return array;";
        f.example(EXAMPLE_1, 126);

        // Finished!
        f = add();
        f.summary = "This function sets the value of an array element.";
        f.name = "set";
        f.formal("array", byte[].class, "is the array that contains the element to set.");
        f.formal("index", int.class, "is the location of the element in the array.");
        f.formal("value", byte.class, "is the value to replace the element with.");
        f.returns(byte[].class, "The modified array is returned.");
        f.raise(NullPointerException.class, "if <i>array</i> is null.", "array == null");
        f.raise(IndexOutOfBoundsException.class, "if <i>index</i> is out of bounds.");
        f.body = "array[index] = value; return array;";
        f.example(EXAMPLE_1, 127);

        // Finished!
        f = add();
        f.summary = "This function sets the value of an array element.";
        f.name = "set";
        f.formal("array", short[].class, "is the array that contains the element to set.");
        f.formal("index", int.class, "is the location of the element in the array.");
        f.formal("value", short.class, "is the value to replace the element with.");
        f.returns(short[].class, "The modified array is returned.");
        f.raise(NullPointerException.class, "if <i>array</i> is null.", "array == null");
        f.raise(IndexOutOfBoundsException.class, "if <i>index</i> is out of bounds.");
        f.body = "array[index] = value; return array;";
        f.example(EXAMPLE_1, 128);

        // Finished!
        f = add();
        f.summary = "This function sets the value of an array element.";
        f.name = "set";
        f.formal("array", int[].class, "is the array that contains the element to set.");
        f.formal("index", int.class, "is the location of the element in the array.");
        f.formal("value", int.class, "is the value to replace the element with.");
        f.returns(int[].class, "The modified array is returned.");
        f.raise(NullPointerException.class, "if <i>array</i> is null.", "array == null");
        f.raise(IndexOutOfBoundsException.class, "if <i>index</i> is out of bounds.");
        f.body = "array[index] = value; return array;";
        f.example(EXAMPLE_1, 129);

        // Finished!
        f = add();
        f.summary = "This function sets the value of an array element.";
        f.name = "set";
        f.formal("array", long[].class, "is the array that contains the element to set.");
        f.formal("index", int.class, "is the location of the element in the array.");
        f.formal("value", long.class, "is the value to replace the element with.");
        f.returns(long[].class, "The modified array is returned.");
        f.raise(NullPointerException.class, "if <i>array</i> is null.", "array == null");
        f.raise(IndexOutOfBoundsException.class, "if <i>index</i> is out of bounds.");
        f.body = "array[index] = value; return array;";
        f.example(EXAMPLE_1, 130);

        // Finished!
        f = add();
        f.summary = "This function sets the value of an array element.";
        f.name = "set";
        f.formal("array", float[].class, "is the array that contains the element to set.");
        f.formal("index", int.class, "is the location of the element in the array.");
        f.formal("value", float.class, "is the value to replace the element with.");
        f.returns(float[].class, "The modified array is returned.");
        f.raise(NullPointerException.class, "if <i>array</i> is null.", "array == null");
        f.raise(IndexOutOfBoundsException.class, "if <i>index</i> is out of bounds.");
        f.body = "array[index] = value; return array;";
        f.example(EXAMPLE_1, 131);

        // Finished!
        f = add();
        f.summary = "This function sets the value of an array element.";
        f.name = "set";
        f.formal("array", double[].class, "is the array that contains the element to set.");
        f.formal("index", int.class, "is the location of the element in the array.");
        f.formal("value", double.class, "is the value to replace the element with.");
        f.returns(double[].class, "The modified array is returned.");
        f.raise(NullPointerException.class, "if <i>array</i> is null.", "array == null");
        f.raise(IndexOutOfBoundsException.class, "if <i>index</i> is out of bounds.");
        f.body = "array[index] = value; return array;";
        f.example(EXAMPLE_1, 132);

        // Finished!
        f = add();
        f.summary = "This function sets the value of an array element.";
        f.name = "set";
        f.formal("array", BigInteger[].class, "is the array that contains the element to set.");
        f.formal("index", int.class, "is the location of the element in the array.");
        f.formal("value", BigInteger.class, "is the value to replace the element with.");
        f.returns(BigInteger[].class, "The modified array is returned.");
        f.raise(NullPointerException.class, "if <i>array</i> is null.", "array == null");
        f.raise(IndexOutOfBoundsException.class, "if <i>index</i> is out of bounds.");
        f.body = "array[index] = value; return array;";
        f.example(EXAMPLE_1, 133);

        // Finished!
        f = add();
        f.summary = "This function sets the value of an array element.";
        f.name = "set";
        f.formal("array", BigDecimal[].class, "is the array that contains the element to set.");
        f.formal("index", int.class, "is the location of the element in the array.");
        f.formal("value", BigDecimal.class, "is the value to replace the element with.");
        f.returns(BigDecimal[].class, "The modified array is returned.");
        f.raise(NullPointerException.class, "if <i>array</i> is null.", "array == null");
        f.raise(IndexOutOfBoundsException.class, "if <i>index</i> is out of bounds.");
        f.body = "array[index] = value; return array;";
        f.example(EXAMPLE_1, 134);

        // Finished!
        f = add();
        f.summary = "This function sets the value of an array element.";
        f.name = "set";
        f.formal("array", String[].class, "is the array that contains the element to set.");
        f.formal("index", int.class, "is the location of the element in the array.");
        f.formal("value", String.class, "is the value to replace the element with.");
        f.returns(String[].class, "The modified array is returned.");
        f.raise(NullPointerException.class, "if <i>array</i> is null.", "array == null");
        f.raise(IndexOutOfBoundsException.class, "if <i>index</i> is out of bounds.");
        f.body = "array[index] = value; return array;";
        f.example(EXAMPLE_1, 135);

        // Finished!
        f = add();
        f.summary = "This function sets the value of an array element.";
        f.name = "set";
        f.infer = true;
        f.formal("array", Object[].class, "is the array that contains the element to set.");
        f.formal("index", int.class, "is the location of the element in the array.");
        f.formal("value", Object.class, "is the value to replace the element with.");
        f.returns(Object[].class, "The modified array is returned.");
        f.raise(NullPointerException.class, "if <i>array</i> is null.", "array == null");
        f.raise(IndexOutOfBoundsException.class, "if <i>index</i> is out of bounds.");
        f.body = "array[index] = value; return array;";
        f.example(EXAMPLE_1, 136);

        f = add();
        f.summary = "(Under Development) - This function sets the value of a list element.";
        f.name = "set";
        f.formal("list", List.class, "is the list that contains the element.");
        f.formal("index", int.class, "is the location of the element in the list.");
        f.formal("value", Object.class, "is the value to replace the element with.");
        f.returns(List.class, "Return modified list itself, if the list is mutable; otherwise, return a modified copy of the list.");
        f.raise(NullPointerException.class, "if <i>list</i> is null.", "list == null");
        f.raise(IndexOutOfBoundsException.class, "if <i>index</i> is out of bounds.");
        f.body = "list.set(index, value); return list;";

        f = add();
        f.summary = "(Under Development) - This function sets the value of a map entry.";
        f.name = "set";
        f.formal("map", Map.class, "is the map that contains the entry.");
        f.formal("key", Object.class, "is the key that identifies the entry.");
        f.formal("value", Object.class, "is the new value of the entry.");
        f.returns(Map.class, "Return modified map itself, if the map is mutable; otherwise, return a modified copy of the map.");
        f.raise(NullPointerException.class, "if <i>map</i> is null.", "map == null");
        f.body = "map.put(key, value); return map;";

        f = add();
        f.summary = "(Under Development) - This function sets the value of an element in a record.";
        f.name = "set";
        f.infer = true;
        f.formal("owner", Record.class, "is the record that contains the element.");
        f.formal("index", int.class, "is the location of the element in the record.");
        f.formal("value", Object.class, "is the value to replace the element with.");
        f.returns(Record.class, "Return a modified copy of the record.");
        f.raise(NullPointerException.class, "if <i>owner</i> is null.", "owner == null");
        f.raise(IndexOutOfBoundsException.class, "if <i>index</i> is out of bounds.");
        f.raise(ClassCastException.class, "if the <i>value</i> cannot be cast to the element's type.");
        f.body = "return owner.set(index, value);";

        f = add();
        f.summary = "(Under Development) - This function sets the value of an element in a record.";
        f.name = "set";
        f.infer = true;
        f.formal("owner", Record.class, "is the record that contains the element.");
        f.formal("name", String.class, "is the name of the element.");
        f.formal("value", Object.class, "is the value to replace the element with.");
        f.returns(Record.class, "Return a modified copy of the record.");
        f.raise(NullPointerException.class, "if <i>owner</i> is null.", "owner == null");
        f.raise(NullPointerException.class, "if <i>name</i> is null.", "name == null");
        f.raise(NoSuchElementException.class, "if the <i>record</i> does not contain an element with the given <i>name</i>.");
        f.raise(ClassCastException.class, "if the <i>value</i> cannot be cast to the element's type.");
        f.body = "return set(owner, (owner.keys().indexOf(name)), value);";

        f = add();
        f.summary = "(Under Development) - This function sets the value of multiple elements in a record simultaneously.";
        f.name = "set";
        f.infer = true;
        f.formal("assignee", Record.class, "is the record that contains the elements.");
        f.formal("values", Map.class, "maps the (name or index) of an element to a new value for that element.");
        f.returns(Record.class, "Return a modified copy of the record.");
        f.raise(NullPointerException.class, "if <i>assignee</i> is null.", "assignee == null");
        f.raise(NullPointerException.class, "if <i>values</i> is null.", "values == null");
        f.body = "return T.set(assignee, values);";

        f = add();
        f.summary = "(Under Development) - This function sets the value of multiple elements in a record simultaneously.";
        f.name = "set";
        f.infer = true;
        f.formal("assignee", Record.class, "is the record that contains the elements to set.");
        f.formal("value", Record.class, "contains the new values for a subset of the <i>assignee</i>.");
        f.returns(Record.class, "Return a modified copy of the record.");
        f.raise(NullPointerException.class, "if <i>assignee</i> is null.", "assignee == null");
        f.raise(NullPointerException.class, "if <i>value</i> is null.", "value == null");
        f.body = "return T.set(assignee, value);";

        // Finished!
        f = add();
        f.summary = "This function converts a value to a string.";
        f.name = "str";
        f.formal("value", Object.class, "is the value to convert to a string.");
        f.returns(String.class, "Return the string representation of the <i>value</i>.");
        f.body = "return \"\" + value;";
        f.example(EXAMPLE_1, 211);

        // Finished!
        f = add();
        f.summary = "This function converts an iterable to a string.";
        f.name = "str";
        f.formal("iterable", Iterable.class, "is the iterable to convert to a string.");
        f.formal("prefix", String.class, "is a string to prepend onto the result.");
        f.formal("separator", String.class, "is a string to place between each element in the result.");
        f.formal("suffix", String.class, "is a string to append onto the result.");
        f.returns(String.class, "Return the <i>value</i> as a string.");
        f.raise(NullPointerException.class, "if <i>iterable</i> is null.", "iterable == null");
        f.raise(NullPointerException.class, "if <i>prefix</i> is null.", "prefix == null");
        f.raise(NullPointerException.class, "if <i>separator</i> is null.", "separator == null");
        f.raise(NullPointerException.class, "if <i>suffix</i> is null.", "suffix == null");
        f.body = "return T.str(iterable, prefix, separator, suffix);";
        f.example(EXAMPLE_1, 210);

        // Finished!
        f = add();
        f.summary = "This function computes the sum of a series of numbers.";
        f.name = "sum";
        f.formal("values", Iterable.class, "contains the values");
        f.returns(BigDecimal.class, "Return the sum of the <i>values</i>.");
        f.raise(NullPointerException.class, "if <i>values</i> is null.", "values == null");
        f.raise(NullPointerException.class, "if any of the <i>values</i> is null.");
        f.raise(IllegalArgumentException.class, "if any of the <i>values</i> cannot be widened.");
        f.detail(0, "Each value will be converted to a $JavaMathBigDecimal$ using the $big$ function.");
        f.detail(0, "If <i>values</i> is empty, then zero is returned.");
        f.body = "return T.sum(values);";
        f.example(EXAMPLE_1, 172);

        f = add();
        f.summary = "(Under Development) - This function synchronizes access to an object while running a task.";
        f.name = "sync";
        f.formal("locked", Object.class, "is the object to synchronous access to.");
        f.formal("action", Action.class, "is the task to perform synchronously.");
        f.returns(void.class, "Nothing.");
        f.raise(NullPointerException.class, "if <i>locked</i> is null.", "locked == null");
        f.raise(NullPointerException.class, "if <i>action</i> is null.", "action == null");
        f.body = "return;";

        f = add();
        f.summary = "(Under Development) - This function replaces escapable characters with equivalent escape sequences.";
        f.name = "unescape";
        f.formal("string", String.class, "is the string that contains the escapable characters.");
        f.returns(String.class, "Return the <i>string</i> with the escapable characters therein replaced with escape sequences.");
        f.raise(NullPointerException.class, "if <i>string</i> is null.", "string == null");
        f.body = "return T.unescape(string);";

        // Finished!
        f = add();
        f.summary = "(Under Development) - This function returns a unique integer as a result of each invocation.";
        f.name = "unique";
        f.returns(BigInteger.class, "Return a value that was not previously returned by this function during the current runtime.");
        f.detail(0, "This function is thread-safe.");
        f.body = "synchronized (F.class) { return T.unique(); }";
        // TODO: example

        // Finished!
        f = add();
        f.summary = "This function creates an unmodifiable list from another list.";
        f.name = "unmodifiable";
        f.formal("original", List.class, "is the data-structure to copy.");
        f.returns(List.class, "Return an unmodifiable view of the <i>original</i>.");
        f.raise(NullPointerException.class, "if <i>original</i> is null.", "original == null");
        f.body = "return Collections.unmodifiableList(original);";
        f.example(EXAMPLE_1, 208);

        // Finished!
        f = add();
        f.summary = "This function creates an unmodifiable set from another set.";
        f.name = "unmodifiable";
        f.formal("original", Set.class, "is the data-structure to copy.");
        f.returns(Set.class, "Return an unmodifiable view of the <i>original</i>.");
        f.raise(NullPointerException.class, "if <i>original</i> is null.", "original == null");
        f.body = "return Collections.unmodifiableSet(original);";
        f.example(EXAMPLE_1, 207);

        // Finished!
        f = add();
        f.summary = "This function creates an unmodifiable map from another map.";
        f.name = "unmodifiable";
        f.formal("original", Map.class, "is the data-structure to copy.");
        f.returns(Map.class, "Return an unmodifiable view of the <i>original</i>.");
        f.raise(NullPointerException.class, "if <i>original</i> is null.", "original == null");
        f.body = "return Collections.unmodifiableMap(original);";
        f.example(EXAMPLE_1, 206);

        // Finished!
        f = add();
        f.summary = "This function adds zeros to the front of a string, as needed.";
        f.name = "zfill";
        f.formal("string", String.class, "is the string to pad.");
        f.formal("length", int.class, "is the desired length of the string.");
        f.returns(String.class, "Return the result of repeatedly adding zero characters to the <i>string</i> until the <i>string</i> is the required <i>length</i>.");
        f.raise(NullPointerException.class, "if <i>string</i> is null.", "string == null");
        f.body = "return padStart(string, length, '0');";
        f.example(EXAMPLE_1, 170);

        f = add();
        f.summary = "(Under Development) - This function creates a list of lists from the equally indexed elements in a list of lists.";
        f.name = "zip";
        f.formal("iterables", Iterable.class, "");
        f.returns(List.class, "Return a list containing the values obtained from <i>iterable</i> in a randomly scrambled order.");
        f.raise(NullPointerException.class, "if <i>iterables</i> is null.", "iterables == null");
//        f.raise(NullPointerException.class, "if any of the iterables are null.", "iterables.contains(null)");
        f.body = "return null;";

        /**
         * Return the sorted list of functions.
         */
        Collections.sort(functions);
        return functions;
    }

    /**
     * This method writes the page's JSON file to the file-system.
     */
    public static void write()
            throws IOException
    {
        final List<List<String>> index = Lists.newArrayList();

        /**
         * Create an index of the functions.
         */
        for (Function x : functions())
        {
            index.add(Lists.newArrayList(x.toString(), ""));
        }

        /**
         * Write the JSON file.
         */
        final String content = (new JsonEncoder()).encode(index);
        final File file = new File(JSONBuilder.SPECIFICATION, "index-of-functions.json");
        Files.write(content, file, Charset.defaultCharset());
    }

    /**
     * This method generates the autumn.util.F class and writes it to the file-system.
     */
    public static void writeClassF()
            throws IOException
    {
        final StringBuilder klass = new StringBuilder();

        klass.append("package autumn.util;\n\n");

        klass.append("import java.math.BigInteger;\n");
        klass.append("import java.math.BigDecimal;\n");
        klass.append("import com.google.common.io.Files;\n");
        klass.append("import com.google.common.base.Strings;\n");
        klass.append("import java.nio.charset.Charset;\n");
        klass.append("import autumn.lang.annotations.*;\n");
        klass.append("import autumn.lang.exceptions.*;\n");
        klass.append("import autumn.lang.internals.*;\n");
        klass.append("import autumn.lang.internals.annotations.*;\n");
        klass.append("import autumn.lang.*;\n");
        klass.append("import java.lang.reflect.*;\n");
        klass.append("import java.util.*;\n");
        klass.append("import com.google.common.collect.*;\n");
        klass.append("import high.mackenzie.autumn.util.*;\n");

        klass.append("\n");
        klass.append("\n");
        klass.append("/** \n");
        klass.append(" * Special Functions\n");
        klass.append(" * \n");
        klass.append(" * <p>\n");
        klass.append(" * <b>Implementation Note:</b> This class was auto-generated using a script.\n");
        klass.append(" * </p>\n");
        klass.append(" */ \n");
        klass.append("public final class F \n{\n");

        for (Function x : functions)
        {
            klass.append(x.generate()).append("\n");
        }

        klass.append("}\n\n");

        Files.write(klass, JSONBuilder.AUTUMN_UTIL_F, Charset.defaultCharset());
    }
}
