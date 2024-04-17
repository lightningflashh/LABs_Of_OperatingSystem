import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBuffer {
    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    final Object[] items = new Object[5];
    int putptr, takeptr, count = 0;

    public void put(Object x) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length) {
                System.out.println("Buffer is full. Producer is waiting...");
                notFull.await();
            }
            items[putptr] = x;
            if (++putptr == items.length) putptr = 0;
            ++count;
            notEmpty.signal();
            System.out.println("Producer put: " + x);
            for (Object item : items) {
                System.out.print(item + " ");
            }
            System.out.println();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                System.out.println("Buffer is empty. Consumer is waiting...");
                notEmpty.await();
            }
            Object x = items[takeptr];
            items[takeptr] = -1;
            if (++takeptr == items.length) takeptr = 0;
            --count;
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }
}
