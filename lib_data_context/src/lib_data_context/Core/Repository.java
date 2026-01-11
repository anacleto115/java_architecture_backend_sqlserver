
package lib_data_context.Core;

import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.sql.Types;
import java.sql.ResultSet;
import java.sql.Statement;
import lib_data_core.Core.IConnection;
import lib_data_core.Core.Parameters;
import lib_domain_context.IParser;
import lib_utilities.Utilities.PCDataHelper;

public class Repository<T> 
{
    protected IParser<T> parser;
    protected IConnection connection;

    protected void Load(HashMap<String, Object> data) throws Exception
    {
        try
        {
            if (data == null || !data.containsKey("Connection"))
                connection = new ConnectionSQL((String)data.get("stringConnection"));
            else
                connection = (IConnection)data.get("Connection");
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }

    public HashMap<String, Object> Execute(HashMap<String, Object> data)
    {
        HashMap<String, Object> response = new HashMap<String, Object>();
        try
        {
            Load(data);
            if (!data.containsKey("Open") || (boolean)data.get("Open"))
                connection.Open();
            
            Statement command = connection.CreateStatement((String)data.get("Procedure"));
            List<Parameters> parameters = (ArrayList<Parameters>)data.get("Parameters");
            parameters.add(new Parameters("create_by", Types.NVARCHAR, "Services"));
            parameters.add(new Parameters("ip", Types.NVARCHAR, PCDataHelper.IpPc()));
            parameters.add(new Parameters("result", Types.INTEGER, 0, true));

            for (Parameters item : parameters)
                connection.CreateParameter(item.Name, item.Type, item.Value, item.Direction);
                
            ResultSet resultSet = connection.ExecuteQuery();
            
            List<T> list = new ArrayList<T>();
            while (resultSet.next())
                list.add(parser.CreateEntity(resultSet));
            
            if (((CallableStatement)command).getInt(parameters.size()) <= 0)
            {
                response.put("Error", "VALIDATION");
                return response;
            }

            if (!data.containsKey("Close") || (boolean)data.get("Close"))
                connection.Close();

            response.put("Entities", list);
            response.put("Response", "OK");
            return response;
        }
        catch (Exception ex)
        {
            response.put("Error", ex.toString());
            return response;
        }
    }
    
    public HashMap<String, Object> ExecuteNonQuery(HashMap<String, Object> data)
    {
        HashMap<String, Object> response = new HashMap<String, Object>();
        try
        {
            Load(data);
            if (!data.containsKey("Open") || (boolean)data.get("Open"))
                connection.Open();
            
            Statement command = connection.CreateStatement((String)data.get("Procedure"));
            List<Parameters> parameters = (ArrayList<Parameters>)data.get("Parameters");
            parameters.add(new Parameters("create_by", Types.NVARCHAR, "Services"));
            parameters.add(new Parameters("ip", Types.NVARCHAR, PCDataHelper.IpPc()));
            parameters.add(new Parameters("result", Types.INTEGER, 0, true));

            for (Parameters item : parameters)
                connection.CreateParameter(item.Name, item.Type, item.Value, item.Direction);
                
            connection.ExecuteNonQuery();
            int result = ((CallableStatement)command).getInt(parameters.size());
            
            if (!data.containsKey("Close") || (boolean)data.get("Close"))
                connection.Close();

            response.put("Result", result);
            response.put("Response", "OK");
            return response;
        }
        catch (Exception ex)
        {
            response.put("Error", ex.toString());
            return response;
        }
    }
}