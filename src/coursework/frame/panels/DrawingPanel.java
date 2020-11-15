package coursework.frame.panels;

import coursework.Main;

import javax.swing.*;
import java.awt.*;

public class DrawingPanel extends JPanel {
    public DrawingPanel(Dimension size) {
        super();
        setPreferredSize(size);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (Main.getModel() == null) {
            return;
        }
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setStroke(new BasicStroke(3));
        Main.getModel().draw(graphics2D);
    }

    public void returnToInitialValues() {
        Main.getModel().returnToInitialValues();
    }
}
