package high.mackenzie.autumn.lang.compiler.compilers;

import com.google.common.base.Preconditions;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVariableType;
import high.mackenzie.autumn.lang.compiler.utils.Utils;
import high.mackenzie.autumn.resources.Finished;
import org.objectweb.asm.tree.InsnList;

/**
 * An instance of this class is used to generate bytecode that manipulates local-variables.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
final class VariableManipulator
{
    /**
     * This object manages the allocation of local variables.
     */
    private final VariableAllocator allocator;

    /**
     * This is the list of bytecode instructions that is being generated by the compiler.
     */
    private final InsnList code;

    /**
     * Sole Constructor.
     *
     * @param allocator manages the allocation of local variables.
     * @param code is the code being generated.
     */
    public VariableManipulator(final VariableAllocator allocator,
                               final InsnList code)
    {
        Preconditions.checkNotNull(allocator);
        Preconditions.checkNotNull(code);

        this.allocator = allocator;
        this.code = code;
    }

    /**
     * This method retrieves the object that manages the allocation of local variables.
     *
     * @return the allocator of local variables.
     */
    public VariableAllocator allocator()
    {
        return allocator;
    }

    /**
     * This method generates the bytecode necessary to initialize the scope.
     */
    public void initScope()
    {
        // For each variable declared in the scope, assign it a default value.
        for (String name : allocator.getVariables())
        {
            // If the variable is a parameter, skip it.
            // Parameter's do not need a default value.
            if (allocator.isParameter(name))
            {
                continue;
            }

            // Load the default value of the variable onto the operand-stack.
            // The default value varies based on the type of the variable.
            code.add(Utils.ldcDefault(allocator.typeOf(name)));

            // Assign the default value to the variable itself.
            store(name);
        }
    }

    /**
     * This method generates bytecode that loads a variable onto the operand-stack.
     *
     * @param name is the name of the variable to load.
     */
    public void load(final String name)
    {
        Preconditions.checkNotNull(name);

        // Get the type of the variable.
        final IVariableType type = allocator.typeOf(name);

        // Get the address where the variable is stored in the stack-frame.
        final int address = allocator.addressOf(name);

        // Generate bytecode that loads the variable onto the operand-stack.
        code.add(Utils.selectLoadVarInsn(type, address));
    }

    /**
     * This method generates bytecode that assigns a value to a variable.
     *
     * @param name is the name of the variable that is being assigned a value.
     */
    public void store(final String name)
    {
        Preconditions.checkNotNull(name);

        // Get the type of the variable.
        final IVariableType type = allocator.typeOf(name);

        // Get the address where the variable is stored in the stack-frame.
        final int address = allocator.addressOf(name);

        // Generate bytecode that pops a value off of the operand-stack into a variable.
        code.add(Utils.selectStoreVarInsn(type, address));
    }
}
