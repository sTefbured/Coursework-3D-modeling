package coursework.frame;

import coursework.Main;
import coursework.exceptions.ConditionNotFoundException;
import coursework.model.ModelCondition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window extends JFrame {
    private final MainPanel panel;

    private boolean isRotating;
    private int previousX;
    private int previousY;
    private boolean isMoving;

    public Window(String title) {
        super(title);
        isRotating = false;
        isMoving = false;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(getToolkit().getScreenSize());
        setLocationRelativeTo(null);
        setVisible(true);
        addListeners();
        panel = new MainPanel();
        add(panel);
    }

    private void addListeners() {
        addKeyInput();
        addMouseWheelInput();
        addMouseInput();
        addMouseMotionInput();
    }

    private void addKeyInput() {
        addKeyListener(new KeyAdapter() {
            private ModelCondition getConditionByKey(int keyCode)
                    throws ConditionNotFoundException {
                return switch (keyCode) {
                    case KeyEvent.VK_LEFT -> ModelCondition.MOVING_LEFT;
                    case KeyEvent.VK_UP -> ModelCondition.MOVING_UP;
                    case KeyEvent.VK_RIGHT -> ModelCondition.MOVING_RIGHT;
                    case KeyEvent.VK_DOWN -> ModelCondition.MOVING_DOWN;
                    case KeyEvent.VK_W -> ModelCondition.ROTATING_X_POS;
                    case KeyEvent.VK_S -> ModelCondition.ROTATING_X_NEG;
                    case KeyEvent.VK_A -> ModelCondition.ROTATING_Y_POS;
                    case KeyEvent.VK_D -> ModelCondition.ROTATING_Y_NEG;
                    case KeyEvent.VK_E -> ModelCondition.ROTATING_Z_NEG;
                    case KeyEvent.VK_Q -> ModelCondition.ROTATING_Z_POS;
                    case KeyEvent.VK_Z -> ModelCondition.MOVING_Z_POS;
                    case KeyEvent.VK_X -> ModelCondition.MOVING_Z_NEG;
                    default -> throw new ConditionNotFoundException();
                };
            }

            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(isRotating) {
                    return;
                }
                ModelCondition condition;
                try {
                    condition = getConditionByKey(e.getKeyCode());
                } catch (ConditionNotFoundException exception) {
                    return;
                }
                Main.getModel().activateCondition(condition);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if(isRotating) {
                    return;
                }
                ModelCondition condition;
                try {
                    condition = getConditionByKey(e.getKeyCode());
                } catch (ConditionNotFoundException exception) {
                    return;
                }
                Main.getModel().deactivateCondition(condition);
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

    private void addMouseInput() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                previousX = e.getX();
                previousY = e.getY();
                if (e.getButton() == MouseEvent.BUTTON1) {
                    isRotating = true;
                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    isMoving = true;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                if (e.getButton() == MouseEvent.BUTTON1) {
                    isRotating = false;
                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    isMoving = false;
                }
            }
        });
    }

    private void addMouseMotionInput() {
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                int x = previousX - e.getX();
                int y = previousY - e.getY();
                previousX = e.getX();
                previousY = e.getY();
                if (isRotating) {
                    Main.getModel().rotate(y, x, 0);
                }
                if (isMoving) {
                    Main.getModel().transit(-x, y, 0);
                }
            }
        });
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        panel.repaint();
    }
}
