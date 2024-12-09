package com.financemanager.personalfinancemanager.transaction;

import java.time.LocalDate;
public class TransactionItem {

    private LocalDate date;
    private double amount;
    private String category;
    private Boolean type;
    private boolean recurring;

    public TransactionItem() {
    }

    public TransactionItem(LocalDate date, double amount, String category, Boolean type, boolean recurring) {
        this.date = date;
        this.amount = amount;
        this.category = category;
        this.type = type;
        this.recurring = recurring;
    }

    @Override
    public String toString() {
        return "Transaction {\n" +
                "  date='" + date + "',\n" +
                "  amount=" + amount + ",\n" +
                "  category='" + category + "',\n" +
                "  type='" + type + "',\n" +
                "  recurring='" + recurring + "'\n" +
                '}';
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public boolean isRecurring() {
        return recurring;
    }

    public void setRecurring(boolean recurring) {
        this.recurring = recurring;
    }
}
