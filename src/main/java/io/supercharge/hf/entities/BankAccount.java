package io.supercharge.hf.entities;



public class BankAccount implements Entity{

    private static Integer nextId = 0;

    public BankAccount() {
        id = nextId++;
    }

    private Integer id;

    private Integer balance;

    private Client owner;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Client getOwner() {
        return owner;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("ID: ");
        builder.append(id);

        builder.append(", Balance: ");
        builder.append(balance);

        return builder.toString();
    }
}
