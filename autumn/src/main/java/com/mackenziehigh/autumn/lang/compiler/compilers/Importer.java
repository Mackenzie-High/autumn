package com.mackenziehigh.autumn.lang.compiler.compilers;

import autumn.lang.compiler.ast.nodes.TypeSpecifier;
import autumn.util.F;
import autumn.util.functors.Action;
import autumn.util.functors.Function0;
import autumn.util.functors.Function1;
import autumn.util.functors.Function2;
import autumn.util.functors.Function3;
import autumn.util.functors.Function4;
import autumn.util.functors.Function5;
import autumn.util.functors.Function6;
import autumn.util.functors.Function7;
import autumn.util.functors.Function8;
import autumn.util.functors.Function9;
import autumn.util.functors.Ordering;
import autumn.util.functors.Predicate;
import autumn.util.functors.ProxyHandler;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IClassType;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IDeclaredType;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IExpressionType;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IInterfaceType;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IReferenceType;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IReturnType;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IType;
import com.mackenziehigh.autumn.lang.compiler.typesystem.design.IVariableType;
import com.mackenziehigh.autumn.lang.compiler.utils.Utils;
import com.mackenziehigh.autumn.resources.Finished;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Essentially, an instance of this class implements import-directives.
 *
 * @author Mackenzie High
 */
@Finished("2015/01/17")
public final class Importer
{
    /**
     * Essentially, this is the module that contains the import-directives implemented herein.
     */
    private final ModuleCompiler module;

    /**
     * This map maps type aliases to the fully-qualified names of types.
     *
     * <p>
     * Example: String => java.lang.String
     * </p>
     */
    private final Map<String, String> imports = Maps.newTreeMap();

    /**
     * Sole Constructor.
     *
     * @param module is essentially the module that is being compiled.
     * @throws NullPointerException if module is null.
     */
    public Importer(final ModuleCompiler module)
    {
        this.module = module;

        // autumn.lang
        importClass(autumn.lang.Delegate.class);
        importClass(autumn.lang.Functor.class);
        importClass(autumn.lang.Lambda.class);
        importClass(autumn.lang.Local.class);
        importClass(autumn.lang.LocalsMap.class);
        importClass(autumn.lang.Module.class);
        importClass(autumn.lang.ModuleInfo.class);
        importClass(autumn.lang.Record.class);
        importClass(autumn.lang.DefinedFunctor.class);
        importClass(autumn.lang.TypedFunctor.class);

        // autumn.lang.annotations
        importClass(autumn.lang.annotations.Author.class);
        importClass(autumn.lang.annotations.Hide.class);
        importClass(autumn.lang.annotations.Infer.class);
        importClass(autumn.lang.annotations.Setup.class);
        importClass(autumn.lang.annotations.Start.class);
        importClass(autumn.lang.annotations.Sync.class);

        // autumn.lang.compiler
        importClass(autumn.lang.compiler.Autumn.class);

        // autumn.lang.exceptions
        importClass(autumn.lang.exceptions.AssertionFailedException.class);
        importClass(autumn.lang.exceptions.AssumptionFailedException.class);
        importClass(autumn.lang.exceptions.CheckedException.class);
        importClass(autumn.lang.exceptions.DispatchException.class);
        importClass(autumn.lang.exceptions.UnexpectedTerminationException.class);

        // autumn.util
        importClass(autumn.util.Bitwise.class);
        importClass(autumn.util.F.class);
        importClass(autumn.util.FileIO.class);

        // autumn.util.test
        importClass(autumn.util.test.MalformedTestException.class);
        importClass(autumn.util.test.Test.class);
        importClass(autumn.util.test.TestCase.class);
        importClass(autumn.util.test.TestResult.class);
        importClass(autumn.util.test.TestResults.class);
        importClass(autumn.util.test.Tester.class);
        importClass(autumn.util.test.UnitTester.class);

        // autumn.util.functors
        importClass(Action.class);
        importClass(Function0.class);
        importClass(Function1.class);
        importClass(Function2.class);
        importClass(Function3.class);
        importClass(Function4.class);
        importClass(Function5.class);
        importClass(Function6.class);
        importClass(Function7.class);
        importClass(Function8.class);
        importClass(Function9.class);
        importClass(Ordering.class);
        importClass(Predicate.class);
        importClass(ProxyHandler.class);

        // java.lang.annotation
        importClass(java.lang.annotation.Annotation.class);

        // java.lang
        importClass(java.lang.ArithmeticException.class);
        importClass(java.lang.Boolean.class);
        importClass(java.lang.Byte.class);
        importClass(java.lang.Character.class);
        importClass(java.lang.CharSequence.class);
        importClass(java.lang.Class.class);
        importClass(java.lang.ClassCastException.class);
        importClass(java.lang.Comparable.class);
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
        importClass(java.lang.Math.class);
        importClass(java.lang.NegativeArraySizeException.class);
        importClass(java.lang.NullPointerException.class);
        importClass(java.lang.Number.class);
        importClass(java.lang.NumberFormatException.class);
        importClass(java.lang.Object.class);
        importClass(java.lang.RuntimeException.class);
        importClass(java.lang.Short.class);
        importClass(java.lang.String.class);
        importClass(java.lang.StringBuilder.class);
        importClass(java.lang.System.class);
        importClass(java.lang.Throwable.class);
        importClass(java.lang.UnsupportedOperationException.class);

        // java.math
        importClass(java.math.BigDecimal.class);
        importClass(java.math.BigInteger.class);

        // java.util
        importClass(java.util.ArrayList.class);
        importClass(java.util.Collection.class);
        importClass(java.util.Collections.class);
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

        /**
         * When Autumn is used as a plugin, the user may specify classes to import into every module.
         */
        if (module != null)
        {
            for (Class type : module.program.imported)
            {
                importClass(type);
            }
        }
    }

