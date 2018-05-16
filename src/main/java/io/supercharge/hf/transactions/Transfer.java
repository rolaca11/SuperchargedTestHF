package io.supercharge.hf.transactions;

import io.supercharge.hf.Session;
import io.supercharge.hf.entities.BankAccount;

public class Transfer implements Transaction {

    private Session session;

    private BankAccount from;
    private BankAccount to;

    private Integer amount;

    protected Transfer(Session session, BankAccount from, BankAccount to, Integer amount) {
        this.session = session;
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public static Transfer transfer(Session session, BankAccount from, BankAccount to, Integer amount) {
        return new Transfer(session, from, to, amount);
    }

    public void execute(boolean silent) {
        Deposit.to(session, amount, to).execute();
        Withdraw.from(session, amount, from).execute();
    }
}
