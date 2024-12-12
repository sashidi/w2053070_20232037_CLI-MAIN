import java.util.Scanner;

public class Customer implements Runnable {
    private final TicketPool ticketPool;

    public Customer(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        // Check if tickets are available
        if (ticketPool.getSize() > 0) {
            System.out.println("\nTickets are available. Please enter the number of tickets you wish to purchase:");
            int ticketsToBuy = scanner.nextInt();

            // Check if enough tickets are available
            if (ticketsToBuy <= ticketPool.getSize()) {
                System.out.println("Purchasing " + ticketsToBuy + " tickets...");
                for (int i = 0; i < ticketsToBuy; i++) {
                    Ticket purchasedTicket = ticketPool.removeTicket();
                    if (purchasedTicket != null) {
                        System.out.println("Ticket " + purchasedTicket.getTicketId() + " purchased!");
                    }
                }
            } else {
                System.out.println("Not enough tickets available.");
            }
        } else {
            System.out.println("No tickets available.");
        }
    }
}
