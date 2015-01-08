package autumn.util;

import java.math.BigInteger;
import java.math.BigDecimal;
import com.google.common.io.Files;
import com.google.common.base.Strings;
import java.nio.charset.Charset;
import autumn.util.*;
import autumn.lang.annotations.*;
import autumn.lang.exceptions.*;
import autumn.lang.*;
import java.util.*;
import com.google.common.collect.*;

public final class F 
{
    
    public static boolean all (final java.lang.Iterable elements, final autumn.util.functors.Predicate condition) throws java.lang.NullPointerException, java.lang.NullPointerException, java.lang.Throwable
    {

        if(elements == null) { throw new java.lang.NullPointerException(); }
        if(condition == null) { throw new java.lang.NullPointerException(); }
        return T.all(elements, condition);
    }

    
    public static boolean any (final java.lang.Iterable elements, final autumn.util.functors.Predicate condition) throws java.lang.NullPointerException, java.lang.NullPointerException, java.lang.Throwable
    {

        if(elements == null) { throw new java.lang.NullPointerException(); }
        if(condition == null) { throw new java.lang.NullPointerException(); }
        return T.any(elements, condition);
    }

    
    public static java.lang.Object apply (final autumn.lang.Functor functor, final java.lang.Iterable arguments) throws java.lang.NullPointerException, java.lang.NullPointerException, java.lang.Throwable
    {

        if(functor == null) { throw new java.lang.NullPointerException(); }
        if(arguments == null) { throw new java.lang.NullPointerException(); }
        return T.apply(functor, arguments);
    }

    
    public static java.lang.Object applyQuietly (final autumn.lang.Functor functor, final java.lang.Iterable arguments) throws java.lang.NullPointerException, java.lang.NullPointerException, autumn.lang.exceptions.CheckedException
    {

        if(functor == null) { throw new java.lang.NullPointerException(); }
        if(arguments == null) { throw new java.lang.NullPointerException(); }
        try { return apply(functor, arguments); } catch (Throwable t) { throw new CheckedException(t); }
    }

    
    public static autumn.lang.AsyncTask async (final autumn.util.functors.Action action) throws java.lang.NullPointerException
    {

        if(action == null) { throw new java.lang.NullPointerException(); }
        return null;
    }

    
    public static java.math.BigDecimal big (final java.lang.Object value) throws java.lang.NullPointerException, java.lang.ClassCastException
    {

        if(value == null) { throw new java.lang.NullPointerException(); }
        return null;
    }

    
    public static int compare (final java.lang.Comparable left, final java.lang.Comparable right) 
    {

        return (left == null && right == null ? 0 : (left == null ? -1 : (right == null ? 1 : left.compareTo(right))));
    }

    
    public static int count (final java.lang.Iterable elements, final autumn.util.functors.Predicate condition) throws java.lang.NullPointerException, java.lang.NullPointerException, java.lang.Throwable
    {

        if(elements == null) { throw new java.lang.NullPointerException(); }
        if(condition == null) { throw new java.lang.NullPointerException(); }
        return T.count(elements, condition);
    }

    
    public static java.lang.Object decodeJson (final autumn.lang.Module module, final java.lang.String string) throws java.lang.NullPointerException, java.lang.NullPointerException
    {

        if(module == null) { throw new java.lang.NullPointerException(); }
        if(string == null) { throw new java.lang.NullPointerException(); }
        return T.json(string, module);
    }

    
    public static java.lang.Object defaultValue (final java.lang.Class type) 
    {

        return T.defaultValue(type);
    }

    
    public static autumn.lang.Delegate delegateTo (final java.lang.reflect.Constructor target) throws java.lang.NullPointerException
    {

        if(target == null) { throw new java.lang.NullPointerException(); }
        return T.delegateTo(target);
    }

    
    public static autumn.lang.Delegate delegateTo (final java.lang.reflect.Method target) throws java.lang.NullPointerException
    {

        if(target == null) { throw new java.lang.NullPointerException(); }
        return null;
    }

    
    public static java.lang.String encodeJson (final java.lang.Object object) throws java.lang.NullPointerException
    {

        if(object == null) { throw new java.lang.NullPointerException(); }
        return T.json(object);
    }

    
    public static java.util.List enumerate (final java.lang.Iterable iterable) throws java.lang.NullPointerException
    {

        if(iterable == null) { throw new java.lang.NullPointerException(); }
        return null;
    }

    
    public static java.lang.String escape (final java.lang.String string) throws java.lang.NullPointerException
    {

        if(string == null) { throw new java.lang.NullPointerException(); }
        return T.escape(string);
    }

    
    public static java.lang.String extractAnnotationValue (final java.lang.annotation.Annotation anno) throws java.lang.NullPointerException, java.lang.IllegalArgumentException
    {

        if(anno == null) { throw new java.lang.NullPointerException(); }
        return T.extractAnnotationValue(anno);
    }

    
    public static java.util.List extractAnnotationValues (final java.lang.annotation.Annotation anno) throws java.lang.NullPointerException
    {

        if(anno == null) { throw new java.lang.NullPointerException(); }
        return T.extractAnnotationValues(anno);
    }

    
    public static java.util.List filter (final java.lang.Iterable elements, final autumn.util.functors.Predicate condition) throws java.lang.NullPointerException, java.lang.NullPointerException, java.lang.Throwable
    {

        if(elements == null) { throw new java.lang.NullPointerException(); }
        if(condition == null) { throw new java.lang.NullPointerException(); }
        return T.filter(elements, condition);
    }

    
    public static java.lang.Object find (final java.lang.Iterable elements, final autumn.util.functors.Predicate condition) throws java.lang.NullPointerException, java.lang.NullPointerException, java.lang.Throwable
    {

        if(elements == null) { throw new java.lang.NullPointerException(); }
        if(condition == null) { throw new java.lang.NullPointerException(); }
        return T.find(elements, condition, 0);
    }

    
    public static java.lang.Object find (final java.lang.Iterable elements, final autumn.util.functors.Predicate condition, final int skip) throws java.lang.NullPointerException, java.lang.NullPointerException, java.lang.IllegalArgumentException, java.lang.Throwable
    {

        if(elements == null) { throw new java.lang.NullPointerException(); }
        if(condition == null) { throw new java.lang.NullPointerException(); }
        if(skip < 0) { throw new java.lang.IllegalArgumentException(); }
        return T.find(elements, condition, skip);
    }

    
    public static java.lang.annotation.Annotation findAnnotation (final java.lang.reflect.AnnotatedElement owner, final java.lang.Class type) throws java.lang.NullPointerException, java.lang.NullPointerException
    {

        if(owner == null) { throw new java.lang.NullPointerException(); }
        if(type == null) { throw new java.lang.NullPointerException(); }
        return T.findAnnotation(owner, type);
    }

    
    public static java.lang.reflect.Constructor findConstructor (final java.lang.Class owner, final java.lang.Iterable formals) throws java.lang.NullPointerException, java.lang.NullPointerException
    {

        if(owner == null) { throw new java.lang.NullPointerException(); }
        if(formals == null) { throw new java.lang.NullPointerException(); }
        return T.findConstructor(owner, formals);
    }

    
    public static java.lang.reflect.Field findField (final java.lang.Class owner, final java.lang.String name) throws java.lang.NullPointerException, java.lang.NullPointerException
    {

        if(owner == null) { throw new java.lang.NullPointerException(); }
        if(name == null) { throw new java.lang.NullPointerException(); }
        return T.findField(owner, name);
    }

    
    public static java.lang.reflect.Method findMethod (final java.lang.Class owner, final java.lang.String name, final java.lang.Iterable formals) throws java.lang.NullPointerException, java.lang.NullPointerException, java.lang.NullPointerException
    {

        if(owner == null) { throw new java.lang.NullPointerException(); }
        if(name == null) { throw new java.lang.NullPointerException(); }
        if(formals == null) { throw new java.lang.NullPointerException(); }
        return T.findMethod(owner, name, formals);
    }

    
    public static java.lang.Object first (final java.util.List list) throws java.lang.NullPointerException
    {

        if(list == null) { throw new java.lang.NullPointerException(); }
        return list.get(0);
    }

    
    public static java.lang.String format (final java.lang.String string, final java.lang.String format, final java.lang.Iterable args) throws java.lang.NullPointerException, java.lang.NullPointerException, java.lang.NullPointerException, java.lang.NullPointerException
    {

        if(string == null) { throw new java.lang.NullPointerException(); }
        if(format == null) { throw new java.lang.NullPointerException(); }
        if(args == null) { throw new java.lang.NullPointerException(); }
        return null;
    }

    
    public static java.math.BigDecimal get (final java.math.BigDecimal[] array, final int index) throws java.lang.NullPointerException, java.lang.IndexOutOfBoundsException
    {

        if(array == null) { throw new java.lang.NullPointerException(); }
        return array[index];
    }

    
    public static java.math.BigInteger get (final java.math.BigInteger[] array, final int index) throws java.lang.NullPointerException, java.lang.IndexOutOfBoundsException
    {

        if(array == null) { throw new java.lang.NullPointerException(); }
        return array[index];
    }

    
    public static java.lang.Object get (final java.lang.Object[] array, final int index) throws java.lang.NullPointerException, java.lang.IndexOutOfBoundsException
    {

        if(array == null) { throw new java.lang.NullPointerException(); }
        return array[index];
    }

    
    public static java.lang.String get (final java.lang.String[] array, final int index) throws java.lang.NullPointerException, java.lang.IndexOutOfBoundsException
    {

        if(array == null) { throw new java.lang.NullPointerException(); }
        return array[index];
    }

    
    public static boolean get (final boolean[] array, final int index) throws java.lang.NullPointerException, java.lang.IndexOutOfBoundsException
    {

        if(array == null) { throw new java.lang.NullPointerException(); }
        return array[index];
    }

    
    public static byte get (final byte[] array, final int index) throws java.lang.NullPointerException, java.lang.IndexOutOfBoundsException
    {

        if(array == null) { throw new java.lang.NullPointerException(); }
        return array[index];
    }

    
    public static char get (final char[] array, final int index) throws java.lang.NullPointerException, java.lang.IndexOutOfBoundsException
    {

        if(array == null) { throw new java.lang.NullPointerException(); }
        return array[index];
    }

    
    public static double get (final double[] array, final int index) throws java.lang.NullPointerException, java.lang.IndexOutOfBoundsException
    {

        if(array == null) { throw new java.lang.NullPointerException(); }
        return array[index];
    }

    
    public static float get (final float[] array, final int index) throws java.lang.NullPointerException, java.lang.IndexOutOfBoundsException
    {

        if(array == null) { throw new java.lang.NullPointerException(); }
        return array[index];
    }

    
    public static int get (final int[] array, final int index) throws java.lang.NullPointerException, java.lang.IndexOutOfBoundsException
    {

        if(array == null) { throw new java.lang.NullPointerException(); }
        return array[index];
    }

    
    public static long get (final long[] array, final int index) throws java.lang.NullPointerException, java.lang.IndexOutOfBoundsException
    {

        if(array == null) { throw new java.lang.NullPointerException(); }
        return array[index];
    }

    
    public static short get (final short[] array, final int index) throws java.lang.NullPointerException, java.lang.IndexOutOfBoundsException
    {

        if(array == null) { throw new java.lang.NullPointerException(); }
        return array[index];
    }

    
    public static java.lang.Object get (final java.util.List list, final int index) throws java.lang.NullPointerException, java.lang.IndexOutOfBoundsException
    {

        if(list == null) { throw new java.lang.NullPointerException(); }
        return list.get(index);
    }

    
    public static java.lang.Object get (final java.util.Map map, final java.lang.Object key) throws java.lang.NullPointerException
    {

        if(map == null) { throw new java.lang.NullPointerException(); }
        return map.get(key);
    }

    
    public static java.lang.Object get (final autumn.lang.Record owner, final int index) throws java.lang.NullPointerException, java.lang.IndexOutOfBoundsException
    {

        if(owner == null) { throw new java.lang.NullPointerException(); }
        return owner.get(index);
    }

    
    public static java.lang.Object get (final autumn.lang.Record owner, final java.lang.String key) throws java.lang.NullPointerException, java.util.NoSuchElementException
    {

        if(owner == null) { throw new java.lang.NullPointerException(); }
        return get(owner, owner.keys().indexOf(key));
    }

    
    public static int hash (final java.lang.Object value) 
    {

        return value == null ? 0 : value.hashCode();
    }

    
    public static java.util.Collection immutable (final java.util.Collection value) throws java.lang.NullPointerException
    {

        if(value == null) { throw new java.lang.NullPointerException(); }
        return Collections.unmodifiableCollection(new ArrayList(value));
    }

    
    public static java.util.List immutable (final java.util.List value) throws java.lang.NullPointerException
    {

        if(value == null) { throw new java.lang.NullPointerException(); }
        return Collections.unmodifiableList(new ArrayList(value));
    }

    
    public static java.util.Map immutable (final java.util.Map value) throws java.lang.NullPointerException
    {

        if(value == null) { throw new java.lang.NullPointerException(); }
        return Collections.unmodifiableMap(new HashMap(value));
    }

    
    public static java.util.Set immutable (final java.util.Set value) throws java.lang.NullPointerException
    {

        if(value == null) { throw new java.lang.NullPointerException(); }
        return Collections.unmodifiableSet(new HashSet(value));
    }

    
    public static boolean isAnnotation (final java.lang.Class type) throws java.lang.NullPointerException
    {

        if(type == null) { throw new java.lang.NullPointerException(); }
        return false;
    }

    
    public static boolean isAssignableTo (final java.lang.Class assignee, final java.lang.Class value) throws java.lang.NullPointerException, java.lang.NullPointerException
    {

        if(assignee == null) { throw new java.lang.NullPointerException(); }
        if(value == null) { throw new java.lang.NullPointerException(); }
        return false;
    }

    
    public static boolean isClass (final java.lang.Class type) throws java.lang.NullPointerException
    {

        if(type == null) { throw new java.lang.NullPointerException(); }
        return false;
    }

    
    public static boolean isDesign (final java.lang.Class type) throws java.lang.NullPointerException
    {

        if(type == null) { throw new java.lang.NullPointerException(); }
        return false;
    }

    
    public static boolean isEnum (final java.lang.Class type) throws java.lang.NullPointerException
    {

        if(type == null) { throw new java.lang.NullPointerException(); }
        return false;
    }

    
    public static boolean isException (final java.lang.Class type) throws java.lang.NullPointerException
    {

        if(type == null) { throw new java.lang.NullPointerException(); }
        return false;
    }

    
    public static boolean isFunctor (final java.lang.Class type) throws java.lang.NullPointerException
    {

        if(type == null) { throw new java.lang.NullPointerException(); }
        return false;
    }

    
    public static boolean isInterface (final java.lang.Class type) throws java.lang.NullPointerException
    {

        if(type == null) { throw new java.lang.NullPointerException(); }
        return false;
    }

    
    public static boolean isRecord (final java.lang.Class type) throws java.lang.NullPointerException
    {

        if(type == null) { throw new java.lang.NullPointerException(); }
        return isDesign(type) || isStruct(type) || isTuple(type);
    }

    
    public static boolean isStruct (final java.lang.Class type) throws java.lang.NullPointerException
    {

        if(type == null) { throw new java.lang.NullPointerException(); }
        return false;
    }

    
    public static boolean isSubtypeOf (final java.lang.Class subtype, final java.lang.Class supertype) throws java.lang.NullPointerException, java.lang.NullPointerException
    {

        if(subtype == null) { throw new java.lang.NullPointerException(); }
        if(supertype == null) { throw new java.lang.NullPointerException(); }
        return false;
    }

    
    public static boolean isTuple (final java.lang.Class type) throws java.lang.NullPointerException
    {

        if(type == null) { throw new java.lang.NullPointerException(); }
        return false;
    }

    
    public static java.lang.Iterable iter (final java.lang.CharSequence input) throws java.lang.NullPointerException
    {

        if(input == null) { throw new java.lang.NullPointerException(); }
        return T.iter(input);
    }

    
    public static java.lang.Iterable iter (final java.lang.Iterable input) throws java.lang.NullPointerException
    {

        if(input == null) { throw new java.lang.NullPointerException(); }
        return T.iter(input);
    }

    
    public static java.lang.Iterable iter (final java.lang.Object[] input) throws java.lang.NullPointerException
    {

        if(input == null) { throw new java.lang.NullPointerException(); }
        return T.iter(input);
    }

    
    public static java.lang.Iterable iter (final boolean[] input) throws java.lang.NullPointerException
    {

        if(input == null) { throw new java.lang.NullPointerException(); }
        return T.iter(input);
    }

    
    public static java.lang.Iterable iter (final byte[] input) throws java.lang.NullPointerException
    {

        if(input == null) { throw new java.lang.NullPointerException(); }
        return T.iter(input);
    }

    
    public static java.lang.Iterable iter (final char[] input) throws java.lang.NullPointerException
    {

        if(input == null) { throw new java.lang.NullPointerException(); }
        return T.iter(input);
    }

    
    public static java.lang.Iterable iter (final double[] input) throws java.lang.NullPointerException
    {

        if(input == null) { throw new java.lang.NullPointerException(); }
        return T.iter(input);
    }

    
    public static java.lang.Iterable iter (final float[] input) throws java.lang.NullPointerException
    {

        if(input == null) { throw new java.lang.NullPointerException(); }
        return T.iter(input);
    }

    
    public static java.lang.Iterable iter (final int[] input) throws java.lang.NullPointerException
    {

        if(input == null) { throw new java.lang.NullPointerException(); }
        return T.iter(input);
    }

    
    public static java.lang.Iterable iter (final long[] input) throws java.lang.NullPointerException
    {

        if(input == null) { throw new java.lang.NullPointerException(); }
        return T.iter(input);
    }

    
    public static java.lang.Iterable iter (final short[] input) throws java.lang.NullPointerException
    {

        if(input == null) { throw new java.lang.NullPointerException(); }
        return T.iter(input);
    }

    
    public static java.lang.Object last (final java.util.List list) throws java.lang.NullPointerException
    {

        if(list == null) { throw new java.lang.NullPointerException(); }
        return list.get(list.size() - 1);
    }

    
    public static int len (final java.lang.Object[] array) throws java.lang.NullPointerException
    {

        if(array == null) { throw new java.lang.NullPointerException(); }
        return array.length;
    }

    
    public static int len (final boolean[] array) throws java.lang.NullPointerException
    {

        if(array == null) { throw new java.lang.NullPointerException(); }
        return array.length;
    }

    
    public static int len (final byte[] array) throws java.lang.NullPointerException
    {

        if(array == null) { throw new java.lang.NullPointerException(); }
        return array.length;
    }

    
    public static int len (final char[] array) throws java.lang.NullPointerException
    {

        if(array == null) { throw new java.lang.NullPointerException(); }
        return array.length;
    }

    
    public static int len (final double[] array) throws java.lang.NullPointerException
    {

        if(array == null) { throw new java.lang.NullPointerException(); }
        return array.length;
    }

    
    public static int len (final float[] array) throws java.lang.NullPointerException
    {

        if(array == null) { throw new java.lang.NullPointerException(); }
        return array.length;
    }

    
    public static int len (final int[] array) throws java.lang.NullPointerException
    {

        if(array == null) { throw new java.lang.NullPointerException(); }
        return array.length;
    }

    
    public static int len (final long[] array) throws java.lang.NullPointerException
    {

        if(array == null) { throw new java.lang.NullPointerException(); }
        return array.length;
    }

    
    public static int len (final short[] array) throws java.lang.NullPointerException
    {

        if(array == null) { throw new java.lang.NullPointerException(); }
        return array.length;
    }

    
    public static int len (final java.util.List list) throws java.lang.NullPointerException
    {

        if(list == null) { throw new java.lang.NullPointerException(); }
        return list.size();
    }

    
    public static int len (final java.util.Map map) throws java.lang.NullPointerException
    {

        if(map == null) { throw new java.lang.NullPointerException(); }
        return map.size();
    }

    
    public static int len (final autumn.lang.Record record) throws java.lang.NullPointerException
    {

        if(record == null) { throw new java.lang.NullPointerException(); }
        return record.size();
    }

    
    public static int len (final java.lang.CharSequence string) throws java.lang.NullPointerException
    {

        if(string == null) { throw new java.lang.NullPointerException(); }
        return string.length();
    }

    
    public static java.util.List map (final java.lang.Iterable elements, final autumn.util.functors.Function1 functor) throws java.lang.NullPointerException, java.lang.NullPointerException, java.lang.Throwable
    {

        if(elements == null) { throw new java.lang.NullPointerException(); }
        if(functor == null) { throw new java.lang.NullPointerException(); }
        return T.map(elements, functor);
    }

    
    public static java.lang.Object max (final java.lang.Iterable values) throws java.lang.NullPointerException
    {

        if(values == null) { throw new java.lang.NullPointerException(); }
        return null;
    }

    
    public static java.math.BigDecimal mean (final java.lang.Iterable values) throws java.lang.NullPointerException
    {

        if(values == null) { throw new java.lang.NullPointerException(); }
        return null;
    }

    
    public static java.lang.Object median (final java.lang.Iterable values) throws java.lang.NullPointerException
    {

        if(values == null) { throw new java.lang.NullPointerException(); }
        return null;
    }

