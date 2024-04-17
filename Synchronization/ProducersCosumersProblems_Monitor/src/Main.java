public class Main {
    public static void main(String[] args) {
        BoundedBuffer buffer = new BoundedBuffer();

        Thread producerThread = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Producer try to put: " + i);
                    buffer.put(i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Consumer try to took product ...");
                    int value = (int) buffer.take();
                    System.out.println("Consumer took: " + value);
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producerThread.start();
        consumerThread.start();

    }
}

