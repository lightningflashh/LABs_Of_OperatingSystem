import java.io.*;

public class BankersAlgorithm {
    static int rows = 0;
    static int cols = 0;
    static int[][] need;
    static int[][] maximum;
    static int[][] allocated;
    static int[] available;
    static int[] safeSequence;
    final static String fileName = "testcase2.txt";
    final static String relativePath = "src/" + fileName;
    final static String filePath = System.getProperty("user.dir") + "/" + relativePath;

    public static void main(String[] args) throws IOException {

        readFile();
        calculateNeed();
        safeSequence();
    }

    static void safeSequence() {
        int count = 0;

        boolean[] visited = new boolean[rows];
        for (int i = 0; i < rows; i++)
        {
            visited[i] = false;
        }

        int[] work = new int[cols];
        for (int i = 0;i < cols; i++)
            work[i] = available[i];

        while (count < rows) {
            boolean flag = false;
            for (int i = 0; i < rows; i++) {
                if (!visited[i]) {
                    for (int j = 0; j < cols; j++) {
                        if(need[i][j] > work[j]) break;

                        if (j == cols - 1) {
                            safeSequence[count++] = i;
                            visited[i] = true;
                            flag = true;
                            for (int x = 0; x < cols; x++) {
                                work[x] += allocated[i][x];
                            }
                        }
                    }
                }
            }
            if (!flag) break;
        }

        if (count < rows)
        {
            System.out.println("The System is UnSafe!");
        }
        else
        {
            System.out.println("Following is the SAFE Sequence");
            for (int i = 0;i < rows; i++)
            {
                System.out.print("P" + safeSequence[i]);
                if (i != rows - 1)
                    System.out.print(" -> ");
            }
        }
    }

    static void calculateNeed() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                need[i][j] = maximum[i][j] - allocated[i][j];
            }
        }
    }
    static void readFile() throws IOException {
        FileInputStream fis = new FileInputStream(new File(filePath));
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));


        String[] crLine = br.readLine().split("\\s+");
        rows = Integer.parseInt(crLine[0]);
        cols = Integer.parseInt(crLine[1]);

        need = new int[rows][cols];
        maximum = new int[rows][cols];
        allocated = new int[rows][cols];
        available = new int[rows];
        safeSequence = new int[rows];

        for (int i = 0; i < rows; i++) {
            String[] allocLine = br.readLine().split("\\s+");
            for (int j = 0; j < cols; j++) {
                allocated[i][j] = Integer.parseInt(allocLine[j]);
            }
        }

        for (int i = 0; i < rows; i++) {
            String[] maxLine = br.readLine().split("\\s+");
            for (int j = 0; j < cols; j++) {
                maximum[i][j] = Integer.parseInt(maxLine[j]);
            }
        }

        String[] availLine = br.readLine().split("\\s+");
        available = new int[cols];
        for (int i = 0; i < cols; i++) {
            available[i] = Integer.parseInt(availLine[i]);
        }
        br.close();
    }
}
