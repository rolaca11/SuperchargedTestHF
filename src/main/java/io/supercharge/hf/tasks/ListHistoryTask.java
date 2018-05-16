package io.supercharge.hf.tasks;

import io.supercharge.hf.Session;
import io.supercharge.hf.entities.BankAccount;
import io.supercharge.hf.entities.EntityLoader;
import io.supercharge.hf.entities.History;

import java.util.AbstractMap;
import java.util.List;
import java.util.Objects;

public class ListHistoryTask extends AbstractTask {

    @Override
    public void execute(Session session) {
        EntityLoader<History> loader = EntityLoader.loadEntity(session, History.class);

        parameters.stream()
                .filter(Objects::nonNull)
                .filter(f -> f.contains("="))
                .map(m -> {
                    String[] split = m.split("=");
                    return new AbstractMap.SimpleEntry<String, Object>(split[0].trim(), split[1].trim());
                }).map(m -> {
                    if(m.getKey().equalsIgnoreCase("account")) {
                        EntityLoader<BankAccount> accountEntityLoader = EntityLoader.loadEntity(session, BankAccount.class);
                        accountEntityLoader.setParameter("id", m.getValue());
                        return new AbstractMap.SimpleEntry<String, Object>(m.getKey(), accountEntityLoader.firstResult());
                    } else {
                        return m;
                    }
                }).forEach(s -> loader.setParameter(s.getKey(), s.getValue()));

        List<History> histories = loader.resultList();

        for(History history: histories) {
            System.out.println(history);
        }
    }
}