    /**
     * This method ensures that a type is actually accessible from where it is used.
     *
     * @param specifier is the Abstract-Syntax-Tree node that represents the usage of the type.
     * @param type is the type-system representation of the specified type.
     * @throws TypeCheckFailed if the specified type is not accessible.
     * @throws NullPointerException if specifier is null.
     * @throws NullPointerException if type is null.
     */
    private void checkAccess(final TypeSpecifier specifier,
                             final IType type)
    {
        Preconditions.checkNotNull(specifier);
        Preconditions.checkNotNull(type);

        /**
         * Only declared-types can have restricted access due to access modifiers.
         */
        if (type instanceof IDeclaredType == false)
        {
            return;
        }

        /**
         * This is the type that may be restricted.
         */
        final IDeclaredType used = (IDeclaredType) type;

        /**
         * This is the type of the module that contains the code
         * that is attempting to use the potentially restricted type.
         */
        final IDeclaredType user = module.type;

        /**
         * This is true, if access to the type is *not* restricted.
         */
        final boolean accessible = module.program.typesystem.utils.isAccessible(user, used);

        /**
         * If access to the type is restricted, then issue a compile-time warning.
         */
        if (!accessible)
        {
            /**
             * This invocation always throws an exception.
             */
            module.program.checker.reportInaccessibleType(specifier, used);
        }
    }

    /**
     * This method creates a set containing the fully-qualified names of the imported types.
     *
     * @return the aforedescribed immutable set.
     */
    public Set<String> imported()
    {
        return F.unmodifiable(new TreeSet(imports.values()));
    }

    /**
     * This method converts a type-alias to a fully-qualified type-name.
     *
     * <p>
     * The alias may refer to a type that was imported.
     * Otherwise, the alias must refer to a type declared in the enclosing module.
     * So, create a string that hopefully is the fully-qualified name of the type.
     * However, if the type does not really exist, then the result is really meaningless.
     * </p>
     *
     * @param alias is the type-alias.
     * @return the fully-qualified name of the aliased type.
     */
    private String dealisTypeName(final String alias)
    {
        final String full_name = imports.containsKey(alias) ? imports.get(alias) : alias;

        final String dealiased = full_name.contains(".")
                ? full_name
                : module.type.getNamespace() + '.' + alias;

        return dealiased;
    }

    /**
     * This method retrieves the type that is specified by a type-specifier.
     *
     * <p>
     * This method causes a compiler-warning to be issued,
     * if the specified type is either non existent or inaccessible.
     * </p>
     *
     * <p>
     * This method causes a compiler-warning to be issued,
     * if the specified type is not a return-type.
     * </p>
     *
     * @param specifier specifies the type to retrieve.
     * @return the specified type, if it exists and is accessible.
     * @throws NullPointerException if the specifier is null.
     * @throws TypeCheckFailed if the specified type is either non existent or inaccessible.
     * @throws TypeCheckFailed if the specified type is not a return-type.
     */
    public IReturnType resolveReturnType(final TypeSpecifier specifier)
    {
        /**
         * Get the name of the type, as it appear in the specifier.
         */
        final String alias = module.program.typesystem.utils.extractTypeName(specifier);

        /**
         * This is the number of array dimensions in the specifier.
         * If the specifier does not specify an array, then this variable will be null.
         */
        final Integer dimensions = specifier.getDimensions();

        final IExpressionType result;

        if (Utils.isKeyword(alias))
        {
            /**
             * Special Case: The specifier specifies a primitive-type or the void-type.
             */
            return module.program.typesystem.utils.findType(alias, dimensions);
        }
        else
        {
            final String typename = dealisTypeName(alias);

            result = module.program.typesystem.utils.findType(typename, dimensions);
        }

        /**
         * Issue a compiler-warning, if the type does not actually exist.
         */
        module.program.checker.requireType(specifier, result);

        /**
         * Issue a compiler warning, if the type is cannot be a return-type.
         */
        module.program.checker.requireReturnType(specifier, result);

        /**
         * Issue a compiler warning, if the type is not accessible.
         */
        checkAccess(specifier, result);

        /**
         * This checked-cast will always succeed.
         */
        return (IReturnType) result;
    }

