package banking.model;

import banking.enums.AccountStatus;
import banking.enums.AccountType;
import banking.enums.TransactionType;
import banking.exception.InActiveAccountException;
import banking.repository.TransactionRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Account {

    private final String accountNumber;
    private final AccountType accountType;
    private BigDecimal balance;
    private AccountStatus status;

//    Account can have multiple transactions
    private final List<Transaction> transactionDetails;

    private final TransactionRepository transactionRepository;


    public Account(String accountNumber,
                   AccountType accountType,
                   BigDecimal balance,
                   AccountStatus accountStatus,
                   TransactionRepository transactionRepository) {

        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.status = accountStatus;
        this.transactionDetails = new ArrayList<>();
        this.transactionRepository = transactionRepository;
    }
//    Protected helper method to add transaction to the list
    protected void addTransaction(Transaction transaction){
        transactionDetails.add(transaction);
        transactionRepository.save(transaction);
    }

    // Protected helper methods
    protected boolean isActive() {
        return this.status == AccountStatus.ACTIVE;
    }

    protected boolean isValidAmount(BigDecimal amount) {
        return amount.compareTo(BigDecimal.ZERO) > 0;
    }

    protected boolean hasSufficientBalance(BigDecimal amount) {
        return amount.compareTo(balance) <= 0;
    }

    protected void subtractBalance(BigDecimal amount) {
        this.balance = this.balance.subtract(amount);
    }

    protected void addBalance(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    // Common behaviour for all account types

    public void depositAmount(BigDecimal amount) {

        if (!isActive()) {
            throw new InActiveAccountException(
                    "Can't deposit, Your account is not Active"
            );
        }

        if (!isValidAmount(amount)) {
            System.out.println("Invalid deposit amount");
            return;
        }

        addBalance(amount);

        // Create a transaction record for the deposit
        Transaction transaction = new Transaction(
                accountNumber,
                TransactionType.DEPOSIT,
                amount,
                balance,
                LocalDateTime.now()
        );

        addTransaction(transaction);
        transaction.printTransaction();
    }

    // Abstract behaviour
    // Every account type must define its own withdrawal rules

    public abstract void withdrawAmount(BigDecimal amount);

    public BigDecimal checkBalance() {
        return this.balance;
    }

    public void viewAccountDetails() {

        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Type: " + accountType);
        System.out.println("Balance: " + balance);
        System.out.println("Status: " + status);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    // Method to record the opening balance as a transaction
    public void recordOpeningBalance() {

        Transaction transaction = new Transaction(
                accountNumber,
                TransactionType.OPENING_BALANCE,
                balance,
                balance,
                LocalDateTime.now()
        );

        addTransaction(transaction);
    }
}