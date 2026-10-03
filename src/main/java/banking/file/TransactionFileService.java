package banking.file;

import banking.model.Transaction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;

public class TransactionFileService {

    private final Path transactionDirectory;

    public TransactionFileService() {
        this.transactionDirectory = Path.of("transactions");
    }

    public void createTransactionDirectory() {
        try {
            Files.createDirectories(transactionDirectory);
        } catch (IOException e) {
            System.out.println("Failed to create transaction directory: " + e.getMessage());
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
}