    @Infer
    public static autumn.lang.Functor memoize (final autumn.lang.Functor functor) throws java.lang.NullPointerException
    {

        if(functor == null) { throw new java.lang.NullPointerException(); }
        return null;
    }

    
    public static java.lang.Object min (final java.lang.Iterable values) throws java.lang.NullPointerException
    {

        if(values == null) { throw new java.lang.NullPointerException(); }
        return null;
    }

    
    public static java.lang.Object mode (final java.lang.Iterable values) throws java.lang.NullPointerException
    {

        if(values == null) { throw new java.lang.NullPointerException(); }
        return null;
    }

    
    public static java.util.Collection mutable (final java.util.Collection value) throws java.lang.NullPointerException
    {

        if(value == null) { throw new java.lang.NullPointerException(); }
        return new ArrayList(value);
    }

    
    public static java.util.List mutable (final java.util.List value) throws java.lang.NullPointerException
    {

        if(value == null) { throw new java.lang.NullPointerException(); }
        return new ArrayList(value);
    }

    
    public static java.util.Map mutable (final java.util.Map value) throws java.lang.NullPointerException
    {

        if(value == null) { throw new java.lang.NullPointerException(); }
        return new HashMap(value);
    }

    
    public static java.util.Set mutable (final java.util.Set value) throws java.lang.NullPointerException
    {

        if(value == null) { throw new java.lang.NullPointerException(); }
        return new HashSet(value);
    }

    
    public static java.lang.Object newArray (final java.lang.Class type, final java.util.List dimensions) throws java.lang.NullPointerException, java.lang.NullPointerException, java.lang.ClassCastException, java.lang.IllegalArgumentException
    {

        if(type == null) { throw new java.lang.NullPointerException(); }
        if(dimensions == null) { throw new java.lang.NullPointerException(); }
        return null;
    }

    
    public static java.lang.Object newArray (final java.lang.Class type, final int size) throws java.lang.NullPointerException, java.lang.IllegalArgumentException
    {

        if(type == null) { throw new java.lang.NullPointerException(); }
        if(size < 0) { throw new java.lang.IllegalArgumentException(); }
        return java.lang.reflect.Array.newInstance(type, size);
    }

    
    public static java.util.List newList (final java.lang.Iterable elements) throws java.lang.NullPointerException
    {

        if(elements == null) { throw new java.lang.NullPointerException(); }
        return immutable(Lists.newLinkedList(elements));
    }

    
    public static java.util.Map newMap (final java.util.Map original) throws java.lang.NullPointerException
    {

        if(original == null) { throw new java.lang.NullPointerException(); }
        return immutable(original);
    }

    
    public static java.lang.Object newProxy (final java.lang.Class type, final autumn.util.functors.ProxyHandler handler) throws java.lang.NullPointerException, java.lang.NullPointerException
    {

        if(type == null) { throw new java.lang.NullPointerException(); }
        if(handler == null) { throw new java.lang.NullPointerException(); }
        return null;
    }

    
    public static java.util.Set newSet (final java.lang.Iterable elements) throws java.lang.NullPointerException
    {

        if(elements == null) { throw new java.lang.NullPointerException(); }
        return immutable(Sets.newHashSet(elements));
    }

    
    public static java.lang.String padEnd (final java.lang.String string, final int length, final char pad) throws java.lang.NullPointerException
    {

        if(string == null) { throw new java.lang.NullPointerException(); }
        return padEnd(string, length, pad);
    }

    
    public static java.lang.String padStart (final java.lang.String string, final int length, final char pad) throws java.lang.NullPointerException
    {

        if(string == null) { throw new java.lang.NullPointerException(); }
        return Strings.padStart(string, length, pad);
    }

    
    public static java.math.BigDecimal parseBigDecimal (final java.lang.String value) throws java.lang.NullPointerException, java.lang.NumberFormatException
    {

        if(value == null) { throw new java.lang.NullPointerException(); }
        return autumn.lang.internals.Helpers.createBigDecimal(value);
    }

    
    public static java.math.BigInteger parseBigInteger (final java.lang.String value) throws java.lang.NullPointerException, java.lang.NumberFormatException
    {

        if(value == null) { throw new java.lang.NullPointerException(); }
        return null;
    }

    
    public static boolean parseBoolean (final java.lang.String value) throws java.lang.NullPointerException, java.lang.NumberFormatException
    {

        if(value == null) { throw new java.lang.NullPointerException(); }
        return Boolean.parseBoolean(value);
    }

    
    public static byte parseByte (final java.lang.String value) throws java.lang.NullPointerException, java.lang.NumberFormatException
    {

        if(value == null) { throw new java.lang.NullPointerException(); }
        return Byte.parseByte(value);
    }

    
    public static double parseDouble (final java.lang.String value) throws java.lang.NullPointerException, java.lang.NumberFormatException
    {

        if(value == null) { throw new java.lang.NullPointerException(); }
        return Double.parseDouble(value);
    }

    
    public static float parseFloat (final java.lang.String value) throws java.lang.NullPointerException, java.lang.NumberFormatException
    {

        if(value == null) { throw new java.lang.NullPointerException(); }
        return Float.parseFloat(value);
    }

    
    public static int parseInt (final java.lang.String value) throws java.lang.NullPointerException, java.lang.NumberFormatException
    {

        if(value == null) { throw new java.lang.NullPointerException(); }
        return Integer.parseInt(value);
    }

    
    public static long parseLong (final java.lang.String value) throws java.lang.NullPointerException, java.lang.NumberFormatException
    {

        if(value == null) { throw new java.lang.NullPointerException(); }
        return Long.parseLong(value);
    }

    
    public static short parseShort (final java.lang.String value) throws java.lang.NullPointerException, java.lang.NumberFormatException
    {

        if(value == null) { throw new java.lang.NullPointerException(); }
        return Short.parseShort(value);
    }

    
    public static void print (final java.lang.Object value) 
    {

        System.out.print(value);
    }

    
    public static void printerr (final java.lang.Object value) 
    {

        System.err.print(value);
    }

    
    public static void printerrln () 
    {

        System.err.println();
    }

    
    public static void printerrln (final java.lang.Object value) 
    {

        System.err.println(value);
    }

    
    public static void printerrlns (final java.lang.Iterable lines) 
    {

        for(Object line : lines) { printerrln(line); }
    }

    
    public static void println () 
    {

        System.out.println();
    }

    
    public static void println (final java.lang.Object value) 
    {

        System.out.println(value);
    }

    
    public static void printlns (final java.lang.Iterable lines) 
    {

        for(Object line : lines) { println(line); }
    }

    
    public static void raise (final java.lang.Exception exception) throws java.lang.Throwable, java.lang.NullPointerException
    {

        if(exception == null) { throw new java.lang.NullPointerException(); }
        throw exception;
    }

    
    public static java.lang.Iterable range (final int minimum, final int maximum) 
    {

        return T.range(minimum, maximum, 1);
    }

    
    public static java.lang.Iterable range (final int minimum, final int maximum, final int step) 
    {

        return T.range(minimum, maximum, step);
    }

    
    public static java.lang.String readln () 
    {

        final Scanner stdin = new Scanner(System.in); return stdin.nextLine();
    }

    
    public static java.lang.Object recall (final java.lang.Object target, final java.lang.Object key) throws java.lang.NullPointerException
    {

        if(target == null) { throw new java.lang.NullPointerException(); }
        return T.recall(target, key);
    }

    
    public static java.lang.Object reduce (final java.lang.Iterable elements, final autumn.util.functors.Function2 functor) throws java.lang.NullPointerException, java.lang.NullPointerException, java.lang.Throwable
    {

        if(elements == null) { throw new java.lang.NullPointerException(); }
        if(functor == null) { throw new java.lang.NullPointerException(); }
        return T.reduce(elements, functor);
    }

    
    public static void remember (final java.lang.Object target, final java.lang.Object key, final java.lang.Object value) throws java.lang.NullPointerException
    {

        if(target == null) { throw new java.lang.NullPointerException(); }
        T.remember(target, key, value);
    }

