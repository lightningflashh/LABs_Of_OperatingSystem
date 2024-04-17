import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class BoundedBuffer {
    private final Queue<Integer> buffer;
    private final Semaphore mutex; // Make sure that there is no more than one process in the Buffer
    private final Semaphore empty;
    private final Semaphore full;

    public BoundedBuffer(int capacity) {
        buffer = new LinkedList<>();
        mutex = new Semaphore(1);
        empty = new Semaphore(capacity);
        full = new Semaphore(0);
    }

    public void produce(int item) throws InterruptedException {
        empty.acquire(); // Wait if buffer is full
        mutex.acquire(); // Acquire mutex lock
        buffer.add(item);
        System.out.println("Produced " + item + ". Buffer: " + buffer);
        mutex.release(); // Release
        full.release(); // Signal that buffer is not empty
    }

    public int consume() throws InterruptedException {
        full.acquire(); // Wait if buffer is empty
        mutex.acquire(); // Acquire mutex lock
        int item = buffer.poll();
        System.out.println("Consumed " + item + ". Buffer: " + buffer);
        mutex.release(); // Release
        empty.release(); // Signal that buffer is not full
        return item;
    }
}
