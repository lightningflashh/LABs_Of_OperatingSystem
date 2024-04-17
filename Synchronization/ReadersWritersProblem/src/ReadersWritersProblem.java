import java.util.concurrent.Semaphore;

public class ReadersWritersProblem {
    private final Semaphore rw_mutex = new Semaphore(1);
    private final Semaphore mutex = new Semaphore(1);
    private int read_count = 0;
    int x = 0;


    public void Read() throws InterruptedException {
        mutex.acquire();
        read_count++;
        if (read_count == 1) {
            rw_mutex.acquire();
        }
        Thread.sleep(3000);
        System.out.println(Thread.currentThread().getId() + " is reading .... " + x);
        mutex.release();
        read_count--;
        if (read_count == 0) {
            rw_mutex.release();
        }
        mutex.release();
    }


    public void Write() throws InterruptedException {
        rw_mutex.acquire();
        Thread.sleep(5000);
        System.out.println(Thread.currentThread().getId() + " is writing .... " + (++x));
        rw_mutex.release();
    }

}
