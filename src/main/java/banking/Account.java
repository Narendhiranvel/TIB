package banking;

import java.math.BigDecimal;

public abstract class Account {

    private final String accountNumber;
    private final AccountType accountType;
    private BigDecimal balance;
    private AccountStatus status;


    public Account(String accountNumber, AccountType accountType, BigDecimal balance, AccountStatus accountStatus) {

        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.status = accountStatus;
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
            System.out.println(
                    "Can't deposit amount, account is not active."
            );
            return;
        }

        if (!isValidAmount(amount)) {
            System.out.println("Invalid deposit amount");
            return;
        }

        addBalance(amount);
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
}