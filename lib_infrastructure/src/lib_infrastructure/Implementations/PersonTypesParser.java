
package lib_infrastructure.Implementations;

import java.sql.ResultSet;
import java.util.HashMap;
import lib_domain_entities.Models.PersonTypes;
import lib_domain_context.IParser;

public class PersonTypesParser implements IParser<PersonTypes>
{
    @Override
    public PersonTypes CreateEntity(Object ItemArray) throws Exception
    {
        ResultSet resultSet = (ResultSet)ItemArray;
        
        PersonTypes entity = new PersonTypes();
        entity.setId(resultSet.getInt(1));        
        entity.setName(resultSet.getString(2));   
        return entity;
    }

    @Override
    public PersonTypes ToEntity(HashMap<String, Object> data) throws Exception
    {
        PersonTypes entity = new PersonTypes();
        entity.setId(Integer.parseInt(data.get("Id").toString())); 
        if (data.containsKey("Name"))      
            entity.setName(data.get("Name").toString());    
        return entity;
    }

    @Override
    public HashMap<String, Object> ToDictionary(PersonTypes entity) throws Exception 
    {
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("Id", String.valueOf(entity.getId()));
        if (entity.getName() != null && !entity.getName().equals(""))      
            data.put("Name", String.valueOf(entity.getName())); 
        return data;
    }

    @Override
    public boolean Validate(PersonTypes entity) throws Exception
    {
        if (entity.getName() == null || entity.getName().equals(""))        
            return false;
        return true;
    }
}