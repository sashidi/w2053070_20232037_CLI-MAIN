import java.util.Scanner;

public class Vendor implements Runnable {
    private final TicketPool ticketPool;
    private final int totalTickets;
    private final int releaseRate;

    public Vendor(TicketPool ticketPool, int totalTickets, int releaseRate) {
        this.ticketPool = ticketPool;
        this.totalTickets = totalTickets;
        this.releaseRate = releaseRate;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome Vendor! Please provide event details.");
        System.out.print("Enter event name: ");
        String eventName = scanner.nextLine();

        // Start adding tickets to the pool
        for (int i = 1; i <= totalTickets; i++) {
            Ticket ticket = new Ticket(i, eventName);
            if (ticketPool.addTicket(ticket)) {
                System.out.println("Ticket added: " + ticket);
            } else {
                System.out.println("Ticket pool is full. Cannot add more tickets.");
                break;
            }
            try {
                Thread.sleep(releaseRate); // Wait before adding the next ticket
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
