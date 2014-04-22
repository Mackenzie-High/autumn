package high.mackenzie.autumn.lang.compiler.utils;

import com.google.common.collect.Lists;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMethod;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVariableType;
import java.io.PrintStream;
import java.util.List;

/**
 * An instance of this class represents the structure of a class that implements a prototype.
 *
 * @author Mackenzie High
 */
public class ProtoClass
{
    public final class Property
    {
        public final String name;

        public final IVariableType type;

        public final boolean nullable;

        public final boolean mutable;

        public final boolean sync;

        public Property(final String name,
                        final IVariableType type,
                        final boolean nullable,
                        final boolean mutable,
                        final boolean sync)
        {
            this.mutable = mutable;
            this.nullable = nullable;
            this.sync = sync;
            this.name = name;
            this.type = type;
        }

        /**
         * This method retrieves the numeric identifier of this property within the prototype.
         *
         * @return the numeric identifier of the property.
         */
        public int offset()
        {
            return properties.indexOf(this);
        }

        /**
         * {@inheritDoc}
         */
        public void print(final PrintStream out)
        {
            out.printf("property %s : %s\n", name, type.getDescriptor());
        }
    }

    public final class Setter
    {
        /**
         * This is the property that the setter sets.
         */
        public final Property property;

        public Setter(final Property property)
        {
            this.property = property;
        }

        /**
         * {@inheritDoc}
         */
        public void print(final PrintStream out)
        {
        }
    }

    public final class Getter
    {
        /**
         * {@inheritDoc}
         */
        public void print(final PrintStream out)
        {
        }
    }

    public final class Method
    {
        /**
         * {@inheritDoc}
         */
        public void print(final PrintStream out)
        {
        }
    }

    public final class SetterBridge
    {
        public final Property property;

        public final IVariableType type;

        public final IMethod bridge;

        public SetterBridge(final IMethod bridge,
                            final Property property,
                            final IVariableType type)
        {
            this.bridge = bridge;
            this.property = property;
            this.type = type;
        }

        /**
         * {@inheritDoc}
         */
        public void print(final PrintStream out)
        {
        }
    }

    public final class GetterBridge
    {
        /**
         * {@inheritDoc}
         */
        public void print(final PrintStream out)
        {
        }
    }

    public final class MethodBridge
    {
        /**
         * {@inheritDoc}
         */
        public void print(final PrintStream out)
        {
        }
    }

    /**
     * These are the properties themselves.
     */
    public final List<Property> properties = Lists.newLinkedList();

    /**
     * These are the setters that set the properties, excluding bridge methods.
     */
    public final List<Setter> setters = Lists.newLinkedList();

    /**
     * These are the getters that get the properties, excluding bridge methods.
     */
    public final List<Getter> getters = Lists.newLinkedList();

    /**
     * These are the bridge methods that redirect to a setter.
     */
    public final List<SetterBridge> setter_bridges = Lists.newLinkedList();

    /**
     * These are the bridge methods that redirect to a getter.
     */
    public final List<GetterBridge> getter_bridges = Lists.newLinkedList();

    /**
     * These are the methods themselves, excluding bridge methods, setters, and getters.
     */
    public final List<Method> methods = Lists.newLinkedList();

    /**
     * These are the bridge methods that redirect to a non-setter or non-getter method.
     */
    public final List<MethodBridge> method_bridges = Lists.newLinkedList();

    public void print(final PrintStream out)
    {
        for (Property p : properties)
        {
            p.print(out);
        }

        for (Setter p : setters)
        {
            p.print(out);
        }

        for (Getter p : getters)
        {
            p.print(out);
        }

        for (Method p : methods)
        {
            p.print(out);
        }

        for (SetterBridge p : setter_bridges)
        {
            p.print(out);
        }

        for (GetterBridge p : getter_bridges)
        {
            p.print(out);
        }

        for (MethodBridge p : method_bridges)
        {
            p.print(out);
        }
    }
}
