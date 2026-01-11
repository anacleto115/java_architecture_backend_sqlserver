
package lib_application_context.Core;

import java.util.HashMap;
import lib_application_context.Implementations.PersonTypesApp;
import lib_domain_context.IApplication;
import lib_domain_context.Enumerables;
import lib_domain_context.IFactory;

public class FactoryApplicationContext implements IFactory<IApplication>
{
    @Override
    public IApplication Get(HashMap<String, Object> data) 
    {
        Enumerables.Types type = (Enumerables.Types)data.get("Type");
        switch (type)
        {
            case PersonTypes: return new PersonTypesApp(data);
            default: return null;
        }
    }    
}