    @Infer
    public static autumn.lang.Record reset (final autumn.lang.Record record) throws java.lang.NullPointerException
    {

        if(record == null) { throw new java.lang.NullPointerException(); }
        return T.clear(record);
    }

    
    public static void rethrow (final java.lang.Throwable exception) throws java.lang.NullPointerException, autumn.lang.exceptions.CheckedException
    {

        if(exception == null) { throw new java.lang.NullPointerException(); }
        throw new CheckedException(exception);
    }

    
    public static java.util.List search (final java.util.ArrayList list, final autumn.lang.TypedFunctor ordering) throws java.lang.NullPointerException
    {

        if(list == null) { throw new java.lang.NullPointerException(); }
        return null;
    }

    
    public static java.math.BigDecimal[] set (final java.math.BigDecimal[] array, final int index, final java.math.BigDecimal value) throws java.lang.NullPointerException, java.lang.IndexOutOfBoundsException
    {

        if(array == null) { throw new java.lang.NullPointerException(); }
        array[index] = value; return array;
    }

    
    public static java.math.BigInteger[] set (final java.math.BigInteger[] array, final int index, final java.math.BigInteger value) throws java.lang.NullPointerException, java.lang.IndexOutOfBoundsException
    {

        if(array == null) { throw new java.lang.NullPointerException(); }
        array[index] = value; return array;
    }

