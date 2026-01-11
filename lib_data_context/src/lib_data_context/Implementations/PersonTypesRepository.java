
package lib_data_context.Implementations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lib_data_context.Core.Repository;
import lib_data_core.Core.Parameters;
import lib_data_core.Interfaces.IPersonTypesRepository;
import lib_domain_entities.Models.PersonTypes;
import lib_infrastructure.Implementations.PersonTypesParser;

public class PersonTypesRepository extends Repository<PersonTypes> implements IPersonTypesRepository
{
    public PersonTypesRepository(HashMap<String, Object> data) 
    { 
        super();
        parser = new PersonTypesParser(); 
    }
        
    @Override
    public HashMap<String, Object> Select(HashMap<String, Object> data) 
    {
        HashMap<String, Object> response = new HashMap<String, Object>();
        try
        {
            List<Parameters> parameters = new ArrayList<Parameters>();
            data.put("Parameters", parameters);
            data.put("Procedure", "EXEC sp_select_per_types ?, ?, ?");
            response = Execute(data);
            if (response == null)
            {
                response.put("Error", "lbNoAnswerDB");
                return response;
            }
            if (response.containsKey("Error"))
                return response;
            response.put("Response", "OK");
            return response;
        }
        catch (Exception ex)
        {
            response.put("Error", ex.getMessage());
            return response;
        }
    }

    @Override
    public HashMap<String, Object> Insert(HashMap<String, Object> data) 
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public HashMap<String, Object> Update(HashMap<String, Object> data) 
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public HashMap<String, Object> Delete(HashMap<String, Object> data) 
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}