/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.F;
import com.google.common.base.Preconditions;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVariableType;
import high.mackenzie.autumn.lang.compiler.utils.Utils;
import java.util.List;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

/**
 * An instance of this class is used to generate bytecode that manipulates local-variables.
 *
 * @author Mackenzie High
 */
public class VariableManipulator
{
    private final VariableScope scope;

    private final List<AbstractInsnNode> code;

    public VariableManipulator(final VariableScope scope,
                               final List<AbstractInsnNode> code)
    {
        Preconditions.checkNotNull(scope);

        this.scope = scope;
        this.code = code;
    }

    public VariableScope scope()
    {
        return scope;
    }

    public void initScope()
    {
        for (String name : scope.getVariables())
        {
            if (scope.isParameter(name))
            {
                continue;
            }

            code.add(Utils.selectLdcDefault(scope.typeOf(name)));

            store(name, true);
        }
    }

    public void load(final String name)
    {
        final boolean closure_var = scope.isClosureVariable(name);

        final IVariableType type = scope.typeOf(name);

        if (closure_var)
        {
            F.printAll(scope.description());
            System.exit(1);
            loadClosureVar(name);
        }
        else
        {
            final int address = scope.addressOf(name);

            loadStackVar(address, type);
        }
    }

    private void loadClosureVar(final String name)
    {
        final String method = "$autumn$imp$getvar$" + name;

        code.add(new VarInsnNode(Opcodes.ALOAD, 0)); // sheep

        code.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "", method, ""));
    }

    private void loadStackVar(final int address,
                              final IVariableType type)
    {
        code.add(Utils.selectLoadVarInsn(type, address));
    }

    public void store(final String name,
                      final boolean force)
    {
        final boolean closure_var = scope.isClosureVariable(name);

        final IVariableType type = scope.typeOf(name);

        if (closure_var)
        {
            storeClosureVar(name);
        }
        else
        {
            final int address = scope.addressOf(name);

            storeStackVar(address, type);
        }
    }

    private void storeClosureVar(final String name)
    {
        final String method = "$autumn$imp$setvar$" + name;

        code.add(new VarInsnNode(Opcodes.ALOAD, 0)); // sheep

        code.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "", method, ""));
    }

    private void storeStackVar(final int address,
                               final IVariableType type)
    {
        code.add(Utils.selectStoreVarInsn(type, address));
    }
}
