package coursework.frame;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private DrawingPanel drawingPanel;
    private MenuPanel menuPanel;

    public Window(String title) {
        super(title);
        Dimension screenSize = getToolkit().getScreenSize();
        Dimension size = new Dimension(3 * screenSize.width / 4,
                                       3 * screenSize.height / 4);
        setSize(size);
        addPanels();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void addPanels() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 2, 10, 10));
        Dimension halfWindowSize =
                new Dimension(getSize().width / 2 - 25, getSize().height);
        drawingPanel = new DrawingPanel(halfWindowSize);
        menuPanel = new MenuPanel(this, halfWindowSize);
        mainPanel.add(drawingPanel);
        mainPanel.add(menuPanel);
        add(mainPanel);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        drawingPanel.repaint();
    }

    public JPanel getDrawingPanel() {
        return drawingPanel;
    }

    public JPanel getMenuPanel() {
        return menuPanel;
    }
}
