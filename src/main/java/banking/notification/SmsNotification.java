package banking.notification;

public class SmsNotification implements NotificationService{

    @Override
    public void sendNotification(String message) {
        System.out.println("Sending SMS Notification: " + message);
    }
}
