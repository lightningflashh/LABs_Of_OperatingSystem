public class Main {
    public static void main(String[] args) {
        ReadersWritersProblem problem = new ReadersWritersProblem();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    problem.Read();
                    Thread.sleep((long) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                try {
                    problem.Write();
                    Thread.sleep((long) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}

