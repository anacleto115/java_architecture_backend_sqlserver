
package lib_utilities.Utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Convert 
{
    public static int StringToInt(String value) throws Exception
    {
        if (value == null || value.equals(""))
            return 0;
        return Integer.parseInt(value);
    }
    
    public static Date StringToDate(String value) throws Exception
    {
        Date date = StringToDate(value, "yyyy-MM-dd HH:mm:ss");
        if (date != null)
            return date;
        date = StringToDate(value, "yyyy-MM-dd'T'HH:mm:ss");
        if (date != null)
            return date;
        date = StringToDate(value, "yyyy-MM-dd");
        return date;
    }
    
    public static Date StringToDate(String value, String format) throws Exception
    {
        Date date = null;
        try
        {
            SimpleDateFormat income = new SimpleDateFormat(format);
            date = income.parse(value);
            return date;
        }
        catch (Exception ex)
        {
            return date;
        }
    }
    
    public static String DateToString(Date value) throws Exception
    { 
        return DateToString(value, "yyyy-MM-dd HH:mm:ss");
    }
    
    public static String DateToString(Date value, String format) throws Exception
    { 
        String result = "";
        try
        {
            if (format == null || format.equals(""))
                format = "yyyy-MM-dd HH:mm:ss";
            DateFormat dateFormat = new SimpleDateFormat(format);  
            result = dateFormat.format(value); 
            return result;
        }
        catch (Exception ex)
        {
            return result;
        }
    }
}
