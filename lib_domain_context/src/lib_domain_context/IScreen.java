
package lib_domain_context;

import java.util.HashMap;
import lib_domain_context.Enumerables.Loading;

public interface IScreen
{
    void Loading(Loading action);
    void MoveFocus();
    void Change(HashMap<String, Object> data);
}