package banking;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Customer {

    private static int customerCounter = 1000;

    private final int customerId;

    private String name;
    private LocalDate dateOfBirth;
    private String phone;
    private String email;

    private Address address;

    private List<Account> accounts;


    public Customer(String name,
                    LocalDate dateOfBirth,
                    String phone,
                    String email,
                    Address address) {


        this.customerId = ++customerCounter;

        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.email = email;
        this.address = address;

        this.accounts = new ArrayList<>();

    }


    public void updateProfile(String name,
                              String phone,
                              String email,
                              Address address) {

        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;

    }


    public void viewProfile() {

        System.out.println("Customer ID: " + customerId);
        System.out.println("Name: " + name);
        System.out.println("DOB: " + dateOfBirth);
        System.out.println("Phone: " + phone);
        System.out.println("Email: " + email);
        System.out.println("City: " + address.getCity());

        System.out.println("Accounts:");

        for (Account account : accounts) {

            System.out.println("Account Number: " + account.getAccountNumber() +
                    "\n" + "Account Status: " + account.getStatus());

        }
    }


    public int getCustomerId() {

        return customerId;

    }

    public void addAccount(Account e){
        accounts.add(e);
    }

    public List<Account> getAccounts() {

        return accounts;

    }
}