    @Infer
    public static java.lang.Object[] set (final java.lang.Object[] array, final int index, final java.lang.Object value) throws java.lang.NullPointerException, java.lang.IndexOutOfBoundsException
    {

        if(array == null) { throw new java.lang.NullPointerException(); }
        array[index] = value; return array;
    }

    
    public static java.lang.String[] set (final java.lang.String[] array, final int index, final java.lang.String value) throws java.lang.NullPointerException, java.lang.IndexOutOfBoundsException
    {

        if(array == null) { throw new java.lang.NullPointerException(); }
        array[index] = value; return array;
    }

    
    public static boolean[] set (final boolean[] array, final int index, final boolean value) throws java.lang.NullPointerException, java.lang.IndexOutOfBoundsException
    {

        if(array == null) { throw new java.lang.NullPointerException(); }
        array[index] = value; return array;
    }

    
    public static byte[] set (final byte[] array, final int index, final byte value) throws java.lang.NullPointerException, java.lang.IndexOutOfBoundsException
    {

        if(array == null) { throw new java.lang.NullPointerException(); }
        array[index] = value; return array;
    }

    
    public static char[] set (final char[] array, final int index, final char value) throws java.lang.NullPointerException, java.lang.IndexOutOfBoundsException
    {

        if(array == null) { throw new java.lang.NullPointerException(); }
        array[index] = value; return array;
    }

    
    public static double[] set (final double[] array, final int index, final double value) throws java.lang.NullPointerException, java.lang.IndexOutOfBoundsException
    {

        if(array == null) { throw new java.lang.NullPointerException(); }
        array[index] = value; return array;
    }

    
    public static float[] set (final float[] array, final int index, final float value) throws java.lang.NullPointerException, java.lang.IndexOutOfBoundsException
    {

        if(array == null) { throw new java.lang.NullPointerException(); }
        array[index] = value; return array;
    }

    
    public static int[] set (final int[] array, final int index, final int value) throws java.lang.NullPointerException, java.lang.IndexOutOfBoundsException
    {

        if(array == null) { throw new java.lang.NullPointerException(); }
        array[index] = value; return array;
    }

    
    public static long[] set (final long[] array, final int index, final long value) throws java.lang.NullPointerException, java.lang.IndexOutOfBoundsException
    {

        if(array == null) { throw new java.lang.NullPointerException(); }
        array[index] = value; return array;
    }

    
    public static short[] set (final short[] array, final int index, final short value) throws java.lang.NullPointerException, java.lang.IndexOutOfBoundsException
    {

        if(array == null) { throw new java.lang.NullPointerException(); }
        array[index] = value; return array;
    }

