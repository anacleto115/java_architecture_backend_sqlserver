
package lib_utilities.Utilities;

//import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncodingHelper 
{
    public static String ToString(byte[] data)
    {
        //return new String(data, StandardCharsets.UTF_8);
        return Base64.getEncoder().encodeToString(data);
    }

    public static byte[] ToBytes(String data)
    {
        //return data.getBytes(StandardCharsets.UTF_8);
        return Base64.getDecoder().decode(data);
    }
}