
package lib_domain_entities.Models;

import lib_domain_context.IEntities;

public class PersonTypes implements IEntities 
{
    private int Id;

    public void setId(int v) { Id = v; }
    public int getId() { return Id; }
    
    private String Name;
    
    public void setName(String v) { Name = v; }
    public String getName() { return Name; }

    @Override public int Get_Id() { return Id; }
    @Override public Object GetClone()
    { 
        try 
        { 
            return super.clone();
        } 
        catch (CloneNotSupportedException ex) 
        {
            return null;
        }
    }
}