package autumn.util.dataflow;

/**
 *
 *
 * @author mackenzie
 */
public interface Dataflow
{
    public Worker find(String name);

    public Worker find(int id);

    public Worker newTicker(final String name,
                            final long interval);
}