    @Infer
    public static autumn.lang.Record set (final autumn.lang.Record assignee, final autumn.lang.Record value) throws java.lang.NullPointerException, java.lang.NullPointerException
    {

        if(assignee == null) { throw new java.lang.NullPointerException(); }
        if(value == null) { throw new java.lang.NullPointerException(); }
        return T.set(assignee, value);
    }

    @Infer
    public static autumn.lang.Record set (final autumn.lang.Record assignee, final java.util.Map values) throws java.lang.NullPointerException, java.lang.NullPointerException
    {

        if(assignee == null) { throw new java.lang.NullPointerException(); }
        if(values == null) { throw new java.lang.NullPointerException(); }
        return T.set(assignee, values);
    }

    
    public static java.util.List set (final java.util.List list, final int index, final java.lang.Object value) throws java.lang.NullPointerException, java.lang.IndexOutOfBoundsException
    {

        if(list == null) { throw new java.lang.NullPointerException(); }
        list.set(index, value); return list;
    }

    
    public static java.util.Map set (final java.util.Map map, final int key, final java.lang.Object value) throws java.lang.NullPointerException
    {

        if(map == null) { throw new java.lang.NullPointerException(); }
        map.put(key, value); return map;
    }

    @Infer
    public static autumn.lang.Record set (final autumn.lang.Record owner, final int index, final java.lang.Object value) throws java.lang.NullPointerException, java.lang.IndexOutOfBoundsException, java.lang.ClassCastException
    {

        if(owner == null) { throw new java.lang.NullPointerException(); }
        return owner.set(index, value);
    }

