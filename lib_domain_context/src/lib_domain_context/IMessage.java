
package lib_domain_context;

import lib_domain_context.MessagesHelper.Message;

public interface IMessage 
{
    Object Show(Object message);
    Object Show(Object message, Message type);
}