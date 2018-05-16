package io.supercharge.hf;

import io.supercharge.hf.entities.BankAccount;
import io.supercharge.hf.entities.Client;
import io.supercharge.hf.tasks.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static Session session = new Session();
    public static Map<String, Task> taskMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        Client client = new Client();
        BankAccount acc1 = new BankAccount();
        BankAccount acc2 = new BankAccount();

        client.addAccount(acc1);
        client.addAccount(acc2);

        acc1.setBalance(5000);
        acc2.setBalance(10000);

        session.persist(client);
        session.persist(acc1);
        session.persist(acc2);

        System.out.println("You, the Client have got 2 bank accounts, with IDs: 1, 2");
        System.out.println("It's up to you, what you do with them, you can deposit, withdraw, and transfer money between them");
        System.out.println("if you want to deposit money, you will have to type: deposit <AccuontID> <Amount> into the console");
        System.out.println("if you want to withdraw money, you will have to type: withdraw <AccuontID> <Amount> into the console");
        System.out.println("if you want to transfer money, you will have to type: transfer <AccountID-from> <AccountID-to> <Amount> into the console");
        System.out.println("You can check the balance of your accounts by typing getAccounts");
        System.out.println("You can view the history, by typing getHistory");
        System.out.println("\tIf you want to filter the results, you can filter by date, balance, amount, id (history ID)");
        System.out.println("\tby writing the name of the property after the command, separated by space. e.g: id=1");
        System.out.println("\tIf you want to filter by account, you can type account=<AccountID>");
        System.out.println("If you are finished, you can quit with the quit command");

        taskMap.put("deposit", new DepositTask());
        taskMap.put("withdraw", new WithdrawTask());
        taskMap.put("transfer", new TransferTask());
        taskMap.put("getHistory", new ListHistoryTask());
        taskMap.put("getAccounts", new ListAccountsTask());

        boolean running = true;
        while(running) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            running = doTask(br.readLine());
        }
    }

    public static boolean doTask(String command) {
        List<String> commandList = Arrays.stream(command.split(" ")).filter(Objects::nonNull).filter(f -> !f.isEmpty()).collect(Collectors.toList());

        if(commandList.get(0).equalsIgnoreCase("quit")) {
            return false;
        }

        List<String> paramList = commandList.subList(1, commandList.size());
        Task task = taskMap.get(commandList.get(0));

        task.setParameters(paramList.toArray(new String[0]));
        task.execute(session);

        return true;
    }
}
