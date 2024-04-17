public class MyThread extends Thread{
    private final BoundedBuffer buffer;
    private boolean check = true;

    public MyThread(BoundedBuffer buffer) {
        this.buffer = buffer;
    }

    public BoundedBuffer getBuffer() {
        return buffer;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    @Override
    public void run() {

    }
    public void stopProcess() {
        check = false;
    }
}
