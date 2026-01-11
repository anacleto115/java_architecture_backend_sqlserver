
package lib_application_context.Core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lib_data_context.Core.FactoryRepositoryContext;
import lib_data_core.Core.FactoryRepository;
import lib_domain_context.IApplication;
import lib_domain_context.IRepository;
import lib_domain_context.IParser;

public class App<T> implements IApplication
{
    protected IParser<T> iParser;
    protected IRepository iRepository;

    public HashMap<String, Object> Load(HashMap<String, Object> data) 
    {
        FactoryRepository.IFactoryRepository = FactoryRepository.IFactoryRepository == null ? 
            new FactoryRepositoryContext() : FactoryRepository.IFactoryRepository;
        if (data.containsKey("IRepository"))
            iRepository = (IRepository)data.get("IRepository");
        return data;
    }

    @Override
    public HashMap<String, Object> Select(HashMap<String, Object> data) 
    {
        try
        {
            data = Load(data);
            HashMap<String, Object> response = iRepository.Select(data);
            if (iParser != null && response.containsKey("Entities"))
            {
                List<T> list = (ArrayList<T>)response.get("Entities");
                int count = 0;
                HashMap<String, Object> dic = new HashMap<String, Object>();
                for (T item : list)
                {
                    dic.put(String.valueOf(count), iParser.ToDictionary(item));
                    count++;
                }
                response.put("Entities", dic);
            }
            return response;
        }
        catch (Exception ex)
        {
            HashMap<String, Object> response = new HashMap<String, Object>();
            response.put("Error", ex.toString());
            return response;
        }
    }

    @Override
    public HashMap<String, Object> Insert(HashMap<String, Object> data) 
    {
        try
        {
            data = Load(data);
            if (!data.containsKey("Entity"))
            {
                HashMap<String, Object> response = new HashMap<String, Object>();
                response.put("Error", "lbMissingInfo");
                return response;
            }
            if (iParser != null)
                data.put("Entity", iParser.ToEntity((HashMap<String, Object>)data.get("Entity")));
            if (iParser != null && !iParser.Validate((T)data.get("Entity")))
            {
                HashMap<String, Object> response = new HashMap<String, Object>();
                response.put("Error", "lbMissingInfo");
                return response;
            }
            HashMap<String, Object> response = iRepository.Insert(data);
            if (iParser != null && response.containsKey("Entity"))
                response.put("Entity", iParser.ToDictionary((T)response.get("Entity")));
            return response;
        }
        catch (Exception ex)
        {
            HashMap<String, Object> response = new HashMap<String, Object>();
            response.put("Error", ex.toString());
            return response;
        }
    }

    @Override
    public HashMap<String, Object> Update(HashMap<String, Object> data) 
    {
        try
        {
            data = Load(data);
            if (!data.containsKey("Entity"))
            {
                HashMap<String, Object> response = new HashMap<String, Object>();
                response.put("Error", "lbMissingInfo");
                return response;
            }
            if (iParser != null)
                data.put("Entity", iParser.ToEntity((HashMap<String, Object>)data.get("Entity")));
            if (iParser != null && !iParser.Validate((T)data.get("Entity")))
            {
                HashMap<String, Object> response = new HashMap<String, Object>();
                response.put("Error", "lbMissingInfo");
                return response;
            }
            HashMap<String, Object> response = iRepository.Update(data);
            if (iParser != null && response.containsKey("Entity"))
                response.put("Entity", iParser.ToDictionary((T)response.get("Entity")));
            return response;
        }
        catch (Exception ex)
        {
            HashMap<String, Object> response = new HashMap<String, Object>();
            response.put("Error", ex.toString());
            return response;
        }
    }

    @Override
    public HashMap<String, Object> Delete(HashMap<String, Object> data) 
    {
        try
        {
            data = Load(data);
            if (!data.containsKey("Entity"))
            {
                HashMap<String, Object> response = new HashMap<String, Object>();
                response.put("Error", "lbMissingInfo");
                return response;
            }
            if (iParser != null)
                data.put("Entity", iParser.ToEntity((HashMap<String, Object>)data.get("Entity")));
            if (iParser != null && !iParser.Validate((T)data.get("Entity")))
            {
                HashMap<String, Object> response = new HashMap<String, Object>();
                response.put("Error", "lbMissingInfo");
                return response;
            }
            HashMap<String, Object> response = iRepository.Delete(data);
            if (iParser != null && response.containsKey("Entity"))
                response.put("Entity", iParser.ToDictionary((T)response.get("Entity")));
            return response;
        }
        catch (Exception ex)
        {
            HashMap<String, Object> response = new HashMap<String, Object>();
            response.put("Error", ex.toString());
            return response;
        }
    }    
}