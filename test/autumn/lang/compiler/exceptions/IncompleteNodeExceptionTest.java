package autumn.lang.compiler.exceptions;

import autumn.lang.compiler.ast.commons.IConstruct;
import autumn.lang.compiler.ast.nodes.Module;
import static org.junit.Assert.*;
import org.junit.Test;

public final class IncompleteNodeExceptionTest
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

        // TODO: Remove these two lines.
        execution.Runner.main(null);
        typechecks.Runner.main(null);

        final IConstruct node = new Module();

        final IncompleteNodeException object = new IncompleteNodeException(node);

        assertTrue(node == object.parent());
    }
}
