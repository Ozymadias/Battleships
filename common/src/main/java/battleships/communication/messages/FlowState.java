package battleships.communication.messages;

/**
 * Represents state of game flow, such as:
 * one client disconnect, everything is fine, etc.
 */
public enum FlowState {
    CLIENT_DISCONNECT, RUNNING
}
