package battleships.communication;

interface Publisher {
    Messagable processRequest(Messagable event);
}
