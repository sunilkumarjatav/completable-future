package completablefuture;

import java.util.concurrent.CompletableFuture;

/**
 * Each task depends on the result of the previous one.
 * The flow is non-blocking, improving system responsiveness.
 * thenApply() is used to transform the result and pass it to the next stage.
 */

public class ChainingDependentTasks {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> validateOrder("order123"))
                .thenApply(order -> processOrder(order))
                .thenApply(paymentStatus -> sendConfirmationMail(paymentStatus))
                .thenAccept(result -> System.out.println(result))
                .join();
    }

    private static String validateOrder(String order) {
        delay();
        System.out.println(order + " validated");
        return order;
    }

    private static String processOrder(String order) {
        delay();
        System.out.println("Payment processed for: " + order);
        return "payment successful for " + order;
    }

    private static String sendConfirmationMail(String paymentStatus) {
        delay();
        return "Confirmation email sent " + paymentStatus;

    }

    private static void delay() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
