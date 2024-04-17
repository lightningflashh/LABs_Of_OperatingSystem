import java.util.Scanner;

public abstract class FixedPartition {
    public static final int MAX = 25;
    public Scanner scanner = new Scanner(System.in);
    protected int nb;
    protected int nf;
    protected int[] blockFlag;
    protected int[] fileFlag;
    protected int[] block;
    protected int[] file;
    protected int[] frag;

    public FixedPartition() {
        blockFlag = new int[MAX];
        fileFlag = new int[MAX];
        block = new int[MAX];
        file = new int[MAX];
        frag = new int[MAX];
    }

    protected void input() {
        System.out.print("Enter the number of blocks: ");
        nb = scanner.nextInt();
        System.out.print("Enter the number of files: ");
        nf = scanner.nextInt();
        System.out.println("Enter the size of the blocks: ");

        for (int i = 0; i < nb; i++) {
            System.out.print("Block " + (i + 1) + ": ");
            block[i] = scanner.nextInt();
            blockFlag[i] = 0;
        }

        System.out.println("Enter the size of the files: ");
        for (int i = 0; i < nf; i++) {
            System.out.print("File " + (i + 1) + ": ");
            file[i] = scanner.nextInt();
            fileFlag[i] = -1;
        }
    }

    protected void output() {
        System.out.printf("\n%-9s%-12s%-10s%-13s%-8s\n", "File_no:", "File_size:", "Block_no:", "Block_size:", "Fragment");
        for (int i = 0; i < nf; i++) {
            int blockIndex = fileFlag[i];
            int blockSize = (blockIndex >= 0 && blockIndex < nb) ? block[blockIndex] : 0;
            System.out.printf("%-9d%-12d%-10d%-13d%-8d\n", i + 1, file[i], blockIndex + 1, blockSize, frag[i]);
        }
    }

    public abstract void executeAlgorithm();


}
