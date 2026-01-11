
package lib_data_core.Core;

import java.util.HashMap;
import lib_domain_context.IRepository;
import lib_domain_context.IFactory;

public class FactoryRepository 
{
    public static IFactory<IRepository> IFactoryRepository;

    public static IRepository Get(HashMap<String, Object> data)
    {
        if (IFactoryRepository == null)
            return null;

        return IFactoryRepository.Get(data);
    }
}