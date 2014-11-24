package high.mackenzie.autumn.compiler.documentor;

import com.google.common.collect.Lists;
import java.lang.reflect.Method;
import java.util.List;

/**
 *
 * @author mackenzie
 */
final class MethodTable
{
    public final List<MetaMethod> methods = Lists.newLinkedList();

    public void load(final Class type)
    {
        for (Method x : type.getMethods())
        {
            methods.add(MetaMethod.create(x));
        }
    }

    public String createTable()
    {
        final StringBuilder html = new StringBuilder();

        html.append("<table>");

        html.append("<tr><td><b>Signature</b></td> <td><b>Summary</b></td></tr>");

        for (MetaMethod method : methods)
        {
            html.append("<tr><td>").append(method.signature()).append("</td>")
                    .append("<td>").append(method.summary).append("</td></tr>");
        }

        html.append("</table>");

        return html.toString();
    }
}
