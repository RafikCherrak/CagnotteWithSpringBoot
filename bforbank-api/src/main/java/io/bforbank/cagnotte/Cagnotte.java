package io.bforbank.cagnotte;

public class Cagnotte {
    private String clientId;
    private double totalAmount;
    private int transactionCount;

    public Cagnotte(String clientId, double totalAmount, int transactionCount) {
        this.clientId = clientId;
        this.totalAmount = totalAmount;
        this.transactionCount = transactionCount;
    }

    // Getters and Setters

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(int transactionCount) {
        this.transactionCount = transactionCount;
    }

    public boolean isAvailable() {
        return transactionCount >= 3 && totalAmount >= 10;
    }
}
