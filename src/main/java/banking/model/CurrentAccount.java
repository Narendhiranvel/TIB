package banking.model;

import banking.enums.AccountStatus;
import banking.enums.AccountType;
import banking.enums.TransactionType;
import banking.exception.InActiveAccountException;
import banking.exception.InsufficientBalanceException;
import banking.repository.TransactionRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CurrentAccount extends Account{

    private static final BigDecimal OVERDRAFT_AMOUNT = new BigDecimal("1000.00");
    TransactionRepository transactionRepository;

    public CurrentAccount(String accountNumber, BigDecimal balance, AccountStatus accountStatus, TransactionRepository transactionRepository) {
        super(accountNumber, AccountType.CURRENT, balance, accountStatus, transactionRepository);
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

        BigDecimal maximumWithdrawal =
                checkBalance().add(OVERDRAFT_AMOUNT);

        if (amount.compareTo(maximumWithdrawal) > 0) {
            throw new InsufficientBalanceException(
                    "Insufficient balance, Withdrawal exceeds the overdraft limit of €1000."
            );
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
