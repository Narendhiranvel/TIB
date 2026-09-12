package banking.service;

import banking.enums.AccountStatus;
import banking.enums.AccountType;
import banking.model.*;
import banking.notification.NotificationService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Bank {

    private final List<Customer> customers;
    private final List<Account> accounts;

    private static int accountCounter = 100000;

    private final NotificationService notificationService;

    public Bank(NotificationService notificationService) {

        this.notificationService = notificationService;

        customers = new ArrayList<>();
        accounts = new ArrayList<>();
    }

    /**
     * Register a new customer
     */
    public Customer registerCustomer(String name, LocalDate dateOfBirth, String phone, String email, Address address) {

        Customer customer = new Customer(name, dateOfBirth, phone, email, address);

        customers.add(customer);

        notificationService.sendNotification(
                "Your customer account has been registered successfully."
        );

        System.out.println("Customer registered successfully.");

        return customer;
    }

    /**
     * Create a bank account for a customer
     */
    public Account createAccount(Customer customer,
                                 AccountType accountType,
                                 BigDecimal openingBalance) {

        String accountNumber = String.valueOf(++accountCounter);

        Account account;

        switch (accountType) {

            case SAVINGS:
                account = new SavingsAccount(
                        accountNumber,
                        openingBalance,
                        AccountStatus.ACTIVE
                );
                break;

            case CURRENT:
                account = new CurrentAccount(
                        accountNumber,
                        openingBalance,
                        AccountStatus.ACTIVE
                );
                break;

            case BUSINESS:
                account = new BusinessAccount(
                        accountNumber,
                        openingBalance,
                        AccountStatus.ACTIVE
                );
                break;

            default:
                throw new IllegalArgumentException(
                        "Unsupported account type: " + accountType
                );
        }

        accounts.add(account);

        // Associate account with customer
        customer.addAccount(account);

        notificationService.sendNotification(
                "Your account has been created successfully."
        );

        System.out.println("Account created successfully.");

        return account;
    }

    /**
     * Find account using account number
     */
    public Account findAccount(String accountNumber) {

        for (Account account : accounts) {

            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    /**
     * Display all customers
     */
    public void displayCustomers() {

        if (customers.isEmpty()) {
            System.out.println("No customers found.");
            return;
        }

        for (Customer customer : customers) {

            customer.viewProfile();
            System.out.println("-------------------------");
        }
    }

    /**
     * Close account
     */
    public void closeAccount(String accountNumber) {

        Account account = findAccount(accountNumber);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        account.setStatus(AccountStatus.CLOSED);

        notificationService.sendNotification(
                "Your account has been closed successfully"
        );

        System.out.println("Account closed successfully.");
    }
}
