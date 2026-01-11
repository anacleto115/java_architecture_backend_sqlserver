
package lib_utilities.Utilities;

public class Task<T> implements Runnable
{
    public T content;

    public Task() { }
    public Task(T content) { this.content = content;  }

    @Override public void run() {  }
}