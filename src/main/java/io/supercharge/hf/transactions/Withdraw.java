package io.supercharge.hf.transactions;

import io.supercharge.hf.Session;
import io.supercharge.hf.entities.BankAccount;
import io.supercharge.hf.entities.History;

import java.util.Date;

public class Withdraw implements Transaction {

    private Session session;

    private Integer amount;

    private BankAccount account;

    protected Withdraw(Session session, Integer amount, BankAccount account) {
        this.session = session;
        this.account = account;
        this.amount = amount;
    }

    public static Withdraw from(Session session, Integer amount, BankAccount account) {
        return new Withdraw(session, amount, account);
    }

    public void execute(boolean silent) {
        account.setBalance(account.getBalance() - amount);
        session.update(account);

        if(!silent) {
            History history = new History();

            history.setAccount(account);
            history.setAmount(-1 * amount);
            history.setBalance(account.getBalance());
            history.setDate(new Date(System.currentTimeMillis()));

            session.persist(history);
        }
    }
}
