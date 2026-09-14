package banking.model;

import banking.enums.AccountStatus;
import banking.enums.AccountType;
import banking.exception.InsufficientBalanceException;

import java.math.BigDecimal;

public class SavingsAccount extends Account{

    private static final BigDecimal MINIMUM_BALANCE = new BigDecimal("500.00");
    private static final BigDecimal SAVINGS_ACCOUNT_INTEREST  = new BigDecimal("0.5");

    public SavingsAccount(String accountNumber, BigDecimal balance, AccountStatus accountStatus) {
        super(accountNumber, AccountType.SAVINGS, balance, accountStatus);
    }

    @Override
    public void withdrawAmount(BigDecimal amount) {

        if (!isActive()) {
            System.out.println(
                    "Can't withdraw amount, account is not active."
            );
            return;
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
    }
}
