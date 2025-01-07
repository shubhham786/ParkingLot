package models;

import Strategies.BillingStrategy;

public class Bill {
    Ticket ticket;            // Associated ticket
    double amount;            // Bill amount

    public Bill(Ticket ticket, double amount) {
        this.ticket = ticket;
        this.amount = amount;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "ticket=" + ticket +
                ", amount=" + amount +
                '}';
    }
}
