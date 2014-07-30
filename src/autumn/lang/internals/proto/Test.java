package autumn.lang.internals.proto;

import autumn.lang.Member;
import autumn.lang.Prototype;
import com.google.common.collect.Lists;

/**
 *
 * @author mackenzie
 */
public class Test
{
    public static interface Person
            extends Prototype
    {
        public static final PersonImp PROTO = new PersonImp();

        public Person name(String name)
                throws Throwable;

        public String name()
                throws Throwable;

        public Person age(int age)
                throws Throwable;

        public int age()
                throws Throwable;

        public void speak()
                throws Throwable;
    }

    public static final class PersonImp
            extends AbstractPrototype
            implements Person
    {
        private static final MetaPrototype klass = new MetaPrototype();

        static
        {
            klass.newProperty("name", String.class);
            klass.newMethod("speak", Lists.<Class>newArrayList(int.class, long.class), void.class);
            klass.newProperty("age", int.class);
        }

        public PersonImp()
        {
            super(klass);
        }

        private PersonImp(final PersonImp self)
        {
            super(self);
        }

        @Override
        public PersonImp copy()
        {
            return new PersonImp(this);
        }

        @Override
        public Person name(String name)
                throws Throwable
        {
            return (Person) AbstractPrototype.Actions.set(this, 0, name);
        }

        @Override
        public String name()
                throws Throwable
        {
            return (String) AbstractPrototype.Actions.getO(this, 0);
        }

        @Override
        public void speak()
                throws Throwable
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Person age(int age)
                throws Throwable
        {
            return (Person) AbstractPrototype.Actions.set(this, 2, age);
        }

        @Override
        public int age()
                throws Throwable
        {
            return AbstractPrototype.Actions.getI(this, 2);
        }
    }

    public static void main(String[] args)
            throws Throwable
    {
        Person p = Person.PROTO;
        Person x = Person.PROTO;

        p = p.name("Hello");
        x = p;
        p = p.name("World");

        p = p.age(25);
        x = x.age(27);

        System.out.println("P Name = " + p.name());
        System.out.println("X Name = " + x.name());

        System.out.println("P Age = " + p.age());
        System.out.println("X Age = " + x.age());

        for (Member m : p.members())
        {
            System.out.println(m);
        }
    }
}
