package threadlocal;

public class ThreadLocalExample {
    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();
    public static void main(String[] args) {
        threadLocal.set("Main Thread");

        System.out.println(Thread.currentThread().getName() + " has value " + threadLocal.get());

        Thread thread = new Thread(() -> {
            Thread.currentThread().setName("User Thread");
            threadLocal.set("Custom Thread");
            System.out.println(Thread.currentThread().getName() + " has value " + threadLocal.get());
            threadLocal.remove();
        });
        thread.start();
        threadLocal.remove();
    }
}
