package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.compiler.ast.nodes.TypeSpecifier;
import autumn.util.ds.ImmutableCollection;
import autumn.util.ds.ImmutableMap;
import autumn.util.ds.ImmutableSet;
import autumn.util.ds.ImmutableSortedMap;
import autumn.util.ds.ImmutableSortedSet;
import autumn.util.ds.MutableCollection;
import autumn.util.ds.MutableList;
import autumn.util.ds.MutableMap;
import autumn.util.ds.MutableSet;
import autumn.util.ds.MutableSortedMap;
import autumn.util.ds.MutableSortedSet;
import autumn.util.functors.MethodCompareTo;
import autumn.util.functors.MethodEquals;
import autumn.util.functors.MethodHashCode;
import autumn.util.functors.MethodIterator;
import autumn.util.functors.MethodToString;
import autumn.util.functors.Predicate;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IClassType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IDeclaredType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IExpressionType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IInterfaceType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IReferenceType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IReturnType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVariableType;
import high.mackenzie.autumn.lang.compiler.utils.Utils;
import java.util.Map;
import java.util.Set;

/**
 * Essentially, an instance of this class implements import-directives.
 *
 * @author Mackenzie High
 */
public final class Importer
{
    private final ModuleCompiler module;

    private final Map<String, String> imports = Maps.newTreeMap();

    /**
     * Sole Constructor.
     *
     * @param module is essentially the module that is being compiled.
     */
    public Importer(final ModuleCompiler module)
    {
        this.module = module;

        // autumn.lang
        importClass(autumn.lang.Copyable.class);
        importClass(autumn.lang.Delegate.class);
        importClass(autumn.lang.Functor.class);
        importClass(autumn.lang.Immutable.class);
        importClass(autumn.lang.Lambda.class);
        importClass(autumn.lang.Local.class);
        importClass(autumn.lang.LocalsMap.class);
        importClass(autumn.lang.Module.class);
        importClass(autumn.lang.Mutable.class);
        importClass(autumn.lang.Record.class);
        importClass(autumn.lang.RecordEntry.class);
        importClass(autumn.lang.SpecialMethods.class);
        importClass(autumn.lang.StaticFunctor.class);
        importClass(autumn.lang.TypedFunctor.class);

        // autumn.lang.annotations
        importClass(autumn.lang.annotations.Clinit.class);
        importClass(autumn.lang.annotations.Hide.class);
        importClass(autumn.lang.annotations.InferReturnType.class);
        importClass(autumn.lang.annotations.Once.class);
        importClass(autumn.lang.annotations.Start.class);
        importClass(autumn.lang.annotations.Sync.class);

        // autumn.lang.compiler
        importClass(autumn.lang.compiler.Autumn.class);

        // autumn.lang.exceptions
        importClass(autumn.lang.exceptions.AssertionFailedException.class);
        importClass(autumn.lang.exceptions.AssumptionFailedException.class);
        importClass(autumn.lang.exceptions.CheckedException.class);

        // autumn.util
        importClass(autumn.util.Bitwise.class);
        importClass(autumn.util.DS.class);
        importClass(autumn.util.F.class);
        importClass(autumn.util.Files.class);
        importClass(autumn.util.Functors.class);
        importClass(autumn.util.Predicates.class);
        importClass(autumn.util.Records.class);
        importClass(autumn.util.Reflect.class);
        importClass(autumn.util.Strings.class);
        importClass(autumn.util.Threads.class);

        // autumn.util.data.json
        importClass(autumn.util.data.json.Json.class);
        importClass(autumn.util.data.json.JsonArray.class);
        importClass(autumn.util.data.json.JsonBoolean.class);
        importClass(autumn.util.data.json.JsonObject.class);
        importClass(autumn.util.data.json.JsonNull.class);
        importClass(autumn.util.data.json.JsonNumber.class);
        importClass(autumn.util.data.json.JsonValue.class);

        // autumn.util.ds
        importClass(ImmutableCollection.class);
        importClass(ImmutableList.class);
        importClass(ImmutableMap.class);
        importClass(ImmutableSet.class);
        importClass(ImmutableSortedMap.class);
        importClass(ImmutableSortedSet.class);
        importClass(MutableCollection.class);
        importClass(MutableList.class);
        importClass(MutableMap.class);
        importClass(MutableSet.class);
        importClass(MutableSortedMap.class);
        importClass(MutableSortedSet.class);

        // autumn.util.test
        importClass(autumn.util.test.MalformedTestException.class);
        importClass(autumn.util.test.Test.class);
        importClass(autumn.util.test.TestCase.class);
        importClass(autumn.util.test.TestResult.class);
        importClass(autumn.util.test.TestResults.class);
        importClass(autumn.util.test.Tester.class);
        importClass(autumn.util.test.UnitTester.class);

        // autumn.util.functors
        importClass(Predicate.class);
        importClass(MethodCompareTo.class);
        importClass(MethodEquals.class);
        importClass(MethodHashCode.class);
        importClass(MethodIterator.class);
        importClass(MethodToString.class);


        // java.lang
        importClass(java.lang.Boolean.class);
        importClass(java.lang.Byte.class);
        importClass(java.lang.Character.class);
        importClass(java.lang.CharSequence.class);
        importClass(java.lang.CharSequence.class);
        importClass(java.lang.Class.class);
        importClass(java.lang.ClassCastException.class);
        importClass(java.lang.Double.class);
        importClass(java.lang.Enum.class);
        importClass(java.lang.Exception.class);
        importClass(java.lang.Float.class);
        importClass(java.lang.IllegalArgumentException.class);
        importClass(java.lang.IllegalStateException.class);
        importClass(java.lang.IndexOutOfBoundsException.class);
        importClass(java.lang.Integer.class);
        importClass(java.lang.Iterable.class);
        importClass(java.lang.Long.class);
        importClass(java.lang.NullPointerException.class);
        importClass(java.lang.Number.class);
        importClass(java.lang.Object.class);
        importClass(java.lang.RuntimeException.class);
        importClass(java.lang.Short.class);
        importClass(java.lang.String.class);
        importClass(java.lang.StringBuilder.class);
        importClass(java.lang.System.class);
        importClass(java.lang.Throwable.class);
        importClass(java.lang.UnsupportedOperationException.class);

        // java.math
        importClass(java.math.BigInteger.class);
        importClass(java.math.BigDecimal.class);

        // java.util
        importClass(java.util.ArrayList.class);
        importClass(java.util.Collection.class);
        importClass(java.util.HashMap.class);
        importClass(java.util.HashSet.class);
        importClass(java.util.Iterator.class);
        importClass(java.util.LinkedList.class);
        importClass(java.util.List.class);
        importClass(java.util.Map.class);
        importClass(java.util.NoSuchElementException.class);
        importClass(java.util.Set.class);
        importClass(java.util.TreeMap.class);
        importClass(java.util.TreeSet.class);
        importClass(java.util.Random.class);
    }

