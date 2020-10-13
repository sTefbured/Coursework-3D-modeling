package coursework.frame;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private final DrawingPanel drawingPanel;

    public Window(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(getToolkit().getScreenSize());
        setLocationRelativeTo(null);
        getContentPane().setLayout(new GridLayout(1, 2, 0, 0));
        drawingPanel = new DrawingPanel();
        drawingPanel.setSize(getWidth() / 2, getHeight() / 2);
        MenuPanel menuPanel = new MenuPanel();
        menuPanel.setSize(getWidth() / 2, getHeight() / 2);
        getContentPane().add(drawingPanel);
        getContentPane().add(menuPanel);
        setVisible(true);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        drawingPanel.repaint();
    }

    public JPanel getDrawingPanel() {
        return drawingPanel;
    }
}
