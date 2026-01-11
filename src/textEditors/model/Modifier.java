package textEditors.model;

public class Modifier extends Thread {
    private SharedBuffer buffer;
    private boolean running = true;

    public Modifier(SharedBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (running) {
            try {
                buffer.modify();
                Thread.sleep(50);
            } catch (InterruptedException e) {
                running = false;
            }
        }
    }

    public void stopModifier() {
        running = false;
        this.interrupt(); // Avslutar om den sover
    }
}
