package banking;

import java.math.BigDecimal;

public class Account {

    private final String accountNumber;
    private AccountType accountType;
    private BigDecimal balance;
    private AccountStatus status;


    public Account(String accountNumber, AccountType accountType, BigDecimal balance, AccountStatus accountStatus) {

        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.status = accountStatus;
    }

    public boolean isActive() {
        return this.status == AccountStatus.ACTIVE;
    }

    public void depositAmount(BigDecimal amount) {

        if (!isActive()) {
            System.out.println("Can't deposit amount, account is not active.");
            return;
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Invalid deposit amount");
            return;
        }

        this.balance = this.balance.add(amount);
    }


    public void withdrawAmount(BigDecimal amount) {

        if (!isActive()) {
            System.out.println("Can't withdraw amount, account is not active.");
            return;
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Invalid withdrawal amount");
            return;
        }

        if (amount.compareTo(balance) > 0) {
            System.out.println("Insufficient balance");
            return;
        }

        this.balance = this.balance.subtract(amount);
    }


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

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
}