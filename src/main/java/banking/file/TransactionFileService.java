package banking.file;

import banking.enums.TransactionType;
import banking.model.Transaction;
import banking.repository.TransactionRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionFileService implements TransactionRepository {

    private final Path transactionDirectory;

    public TransactionFileService() {
        this.transactionDirectory = Path.of("transactions");
    }

    public void createTransactionDirectory() {
        try {
            Files.createDirectories(transactionDirectory);
        } catch (IOException e) {
            System.out.println(
                    "Failed to create transaction directory: " + e.getMessage()
            );
        }
    }

    public Path getDailyTransactionFile() {
        LocalDate today = LocalDate.now();
        String fileName = today.toString() + ".txt";

        return transactionDirectory.resolve(fileName);
    }

    public void writeTransactionToFile(Transaction transaction) {

        String transactionRecord = transaction.getTransactionId() + "," +
                transaction.getAccountNumber() + "," +
                transaction.getTransactionType() + "," +
                transaction.getAmount() + "," +
                transaction.getBalanceAfterTransaction() + "," +
                transaction.getTransactionDateTime() +
                System.lineSeparator();

        Path file = getDailyTransactionFile();

        try {
            Files.writeString(file,
                    transactionRecord,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);

        } catch (IOException e) {
            System.out.println("Failed to write transaction: " + e.getMessage());
        }
    }

    public List<Transaction> readDailyTransactions() {

        Path file = getDailyTransactionFile();

        List<Transaction> transactions = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(file);

            for (String line : lines) {
                Transaction transaction = parseTransaction(line);
                transactions.add(transaction);
            }

            return transactions;

        } catch (IOException e) {
            System.out.println(
                    "Failed to read transactions: " + e.getMessage()
            );

            return List.of();
        }
    }

    public Transaction parseTransaction(String line) {
        String[] fields = line.split(",");

        if (fields.length != 6) {
            throw new IllegalArgumentException(
                    "Invalid transaction record: " + line
            );
        }

        String transactionId = fields[0];
        String accountNumber = fields[1];
        TransactionType transactionType = TransactionType.valueOf(fields[2]);
        BigDecimal amount = new BigDecimal(fields[3]);
        BigDecimal balanceAfterTransaction = new BigDecimal(fields[4]);
        LocalDateTime dateTime = LocalDateTime.parse(fields[5]);

        return new Transaction(
                transactionId,
                accountNumber,
                transactionType,
                amount,
                balanceAfterTransaction,
                dateTime
        );
    }

    @Override
    public void save(Transaction transaction) {
        createTransactionDirectory();
        writeTransactionToFile(transaction);
    }
}
