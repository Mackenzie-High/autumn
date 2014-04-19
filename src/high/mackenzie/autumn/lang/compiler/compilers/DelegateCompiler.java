/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.compiler.ClassFile;
import autumn.lang.compiler.ast.nodes.DelegateExpression;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import high.mackenzie.autumn.lang.compiler.utils.Utils;
import java.util.List;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

/**
 * An instance of this interface handles the compilation of a delegate.
 *
 * @author Mackenzie
 */
public final class DelegateCompiler
{
    private static int counter = 0;

    public final ProgramCompiler program;

    public final ModuleCompiler module;

    public final DelegateExpression node;

    private final int number;

    public DelegateCompiler(final ModuleCompiler module,
                            final DelegateExpression node)
    {
        this.program = module.program;
        this.module = module;
        this.node = node;
        this.number = counter++;
    }

    public String simpleName()
    {
        return "autumn$delegate$" + Strings.padStart(number + "", 5, '0');
    }

    public String namespace()
    {
        return module.type.getNamespace();
    }

    public String internalName()
    {
        return namespace().replace(".", "/") + simpleName();
    }

    public String descriptor()
    {
        return "L" + internalName() + ";";
    }

    public void load(final List<AbstractInsnNode> code)
    {
    }

    public ClassFile build()
    {
        final String source_name = internalName().replace("/", ".");

        /**
         * Create the bytecode representation of the enum itself.
         */
        final ClassNode clazz = new ClassNode();
        {
            clazz.version = Opcodes.V1_6;
            clazz.visibleAnnotations = Lists.newLinkedList();
            clazz.access = Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_SYNTHETIC;
            clazz.name = internalName();
            clazz.superName = Utils.internalName(program.typesystem.utils.DELEGATE);
            clazz.interfaces = Lists.newLinkedList();
            clazz.fields = ImmutableList.of();
            clazz.methods = Lists.newLinkedList();
            clazz.sourceFile = String.valueOf(node.getLocation().getFile());

            // Add some special methods to the enum.

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
        final ClassFile file = new ClassFile(source_name, bytecode);

        return file;
    }

    private void addMethodExecute(final ClassNode clazz)
    {
        final MethodNode m = new MethodNode();
        m.access = Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL;
        m.name = "execute";
        m.desc = "(Lautumn/lang/internals/ArgumentStack;)Ljava/lang/Object;";
        m.exceptions = ImmutableList.of("java/lang/Throwable");


    }
}
