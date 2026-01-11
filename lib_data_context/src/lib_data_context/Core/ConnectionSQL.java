
package lib_data_context.Core;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import lib_data_core.Core.IConnection;
import lib_data_core.Core.Parameters;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionSQL implements IConnection
{
    private Connection connection;
    private Statement statement;
    private String stringConnection;
    private int count = 1;
        
    public ConnectionSQL(String stringConnection) throws Exception
    {
        this.stringConnection = stringConnection;     
    }
        
    @Override public int GetConnectionTimeout() throws Exception { return connection.getNetworkTimeout(); }
    @Override public boolean GetState() throws Exception { return connection.isClosed(); }
    @Override public void SetStringConnection(String v) throws Exception { stringConnection = v; }
    @Override public String GetStringConnection() throws Exception { return stringConnection; }

    @Override
    public Statement CreateStatement(String name) throws Exception 
    {
        statement = connection.prepareCall(name);
        count = 1;
        return statement;
    }

    @Override
    public Parameters CreateParameter(String name, int type, Object value, boolean direction) throws Exception 
    {        
        Parameters parameter = new Parameters(name, type, value, direction);
        if (!direction)
            ((CallableStatement)statement).setObject(count, value); 
        else
            ((CallableStatement)statement).registerOutParameter(count, type); 
        count++;
        return parameter;
    }
    
    @Override
    public ResultSet ExecuteQuery() throws Exception 
    {
        return ((PreparedStatement)statement).executeQuery();
    }

    @Override
    public int ExecuteNonQuery() throws Exception 
    {
        return ((CallableStatement)statement).executeUpdate();
    }
    
    @Override
    public void Open() throws Exception 
    {
        if (stringConnection == null || stringConnection.equals(""))
            return;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
        connection = DriverManager.getConnection(this.stringConnection); 
    }

    @Override
    public void Close() throws Exception 
    {
        if (connection == null)
            return;
        connection.close();
        connection = null;
    }
}