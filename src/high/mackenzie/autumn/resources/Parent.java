package high.mackenzie.autumn.resources;

import java.util.Collection;

/**
 * TODO remove
 *
 * @author mackenzie
 */
public interface Parent
{
    public Parent name(String value);

    public String name();

    public Parent children(final Collection value);

    public Collection children();
}
