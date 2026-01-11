
package srw_persons;

import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application 
{
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }
    
    private void addRestResourceClasses(Set<Class<?>> resources) 
    {
        resources.add(srw_persons.Implementations.PersonTypesResource.class);
        resources.add(srw_persons.Implementations.TokenResource.class);
    }
}