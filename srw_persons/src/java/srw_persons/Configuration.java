
package srw_persons;

import lib_domain_context.IConfiguration;

public class Configuration implements IConfiguration 
{
    @Override
    public String Get(String key) 
    {
        return "jdbc:sqlserver://DESKTOP-5DHDU2D:1433;databaseName=db_persons;user=sa;" +
               "password=*****;encrypt=true;trustServerCertificate=true";
    }
}