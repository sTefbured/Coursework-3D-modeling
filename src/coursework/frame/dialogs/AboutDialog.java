package coursework.frame.dialogs;

import coursework.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AboutDialog extends JDialog {
    private final String content = "<html><div style=\"padding: 30px\""
            + "align=\"center\">"
            + "<h1>This app is created by <br> Stepan Kotikov </h1>"
            + "as a coursework for discipline \"3d modelling.\"</div>"
            + "<div style=\"padding-bottom: 5px\" "
            + "align=\"center\">2020 &copy</div></html>";

    public AboutDialog() {
        super(Main.getWindow(), "About", false);
        setLayout(new BorderLayout());
        add(new JLabel(content), BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    setVisible(false);
                }
            }
        });
    }
}
