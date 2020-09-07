package coursework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window extends JFrame {
    private final int CODE_ARROW_LEFT = 37;
    private final int CODE_ARROW_UP = 38;
    private final int CODE_ARROW_RIGHT = 39;
    private final int CODE_ARROW_DOWN = 40;
    private final int CODE_A_SMALL = 65;
    private final int CODE_W_SMALL = 87;
    private final int CODE_D_SMALL = 68;
    private final int CODE_S_SMALL = 83;
    private final int CODE_Q_SMALL = 81;
    private final int CODE_E_SMALL = 69;

    public Window(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(getToolkit().getScreenSize().width,
                getToolkit().getScreenSize().height);
        setLocationRelativeTo(null);
        setFocusable(true);
        setVisible(true);
        addListeners();
    }

    private void addListeners() {
        addKeyInput();
        addMouseWheelInput();
    }

    private void addKeyInput() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case CODE_ARROW_LEFT -> Main.getModel().transit(-10, 0, 0);
                    case CODE_ARROW_UP -> Main.getModel().transit(0, 10, 0);
                    case CODE_ARROW_RIGHT -> Main.getModel().transit(10, 0, 0);
                    case CODE_ARROW_DOWN -> Main.getModel().transit(0, -10, 0);
                    case CODE_W_SMALL -> Main.getModel().rotate(5, 0, 0);
                    case CODE_S_SMALL -> Main.getModel().rotate(-5, 0, 0);
                    case CODE_A_SMALL -> Main.getModel().rotate(0, 5, 0);
                    case CODE_D_SMALL -> Main.getModel().rotate(0, -5, 0);
                    case CODE_E_SMALL -> Main.getModel().rotate(0, 0, 5);
                    case CODE_Q_SMALL -> Main.getModel().rotate(0, 0, -5);
                }
                repaint();
            }
        });
    }

    private void addMouseWheelInput() {
        addMouseWheelListener(e -> {
            double ds = 1 + e.getWheelRotation() * 0.1;
            Main.getModel().scale(ds, ds, ds);
            repaint();
        });
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setStroke(new BasicStroke(3));
        Main.getModel().draw(graphics2D);
    }
}
