package multithreading;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        int corePoolSize = 2;
        int maxPoolSize = 4;
        long keepAliveTime = 10;
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(10);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize,
                maxPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                queue);

        for(int i=1;i<=5;i++) {
            executor.execute(() -> {
                System.out.println("Running executor: " + Thread.currentThread().getName());
            });
        }

        executor.shutdown();
    }
}
