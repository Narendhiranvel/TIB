package banking.model;

import banking.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {

    private static int transactionCounter = 1000;

    private final String transactionId;
    private final String accountNumber;
    private final TransactionType transactionType;
    private final BigDecimal amount;
    private final BigDecimal balanceAfterTransaction;
    private final LocalDateTime transactionDateTime;


    public Transaction(String accountNo, TransactionType transactionType, BigDecimal amount, BigDecimal balanceAfterTransaction, LocalDateTime transactionDateTime) {
        this.transactionId = "TXN" + (++transactionCounter);
        this.accountNumber = accountNo;
        this.transactionType = transactionType;
        this.amount = amount;
        this.balanceAfterTransaction = balanceAfterTransaction;
        this.transactionDateTime = transactionDateTime;
    }

    public void printTransaction() {
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Transaction Type: " + transactionType);
        System.out.println("Amount: " + amount);
        System.out.println("Balance: " + balanceAfterTransaction);
        System.out.println("Date & Time: " + transactionDateTime);
    }

    public static int getTransactionCounter() {
        return transactionCounter;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getBalance() {
        return balanceAfterTransaction;
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }
}
