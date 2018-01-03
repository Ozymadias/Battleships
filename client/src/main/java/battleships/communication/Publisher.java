package battleships.communication;

interface Publisher {
  Messageable processRequest(Messageable event);
}
