package textEditors.view;

import textEditors.main.Controller;

import javax.swing.*;
import java.util.List;

public class MainFrame extends JFrame
{
    private final int width = 800;
    private final int height = 650;

    private Controller controller;
    private MainPanel mainPanel;

    public MainFrame(Controller controller)
    {
        this.controller = controller;
        ShowMainFrame();
    }
    private void ShowMainFrame()
    {
        mainPanel = new MainPanel(controller, width, height);
        JFrame frame = new JFrame("Text File Editor <by your name>");
        frame.add(mainPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }
    public void markWord(String findString){mainPanel.markWord(findString);}


    public void setSourceText(List<String> lines) {
        mainPanel.setOriginalText(lines); // laddad text → taSource
    }

    public void setEditedText(List<String> lines) {
        mainPanel.setEditedText(lines);   // bearbetad text → taDest
    }

}
