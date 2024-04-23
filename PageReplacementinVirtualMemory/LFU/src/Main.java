import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int[] pages = new int[20];
    static int pLen = 0;
    static int capacity = 0;
    static int[] freq;

    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {
        input();
        int res = pageFaults();
        System.out.println("Total no. of page faults " + res);
    }

    static void input() {
        int maxValue = Integer.MIN_VALUE;
        System.out.print("Enter the no. of pages: ");
        pLen = sc.nextInt();
        System.out.print("Enter the reference string: ");
        for (int i = 0; i < pLen; i++) {
            pages[i] = sc.nextInt();
            if (pages[i] > maxValue)
                maxValue = pages[i];
        }
        freq = new int[maxValue + 1];
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
                            freq[pReplacement]--;
                            break;
                        }
                    }
                } else {
                    list.add(pages[i]);
                }
            }
            freq[pages[i]]++;
        }
        return pageFaults;
    }

    static int replacePage(int currIndex, List<Integer> currentList) {
        int minFreq = Integer.MAX_VALUE;
        int pageToReplace = -1;
        int far = Integer.MAX_VALUE;

        for (int x : currentList) {
            if (freq[x] < minFreq) {
                minFreq = freq[x];
            }
        }

        for (int x : currentList) {
            if (freq[x] == minFreq) {
                int i = currIndex - 1;
                while (i >= 0) {
                    if (far > i && pages[i] == x) {
                        far = i;
                        pageToReplace = pages[i];
                    } else if (far < i && pages[i] == x) {
                        i = 0;
                    }
                    i--;
                }
            }
        }
        return pageToReplace;
    }
}

// 15
// 7 0 1 2 0 3 0 4 2 3 0 3 2 1 2
// 3
// Result = 9



