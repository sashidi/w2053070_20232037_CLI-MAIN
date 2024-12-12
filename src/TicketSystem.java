import java.util.Scanner;

public class TicketSystem {
    private static int getValidInput(String prompt, int min, int max) {
        Scanner scanner = new Scanner(System.in);
        int input;
        while (true) {
            System.out.print(prompt);
            try {
                input = Integer.parseInt(scanner.nextLine());
                if (input >= min && input <= max) {
                    return input;
                } else {
                    System.out.println("Please enter a value between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer.");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // User chooses the role (Vendor or Customer)
        System.out.println("Choose Role: ");
        System.out.println("1. Vendor");
        System.out.println("2. Customer");
        int roleChoice = getValidInput("Enter your role (1 for Vendor, 2 for Customer): ", 1, 2);

        // Collect user inputs for system configuration
        int totalTickets = getValidInput("Enter the total number of tickets: ", 1, 1000);
        int ticketReleaseRate = getValidInput("Enter the ticket release rate (in milliseconds): ", 100, 5000);
        int maxTicketCapacity = getValidInput("Enter the maximum ticket capacity: ", 1, 100);

        // Initialize ticket pool
        TicketPool ticketPool = new TicketPool(maxTicketCapacity);

        // Based on the role chosen, either start Vendor or Customer
        if (roleChoice == 1) {
            // Start Vendor thread to add tickets to the pool
            Thread vendorThread = new Thread(new Vendor(ticketPool, totalTickets, ticketReleaseRate));
            vendorThread.start();

            // Real-time monitoring of the ticket pool (loop)
            try {
                while (true) {
                    // Stop monitoring if no tickets are available
                    if (ticketPool.getSize() == 0) {
                        System.out.println("\nNo tickets available. Stopping real-time monitoring.");
                        break;  // Exit the loop
                    }

                    System.out.println("\nReal-time Ticket Pool Status:");
                    System.out.println("Tickets Available: " + ticketPool.getSize());
                    System.out.println("Ticket Pool Capacity: " + ticketPool.getMaxCapacity());
                    Thread.sleep(2000); // Monitor status every 2 seconds
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (roleChoice == 2) {
            // Customer role
            Thread customerThread = new Thread(new Customer(ticketPool));
            customerThread.start();
        }
    }
}
