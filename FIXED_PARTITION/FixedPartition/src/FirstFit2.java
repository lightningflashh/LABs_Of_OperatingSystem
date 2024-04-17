public class FirstFit2 extends FixedPartition{

    private int[] idxOccupied = new int[MAX];

    @Override
    public void executeAlgorithm() {
        for (int i = 0; i < nf ; i++) {
            for (int j = 0; j < nb; j++) {
                if (blockFlag[j] == 0 && file[i] <= block[j]) {
                    fileFlag[i] = j;
                    idxOccupied[j] = i;
                    blockFlag[j] = 1;
                    frag[i] = block[j] - file[i];
                    break;
                }

                if (blockFlag[j] == 1 && file[i] <= frag[idxOccupied[j]]) {
                    frag[i] = frag[idxOccupied[j]] - file[i];
                    fileFlag[i] = j;
                    idxOccupied[j] = i;
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        FixedPartition firstFit2 = new FirstFit2();
        firstFit2.input();
        firstFit2.executeAlgorithm();
        firstFit2.output();

    }
}
