package producerconsumer;

public class ProducerConsumer {
    private int data;
    private boolean available = false;

    public synchronized void produce(int value) throws InterruptedException {
        while(available) {
            wait();
        }
        data = value;
        available = true;
        System.out.println("Data produced: " + value);
        notify();
    }

    public synchronized void consume() throws InterruptedException {
        while (!available) {
            wait();
        }
        available = false;
        System.out.println("Data Consumed: " + data);
        notify();
    }
}

class Test {
    public static void main(String[] args) {
        ProducerConsumer pc  = new ProducerConsumer();
        new Thread(() -> {
           for(int i=0;i<=10;i++) {
               try {
                   pc.produce(i);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        },"producer").start();

        new Thread(() -> {
            for(int i=0;i<=10;i++) {
                try {
                    pc.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "consumer").start();
    }
}
