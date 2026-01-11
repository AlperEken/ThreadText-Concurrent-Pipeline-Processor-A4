package textEditors.model;

import java.util.List;

public class Writer extends Thread {
    private SharedBuffer buffer;
    private List<String> lines;
    private static int currentLineIndex = 0;
    private static final Object lock = new Object();
    private boolean running = true;

    public Writer(SharedBuffer buffer, List<String> lines) {
        this.buffer = buffer;
        this.lines = lines;
    }

    @Override
    public void run() {
        while (running) {
            String line = null;

            // Synka läsningen från listan
            synchronized (lock) {
                if (currentLineIndex < lines.size()) {
                    line = lines.get(currentLineIndex);
                    currentLineIndex++;
                } else {
                    break;
                }
            }

            try {
                if (line != null) {
                    buffer.write(line);
                    Thread.sleep(50);
                }
            } catch (InterruptedException e) {
                running = false;
            }
        }
    }

    public void stopWriter() {
        running = false;
        this.interrupt();
    }

    public static void resetIndex() {
        currentLineIndex = 0;
    }

}
