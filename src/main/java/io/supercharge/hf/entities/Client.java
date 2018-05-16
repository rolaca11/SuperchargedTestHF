package io.supercharge.hf.entities;


import java.util.ArrayList;
import java.util.List;

public class Client implements Entity {

    private static Integer nextId = 0;

    public Client() {
        id = nextId++;
    }

    private Integer id;

    private List<BankAccount> accountList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<BankAccount> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<BankAccount> accountList) {
        this.accountList = accountList;
    }

    public void addAccount(BankAccount account) {
        accountList = accountList == null ? new ArrayList<BankAccount>() : accountList;

        accountList.add(account);

        account.setOwner(this);
    }

    public void removeAccount(BankAccount account) {
        if(accountList == null)
            return;

        accountList.remove(account);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("ID: ");
        builder.append(id);

        for(BankAccount account: accountList) {
            builder.append(", Account: ");
            builder.append(account);
        }

        return builder.toString();
    }
}
