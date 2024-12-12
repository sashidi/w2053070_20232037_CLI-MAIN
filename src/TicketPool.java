import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private final int maxCapacity;
    private final Queue<Ticket> tickets;

    public TicketPool(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.tickets = new LinkedList<>();
    }

    // Vendor can only add tickets (not customers).
    public synchronized boolean addTicket(Ticket ticket) {
        if (tickets.size() < maxCapacity) {
            tickets.add(ticket);
            return true;
        }
        return false;
    }

    // Customers can only remove tickets (buy them).
    public synchronized Ticket removeTicket() {
        return tickets.poll(); // Removes one ticket for the customer.
    }

    public synchronized int getSize() {
        return tickets.size();
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }
}
