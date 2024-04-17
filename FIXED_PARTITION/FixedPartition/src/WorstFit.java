public class WorstFit extends FixedPartition{
    private int highest;

    public WorstFit() {
        super();
        highest = 0;
    }

    @Override
    public void executeAlgorithm() {
        for (int i = 0; i < nf ; i++) {
            for (int j = 0; j < nb; j++) {
                if (blockFlag[j] == 0 && highest < block[j] - file[i]) {
                    fileFlag[i] = j;
                    highest = block[j] - file[i];
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
        FixedPartition worstFit = new WorstFit();
        worstFit.input();
        worstFit.executeAlgorithm();
        worstFit.output();
    }

}
