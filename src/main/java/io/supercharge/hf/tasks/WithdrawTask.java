package io.supercharge.hf.tasks;

import io.supercharge.hf.Session;
import io.supercharge.hf.entities.BankAccount;
import io.supercharge.hf.entities.EntityLoader;
import io.supercharge.hf.transactions.Withdraw;

public class WithdrawTask extends AbstractTask {

    @Override
    public void execute(Session session) {
        EntityLoader<BankAccount> loader = EntityLoader.loadEntity(session, BankAccount.class);
        loader.setParameter("id", parameters.get(0));

        BankAccount account = loader.firstResult();

        Withdraw.from(session, Integer.valueOf(parameters.get(1)), account).execute();
    }
}
