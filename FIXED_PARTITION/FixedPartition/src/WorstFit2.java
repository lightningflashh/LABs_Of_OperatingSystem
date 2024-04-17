public class WorstFit2 extends FixedPartition{
    private int highest;
    private int[] idxOccupied = new int[MAX];

    public WorstFit2() {
        super();
        highest = 0;
    }

    @Override
    public void executeAlgorithm() {
        for (int i = 0; i < nf ; i++) {
            for (int j = 0; j < nb; j++) {
                if (blockFlag[j] == 1 && frag[idxOccupied[j]] >= file[i] && highest < frag[idxOccupied[j]] - file[i]) {
                    fileFlag[i] = j;
                    highest = frag[idxOccupied[j]] - file[i];
                    idxOccupied[j] = i;
                }

                if (blockFlag[j] == 0 && highest < block[j] - file[i]) {
                    fileFlag[i] = j;
                    highest = block[j] - file[i];
                    idxOccupied[j] = i;
                }
            }

            frag[i] = highest;
            if (fileFlag[i] != -1) {
                blockFlag[fileFlag[i]] = 1;
            }
            highest = 0;
        }
    }

    public static void main(String[] args) {
        FixedPartition worstFit2 = new WorstFit2();
        worstFit2.input();
        worstFit2.executeAlgorithm();
        worstFit2.output();
    }

}
