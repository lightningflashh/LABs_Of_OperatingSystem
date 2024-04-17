import java.util.Random;

public class Producer extends MyThread{
    public Producer(BoundedBuffer buffer) {
        super(buffer);
    }

    @Override
    public void run() {
        Random random = new Random();
        try {
            while (this.isCheck()) {
                int item = random.nextInt(100);
                this.getBuffer().produce(item);
                Thread.sleep((long) (Math.random() * 1000)); // Sleep for random time
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void stopProcess() {
        this.setCheck(false);
    }

}
