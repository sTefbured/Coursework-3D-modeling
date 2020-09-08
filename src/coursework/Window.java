package coursework;

import coursework.exceptions.ConditionNotFoundException;

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

            private ModelCondition getConditionByKey(int keyCode)
                    throws ConditionNotFoundException {
                return switch (keyCode) {
                    case CODE_ARROW_LEFT -> ModelCondition.MOVING_LEFT;
                    case CODE_ARROW_UP -> ModelCondition.MOVING_UP;
                    case CODE_ARROW_RIGHT -> ModelCondition.MOVING_RIGHT;
                    case CODE_ARROW_DOWN -> ModelCondition.MOVING_DOWN;
                    case CODE_W_SMALL -> ModelCondition.ROTATING_X_POS;
                    case CODE_S_SMALL -> ModelCondition.ROTATING_X_NEG;
                    case CODE_A_SMALL -> ModelCondition.ROTATING_Y_NEG;
                    case CODE_D_SMALL -> ModelCondition.ROTATING_Y_POS;
                    case CODE_E_SMALL -> ModelCondition.ROTATING_Z_NEG;
                    case CODE_Q_SMALL -> ModelCondition.ROTATING_Z_POS;
                    default ->
                            throw new ConditionNotFoundException();
                };
            }

            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                Main.getModel().isTransforming = true;
                ModelCondition condition;
                try {
                    condition = getConditionByKey(e.getKeyCode());
                } catch (ConditionNotFoundException exception) {
                    return;
                }
                Main.getModel().conditions |= condition.getValue();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                Main.getModel().isTransforming = false;
                ModelCondition condition;
                try {
                    condition = getConditionByKey(e.getKeyCode());
                } catch (ConditionNotFoundException exception) {
                    return;
                }
                Main.getModel().conditions ^= condition.getValue();
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
