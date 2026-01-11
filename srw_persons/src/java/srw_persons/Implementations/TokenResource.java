
package srw_persons.Implementations;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import lib_domain_context.ServiceData;
import lib_service_core.Core.IToken;

@Path("Token")
public class TokenResource implements IToken
{
    @Override
    public boolean Validate(HashMap<String, Object> data) 
    {
        try
        {
            if (data == null || !data.containsKey("Bearer"))
                return false;
            String bearer = (String)data.get("Bearer");
            if (!ServiceData.KeyToken.equals(bearer))
                return false;
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }

    @Path("Authenticate")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public String Authenticate(String data) 
    {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return "{" + 
                "'Token': '" + ServiceData.KeyToken + "', " +
                "'Expires':'" + formatter.format(new Date()) + "'" +
            "}";
    }
}