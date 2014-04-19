package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.Delegate;
import autumn.lang.F;
import autumn.lang.Functor;
import autumn.lang.compiler.ClassFile;
import autumn.lang.compiler.ast.nodes.AnnotationDefinition;
import autumn.lang.compiler.ast.nodes.EnumDefinition;
import autumn.lang.compiler.ast.nodes.ExceptionDefinition;
import autumn.lang.compiler.ast.nodes.FunctionDefinition;
import autumn.lang.compiler.ast.nodes.ImportDirective;
import autumn.lang.compiler.ast.nodes.Module;
import autumn.lang.compiler.ast.nodes.ModuleDirective;
import autumn.lang.compiler.ast.nodes.Name;
import autumn.lang.compiler.ast.nodes.StructDefinition;
import autumn.lang.compiler.ast.nodes.TypeSpecifier;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import high.mackenzie.autumn.lang.compiler.typesystem.CustomDeclaredType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IInterfaceType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IReturnType;
import high.mackenzie.autumn.lang.compiler.utils.AnnotationUtils;
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
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;

/**
 * An instance of this class controls the compilation of an entire module.
 *
 * @author Mackenzie High
 */
public final class ModuleCompiler
        implements ICompiler
{
    public final ProgramCompiler program;

    public final Module node;

    public CustomDeclaredType type;

    public AnnotationUtils anno_utils;

    private final Map<String, String> imports = Maps.newTreeMap();


    {
        imports.put("Start", "autumn.lang.annotations.Start");
        imports.put("Significant", "autumn.lang.annotations.Significant");
        imports.put("Unique", "autumn.lang.annotations.Unique");

        importClass(F.class);
        importClass(Functor.class);
        importClass(Delegate.class);


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
        importClass(String.class);
        importClass(StringBuilder.class);
        importClass(BigInteger.class);
        importClass(BigDecimal.class);

        importClass(Class.class);

        importClass(Throwable.class);
        importClass(Exception.class);
        importClass(RuntimeException.class);
        importClass(IllegalArgumentException.class);
        importClass(IllegalStateException.class);

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
    }

    public final List<AnnotationCompiler> annotations = Lists.newLinkedList();

    public final List<ExceptionCompiler> exceptions = Lists.newLinkedList();

    public final List<EnumCompiler> enums = Lists.newLinkedList();

//    public final List<DesignCompiler> designs = Lists.newLinkedList();
    public final List<FunctionCompiler> functions = Lists.newLinkedList();

    public final List<FieldNode> yields = Lists.newLinkedList();

    public ModuleCompiler(final ProgramCompiler program,
                          final Module node)
    {
        this.program = program;
        this.node = node;

        for (AnnotationDefinition x : node.getAnnotations())
        {
            annotations.add(new AnnotationCompiler(this, x));
        }

        for (ExceptionDefinition x : node.getExceptions())
        {
            exceptions.add(new ExceptionCompiler(this, x));
        }

        for (EnumDefinition x : node.getEnums())
        {
            enums.add(new EnumCompiler(this, x));
        }

        for (StructDefinition x : node.getStructs())
        {
//            designs.add(new DesignCompiler(this, x));
        }

        for (FunctionDefinition x : node.getFunctions())
        {
            functions.add(new FunctionCompiler(this, x));
        }

        this.anno_utils = new AnnotationUtils(this);
    }

    public Set<ClassFile> build()
    {
        final Set<ClassFile> classes = Sets.newHashSet();

        for (AnnotationCompiler x : annotations)
        {
            classes.add(x.build());
        }

        for (ExceptionCompiler x : exceptions)
        {
            classes.add(x.build());
        }

        for (EnumCompiler x : enums)
        {
            classes.add(x.build());
        }

//        for (DesignCompiler x : designs)
//        {
//            classes.add(x.build());
//        }

        classes.add(buildModule());

        return classes;
    }

    private ClassFile buildModule()
    {
        final List<MethodNode> methods = Lists.newLinkedList();

        methods.add(buildClinit());

        for (FunctionCompiler x : functions)
        {
            methods.add(x.method);
        }

        final String module_internal_name = Utils.internalName(type);

        final String module_source_name = Utils.sourceName(type);

        final ClassNode clazz = new ClassNode();
        {
            clazz.version = Opcodes.V1_6;
            clazz.visibleAnnotations = ImmutableList.of();
            clazz.access = type.getModifiers();
            clazz.name = module_internal_name;
            clazz.superName = Utils.internalName(type.getSuperclass());
            clazz.interfaces = ImmutableList.of();
            clazz.fields = yields;
            clazz.methods = ImmutableList.copyOf(methods);
            clazz.sourceFile = String.valueOf(node.getLocation().getFile());

            assert clazz.superName.equals("java/lang/Object");
            assert clazz.access == Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL;
        }

        final ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);

        clazz.accept(writer);

        final byte[] bytecode = writer.toByteArray();

        final ClassFile file = new ClassFile(module_source_name, bytecode);

        return file;
    }

    private MethodNode buildClinit()
    {
        final MethodNode m = new MethodNode();

        m.access = Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC + Opcodes.ACC_FINAL;
        m.name = "<clinit>";
        m.desc = "()V";
        m.exceptions = ImmutableList.of();

        String owner;
        String name;
        String desc;

        for (FieldNode field : yields)
        {
            // Create a YieldState object.
            owner = Utils.internalName(program.typesystem.utils.YIELD_STATE);
            m.instructions.add(new TypeInsnNode(Opcodes.NEW, owner));
            m.instructions.add(new InsnNode(Opcodes.DUP));
            name = "<init>";
            desc = "()V";
            m.instructions.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, owner, name, desc));

            // Store the YieldState object in the field.
            owner = Utils.internalName(type);
            name = field.name;
            desc = field.desc;
            m.instructions.add(new FieldInsnNode(Opcodes.PUTSTATIC, owner, name, desc));
        }

        // Exit the static constructor.
        m.instructions.add(new InsnNode(Opcodes.RETURN));

        return m;
    }

    private String dealisTypeName(final String typename)
    {
        final String deimported = imports.containsKey(typename) ? imports.get(typename) : typename;

        final String dealiased = deimported.contains(".")
                ? deimported
                : type.getNamespace() + '.' + typename;

        return dealiased;
    }

    public IReturnType resolveType(final TypeSpecifier specifier)
    {
        final String alias = program.typesystem.utils.extractTypeName(specifier);

        if (Utils.isKeyword(alias))
        {
            // Special Case: The specifier specifies a primitive-type or the void-type.
            return program.typesystem.utils.findType(alias, null);
        }

        final String typename = dealisTypeName(alias);

        final Integer dimensions = specifier.getDimensions();

        final IReturnType result = program.typesystem.utils.findType(typename, dimensions);

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeDeclaration()
    {
        if (node.getModuleDirectives().size() < 1)
        {
            // Error - missing module directive
            // fail fast
        }

        if (node.getModuleDirectives().size() > 1)
        {
            // Error - too many directives
            // fail fast
        }

        final ModuleDirective directive = node.getModuleDirectives().asMutableList().get(0);

        final String module_descriptor = processModuleDirective(directive);

        this.type = program.typesystem.getTypeFactory().newClassType(module_descriptor);

        for (ICompiler x : compilers())
        {
            x.performTypeDeclaration();
        }

        loadImports();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeInitialization()
    {
        this.type.setModifiers(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL);
        this.type.setSuperclass(program.typesystem.utils.OBJECT);
        this.type.setSuperinterfaces(ImmutableList.<IInterfaceType>of());

        for (ICompiler x : compilers())
        {
            x.performTypeInitialization();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeStructureChecking()
    {
        // TODO:
        // . No duplicate methods.
        // . No methods that hide/override a method from OBJECT.
        // . Chack annotation-list on the module directive.
        // . The name of the module cannot be a forbidden name.
        //

        for (ICompiler x : compilers())
        {
            x.performTypeStructureChecking();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performTypeUsageChecking()
    {
        for (ICompiler x : compilers())
        {
            x.performTypeUsageChecking();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performCodeGeneration()
    {
        for (ICompiler x : compilers())
        {
            x.performCodeGeneration();
        }
    }

    /**
     * This method executes all the import-directives and imports the types defined in this module.
     */
    private void loadImports()
    {
        /**
         * Import the module itself.
         */
        imports.put(node.getModuleDirectives().asMutableList().get(0).getName().getName(),
                    Utils.internalName(type).replace('/', '.'));

        /**
         * Execute all the import directives.
         */
        for (ImportDirective import_directive : node.getImportDirectives())
        {
            final TypeSpecifier imported_type = import_directive.getType();

            final String typename = program.typesystem.utils.extractTypeName(imported_type);

            final String simple_name = imported_type.getName().getName();

            if (imported_type.getDimensions() != null)
            {
                // TODO: error
            }

            imports.put(simple_name, typename);
        }

        /**
         * Import all the enums defined in this module.
         */
        for (EnumCompiler cmp : enums)
        {
            final String simple_name = cmp.node.getName().getName();

            final String typename = Utils.internalName(cmp.type).replace('/', '.');

            imports.put(simple_name, typename);
        }
    }

    private String processModuleDirective(final ModuleDirective directive)
    {
        // TODO: Handle annotations on the directive

        final StringBuilder module_package = new StringBuilder();

        for (Name n : directive.getNamespace().getNames())
        {
            module_package.append(n.getName());
            module_package.append('/');
        }

        final String module_name = directive.getName() == null
                ? createNameForAnonymousModule()
                : directive.getName().getName();

        final String module_descriptor = "L" + module_package + module_name + ";";

        return module_descriptor;
    }

    private String createNameForAnonymousModule()
    {
        return "TODO";
    }

    /**
     * This method avoids the need for multiple for-loops elsewhere.
     *
     * @return the compilers used by this compiler.
     */
    private Set<ICompiler> compilers()
    {
        final Set<ICompiler> result = Sets.newHashSet();

        result.addAll(annotations);
        result.addAll(exceptions);
        result.addAll(enums);
//        result.addAll(designs);
        result.addAll(functions);

        return result;
    }

    private void importClass(final Class type)
    {
        imports.put(type.getSimpleName(), type.getName());
    }
}
