import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBuffer {
    private final Queue<Integer> buffer = new LinkedList<>();
    private final int capacity;
    private final Lock lock = new ReentrantLock();

    public BoundedBuffer(int capacity) {
        this.capacity = capacity;
    }

    public void produce(int item) throws InterruptedException{
        lock.lock();
        try {
            while (buffer.size() == capacity) {
                Thread.sleep(100);
            }
            buffer.add(item);
            System.out.println("Produced: " + item);
            System.out.println("Buffer has " + buffer.size() + " item");
        } finally {
            lock.unlock();
        }
    }

    public void consume() throws InterruptedException {
        lock.lock();
        try {
            while (buffer.isEmpty()) {
                Thread.sleep(100);
            }
            int item = buffer.poll();
            System.out.println("Consumed: " + item);
        } finally {
            lock.unlock();
        }
    }
}
