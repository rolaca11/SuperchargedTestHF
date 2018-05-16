package io.supercharge.hf.transactions;

public interface Transaction {

    void execute(boolean silent);

    default void execute() {
        execute(false);
    }
}
