public class Ticket {
    private final int ticketId;
    private final String eventName;

    public Ticket(int ticketId, String eventName) {
        this.ticketId = ticketId;
        this.eventName = eventName;
    }

    public int getTicketId() {
        return ticketId;
    }

    public String getEventName() {
        return eventName;
    }

    @Override
    public String toString() {
        return "Ticket ID: " + ticketId + " | Event: " + eventName;
    }
}
