package com.mackenziehigh.autumn.lang.compiler.typesystem.design;

import com.mackenziehigh.autumn.resources.Finished;
import java.util.Collection;
import java.util.List;

/**
 * An instance of this interface provides utility methods for working with type-system components.
 *
 * @author Mackenzie High
 */
@Finished("2014/07/12")
public interface ITypeSystemUtils
{
    /**
     * This method finds a field, given its name, from a collection of fields.
     *
     * @param fields is the collection containing the fields.
     * @param name is the name of the field to find.
     * @return the field that is sought after, or null, if the field is not found.
     * @throws NullPointerException <code>if fields == null</code>
     * @throws NullPointerException <code>if name == null</code>
     */
    public IField findField(Collection<? extends IField> fields,
                            String name);

    /**
     * This method finds a constructor, given its formal-parameter types,
     * from a collection of constructors.
     *
     * @param constructors is the collection containing the constructors.
     * @param parameters is a list containing the types of the constructor's formal-parameters.
     * @return the constructor that is sought after, or null, if the constructor is not found.
     * @throws NullPointerException <code>if constructors == null</code>
     * @throws NullPointerException <code>if parameters == null</code>
     * @throws NullPointerException <code>if parameters contains a null element</code>
     */
    public IConstructor findConstructor(Collection<? extends IConstructor> constructors,
                                        List<? extends IVariableType> parameters);

    /**
     * This method finds a method, given its name and formal-parameter types,
     * from a collection of methods.
     *
     * @param methods is the collection containing the methods.
     * @param parameters is a list containing the types of the method's formal-parameters.
     * @return the method that is sought after, or null, if the method is not found.
     * @throws NullPointerException <code>if methods == null</code>
     * @throws NullPointerException <code>if name == null</code>
     * @throws NullPointerException <code>if parameters == null</code>
     * @throws NullPointerException <code>if parameters contains a null element</code>
     */
    public IConstructor findMethod(Collection<? extends IConstructor> methods,
                                   String name,
                                   List<? extends IVariableType> parameters);
}
