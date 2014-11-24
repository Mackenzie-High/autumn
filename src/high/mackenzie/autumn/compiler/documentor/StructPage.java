package high.mackenzie.autumn.compiler.documentor;

import autumn.lang.compiler.ast.commons.IRecord;

/**
 *
 * @author mackenzie
 */
public class StructPage
        extends AbstractRecordPage
{
    public StructPage(final ModulePage module,
                      final Documentor documentor,
                      final IRecord node)
    {
        super(module, documentor, node);
    }
}
