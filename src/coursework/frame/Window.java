package coursework.frame;

import coursework.Main;
import coursework.exceptions.ConditionNotFoundException;
import coursework.model.ModelCondition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window extends JFrame {
    private final MainPanel panel;

    private boolean isInTypeMode;

    public Window(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(getToolkit().getScreenSize());
        setLocationRelativeTo(null);
        setVisible(true);
        addListeners();
        panel = new MainPanel();
        add(panel);
        isInTypeMode = false;
    }

    private void addListeners() {
        addKeyInput();
        addMouseWheelInput();
    }

    private void addKeyInput() {
        addKeyListener(new KeyAdapter() {
            private ModelCondition getConditionByKey(int keyCode)
                    throws ConditionNotFoundException {
                switch (keyCode) {
                    case KeyEvent.VK_LEFT:
                        return ModelCondition.MOVING_LEFT;
                    case KeyEvent.VK_UP:
                        return ModelCondition.MOVING_UP;
                    case KeyEvent.VK_RIGHT:
                        return ModelCondition.MOVING_RIGHT;
                    case KeyEvent.VK_DOWN:
                        return ModelCondition.MOVING_DOWN;
                    case KeyEvent.VK_W:
                        return ModelCondition.ROTATING_X_POS;
                    case KeyEvent.VK_S:
                        return ModelCondition.ROTATING_X_NEG;
                    case KeyEvent.VK_A:
                        return ModelCondition.ROTATING_Y_NEG;
                    case KeyEvent.VK_D:
                        return ModelCondition.ROTATING_Y_POS;
                    case KeyEvent.VK_E:
                        return ModelCondition.ROTATING_Z_NEG;
                    case KeyEvent.VK_Q:
                        return ModelCondition.ROTATING_Z_POS;
                    case KeyEvent.VK_T:
                        isInTypeMode = !isInTypeMode;
                        // Falls down to default
                    default:
                        throw new ConditionNotFoundException();
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (!isInTypeMode) {
                    return;
                }
                ModelCondition condition;
                try {
                    condition = getConditionByKey(e.getKeyCode());
                } catch (ConditionNotFoundException exception) {
                    return;
                }
                Main.getModel().conditions |= condition.getValue();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (isInTypeMode) {
                    return;
                }
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
                if (isInTypeMode) {
                    return;
                }
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
        panel.repaint();
    }
}
