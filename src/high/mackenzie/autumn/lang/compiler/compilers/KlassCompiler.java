package high.mackenzie.autumn.lang.compiler.compilers;

import autumn.lang.compiler.ast.nodes.SourceLocation;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IInterfaceType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.utils.Design;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

/**
 *
 * @author mackenzie
 */
public final class KlassCompiler
{
    private static int counter = 0;

    public final ProgramCompiler program;

    public final ModuleCompiler module;

    public final IInterfaceType type;

    public final SourceLocation location;

    private final int index = counter++;

    public final Design info;

    public KlassCompiler(final ModuleCompiler module,
                         final IInterfaceType type,
                         final SourceLocation location)
    {
        this.program = module.program;
        this.module = module;
        this.type = type;
        this.location = location;

        this.info = new Design(type);
    }

    public ClassNode build()
    {
        return null;
    }

    private MethodNode generateCtorNullary()
    {
        return null;
    }

    private MethodNode generateCtorCopy()
    {
        return null;
    }

    private MethodNode generateMethodCopy()
    {
        return null;
    }

    private MethodNode generateSetter(final IMethod setter_type)
    {
        return null;
    }

    private MethodNode generateGetter(final IMethod getter_type)
    {
        return null;
    }

    private MethodNode generateMethod(final IMethod method_type)
    {
        return null;
    }

    private MethodNode generateBridgeMethod()
    {
        return null;
    }
}
