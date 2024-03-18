package ru.job4j.cash;

import java.util.HashMap;
import java.util.Optional;

public class AccountStorage {
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        return accounts.putIfAbsent(account.id(), account) == null;
    }

    public synchronized boolean update(Account account) {
        return accounts.replace(account.id(), account) != null;
    }

    public synchronized void delete(int id) {
        accounts.remove(id);
    }

    public synchronized Optional<Account> getById(int id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        Optional<Account> fromAccount = this.getById(fromId);
        Optional<Account> toAccount = this.getById(toId);
        boolean check = fromAccount.isEmpty() || toAccount.isEmpty() || fromAccount.get().amount() < amount;
        if (!check) {
            this.update(new Account(fromAccount.get().id(), fromAccount.get().amount() - amount));
            this.update(new Account(toAccount.get().id(), toAccount.get().amount() + amount));
        }
        return check;
    }
}
