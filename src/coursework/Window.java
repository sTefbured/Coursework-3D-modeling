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
//                switch (e.getKeyCode()) {
//                    case CODE_ARROW_LEFT -> Main.getModel().transit(-10, 0, 0);
//                    case CODE_ARROW_UP -> Main.getModel().transit(0, 10, 0);
//                    case CODE_ARROW_RIGHT -> Main.getModel().transit(10, 0, 0);
//                    case CODE_ARROW_DOWN -> Main.getModel().transit(0, -10, 0);
//                    case CODE_W_SMALL -> Main.getModel().rotate(5, 0, 0);
//                    case CODE_S_SMALL -> Main.getModel().rotate(-5, 0, 0);
//                    case CODE_A_SMALL -> Main.getModel().rotate(0, 5, 0);
//                    case CODE_D_SMALL -> Main.getModel().rotate(0, -5, 0);
//                    case CODE_E_SMALL -> Main.getModel().rotate(0, 0, 5);
//                    case CODE_Q_SMALL -> Main.getModel().rotate(0, 0, -5);
//                }
//                repaint();
                //TODO: refactor
                Main.getModel().isTransforming = true;
                switch (e.getKeyCode()) {
                    case CODE_ARROW_LEFT -> Main
                            .getModel()
                            .condition = ModelCondition.MOVING_LEFT;
                    case CODE_ARROW_UP -> Main
                            .getModel()
                            .condition = ModelCondition.MOVING_UP;
                    case CODE_ARROW_RIGHT -> Main
                            .getModel()
                            .condition = ModelCondition.MOVING_RIGHT;
                    case CODE_ARROW_DOWN -> Main
                            .getModel()
                            .condition = ModelCondition.MOVING_DOWN;
                    case CODE_W_SMALL -> Main.getModel().condition = ModelCondition.ROTATING_X_NEG;
                    case CODE_S_SMALL -> Main.getModel().condition = ModelCondition.ROTATING_X_POS;
                    case CODE_A_SMALL -> Main.getModel().condition = ModelCondition.ROTATING_Y_NEG;
                    case CODE_D_SMALL -> Main.getModel().condition = ModelCondition.ROTATING_Y_POS;
                    case CODE_E_SMALL -> Main.getModel().condition = ModelCondition.ROTATING_Z_NEG;
                    case CODE_Q_SMALL -> Main.getModel().condition = ModelCondition.ROTATING_Z_POS;
                    default -> Main.getModel().isTransforming = false;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                switch (e.getKeyCode()) {
                    case CODE_ARROW_LEFT,
                            CODE_ARROW_UP,
                            CODE_ARROW_RIGHT,
                            CODE_ARROW_DOWN,
                            CODE_W_SMALL,
                            CODE_S_SMALL,
                            CODE_A_SMALL,
                            CODE_D_SMALL,
                            CODE_E_SMALL,
                            CODE_Q_SMALL -> {
                        Main.getModel().isTransforming = false;
                        Main.getModel().condition = ModelCondition.NONE;
                    }
                }
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
        MainPanel panel = new MainPanel();
        add(panel);
    }
}
