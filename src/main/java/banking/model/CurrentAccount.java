package banking.model;

import banking.enums.AccountStatus;
import banking.enums.AccountType;
import banking.exception.InActiveAccountException;
import banking.exception.InsufficientBalanceException;

import java.math.BigDecimal;

public class CurrentAccount extends Account{

    private static final BigDecimal OVERDRAFT_AMOUNT = new BigDecimal("1000.00");

    public CurrentAccount(String accountNumber, BigDecimal balance, AccountStatus accountStatus) {
        super(accountNumber, AccountType.CURRENT, balance, accountStatus);
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
    }
}