    /**
     * This method retrieves the type that is specified by a type-specifier.
     *
     * <p>
     * This method causes a compiler-warning to be issued,
     * if the specified type is either non existent or inaccessible.
     * </p>
     *
     * <p>
     * This method causes a compiler-warning to be issued,
     * if the specified type is not a variable-type.
     * </p>
     *
     * @param specifier specifies the type to retrieve.
     * @return the specified type, if it exists and is accessible.
     * @throws NullPointerException if the specifier is null.
     * @throws TypeCheckFailed if the specified type is either non existent or inaccessible.
     * @throws TypeCheckFailed if the specified type is not a variable-type.
     */
    public IVariableType resolveVariableType(final TypeSpecifier specifier)
    {
        final IReturnType type = resolveReturnType(specifier);

        /**
         * Issue a compiler warning, if the type cannot be the type of a variable.
         */
        module.program.checker.requireVariableType(specifier, type);

        /**
         * This checked-cast will always succeed.
         */
        return (IVariableType) type;
    }

    /**
     * This method retrieves the type that is specified by a type-specifier.
     *
     * <p>
     * This method causes a compiler-warning to be issued,
     * if the specified type is either non existent or inaccessible.
     * </p>
     *
     * <p>
     * This method causes a compiler-warning to be issued,
     * if the specified type is not a module-type.
     * </p>
     *
     * @param specifier specifies the type to retrieve.
     * @return the specified type, if it exists and is accessible.
     * @throws NullPointerException if the specifier is null.
     * @throws TypeCheckFailed if the specified type is either non existent or inaccessible.
     * @throws TypeCheckFailed if the specified type is not a module-type.
     */
    public IClassType resolveModuleType(final TypeSpecifier specifier)
    {
        final IReturnType type = resolveReturnType(specifier);

        /**
         * Issue a compiler warning, if the type cannot be the type of a module.
         */
        module.program.checker.requireModule(specifier, type);

        /**
         * This checked-cast will always succeed.
         */
        return (IClassType) type;
    }

    /**
     * This method retrieves the type that is specified by a type-specifier.
     *
     * <p>
     * This method causes a compiler-warning to be issued,
     * if the specified type is either non existent or inaccessible.
     * </p>
     *
     * <p>
     * This method causes a compiler-warning to be issued,
     * if the specified type is not a functor-type.
     * </p>
     *
     * @param specifier specifies the type to retrieve.
     * @return the specified type, if it exists and is accessible.
     * @throws NullPointerException if the specifier is null.
     * @throws TypeCheckFailed if the specified type is either non existent or inaccessible.
     * @throws TypeCheckFailed if the specified type is not a functor-type.
     */
    public IClassType resolveDefinedFunctorType(final TypeSpecifier specifier)
    {
        final IReturnType type = resolveReturnType(specifier);

        /**
         * Issue a compiler warning, if the type cannot be the type of a defined-functor.
         */
        module.program.checker.requireDefinedFunctorType(specifier, type);

        /**
         * Issue a compiler warning, if the type cannot be a class-type.
         */
        module.program.checker.requireClassType(specifier, type);

        /**
         * This checked-cast will always succeed.
         */
        return (IClassType) type;
    }

    /**
     * This method retrieves the type that is specified by a type-specifier.
     *
     * <p>
     * This method causes a compiler-warning to be issued,
     * if the specified type is either non existent or inaccessible.
     * </p>
     *
     * <p>
     * This method causes a compiler-warning to be issued,
     * if the specified type is not an interface-type.
     * </p>
     *
     * @param specifier specifies the type to retrieve.
     * @return the specified type, if it exists and is accessible.
     * @throws NullPointerException if the specifier is null.
     * @throws TypeCheckFailed if the specified type is either non existent or inaccessible.
     * @throws TypeCheckFailed if the specified type is not an interface-type.
     */
    public IInterfaceType resolveInterfaceType(final TypeSpecifier specifier)
    {
        final IReturnType type = resolveReturnType(specifier);

        /**
         * Issue a compiler warning, if the type cannot be a interface-type.
         */
        module.program.checker.requireInterfaceType(specifier, type);

        /**
         * This checked-cast will always succeed.
         */
        return (IInterfaceType) type;
    }

