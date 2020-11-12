package coursework.frame;

import coursework.frame.menu.Menu;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    public MenuPanel(Window window, Dimension size) {
        super();
        setPreferredSize(size);
        Menu menu = new Menu();
        menu.setPreferredSize(size);
        add(menu);
        setVisible(true);
    }
}
