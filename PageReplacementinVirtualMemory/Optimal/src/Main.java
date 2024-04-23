import java.util.*;

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
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < pLen; i++) {
            if (!list.contains(pages[i])) {
                pageFaults++;
                if (list.size() == capacity) {
                    int pReplacement = replacePage(i, list);
                    for (int j = 0; j < list.size(); j++) {
                        if (list.get(j).equals(pReplacement)) {
                            list.set(j, pages[i]);
                            break;
                        }
                    }
                } else {
                    list.add(pages[i]);
                }
            }
        }
        return pageFaults;
    }

    static int replacePage(int currentIndex, List<Integer> currentList) {
        int t;
        int max = Integer.MIN_VALUE;
        int pageToReplace = -1;
        for (int x : currentList) {
            t = currentIndex + 1;
            while (t < pLen) {
                if (x == pages[t]) {
                    if (t > max) {
                        max = t;
                        pageToReplace = x;
                    }
                    break;
                }
                t++;
                if (t == pLen) { //the page 'x' not exist in future
                    return x;
                }
            }
        }
        return pageToReplace;
    }
}

// 20
// 7 0 1 2 0 3 0 4 2 3 0 3 2 1 2 0 1 7 0 1
// 3
// Result = 9