package banking;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        //Create Bank
        Bank bank = new Bank(new EmailNotification());

        //Create Address
        Address address = new Address(
                6,
                "Spring Fort St",
                "Naas",
                "Ireland"
        );

        //Register Customer through Bank
        Customer customer = bank.registerCustomer(
                "Narendhiran",
                LocalDate.of(1998, 5, 20),
                "+353871234567",
                "naren@gmail.com",
                address
        );

        //Create Account for customer
        Account account = bank.createAccount(
                customer,
                AccountType.SAVINGS,
                new BigDecimal("5000")
        );

        //Account Operation
        account.depositAmount(
                new BigDecimal("1000")
        );

        account.withdrawAmount(
                new BigDecimal("500")
        );

        customer.viewProfile();

        // 7. Check Balance
        System.out.println(
                account.checkBalance()
        );

//      can't withdraw amount - Minimum balance should be maintained
        account.withdrawAmount(
                new BigDecimal("5500")
        );

        bank.closeAccount(account.getAccountNumber());

//        Can't deposit after closing
        account.depositAmount(
                new BigDecimal("1000")
        );

//        Can't withdraw after closing
        account.withdrawAmount(
                new BigDecimal("500")
        );

        customer.viewProfile();
    }
}
