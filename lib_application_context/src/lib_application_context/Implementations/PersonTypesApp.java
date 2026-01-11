
package lib_application_context.Implementations;

import java.util.HashMap;
import lib_application_context.Core.App;
import lib_application_core.Interfaces.IPersonTypesApp;
import lib_data_core.Core.FactoryRepository;
import lib_domain_context.Enumerables.Architecture;
import lib_domain_entities.Models.PersonTypes;
import lib_infrastructure.Implementations.PersonTypesParser;

public class PersonTypesApp extends App<PersonTypes> implements IPersonTypesApp
{
    public PersonTypesApp(HashMap<String, Object> data) 
    {
        super();
    }
    
    @Override 
    public HashMap<String, Object> Load(HashMap<String, Object> data) 
    {
        data = super.Load(data);
        if (data.containsKey("Architecture") &&
            (Architecture)data.get("Architecture") == Architecture.Services)
            iParser = new PersonTypesParser();
        if (!data.containsKey("IRepository"))
            iRepository = FactoryRepository.Get(data);
        return data;
    }
}