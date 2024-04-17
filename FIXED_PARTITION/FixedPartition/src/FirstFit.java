
public class FirstFit extends  FixedPartition{

    @Override
    public void executeAlgorithm() {
        for (int i = 0; i < nf ; i++) {
            for (int j = 0; j < nb; j++) {
                if (blockFlag[j] == 0 && file[i] <= block[j]) {
                    fileFlag[i] = j;
                    blockFlag[j] = 1;
                    frag[i] = block[j] - file[i];
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        FixedPartition firstFit = new FirstFit();
        firstFit.input();
        firstFit.executeAlgorithm();
        firstFit.output();

    }

}