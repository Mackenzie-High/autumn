/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package high.mackenzie.autumn.lang.compiler.typesystem;

import high.mackenzie.autumn.lang.compiler.typesystem.design.IAnnotation;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IDeclaredType;
import high.mackenzie.autumn.lang.compiler.typesystem.design.IMember;
import high.mackenzie.autumn.lang.compiler.typesystem.design.ITypeFactory;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author mackenzie
 */
public class AbstractCustomMember extends AbstractMember
{

    private IDeclaredType owner;

    private List<IAnnotation> annotations;

    private int modifiers;

    private String name;
      
    public AbstractCustomMember(final ITypeFactory factory)
    {
        super(factory);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public IDeclaredType getOwner()
    {
        return owner;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<IAnnotation> getAnnotations()
    {
        return annotations;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getModifiers()
    {
        return modifiers;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName()
    {
        return name;
    }
    
    /**
     * Setter.
     * 
     * @param owner is the owner of this member.
     */
    public void setOwner(final IDeclaredType owner)
    {
        this.owner = owner;
    }

    /**
     * Setter.
     * 
     * @param annotations are the annotations applied directly to this member.
     */
    public void setAnnotations(final List<IAnnotation> annotations)
    {
        this.annotations = annotations;
    }

    /**
     * Setter.
     * 
     * @param modifiers are the modifiers applied to this member.
     */
    public void setModifiers(final int modifiers)
    {
        this.modifiers = modifiers;
    }

    /**
     * Setter.
     * 
     * @param name is the name of this member.
     */
    public void setName(final String name)
    {
        this.name = name;
    }    
    
}
