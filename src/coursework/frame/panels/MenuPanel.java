package coursework.frame.panels;

import coursework.frame.menus.tabbedMenu.Menu;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    private final Menu menu;

    public MenuPanel(Dimension size) {
        super();
        setPreferredSize(size);
        menu = new Menu();
        menu.setPreferredSize(size);
        add(menu);
        setVisible(true);
    }

    public void returnToInitialValues() {
        menu.returnToInitialValues();
    }
}
