/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.lang.compiler.utils;

import com.google.common.collect.Maps;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IExpressionType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IReturnType;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;

/**
 *
 * @author mackenzie
 */
public class Converter
{
    private final LinkedHashMap<IReturnType, IExpressionType> predefined = Maps.newLinkedHashMap();

    /**
     * This method adds another predefined conversion to this converter.
     *
     * @param output is the type of value that the conversion will return.
     * @param input is the type of value that the conversion takes as input.
     * @param method is the method that performs the actual conversion.
     */
    public void addPredefined(final IReturnType output,
                              final IExpressionType input,
                              final Method method)
    {
    }
}
