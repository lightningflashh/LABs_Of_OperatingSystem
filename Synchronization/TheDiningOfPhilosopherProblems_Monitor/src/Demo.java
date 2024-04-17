import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo {
    static PhilosopherState[] state = new PhilosopherState[5];
    static Lock[] self = new Lock[5];
    static Condition[] condition = new Condition[5];

    enum PhilosopherState {THINKING, HUNGRY, EATING}

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            state[i] = PhilosopherState.THINKING;
            self[i] = new ReentrantLock();
            condition[i] = self[i].newCondition();
        }

        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            final int philosopher = i;
            threads[i] = new Thread(() -> Philosopher(philosopher));
            threads[i].start();
        }

        try {
            Thread.sleep(5000); // Allow the philosophers to run for some time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.exit(0);
    }

    static void Philosopher(int i) {
        while (true) {
            Think(i);
            Pickup(i);
            Eat(i);
            PutDown(i);
        }
    }

    static void Think(int i) {
        System.out.println("Philosopher " + i + " is thinking.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void Eat(int i) {
        System.out.println("Philosopher " + i + " is eating.");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void Pickup(int i) {
        self[i].lock();
        try {
            state[i] = PhilosopherState.HUNGRY;
            Test(i);
            if (state[i] != PhilosopherState.EATING)
                condition[i].await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            self[i].unlock();
        }
    }

    static void Test(int i) {
        if (state[i] == PhilosopherState.HUNGRY &&
                state[(i + 4) % 5] != PhilosopherState.EATING &&
                state[(i + 1) % 5] != PhilosopherState.EATING) {
            state[i] = PhilosopherState.EATING;
            condition[i].signal();
        }
    }

    static void PutDown(int i) {
        self[i].lock();
        try {
            state[i] = PhilosopherState.THINKING;
            Test((i + 4) % 5);
            Test((i + 1) % 5);
        } finally {
            self[i].unlock();
        }
    }
}

