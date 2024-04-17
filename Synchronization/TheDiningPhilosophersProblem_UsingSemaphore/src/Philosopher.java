
public class Philosopher extends Thread {
    private final int left;
    private final int right;
    private int id;

    Philosopher(int left, int right, int number) {
        this.left = left;
        this.right = right;
        this.setName("Philosopher" + number);
        id = number;
    }

    @Override
    public void run() {
        try {
            while (true) {
                think();
                eat();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void think() throws InterruptedException {
        System.out.println("Philosopher " + id + " is thinking.");
        Thread.sleep((long) (Math.random() * 1000));
    }

    private void eat() throws InterruptedException {
        if (!Main.chopsticks[left].tryAcquire()) {
            System.out.println("Philosopher " + id + " couldn't pick up the left chopstick. Back to thinking.");
            return;
        }
        System.out.println("Philosopher " + id + " picked up the left chopstick.");

        if (!Main.chopsticks[right].tryAcquire()) {
            System.out.println("Philosopher " + id + " couldn't pick up the right chopstick. Back to thinking.");
            Main.chopsticks[left].release();
            return;
        }
        System.out.println("Philosopher " + id + " picked up the right chopstick.");

        System.out.println("Philosopher " + id + " is eating.");
        Thread.sleep((long) (Math.random() * 1000));

        Main.chopsticks[left].release();
        System.out.println("Philosopher " + id + " put down the left chopstick.");
        Main.chopsticks[right].release();
        System.out.println("Philosopher " + id + " put down the right chopstick.");
    }
}


