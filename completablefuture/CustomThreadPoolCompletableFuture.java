package completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Use custom thread pools for better performance, especially in high-concurrency applications.
 * Prevents thread starvation in critical system components.
 * Ideal for segregating CPU-bound and I/O-bound tasks.
 */
public class CustomThreadPoolCompletableFuture {
    private static final ExecutorService customExecutor = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {
        CompletableFuture.runAsync(() -> {
            System.out.println("Running in custom thread pool " + Thread.currentThread().getName());
        }, customExecutor).join();

        customExecutor.shutdown();
    }
}
