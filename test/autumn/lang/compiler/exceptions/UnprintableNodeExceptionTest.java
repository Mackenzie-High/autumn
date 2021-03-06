package autumn.lang.compiler.exceptions;

import autumn.lang.compiler.ast.commons.IConstruct;
import autumn.lang.compiler.ast.nodes.Module;
import static org.junit.Assert.*;
import org.junit.Test;

public final class UnprintableNodeExceptionTest
{
    /**
     * Test: 20140111062507815886
     *
     * <p>
     * Case: Test Everything
     * </p>
     */
    @Test
    public void test20140111062507815886()
    {
        System.out.println("Test: 20140111062507815886");

        final IConstruct node = new Module();

        final UnprintableNodeException object = new UnprintableNodeException(node);

        assertTrue(node == object.node());
    }
}
