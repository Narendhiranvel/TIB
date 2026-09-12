package banking.model;

import banking.enums.AccountStatus;
import banking.enums.AccountType;

import java.math.BigDecimal;

public class BusinessAccount extends Account{

    private static final BigDecimal MINIMUM_BALANCE = new BigDecimal("2000.00");
    private static final BigDecimal BUSINESS_ACCOUNT_INTEREST  = new BigDecimal("3.00");

    public BusinessAccount(String accountNumber, BigDecimal balance, AccountStatus accountStatus) {
        super(accountNumber, AccountType.BUSINESS, balance, accountStatus);
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
            System.out.println("Insufficient balance");
            return;
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
    }
}
