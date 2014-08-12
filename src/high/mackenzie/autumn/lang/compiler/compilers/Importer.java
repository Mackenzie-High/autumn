package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.Delegate;
import autumn.lang.Functor;
import autumn.lang.Prototype;
import autumn.lang.compiler.ast.nodes.TypeSpecifier;
import autumn.util.DS;
import autumn.util.F;
import autumn.util.Predicates;
import autumn.util.Reflect;
import autumn.util.functors.Predicate;
import autumn.util.proto.ProtoMap;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IClassType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IDeclaredType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IInterfaceType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IReturnType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVariableType;
import high.mackenzie.autumn.lang.compiler.utils.Utils;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

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

        imports.put("Start", "autumn.lang.annotations.Start");
        imports.put("Significant", "autumn.lang.annotations.Significant");
        imports.put("Unique", "autumn.lang.annotations.Unique");

        importClass(F.class);
        importClass(DS.class);
        importClass(Functor.class);
        importClass(Delegate.class);
        importClass(Prototype.class);

        importClass(Object.class);
        importClass(Number.class);
        importClass(CharSequence.class);

        importClass(Boolean.class);
        importClass(Character.class);
        importClass(Byte.class);
        importClass(Short.class);
        importClass(Integer.class);
        importClass(Long.class);
        importClass(Float.class);
        importClass(Double.class);
        importClass(BigInteger.class);
        importClass(BigDecimal.class);
        importClass(String.class);
        importClass(CharSequence.class);
        importClass(StringBuilder.class);

        importClass(Class.class);

        importClass(Enum.class);

        importClass(Throwable.class);
        importClass(Exception.class);
        importClass(RuntimeException.class);
        importClass(ClassCastException.class);
        importClass(IllegalArgumentException.class);
        importClass(IllegalStateException.class);
        importClass(IndexOutOfBoundsException.class);
        importClass(NoSuchElementException.class);
        importClass(NullPointerException.class);

        importClass(Iterable.class);
        importClass(Iterator.class);

        importClass(Collection.class);
        importClass(List.class);
        importClass(LinkedList.class);
        importClass(ArrayList.class);
        importClass(Map.class);
        importClass(HashMap.class);
        importClass(TreeMap.class);
        importClass(Set.class);
        importClass(HashSet.class);
        importClass(TreeSet.class);

        importClass(ProtoMap.class);

        importClass(Predicate.class);

        importClass(Predicates.class);
        importClass(Reflect.class);
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

        if (Utils.isKeyword(alias))
        {
            // Special Case: The specifier specifies a primitive-type or the void-type.
            return module.program.typesystem.utils.findType(alias, null);
        }

        final String typename = dealisTypeName(alias);

        final Integer dimensions = specifier.getDimensions();

        final IReturnType result = module.program.typesystem.utils.findType(typename, dimensions);

        module.program.checker.requireType(specifier, result);

        checkAccess(specifier, result);

        return result;
    }

    public IVariableType resolveVariableType(final TypeSpecifier specifier)
    {
        // TODO: error if non-vartype

        return (IVariableType) resolveReturnType(specifier);
    }

    public IClassType resolveModuleType(final TypeSpecifier specifier)
    {
        // TODO: error if non-vartype

        return (IClassType) resolveReturnType(specifier);
    }

    public IClassType resolveFunctorType(final TypeSpecifier specifier)
    {
        // TODO: error if non-vartype

        return (IClassType) resolveReturnType(specifier);
    }

    public IInterfaceType resolveInterfaceType(final TypeSpecifier specifier)
    {
        // TODO: error if non-vartype

        return (IInterfaceType) resolveReturnType(specifier);
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
