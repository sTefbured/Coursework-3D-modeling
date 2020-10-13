package coursework.frame;

import coursework.frame.menu.Menu;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    public MenuPanel() {
        super();
        setLayout(new FlowLayout());
        Menu menu = new Menu();
        add(menu);
        setVisible(true);
    }
}
