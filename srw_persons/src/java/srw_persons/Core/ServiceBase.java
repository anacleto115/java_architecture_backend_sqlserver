
package srw_persons.Core;

import java.util.HashMap;
import lib_application_core.Core.FactoryApplication;
import lib_application_context.Core.FactoryApplicationContext;
import lib_domain_context.Enumerables.Architecture;
import lib_domain_context.IApplication;
import lib_domain_context.IConfiguration;
import lib_domain_context.IService;
import lib_service_core.Core.IToken;
import lib_utilities.Utilities.JsonHelper;
import srw_persons.Configuration;
import srw_persons.Implementations.TokenResource;

public class ServiceBase implements IService
{
    protected String name;
    protected IApplication iApp;
    protected IToken iToken;
    protected IConfiguration iConfiguration;
    
    public HashMap<String, Object> LoadApplication(HashMap<String, Object> data)
    {
        iConfiguration = iConfiguration == null ? new Configuration() : iConfiguration;
        FactoryApplication.IFactoryApplication = FactoryApplication.IFactoryApplication == null ? 
            new FactoryApplicationContext() : FactoryApplication.IFactoryApplication;
        iToken = iToken == null ? new TokenResource() : iToken;
        data.put("stringConnection", iConfiguration.Get("Default"));
        data.put("Architecture", Architecture.Services);
        if (data.containsKey("IApplication"))
            iApp = (IApplication)data.get("IApplication");
        return data;
    }
    
    @Override
    public String Select(String income) 
    {
        HashMap<String, Object> response = new HashMap<String, Object>();
        try
        {
            HashMap<String, Object> data = FuncValidate(income);
            if (data.containsKey("Error"))
                return JsonHelper.ConvertToString(data);
            response = iApp.Delete(data);
            return JsonHelper.ConvertToString(response);
        }
        catch (Exception ex)
        {
            response.put("Error", ex.toString());
            return JsonHelper.ConvertToString(response);
        }
    }

    @Override
    public String Insert(String income) 
    {
        HashMap<String, Object> response = new HashMap<String, Object>();
        try
        {
            HashMap<String, Object> data = FuncValidate(income);
            if (data.containsKey("Error"))
                return JsonHelper.ConvertToString(data);
            response = iApp.Delete(data);
            return JsonHelper.ConvertToString(response);
        }
        catch (Exception ex)
        {
            response.put("Error", ex.toString());
            return JsonHelper.ConvertToString(response);
        }
    }

    @Override
    public String Update(String income)
    {
        HashMap<String, Object> response = new HashMap<String, Object>();
        try
        {
            HashMap<String, Object> data = FuncValidate(income);
            if (data.containsKey("Error"))
                return JsonHelper.ConvertToString(data);
            response = iApp.Delete(data);
            return JsonHelper.ConvertToString(response);
        }
        catch (Exception ex)
        {
            response.put("Error", ex.toString());
            return JsonHelper.ConvertToString(response);
        }
    }

    @Override
    public String Delete(String income)
    {
        HashMap<String, Object> response = new HashMap<String, Object>();
        try
        {
            HashMap<String, Object> data = FuncValidate(income);
            if (data.containsKey("Error"))
                return JsonHelper.ConvertToString(data);
            response = iApp.Delete(data);
            return JsonHelper.ConvertToString(response);
        }
        catch (Exception ex)
        {
            response.put("Error", ex.toString());
            return JsonHelper.ConvertToString(response);
        }
    }    
    
    protected HashMap<String, Object> FuncValidate(String income)
    {
        HashMap<String, Object> response = new HashMap<String, Object>();
        try
        {
            HashMap<String, Object> data = JsonHelper.ConvertToObject(income);
            data = LoadApplication(data);
            if (!iToken.Validate(data))
            {
                response.put("Error", "NoAuthenticate");
                return response;
            }
            return data;
        }
        catch (Exception ex)
        {
            response.put("Error", ex.toString());
            return response;
        }
    }
}