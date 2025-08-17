package completablefuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * handle() processes both success and failure, making it versatile for post-processing.
 * whenComplete() is useful for logging or side effects without altering the result.
 * Ensures graceful degradation of services in case of failures.
 */
public class ExceptionHandling {
    public static void main(String[] args) {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            if (new Random().nextBoolean()) {
                throw new RuntimeException("Unexpected Error!");
            }
            return "Success!";
        });

        future.handle((result, ex) -> {
            if (ex != null) {
                System.out.println("Error occurred: " + ex.getMessage());
                return "Default Value";
            }
            return result;
        }).thenAccept(System.out::println);
    }
}
