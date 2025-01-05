import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Server extends UnicastRemoteObject implements ServerInterface {

    private static Server instance;
    private final List<Task> tasks;

    private Server() throws Exception {
        tasks = new ArrayList<>();
    }

    public static void main(String[] args) {
        try {
            // Uruchomienie rejestru RMI
            try {
                LocateRegistry.createRegistry(1099);
                System.out.println("RMI Registry started on port 1099.");
            } catch (Exception e) {
                System.out.println("RMI Registry already running.");
            }

            // Tworzenie instancji serwera i rejestracja w RMI
            instance = new Server();
            Registry registry = LocateRegistry.getRegistry(1099);
            registry.rebind("Server", instance);
            System.out.println("Server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Singleton: getInstance
    public static Server getInstance() throws Exception {
        if (instance == null) {
            instance = new Server();
        }
        return instance;
    }

    @Override
    public void addTask(String name, String description) {
        int id = tasks.size() + 1;
        tasks.add(new Task(id, name, description, "Pending"));
    }

    @Override
    public List<Task> getTasks() {
        return tasks;
    }

    @Override
    public void updateTaskStatus(int id, String status) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setStatus(status);
                break;
            }
        }
    }

    @Override
    public void removeTask(int id) {
        tasks.removeIf(task -> task.getId() == id);
    }
}
