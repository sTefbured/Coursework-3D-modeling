package Coursework;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    public Window(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setSize(getToolkit().getScreenSize().width,
//                getToolkit().getScreenSize().height);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setFocusable(true);
        setVisible(true);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        Main.getModel().draw(graphics2D);
    }

    @Override
    public void repaint(long time, int x, int y, int width, int height) {
        super.repaint(time, x, y, width, height);
    }
}
