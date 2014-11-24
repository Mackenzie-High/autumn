package high.mackenzie.autumn.compiler.documentor;

import autumn.lang.compiler.ast.commons.IRecord;

/**
 *
 * @author mackenzie
 */
public final class TuplePage
        extends AbstractRecordPage
{
    public TuplePage(final ModulePage module,
                     final Documentor documentor,
                     final IRecord node)
    {
        super(module, documentor, node);
    }
}
