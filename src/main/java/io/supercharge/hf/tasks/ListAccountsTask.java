package io.supercharge.hf.tasks;

import io.supercharge.hf.Session;
import io.supercharge.hf.entities.BankAccount;
import io.supercharge.hf.entities.EntityLoader;

import java.util.List;

public class ListAccountsTask extends AbstractTask {

    @Override
    public void execute(Session session) {
        List<BankAccount> accounts = EntityLoader.loadEntity(session, BankAccount.class).resultList();

        for(BankAccount account: accounts) {
            System.out.println(account);
        }
    }
}
