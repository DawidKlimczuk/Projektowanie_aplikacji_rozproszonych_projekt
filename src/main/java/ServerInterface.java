import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ServerInterface extends Remote {
    void addTask(String name, String description) throws RemoteException;
    List<Task> getTasks() throws RemoteException;
    void updateTaskStatus(int id, String status) throws RemoteException;
    void removeTask(int id) throws RemoteException;
}