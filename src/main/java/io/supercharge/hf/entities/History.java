package io.supercharge.hf.entities;


import java.util.Date;

public class History implements Entity {

    private static Integer nextId = 0;

    public History() {
        id = nextId++;
    }

    private Integer id;

    private Date date;

    private Integer amount;

    private Integer balance;

    private BankAccount account;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public BankAccount getAccount() {
        return account;
    }

    public void setAccount(BankAccount account) {
        this.account = account;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("ID: ");
        builder.append(id);

        builder.append(", Balance: ");
        builder.append(balance);

        builder.append(", Date: ");
        builder.append(date);

        builder.append(", Account: ");
        builder.append(account);

        builder.append(", Amount: ");
        builder.append(amount);

        return builder.toString();
    }
}
