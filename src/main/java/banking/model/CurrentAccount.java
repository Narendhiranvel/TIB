package banking.model;

import banking.enums.AccountStatus;
import banking.enums.AccountType;

import java.math.BigDecimal;

public class CurrentAccount extends Account{

    private static final BigDecimal OVERDRAFT_AMOUNT = new BigDecimal("1000.00");

    public CurrentAccount(String accountNumber, BigDecimal balance, AccountStatus accountStatus) {
        super(accountNumber, AccountType.CURRENT, balance, accountStatus);
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

        BigDecimal maximumWithdrawal =
                checkBalance().add(OVERDRAFT_AMOUNT);

        if (amount.compareTo(maximumWithdrawal) > 0) {
            System.out.println(
                    "Withdrawal exceeds the overdraft limit of €1000."
            );
            return;
        }
        subtractBalance(amount);
    }
}
