
package lib_service_core.Core;

import java.util.HashMap;

public interface IToken
{
    boolean Validate(HashMap<String, Object> data);
    String Authenticate(String string);
}