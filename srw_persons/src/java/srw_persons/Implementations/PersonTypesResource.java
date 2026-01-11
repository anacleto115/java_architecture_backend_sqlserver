
package srw_persons.Implementations;

import java.util.HashMap;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import lib_application_core.Core.FactoryApplication;
import lib_domain_context.Enumerables.Types;
import srw_persons.Core.ServiceBase;

@Path("PersonTypes")
public class PersonTypesResource extends ServiceBase
{
    @Override
    public HashMap<String, Object> LoadApplication(HashMap<String, Object> data)
    {
        data = super.LoadApplication(data);
        data.put("Type", Types.PersonTypes);
        iApp = iApp == null ? FactoryApplication.Get(data) : iApp;
        return data;
    }
        
    @Path("Select")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public String Select(String income) { return super.Select(income); }
}