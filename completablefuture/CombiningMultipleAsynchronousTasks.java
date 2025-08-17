package completablefuture;

import java.util.concurrent.CompletableFuture;

/**
 * anyOf() improves responsiveness by returning as soon as one task finishes.
 * allOf() is ideal for scenarios like report generation, where all data points are required.
 * Reduces unnecessary waiting in latency-sensitive applications.
 */
public class CombiningMultipleAsynchronousTasks {
    public static void main(String[] args) {
        CompletableFuture<String> service1 = CompletableFuture.supplyAsync(() -> {
            delay();
            return "Response from service 1";
        });

        CompletableFuture<String> service2 = CompletableFuture.supplyAsync(() -> {
            delay();
            return "Response from service 2";
        });

        CompletableFuture<Object> future = CompletableFuture.anyOf(service1, service2);

        System.out.println(future.join());
    }



    private static void delay() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
