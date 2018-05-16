package io.supercharge.hf.tasks;

import io.supercharge.hf.Session;
import io.supercharge.hf.entities.BankAccount;
import io.supercharge.hf.entities.EntityLoader;
import io.supercharge.hf.transactions.Transfer;

public class TransferTask extends AbstractTask {

    @Override
    public void execute(Session session) {
        EntityLoader<BankAccount> loader = EntityLoader.loadEntity(session, BankAccount.class);

        loader.setParameter("id", parameters.get(0));
        BankAccount from = loader.firstResult();

        loader.setParameter("id", parameters.get(1));
        BankAccount to = loader.firstResult();

        Transfer.transfer(session, from, to, Integer.valueOf(parameters.get(2))).execute();
    }
}
