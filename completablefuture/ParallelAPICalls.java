package completablefuture;

import java.util.concurrent.CompletableFuture;

/**
 * All API calls are executed in parallel, reducing total response time from ~3 seconds (sequential) to ~1 second.
 * CompletableFuture.allOf() waits for all tasks to complete.
 */

public class ParallelAPICalls {
    private static CompletableFuture<String> getProductDetails() {
        return CompletableFuture.supplyAsync(() -> {
            delay();
            return "Product details";
        });
    }

    private static CompletableFuture<String> getPricing() {
      return CompletableFuture.supplyAsync(() -> {
          delay();
          return "Price details";
      });
    }

    private static CompletableFuture<String> getInventory() {
        return CompletableFuture.supplyAsync(() -> {
            delay();
            return "Inventory details";
        });
    }

    private static void delay() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
      CompletableFuture<String> productDetails = getProductDetails();
      CompletableFuture<String> priceDetails = getPricing();
      CompletableFuture<String> inventoryDetails = getInventory();

      CompletableFuture<Void> allOf = CompletableFuture.allOf(productDetails, priceDetails, inventoryDetails);

      allOf.thenRun(() -> {
          try {
              System.out.println(productDetails.get());
              System.out.println(priceDetails.get());
              System.out.println(inventoryDetails.get());
          } catch (Exception e) {
              e.printStackTrace();
          }
      }).join();

    }
}
