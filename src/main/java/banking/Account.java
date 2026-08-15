package banking;

import java.math.BigDecimal;

public class Account {

    private final String accountNumber;
    private AccountType accountType;
    private BigDecimal balance;
    private AccountStatus status;


    public Account(String accountNumber, AccountType accountType, BigDecimal balance) {

        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.status = AccountStatus.ACTIVE;
    }


    public void depositAmount(BigDecimal amount) {

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Invalid deposit amount");
            return;
        }

        this.balance = this.balance.add(amount);
    }


    public void withdrawAmount(BigDecimal amount) {

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


    public String getAccountNumber() {

        return accountNumber;

    }


    public AccountStatus getStatus() {

        return status;

    }


    public void setStatus(AccountStatus status) {

        this.status = status;

    }


    public AccountType getAccountType() {

        return accountType;

    }
}