package multithreading;

import java.util.concurrent.*;

public class CallableExample {
    private static final ExecutorService service = Executors.newSingleThreadExecutor();
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> task = () -> {
            return "Hello from callable";
        };

        Future<String> future = service.submit(task);
        System.out.println(future.get());
    }
}
