package io.bforbank.cagnotte;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(String clientId) {
        super("Client not found with ID: " + clientId);
    }
}
