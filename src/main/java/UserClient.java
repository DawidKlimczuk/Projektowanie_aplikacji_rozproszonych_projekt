import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

public class UserClient {
    public static void main(String[] args) {
        try {
            // Połączenie z serwerem
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            ServerInterface server = (ServerInterface) registry.lookup("Server");
            System.out.println("UserClient started...");

            Scanner scanner = new Scanner(System.in);
            boolean running = true;

            while (running) {
                System.out.println("\n--- Tasks ---");
                List<Task> tasks = server.getTasks();
                if (tasks.isEmpty()) {
                    System.out.println("No tasks available.");
                } else {
                    for (Task task : tasks) {
                        System.out.println(task);
                    }
                }

                System.out.println("\nOptions:");
                System.out.println("1. Update task status");
                System.out.println("2. Refresh task list");
                System.out.println("3. Exit");
                System.out.print("Select an option: ");
                String input = scanner.nextLine();

                switch (input) {
                    case "1": // Aktualizacja statusu zadania
                        System.out.print("Enter task ID to update: ");
                        String idInput = scanner.nextLine();
                        try {
                            int taskId = Integer.parseInt(idInput);
                            System.out.print("Enter new status: ");
                            String status = scanner.nextLine();
                            server.updateTaskStatus(taskId, status);
                            System.out.println("Task status updated.");
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid task ID.");
                        }
                        break;

                    case "2": // Odświeżanie listy zadań
                        System.out.println("Refreshing task list...");
                        // Właściwe dane zostaną ponownie pobrane w następnym przebiegu pętli
                        break;

                    case "3": // Wyjście
                        running = false;
                        System.out.println("Exiting UserClient...");
                        break;

                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }

        } catch (Exception e) {
            System.err.println("Unexpected error occurred.");
            e.printStackTrace();
        }
    }
}
