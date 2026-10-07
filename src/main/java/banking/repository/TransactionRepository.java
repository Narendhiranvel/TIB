package banking.repository;

import banking.model.Transaction;

public interface TransactionRepository {

    void save (Transaction transaction);
}
