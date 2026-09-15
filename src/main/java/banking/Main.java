package banking;

import banking.enums.AccountType;
import banking.exception.InsufficientBalanceException;
import banking.model.Account;
import banking.model.Address;
import banking.model.Customer;
import banking.notification.EmailNotification;
import banking.notification.SmsNotification;
import banking.service.Bank;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        System.out.println("---------------------------SAVINGS ACCOUNT---------------------------------");
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

        // Can't withdraw - Insufficient balance
        try {
            account.withdrawAmount(new BigDecimal("6001.00"));
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }

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

        System.out.println();
        System.out.println("---------------------------BUSINESS ACCOUNT---------------------------------");

        //Create Bank
        Bank bank2 = new Bank(new SmsNotification());

        //Create Address
        Address address2 = new Address(
                3,
                "Abbey street",
                "Kildare",
                "Ireland"
        );

        //Register Customer through Bank
        Customer customer2 = bank2.registerCustomer(
                "Kevin",
                LocalDate.of(1996, 2, 13),
                "+3537845673412",
                "kevin@gmail.com",
                address2
        );

        //Create Account for customer
        Account account2 = bank2.createAccount(
                customer2,
                AccountType.BUSINESS,
                new BigDecimal("10000")
        );

        //Account Operation
        account2.depositAmount(
                new BigDecimal("1000")
        );

        // Can't withdraw - Insufficient balance
        try {
            account2.withdrawAmount(new BigDecimal("12000.00"));
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }

        customer2.viewProfile();

        // 7. Check Balance
        System.out.println(
                account2.checkBalance()
        );

//      can't withdraw amount - Minimum balance should be maintained
        account2.withdrawAmount(
                new BigDecimal("9500")
        );

        bank2.closeAccount(account2.getAccountNumber());

//        Can't deposit after closing
        account2.depositAmount(
                new BigDecimal("1000")
        );

//        Can't withdraw after closing
        account2.withdrawAmount(
                new BigDecimal("500")
        );

        customer2.viewProfile();
    }
}
