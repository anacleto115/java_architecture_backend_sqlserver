
package lib_data_core.Core;

public class Parameters 
{
    public String Name;
    public int Type;
    public Object Value;
    public boolean Direction = false;

    public Parameters(String name, int type, Object value)
    {
        Name = name;
        Type = type;
        Value = value;
    }    
    
    public Parameters(String name, int type, Object value, boolean direction)
    {
        Name = name;
        Type = type;
        Value = value;
        Direction = direction;
    }    
}