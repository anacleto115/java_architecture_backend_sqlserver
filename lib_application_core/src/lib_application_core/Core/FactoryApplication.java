
package lib_application_core.Core;

import java.util.HashMap;
import lib_domain_context.IApplication;
import lib_domain_context.IFactory;

public class FactoryApplication 
{
    public static IFactory<IApplication> IFactoryApplication;

    public static IApplication Get(HashMap<String, Object> data)
    {
        if (IFactoryApplication == null)
            return null;

        return IFactoryApplication.Get(data);
    }
}