    @Infer
    public static autumn.lang.Record set (final autumn.lang.Record owner, final java.lang.String name, final java.lang.Object value) throws java.lang.NullPointerException, java.lang.NullPointerException, java.util.NoSuchElementException, java.lang.ClassCastException
    {

        if(owner == null) { throw new java.lang.NullPointerException(); }
        if(name == null) { throw new java.lang.NullPointerException(); }
        return set(owner, (owner.keys().indexOf(name)), value);
    }

    
    public static java.util.List sort (final java.util.ArrayList list, final autumn.lang.TypedFunctor ordering) throws java.lang.NullPointerException
    {

        if(list == null) { throw new java.lang.NullPointerException(); }
        return null;
    }

    
    public static java.math.BigDecimal standardDeviation (final java.lang.Iterable values) throws java.lang.NullPointerException
    {

        if(values == null) { throw new java.lang.NullPointerException(); }
        return null;
    }

    
    public static java.lang.String str (final java.lang.Iterable iterable, final java.lang.String prefix, final java.lang.String separator, final java.lang.String suffix) throws java.lang.NullPointerException, java.lang.NullPointerException, java.lang.NullPointerException, java.lang.NullPointerException
    {

        if(iterable == null) { throw new java.lang.NullPointerException(); }
        if(prefix == null) { throw new java.lang.NullPointerException(); }
        if(separator == null) { throw new java.lang.NullPointerException(); }
        if(suffix == null) { throw new java.lang.NullPointerException(); }
        return T.str(iterable, prefix, separator, suffix);
    }

    
    public static java.lang.String str (final java.lang.Object value) throws java.lang.NullPointerException, java.lang.NumberFormatException
    {

        if(value == null) { throw new java.lang.NullPointerException(); }
        return "" + value;
    }

    
    public static java.math.BigDecimal sum (final java.lang.Iterable values) throws java.lang.NullPointerException
    {

        if(values == null) { throw new java.lang.NullPointerException(); }
        return null;
    }

    
    public static void sync (final java.lang.Object locked, final autumn.util.functors.Action action) throws java.lang.NullPointerException, java.lang.NullPointerException
    {

        if(locked == null) { throw new java.lang.NullPointerException(); }
        if(action == null) { throw new java.lang.NullPointerException(); }
        return;
    }

