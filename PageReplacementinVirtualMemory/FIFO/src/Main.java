import java.io.Console;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int[] pages = new int[20];
    static int pLen = 0;
    static int capacity = 0;

    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {
        input();
        int res = pageFaults();
        System.out.println("Total no. of page faults " + res);
    }

    static void input() {
        System.out.print("Enter the no. of pages: ");
        pLen = sc.nextInt();
        System.out.print("Enter the reference string: ");
        for (int i = 0; i < pLen; i++) {
            pages[i] = sc.nextInt();
        }
        System.out.print("Enter the no. of frames: ");
        capacity = sc.nextInt();
    }

    static int pageFaults() {
        int pageFaults = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < pLen; i++) {
            if (!queue.contains(pages[i])) {
                pageFaults++;
                if (capacity == queue.size()) {
                    queue.poll();
                }
                queue.add(pages[i]);
            }
        }
        return pageFaults;
    }
}

// 13
// 7 0 1 2 0 3 0 4 2 3 0 3 2
// 4
// Result = 7