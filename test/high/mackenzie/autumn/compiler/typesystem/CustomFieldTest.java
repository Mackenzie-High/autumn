/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.compiler.typesystem;

import high.mackenzie.autumn.lang.compiler.typesystem.CustomField;
import high.mackenzie.autumn.lang.compiler.typesystem.TypeFactory;
import high.mackenzie.autumn.lang.compiler.typesystem.design.ITypeFactory;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IVariableType;
import java.lang.reflect.Field;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mackenzie
 */
public class CustomFieldTest
{
    /**
     * Test: 20130726164334015946
     *
     * <p>
     * Case: Setter and Getter Throughput
     * </p>
     */
    @Test
    public void test20130726164334015946()
    {
        System.out.println("Test: 20130726164334015946");

        final ITypeFactory factory = new TypeFactory();

        final CustomField field = new CustomField(factory);

        fail();
    }
}
