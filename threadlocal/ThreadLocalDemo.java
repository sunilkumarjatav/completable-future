package threadlocal;

public class ThreadLocalDemo {
    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();
    public static void main(String[] args) {
        Runnable task = () -> {
            String name = Thread.currentThread().getName();
            threadLocal.set("Value for " + name);
            System.out.println(name + " : " + threadLocal.get());
        };

        Thread t1 = new Thread(task, "Thread 1");
        Thread t2 = new Thread(task, "Thread 2");
        t1.start();
        t2.start();
    }
}
