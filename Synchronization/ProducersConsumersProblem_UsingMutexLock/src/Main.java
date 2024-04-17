public class Main {
    public static void main(String[] args) {
        BoundedBuffer boundedBuffer = new BoundedBuffer(5);

        // Producer thread
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    boundedBuffer.produce(i);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        // Consumer thread
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    boundedBuffer.consume();
                    Thread.sleep(700);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}