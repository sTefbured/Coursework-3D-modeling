package coursework.frame;

import coursework.Main;

import javax.swing.*;
import java.awt.*;

class DrawingPanel extends JPanel {
    DrawingPanel(Dimension size) {
        super();
        setPreferredSize(size);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setStroke(new BasicStroke(3));
        Main.getModel().draw(graphics2D);
    }
}
