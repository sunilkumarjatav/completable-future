package completablefuture;

import java.util.concurrent.CompletableFuture;
/**
 * thenCompose() flattens nested futures (CompletableFuture<CompletableFuture<T>> to CompletableFuture<T>).
 * Ideal for workflows where tasks are dependent on previous outcomes (e.g., login → fetch profile → fetch preferences).
 * Enhances readability and avoids callback hell.
 */
public class AsynchronousPipelinesThenCompose {
    public static CompletableFuture<String> authenticateUser(String user) {
        return CompletableFuture.supplyAsync(() -> {
            simulateDelay(1000);
            return "AuthTokenFor-" + user;
        });
    }
    public static CompletableFuture<String> fetchUserProfile(String token) {
        return CompletableFuture.supplyAsync(() -> {
            simulateDelay(1000);
            return "UserProfile for token: " + token;
        });
    }
    public static void main(String[] args) {
        CompletableFuture<String> userProfile = authenticateUser("JohnDoe")
                .thenCompose(token -> fetchUserProfile(token));
        System.out.println(userProfile.join());
    }
    private static void simulateDelay(int millis) {
        try { Thread.sleep(millis); } catch (InterruptedException e) { e.printStackTrace(); }
    }
}
