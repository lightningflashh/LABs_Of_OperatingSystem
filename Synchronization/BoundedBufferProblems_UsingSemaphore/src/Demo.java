public class Demo {
    public static void main(String[] args) {
        BoundedBuffer buffer = new BoundedBuffer(5);

        Producer producer = new Producer(buffer);
        Consumer consumer = new Consumer(buffer);

        producer.start();
        consumer.start();


        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