    /**
     * This method retrieves the type that is specified by a type-specifier.
     *
     * <p>
     * This method causes a compiler-warning to be issued,
     * if the specified type is either non existent or inaccessible.
     * </p>
     *
     * <p>
     * This method causes a compiler-warning to be issued,
     * if the specified type is not a design-type.
     * </p>
     *
     * @param specifier specifies the type to retrieve.
     * @return the specified type, if it exists and is accessible.
     * @throws NullPointerException if the specifier is null.
     * @throws TypeCheckFailed if the specified type is either non existent or inaccessible.
     * @throws TypeCheckFailed if the specified type is not a design-type.
     */
    public IInterfaceType resolveDesignType(final TypeSpecifier specifier)
    {
        final IReturnType type = resolveReturnType(specifier);

        /**
         * Issue a compiler warning, if the type cannot be the type of a design.
         */
        module.program.checker.requireDesignType(specifier, type);

        /**
         * This checked-cast will always succeed.
         */
        return (IInterfaceType) type;
    }

    /**
     * This method retrieves the type that is specified by a type-specifier.
     *
     * <p>
     * This method causes a compiler-warning to be issued,
     * if the specified type is either non existent or inaccessible.
     * </p>
     *
     * <p>
     * This method causes a compiler-warning to be issued,
     * if the specified type is not a reference-type.
     * </p>
     *
     * @param specifier specifies the type to retrieve.
     * @return the specified type, if it exists and is accessible.
     * @throws NullPointerException if the specifier is null.
     * @throws TypeCheckFailed if the specified type is either non existent or inaccessible.
     * @throws TypeCheckFailed if the specified type is not a reference-type.
     */
    public IReferenceType resolveReferenceType(final TypeSpecifier specifier)
    {
        final IReturnType type = resolveReturnType(specifier);

        /**
         * Issue a compiler warning, if the type cannot be a reference-type.
         */
        module.program.checker.requireReferenceType(specifier, type);

        /**
         * This checked-cast will always succeed.
         */
        return (IReferenceType) type;
    }

    /**
     * This method retrieves the type that is specified by a type-specifier.
     *
     * <p>
     * This method causes a compiler-warning to be issued,
     * if the specified type is either non existent or inaccessible.
     * </p>
     *
     * @param specifier specifies the type to retrieve.
     * @return the specified type, if it exists and is accessible.
     * @throws NullPointerException if the specifier is null.
     * @throws TypeCheckFailed if the specified type is either non existent or inaccessible.
     * @throws TypeCheckFailed if the specified type is not a class-type.
     */
    public IClassType resolveClassType(final TypeSpecifier specifier)
    {
        final IReturnType type = resolveReturnType(specifier);

        /**
         * Issue a compiler warning, if the type cannot be a class-type.
         */
        module.program.checker.requireClassType(specifier, type);

        /**
         * This checked-cast will always succeed.
         */
        return (IClassType) type;
    }

    /**
     * This method retrieves the type that is specified by a type-specifier.
     *
     * <p>
     * This method causes a compiler-warning to be issued,
     * if the specified type is either non existent or inaccessible.
     * </p>
     *
     * <p>
     * This method causes a compiler-warning to be issued,
     * if the specified type is not a declared-type.
     * </p>
     *
     * @param specifier specifies the type to retrieve.
     * @return the specified type, if it exists and is accessible.
     * @throws NullPointerException if the specifier is null.
     * @throws TypeCheckFailed if the specified type is either non existent or inaccessible.
     * @throws TypeCheckFailed if the specified type is not a declared-type.
     */
    public IDeclaredType resolveDeclaredType(final TypeSpecifier specifier)
    {
        final IReturnType type = resolveReturnType(specifier);

        /**
         * Issue a compiler warning, if the type cannot be a class-type.
         */
        module.program.checker.requireDeclaredType(specifier, type);

        /**
         * This checked-cast will always succeed.
         */
        return (IDeclaredType) type;
    }

    /**
     * This method creates a type-alias.
     *
     * @param alias is the alias to use for the type.
     * @param type is the fully-qualified name of the type.
     * @throws NullPointerException if alias is null.
     * @throws NullPointerException if type is null.
     */
    public void importType(final String alias,
                           final String type)
    {
        Preconditions.checkNotNull(alias);
        Preconditions.checkNotNull(type);

        imports.put(alias, type);
    }

    /**
     * This method creates a type-alias.
     *
     * @param type is the type to import.
     * @throws NullPointerException if type is null.
     */
    public void importClass(final Class type)
    {
        Preconditions.checkNotNull(type);

        imports.put(type.getSimpleName(), type.getName());
    }
}
