
package lib_data_core.Core;

import java.sql.Types;
import java.sql.ResultSet;
import java.sql.Statement;

public interface IConnection 
{
    int GetConnectionTimeout() throws Exception;
    boolean GetState() throws Exception;
    void SetStringConnection(String v) throws Exception;
    String GetStringConnection() throws Exception;

    void Open() throws Exception;
    void Close() throws Exception;
    ResultSet ExecuteQuery() throws Exception;
    Statement CreateStatement(String name) throws Exception;
    Parameters CreateParameter(String name, int type, Object value, boolean direction) throws Exception;
    int ExecuteNonQuery() throws Exception;
}