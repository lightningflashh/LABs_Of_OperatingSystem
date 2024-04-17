public class Consumer extends MyThread{

    public Consumer(BoundedBuffer buffer) {
        super(buffer);
    }

    @Override
    public void run() {
        try {
            while (this.isCheck()) {
                this.getBuffer().consume();
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
