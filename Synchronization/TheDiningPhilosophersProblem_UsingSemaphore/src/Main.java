import java.util.concurrent.Semaphore;

public class Main {
    static final int NUM_PHILOSOPHERS = 5;
    static Semaphore[] chopsticks = new Semaphore[] {
            new Semaphore(1),
            new Semaphore(1),
            new Semaphore(1),
            new Semaphore(1),
            new Semaphore(1)
    };


    public static void main(String[] args) {
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            new Philosopher(i, (i + 1) % 5, i).start();
        }
    }
}