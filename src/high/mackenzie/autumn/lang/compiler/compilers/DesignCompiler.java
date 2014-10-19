package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.compiler.ClassFile;
import autumn.lang.compiler.ast.commons.IRecord;
import autumn.lang.compiler.ast.nodes.Element;
import com.google.common.collect.Lists;
import high.mackenzie.autumn.lang.compiler.typesystem.CustomDeclaredType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.utils.Utils;
import java.util.List;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;

/**
 * An instance of this class controls the compilation of a design-definition.
 *
 * @author Mackenzie High
 */
public class DesignCompiler
        implements ICompiler
{
    /**
     * Essentially, this is the program that is being compiled.
     */
    public final ProgramCompiler program;

    /**
     * Essentially, this is the enclosing module that is also being compiled.
     */
    public final ModuleCompiler module;

    /**
     * This is the Abstract-Syntax-Tree representation of the design's definition.
     */
    public final IRecord node;

    /**
     * This will be the type-system representation of the design's definition.
     *
     * This field will be initialized during the type-declaration compiler pass.
     */
    public CustomDeclaredType type;

    /**
     * Sole Constructor.
     *
     * @param module is the module that contains the tuple being compiled.
     * @param node is the AST node that represents the tuple being compiled.
     */
    public DesignCompiler(final ModuleCompiler module,
                          final IRecord node)
    {
        assert module != null;
        assert node != null;
        assert module.tuples.contains(node);

        this.program = module.program;
        this.module = module;
        this.node = node;
    }

    @Override
    public void performTypeDeclaration()
    {
        /**
         * Determine the descriptor of the tuple.
         */
        final String namespace = module.type.getNamespace().replace('.', '/');
        final String name = node.getName().getName();
        final String descriptor = "L" + namespace + '/' + name + ';';

        /**
         * Ensure that the type was not already declared elsewhere.
         */
        program.checker.requireNonDuplicateType(node.getName(), descriptor);

        /**
         * Declare the tuple.
         */
        this.type = program.typesystem.typefactory().newClassType(descriptor);
    }

    @Override
    public void performTypeInitialization()
    {
        /**
         * Set the type's modifier flags to public and final.
         */
        type.setModifiers(Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT + Opcodes.ACC_INTERFACE);

        /**
         * Set the superclass of the tuple's type.
         */
//        type.setSuperclass(program.typesystem.utils.ABSTRACT_RECORD);
        /**
         * Add the types of the methods defined in the tuple to the tuple's type.
         */
        final List<IMethod> methods = Lists.newLinkedList();

        /**
         * Create the getter and setter methods for each element in the tuple.
         */
        for (Element entry : node.getElements().getElements())
        {
//            final String element_name = entry.getName().getName();
//
//            final IVariableType element_type = module.imports.resolveVariableType(entry.getType());
//
//            final AbstractRecordCompiler.ElementInfo element = new AbstractRecordCompiler.ElementInfo(elements.size(), element_name, element_type);
//
//            // Hold onto the type of the element for later use in other methods.
//            elements.add(element);
//
//            /**
//             * Each element has an associated getter method and a setter method.
//             */
//            methods.add(typeofSetter(element));
//            methods.add(typeofGetter(element));
        }

        /**
         * Now we have generated the types of the methods in the tuple.
         * However, we still need to add the generated method types to the tuple's type.
         */
        type.setMethods(methods);

        /**
         * Now we need to generate bridge methods.
         */
//        typeofBridgeMethods();
    }

    @Override
    public void performTypeStructureChecking()
    {
        // TODO
    }

    @Override
    public void performTypeUsageChecking()
    {
        // Pass
    }

    public ClassFile build()
    {
        final String tuple_internal_name = Utils.internalName(type);

        final String tuple_source_name = Utils.sourceName(type);

        /**
         * Create the bytecode representation of the tuple itself.
         */
        final ClassNode clazz = new ClassNode();
        {
            clazz.version = Opcodes.V1_6;
            clazz.visibleAnnotations = Lists.newLinkedList();
            clazz.access = type.getModifiers();
            clazz.name = tuple_internal_name;
            clazz.superName = Utils.internalName(type.getSuperclass());
            clazz.interfaces = Lists.newLinkedList();
            clazz.fields = Lists.newArrayList();
            clazz.methods = Lists.newLinkedList();
            clazz.sourceFile = String.valueOf(node.getLocation().getFile());

//
//            for (ElementInfo element : elements)
//            {
//                clazz.methods.add(this.generateSetter(element));
//                clazz.methods.add(this.generateGetter(element));
//            }
        }

        /**
         * Assemble the bytecode into an array of bytes.
         */
        final ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        clazz.accept(writer);
        final byte[] bytecode = writer.toByteArray();

        /**
         * Create the class-file object that will store the emitted bytecode.
         */
        final ClassFile file = new ClassFile(tuple_source_name, bytecode);

        return file;
    }
}
