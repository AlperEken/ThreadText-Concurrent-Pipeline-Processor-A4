package textEditors.model;

public class SharedBuffer {
    private final String[] buffer;
    private final BufferStatus[] status;
    private final int size;

    private int writePos = 0;
    private int modifyPos = 0;
    private int readPos = 0;

    private String find;
    private String replace;

    public SharedBuffer(int size, String find, String replace) {
        this.size = size;
        this.find = find;
        this.replace = replace;
        buffer = new String[size];
        status = new BufferStatus[size];

        for (int i = 0; i < size; i++) {
            status[i] = BufferStatus.Empty;
        }
    }

    public synchronized void write(String line) throws InterruptedException {
        while (status[writePos] != BufferStatus.Empty) {
            wait();
        }
        buffer[writePos] = line;
        status[writePos] = BufferStatus.New;
        writePos = (writePos + 1) % size;
        notifyAll();
    }

    public synchronized void modify() throws InterruptedException {
        while (status[modifyPos] != BufferStatus.New) {
            wait();
        }

        if (buffer[modifyPos].contains(find)) {
            buffer[modifyPos] = buffer[modifyPos].replace(find, replace);
        }

        status[modifyPos] = BufferStatus.Checked;
        modifyPos = (modifyPos + 1) % size;
        notifyAll();
    }

    public synchronized String read() throws InterruptedException {
        while (status[readPos] != BufferStatus.Checked) {
            wait();
        }

        String result = buffer[readPos];
        //  Gör att samma rad skickas tillbaka för modifiering igen
        status[readPos] = BufferStatus.Empty;
        readPos = (readPos + 1) % size;
        notifyAll();
        return result;
    }

}
