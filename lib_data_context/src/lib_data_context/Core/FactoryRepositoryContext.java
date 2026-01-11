
package lib_data_context.Core;

import java.util.HashMap;
import lib_data_context.Implementations.PersonTypesRepository;
import lib_domain_context.IRepository;
import lib_domain_context.Enumerables.Types;
import lib_domain_context.IFactory;

public class FactoryRepositoryContext implements IFactory<IRepository>
{
    @Override
    public IRepository Get(HashMap<String, Object> data)
    {
        Types type = (Types)data.get("Type");
        switch (type)
        {
            case PersonTypes: return new PersonTypesRepository(data);
            default: return null;
        }
    }
}