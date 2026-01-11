
package lib_utilities.Utilities;

import java.util.HashMap;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
        
public class JsonHelper
{
    private static JSONParser parser = new JSONParser();
    
    public static String ConvertToString(HashMap data) 
    {
        JSONObject JSONObject = new JSONObject(data);
        return JSONObject.toString().replace('"', '\"');
    }
    
    public static HashMap ConvertToObject(String data) throws Exception
    {
        return (HashMap<String,Object>)parser.parse(data);
    }   
}