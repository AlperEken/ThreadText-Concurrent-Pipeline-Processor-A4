package textEditors.model;

import java.util.List;
import java.util.Vector;

public class Reader extends Thread {
    private SharedBuffer buffer;
    private List<String> output;
    private int totalLines;
    private boolean running = true;

    public Reader(SharedBuffer buffer, int totalLines) {
        this.buffer = buffer;
        this.totalLines = totalLines;
        this.output = new Vector<>();
    }

    @Override
    public void run() {
        int linesRead = 0;

        while (running && linesRead < totalLines) {
            try {
                String line = buffer.read();
                output.add(line);
                linesRead++;
                Thread.sleep(50);
            } catch (InterruptedException e) {
                running = false;
            }
        }
    }

    public List<String> getOutput() {
        return output;
    }

    public void stopReader() {
        running = false;
        this.interrupt();
    }
}
