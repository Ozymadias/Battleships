package battleships.communication;

public interface Publisher {
    public Messagable processRequest(Messagable event);
}