    private void checkAccess(final TypeSpecifier specifier,
                             final IType type)
    {
        if (type instanceof IDeclaredType == false)
        {
            return;
        }

        final IDeclaredType used = (IDeclaredType) type;

        final IDeclaredType user = module.type;

        final boolean accessible = module.program.typesystem.utils.isAccessible(user, used);

        if (!accessible)
        {
            module.program.checker.reportInaccessibleType(specifier, used);
        }
    }

    public Set<String> imported()
    {
        return Sets.newTreeSet(imports.values());
    }

    private String dealisTypeName(final String typename)
    {
        final String deimported = imports.containsKey(typename) ? imports.get(typename) : typename;

        final String dealiased = deimported.contains(".")
                ? deimported
                : module.type.getNamespace() + '.' + typename;

        return dealiased;
    }

    public IReturnType resolveReturnType(final TypeSpecifier specifier)
    {
        final String alias = module.program.typesystem.utils.extractTypeName(specifier);

        final Integer dimensions = specifier.getDimensions();

        final IExpressionType result;

        if (Utils.isKeyword(alias))
        {
            // Special Case: The specifier specifies a primitive-type or the void-type.
            return module.program.typesystem.utils.findType(alias, dimensions);
        }
        else
        {
            final String typename = dealisTypeName(alias);

            result = module.program.typesystem.utils.findType(typename, dimensions);
        }

        module.program.checker.requireType(specifier, result);

        module.program.checker.requireReturnType(specifier, result);

        checkAccess(specifier, result);

        return (IReturnType) result;
    }

    public IVariableType resolveVariableType(final TypeSpecifier specifier)
    {
        final IReturnType type = resolveReturnType(specifier);

        module.program.checker.requireVariableType(specifier, type);

        return (IVariableType) type;
    }

    public IClassType resolveModuleType(final TypeSpecifier specifier)
    {
        final IReturnType type = resolveReturnType(specifier);

        module.program.checker.requireModule(specifier, type);

        return (IClassType) type;
    }

    public IClassType resolveFunctorType(final TypeSpecifier specifier)
    {
        final IReturnType type = resolveReturnType(specifier);

        module.program.checker.requireDefinedFunctor(specifier, type);

        return (IClassType) type;
    }

    public IInterfaceType resolveInterfaceType(final TypeSpecifier specifier)
    {
        final IReturnType type = resolveReturnType(specifier);

        module.program.checker.requireInterfaceType(specifier, type);

        return (IInterfaceType) type;
    }

    public IInterfaceType resolveDesignType(final TypeSpecifier specifier)
    {
        final IReturnType type = resolveReturnType(specifier);

        module.program.checker.requireDesignType(specifier, type);

        return (IInterfaceType) type;
    }

    public IReferenceType resolveReferenceType(final TypeSpecifier specifier)
    {
        final IReturnType type = resolveReturnType(specifier);

        module.program.checker.requireReferenceType(specifier, type);

        return (IReferenceType) type;
    }

    public IClassType resolveClassType(final TypeSpecifier specifier)
    {
        final IReturnType type = resolveReturnType(specifier);

        module.program.checker.requireClassType(specifier, type);

        return (IClassType) type;
    }

    public IDeclaredType resolveDeclaredType(final TypeSpecifier specifier)
    {
        final IReturnType type = resolveReturnType(specifier);

        module.program.checker.requireDeclaredType(specifier, type);

        return (IDeclaredType) type;
    }

    public void importType(final String alias,
                           final String type)
    {
        Preconditions.checkNotNull(alias);
        Preconditions.checkNotNull(type);

        imports.put(alias, type);
    }

    public void importClass(final Class type)
    {
        imports.put(type.getSimpleName(), type.getName());
    }
}
