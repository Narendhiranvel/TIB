package banking.model;

import banking.enums.AccountStatus;
import banking.enums.AccountType;
import banking.enums.TransactionType;
import banking.exception.InActiveAccountException;
import banking.exception.InsufficientBalanceException;
import banking.repository.TransactionRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BusinessAccount extends Account {

    private static final BigDecimal MINIMUM_BALANCE = new BigDecimal("2000.00");
    private static final BigDecimal BUSINESS_ACCOUNT_INTEREST = new BigDecimal("3.00");
    TransactionRepository transactionRepository;

    public BusinessAccount(String accountNumber, BigDecimal balance, AccountStatus accountStatus, TransactionRepository transactionRepository) {
        super(accountNumber, AccountType.BUSINESS, balance, accountStatus, transactionRepository);
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
                    "Minimum balance of €2000 must be maintained."
            );
            return;
        }

        subtractBalance(amount);

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