    @Infer
    public static autumn.lang.Functor synchronize (final autumn.lang.Functor functor) throws java.lang.NullPointerException
    {

        if(functor == null) { throw new java.lang.NullPointerException(); }
        return null;
    }

    
    public static java.lang.String unescape (final java.lang.String string) throws java.lang.NullPointerException
    {

        if(string == null) { throw new java.lang.NullPointerException(); }
        return T.unescape(string);
    }

    
    public static java.math.BigInteger unique () 
    {

        return T.unique();
    }

    
    public static java.util.Collection unmodifiable (final java.util.Collection value) throws java.lang.NullPointerException
    {

        if(value == null) { throw new java.lang.NullPointerException(); }
        return Collections.unmodifiableCollection(value);
    }

    
    public static java.util.List unmodifiable (final java.util.List value) throws java.lang.NullPointerException
    {

        if(value == null) { throw new java.lang.NullPointerException(); }
        return Collections.unmodifiableList(value);
    }

    
    public static java.util.Map unmodifiable (final java.util.Map value) throws java.lang.NullPointerException
    {

        if(value == null) { throw new java.lang.NullPointerException(); }
        return Collections.unmodifiableMap(value);
    }

    
    public static java.util.Set unmodifiable (final java.util.Set value) throws java.lang.NullPointerException
    {

        if(value == null) { throw new java.lang.NullPointerException(); }
        return Collections.unmodifiableSet(value);
    }

    
    public static java.lang.String zfill (final java.lang.String string, final int length) throws java.lang.NullPointerException
    {

        if(string == null) { throw new java.lang.NullPointerException(); }
        return padStart(string, length, '0');
    }

    
    public static java.util.List zip (final java.lang.Iterable iterables) throws java.lang.NullPointerException
    {

        if(iterables == null) { throw new java.lang.NullPointerException(); }
        return null;
    }

}

