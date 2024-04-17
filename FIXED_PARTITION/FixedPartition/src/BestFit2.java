public class BestFit2 extends FixedPartition{

    private int[] idxOccupied = new int[MAX];
    private int best;

    public BestFit2() {
        super();
        best = Integer.MAX_VALUE;
    }

    @Override
    public void executeAlgorithm() {
        for (int i = 0; i < nf ; i++) {
            for (int j = 0; j < nb; j++) {
                if (blockFlag[j] == 0 && best > block[j] - file[i] && block[j] - file[i] >= 0 && file[j] <= block[i]) {
                    fileFlag[i] = j;
                    best = block[j] - file[i];
                    idxOccupied[j] = i;
                }

                if (blockFlag[j] == 1 && frag[idxOccupied[j]] >= file[i] && best > frag[idxOccupied[j]] - file[i]) {
                    fileFlag[i] = j;
                    best = frag[idxOccupied[j]] - file[i];
                    idxOccupied[j] = i;
                }
            }

            frag[i] = best;
            if (fileFlag[i] != -1) {
                blockFlag[fileFlag[i]] = 1;
            }
            best = Integer.MAX_VALUE;
        }
    }

    public static void main(String[] args) {
        FixedPartition bestFit = new BestFit2();
        bestFit.input();
        bestFit.executeAlgorithm();
        bestFit.output();
    }
}
