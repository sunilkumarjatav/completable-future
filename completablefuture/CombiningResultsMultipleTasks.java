package completablefuture;

import java.util.concurrent.CompletableFuture;

/**
 * thenCombine() merges the results of two independent tasks.
 * Ideal for situations where tasks can run in parallel, but their results are needed together.
 */
public class CombiningResultsMultipleTasks {
    public static void main(String[] args) {
        CompletableFuture<String> availabilityFuture = CompletableFuture.supplyAsync(() -> {
            delay();
            return "Flight Available";
        });
        CompletableFuture<String> pricingFuture = CompletableFuture.supplyAsync(() -> {
            delay();
            return "Price: $350";
        });
        CompletableFuture<String> combinedFuture = availabilityFuture.thenCombine(pricingFuture,
                (availability, pricing) -> availability + " | " + pricing);
        System.out.println(combinedFuture.join());
    }

    private static void delay() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
