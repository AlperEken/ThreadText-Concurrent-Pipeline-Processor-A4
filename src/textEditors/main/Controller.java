package textEditors.main;

import textEditors.model.*;
import textEditors.view.MainFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Controller
{
    private List<String> sourceStrings;

    private MainFrame view;
    public Controller()
    {
        view = new MainFrame(this);
    }


    public void execute(String[] lines, String find, String replace) {
        Writer.resetIndex();
        view.markWord(find);

        List<String> lineList = new ArrayList<>();
        for (String line : lines) {
            lineList.add(line);
        }

        SharedBuffer buffer = new SharedBuffer(20, find, replace);

        // Start Writers
        for (int i = 0; i < 3; i++) {
            Writer writer = new Writer(buffer, lineList);
            writer.start();
        }

        // Start Modifiers
        for (int i = 0; i < 4; i++) {
            Modifier modifier = new Modifier(buffer);
            modifier.start();
        }

        // Start Reader
        Reader reader = new Reader(buffer, lineList.size());
        reader.start();

        // Vänta på att Reader ska bli klar och visa resultat i GUI
        new Thread(() -> {
            try {
                reader.join();
                SwingUtilities.invokeLater(() -> {
                    view.setEditedText(reader.getOutput()); //Skriver ut texten i GUI
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }



    public void setSourceText(List<String> lines)
    {
        view.setSourceText(lines);
    }

    public void loadFileOnlyforTest() {
        FileManager fileManager = new FileManager();
        sourceStrings = fileManager.onLoadFile();
        view.setSourceText(sourceStrings); // visar i vänstra rutan
    }



}
