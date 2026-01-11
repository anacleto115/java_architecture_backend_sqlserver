
package lib_utilities.Utilities;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptHelper 
{
    private static SecretKey secretKey;
    private static String key = "2021!--*+P3Rs0n2023--";  
    private static byte[] sharedvector = { 0x01, 0x02, 0x03, 0x05, 0x07, 0x0B, 0x0D, 0x11 };

    private static void GenerateKey(String subKey) throws Exception
    {
        if (secretKey != null)
            return;
        KeyGenerator aes = KeyGenerator.getInstance("DESede");
        aes.init(112);
        secretKey = aes.generateKey();
        secretKey = new SecretKeySpec(secretKey.getEncoded(), "DESede");
    }
    
    public static String EncryptKey(String value) { return EncryptKey(value, ""); }
    
    public static String EncryptKey(String value, String subKey)
    {
        try
        {
            if (value == null || value.equals(""))
                return value;
            
            GenerateKey(subKey);  
            Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(sharedvector));
            byte[] encrypted = c.doFinal(value.getBytes("UTF-8"));
            encrypted = Base64.getEncoder().encode(encrypted);
            return new String(encrypted, "UTF-8");
        }
        catch (Exception ex)
        {
            return "";
        }
    }
    
    public static String DecryptKey(String value) { return DecryptKey(value, ""); }
    
    public static String DecryptKey(String value, String subKey)
    {
        try
        {
            if (value == null || value.equals(""))
                return value;
            
            GenerateKey(subKey);  
            Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            c.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(sharedvector));
            byte[] decrypted = c.doFinal(Base64.getDecoder().decode(value));
            return new String(decrypted, "UTF-8");
        }
        catch (Exception ex)
        {
            return "";
        }
    }
}
