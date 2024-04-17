import java.util.Scanner;

public class Main {
    static int mutex = 1, full = 0, empty = 3, x = 0;

    public static void main(String[] args) {
        int n;
        System.out.println("\n1.PRODUCER\n2.CONSUMER\n3.EXIT\n");
        while (true) {
            System.out.println("\nENTER YOUR CHOICE\n");
            Scanner scanner = new Scanner(System.in);
            n = scanner.nextInt();
            switch (n) {
                case 1:
                    if ((mutex == 1) && (empty != 0))
                        producer();
                    else
                        System.out.println("BUFFER IS FULL");
                    break;
                case 2:
                    if ((mutex == 1) && (full != 0))
                        consumer();
                    else
                        System.out.println("BUFFER IS EMPTY");
                    break;
                case 3:
                    System.exit(0);
                    break;
            }
        }
    }

    static int wait(int s) {
        return (--s);
    }

    static int signal(int s) {
        return (++s);
    }

    static void producer() {
        mutex = wait(mutex);
        full = signal(full);
        empty = wait(empty);
        x++;
        System.out.println("\nproducer produces the item" + x);
        mutex = signal(mutex);
    }

    static void consumer() {
        mutex = wait(mutex);
        full = wait(full);
        empty = signal(empty);
        System.out.println("\n consumer consumes item" + x);
        x--;
        mutex = signal(mutex);
    }
}
