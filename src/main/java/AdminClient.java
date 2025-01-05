import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

public class AdminClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(1099);
            ServerInterface server = (ServerInterface) registry.lookup("Server");
            System.out.println("AdminClient started. Connected to Server.");

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\nTasks:");
                List<Task> tasks = server.getTasks();
                if (tasks.isEmpty()) {
                    System.out.println("No tasks available.");
                } else {
                    for (Task task : tasks) {
                        System.out.println(task);
                    }
                }

                System.out.println("\nOptions:");
                System.out.println("1. Add Task");
                System.out.println("2. Remove Task");
                System.out.println("3. Refresh Tasks");
                System.out.println("q. Quit");
                System.out.print("Choose an option: ");
                String choice = scanner.nextLine();

                if ("q".equalsIgnoreCase(choice)) {
                    break;
                }

                switch (choice) {
                    case "1":
                        System.out.print("Enter task name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter task description: ");
                        String description = scanner.nextLine();
                        server.addTask(name, description);
                        System.out.println("Task added: " + name);
                        break;

                    case "2":
                        System.out.print("Enter task ID to remove: ");
                        int idToRemove = Integer.parseInt(scanner.nextLine());
                        server.removeTask(idToRemove);
                        System.out.println("Task removed.");
                        break;

                    case "3":
                        System.out.println("Refreshing tasks...");
                        // Tasks are refreshed automatically
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }
            }

        } catch (Exception e) {
            System.err.println("Unexpected error occurred.");
            e.printStackTrace();
        }
    }
}
