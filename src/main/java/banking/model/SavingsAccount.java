package banking.model;

import banking.enums.AccountStatus;
import banking.enums.AccountType;
import banking.enums.TransactionType;
import banking.exception.InActiveAccountException;
import banking.exception.InsufficientBalanceException;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SavingsAccount extends Account{

    private static final BigDecimal MINIMUM_BALANCE = new BigDecimal("500.00");
    private static final BigDecimal SAVINGS_ACCOUNT_INTEREST  = new BigDecimal("0.5");

    public SavingsAccount(String accountNumber, BigDecimal balance, AccountStatus accountStatus) {
        super(accountNumber, AccountType.SAVINGS, balance, accountStatus);
    }

    @Override
    public void withdrawAmount(BigDecimal amount) {

        if (!isActive()) {
            throw new InActiveAccountException(
                    "Can't withdraw Your Savings account is not Active"
            );
        }

        if (!isValidAmount(amount)) {
            System.out.println("Invalid withdrawal amount");
            return;
        }

        if (!hasSufficientBalance(amount)) {
            throw new InsufficientBalanceException(
                    "Insufficient balance"
            );
        }

        BigDecimal remainingBalance =
                checkBalance().subtract(amount);

        if (remainingBalance.compareTo(MINIMUM_BALANCE) < 0) {
            System.out.println(
                    "Minimum balance of €500 must be maintained."
            );
            return;
        }

        subtractBalance(amount);

        // Create a transaction for the withdrawal
        Transaction transaction = new Transaction(
                getAccountNumber(),
                TransactionType.WITHDRAWAL,
                amount,
                checkBalance(),
                LocalDateTime.now()
        );

        addTransaction(transaction);
    }
}
