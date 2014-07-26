package high.mackenzie.autumn.lang.compiler.optimizer;

import com.google.common.collect.Maps;
import java.util.Map;
import org.objectweb.asm.tree.InsnList;

/**
 * An instance of this class can replace invocations of certain special methods with faster code.
 *
 * @author Mackenzie High
 */
public final class SpecialMethodInliner
{
    private final Map<String, InsnList> optim = Maps.newTreeMap();
}
