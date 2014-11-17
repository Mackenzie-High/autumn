package high.mackenzie.autumn.lang.compiler.compilers;

import com.google.common.collect.Lists;
import java.util.List;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.TryCatchBlockNode;

/**
 * This interface provides the functionality that is shared among regular functions and lambdas.
 *
 * @author Mackenzie High
 */
public abstract class AbstractFunctionCompiler
        implements ICompiler
{
    /**
     * This object represents the program that contains the function.
     *
     * More specifically, this is the compiler that is compiling the program.
     */
    public final ProgramCompiler program;

    /**
     * This object represents the module that contains the function.
     *
     * More specifically, this is the compiler that is compiling the module.
     */
    public final ModuleCompiler module;

    /**
     * These are the bytecode instructions that constitute the body of the function.
     */
    public final InsnList instructions = new InsnList();

    /**
     * These are the bytecode declarations of the try-catch blocks in the function.
     */
    public final List<TryCatchBlockNode> trycatches = Lists.newLinkedList();

    /**
     * This object manages the allocation of local variables.
     */
    public final VariableAllocator allocator;

    /**
     * This object simplifies the generation of bytecode that manipulates local variables.
     */
    public final VariableManipulator vars;

    /**
     * This object manages the allocation of user-visible labels in the function.
     */
    public final LabelScope labels;

    /**
     * Sole Constructor.
     *
     * @param module is the compiler of the module that this definition is part of.
     */
    public AbstractFunctionCompiler(final ModuleCompiler module,
                                    final VariableAllocator allocator)
    {
        this.program = module.program;
        this.module = module;

        this.allocator = allocator;
        this.vars = new VariableManipulator(allocator, this.instructions);
        this.labels = new LabelScope(program);
    }
}
